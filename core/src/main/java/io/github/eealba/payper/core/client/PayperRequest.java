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

import io.github.eealba.payper.core.web.Request;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

/**
 * The interface PayperRequest.
 * This interface represents a request in the Payper framework.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * PayperRequest request = PayperRequest.newBuilder()
 *     .uri(new URI("http://example.com"))
 *     .header("Content-Type", "application/json")
 *     .GET()
 *     .build();
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface PayperRequest {

    /**
     * Gets the relative path of the request.
     *
     * @return the relative path
     */
    String path();

    /**
     * Gets the HTTP method of the request.
     *
     * @return the HTTP method
     */
    Method method();

    /**
     * Gets the timeout duration of the request.
     *
     * @return an Optional containing the timeout duration, or an empty Optional if no timeout is set
     */
    Optional<Duration> timeout();

    /**
     * Gets the body publisher of the request.
     *
     * @return an Optional containing the body publisher, or an empty Optional if no body is set
     */
    Optional<BodyPublisher> bodyPublisher();

    /**
     * Gets the headers of the request.
     *
     * @return a map containing the headers
     */
    Map<String, String> headers();

    /**
     * The interface Builder.
     * This interface provides methods to build a PayperRequest.
     */
    interface Builder {

        /**
         * Sets the URI of the request.
         *
         * @param path the URI
         * @return the builder
         */
        Builder path(String path);

        /**
         * Adds a header to the request.
         *
         * @param name  the name of the header
         * @param value the value of the header
         * @return the builder
         */
        Builder header(String name, String value);

        /**
         * Adds multiple headers to the request.
         *
         * @param headers the headers
         * @return the builder
         */
        Builder headers(String... headers);

        /**
         * Adds a query parameter to the request.
         *
         * @param name  the name of the query parameter
         * @param value the value of the query parameter
         * @return the builder
         */
        Builder query(String name, String value);

        /**
         * Adds multiple query parameters to the request.
         *
         * @param headers the query parameters
         * @return the builder
         */
        Builder queries(String... headers);

        /**
         * Adds a path parameter to the request.
         *
         * @param name  the name of the path parameter
         * @param value the value of the path parameter
         * @return the builder
         */
        Builder pathParameter(String name, String value);

        /**
         * Adds multiple path parameters to the request.
         *
         * @param headers the path parameters
         * @return the builder
         */
        Builder pathParameters(String... headers);

        /**
         * Sets the timeout duration of the request.
         *
         * @param duration the timeout duration
         * @return the builder
         */
        Builder timeout(Duration duration);

        /**
         * Sets the HTTP method of the request to GET.
         *
         * @return the builder
         */
        Builder GET();

        /**
         * Sets the HTTP method of the request to POST.
         *
         * @param bodyPublisher the body publisher
         * @return the builder
         */
        Builder POST(BodyPublisher bodyPublisher);

        /**
         * Sets the HTTP method of the request to PATCH.
         *
         * @param bodyPublisher the body publisher
         * @return the builder
         */
        Builder PATCH(BodyPublisher bodyPublisher);

        /**
         * Sets the HTTP method of the request to PUT.
         *
         * @param bodyPublisher the body publisher
         * @return the builder
         */
        Builder PUT(BodyPublisher bodyPublisher);

        /**
         * Sets the HTTP method of the request to DELETE.
         *
         * @return the builder
         */
        Builder DELETE();

        /**
         * Builds the PayperRequest.
         *
         * @return the PayperRequest
         */
        PayperRequest build();
    }

    /**
     * Creates a new builder for PayperRequest.
     *
     * @return the builder
     */
    static Builder newBuilder() {
        return PayperProvider.provider().createPayperRequestBuilder();
    }

    /**
     * The enum Method.
     * This enum represents the HTTP methods.
     */
    enum Method {
        GET, POST, PUT, DELETE, PATCH
    }

    /**
     * The interface BodyPublisher.
     * This functional interface represents a body publisher for the request.
     */
    @FunctionalInterface
    interface BodyPublisher {

        /**
         * Applies the body publisher.
         *
         * @return the byte array representing the body
         */
        byte[] apply();
    }

    /**
     * The class BodyPublishers.
     * This class provides factory methods for creating BodyPublisher instances.
     */
    class BodyPublishers {

        /**
         * Creates a BodyPublisher for the given object.
         *
         * @param obj the object
         * @return the BodyPublisher
         */
        public static BodyPublisher of(Object obj) {
            return PayperProvider.provider().bodyPublisherOf(obj);
        }

        /**
         * Creates a BodyPublisher that represents an empty body.
         *
         * @return the BodyPublisher
         */
        public static BodyPublisher noBody() {
            return () -> new byte[0];
        }

        public static Request.BodyPublisher ofString(String body) {
            return () -> body.getBytes(StandardCharsets.UTF_8);
        }
    }
}