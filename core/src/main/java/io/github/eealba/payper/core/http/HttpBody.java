package io.github.eealba.payper.core.http;

/**
 * This interface represents the basic contract of an http body.
 *
 * @author Edgar Enrique Alba Barrile (eealba@gmail.com)
 * @since 1.0
 */
public interface HttpBody {
    /**
     * The content of the body in a byte array.
     *
     * @return the body content
     */
    byte[] content();

    /**
     * The content type of the body
     *
     * @return The content type
     */
    String contentType();

    /**
     * The content length of the body
     *
     * @return the content length
     */
    long contentLength();

}
