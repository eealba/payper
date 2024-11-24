package io.github.eealba.payper.core.json;

import io.github.eealba.payper.core.util.Providers;

public abstract class JsonProvider {

    private static final String DEFAULT_PROVIDER = "io.github.eealba.payper.core.json.internal.JsonProviderImpl";

    public JsonProvider() {
    }

    public static JsonProvider provider() {
        return Providers.getProvider(JsonProvider.class, DEFAULT_PROVIDER);
    }


    public abstract Json createJson(JsonConfig config);

}
