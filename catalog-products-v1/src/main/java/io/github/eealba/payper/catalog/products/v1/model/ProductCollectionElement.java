package io.github.eealba.payper.catalog.products.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.List;

/**
 * The details for a product in the collection response.
 */
public class ProductCollectionElement {


    
    private final String id;
    
    private final String name;
    
    private final String description;
    @JasonerProperty("create_time")
    private final Instant createTime;
    
    private final List<LinkDescription> links;

    private ProductCollectionElement(Builder builder) {
        id = builder.id;
        name = builder.name;
        description = builder.description;
        createTime = builder.createTime;
        links = builder.links;

    }

    /**
     * The ID of the product.
     */
    
    public String id() {
        return id;
    }

    /**
     * The product name.
     */
    
    public String name() {
        return name;
    }

    /**
     * The product description.
     */
    
    public String description() {
        return description;
    }

    /**
     * createTime
     */
    @JasonerProperty("create_time")
    public Instant createTime() {
        return createTime;
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

        private String id;
        private String name;
        private String description;
        private Instant createTime;
        private List<LinkDescription> links;

        /**
         * The ID of the product.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The product name.
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * The product description.
         */
        
        public Builder description(String value) {
            this.description = value;
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
         * An array of request-related [HATEOAS links](/docs/api/overview/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public ProductCollectionElement build() {
            return new ProductCollectionElement(this);
        }

    }

}

