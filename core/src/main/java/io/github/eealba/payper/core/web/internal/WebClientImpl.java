package io.github.eealba.payper.core.web.internal;

import io.github.eealba.payper.core.PayperException;
import io.github.eealba.payper.core.web.Request;
import io.github.eealba.payper.core.web.Response;
import io.github.eealba.payper.core.web.WebClient;
import io.github.eealba.payper.core.web.WebClientConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

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
        this.client = builder.build();

    }


    /**
     * Send async completable future.
     *
     * @param request             the request
     * @param responseBodyHandler the response body handler
     * @return the completable future
     */
    @Override
    public <T> CompletableFuture<Response<T>> sendAsync(Request request, Response.BodyHandler<T> responseBodyHandler) {
        var bodyHandler = HttpResponse.BodyHandlers.ofInputStream();
        return client.sendAsync(mapper.mapRequest(request), bodyHandler)
                .thenApply((HttpResponse<InputStream> v) -> new InputStreamResponse<>(v, responseBodyHandler));
    }

    @Override
    public CompletableFuture<Response<Void>> sendAsync(Request request) {
        var httpBodyHandler = HttpResponse.BodyHandlers.discarding();
        var httpRequest = mapper.mapRequest(request);

        return client.sendAsync(httpRequest, httpBodyHandler).thenApply(EmptyResponseImpl::new);

    }

    @Override
    public <T> Response<T> send(Request request, Response.BodyHandler<T> bodyHandler){
        var httpBodyHandler = HttpResponse.BodyHandlers.ofInputStream();
        var httpRequest = mapper.mapRequest(request);

        return new InputStreamResponse<>(exec(httpRequest, httpBodyHandler), bodyHandler);
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
