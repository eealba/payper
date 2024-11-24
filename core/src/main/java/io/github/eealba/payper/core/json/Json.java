package io.github.eealba.payper.core.json;


public interface Json {
    static Json newJson() {
        return JsonProvider.provider().createJson(JsonConfig.DEFAULT);
    }
    static Json newJson(JsonConfig config) {
        return JsonProvider.provider().createJson(config);
    }

        String toJson(Object object);

        <T> T fromJson(String json, Class<T> clazz);
}
