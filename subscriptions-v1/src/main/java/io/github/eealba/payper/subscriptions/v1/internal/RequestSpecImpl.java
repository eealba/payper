package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;

abstract class RequestSpecImpl<T extends Subscriptions.RequestSpec<T,R1, R2>, R1, R2>
        implements Subscriptions.RequestSpec<T, R1, R2> {
    final PayperRequest.Builder requestBuilder = PayperRequest.newBuilder();
    private final Payper payper;
    private final Class<R1> clazz1;
    private final Class<R2> clazz2;


    RequestSpecImpl(Payper payper, String path, Class<R1> clazz1, Class<R2> clazz2) {
        this.payper = payper;
        this.clazz1 = clazz1;
        this.clazz2 = clazz2;
        requestBuilder.path(path);
        var method = getMethod();
        switch (method) {
            case GET -> requestBuilder.GET();
            case DELETE -> requestBuilder.DELETE();
        }
    }

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
    public Subscriptions.ResponseSpec<R1, R2> retrieve() {
        return new SubscriptionsResponseSpecImpl<>(payper, requestBuilder.build(), getEntityClass(),
                getErrorEntityClass());


    }
    @SuppressWarnings("unchecked")
    T self() {
        return (T) this;
    }


    @Override
    public Class<R1> getEntityClass() {
        return clazz1;
    }

    @Override
    public Class<R2> getErrorEntityClass() {
        return clazz2;
    }
}
