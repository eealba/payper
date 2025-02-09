package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The template settings. Sets a template as the default template or edit template.
 */
public class TemplateSettings {


    @JasonerProperty("template_item_settings")
    private final List<TemplateItemSetting> templateItemSettings;
    @JasonerProperty("template_subtotal_settings")
    private final List<TemplateSubtotalSetting> templateSubtotalSettings;

    private TemplateSettings(Builder builder) {
        templateItemSettings = builder.templateItemSettings;
        templateSubtotalSettings = builder.templateSubtotalSettings;
    }

    /**
     * The template item headers display preference.
     */
    @JasonerProperty("template_item_settings")
    public List<TemplateItemSetting> templateItemSettings() {
        return templateItemSettings;
    }

    /**
     * The template subtotal headers display preference.
     */
    @JasonerProperty("template_subtotal_settings")
    public List<TemplateSubtotalSetting> templateSubtotalSettings() {
        return templateSubtotalSettings;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<TemplateItemSetting> templateItemSettings;
        private List<TemplateSubtotalSetting> templateSubtotalSettings;

        /**
         * The template item headers display preference.
         */
        @JasonerProperty("template_item_settings")
        public Builder templateItemSettings(List<TemplateItemSetting> value) {
            this.templateItemSettings = value;
            return this;
        }

        /**
         * The template subtotal headers display preference.
         */
        @JasonerProperty("template_subtotal_settings")
        public Builder templateSubtotalSettings(List<TemplateSubtotalSetting> value) {
            this.templateSubtotalSettings = value;
            return this;
        }

        public TemplateSettings build() {
            return new TemplateSettings(this);
        }

    }

}

