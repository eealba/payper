package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The template subtotal setting. Includes the field name and display preference.
 */
public class TemplateSubtotalSetting {


    @JasonerProperty("field_name")
    private final TemplateSubtotalField fieldName;
    @JasonerProperty("display_preference")
    private final TemplateDisplayPreference displayPreference;

    private TemplateSubtotalSetting(Builder builder) {
        fieldName = builder.fieldName;
        displayPreference = builder.displayPreference;
    }

    /**
     * fieldName
     */
    @JasonerProperty("field_name")
    public TemplateSubtotalField fieldName() {
        return fieldName;
    }

    /**
     * displayPreference
     */
    @JasonerProperty("display_preference")
    public TemplateDisplayPreference displayPreference() {
        return displayPreference;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private TemplateSubtotalField fieldName;
        private TemplateDisplayPreference displayPreference;

        /**
         * fieldName
         */
        @JasonerProperty("field_name")
        public Builder fieldName(TemplateSubtotalField value) {
            this.fieldName = value;
            return this;
        }

        /**
         * displayPreference
         */
        @JasonerProperty("display_preference")
        public Builder displayPreference(TemplateDisplayPreference value) {
            this.displayPreference = value;
            return this;
        }

        public TemplateSubtotalSetting build() {
            return new TemplateSubtotalSetting(this);
        }

    }

}

