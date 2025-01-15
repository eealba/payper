package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The template with invoice details to load with all captured fields.
 */
public class Template {


    
    private final String id;
    
    private final String name;
    @JasonerProperty("default_template")
    private final Boolean defaultTemplate;
    @JasonerProperty("template_info")
    private final TemplateInfo templateInfo;
    
    private final TemplateSettings settings;
    @JasonerProperty("unit_of_measure")
    private final UnitOfMeasure unitOfMeasure;
    @JasonerProperty("standard_template")
    private final Boolean standardTemplate;
    
    private final List<LinkDescription> links;

    private Template(Builder builder) {
        id = builder.id;
        name = builder.name;
        defaultTemplate = builder.defaultTemplate;
        templateInfo = builder.templateInfo;
        settings = builder.settings;
        unitOfMeasure = builder.unitOfMeasure;
        standardTemplate = builder.standardTemplate;
        links = builder.links;

    }

    /**
     * The ID of the template.
     */
    
    public String id() {
        return id;
    }

    /**
     * The template name.<blockquote><strong>Note:</strong> The template name must be unique.</blockquote>
     */
    
    public String name() {
        return name;
    }

    /**
     * Indicates whether this template is the default template. A invoicer can have one default template.
     */
    @JasonerProperty("default_template")
    public Boolean defaultTemplate() {
        return defaultTemplate;
    }

    /**
     * templateInfo
     */
    @JasonerProperty("template_info")
    public TemplateInfo templateInfo() {
        return templateInfo;
    }

    /**
     * settings
     */
    
    public TemplateSettings settings() {
        return settings;
    }

    /**
     * unitOfMeasure
     */
    @JasonerProperty("unit_of_measure")
    public UnitOfMeasure unitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Indicates whether this template is a invoicer-created custom template. The system generates non-custom 
templates.
     */
    @JasonerProperty("standard_template")
    public Boolean standardTemplate() {
        return standardTemplate;
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

        private String id;
        private String name;
        private Boolean defaultTemplate;
        private TemplateInfo templateInfo;
        private TemplateSettings settings;
        private UnitOfMeasure unitOfMeasure;
        private Boolean standardTemplate;
        private List<LinkDescription> links;

        /**
         * The ID of the template.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The template name.<blockquote><strong>Note:</strong> The template name must be unique.</blockquote>
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * Indicates whether this template is the default template. A invoicer can have one default template.
         */
        @JasonerProperty("default_template")
        public Builder defaultTemplate(Boolean value) {
            this.defaultTemplate = value;
            return this;
        }

        /**
         * templateInfo
         */
        @JasonerProperty("template_info")
        public Builder templateInfo(TemplateInfo value) {
            this.templateInfo = value;
            return this;
        }

        /**
         * settings
         */
        
        public Builder settings(TemplateSettings value) {
            this.settings = value;
            return this;
        }

        /**
         * unitOfMeasure
         */
        @JasonerProperty("unit_of_measure")
        public Builder unitOfMeasure(UnitOfMeasure value) {
            this.unitOfMeasure = value;
            return this;
        }

        /**
         * Indicates whether this template is a invoicer-created custom template. The system generates non-custom 
templates.
         */
        @JasonerProperty("standard_template")
        public Builder standardTemplate(Boolean value) {
            this.standardTemplate = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public Template build() {
            return new Template(this);
        }

    }

}

