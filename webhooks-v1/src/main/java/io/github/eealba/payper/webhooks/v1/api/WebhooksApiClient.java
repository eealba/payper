package io.github.eealba.payper.webhooks.v1.api;

import io.github.eealba.payper.core.client.PayperConfig;

/**
 * Abstract class for webhooks api client.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class WebhooksApiClient {

    /**
     * Creates a new WebhooksApiClient instance using the default configuration.
     * <p>
     * This method initializes the client with the default settings and credentials
     * required for communicating with the Webhooks API.
     *
     * @return a new WebhooksApiClient instance
     */
    public static WebhooksApiClient create() {
        return create(PayperConfig.builder().build());
    }

    /**
     * Creates a new WebhooksApiClient instance using the specified configuration.
     * <p>
     * This method allows for custom configuration of the client, including setting
     * specific credentials and other settings required for authenticating and
     * communicating with the Webhooks API.
     *
     * @param config the configuration to be used
     * @return a new WebhooksApiClient instance
     */
    public static WebhooksApiClient create(PayperConfig config) {
        return WebhooksProvider.provider().create(config);
    }
    /**
     * Returns the Webhooks API interface.
     *
     * @return the Webhooks API interface
     */
    public abstract WebhooksApi webhooks();

    /**
     * Returns the Webhooks Lookup API interface.
     *
     * @return the Webhooks Lookup API interface
     */
    public abstract WebhooksLookupApi webhooksLookup();

    /**
     * Returns the Verify Webhook Signature API interface.
     *
     * @return the Verify Webhook Signature API interface
     */
    public abstract VerifyWebhookSignatureApi verifyWebhookSignature();

    /**
     * Returns the Webhooks Event Type API interface.
     *
     * @return the Webhooks Event Type API interface
     */
    public abstract WebhooksEventTypesApi webhooksEventTypes();
    /**
     * Returns the Webhooks Events API interface.
     *
     * @return the Webhooks Events API interface
     */
    public abstract WebhooksEventsApi webhooksEvents();
    /**
     * Returns the Simulate Event API interface.
     *
     * @return the Simulate Event API interface
     */
    public abstract SimulateEventApi simulateEvent();

}
