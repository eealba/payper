package io.github.eealba.payper.core.http;

/**
 * A builder of {@link HttpRequest HttpRequests}
 * <br>
 * Instances of HttpRequestBuilderr are created by calling
 * {@link Http#newHttpRequestBuilder}
 * <p>
 * <p>
 * Each of the setter methods modifies the state of the builder and returns the
 * same instance.
 * Builders are not thread-safe and should not be used concurrently from
 * multiple threads without external synchronization.
 *
 * @author Edgar Enrique Alba Barrile (eealba@gmail.com)
 * @since 1.0
 */
public interface HttpRequestBuilder {
    /**
     * Adds the given name value pair to the set of headers for this request.
     *
     * @param name  the header name
     * @param value the header value
     * @return http request builder
     */
    HttpRequestBuilder header(String name, String value);

    /**
     * Query parameter http request builder.
     *
     * @param name  the name
     * @param value the value
     * @return http request builder
     */
    HttpRequestBuilder queryParameter(String name, String value);

    /**
     * Path parameter http request builder.
     *
     * @param name  the name
     * @param value the value
     * @return http request builder
     */
    HttpRequestBuilder pathParameter(String name, String value);

    /**
     * Body http request builder.
     *
     * @param body the body
     * @return http request builder
     */
    HttpRequestBuilder body(HttpBody body);

    /**
     * Returns a new {@link HttpRequest} built from the current state of this
     * builder.
     *
     * @return a new {@link HttpRequest}
     */
    HttpRequest build();

}
