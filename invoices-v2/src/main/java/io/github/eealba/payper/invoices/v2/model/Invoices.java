package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * An array of merchant invoices. Includes the total invoices count and [HATEOAS 
links](/docs/api/reference/api-responses/#hateoas-links) for navigation.
 */
public class Invoices {


    @JasonerProperty("total_pages")
    private final Integer totalPages;
    @JasonerProperty("total_items")
    private final Integer totalItems;
    
    private final List<Invoice> items;
    
    private final List<LinkDescription> links;

    private Invoices(Builder builder) {
        totalPages = builder.totalPages;
        totalItems = builder.totalItems;
        items = builder.items;
        links = builder.links;

    }

    /**
     * The total number of pages that are available for the search criteria. <blockquote><strong>Note:</strong> 
Clients MUST NOT assume that the value of total_pages is constant. The value MAY change from one request to 
the next</blockquote>
     */
    @JasonerProperty("total_pages")
    public Integer totalPages() {
        return totalPages;
    }

    /**
     * The total number of invoices that match the search criteria.<blockquote><strong>Note:</strong> Clients MUST 
NOT assume that the value of <code>total_items</code> is constant. The value MAY change from one request to 
the next.</blockquote>
     */
    @JasonerProperty("total_items")
    public Integer totalItems() {
        return totalItems;
    }

    /**
     * The list of invoices that match the search criteria.
     */
    
    public List<Invoice> items() {
        return items;
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

        private Integer totalPages;
        private Integer totalItems;
        private List<Invoice> items;
        private List<LinkDescription> links;

        /**
         * The total number of pages that are available for the search criteria. <blockquote><strong>Note:</strong> 
Clients MUST NOT assume that the value of total_pages is constant. The value MAY change from one request to 
the next</blockquote>
         */
        @JasonerProperty("total_pages")
        public Builder totalPages(Integer value) {
            this.totalPages = value;
            return this;
        }

        /**
         * The total number of invoices that match the search criteria.<blockquote><strong>Note:</strong> Clients MUST 
NOT assume that the value of <code>total_items</code> is constant. The value MAY change from one request to 
the next.</blockquote>
         */
        @JasonerProperty("total_items")
        public Builder totalItems(Integer value) {
            this.totalItems = value;
            return this;
        }

        /**
         * The list of invoices that match the search criteria.
         */
        
        public Builder items(List<Invoice> value) {
            this.items = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public Invoices build() {
            return new Invoices(this);
        }

    }

}

