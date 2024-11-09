package io.github.eealba.payper.subscriptions.v1.model;

import java.util.List;
import io.github.eealba.jasoner.JasonerProperty;

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

    
    public List<Transaction> transactions() {
        return transactions;
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

        private List<Transaction> transactions;
        private Integer totalItems;
        private Integer totalPages;
        private List<LinkDescription> links;

        
        public Builder transactions(List<Transaction> value) {
            transactions = value;
            return this;
        }

        @JasonerProperty("total_items")
        public Builder totalItems(Integer value) {
            totalItems = value;
            return this;
        }

        @JasonerProperty("total_pages")
        public Builder totalPages(Integer value) {
            totalPages = value;
            return this;
        }

        
        public Builder links(List<LinkDescription> value) {
            links = value;
            return this;
        }

        public TransactionsList build() {
            return new TransactionsList(this);
        }

    }

}

