/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.payper.core.web;

import java.util.concurrent.CompletableFuture;

/**
 * The interface WebClient.
 * This interface is used to send requests and receive responses.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Synchronous request
 * WebClient client = WebClient.newWebClient();
 * Request request = Request.newBuilder().uri("http://example.com").GET().build();
 * Response<String> response = client.send(request, Response.BodyHandlers.ofString());
 * System.out.println(response.body());
 *
 * // Asynchronous request
 * CompletableFuture<Response<String>> futureResponse = client.sendAsync(request, Response.BodyHandlers.ofString());
 * futureResponse.thenAccept(resp -> System.out.println(resp.body()));
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 *
 * @see Request
 * @see Response
 * @see Response.BodyHandler
 */
public interface WebClient {
    /**
     * Creates a new WebClient with the default configuration.
     *
     * @return a new WebClient instance
     */
    static WebClient newWebClient() {
        return WebProvider.provider().createWebClient(WebClientConfig.builder().build());
    }

    /**
     * Creates a new WebClient with the specified configuration.
     *
     * @param config the WebClient configuration
     * @return a new WebClient instance
     */
    static WebClient newWebClient(WebClientConfig config) {
        return WebProvider.provider().createWebClient(config);
    }

    /**
     * Sends a request and receives a response.
     *
     * @param request the request to be sent
     * @return the response received
     */
    Response<Void> send(Request request);

    /**
     * Sends a request and receives a response with a body handler.
     *
     * @param <T> the type of the response body
     * @param request the request to be sent
     * @param bodyHandler the body handler to handle the response body
     * @return the response received
     */
    <T> Response<T> send(Request request, Response.BodyHandler<T> bodyHandler);

    /**
     * Sends a request asynchronously and receives a response.
     *
     * @param request the request to be sent
     * @return a CompletableFuture that will complete with the response
     */
    CompletableFuture<Response<Void>> sendAsync(Request request);

    /**
     * Sends a request asynchronously and receives a response with a body handler.
     *
     * @param <T> the type of the response body
     * @param request the request to be sent
     * @param bodyHandler the body handler to handle the response body
     * @return a CompletableFuture that will complete with the response
     */
    <T> CompletableFuture<Response<T>> sendAsync(Request request, Response.BodyHandler<T> bodyHandler);
}