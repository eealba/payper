package io.github.eealba.payper.core.rest;

public interface BodyRequestSpec<T extends BodyRequestSpec<T,T2,T3,T4>,T2,T3,T4> extends RequestSpec<T,T3,T4> {
   T withBody(T2 body);

}
