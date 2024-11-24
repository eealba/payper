package io.github.eealba.payper.core.rest;

import io.github.eealba.payper.core.web.Method;

public interface PatchRequestSpec<T extends PatchRequestSpec<T,T2,T3,T4>,T2,T3,T4> extends BodyRequestSpec<T,T2,T3,T4> {
   default Method getMethod() {
      return Method.PATCH;
   }
}
