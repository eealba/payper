package io.github.eealba.payper.core.rest;

import io.github.eealba.payper.core.web.Method;

public interface RequestSpec<T,T2,T3> {
    T withPrefer(String prefer);
    T withPaypalRequestId(String paypalRequestId);
    ResponseSpec<T2, T3> retrieve();
    Method getMethod();

}
