package io.github.eealba.payper.webhooks.v1.internal;

import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.client.PayperRequest;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.webhooks.v1.api.WebhooksApi;
import io.github.eealba.payper.webhooks.v1.model.EventTypeList;
import io.github.eealba.payper.webhooks.v1.model.Webhook;
import io.github.eealba.payper.webhooks.v1.model.WebhookList;
/**
 * Implementation of the Webhooks API.
 * <p>
 *     This class provides the implementation of the Webhooks API.
 * </p>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
class WebhooksImpl implements WebhooksApi {
    private final Payper payper;

    WebhooksImpl(Payper payper) {
        this.payper = payper;
    }

    /**
     * Creates a webhook.
     * <p>
     * This method initializes a request to create a new webhook.
     * </p>
     *
     * @return the create webhook request specification
     */
    @Override
    public CreateWebhook create() {
        var spec = PayperProvider.provider().createSpecBuilder(CreateWebhook.class, payper,
                        "/v1/notifications/webhooks", Webhook.class, Error.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Lists all webhooks.
     * <p>
     * This method initializes a request to list all webhooks.
     * </p>
     *
     * @return the list webhooks request specification
     */
    @Override
    public ListWebhooks list() {
        var spec = PayperProvider.provider().createSpecBuilder(ListWebhooks.class, payper,
                        "/v1/notifications/webhooks", WebhookList.class, Error.class)
                .alias("withAnchorType", "query,anchor_type")
                .method(PayperRequest.Method.GET)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Gets a webhook.
     * <p>
     * This method initializes a request to retrieve a specific webhook by its ID.
     * </p>
     *
     * @return the get webhook request specification
     */
    @Override
    public GetWebhook get() {
        var spec = PayperProvider.provider().createSpecBuilder(GetWebhook.class, payper,
                        "/v1/notifications/webhooks/{id}", Webhook.class, Error.class)
                .method(PayperRequest.Method.GET)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Updates a webhook.
     * <p>
     * This method initializes a request to update an existing webhook.
     * </p>
     *
     * @return the update webhook request specification
     */
    @Override
    public UpdateWebhook update() {
        var spec = PayperProvider.provider().createSpecBuilder(UpdateWebhook.class, payper,
                        "/v1/notifications/webhooks/{id}", Webhook.class, Error.class)
                .method(PayperRequest.Method.PATCH)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Deletes a webhook.
     * <p>
     * This method initializes a request to delete a specific webhook by its ID.
     * </p>
     *
     * @return the delete webhook request specification
     */
    @Override
    public DeleteWebhook delete() {
        var spec = PayperProvider.provider().createSpecBuilder(DeleteWebhook.class, payper,
                        "/v1/notifications/webhooks/{id}", Void.class, Error.class)
                .method(PayperRequest.Method.DELETE)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Lists webhook event types.
     * <p>
     * This method initializes a request to list all event types for a specific webhook.
     * </p>
     *
     * @return the list webhook event types request specification
     */
    @Override
    public ListWebhookEventTypes listEventTypes() {
        var spec = PayperProvider.provider().createSpecBuilder(ListWebhookEventTypes.class, payper,
                        "/v1/notifications/webhooks/{id}/event-types", EventTypeList.class, Error.class)
                .method(PayperRequest.Method.GET)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }
}
