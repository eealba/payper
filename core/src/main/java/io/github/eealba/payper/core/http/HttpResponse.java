package io.github.eealba.payper.core.http;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * An HTTP response.
 * <p>
 * An HttpResponse is not created directly, but is returned as the result of sending an HttpRequest.
 * An HttpResponse is available when the response status code and headers have been received,
 * and typically after the entire response body has also been received.
 *
 * @param <T> the type parameter
 * @author Edgar Enrique Alba Barrile (eealba@gmail.com)
 * @since 1.0
 */
public interface HttpResponse<T> {
    /**
     * Status code int.
     *
     * @return the int
     */
    int statusCode();

    /**
     * Returns the body as an Entity type T
     *
     * @return Returns the body
     */
    T body();

    /**
     * Returns the body as String
     *
     * @return An optional with a body as String
     */
    default Optional<String> bodyAsString() {
		String str = null;
		Optional<byte[]> body = bodyAsBytes();
		if (body.isPresent())
			str = new String(body.get(), StandardCharsets.UTF_8);
		return Optional.ofNullable(str);
	}

    /**
     * Returns the body as byte array
     *
     * @return An optional with a body as byte array
     */
    Optional<byte[]> bodyAsBytes();

    /**
     * Returns the received response headers
     *
     * @return the response headers
     */
    List<NameValuePair> headers();

    /**
     * Returns the specific  response header if exists
     *
     * @param name The header name
     * @return the header value
     */
    default String header(String name) {
		for (NameValuePair nv : headers()) {
			if (nv.getName().equalsIgnoreCase(name))
				return nv.getValue();
		}
		return null;
	}
}
