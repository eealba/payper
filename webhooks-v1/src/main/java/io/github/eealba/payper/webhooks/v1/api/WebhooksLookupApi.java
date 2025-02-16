package io.github.eealba.payper.webhooks.v1.api;

import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.webhooks.v1.model.WebhookLookupList;
import io.github.eealba.payper.webhooks.v1.model.WebhooksLookup;

/**
 * API to manage webhooks lookup.
 * <p>
 *     This API is used to manage webhooks lookup.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface WebhooksLookupApi {
    /**
     * Create webhook lookup.
     *
     * @return the API
     */
    CreateWebhookLookup create();
    /**
     * List webhook lookup.
     *
     * @return the API
     */
    ListWebhookLookup list();
    /**
     * Get webhook lookup.
     *
     * @return the API
     */
    GetWebhookLookup get();
    /**
     * Delete webhook lookup.
     *
     * @return the API
     */
    DeleteWebhookLookup delete();
    /**
     * API to create a webhook lookup.
     * <p>
     *     This API is used to create a webhook lookup.
     *
     * @since 1.0
     * @version 1.0
     */
    interface CreateWebhookLookup extends RequestSpec<WebhooksLookup, Error>,
            RequestSpec.BodySpec<CreateWebhookLookup, WebhooksLookup> {
    }
    /**
     * API to list webhook lookup.
     * <p>
     *     This API is used to list webhook lookup.
     *
     * @since 1.0
     * @version 1.0
     */
    interface ListWebhookLookup extends RequestSpec<WebhookLookupList, Error> {
    }
    /**
     * API to get a webhook lookup.
     * <p>
     *     This API is used to get a webhook lookup.
     *
     * @since 1.0
     * @version 1.0
     */
    interface GetWebhookLookup extends RequestSpec<WebhooksLookup, Error>,
        RequestSpec.IdSpec<GetWebhookLookup> {
    }
    /**
     * API to delete a webhook lookup.
     * <p>
     *     This API is used to delete a webhook lookup.
     *
     * @since 1.0
     * @version 1.0
     */
    interface DeleteWebhookLookup extends RequestSpec<Void, Error>,
        RequestSpec.IdSpec<DeleteWebhookLookup> {
    }
}
