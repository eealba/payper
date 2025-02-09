package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;

/**
 * The file reference. Can be a file in PayPal MediaServ, PayPal DMS, or other custom store.
 */
public class FileReference {


    
    private final String id;
    @JasonerProperty("reference_url")
    private final String referenceUrl;
    @JasonerProperty("content_type")
    private final String contentType;
    @JasonerProperty("create_time")
    private final Instant createTime;
    
    private final String size;

    private FileReference(Builder builder) {
        id = builder.id;
        referenceUrl = builder.referenceUrl;
        contentType = builder.contentType;
        createTime = builder.createTime;
        size = builder.size;
    }

    /**
     * The ID of the referenced file.
     */
    
    public String id() {
        return id;
    }

    /**
     * The reference URL for the file.
     */
    @JasonerProperty("reference_url")
    public String referenceUrl() {
        return referenceUrl;
    }

    /**
     * The [Internet Assigned Numbers Authority (IANA) media type of the 
file](https://www.iana.org/assignments/media-types/media-types.xhtml).
     */
    @JasonerProperty("content_type")
    public String contentType() {
        return contentType;
    }

    /**
     * createTime
     */
    @JasonerProperty("create_time")
    public Instant createTime() {
        return createTime;
    }

    /**
     * The size of the file, in bytes.
     */
    
    public String size() {
        return size;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String referenceUrl;
        private String contentType;
        private Instant createTime;
        private String size;

        /**
         * The ID of the referenced file.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The reference URL for the file.
         */
        @JasonerProperty("reference_url")
        public Builder referenceUrl(String value) {
            this.referenceUrl = value;
            return this;
        }

        /**
         * The [Internet Assigned Numbers Authority (IANA) media type of the 
file](https://www.iana.org/assignments/media-types/media-types.xhtml).
         */
        @JasonerProperty("content_type")
        public Builder contentType(String value) {
            this.contentType = value;
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
         * The size of the file, in bytes.
         */
        
        public Builder size(String value) {
            this.size = value;
            return this;
        }

        public FileReference build() {
            return new FileReference(this);
        }

    }

}

