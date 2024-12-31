package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The payment details of the invoice. Includes payment type, method, date, discount, and transaction type.
 */
public class PaymentDetail {


    
    private final PaymentType type;
    @JasonerProperty("payment_id")
    private final String paymentId;
    @JasonerProperty("payment_date")
    private final LocalDate paymentDate;
    
    private final PaymentMethod method;
    
    private final String note;
    
    private final Money amount;
    @JasonerProperty("shipping_info")
    private final ContactNameAddress shippingInfo;

    private PaymentDetail(Builder builder) {
        type = builder.type;
        paymentId = builder.paymentId;
        paymentDate = builder.paymentDate;
        note = builder.note;
        amount = builder.amount;
        shippingInfo = builder.shippingInfo;
        method = Objects.requireNonNull(builder.method);
    }

    /**
     * type
     */
    
    public PaymentType type() {
        return type;
    }

    /**
     * The ID for a PayPal payment transaction. Required for the `PAYPAL` payment type.
     */
    @JasonerProperty("payment_id")
    public String paymentId() {
        return paymentId;
    }

    /**
     * paymentDate
     */
    @JasonerProperty("payment_date")
    public LocalDate paymentDate() {
        return paymentDate;
    }

    /**
     * method
     */
    
    public PaymentMethod method() {
        return method;
    }

    /**
     * A note associated with an external cash or check payment.
     */
    
    public String note() {
        return note;
    }

    /**
     * amount
     */
    
    public Money amount() {
        return amount;
    }

    /**
     * shippingInfo
     */
    @JasonerProperty("shipping_info")
    public ContactNameAddress shippingInfo() {
        return shippingInfo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PaymentType type;
        private String paymentId;
        private LocalDate paymentDate;
        private PaymentMethod method;
        private String note;
        private Money amount;
        private ContactNameAddress shippingInfo;

        /**
         * type
         */
        
        public Builder type(PaymentType value) {
            this.type = value;
            return this;
        }

        /**
         * The ID for a PayPal payment transaction. Required for the `PAYPAL` payment type.
         */
        @JasonerProperty("payment_id")
        public Builder paymentId(String value) {
            this.paymentId = value;
            return this;
        }

        /**
         * paymentDate
         */
        @JasonerProperty("payment_date")
        public Builder paymentDate(LocalDate value) {
            this.paymentDate = value;
            return this;
        }

        /**
         * method
         */
        
        public Builder method(PaymentMethod value) {
            this.method = value;
            return this;
        }

        /**
         * A note associated with an external cash or check payment.
         */
        
        public Builder note(String value) {
            this.note = value;
            return this;
        }

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        /**
         * shippingInfo
         */
        @JasonerProperty("shipping_info")
        public Builder shippingInfo(ContactNameAddress value) {
            this.shippingInfo = value;
            return this;
        }

        public PaymentDetail build() {
            return new PaymentDetail(this);
        }

    }

}

