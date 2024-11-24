package io.github.eealba.payper.core.json.internal;

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.core.json.JsonConfig;

class JsonImpl implements Json {
    Jasoner jasoner;
    public JsonImpl(JsonConfig config) {
        jasoner = JasonerBuilder.create();
    }

    @Override
    public String toJson(Object object) {
        return jasoner.toJson(object);
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        return jasoner.fromJson(json, clazz);
    }
}
