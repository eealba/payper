package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.PayperException;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.PayperResponse;
import io.github.eealba.payper.core.web.Response;
import io.github.eealba.payper.core.web.WebClient;

import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

class PayerResponseSpecImpl<R1, R2> implements PayperResponse.PayperResponseSpec<R1, R2> {
    private final WebClient webClient;
    private final PayperRequest request;
    private final PayperResponse.BodyHandler<R1> bodyHandler;
    private final PayperResponse.BodyHandler<R2> bodyHandler2;
    PayerResponseSpecImpl(WebClient webClient,
                                 PayperRequest request,
                                 PayperResponse.BodyHandler<R1> bodyHandler,
                                 PayperResponse.BodyHandler<R2> bodyHandler2) {
        this.webClient = webClient;
        this.request = request;
        this.bodyHandler = bodyHandler;
        this.bodyHandler2 = bodyHandler2;

    }

    @Override
    public PayperResponse<R1, R2> toResponse() {
        return new PayperResponseImpl();
    }

    @Override
    public CompletableFuture<PayperResponse<R1, R2>> toFuture() {
        return webClient.sendAsync(Mapper.mapRequest(request), Response.BodyHandlers.ofBytes())
                .thenApply(res -> new PayperResponseImpl(res.body(), res.statusCode(),
                        res.charset()));
    }


    class PayperResponseImpl implements PayperResponse<R1, R2> {
        private Charset charset;
        private byte[] data;
        private int statusCode;
        PayperResponseImpl() {
        }
        PayperResponseImpl(byte[] data, int statusCode, Charset charset) {
            this.data = data;
            this.statusCode = statusCode;
            this.charset = charset;
        }
        private void call(boolean discard){
            if(statusCode == 0) {
                if (discard){
                    statusCode = webClient.send(Mapper.mapRequest(request), Response.BodyHandlers.discarding())
                            .statusCode();
                }else {
                    var res = webClient.send(Mapper.mapRequest(request), Response.BodyHandlers.ofBytes());
                    statusCode = res.statusCode();
                    data = res.body();
                    charset = res.charset();
                }
            }
        }
        @Override
        public int statusCode() {
            call(false);
            return statusCode;
        }

        @Override
        public R1 toEntity() {
            call(false);
            if (statusCode >= 400) {
                //TODO improve error handling
                throw new PayperException(String.format("Error %d", statusCode));
            }
            return bodyHandler.apply().apply(charset, data);
        }

        @Override
        public R2 toErrorEntity() {
            call(false);
            if (statusCode < 400) {
                    return null;
            }
            return bodyHandler2.apply().apply(charset, data);
        }

        @Override
        public void toVoid() {
            call(true);
        }
    }

}
