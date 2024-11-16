package io.github.eealba.payper.subscriptions.v1.api;

public interface GetByIdRequestCommand<T> extends RequestCommand<T> {
   T withId(String id);

}
