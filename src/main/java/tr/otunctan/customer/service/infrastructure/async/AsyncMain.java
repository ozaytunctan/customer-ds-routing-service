package tr.otunctan.customer.service.infrastructure.async;

import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class AsyncMain {
    public static void main(String[] args) {

        Executor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                6,
                5000L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(30)
        );
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

        AsyncService asyncService = new AsyncServiceImpl(
                threadPoolExecutor,
                scheduledExecutorService
        );

        Supplier<String> task1 = () -> {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hi";
        };
        Supplier<String> task2 = () -> {
            return "hi";
        };
        Supplier<String> task3 = () -> {
            return "hi";
        };

        CompletableFuture<List<String>> results = asyncService.executeAll(
                List.of(task1, task2, task3),
                new AsyncOptions()
                        .timeout(Duration.ofSeconds(3))
                        .retry(0)
                        .retryDelay(Duration.ofMillis(2000))
                        .retryPredicate((t) -> t instanceof SocketTimeoutException)
        );
        List<String> join = results.join();
        System.out.println();
    }
}
