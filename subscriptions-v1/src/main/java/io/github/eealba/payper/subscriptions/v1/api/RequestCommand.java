package io.github.eealba.payper.subscriptions.v1.api;

public interface RequestCommand<T> {
    T withPrefer(String prefer);
    T withPaypalRequestId(String paypalRequestId);
}
