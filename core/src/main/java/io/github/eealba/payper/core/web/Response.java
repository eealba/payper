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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

/**
 * The interface Response.
 * This interface is used to handle responses in the Payper library.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * WebClient client = WebClient.newWebClient();
 * Request request = Request.newBuilder().uri("http://example.com").GET().build();
 * Response<String> response = client.send(request, Response.BodyHandlers.ofString());
 * System.out.println("Status code: " + response.statusCode());
 * System.out.println("Response body: " + response.body());
 * }</pre>
 *
 * @param <T> the type of the response body
 *
 * @since 1.0
 * @version 1.0
 *
 * @see Headers
 * @see Request
 * @see Response.BodyHandler
 */
public interface Response<T> {
    /**
     * Gets the status code of the response.
     *
     * @return the status code
     */
    int statusCode();

    /**
     * Gets the body of the response.
     *
     * @return the response body
     */
    T body();

    /**
     * Gets the headers of the response.
     *
     * @return the response headers
     */
    Headers headers();

    /**
     * Gets the charset of the response based on the Content-Type header.
     *
     * @return the charset
     */
    default Charset charset() {
        String contentType = headers().getValue("Content-Type").orElse("text/html; charset=utf-8");
        String charset = "utf-8"; // default charset
        String[] parts = contentType.split(";");
        for (String part : parts) {
            part = part.trim();
            if (part.toLowerCase().startsWith("charset=")) {
                charset = part.substring("charset=".length());
                break;
            }
        }
        try {
            return Charset.forName(charset);
        } catch (Exception e) {
            return StandardCharsets.UTF_8;
        }
    }

    /**
     * The interface BodyHandler.
     * This interface is used to handle the body of the response.
     *
     * @param <T> the type of the response body
     */
    @FunctionalInterface
    interface BodyHandler<T> {
        /**
         * Applies the body handler to the response.
         *
         * @param response the response
         * @return a function that converts the response body bytes to the specified type
         */
        Function<byte[], T> apply(Response<T> response);
    }

    /**
     * The class BodyHandlers.
     * This class provides various body handlers for handling the response body.
     */
    class BodyHandlers {
        /**
         * Returns a body handler that discards the response body.
         *
         * @return a body handler that discards the response body
         */
        public static BodyHandler<Void> discarding() {
            return body -> null;
        }

        /**
         * Returns a body handler that converts the response body to a string.
         *
         * @return a body handler that converts the response body to a string
         */
        public static BodyHandler<String> ofString() {
            return body -> (bytes) -> new String(bytes, body.charset());
        }

        /**
         * Returns a body handler that returns the response body as a byte array.
         *
         * @return a body handler that returns the response body as a byte array
         */
        public static BodyHandler<byte[]> ofBytes() {
            return body -> (bytes) -> bytes;
        }
    }
}