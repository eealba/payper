package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperProvider;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.web.Response;

public class PayperProviderImpl extends PayperProvider {
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
        return null;
    }

    @Override
    public <T> Response.BodyHandler<T> bodyHandlerOfString(Class<T> clazz) {
        return null;
    }
}
