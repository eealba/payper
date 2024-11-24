package io.github.eealba.payper.core;


import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;

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
        BiFunction<Charset,byte[], T> apply();
    }

    class BodyHandlers {
        public static PayperResponse.BodyHandler<Void> discarding() {
            return () -> null;
        }

        public static <T> PayperResponse.BodyHandler<T> ofClass(Class<T> clazz) {
            return PayperProvider.provider().bodyHandlerOfString(clazz);
        }
    }

}
