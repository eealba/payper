package io.github.eealba.payper.core.rest.internal;

import io.github.eealba.payper.core.rest.RequestSpec;
import io.github.eealba.payper.core.rest.ResponseSpec;
import io.github.eealba.payper.core.web.Request;

public abstract class RequestSpecImpl<T extends RequestSpec<T,T2,T3>,T2, T3> implements RequestSpec<T, T2, T3> {
    Request.Builder requestBuilder = Request.newBuilder();
    @Override
    public T withPrefer(String prefer) {
        requestBuilder.header("Prefer", prefer);
        return self();
    }

    @Override
    public T withPaypalRequestId(String paypalRequestId) {
        requestBuilder.header("Paypal-Request-Id", paypalRequestId);
        return self();
    }

    @Override
    public ResponseSpec<T2, T3> retrieve() {
        return null;
    }
    @SuppressWarnings("unchecked")
    T self() {
        return (T) this;
    }
}
