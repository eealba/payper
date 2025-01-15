package io.github.eealba.payper.invoices.v2.model;



/**
 * The discount as a percent or amount at invoice level. The invoice discount amount is subtracted from the item total.
 */
public class Discount {


    
    private final Percentage percent;
    
    private final Money amount;

    private Discount(Builder builder) {
        percent = builder.percent;
        amount = builder.amount;

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

        private Percentage percent;
        private Money amount;

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

        public Discount build() {
            return new Discount(this);
        }

    }

}

