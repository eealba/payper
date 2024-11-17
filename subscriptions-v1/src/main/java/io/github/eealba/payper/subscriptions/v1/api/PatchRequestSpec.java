package io.github.eealba.payper.subscriptions.v1.api;

public interface PatchRequestSpec<T,T2, T3, T4> extends RequestSpec<T,T3,T4> {
   T withBody(T2 body);

}
