package io.github.eealba.payper.core.http;

import java.net.URL;

/**
 * This class consists exclusively of static methods that return the main
 * objects to interact with the Http client API.
 *
 * @author Edgar Enrique Alba Barrile (eealba@gmail.com)
 * @since 1.0
 */
public class Http {

	private Http() {

	}

    /**
     * Return a new instance of an HttpClientBuilder
     *
     * @return the {@link HttpClientBuilder} instance
     * @see HttpClientBuilder
     * @since 1.0
     */
    public static HttpClientBuilder newHttpClientBuilder() {
		return HttpProvider.provider().createHttpClientBuilder();
	}

    /**
     * Return a new instance of an HttpRequestBuilder
     *
     * @param method the HTTP Method to execute @see HttpMethod
     * @param url    the target url
     * @return the {@link HttpRequestBuilder} instance
     * @see HttpRequestBuilder
     * @since 1.0
     */
    public static HttpRequestBuilder newHttpRequestBuilder(HttpMethod method, URL url) {
		return HttpProvider.provider().createHttpRequestBuilder(method, url);
	}

    /**
     * Return a new instance of an HttpBodyFactory
     *
     * @return the {@link HttpBodyFactory} instance
     * @see HttpBodyFactory
     * @since 1.0
     */
    public static HttpBodyFactory newHttpBodyFactory() {
		return HttpProvider.provider().createHttpBodyFactory();

	}

}
