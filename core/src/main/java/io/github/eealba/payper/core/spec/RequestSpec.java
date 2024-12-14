package io.github.eealba.payper.core.spec;

public interface RequestSpec<R1, R2> {
    ResponseSpec<R1, R2> retrieve();

    interface PreferSpec<T> {
        T withPrefer(String prefer);
    }
    interface PaypalRequestIdSpec<T> {
        T withPaypalRequestId(String paypalRequestId);
    }

    interface IdSpec<T> {
        T withId(String id);
    }

    interface BodySpec<T, T2> {
        T withBody(T2 body);
    }

}
