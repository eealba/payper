package io.github.eealba.payper.invoices.v2.model;



/**
 * The custom amount to apply to an invoice. If you include a label, you must include a custom amount.
 */
public class CustomAmount {


    
    private final String label;
    
    private final Money amount;

    private CustomAmount(Builder builder) {
        label = builder.label;
        amount = builder.amount;
    }

    /**
     * The label to the custom amount of the invoice.
     */
    
    public String label() {
        return label;
    }

    /**
     * amount
     */
    
    public Money amount() {
        return amount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String label;
        private Money amount;

        /**
         * The label to the custom amount of the invoice.
         */
        
        public Builder label(String value) {
            this.label = value;
            return this;
        }

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        public CustomAmount build() {
            return new CustomAmount(this);
        }

    }

}

