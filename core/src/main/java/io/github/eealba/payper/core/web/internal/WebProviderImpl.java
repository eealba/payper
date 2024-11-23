package io.github.eealba.payper.core.web.internal;

import io.github.eealba.payper.core.web.Request;
import io.github.eealba.payper.core.web.WebClient;
import io.github.eealba.payper.core.web.WebClientConfig;
import io.github.eealba.payper.core.web.WebProvider;

public class WebProviderImpl extends WebProvider {

    /**
     * Creates a Payper object.
     *
     * @param config the configuration
     * @return a Payper object
     */
    @Override
    public WebClient createWebClient(WebClientConfig config) {
        return new WebClientImpl(config);
    }

    @Override
    public Request.Builder createRequestBuilder() {
        return new RequestImpl.RequestBuilder();
    }
}
