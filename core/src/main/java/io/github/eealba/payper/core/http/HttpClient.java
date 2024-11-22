package io.github.eealba.payper.core.http;

import java.io.IOException;

/**
 * This interface represents the basic contract for executing HTTP requests. It does not impose restrictions
 * or particular details on the process of executing the request.
 *
 * @author Edgar Enrique Alba Barrile (eealba@gmail.com)
 * @since 1.0
 */

public interface HttpClient {
    /**
     * Executes HTTP request
     *
     * @param request the request to execute
     * @return the response to the request. Receives the response body as a String
     * @throws IOException in case of a problem or the connection was aborted
     */
    HttpResponse<String> execute(HttpRequest request) throws IOException;

    /**
     * Executes HTTP request
     *
     * @param <T>          the type of the entity
     * @param request      the request to execute
     * @param deserializer The instance that will transform the response into an entity T
     * @return the response to the request. Receives the response body as a T
     * @throws IOException in case of a problem or the connection was aborted
     */
    <T> HttpResponse<T> execute(HttpRequest request, Deserializer<T> deserializer) throws IOException;
}
