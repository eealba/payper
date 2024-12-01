/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.payper.core;

import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * The interface PayperResponse.
 * This interface represents a response from a Payper request.
 *
 * @param <T>  the type of the entity in the response
 * @param <T2> the type of the error entity in the response
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface PayperResponse<T, T2> {

    /**
     * Gets the status code of the response.
     *
     * @return the status code
     */
    int statusCode();

    /**
     * Converts the response to an entity of type T.
     *
     * @return the entity
     */
    T toEntity();

    /**
     * Converts the response to an error entity of type T2.
     *
     * @return the error entity
     */
    T2 toErrorEntity();

    /**
     * Consumes the response without returning any entity.
     */
    void toVoid();

    /**
     * The interface PayperResponseSpec.
     * This interface provides methods to handle the response in different ways.
     *
     * @param <T>  the type of the entity in the response
     * @param <T2> the type of the error entity in the response
     */
    interface PayperResponseSpec<T, T2> {

        /**
         * Converts the response specification to a PayperResponse.
         *
         * @return the PayperResponse
         */
        PayperResponse<T, T2> toResponse();

        /**
         * Converts the response specification to a CompletableFuture.
         *
         * @return the CompletableFuture of PayperResponse
         */
        CompletableFuture<PayperResponse<T, T2>> toFuture();

        /**
         * Converts the response specification to an entity of type T.
         *
         * @return the entity
         */
        default T toEntity() {
            return toResponse().toEntity();
        }

        /**
         * Consumes the response specification without returning any entity.
         */
        default void toVoid() {
            toResponse().toVoid();
        }

        /**
         * Consumes the response specification using the provided consumer.
         *
         * @param consumer the consumer to handle the response
         */
        default void consume(Consumer<PayperResponse<T, T2>> consumer) {
            consumer.accept(toResponse());
        }

        /**
         * Consumes the response specification asynchronously using the provided consumer.
         *
         * @param consumer the consumer to handle the response
         */
        default void consumeAsync(Consumer<PayperResponse<T, T2>> consumer) {
            toFuture().thenAccept(consumer);
        }
    }

    /**
     * The interface BodyHandler.
     * This functional interface represents a handler for the response body.
     *
     * @param <T> the type of the entity in the response body
     */
    @FunctionalInterface
    interface BodyHandler<T> {

        /**
         * Applies the handler to the response body.
         *
         * @return a BiFunction that takes a Charset and a byte array and returns an entity of type T
         */
        BiFunction<Charset, byte[], T> apply();
    }

    /**
     * The class BodyHandlers.
     * This class provides factory methods for creating BodyHandler instances.
     */
    class BodyHandlers {

        /**
         * Returns a BodyHandler that discards the response body.
         *
         * @return a BodyHandler that discards the response body
         */
        public static PayperResponse.BodyHandler<Void> discarding() {
            return () -> null;
        }

        /**
         * Returns a BodyHandler that converts the response body to an entity of the specified class.
         *
         * @param <T>   the type of the entity
         * @param clazz the class of the entity
         * @return a BodyHandler that converts the response body to an entity of the specified class
         */
        public static <T> PayperResponse.BodyHandler<T> ofClass(Class<T> clazz) {
            return PayperProvider.provider().bodyHandlerOf(clazz);
        }

        public static PayperResponse.BodyHandler<String> ofString() {
            return () -> (Charset cs, byte[] body) -> new String(body, cs);
        }
    }
}