package io.github.eealba.payper.core.rest;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface ResponseSpec <T, T2> {
    Response<T, T2> toResponse();
    CompletableFuture<Response<T, T2>> toFuture();

    default T toEntity() {
        return toResponse().toEntity();
    }

    default void toVoid() {
        toResponse().toVoid();

    }

    default void consume(Consumer<Response<T, T2>> consumer) {
        consumer.accept(toResponse());

    }

    default void consumeAsync(Consumer<Response<T, T2>> consumer) {
        toFuture().thenAccept(consumer);
    }

}
