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

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;

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
public abstract class RequestSpecImpl<T, T2, R1, R2> implements  RequestSpec<R1, R2>,
        RequestSpec.PreferSpec<T>,
        RequestSpec.PaypalRequestIdSpec<T>,
        RequestSpec.BodySpec<T, T2>,
        RequestSpec.IdSpec<T>,
        RequestSpec.PayPalClientMetadataIdSpec<T>,
        RequestSpec.PayPalPartnerAttributionIdSpec<T>,
        RequestSpec.PayPalAuthAssertionSpec<T> {

    private final PayperRequest.Builder requestBuilder = PayperRequest.newBuilder();
    private final Payper payper;
    private final Class<R1> clazz1;
    private final Class<R2> clazz2;

    /**
     * Constructs a request specification.
     *
     * @param payper the Payper instance
     * @param path the path of the request
     * @param clazz1 the class of the response entity
     * @param clazz2 the class of the error entity
     */
    public RequestSpecImpl(Payper payper, String path, Class<R1> clazz1, Class<R2> clazz2) {
        this.payper = payper;
        this.clazz1 = clazz1;
        this.clazz2 = clazz2;
        requestBuilder.path(path);
        var method = getMethod();
        switch (method) {
            case GET -> requestBuilder.GET();
            case DELETE -> requestBuilder.DELETE();
            case POST -> requestBuilder.POST(PayperRequest.BodyPublishers.noBody());
        }
    }
    /**
     * Gets the method of the request.
     *
     * @return the method of the request
     */
    protected abstract PayperRequest.Method getMethod();
    /**
     * Adds a prefer header to the request.
     *
     * @param prefer the prefer header value
     * @return the request specification
     */
    @Override
    public T withPrefer(String prefer) {
        header("Prefer", prefer);
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
        return new ResponseSpecImpl<>(payper, requestBuilder.build(), clazz1, clazz2);


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
        var method = getMethod();
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
     * @param fields the name of the query parameter
     * @param fields1 the value of the query parameter
     */
    protected void query(String fields, String fields1) {
        requestBuilder.query(fields, fields1);
    }
    /**
     * Adds a path parameter to the request.
     *
     * @param name the name of the path parameter
     * @param value the value of the path parameter
     */
    protected void pathParameter(String name, String value) {
        requestBuilder.pathParameter(name, value);
    }

    /**
     * Adds a header to the request.
     *
     * @param name the name of the header
     * @param value the value of the header
     */
    protected void header(String name, String value) {
        requestBuilder.header(name, value);
    }
}
