package io.github.eealba.payper.core.rest.internal;

import io.github.eealba.payper.core.rest.RequestSpec;
import io.github.eealba.payper.core.rest.ResponseSpec;
import io.github.eealba.payper.core.web.Request;

public class RequestSpecImpl<T2, T3> implements RequestSpec<RequestSpecImpl<T2, T3>, T2, T3> {
    Request.Builder requestBuilder = Request.newBuilder();
    @Override
    public RequestSpecImpl<T2, T3> withPrefer(String prefer) {
        requestBuilder.header("Prefer", prefer);
        return this;
    }

    @Override
    public RequestSpecImpl<T2, T3> withPaypalRequestId(String paypalRequestId) {
        requestBuilder.header("Paypal-Request-Id", paypalRequestId);
        return null;
    }

    @Override
    public ResponseSpec<T2, T3> retrieve() {
        return null;
    }
}
