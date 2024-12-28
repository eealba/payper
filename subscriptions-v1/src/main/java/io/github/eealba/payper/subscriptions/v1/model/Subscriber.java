package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.LocalDate;

/**
 * The subscriber response information.
 */
public class Subscriber {


    @JasonerProperty("email_address")
    private final Email emailAddress;
    @JasonerProperty("payer_id")
    private final AccountId payerId;
    
    private final Name name;
    
    private final PhoneWithType phone;
    @JasonerProperty("birth_date")
    private final LocalDate birthDate;
    @JasonerProperty("tax_info")
    private final TaxInfo taxInfo;
    
    private final AddressPortable address;
    @JasonerProperty("shipping_address")
    private final ShippingDetail shippingAddress;
    @JasonerProperty("payment_source")
    private final PaymentSourceResponse paymentSource;

    private Subscriber(Builder builder) {
        emailAddress = builder.emailAddress;
        payerId = builder.payerId;
        name = builder.name;
        phone = builder.phone;
        birthDate = builder.birthDate;
        taxInfo = builder.taxInfo;
        address = builder.address;
        shippingAddress = builder.shippingAddress;
        paymentSource = builder.paymentSource;

    }

    /**
     * emailAddress
     */
    @JasonerProperty("email_address")
    public Email emailAddress() {
        return emailAddress;
    }

    /**
     * payerId
     */
    @JasonerProperty("payer_id")
    public AccountId payerId() {
        return payerId;
    }

    /**
     * name
     */
    
    public Name name() {
        return name;
    }

    /**
     * phone
     */
    
    public PhoneWithType phone() {
        return phone;
    }

    /**
     * birthDate
     */
    @JasonerProperty("birth_date")
    public LocalDate birthDate() {
        return birthDate;
    }

    /**
     * taxInfo
     */
    @JasonerProperty("tax_info")
    public TaxInfo taxInfo() {
        return taxInfo;
    }

    /**
     * address
     */
    
    public AddressPortable address() {
        return address;
    }

    /**
     * shippingAddress
     */
    @JasonerProperty("shipping_address")
    public ShippingDetail shippingAddress() {
        return shippingAddress;
    }

    /**
     * paymentSource
     */
    @JasonerProperty("payment_source")
    public PaymentSourceResponse paymentSource() {
        return paymentSource;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Email emailAddress;
        private AccountId payerId;
        private Name name;
        private PhoneWithType phone;
        private LocalDate birthDate;
        private TaxInfo taxInfo;
        private AddressPortable address;
        private ShippingDetail shippingAddress;
        private PaymentSourceResponse paymentSource;

        /**
         * emailAddress
         */
        @JasonerProperty("email_address")
        public Builder emailAddress(Email value) {
            this.emailAddress = value;
            return this;
        }

        /**
         * payerId
         */
        @JasonerProperty("payer_id")
        public Builder payerId(AccountId value) {
            this.payerId = value;
            return this;
        }

        /**
         * name
         */
        
        public Builder name(Name value) {
            this.name = value;
            return this;
        }

        /**
         * phone
         */
        
        public Builder phone(PhoneWithType value) {
            this.phone = value;
            return this;
        }

        /**
         * birthDate
         */
        @JasonerProperty("birth_date")
        public Builder birthDate(LocalDate value) {
            this.birthDate = value;
            return this;
        }

        /**
         * taxInfo
         */
        @JasonerProperty("tax_info")
        public Builder taxInfo(TaxInfo value) {
            this.taxInfo = value;
            return this;
        }

        /**
         * address
         */
        
        public Builder address(AddressPortable value) {
            this.address = value;
            return this;
        }

        /**
         * shippingAddress
         */
        @JasonerProperty("shipping_address")
        public Builder shippingAddress(ShippingDetail value) {
            this.shippingAddress = value;
            return this;
        }

        /**
         * paymentSource
         */
        @JasonerProperty("payment_source")
        public Builder paymentSource(PaymentSourceResponse value) {
            this.paymentSource = value;
            return this;
        }

        public Subscriber build() {
            return new Subscriber(this);
        }

    }

}

