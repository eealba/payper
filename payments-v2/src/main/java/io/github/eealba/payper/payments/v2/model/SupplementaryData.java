package io.github.eealba.payper.payments.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The supplementary data.
 */
public class SupplementaryData {


    @JasonerProperty("related_ids")
    private final RelatedIds relatedIds;

    private SupplementaryData(Builder builder) {
        relatedIds = builder.relatedIds;

    }

    /**
     * relatedIds
     */
    @JasonerProperty("related_ids")
    public RelatedIds relatedIds() {
        return relatedIds;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private RelatedIds relatedIds;

        /**
         * relatedIds
         */
        @JasonerProperty("related_ids")
        public Builder relatedIds(RelatedIds value) {
            this.relatedIds = value;
            return this;
        }

        public SupplementaryData build() {
            return new SupplementaryData(this);
        }

    }

}

