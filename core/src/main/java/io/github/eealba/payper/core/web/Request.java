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
package io.github.eealba.payper.core.web;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;

/**
 * The interface Request.
 * This interface is used to create and configure HTTP requests.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Request request = Request.newBuilder()
 *     .uri(URI.create("http://example.com"))
 *     .header("Content-Type", "application/json")
 *     .timeout(Duration.ofSeconds(10))
 *     .GET()
 *     .build();
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 *
 * @see URI
 * @see Duration
 * @see Headers
 */
public interface Request {
    /**
     * Gets the URI of the request.
     *
     * @return the URI
     */
    URI uri();

    /**
     * Gets the method of the request.
     *
     * @return the method
     */
    Method method();

    /**
     * Gets the timeout duration of the request.
     *
     * @return the optional timeout duration
     */
    Optional<Duration> timeout();

    /**
     * Gets the body publisher of the request.
     *
     * @return the optional body publisher
     */
    Optional<Request.BodyPublisher> bodyPublisher();

    /**
     * Gets the headers of the request.
     *
     * @return the headers
     */
    Headers headers();

    /**
     * The interface Builder.
     * This interface is used to build and configure a Request.
     */
    interface Builder {
        /**
         * Sets the URI of the request.
         *
         * @param uri the URI
         * @return the builder
         */
        Request.Builder uri(URI uri);

        /**
         * Adds a header to the request.
         *
         * @param name the header name
         * @param value the header value
         * @return the builder
         */
        Request.Builder header(String name, String value);

        /**
         * Adds multiple headers to the request.
         *
         * @param headers the headers
         * @return the builder
         */
        Request.Builder headers(String... headers);

        /**
         * Sets the timeout duration of the request.
         *
         * @param duration the timeout duration
         * @return the builder
         */
        Request.Builder timeout(Duration duration);

        /**
         * Sets the request method to GET.
         *
         * @return the builder
         */
        Request.Builder GET();

        /**
         * Sets the request method to POST with a body publisher.
         *
         * @param bodyPublisher the body publisher
         * @return the builder
         */
        Request.Builder POST(Request.BodyPublisher bodyPublisher);

        /**
         * Sets the request method to PATCH with a body publisher.
         *
         * @param bodyPublisher the body publisher
         * @return the builder
         */
        Request.Builder PATCH(Request.BodyPublisher bodyPublisher);

        /**
         * Sets the request method to PUT with a body publisher.
         *
         * @param bodyPublisher the body publisher
         * @return the builder
         */
        Request.Builder PUT(Request.BodyPublisher bodyPublisher);

        /**
         * Sets the request method to DELETE.
         *
         * @return the builder
         */
        Request.Builder DELETE();

        /**
         * Builds the request.
         *
         * @return the request
         */
        Request build();
    }

    /**
     * Creates a new request builder.
     *
     * @return the request builder
     */
    static Request.Builder newBuilder() {
        return WebProvider.provider().createRequestBuilder();
    }

    /**
     * The interface BodyPublisher.
     * This interface is used to publish the body of the request.
     */
    @FunctionalInterface
    interface BodyPublisher {
        /**
         * Gets the body bytes.
         *
         * @return the body bytes
         */
        byte[] get();
    }

    /**
     * The class BodyPublishers.
     * This class provides various body publishers for publishing the request body.
     */
    class BodyPublishers {
        /**
         * Returns a body publisher that publishes a string.
         *
         * @param str the string
         * @return the body publisher
         */
        public static Request.BodyPublisher ofString(String str) {
            return () -> str.getBytes(StandardCharsets.UTF_8);
        }

        /**
         * Returns a body publisher that publishes a string with a specified charset.
         *
         * @param s the string
         * @param charset the charset
         * @return the body publisher
         */
        public static Request.BodyPublisher ofString(String s, Charset charset) {
            return () -> s.getBytes(charset);
        }

        /**
         * Returns a body publisher that publishes a byte array.
         *
         * @param buf the byte array
         * @return the body publisher
         */
        public static Request.BodyPublisher ofByteArray(byte[] buf) {
            return () -> buf;
        }

        /**
         * Returns a body publisher that publishes no body.
         *
         * @return the body publisher
         */
        public static BodyPublisher noBody() {
            return () -> new byte[0];
        }
    }
}