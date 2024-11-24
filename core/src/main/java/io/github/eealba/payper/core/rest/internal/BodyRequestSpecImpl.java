package io.github.eealba.payper.core.rest.internal;

import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.payper.core.rest.BodyRequestSpec;
import io.github.eealba.payper.core.web.Request;


public abstract class BodyRequestSpecImpl<T extends BodyRequestSpec<T, T2, T3, T4>, T2, T3, T4>
        extends RequestSpecImpl<T, T3, T4>
        implements BodyRequestSpec<T, T2, T3, T4> {
    Request.Builder requestBuilder = Request.newBuilder();


    @Override
    public T withBody(T2 body) {
        var json = JasonerBuilder.create().toJson(body);
        requestBuilder.POST(Request.BodyPublishers.ofString(json));
        return self();
    }

}