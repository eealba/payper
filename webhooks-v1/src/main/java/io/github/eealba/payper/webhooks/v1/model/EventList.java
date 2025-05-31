package io.github.eealba.payper.webhooks.v1.model;

import java.util.List;

/**
 * A list of webhooks events.
 */
public class EventList {


    
    private final List<Event> events;
    
    private final Integer count;
    
    private final List<LinkDescription> links;

    private EventList(Builder builder) {
        events = builder.events;
        count = builder.count;
        links = builder.links;
    }

    /**
     * An array of webhooks events.
     */
    
    public List<Event> events() {
        return events;
    }

    /**
     * The number of items in each range of results. Note that the response might have fewer items than the requested 
`page_size` value.
     */
    
    public Integer count() {
        return count;
    }

    /**
     * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<Event> events;
        private Integer count;
        private List<LinkDescription> links;

        /**
         * An array of webhooks events.
         */
        
        public Builder events(List<Event> value) {
            this.events = value;
            return this;
        }

        /**
         * The number of items in each range of results. Note that the response might have fewer items than the requested 
`page_size` value.
         */
        
        public Builder count(Integer value) {
            this.count = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public EventList build() {
            return new EventList(this);
        }

    }

}

