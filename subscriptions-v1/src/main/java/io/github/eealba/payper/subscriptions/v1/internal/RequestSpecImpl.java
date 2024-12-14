package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.subscriptions.v1.spec.RequestSpec;
import io.github.eealba.payper.subscriptions.v1.spec.ResponseSpec;


abstract class RequestSpecImpl<T, T2, R1, R2> implements  RequestSpec<R1, R2>, RequestSpec.PreferSpec<T>,
        RequestSpec.PaypalRequestIdSpec<T>, RequestSpec.BodySpec<T, T2>, RequestSpec.IdSpec<T>{
    private final PayperRequest.Builder requestBuilder = PayperRequest.newBuilder();
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
            case POST -> requestBuilder.POST(PayperRequest.BodyPublishers.noBody());
        }
    }
    abstract PayperRequest.Method getMethod();

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
    public ResponseSpec<R1, R2> retrieve() {
        return new ResponseSpecImpl<>(payper, requestBuilder.build(), clazz1, clazz2);


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

    @Override
    public T withId(String id) {
        requestBuilder.pathParameter("id", id);
        return self();
    }

    @SuppressWarnings("unchecked")
    T self() {
        return (T) this;
    }

    void query(String fields, String fields1) {
        requestBuilder.query(fields, fields1);
    }
}
