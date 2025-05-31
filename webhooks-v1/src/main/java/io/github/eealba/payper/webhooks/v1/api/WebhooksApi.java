package io.github.eealba.payper.webhooks.v1.api;

import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.webhooks.v1.model.AnchorType;
import io.github.eealba.payper.webhooks.v1.model.EventTypeList;
import io.github.eealba.payper.webhooks.v1.model.PatchRequest;
import io.github.eealba.payper.webhooks.v1.model.Webhook;
import io.github.eealba.payper.webhooks.v1.model.WebhookList;

/**
 * Interface representing the Webhooks API.
 * <p>
 * This interface provides methods for creating, listing, retrieving, updating, and deleting webhooks,
 * as well as listing webhook event types.
 * </p>
 *
 * @since 1.0
 * @version 1.0
 */
public interface WebhooksApi {

    /**
     * Creates a webhook.
     * <p>
     * This method initializes a request to create a new webhook.
     * </p>
     *
     * @return the create webhook request specification
     */
    CreateWebhook create();

    /**
     * Lists all webhooks.
     * <p>
     * This method initializes a request to list all webhooks.
     * </p>
     *
     * @return the list webhooks request specification
     */
    ListWebhooks list();

    /**
     * Gets a webhook.
     * <p>
     * This method initializes a request to retrieve a specific webhook by its ID.
     * </p>
     *
     * @return the get webhook request specification
     */
    GetWebhook get();

    /**
     * Updates a webhook.
     * <p>
     * This method initializes a request to update an existing webhook.
     * </p>
     *
     * @return the update webhook request specification
     */
    UpdateWebhook update();

    /**
     * Deletes a webhook.
     * <p>
     * This method initializes a request to delete a specific webhook by its ID.
     * </p>
     *
     * @return the delete webhook request specification
     */
    DeleteWebhook delete();

    /**
     * Lists webhook event types.
     * <p>
     * This method initializes a request to list all event types for a specific webhook.
     * </p>
     *
     * @return the list webhook event types request specification
     */
    ListWebhookEventTypes listEventTypes();

    /**
     * Interface for creating a webhook.
     * <p>
     * This interface defines the specifications for creating a webhook, including setting the request body.
     * </p>
     */
    interface CreateWebhook extends RequestSpec<Webhook, Error>,
            RequestSpec.BodySpec<CreateWebhook, Webhook> {
    }

    /**
     * Interface for listing webhooks.
     * <p>
     * This interface defines the specifications for listing all webhooks.
     * </p>
     */
    interface ListWebhooks extends RequestSpec<WebhookList, Error> {
        /**
         * Sets the anchor type for the request.
         *
         * @param anchorType the anchor type
         * @return the list webhooks request specification
         */
        ListWebhooks withAnchorType(AnchorType anchorType);
    }

    /**
     * Interface for getting a webhook.
     * <p>
     * This interface defines the specifications for retrieving a specific webhook by its ID.
     * </p>
     */
    interface GetWebhook extends RequestSpec<Webhook, Error>,
            RequestSpec.IdSpec<GetWebhook> {
    }

    /**
     * Interface for updating a webhook.
     * <p>
     * This interface defines the specifications for updating an existing webhook, including setting the request body and specifying the webhook ID.
     * </p>
     */
    interface UpdateWebhook extends RequestSpec<Webhook, Error>,
            RequestSpec.IdSpec<UpdateWebhook>,
            RequestSpec.BodySpec<UpdateWebhook, PatchRequest> {
    }

    /**
     * Interface for deleting a webhook.
     * <p>
     * This interface defines the specifications for deleting a specific webhook by its ID.
     * </p>
     */
    interface DeleteWebhook extends RequestSpec<Void, Error>,
            RequestSpec.IdSpec<DeleteWebhook> {
    }

    /**
     * Interface for listing webhook event types.
     * <p>
     * This interface defines the specifications for listing all event types for a specific webhook.
     * </p>
     */
    interface ListWebhookEventTypes extends RequestSpec<EventTypeList, Error>,
            RequestSpec.IdSpec<ListWebhookEventTypes> {
    }
}