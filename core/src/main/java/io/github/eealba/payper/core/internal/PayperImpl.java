package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.PayperResponse;

class PayperImpl implements Payper {
    PayperImpl(PayperConfig config) {

    }

    /**
     * Send response spec.
     *
     * @param request      the request
     * @param bodyHandler  the body handler
     * @param bodyHandler2
     * @return the response spec
     */
    @Override
    public <T, T2> PayperResponse.PayperResponseSpec<T, T2> send(PayperRequest request,
                                                                 PayperResponse.BodyHandler<T> bodyHandler,
                                                                 PayperResponse.BodyHandler<T2> bodyHandler2) {
        return null;
    }
}
