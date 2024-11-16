package io.github.eealba.payper.subscriptions.v1.api;

public interface PatchRequestCommand<T,T2> extends RequestCommand<T> {
   T withBody(T2 body);

}
