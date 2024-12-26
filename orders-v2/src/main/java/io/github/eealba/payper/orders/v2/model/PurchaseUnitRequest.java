package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;
import java.util.Objects;


/**
 * The purchase unit request. Includes required information for the payment contract.
 */
public class PurchaseUnitRequest {


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
    @JasonerProperty("soft_descriptor")
    private final String softDescriptor;
    
    private final List<Item> items;
    
    private final ShippingDetail shipping;
    @JasonerProperty("supplementary_data")
    private final SupplementaryData supplementaryData;

    private PurchaseUnitRequest(Builder builder) {
        referenceId = builder.referenceId;
        payee = builder.payee;
        paymentInstruction = builder.paymentInstruction;
        description = builder.description;
        customId = builder.customId;
        invoiceId = builder.invoiceId;
        softDescriptor = builder.softDescriptor;
        items = builder.items;
        shipping = builder.shipping;
        supplementaryData = builder.supplementaryData;
        amount = Objects.requireNonNull(builder.amount);
    }

    /**
     * The API caller-provided external ID for the purchase unit. Required for multiple purchase units when you must 
update the order through `PATCH`. If you omit this value and the order contains only one purchase unit, PayPal 
sets this value to `default`.
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
     * The purchase description. The maximum length of the character is dependent on the type of characters used. The 
character length is specified assuming a US ASCII character. Depending on type of character; (e.g. accented 
character, Japanese characters) the number of characters that that can be specified as input might not equal 
the permissible max length.
     */
    
    public String description() {
        return description;
    }

    /**
     * The API caller-provided external ID. Used to reconcile client transactions with PayPal transactions. Appears 
in transaction and settlement reports but is not visible to the payer.
     */
    @JasonerProperty("custom_id")
    public String customId() {
        return customId;
    }

    /**
     * The API caller-provided external invoice number for this order. Appears in both the payer's transaction 
history and the emails that the payer receives.
     */
    @JasonerProperty("invoice_id")
    public String invoiceId() {
        return invoiceId;
    }

    /**
     * The soft descriptor is the dynamic text used to construct the statement descriptor that appears on a payer's 
card statement.<br><br>If an Order is paid using the "PayPal Wallet", the statement descriptor will appear in 
following format on the payer's card statement: 
<code><var>PAYPAL_prefix</var>+(space)+<var>merchant_descriptor</var>+(space)+ 
<var>soft_descriptor</var></code><blockquote><strong>Note:</strong> The merchant descriptor is the descriptor 
of the merchant’s payment receiving preferences which can be seen by logging into the merchant account 
https://www.sandbox.paypal.com/businessprofile/settings/info/edit</blockquote>The <code>PAYPAL</code> prefix 
uses 8 characters. Only the first 22 characters will be displayed in the statement. <br>For example, 
if:<ul><li>The PayPal prefix toggle is <code>PAYPAL *</code>.</li><li>The merchant descriptor in the profile 
is <code>Janes Gift</code>.</li><li>The soft descriptor is <code>800-123-1234</code>.</li></ul>Then, the 
statement descriptor on the card is <code>PAYPAL * Janes Gift 80</code>.
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
    
    public ShippingDetail shipping() {
        return shipping;
    }

    /**
     * supplementaryData
     */
    @JasonerProperty("supplementary_data")
    public SupplementaryData supplementaryData() {
        return supplementaryData;
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
        private String softDescriptor;
        private List<Item> items;
        private ShippingDetail shipping;
        private SupplementaryData supplementaryData;

        /**
         * The API caller-provided external ID for the purchase unit. Required for multiple purchase units when you must 
update the order through `PATCH`. If you omit this value and the order contains only one purchase unit, PayPal 
sets this value to `default`.
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
         * The purchase description. The maximum length of the character is dependent on the type of characters used. The 
character length is specified assuming a US ASCII character. Depending on type of character; (e.g. accented 
character, Japanese characters) the number of characters that that can be specified as input might not equal 
the permissible max length.
         */
        
        public Builder description(String value) {
            this.description = value;
            return this;
        }

        /**
         * The API caller-provided external ID. Used to reconcile client transactions with PayPal transactions. Appears 
in transaction and settlement reports but is not visible to the payer.
         */
        @JasonerProperty("custom_id")
        public Builder customId(String value) {
            this.customId = value;
            return this;
        }

        /**
         * The API caller-provided external invoice number for this order. Appears in both the payer's transaction 
history and the emails that the payer receives.
         */
        @JasonerProperty("invoice_id")
        public Builder invoiceId(String value) {
            this.invoiceId = value;
            return this;
        }

        /**
         * The soft descriptor is the dynamic text used to construct the statement descriptor that appears on a payer's 
card statement.<br><br>If an Order is paid using the "PayPal Wallet", the statement descriptor will appear in 
following format on the payer's card statement: 
<code><var>PAYPAL_prefix</var>+(space)+<var>merchant_descriptor</var>+(space)+ 
<var>soft_descriptor</var></code><blockquote><strong>Note:</strong> The merchant descriptor is the descriptor 
of the merchant’s payment receiving preferences which can be seen by logging into the merchant account 
https://www.sandbox.paypal.com/businessprofile/settings/info/edit</blockquote>The <code>PAYPAL</code> prefix 
uses 8 characters. Only the first 22 characters will be displayed in the statement. <br>For example, 
if:<ul><li>The PayPal prefix toggle is <code>PAYPAL *</code>.</li><li>The merchant descriptor in the profile 
is <code>Janes Gift</code>.</li><li>The soft descriptor is <code>800-123-1234</code>.</li></ul>Then, the 
statement descriptor on the card is <code>PAYPAL * Janes Gift 80</code>.
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
        
        public Builder shipping(ShippingDetail value) {
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

        public PurchaseUnitRequest build() {
            return new PurchaseUnitRequest(this);
        }

    }


}

