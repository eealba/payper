package io.github.eealba.payper.webhooks.v1.internal;

import io.github.eealba.payper.core.client.PayperConfig;
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;
import io.github.eealba.payper.webhooks.v1.api.WebhooksProvider;

public class WebhooksProviderImpl extends WebhooksProvider {
    /**
     * Creates a new WebhooksApiClient instance using the specified configuration.
     *
     * @param config the configuration to be used
     * @return a new WebhooksApiClient instance
     */
    @Override
    public WebhooksApiClient create(PayperConfig config) {
        return new WebhooksApiClientImpl(config);
    }
}
