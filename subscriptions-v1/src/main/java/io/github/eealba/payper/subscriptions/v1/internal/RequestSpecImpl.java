package io.github.eealba.payper.subscriptions.v1.internal;


import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;

abstract class RequestSpecImpl<T extends Subscriptions.RequestSpec<T,T2,T3>,T2, T3>
        implements Subscriptions.RequestSpec<T, T2, T3> {
    final PayperRequest.Builder requestBuilder = PayperRequest.newBuilder();
    private final Payper payper;


    public RequestSpecImpl(Payper payper, String path) {
        this.payper = payper;
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
    public Subscriptions.ResponseSpec<T2, T3> retrieve() {
        // TODO: Implement this method
        //requestBuilder.uri(getUri());
        return new SubscriptionsResponseSpecImpl<>(payper, requestBuilder.build(), getEntityClass(),
                getErrorEntityClass());


    }
    @SuppressWarnings("unchecked")
    T self() {
        return (T) this;
    }
}
