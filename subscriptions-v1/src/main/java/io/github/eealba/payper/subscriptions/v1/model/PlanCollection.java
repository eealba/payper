package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The list of plans with details.
 */
public class PlanCollection {


    
    private final List<Plan> plans;
    @JasonerProperty("total_items")
    private final Integer totalItems;
    @JasonerProperty("total_pages")
    private final Integer totalPages;
    
    private final List<LinkDescription> links;

    private PlanCollection(Builder builder) {
        plans = builder.plans;
        totalItems = builder.totalItems;
        totalPages = builder.totalPages;
        links = builder.links;

    }

    /**
     * An array of plans.
     */
    
    public List<Plan> plans() {
        return plans;
    }

    /**
     * The total number of items.
     */
    @JasonerProperty("total_items")
    public Integer totalItems() {
        return totalItems;
    }

    /**
     * The total number of pages.
     */
    @JasonerProperty("total_pages")
    public Integer totalPages() {
        return totalPages;
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

        private List<Plan> plans;
        private Integer totalItems;
        private Integer totalPages;
        private List<LinkDescription> links;

        /**
         * An array of plans.
         */
        
        public Builder plans(List<Plan> value) {
            this.plans = value;
            return this;
        }

        /**
         * The total number of items.
         */
        @JasonerProperty("total_items")
        public Builder totalItems(Integer value) {
            this.totalItems = value;
            return this;
        }

        /**
         * The total number of pages.
         */
        @JasonerProperty("total_pages")
        public Builder totalPages(Integer value) {
            this.totalPages = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public PlanCollection build() {
            return new PlanCollection(this);
        }

    }

}

