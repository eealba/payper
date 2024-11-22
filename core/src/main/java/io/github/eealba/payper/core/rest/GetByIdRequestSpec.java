package io.github.eealba.payper.core.rest;

public interface GetByIdRequestSpec<T, T2,T3> extends RequestSpec<T, T2, T3> {
   T withId(String id);

}
