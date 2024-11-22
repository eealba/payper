package io.github.eealba.payper.core.rest.internal;

import io.github.eealba.payper.core.rest.RequestSpec;
import io.github.eealba.payper.core.rest.ResponseSpec;

public class RequestSpecImpl<T, T2, T3> implements RequestSpec<T, T2, T3> {
    @Override
    public T withPrefer(String prefer) {
        return null;
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
