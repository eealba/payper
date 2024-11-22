package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.BodyHandler;
import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.Request;
import io.github.eealba.payper.core.Response;

import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

class PayperImpl implements Payper {
    private final HttpClient client;
    private final Mapper mapper = MapperImpl.getInstance();

    PayperImpl(PayperConfig config) {
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
    public <T> CompletableFuture<Response<T>> send(Request request, BodyHandler<T> responseBodyHandler) {
        var bodyHandler = HttpResponse.BodyHandlers.ofInputStream();
        return client.sendAsync(mapper.mapRequest(request), bodyHandler)
                .thenApply((HttpResponse<InputStream> v) -> new ResponseImpl<>(v, responseBodyHandler));
    }

}
