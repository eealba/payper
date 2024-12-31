package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;

/**
 * The audit metadata. Captures all template actions on create and update.
 */
public class TemplateMetadata {


    @JasonerProperty("create_time")
    private final Instant createTime;
    @JasonerProperty("created_by")
    private final String createdBy;
    @JasonerProperty("last_update_time")
    private final Instant lastUpdateTime;
    @JasonerProperty("last_updated_by")
    private final String lastUpdatedBy;

    private TemplateMetadata(Builder builder) {
        createTime = builder.createTime;
        createdBy = builder.createdBy;
        lastUpdateTime = builder.lastUpdateTime;
        lastUpdatedBy = builder.lastUpdatedBy;

    }

    /**
     * createTime
     */
    @JasonerProperty("create_time")
    public Instant createTime() {
        return createTime;
    }

    /**
     * The email address of the account that created the resource.
     */
    @JasonerProperty("created_by")
    public String createdBy() {
        return createdBy;
    }

    /**
     * lastUpdateTime
     */
    @JasonerProperty("last_update_time")
    public Instant lastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * The email address of the account that last edited the resource.
     */
    @JasonerProperty("last_updated_by")
    public String lastUpdatedBy() {
        return lastUpdatedBy;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Instant createTime;
        private String createdBy;
        private Instant lastUpdateTime;
        private String lastUpdatedBy;

        /**
         * createTime
         */
        @JasonerProperty("create_time")
        public Builder createTime(Instant value) {
            this.createTime = value;
            return this;
        }

        /**
         * The email address of the account that created the resource.
         */
        @JasonerProperty("created_by")
        public Builder createdBy(String value) {
            this.createdBy = value;
            return this;
        }

        /**
         * lastUpdateTime
         */
        @JasonerProperty("last_update_time")
        public Builder lastUpdateTime(Instant value) {
            this.lastUpdateTime = value;
            return this;
        }

        /**
         * The email address of the account that last edited the resource.
         */
        @JasonerProperty("last_updated_by")
        public Builder lastUpdatedBy(String value) {
            this.lastUpdatedBy = value;
            return this;
        }

        public TemplateMetadata build() {
            return new TemplateMetadata(this);
        }

    }

}

