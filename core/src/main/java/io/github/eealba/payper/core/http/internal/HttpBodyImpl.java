package io.github.eealba.payper.core.http.internal;

import io.github.eealba.paypal.core.http.HttpBody;

import java.util.Objects;

/**
 * The type Http body.
 */
final class HttpBodyImpl implements HttpBody {

	private final byte[] data;
	private final String contentType;

    /**
     * Instantiates a new Http body.
     *
     * @param data        the data
     * @param contentType the content type
     */
    HttpBodyImpl(byte[] data, String contentType) {
		this.data = Objects.requireNonNull(data);
		this.contentType = Objects.requireNonNull(contentType);
	}

	@Override
	public byte[] content() {
		return data;
	}

	@Override
	public String contentType() {
		return contentType;
	}

	@Override
	public long contentLength() {
		return data.length;
	}

}
