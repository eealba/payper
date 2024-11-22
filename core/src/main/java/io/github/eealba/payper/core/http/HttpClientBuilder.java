package io.github.eealba.payper.core.http;

/**
 * The HttpclientBuilder interface must be implemented by any class whose instances are intended to build an HttpClient.
 *
 * @author Edgar Enrique Alba Barrile (eealba@gmail.com)
 * @since 1.0
 */
public interface HttpClientBuilder {
    /**
     * Make an instance of {@link HttpClient} based on the properties set on this builder.
     *
     * @return the instance of {@link HttpClient}
     */
    HttpClient build();

    /**
     * Set a connection timeout
     *
     * @param milis Milliseconds timeout, 0 means no timeout
     * @return a reference to this object.
     */
    HttpClientBuilder connectTimeout(int milis);

    /**
     * Set a read timeout
     *
     * @param milis Milliseconds timeout, 0 means no timeout
     * @return a reference to this object.
     */
    HttpClientBuilder readTimeout(int milis);
}
