package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The template item setting. Sets a template as the default template or edit template.
 */
public class TemplateItemSetting {


    @JasonerProperty("field_name")
    private final TemplateItemField fieldName;
    @JasonerProperty("display_preference")
    private final TemplateDisplayPreference displayPreference;

    private TemplateItemSetting(Builder builder) {
        fieldName = builder.fieldName;
        displayPreference = builder.displayPreference;
    }

    /**
     * fieldName
     */
    @JasonerProperty("field_name")
    public TemplateItemField fieldName() {
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

        private TemplateItemField fieldName;
        private TemplateDisplayPreference displayPreference;

        /**
         * fieldName
         */
        @JasonerProperty("field_name")
        public Builder fieldName(TemplateItemField value) {
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

        public TemplateItemSetting build() {
            return new TemplateItemSetting(this);
        }

    }

}

