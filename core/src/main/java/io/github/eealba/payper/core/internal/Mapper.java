package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.web.Request;
import io.github.eealba.payper.core.web.WebClientConfig;

import java.net.URI;
import java.time.Duration;

class Mapper {

    static WebClientConfig mapWebClientConfig(PayperConfig config) {
        var builder = WebClientConfig.builder();
        builder.connectTimeout(config.connectTimeout().orElse(Duration.ofSeconds(30)));
        builder.executor(config.executor().orElse(null));
        return builder.build();
    }

    static Request mapRequest(PayperConfig config, PayperRequest request, TokenImpl token) {
        var builder = Request.newBuilder();
        builder.uri(URI.create(config.authenticator().getBaseUrl() + request.path()));
        request.headers().forEach(builder::header);
        builder.authorization(Request.Authorizations.BEARER(token.accessToken()));
        request.timeout().ifPresent(builder::timeout);

        switch (request.method()) {
            case GET -> builder.GET();
            case POST -> builder.POST(mapPublisher(request.bodyPublisher().orElse(null)));
            case PUT -> builder.PUT(mapPublisher(request.bodyPublisher().orElse(null)));
            case DELETE -> builder.DELETE();
            case PATCH -> builder.PATCH(mapPublisher(request.bodyPublisher().orElse(null)));
        }
        return builder.build();
    }

    private static Request.BodyPublisher mapPublisher(PayperRequest.BodyPublisher bodyPublisher) {
        if (bodyPublisher == null) {
            return Request.BodyPublishers.noBody();
        }
        return bodyPublisher::apply;
    }
}
