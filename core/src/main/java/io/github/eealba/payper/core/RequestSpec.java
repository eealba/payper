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

/**
 * Interface representing a request specification.
 *
 * @param <R1> the type of the response
 * @param <R2> the type of the error response
 * @author Edgar Alba
 * @version 1.0
 * @since 1.0
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
     * Interface for specifying the PayPal Partner Attribution ID.
     *
     * @param <T> the type of the request specification
     */
    interface PayPalPartnerAttributionIdSpec<T> {

        /**
         * Sets the PayPal Partner Attribution ID for the request.
         *
         * @param payPalPartnerAttributionId the PayPal Partner Attribution ID
         * @return the request specification
         */
        T withPayPalPartnerAttributionId(String payPalPartnerAttributionId);
    }

    /**
     * Interface for specifying the PayPal Client Metadata ID.
     *
     * @param <T> the type of the request specification
     */
    interface PayPalClientMetadataIdSpec<T> {

        /**
         * Sets the PayPal Client Metadata ID for the request.
         *
         * @param payPalClientMetadataId the PayPal Client Metadata ID
         * @return the request specification
         */
        T withPayPalClientMetadataId(String payPalClientMetadataId);
    }

    /**
     * Interface for specifying the PayPal Auth Assertion.
     *
     * @param <T> the type of the request specification
     */
    interface PayPalAuthAssertionSpec<T> {

        /**
         * Sets the PayPal Auth Assertion for the request.
         *
         * @param payPalAuthAssertion the PayPal Auth Assertion
         * @return the request specification
         */
        T withPayPalAuthAssertion(String payPalAuthAssertion);
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
    interface Id2Spec<T> {

        /**
         * Sets the ID for the request.
         *
         * @param id the ID
         * @return the request specification
         */
        T withId2(String id);
    }

    /**
     * Interface for specifying the body of the request.
     *
     * @param <T>  the type of the request specification
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
    /**
     * Interface for specifying the pagination of the request.
     *
     * @param <T> the type of the request specification
     */
    interface PaginationSpec<T> {
        /**
         * Sets the page size for the request.
         *
         * @param pageSize the page size
         * @return the request specification
         */
        T withPageSize(int pageSize);

        /**
         * Sets the page number for the request.
         *
         * @param page the page number
         * @return the request specification
         */
        T withPage(int page);
        /**
         * Sets the total count requirement for the request.
         *
         * @param totalRequired the total count requirement
         * @return the request specification
         */
        T withTotalRequired(boolean totalRequired);

    }
    interface FieldsSpec<T> {
        /**
         * Sets the fields for the request.
         *
         * @param fields the fields
         * @return the request specification
         */
        T withFields(String fields);
    }
}