package io.github.eealba.payper.webhooks.v1.internal;

import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.client.PayperRequest;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.webhooks.v1.api.WebhooksLookupApi;
import io.github.eealba.payper.webhooks.v1.model.WebhookLookupList;
import io.github.eealba.payper.webhooks.v1.model.WebhooksLookup;

/**
 * Implementation of the WebhooksLookupApi.
 * <p>
 *     This class is used to implement the WebhooksLookupApi.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
class WebhooksLookupImpl implements WebhooksLookupApi {
    private final Payper payper;

    WebhooksLookupImpl(Payper payper) {
        this.payper = payper;
    }

    /**
     * Create webhook lookup.
     *
     * @return the API
     */
    @Override
    public CreateWebhookLookup create() {
        var spec = PayperProvider.provider().createSpecBuilder(CreateWebhookLookup.class, payper,
                        "/v1/notifications/webhooks-lookup", WebhooksLookup.class, Error.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * List webhook lookup.
     *
     * @return the API
     */
    @Override
    public ListWebhookLookup list() {
        var spec = PayperProvider.provider().createSpecBuilder(ListWebhookLookup.class, payper,
                        "/v1/notifications/webhooks-lookup", WebhookLookupList.class, Error.class)
                .method(PayperRequest.Method.GET)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Get webhook lookup.
     *
     * @return the API
     */
    @Override
    public GetWebhookLookup get() {
        var spec = PayperProvider.provider().createSpecBuilder(GetWebhookLookup.class, payper,
                        "/v1/notifications/webhooks-lookup/{id}", WebhooksLookup.class, Error.class)
                .method(PayperRequest.Method.GET)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Delete webhook lookup.
     *
     * @return the API
     */
    @Override
    public DeleteWebhookLookup delete() {
        var spec = PayperProvider.provider().createSpecBuilder(DeleteWebhookLookup.class, payper,
                        "/v1/notifications/webhooks-lookup/{id}", WebhooksLookup.class, Error.class)
                .method(PayperRequest.Method.DELETE)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }
}
