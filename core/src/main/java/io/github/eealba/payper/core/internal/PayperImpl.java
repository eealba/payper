package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperException;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.PayperResponse;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.core.web.Request;
import io.github.eealba.payper.core.web.Response;
import io.github.eealba.payper.core.web.WebClient;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

class PayperImpl extends Payper {
    private final WebClient webClient;
    private final PayperConfig config;
    private volatile TokenImpl token;
    private final Json json = Json.newJson();

    PayperImpl(PayperConfig config) {
        this.webClient = WebClient.newWebClient(Mapper.mapWebClientConfig(config));
        this.config = config;

    }


    @Override
    public <R1, R2> PayperResponse.PayperResponseSpec<R1, R2> send(PayperRequest request,
                                                                   PayperResponse.BodyHandler<R1> bodyHandler,
                                                                   PayperResponse.BodyHandler<R2> bodyHandler2) {
        return new PayerResponseSpecImpl<>(request, bodyHandler, bodyHandler2);
    }

    class PayerResponseSpecImpl<R1, R2> implements PayperResponse.PayperResponseSpec<R1, R2> {
        private final PayperRequest request;
        private final PayperResponse.BodyHandler<R1> bodyHandler;
        private final PayperResponse.BodyHandler<R2> bodyHandler2;

        PayerResponseSpecImpl(PayperRequest request,
                              PayperResponse.BodyHandler<R1> bodyHandler,
                              PayperResponse.BodyHandler<R2> bodyHandler2) {
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
            return getToken().thenCompose(token ->
                    webClient.sendAsync(Mapper.mapRequest(config, request, token), Response.BodyHandlers.ofBytes())
                            .thenApply(res -> new PayperResponseImpl(res.body(), res.statusCode(),
                                    res.charset())));
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

            private void call(boolean discard) {
                if (statusCode == 0) {
                    if (discard) {
                        statusCode = webClient.send(Mapper.mapRequest(config, request, getToken().join()),
                                        Response.BodyHandlers.discarding())
                                .statusCode();
                    } else {
                        var res = webClient.send(Mapper.mapRequest(config, request, getToken().join()),
                                Response.BodyHandlers.ofBytes());
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
                    throw new PayperException(String.format("Error %d", statusCode));
                }
                return bodyHandler.apply().apply(charset, data);
            }
            @Override
            public Optional<R1> toOptionalEntity() {
                call(false);
                if (statusCode >= 400) {
                    return Optional.empty();
                }
                return Optional.ofNullable(bodyHandler.apply().apply(charset, data));
            }

            @Override
            public R2 toErrorEntity() {
                call(false);
                if (statusCode < 400) {
                    return null;
                }
                return bodyHandler2.apply().apply(charset, data);
            }
            public Optional<R2> toOptionalErrorEntity() {
                call(false);
                if (statusCode < 400) {
                    return Optional.empty();
                }
                return Optional.ofNullable(bodyHandler2.apply().apply(charset, data));
            }

            @Override
            public void toVoid() {
                call(true);
            }
        }


        private CompletableFuture<TokenImpl> getToken() {
            if (token != null && token.isValid()) {
                return CompletableFuture.completedFuture(token);
            }
            return createToken().thenApply(t -> {
                token = t;
                return t;
            });
        }

        private CompletableFuture<TokenImpl> createToken() {
            var authenticator = config.authenticator();

            var request = Request.newBuilder()
                    .uri(URI.create(authenticator.getBaseUrl() + "/v1/oauth2/token"))
                    .authorization(Request.Authorizations.BASIC(authenticator.getClientId(),
                            authenticator.getClientSecret()))
                    .contentType(Request.ContentTypes.APPLICATION_FORM_URLENCODED)
                    .POST(PayperRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                    .build();

            return webClient.sendAsync(request, Response.BodyHandlers.ofString())
                    .thenCompose(s -> {
                        if (s.statusCode() >= 400) {
                            throw new PayperException("Error " + s.statusCode() + " " +  s.body());
                        }
                        return CompletableFuture.completedFuture(s.body());
                    })
                    .thenApply(s -> json.fromJson(s, TokenImpl.class));
        }
    }
}
