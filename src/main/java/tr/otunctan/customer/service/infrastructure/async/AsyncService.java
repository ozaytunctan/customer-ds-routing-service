package tr.otunctan.customer.service.infrastructure.async;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public interface AsyncService {

    <T> CompletableFuture<T> execute(Supplier<T> task);

    <T> CompletableFuture<T> execute(
            Supplier<T> task,
            AsyncOptions options);

    <T> CompletableFuture<List<T>> executeAll(
            Collection<Supplier<T>> tasks,
            AsyncOptions options);
}