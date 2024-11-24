package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.PayperResponse;
import io.github.eealba.payper.core.web.WebClient;

class PayperImpl implements Payper {
    private final WebClient webClient;
    private final PayperConfig config;

    PayperImpl(PayperConfig config) {
        this.webClient = WebClient.newWebClient(Mapper.mapWebClientConfig(config));
        this.config = config;

    }


    @Override
    public <R1, R2> PayperResponse.PayperResponseSpec<R1, R2> send(PayperRequest request,
                                                                 PayperResponse.BodyHandler<R1> bodyHandler,
                                                                 PayperResponse.BodyHandler<R2> bodyHandler2) {
        return new PayerResponseSpecImpl<>(webClient, request, bodyHandler, bodyHandler2);
    }

}
