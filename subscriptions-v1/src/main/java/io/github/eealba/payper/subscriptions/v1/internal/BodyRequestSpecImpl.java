package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;


abstract class BodyRequestSpecImpl<T extends Subscriptions.BodyRequestSpec<T, T2, T3, T4>, T2, T3, T4>
        extends RequestSpecImpl<T, T3, T4>
        implements Subscriptions.BodyRequestSpec<T, T2, T3, T4> {


    @Override
    public T withBody(T2 body) {
        var json = JasonerBuilder.create().toJson(body);
//        requestBuilder.POST(Request.BodyPublishers.ofString(json));
        return self();
    }

}