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
package io.github.eealba.payper.core.client.internal;

import io.github.eealba.payper.core.client.PayperRequest;
import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.core.client.ResponseSpec;
import io.github.eealba.payper.core.client.Spec;

import java.time.Instant;
import java.util.Collections;

/**
 * Abstract class representing a request specification.
 *
 * @param <T> the type of the request specification
 * @param <T2> the type of the request body
 * @param <R1> the type of the response entity
 * @param <R2> the type of the error entity
 * @since 1.0
 * @version 1.0
 * author Edgar Alba
 */
final class RequestSpecImpl<T, T2, R1, R2> implements RequestSpec<R1, R2>,
        RequestSpec.PreferSpec<T>,
        RequestSpec.PaypalRequestIdSpec<T>,
        RequestSpec.BodySpec<T, T2>,
        RequestSpec.IdSpec<T>,
        RequestSpec.Id2Spec<T>,
        RequestSpec.PayPalClientMetadataIdSpec<T>,
        RequestSpec.PayPalPartnerAttributionIdSpec<T>,
        RequestSpec.PayPalAuthAssertionSpec<T>,
        RequestSpec.PaginationSpec<T>,
        RequestSpec.FieldsSpec<T> {

    private final PayperRequest.Builder requestBuilder = PayperRequest.newBuilder();
    private final Spec<T, R1, R2> spec;

    /**
     * Constructs a request specification.
     *
        * @param spec the specification
     */
    RequestSpecImpl(Spec<T, R1, R2> spec) {
        this.spec = spec;
        requestBuilder.path(spec.path());
        spec.headers().orElse(Collections.emptyMap()).forEach(requestBuilder::header);
        var method = spec.getMethod();
        switch (method) {
            case GET -> requestBuilder.GET();
            case DELETE -> requestBuilder.DELETE();
            case POST -> requestBuilder.POST(PayperRequest.BodyPublishers.noBody());
        }
    }
    /**
     * Adds a prefer header to the request.
     *
     * @param prefer the prefer header value
     * @return the request specification
     */
    @Override
    public T withPrefer(Prefer prefer) {
        header("Prefer", prefer.getValue());
        return self();
    }
    /**
     * Adds a PayPal request ID to the request.
     *
     * @param paypalRequestId the PayPal request ID
     * @return the request specification
     */
    @Override
    public T withPaypalRequestId(String paypalRequestId) {
        header("Paypal-Request-Id", paypalRequestId);
        return self();
    }

    @Override
    public ResponseSpec<R1, R2> retrieve() {
        return new ResponseSpecImpl<>(spec.payper(), spec.entityHandler(), spec.errorHandler(), requestBuilder.build());


    }

    /**
     * Adds a body to the request.
     *
     * @param body the body of the request
     * @return the request specification
     */
    @Override
    public T withBody(T2 body) {
        requestBuilder.header("Content-Type", "application/json");
        var method = spec.getMethod();
        switch (method) {
            case POST -> requestBuilder.POST(PayperRequest.BodyPublishers.of(body));
            case PUT -> requestBuilder.PUT(PayperRequest.BodyPublishers.of(body));
            case PATCH -> requestBuilder.PATCH(PayperRequest.BodyPublishers.of(body));
        }
        return self();
    }
    /**
     * Adds an id to the request.
     *
     * @param id the id of the request
     * @return the request specification
     */
    @Override
    public T withId(String id) {
        pathParameter("id", id);
        return self();
    }
    /**
     * Adds an id to the request.
     *
     * @param id the id of the request
     * @return the request specification
     */
    @Override
    public T withId2(String id) {
        pathParameter("id2", id);
        return self();
    }


    @Override
    public T withPayPalClientMetadataId(String paypalClientMetadataId) {
        header("PayPal-Client-Metadata-Id", paypalClientMetadataId);
        return self();
    }

    @Override
    public T withPayPalPartnerAttributionId(String paypalPartnerAttributionId) {
        header("PayPal-Partner-Attribution-Id", paypalPartnerAttributionId);
        return self();
    }
    @Override
    public T withPayPalAuthAssertion(String paypalAuthAssertion) {
        header("PayPal-Auth-Assertion", paypalAuthAssertion);
        return self();
    }

    @SuppressWarnings("unchecked")
    T self() {
        return (T) this;
    }
    /**
     * Adds a query parameter to the request.
     *
     * @param name the name of the query parameter
     * @param value the value of the query parameter
     */
    public T query(String name, String value) {
        requestBuilder.query(name, value);
        return self();
    }
    public T query(String name, Instant value) {
        requestBuilder.query(name, String.valueOf(value));
        return self();
    }
    public T query(String name, Boolean value) {
        requestBuilder.query(name, String.valueOf(value));
        return self();
    }
    public T query(String name, Object value) {
        requestBuilder.query(name, String.valueOf(value));
        return self();
    }
    /**
     * Adds a path parameter to the request.
     *
     * @param name the name of the path parameter
     * @param value the value of the path parameter
     */
    public T pathParameter(String name, String value) {
        requestBuilder.pathParameter(name, value);
        return self();
    }

    /**
     * Adds a header to the request.
     *
     * @param name the name of the header
     * @param value the value of the header
     */
    public T header(String name, String value) {
        requestBuilder.header(name, value);
        return self();
    }

    @Override
    public T withPageSize(int pageSize) {
        query("page_size", String.valueOf(pageSize));
        return self();
    }

    @Override
    public T withPage(int page) {
        query("page", String.valueOf(page));
        return self();
    }

    @Override
    public T withTotalRequired(boolean totalRequired) {
        query("total_required", String.valueOf(totalRequired));
        return self();
    }
    @Override
    public T withFields(String fields) {
        query("fields", fields);
        return self();
    }
}
