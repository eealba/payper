package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The partial payment details. Includes the minimum amount that the invoicer expects from the payer.
 */
public class PartialPayment {


    @JasonerProperty("allow_partial_payment")
    private final Boolean allowPartialPayment;
    @JasonerProperty("minimum_amount_due")
    private final Money minimumAmountDue;

    private PartialPayment(Builder builder) {
        allowPartialPayment = builder.allowPartialPayment;
        minimumAmountDue = builder.minimumAmountDue;
    }

    /**
     * Indicates whether the invoice allows a partial payment. If `false`, the invoice must be paid in full. If 
`true`, the invoice allows partial payments.<blockquote><strong>Note:</strong> This feature is not available 
for users in `India`, `Brazil`, or `Israel`.</blockquote>
     */
    @JasonerProperty("allow_partial_payment")
    public Boolean allowPartialPayment() {
        return allowPartialPayment;
    }

    /**
     * minimumAmountDue
     */
    @JasonerProperty("minimum_amount_due")
    public Money minimumAmountDue() {
        return minimumAmountDue;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Boolean allowPartialPayment;
        private Money minimumAmountDue;

        /**
         * Indicates whether the invoice allows a partial payment. If `false`, the invoice must be paid in full. If 
`true`, the invoice allows partial payments.<blockquote><strong>Note:</strong> This feature is not available 
for users in `India`, `Brazil`, or `Israel`.</blockquote>
         */
        @JasonerProperty("allow_partial_payment")
        public Builder allowPartialPayment(Boolean value) {
            this.allowPartialPayment = value;
            return this;
        }

        /**
         * minimumAmountDue
         */
        @JasonerProperty("minimum_amount_due")
        public Builder minimumAmountDue(Money value) {
            this.minimumAmountDue = value;
            return this;
        }

        public PartialPayment build() {
            return new PartialPayment(this);
        }

    }

}

