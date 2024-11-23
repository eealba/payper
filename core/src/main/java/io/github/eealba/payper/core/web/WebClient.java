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
 * The interface Payper.
 * This interface is used to send requests and receive responses.
 *
 * @since 1.0
 * @version 1.0
 *
 * @see Request
 * @see Response
 * @see Response.BodyHandler
 *
 * @author Edgar Alba
 */
public interface WebClient {
    /**
     * Send response.
     *
     * @param request the request
     * @return the response
     */
    Response<Void> send(Request request);
    /**
     * Send response.
     *
     * @param <T> the type parameter
     * @param request the request
     * @param bodyHandler the body handler
     * @return the response
     */
    <T> Response<T> send(Request request, Response.BodyHandler<T> bodyHandler);
    /**
     * Send async completable future.
     *
     * @param request the request
     * @return the completable future
     */
    CompletableFuture<Response<Void>> sendAsync(Request request);
    /**
     * Send async completable future.
     *
     * @param <T> the type parameter
     * @param request the request
     * @param bodyHandler the body handler
     * @return the completable future
     */
    <T> CompletableFuture<Response<T>> sendAsync(Request request, Response.BodyHandler<T> bodyHandler);

}
