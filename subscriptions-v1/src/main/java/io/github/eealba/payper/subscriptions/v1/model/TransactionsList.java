package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The list transactions for a subscription request details.
 */
public class TransactionsList {


    
    private final List<Transaction> transactions;
    @JasonerProperty("total_items")
    private final Integer totalItems;
    @JasonerProperty("total_pages")
    private final Integer totalPages;
    
    private final List<LinkDescription> links;

    private TransactionsList(Builder builder) {
        transactions = builder.transactions;
        totalItems = builder.totalItems;
        totalPages = builder.totalPages;
        links = builder.links;

    }

    /**
     * An array of transactions.
     */
    
    public List<Transaction> transactions() {
        return transactions;
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

        private List<Transaction> transactions;
        private Integer totalItems;
        private Integer totalPages;
        private List<LinkDescription> links;

        /**
         * An array of transactions.
         */
        
        public Builder transactions(List<Transaction> value) {
            this.transactions = value;
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

        public TransactionsList build() {
            return new TransactionsList(this);
        }

    }

}

