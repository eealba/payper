package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.List;


/**
 * The tracking response on creation of tracker.
 */
public class Tracker {


    
    private final String id;
    
    private final TrackerStatus status;
    
    private final List<TrackerItem> items;
    
    private final List<LinkDescription> links;
    @JasonerProperty("create_time")
    private final Instant createTime;
    @JasonerProperty("update_time")
    private final Instant updateTime;

    private Tracker(Builder builder) {
        id = builder.id;
        status = builder.status;
        items = builder.items;
        links = builder.links;
        createTime = builder.createTime;
        updateTime = builder.updateTime;

    }

    /**
     * The tracker id.
     */
    
    public String id() {
        return id;
    }

    /**
     * status
     */
    
    public TrackerStatus status() {
        return status;
    }

    /**
     * An array of details of items in the shipment.
     */
    
    public List<TrackerItem> items() {
        return items;
    }

    /**
     * An array of request-related HATEOAS links.
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    /**
     * createTime
     */
    @JasonerProperty("create_time")
    public Instant createTime() {
        return createTime;
    }

    /**
     * updateTime
     */
    @JasonerProperty("update_time")
    public Instant updateTime() {
        return updateTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private TrackerStatus status;
        private List<TrackerItem> items;
        private List<LinkDescription> links;
        private Instant createTime;
        private Instant updateTime;

        /**
         * The tracker id.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * status
         */
        
        public Builder status(TrackerStatus value) {
            this.status = value;
            return this;
        }

        /**
         * An array of details of items in the shipment.
         */
        
        public Builder items(List<TrackerItem> value) {
            this.items = value;
            return this;
        }

        /**
         * An array of request-related HATEOAS links.
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        /**
         * createTime
         */
        @JasonerProperty("create_time")
        public Builder createTime(Instant value) {
            this.createTime = value;
            return this;
        }

        /**
         * updateTime
         */
        @JasonerProperty("update_time")
        public Builder updateTime(Instant value) {
            this.updateTime = value;
            return this;
        }

        public Tracker build() {
            return new Tracker(this);
        }

    }


}

