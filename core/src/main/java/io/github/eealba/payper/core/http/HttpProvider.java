package io.github.eealba.payper.core.http;


import io.github.eealba.paypal.core.util.Providers;

import java.net.URL;
import java.util.ServiceLoader;

/**
 * Service provider for Http objects.
 *
 * <p>
 * All the methods in this class are safe for use by multiple concurrent
 * threads.
 *
 * @author Edgar Enrique Alba Barrile (eealba@gmail.com)
 * @see ServiceLoader
 * @since 1.0
 */
public abstract class HttpProvider {
    /**
     * A constant representing the name of the default {@code HttpProvider}
     * implementation class.
     */
    private static final String DEFAULT_PROVIDER = "io.github.eealba.paypal.core.http.internal.HttpProviderImpl";

    /**
     * Creates a HttpProvider object. If there are no available service providers,
     * this method returns the default service provider.
     *
     * @return a JSON provider
     */
    public static HttpProvider provider() {
        return Providers.getProvider(HttpProvider.class, DEFAULT_PROVIDER);
    }

    /**
     * Creates a HttpClientBuilder.
     *
     * @return a HttpClientBuilder
     */
    public abstract HttpClientBuilder createHttpClientBuilder();

    /**
     * Creates a HttpRequestBuilder.
     *
     * @param method the request method
     * @param url    the target url
     * @return a HttpRequestBuilder
     */
    public abstract HttpRequestBuilder createHttpRequestBuilder(HttpMethod method, URL url);

    /**
     * Creates a HttpBodyFactory
     *
     * @return a HttpBodyFactory
     */
    public abstract HttpBodyFactory createHttpBodyFactory();

}
