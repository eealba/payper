package io.github.eealba.payper.core.rest;

public interface PostRequestSpec<T extends PostRequestSpec<T,T2,T3,T4>,T2,T3,T4> {
   T withPrefer(String prefer);
   T withPaypalRequestId(String paypalRequestId);
   ResponseSpec<T3, T4> retrieve();

   T withBody(T2 body);

}
