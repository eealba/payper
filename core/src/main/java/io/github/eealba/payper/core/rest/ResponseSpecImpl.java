package io.github.eealba.payper.core.rest;

import java.util.concurrent.CompletableFuture;

public class ResponseSpecImpl<T, T2> implements ResponseSpec<T, T2> {

    @Override
    public Response<T, T2> toResponse() {
        return null;
    }

    @Override
    public CompletableFuture<Response<T, T2>> toFuture() {
        return null;
    }
}
