package io.github.eealba.payper.invoices.v2.model;


import java.util.Objects;

/**
 * The tax information. Includes the tax name and tax rate of invoice items. The tax amount is added to the item total.
 */
public class Tax {


    
    private final String name;
    
    private final Percentage percent;
    
    private final Money amount;

    private Tax(Builder builder) {
        amount = builder.amount;
        name = Objects.requireNonNull(builder.name);
        percent = Objects.requireNonNull(builder.percent);
    }

    /**
     * The name of the tax applied on the invoice items.
     */
    
    public String name() {
        return name;
    }

    /**
     * percent
     */
    
    public Percentage percent() {
        return percent;
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

        private String name;
        private Percentage percent;
        private Money amount;

        /**
         * The name of the tax applied on the invoice items.
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * percent
         */
        
        public Builder percent(Percentage value) {
            this.percent = value;
            return this;
        }

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        public Tax build() {
            return new Tax(this);
        }

    }

}

