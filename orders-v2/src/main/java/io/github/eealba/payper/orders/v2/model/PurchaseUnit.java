package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The purchase unit details. Used to capture required information for the payment contract.
 */
public class PurchaseUnit {


    @JasonerProperty("reference_id")
    private final String referenceId;
    
    private final AmountWithBreakdown amount;
    
    private final Payee payee;
    @JasonerProperty("payment_instruction")
    private final PaymentInstruction paymentInstruction;
    
    private final String description;
    @JasonerProperty("custom_id")
    private final String customId;
    @JasonerProperty("invoice_id")
    private final String invoiceId;
    
    private final String id;
    @JasonerProperty("soft_descriptor")
    private final String softDescriptor;
    
    private final List<Item> items;
    
    private final ShippingWithTrackingDetails shipping;
    @JasonerProperty("supplementary_data")
    private final SupplementaryData supplementaryData;
    
    private final PaymentCollection payments;

    private PurchaseUnit(Builder builder) {
        referenceId = builder.referenceId;
        amount = builder.amount;
        payee = builder.payee;
        paymentInstruction = builder.paymentInstruction;
        description = builder.description;
        customId = builder.customId;
        invoiceId = builder.invoiceId;
        id = builder.id;
        softDescriptor = builder.softDescriptor;
        items = builder.items;
        shipping = builder.shipping;
        supplementaryData = builder.supplementaryData;
        payments = builder.payments;

    }

    /**
     * The API caller-provided external ID for the purchase unit. Required for multiple purchase units when you must 
update the order through `PATCH`. If you omit this value and the order contains only one purchase unit, PayPal 
sets this value to `default`. <blockquote><strong>Note:</strong> If there are multiple purchase units, 
<code>reference_id</code> is required for each purchase unit.</blockquote>
     */
    @JasonerProperty("reference_id")
    public String referenceId() {
        return referenceId;
    }

    /**
     * amount
     */
    
    public AmountWithBreakdown amount() {
        return amount;
    }

    /**
     * payee
     */
    
    public Payee payee() {
        return payee;
    }

    /**
     * paymentInstruction
     */
    @JasonerProperty("payment_instruction")
    public PaymentInstruction paymentInstruction() {
        return paymentInstruction;
    }

    /**
     * The purchase description.
     */
    
    public String description() {
        return description;
    }

    /**
     * The API caller-provided external ID. Used to reconcile API caller-initiated transactions with PayPal 
transactions. Appears in transaction and settlement reports.
     */
    @JasonerProperty("custom_id")
    public String customId() {
        return customId;
    }

    /**
     * The API caller-provided external invoice ID for this order.
     */
    @JasonerProperty("invoice_id")
    public String invoiceId() {
        return invoiceId;
    }

    /**
     * The PayPal-generated ID for the purchase unit. This ID appears in both the payer's transaction history and the 
emails that the payer receives. In addition, this ID is available in transaction and settlement reports that 
merchants and API callers can use to reconcile transactions. This ID is only available when an order is saved 
by calling <code>v2/checkout/orders/id/save</code>.
     */
    
    public String id() {
        return id;
    }

    /**
     * The payment descriptor on account transactions on the customer's credit card statement, that PayPal sends to 
processors. The maximum length of the soft descriptor information that you can pass in the API field is 22 
characters, in the following format:<code>22 - len(PAYPAL * (8)) - len(<var>Descriptor in Payment Receiving 
Preferences of Merchant account</var> + 1)</code>The PAYPAL prefix uses 8 characters.<br/><br/>The soft 
descriptor supports the following ASCII characters:<ul><li>Alphanumeric 
characters</li><li>Dashes</li><li>Asterisks</li><li>Periods (.)</li><li>Spaces</li></ul>For Wallet payments 
marketplace integrations:<ul><li>The merchant descriptor in the Payment Receiving Preferences must be the 
marketplace name.</li><li>You can't use the remaining space to show the customer service number.</li><li>The 
remaining spaces can be a combination of seller name and country.</li></ul><br/>For unbranded payments (Direct 
Card) marketplace integrations, use a combination of the seller name and phone number.
     */
    @JasonerProperty("soft_descriptor")
    public String softDescriptor() {
        return softDescriptor;
    }

    /**
     * An array of items that the customer purchases from the merchant.
     */
    
    public List<Item> items() {
        return items;
    }

    /**
     * shipping
     */
    
    public ShippingWithTrackingDetails shipping() {
        return shipping;
    }

    /**
     * supplementaryData
     */
    @JasonerProperty("supplementary_data")
    public SupplementaryData supplementaryData() {
        return supplementaryData;
    }

    /**
     * payments
     */
    
    public PaymentCollection payments() {
        return payments;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String referenceId;
        private AmountWithBreakdown amount;
        private Payee payee;
        private PaymentInstruction paymentInstruction;
        private String description;
        private String customId;
        private String invoiceId;
        private String id;
        private String softDescriptor;
        private List<Item> items;
        private ShippingWithTrackingDetails shipping;
        private SupplementaryData supplementaryData;
        private PaymentCollection payments;

        /**
         * The API caller-provided external ID for the purchase unit. Required for multiple purchase units when you must 
update the order through `PATCH`. If you omit this value and the order contains only one purchase unit, PayPal 
sets this value to `default`. <blockquote><strong>Note:</strong> If there are multiple purchase units, 
<code>reference_id</code> is required for each purchase unit.</blockquote>
         */
        @JasonerProperty("reference_id")
        public Builder referenceId(String value) {
            this.referenceId = value;
            return this;
        }

        /**
         * amount
         */
        
        public Builder amount(AmountWithBreakdown value) {
            this.amount = value;
            return this;
        }

        /**
         * payee
         */
        
        public Builder payee(Payee value) {
            this.payee = value;
            return this;
        }

        /**
         * paymentInstruction
         */
        @JasonerProperty("payment_instruction")
        public Builder paymentInstruction(PaymentInstruction value) {
            this.paymentInstruction = value;
            return this;
        }

        /**
         * The purchase description.
         */
        
        public Builder description(String value) {
            this.description = value;
            return this;
        }

        /**
         * The API caller-provided external ID. Used to reconcile API caller-initiated transactions with PayPal 
transactions. Appears in transaction and settlement reports.
         */
        @JasonerProperty("custom_id")
        public Builder customId(String value) {
            this.customId = value;
            return this;
        }

        /**
         * The API caller-provided external invoice ID for this order.
         */
        @JasonerProperty("invoice_id")
        public Builder invoiceId(String value) {
            this.invoiceId = value;
            return this;
        }

        /**
         * The PayPal-generated ID for the purchase unit. This ID appears in both the payer's transaction history and the 
emails that the payer receives. In addition, this ID is available in transaction and settlement reports that 
merchants and API callers can use to reconcile transactions. This ID is only available when an order is saved 
by calling <code>v2/checkout/orders/id/save</code>.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The payment descriptor on account transactions on the customer's credit card statement, that PayPal sends to 
processors. The maximum length of the soft descriptor information that you can pass in the API field is 22 
characters, in the following format:<code>22 - len(PAYPAL * (8)) - len(<var>Descriptor in Payment Receiving 
Preferences of Merchant account</var> + 1)</code>The PAYPAL prefix uses 8 characters.<br/><br/>The soft 
descriptor supports the following ASCII characters:<ul><li>Alphanumeric 
characters</li><li>Dashes</li><li>Asterisks</li><li>Periods (.)</li><li>Spaces</li></ul>For Wallet payments 
marketplace integrations:<ul><li>The merchant descriptor in the Payment Receiving Preferences must be the 
marketplace name.</li><li>You can't use the remaining space to show the customer service number.</li><li>The 
remaining spaces can be a combination of seller name and country.</li></ul><br/>For unbranded payments (Direct 
Card) marketplace integrations, use a combination of the seller name and phone number.
         */
        @JasonerProperty("soft_descriptor")
        public Builder softDescriptor(String value) {
            this.softDescriptor = value;
            return this;
        }

        /**
         * An array of items that the customer purchases from the merchant.
         */
        
        public Builder items(List<Item> value) {
            this.items = value;
            return this;
        }

        /**
         * shipping
         */
        
        public Builder shipping(ShippingWithTrackingDetails value) {
            this.shipping = value;
            return this;
        }

        /**
         * supplementaryData
         */
        @JasonerProperty("supplementary_data")
        public Builder supplementaryData(SupplementaryData value) {
            this.supplementaryData = value;
            return this;
        }

        /**
         * payments
         */
        
        public Builder payments(PaymentCollection value) {
            this.payments = value;
            return this;
        }

        public PurchaseUnit build() {
            return new PurchaseUnit(this);
        }

    }

}

