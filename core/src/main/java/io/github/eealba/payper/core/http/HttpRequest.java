package io.github.eealba.payper.core.http;

import java.net.URL;
import java.util.List;

/**
 * An HTTP request.
 * <br>
 * <br>
 * An HttpRequest instance is built through an {@link HttpRequestBuilder}
 * <br>
 * <br>
 * The following is an example of a GET request:<br>
 * <br>
 * <pre>
 *
 * {@code
 * HttpClient client = Http.newHttpClientBuilder().build();
 * }*
 * {@code
 * HttpRequest httpRequest = Http.newHttpRequestBuilder(HttpMethod.GET, new URL("http://foo.com/")).build();
 * }*
 * {@code
 * HttpResponse<String> resp = client.execute(request);
 * }*
 * </pre>
 *
 * @author Edgar Enrique Alba Barrile (eealba@gmail.com)
 * @since 1.0
 */
public interface HttpRequest {
    /**
     * The request headers that this request was (or will be) sent with.
     *
     * @return the headers array List
     */
    List<NameValuePair> headers();

    /**
     * Returns an HTTP Body to be send
     *
     * @return the http body
     */
    HttpBody body();

    /**
     * Returns the HTTP Method to be executed
     *
     * @return the HTTP Method
     */
    HttpMethod method();

    /**
     * The target url
     *
     * @return target url
     */
    URL url();

}
