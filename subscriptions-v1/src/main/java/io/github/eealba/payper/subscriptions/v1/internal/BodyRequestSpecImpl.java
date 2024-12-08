package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.spec.RequestSpec;


abstract class BodyRequestSpecImpl<T extends RequestSpec.BodyRequestSpec<T, T2, R1, R2>, T2, R1, R2>
        extends RequestSpecImpl<T, R1, R2>
        implements RequestSpec.BodyRequestSpec<T, T2, R1, R2> {


    BodyRequestSpecImpl(Payper payper, String path, Class<R1> clazz1, Class<R2> clazz2) {
        super(payper, path, clazz1, clazz2);
    }

    @Override
    public T withBody(T2 body) {
        var method = getMethod();
        switch (method) {
            case POST -> requestBuilder.POST(PayperRequest.BodyPublishers.of(body));
            case PUT -> requestBuilder.PUT(PayperRequest.BodyPublishers.of(body));
            case PATCH -> requestBuilder.PATCH(PayperRequest.BodyPublishers.of(body));
        }
        return self();
    }

}