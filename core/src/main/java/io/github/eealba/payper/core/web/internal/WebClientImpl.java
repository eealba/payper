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
package io.github.eealba.payper.core.web.internal;

import io.github.eealba.payper.core.PayperException;
import io.github.eealba.payper.core.web.Request;
import io.github.eealba.payper.core.web.Response;
import io.github.eealba.payper.core.web.WebClient;
import io.github.eealba.payper.core.web.WebClientConfig;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * Author: Edgar Alba
 */
class WebClientImpl implements WebClient {
    private final HttpClient client;
    private final Mapper mapper = MapperImpl.getInstance();

    WebClientImpl(WebClientConfig config) {
        var builder  = HttpClient.newBuilder();
        if (config.executor().isPresent()) {
            builder.executor(config.executor().get());
        }
        if (config.connectTimeout().isPresent()) {
            builder.connectTimeout(config.connectTimeout().get());
        }
        if (config.proxySelector().isPresent()){
            builder.proxy(config.proxySelector().get());
        }
        this.client = builder.build();
    }

    @Override
    public <T> CompletableFuture<Response<T>> sendAsync(Request request, Response.BodyHandler<T> responseBodyHandler) {
        var bodyHandler = HttpResponse.BodyHandlers.ofByteArray();
        return client.sendAsync(mapper.mapRequest(request), bodyHandler)
                .thenApply((HttpResponse<byte[]> v) -> new ByteArrayResponse<>(v, responseBodyHandler));
    }

    @Override
    public CompletableFuture<Response<Void>> sendAsync(Request request) {
        var httpBodyHandler = HttpResponse.BodyHandlers.discarding();
        var httpRequest = mapper.mapRequest(request);

        return client.sendAsync(httpRequest, httpBodyHandler).thenApply(EmptyResponseImpl::new);
    }

    @Override
    public <T> Response<T> send(Request request, Response.BodyHandler<T> bodyHandler){
        var httpBodyHandler = HttpResponse.BodyHandlers.ofByteArray();
        var httpRequest = mapper.mapRequest(request);

        return new ByteArrayResponse<>(exec(httpRequest, httpBodyHandler), bodyHandler);
    }

    @Override
    public Response<Void> send(Request request){
        var httpBodyHandler = HttpResponse.BodyHandlers.discarding();
        var httpRequest = mapper.mapRequest(request);

        return new EmptyResponseImpl<>(exec(httpRequest, httpBodyHandler));
    }

    private <T> HttpResponse<T> exec(HttpRequest request, HttpResponse.BodyHandler<T> bodyHandler) {
        try {
            return client.send(request, bodyHandler);
        } catch (IOException | InterruptedException e) {
            throw new PayperException(e);
        }
    }
}