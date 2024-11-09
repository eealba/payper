package io.github.eealba.payper.subscriptions.v1.model;

import java.util.List;
import io.github.eealba.jasoner.JasonerProperty;

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

    
    public List<Plan> plans() {
        return plans;
    }

    @JasonerProperty("total_items")
    public Integer totalItems() {
        return totalItems;
    }

    @JasonerProperty("total_pages")
    public Integer totalPages() {
        return totalPages;
    }

    
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

        
        public Builder plans(List<Plan> value) {
            this.plans = value;
            return this;
        }

        @JasonerProperty("total_items")
        public Builder totalItems(Integer value) {
            this.totalItems = value;
            return this;
        }

        @JasonerProperty("total_pages")
        public Builder totalPages(Integer value) {
            this.totalPages = value;
            return this;
        }

        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public PlanCollection build() {
            return new PlanCollection(this);
        }

    }

}

