package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

public class Subscriber {

    @JasonerProperty("email_address")
    private final Email emailAddress;
    @JasonerProperty("payer_id")
    private final AccountId payerId;
    
    private final Name name;
    
    private final PhoneWithType phone;
    @JasonerProperty("birth_date")
    private final DateNoTime birthDate;
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

    @JasonerProperty("email_address")
    public Email emailAddress() {
        return emailAddress;
    }

    @JasonerProperty("payer_id")
    public AccountId payerId() {
        return payerId;
    }

    
    public Name name() {
        return name;
    }

    
    public PhoneWithType phone() {
        return phone;
    }

    @JasonerProperty("birth_date")
    public DateNoTime birthDate() {
        return birthDate;
    }

    @JasonerProperty("tax_info")
    public TaxInfo taxInfo() {
        return taxInfo;
    }

    
    public AddressPortable address() {
        return address;
    }

    @JasonerProperty("shipping_address")
    public ShippingDetail shippingAddress() {
        return shippingAddress;
    }

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
        private DateNoTime birthDate;
        private TaxInfo taxInfo;
        private AddressPortable address;
        private ShippingDetail shippingAddress;
        private PaymentSourceResponse paymentSource;

        @JasonerProperty("email_address")
        public Builder emailAddress(Email value) {
            this.emailAddress = value;
            return this;
        }

        @JasonerProperty("payer_id")
        public Builder payerId(AccountId value) {
            this.payerId = value;
            return this;
        }

        
        public Builder name(Name value) {
            this.name = value;
            return this;
        }

        
        public Builder phone(PhoneWithType value) {
            this.phone = value;
            return this;
        }

        @JasonerProperty("birth_date")
        public Builder birthDate(DateNoTime value) {
            this.birthDate = value;
            return this;
        }

        @JasonerProperty("tax_info")
        public Builder taxInfo(TaxInfo value) {
            this.taxInfo = value;
            return this;
        }

        
        public Builder address(AddressPortable value) {
            this.address = value;
            return this;
        }

        @JasonerProperty("shipping_address")
        public Builder shippingAddress(ShippingDetail value) {
            this.shippingAddress = value;
            return this;
        }

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
