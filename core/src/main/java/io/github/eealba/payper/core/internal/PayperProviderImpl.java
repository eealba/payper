package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperProvider;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.PayperResponse;
import io.github.eealba.payper.core.json.Json;

import java.nio.charset.Charset;

public class PayperProviderImpl extends PayperProvider {
    private static final Json json = Json.newJson();
    /**
     * Creates a Payper object.
     *
     * @param config the configuration
     * @return a Payper object
     */
    @Override
    public Payper createPayper(PayperConfig config) {
        return new PayperImpl(config);
    }

    @Override
    public PayperRequest.Builder createPayperRequestBuilder() {
        return null;
    }

    @Override
    public PayperRequest.BodyPublisher bodyPublisherOf(Object obj) {
        return () -> json.toJson(obj).getBytes();
    }

    @Override
    public <T> PayperResponse.BodyHandler<T> bodyHandlerOfString(Class<T> clazz) {
        return () -> (Charset cs, byte[] body) ->  json.fromJson(new String(body, cs), clazz);
    }
}
