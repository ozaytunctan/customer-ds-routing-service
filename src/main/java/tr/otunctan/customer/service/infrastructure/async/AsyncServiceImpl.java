package tr.otunctan.customer.service.infrastructure.async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class AsyncServiceImpl implements AsyncService {

    private final Executor executor;
    private final ScheduledExecutorService scheduler;

    public AsyncServiceImpl(
            Executor executor,
            ScheduledExecutorService scheduler) {

        this.executor = executor;
        this.scheduler = scheduler;
    }

    @Override
    public <T> CompletableFuture<T> execute(Supplier<T> task) {
        return execute(task, new AsyncOptions());
    }

    @Override
    public <T> CompletableFuture<T> execute(
            Supplier<T> task,
            AsyncOptions options) {

        return executeWithRetry(task, options, 0);
    }

    private <T> CompletableFuture<T> executeWithRetry(
            Supplier<T> task,
            AsyncOptions options,
            int retryCount) {

        CompletableFuture<T> future =
                CompletableFuture.supplyAsync(task, executor)
                        .orTimeout(
                                options.getTimeout().toMillis(),
                                TimeUnit.MILLISECONDS);

        return future.handle((result, throwable) -> {

            if (throwable == null)
                return CompletableFuture.completedFuture(result);

            Throwable cause =
                    throwable instanceof CompletionException
                            ? throwable.getCause()
                            : throwable;

            if (retryCount < options.getRetryCount() && options.getRetryPredicate().test(cause)) {

                CompletableFuture<T> retryFuture =
                        new CompletableFuture<>();

                scheduler.schedule(() ->

                        executeWithRetry(
                                task,
                                options,
                                retryCount + 1)

                                .whenComplete((r, e) -> {

                                    if (e == null)
                                        retryFuture.complete(r);
                                    else
                                        retryFuture.completeExceptionally(e);

                                })

                        ,
                        options.getRetryDelay().toMillis(),
                        TimeUnit.MILLISECONDS);

                return retryFuture;
            }

            CompletableFuture<T> failed =
                    new CompletableFuture<>();

            failed.completeExceptionally(cause);

            return failed;

        }).thenCompose(x -> x);
    }

    @Override
    public <T> CompletableFuture<List<T>> executeAll(
            Collection<Supplier<T>> tasks,
            AsyncOptions options) {

        List<CompletableFuture<T>> futures = new ArrayList<>();

        for (Supplier<T> task : tasks) {

            futures.add(execute(task, options));
        }

        return CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v ->

                        futures.stream()
                                .map(CompletableFuture::join)
                                .toList());
    }

}