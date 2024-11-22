package io.github.eealba.payper.core.rest;

public interface RequestSpec<T,T2,T3> {
    T withPrefer(String prefer);
    T withPaypalRequestId(String paypalRequestId);
    ResponseSpec<T2, T3> retrieve();
}
