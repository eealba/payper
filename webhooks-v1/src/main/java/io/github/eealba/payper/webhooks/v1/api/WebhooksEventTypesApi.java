package io.github.eealba.payper.webhooks.v1.api;

import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.webhooks.v1.model.EventTypeList;
/**
 * API to get the list of event types.
 * <p>
 *     This API is used to get the list of event types.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface WebhooksEventTypesApi extends RequestSpec<EventTypeList, Error> {
}
