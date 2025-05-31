package io.github.eealba.payper.webhooks.v1.internal;

import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.PayperConfig;
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.client.PayperRequest;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.webhooks.v1.api.SimulateEventApi;
import io.github.eealba.payper.webhooks.v1.api.VerifyWebhookSignatureApi;
import io.github.eealba.payper.webhooks.v1.api.WebhooksApi;
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;
import io.github.eealba.payper.webhooks.v1.api.WebhooksEventTypesApi;
import io.github.eealba.payper.webhooks.v1.api.WebhooksEventsApi;
import io.github.eealba.payper.webhooks.v1.api.WebhooksLookupApi;
import io.github.eealba.payper.webhooks.v1.model.Event;
import io.github.eealba.payper.webhooks.v1.model.EventTypeList;
import io.github.eealba.payper.webhooks.v1.model.VerifyWebhookSignatureResponse;

class WebhooksApiClientImpl extends WebhooksApiClient {
    private final WebhooksApi webhooksImpl;
    private final WebhooksLookupApi webhooksLookupImpl;
    private final WebhooksEventsApi webhooksEventsImpl;
    private final Payper payper;

    WebhooksApiClientImpl(PayperConfig config) {
        this.payper = Payper.create(config);
        webhooksImpl = new WebhooksImpl(payper);
        webhooksLookupImpl = new WebhooksLookupImpl(payper);
        webhooksEventsImpl = new WebhooksEventsImpl(payper);
    }

    /**
     * Returns the Webhooks API interface.
     *
     * @return the Webhooks API interface
     */
    @Override
    public WebhooksApi webhooks() {
        return webhooksImpl;
    }

    /**
     * Returns the Webhooks Lookup API interface.
     *
     * @return the Webhooks Lookup API interface
     */
    @Override
    public WebhooksLookupApi webhooksLookup() {
        return webhooksLookupImpl;
    }

    /**
     * Returns the Verify Webhook Signature API interface.
     *
     * @return the Verify Webhook Signature API interface
     */
    @Override
    public VerifyWebhookSignatureApi verifyWebhookSignature() {
        var spec = PayperProvider.provider().createSpecBuilder(VerifyWebhookSignatureApi.class, payper,
                                                               "/v1/notifications/verify-webhook-signature",
                                                               VerifyWebhookSignatureResponse.class, Error.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Returns the Webhooks Event Type API interface.
     *
     * @return the Webhooks Event Type API interface
     */
    @Override
    public WebhooksEventTypesApi webhooksEventTypes() {
        var spec = PayperProvider.provider().createSpecBuilder(WebhooksEventTypesApi.class, payper,
                        "/v1/notifications/webhooks-event-types", EventTypeList.class, Error.class)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Returns the Webhooks Events API interface.
     *
     * @return the Webhooks Events API interface
     */
    @Override
    public WebhooksEventsApi webhooksEvents() {
        return webhooksEventsImpl;
    }

    /**
     * Returns the Simulate Event API interface.
     *
     * @return the Simulate Event API interface
     */
    @Override
    public SimulateEventApi simulateEvent() {
        var spec = PayperProvider.provider().createSpecBuilder(SimulateEventApi.class, payper,
                        "/v1/notifications/simulate-event", Event.class, Error.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }
}
