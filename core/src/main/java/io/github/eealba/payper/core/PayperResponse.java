package io.github.eealba.payper.core;

import io.github.eealba.payper.core.web.Response;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public interface PayperResponse<T,T2> {
    int statusCode();
    T toEntity();
    T2 toErrorEntity();
    void toVoid();
    interface PayperResponseSpec <T, T2> {
        PayperResponse<T, T2> toResponse();
        CompletableFuture<PayperResponse<T, T2>> toFuture();

        default T toEntity() {
            return toResponse().toEntity();
        }

        default void toVoid() {
            toResponse().toVoid();

        }

        default void consume(Consumer<PayperResponse<T, T2>> consumer) {
            consumer.accept(toResponse());

        }

        default void consumeAsync(Consumer<PayperResponse<T, T2>> consumer) {
            toFuture().thenAccept(consumer);
        }

    }
    @FunctionalInterface
    interface BodyHandler<T> {
        Function<byte[], T> apply(Response<T> response);
    }

    class BodyHandlers {
        public static Response.BodyHandler<Void> discarding() {
            return body -> null;
        }

        public static <T> Response.BodyHandler<T> ofString(Class<T> clazz) {
            return PayperProvider.provider().bodyHandlerOfString(clazz);
        }
    }

}
