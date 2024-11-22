package io.github.eealba.payper.core.http.internal;

import io.github.eealba.paypal.core.http.HttpBody;
import io.github.eealba.paypal.core.http.HttpBodyFactory;
import io.github.eealba.paypal.core.http.Serializer;

import java.nio.charset.StandardCharsets;

/**
 * The enum Http body factory.
 */
enum HttpBodyFactoryImpl implements HttpBodyFactory {
    /**
     * Instance http body factory.
     */
    INSTANCE;

	private static final String APPLICATION_JSON = "application/json";

	@Override
	public HttpBody ofByteArray(byte[] data, String contentType) {
		return new HttpBodyImpl(data, contentType);
	}

	@Override
	public HttpBody ofString(String data, String contentType) {
		return new HttpBodyImpl(data.getBytes(StandardCharsets.UTF_8), contentType);
	}

	@Override
	public HttpBody ofJsonString(String data) {
		return new HttpBodyImpl(data.getBytes(StandardCharsets.UTF_8), APPLICATION_JSON);
	}

	@Override
	public HttpBody ofJsonObject(Serializer serializer, Object data) {
		return new HttpBodyImpl(serializer.serializeToByteArray(data), APPLICATION_JSON);
	}

}
