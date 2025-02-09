package io.github.eealba.payper.invoices.v2.model;



/**
 * The template display preference.
 */
public class TemplateDisplayPreference {


    
    private final Boolean hidden;

    private TemplateDisplayPreference(Builder builder) {
        hidden = builder.hidden;
    }

    /**
     * Indicates whether to show or hide this field.
     */
    
    public Boolean hidden() {
        return hidden;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Boolean hidden;

        /**
         * Indicates whether to show or hide this field.
         */
        
        public Builder hidden(Boolean value) {
            this.hidden = value;
            return this;
        }

        public TemplateDisplayPreference build() {
            return new TemplateDisplayPreference(this);
        }

    }

}

