package tr.otunctan.customer.service.infrastructure.async;

import java.time.Duration;
import java.util.function.Predicate;

public class AsyncOptions {

    private Duration timeout = Duration.ofSeconds(30);

    private int retryCount = 0;

    private Duration retryDelay = Duration.ofSeconds(1);

    private Predicate<Throwable> retryPredicate = ex -> true;

    public Duration getTimeout() {
        return timeout;
    }

    public AsyncOptions timeout(Duration timeout) {
        this.timeout = timeout;
        return this;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public AsyncOptions retry(int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public Duration getRetryDelay() {
        return retryDelay;
    }

    public AsyncOptions retryDelay(Duration retryDelay) {
        this.retryDelay = retryDelay;
        return this;
    }

    public Predicate<Throwable> getRetryPredicate() {
        return retryPredicate;
    }

    public AsyncOptions retryPredicate(Predicate<Throwable> retryPredicate) {
        this.retryPredicate = retryPredicate;
        return this;
    }
}