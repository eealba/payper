package io.github.eealba.payper.core.http.internal;


import io.github.eealba.paypal.core.http.*;

import java.net.URL;

/**
 * The type Http provider.
 */
class HttpProviderImpl extends HttpProvider {

	@Override
	public HttpClientBuilder createHttpClientBuilder() {
		return new HttpClientImpl.Builder();
	}

	@Override
	public HttpRequestBuilder createHttpRequestBuilder(HttpMethod method, URL url) {
		return new HttpRequestImpl.Builder(method, url);
	}

	@Override
	public HttpBodyFactory createHttpBodyFactory() {
		return HttpBodyFactoryImpl.INSTANCE;
	}

}
