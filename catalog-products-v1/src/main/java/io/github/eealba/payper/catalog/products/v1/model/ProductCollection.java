package io.github.eealba.payper.catalog.products.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The list of products, with details.
 */
public class ProductCollection {


    
    private final List<ProductCollectionElement> products;
    @JasonerProperty("total_items")
    private final Integer totalItems;
    @JasonerProperty("total_pages")
    private final Integer totalPages;
    
    private final List<LinkDescription> links;

    private ProductCollection(Builder builder) {
        products = builder.products;
        totalItems = builder.totalItems;
        totalPages = builder.totalPages;
        links = builder.links;

    }

    /**
     * An array of products.
     */
    
    public List<ProductCollectionElement> products() {
        return products;
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
     * An array of request-related [HATEOAS links](/docs/api/overview/#hateoas-links).
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<ProductCollectionElement> products;
        private Integer totalItems;
        private Integer totalPages;
        private List<LinkDescription> links;

        /**
         * An array of products.
         */
        
        public Builder products(List<ProductCollectionElement> value) {
            this.products = value;
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
         * An array of request-related [HATEOAS links](/docs/api/overview/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public ProductCollection build() {
            return new ProductCollection(this);
        }

    }

}

