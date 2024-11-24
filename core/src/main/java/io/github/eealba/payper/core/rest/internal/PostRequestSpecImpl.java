package io.github.eealba.payper.core.rest.internal;

import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.payper.core.rest.PostRequestSpec;
import io.github.eealba.payper.core.rest.ResponseSpec;
import io.github.eealba.payper.core.web.Request;


public class PostRequestSpecImpl<T extends PostRequestSpec<T, T2, T3, T4>, T2, T3, T4> implements PostRequestSpec<T, T2, T3, T4> {
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
    public ResponseSpec<T3, T4> retrieve() {
        return null;
    }

    @Override
    public T withBody(T2 body) {
        var json = JasonerBuilder.create().toJson(body);
        requestBuilder.POST(Request.BodyPublishers.ofString(json));
        return self();
    }

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }
}