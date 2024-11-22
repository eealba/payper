package io.github.eealba.payper.core.http;

/**
 * A Factory class to create {@link HttpBody}
 *
 * @author Edgar Enrique Alba Barrile (eealba@gmail.com)
 * @since 1.0
 */
public interface HttpBodyFactory {
    /**
     * Returns an empty body, ideal for GET requests.
     *
     * @return the empty {@link HttpBody}
     */
    public default HttpBody createEmptyBody() {
		return new HttpBody() {

			@Override
			public byte[] content() {
				return null;
			}

			@Override
			public String contentType() {
				return null;
			}

			@Override
			public long contentLength() {
				return 0;
			}

		};
	}

    /**
     * Returns an http body from a byte array.
     *
     * @param data        the byte array to build the HttpBody
     * @param contentType the content type of the body
     * @return The {@link HttpBody}
     */
    public HttpBody ofByteArray(byte[] data, String contentType);

    /**
     * Returns an http body from String.
     *
     * @param data        the String to build the HttpBody
     * @param contentType the content type of the body
     * @return The {@link HttpBody}
     */
    public HttpBody ofString(String data, String contentType);

    /**
     * Returns an http body from a Json String.
     *
     * @param data the String that represent a Json
     * @return The {@link HttpBody}
     */
    public HttpBody ofJsonString(String data);

    /**
     * Returns an http body in Json from a object.
     *
     * @param serializer the serializer object
     * @param data       the Object to build the HttpBody using the serializer
     * @return The {@link HttpBody}
     */
    public HttpBody ofJsonObject(Serializer serializer, Object data);

}
