package io.github.eealba.payper.subscriptions.v1.api;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface ResponseSpec <T, T2> {
    T toEntity();
    void toVoid();
    Response<T, T2> toResponse();
    CompletableFuture<Response<T, T2>> toFuture();
    void consume(Consumer<Response<T, T2>> consumer);
    void consumeAsync(Consumer<Response<T, T2>> consumer);
}
