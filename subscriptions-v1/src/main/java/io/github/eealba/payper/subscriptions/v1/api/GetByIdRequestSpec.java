package io.github.eealba.payper.subscriptions.v1.api;

public interface GetByIdRequestSpec<T, T2,T3> extends RequestSpec<T, T2, T3> {
   T withId(String id);

}
