package io.github.eealba.payper.core.http.internal;

import io.github.eealba.paypal.core.http.Deserializer;
import io.github.eealba.paypal.core.http.HttpResponse;
import io.github.eealba.paypal.core.http.NameValuePair;

import java.util.List;
import java.util.Optional;

/**
 * The type Http response.
 *
 * @param <T> the type parameter
 */
class HttpResponseImpl<T> implements HttpResponse<T> {
	private final int statusCode;
	private final List<NameValuePair> headers;
	private final byte[] body;
	private final Deserializer<T> deserializer;

    /**
     * Instantiates a new Http response.
     *
     * @param statusCode   the status code
     * @param headers      the headers
     * @param body         the body
     * @param deserializer the deserializer
     */
    HttpResponseImpl(int statusCode, List<NameValuePair> headers, byte[] body,  Deserializer<T> deserializer) {
		this.statusCode = statusCode;
		this.headers = headers;
		this.body = body;
		this.deserializer = deserializer;
	}

	@Override
	public int statusCode() {
		return this.statusCode;
	}

	@Override
	public List<NameValuePair> headers() {
		return this.headers;
	}

	@Override
	public Optional<byte[]> bodyAsBytes() {
		return Optional.ofNullable(this.body);
	}

	@Override
	public T body() {
		if (!bodyAsBytes().isPresent()){
			return null;
		}
		return deserializer.deserialize(bodyAsBytes().get());
	}
}
