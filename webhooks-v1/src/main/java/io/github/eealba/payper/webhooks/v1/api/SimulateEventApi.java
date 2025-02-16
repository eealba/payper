package io.github.eealba.payper.webhooks.v1.api;

import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.webhooks.v1.model.Event;
import io.github.eealba.payper.webhooks.v1.model.SimulateEvent;

/**
 * API to simulate an event.
 * <p>
 *     This API is used to simulate an event.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface SimulateEventApi extends RequestSpec<Event, Error>,
        RequestSpec.BodySpec<SimulateEventApi, SimulateEvent> {
}
