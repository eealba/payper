package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;


abstract class BodyRequestSpecImpl<T extends Subscriptions.BodyRequestSpec<T, T2, T3, T4>, T2, T3, T4>
        extends RequestSpecImpl<T, T3, T4>
        implements Subscriptions.BodyRequestSpec<T, T2, T3, T4> {


    public BodyRequestSpecImpl(Payper payper) {
        super(payper);
    }

    @Override
    public T withBody(T2 body) {
        requestBuilder.POST(PayperRequest.BodyPublishers.of(body));
        return self();
    }

}