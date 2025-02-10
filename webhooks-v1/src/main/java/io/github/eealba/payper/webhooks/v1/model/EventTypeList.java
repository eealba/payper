package io.github.eealba.payper.webhooks.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * A list of webhook events.
 */
public class EventTypeList {


    @JasonerProperty("event_types")
    private final List<EventType> eventTypes;

    private EventTypeList(Builder builder) {
        eventTypes = builder.eventTypes;
    }

    /**
     * An array of webhook events.
     */
    @JasonerProperty("event_types")
    public List<EventType> eventTypes() {
        return eventTypes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<EventType> eventTypes;

        /**
         * An array of webhook events.
         */
        @JasonerProperty("event_types")
        public Builder eventTypes(List<EventType> value) {
            this.eventTypes = value;
            return this;
        }

        public EventTypeList build() {
            return new EventTypeList(this);
        }

    }

}

