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


import java.io.InputStream;

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

    @FunctionalInterface
    interface BodyHandler<T> {
        T apply(InputStream body);
    }

    class BodyHandlers {
    public static BodyHandler<Void> discarding() {
            return body -> null;
        }
    }
}
