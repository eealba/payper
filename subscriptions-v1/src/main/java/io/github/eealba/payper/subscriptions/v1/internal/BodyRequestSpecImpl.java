package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;


abstract class BodyRequestSpecImpl<T extends Subscriptions.BodyRequestSpec<T, T2, T3, T4>, T2, T3, T4>
        extends RequestSpecImpl<T, T3, T4>
        implements Subscriptions.BodyRequestSpec<T, T2, T3, T4> {


    public BodyRequestSpecImpl(Payper payper, String path) {
        super(payper, path);
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