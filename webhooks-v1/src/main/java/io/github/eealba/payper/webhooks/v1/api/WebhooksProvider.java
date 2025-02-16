package io.github.eealba.payper.webhooks.v1.api;

import io.github.eealba.payper.core.client.PayperConfig;
import io.github.eealba.payper.core.util.Providers;
/**
 * Abstract class for webhooks provider.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class WebhooksProvider {
    private static final String DEFAULT = "io.github.eealba.payper.webhooks.v1.api.internal.WebhooksProviderImpl";
    /**
     * Return a default WebhooksProvider.
     */
    public static WebhooksProvider provider(){
        return Providers.getProvider(WebhooksProvider.class, DEFAULT);
    }
    /**
     * Creates a new WebhooksApiClient instance using the specified configuration.
     *
     * @param config the configuration to be used
     * @return a new WebhooksApiClient instance
     */
    public abstract WebhooksApiClient create(PayperConfig config);
}
