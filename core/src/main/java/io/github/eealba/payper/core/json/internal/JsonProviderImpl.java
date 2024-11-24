package io.github.eealba.payper.core.json.internal;

import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.core.json.JsonConfig;
import io.github.eealba.payper.core.json.JsonProvider;

public class JsonProviderImpl extends JsonProvider {
    public JsonProviderImpl() {
    }

    public Json createJson(JsonConfig config) {
        return new JsonImpl(config);
    }
}
