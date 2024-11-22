package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.rest.RequestSpec;
import io.github.eealba.payper.core.rest.ResponseSpec;

public abstract class AbstractRequestSpec<T,T2,T3> implements RequestSpec<T,T2,T3> {


    @Override
    public T withPrefer(String prefer) {
        return this;
    }

    @Override
    public T withPaypalRequestId(String paypalRequestId) {
        return null;
    }

    @Override
    public ResponseSpec<T2, T3> retrieve() {
        return null;
    }
}
