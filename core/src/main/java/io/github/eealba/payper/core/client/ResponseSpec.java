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
package io.github.eealba.payper.core.client;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Interface representing a response specification.
 *
 * @param <T> the type of the response entity
 * @param <T2> the type of the error entity
 * @since 1.0
 * @version 1.0
 * author Edgar Alba
 */
public interface ResponseSpec<T, T2> {

    /**
     * Converts the response to a Response object.
     *
     * @return the response object
     */
    Response<T, T2> toResponse();

    /**
     * Converts the response to a CompletableFuture.
     *
     * @return the CompletableFuture of the response
     */
    CompletableFuture<Response<T, T2>> toFuture();

    /**
     * Converts the response to an entity.
     *
     * @return the response entity
     */
    default T toEntity() {
        return toResponse().toEntity();
    }

    /**
     * Converts the response to an Optional entity.
     *
     * @return the Optional response entity
     */
    default Optional<T> toOptionalEntity() {
        return toResponse().toOptionalEntity();
    }

    /**
     * Converts the response to void.
     */
    default void toVoid() {
        toResponse().toVoid();
    }

    /**
     * Consumes the response using the provided consumer.
     *
     * @param consumer the consumer to process the response
     */
    default void consume(Consumer<Response<T, T2>> consumer) {
        consumer.accept(toResponse());
    }

    /**
     * Consumes the response asynchronously using the provided consumer.
     *
     * @param consumer the consumer to process the response
     */
    default void consumeAsync(Consumer<Response<T, T2>> consumer) {
        toFuture().thenAccept(consumer);
    }

    /**
     * Interface representing a response.
     *
     * @param <T> the type of the response entity
     * @param <T2> the type of the error entity
     * @since 1.0
     * @version 1.0
     * author Edgar Alba
     */
    interface Response<T, T2> {

        /**
         * Retrieves the status code of the response.
         *
         * @return the status code
         */
        int statusCode();

        /**
         * Converts the response to an entity.
         *
         * @return the response entity
         */
        T toEntity();

        /**
         * Converts the response to an error entity.
         *
         * @return the error entity
         */
        T2 toErrorEntity();

        /**
         * Converts the response to void.
         */
        void toVoid();

        /**
         * Converts the response to an Optional entity.
         *
         * @return the Optional response entity
         */
        Optional<T> toOptionalEntity();

        /**
         * Converts the response to an Optional error entity.
         *
         * @return the Optional error entity
         */
        Optional<T2> toOptionalErrorEntity();
        /**
         * Checks if the response is an error.
         *
         * @return true if the response is an error, false otherwise
         */
        default boolean isError(){
            return statusCode() >= 400;
        }
        /**
         * Checks if the response is successful.
         *
         * @return true if the response is successful, false otherwise
         */
        default boolean isSuccessful(){
            return statusCode() >= 200 && statusCode() < 300;
        }
        /**
         * Checks if the response is a client error.
         *
         * @return true if the response is a client error, false otherwise
         */
        default boolean isClientError(){
            return statusCode() >= 400 && statusCode() < 500;
        }
        /**
         * Checks if the response is a server error.
         *
         * @return true if the response is a server error, false otherwise
         */
        default boolean isServerError(){
            return statusCode() >= 500;
        }

    }
}