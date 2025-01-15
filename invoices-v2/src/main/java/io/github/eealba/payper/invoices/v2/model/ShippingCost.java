package io.github.eealba.payper.invoices.v2.model;



/**
 * The shipping fee for all items. Includes tax on shipping.
 */
public class ShippingCost {


    
    private final Money amount;
    
    private final Tax tax;

    private ShippingCost(Builder builder) {
        amount = builder.amount;
        tax = builder.tax;

    }

    /**
     * amount
     */
    
    public Money amount() {
        return amount;
    }

    /**
     * tax
     */
    
    public Tax tax() {
        return tax;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money amount;
        private Tax tax;

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        /**
         * tax
         */
        
        public Builder tax(Tax value) {
            this.tax = value;
            return this;
        }

        public ShippingCost build() {
            return new ShippingCost(this);
        }

    }

}

