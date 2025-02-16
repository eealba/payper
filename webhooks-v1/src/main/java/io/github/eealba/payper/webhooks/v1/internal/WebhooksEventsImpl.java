package io.github.eealba.payper.webhooks.v1.internal;

import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.client.PayperRequest;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.webhooks.v1.api.WebhooksEventsApi;
import io.github.eealba.payper.webhooks.v1.model.Event;
import io.github.eealba.payper.webhooks.v1.model.EventList;

class WebhooksEventsImpl implements WebhooksEventsApi {
    private final Payper payper;

    WebhooksEventsImpl(Payper payper) {
        this.payper = payper;
    }

    /**
     * List webhook event.
     *
     * @return the API
     */
    @Override
    public ListWebhookEvent list() {
        var spec = PayperProvider.provider().createSpecBuilder(ListWebhookEvent.class, payper,
                        "/v1/notifications/webhooks-events", EventList.class, Error.class)
                .method(PayperRequest.Method.GET)
                .alias("withPageSize", "query,page_size")
                .alias("withStartTime", "query,start_time")
                .alias("withEndTime", "query,end_time")
                .alias("withTransactionId", "query,transaction_id")
                .alias("withEventType", "query,event_type")
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Get webhook event.
     *
     * @return the API
     */
    @Override
    public GetWebhookEvent get() {
        var spec = PayperProvider.provider().createSpecBuilder(GetWebhookEvent.class, payper,
                        "/v1/notifications/webhooks-events/{id}", Event.class, Error.class)
                .method(PayperRequest.Method.GET)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Resend webhook event.
     *
     * @return the API
     */
    @Override
    public ResendWebhookEvent resend() {
        var spec = PayperProvider.provider().createSpecBuilder(ResendWebhookEvent.class, payper,
                        "/v1/notifications/webhooks-events/{id}/resend", Event.class, Error.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }
}
