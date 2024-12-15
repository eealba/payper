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
package io.github.eealba.payper.core.spec;

/**
 * Interface representing a request specification.
 *
 * @param <R1> the type of the response
 * @param <R2> the type of the error response
 * @author Edgar Alba
 * @since 1.0
 * @version 1.0
 */
public interface RequestSpec<R1, R2> {

    /**
     * Retrieves the response specification.
     *
     * @return the response specification
     */
    ResponseSpec<R1, R2> retrieve();

    /**
     * Interface for specifying the 'Prefer' header.
     *
     * @param <T> the type of the request specification
     */
    interface PreferSpec<T> {

        /**
         * Sets the 'Prefer' header for the request.
         *
         * @param prefer the 'Prefer' header value
         * @return the request specification
         */
        T withPrefer(String prefer);
    }

    /**
     * Interface for specifying the PayPal request ID.
     *
     * @param <T> the type of the request specification
     */
    interface PaypalRequestIdSpec<T> {

        /**
         * Sets the PayPal request ID for the request.
         *
         * @param paypalRequestId the PayPal request ID
         * @return the request specification
         */
        T withPaypalRequestId(String paypalRequestId);
    }

    /**
     * Interface for specifying the ID.
     *
     * @param <T> the type of the request specification
     */
    interface IdSpec<T> {

        /**
         * Sets the ID for the request.
         *
         * @param id the ID
         * @return the request specification
         */
        T withId(String id);
    }

    /**
     * Interface for specifying the body of the request.
     *
     * @param <T> the type of the request specification
     * @param <T2> the type of the body
     */
    interface BodySpec<T, T2> {

        /**
         * Sets the body for the request.
         *
         * @param body the body
         * @return the request specification
         */
        T withBody(T2 body);
    }
}