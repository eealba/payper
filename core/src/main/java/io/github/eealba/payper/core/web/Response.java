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
 * The type Response.
 * This class is used to handle responses in the Payper library.
 *
 * @since 1.0
 * @version 1.0
 *
 * @see Headers
 * @see Request
 * @see BodyHandler
 *
 */
public interface Response<T> {
    /**
     * Status code int.
     *
     * @return the int
     */
    int statusCode();
    /**
     * Body t.
     *
     * @return the t
     */
    T body();

    /**
     * Headers headers.
     *
     * @return the headers
     */
    Headers headers();
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

    @FunctionalInterface
    interface BodyHandler<T> {
        Function<byte[], T> apply(Response<T> response);
    }

    class BodyHandlers {
        public static BodyHandler<Void> discarding() {
            return body -> null;
        }

        public static BodyHandler<String> ofString() {
            return body -> (bytes ) -> new String (bytes, body.charset());
        }
        public static BodyHandler<byte[]> ofBytes() {
            return body -> (bytes ) -> bytes;
        }
    }
}
