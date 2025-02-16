package io.github.eealba.payper.webhooks.v1.api;

import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.webhooks.v1.model.Event;
import io.github.eealba.payper.webhooks.v1.model.EventList;
import io.github.eealba.payper.webhooks.v1.model.EventResend;

import java.time.Instant;
/**
 * API to manage webhook events.
 * <p>
 *     This API is used to manage webhook events.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface WebhooksEventsApi {
    /**
     * List webhook event.
     *
     * @return the API
     */
    ListWebhookEvent list();
    /**
     * Get webhook event.
     *
     * @return the API
     */
    GetWebhookEvent get();
    /**
     * Resend webhook event.
     *
     * @return the API
     */
    ResendWebhookEvent resend();

    /**
     * API to list webhook event.
     * <p>
     *     This API is used to list webhook event.
     *
     * @since 1.0
     * @version 1.0
     */
    interface ListWebhookEvent extends RequestSpec<EventList, Error> {
        /**
         * Set the page size.
         *
         * @return the API
         */
        ListWebhookEvent withPageSize(int pageSize);

        /**
         * Set the start time.
         * @return the API
         */
        ListWebhookEvent withStartTime(Instant startTime);
        /**
         * Set the end time.
         *
         * @param endTime the end time
         * @return the API
         */
        ListWebhookEvent withEndTime(Instant endTime);
        /**
         * Set the event type.
         *
         * @param eventType the event type
         * @return the API
         */
        ListWebhookEvent withEventType(String eventType);
        /**
         * Set the transaction id.
         *
         * @param transactionId the transaction id
         * @return the API
         */
        ListWebhookEvent withTransactionId(String transactionId);
    }

    /**
     * API to get a webhook event.
     * <p>
     *     This API is used to get a webhook event.
     *
     * @since 1.0
     * @version 1.0
     */
    interface GetWebhookEvent extends RequestSpec<Event, Error>,
        RequestSpec.IdSpec<GetWebhookEvent> {
    }

    /**
     * API to resend a webhook event.
     * <p>
     *     This API is used to resend a webhook event.
     *
     * @since 1.0
     * @version 1.0
     */
    interface ResendWebhookEvent extends RequestSpec<Event, Error>,
        RequestSpec.IdSpec<ResendWebhookEvent>,
        RequestSpec.BodySpec<ResendWebhookEvent, EventResend> {

    }
}
