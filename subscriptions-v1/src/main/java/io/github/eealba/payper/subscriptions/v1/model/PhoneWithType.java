package io.github.eealba.payper.subscriptions.v1.model;


import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

public class PhoneWithType {

    @JasonerProperty("phone_type")
    private final PhoneType phoneType;
    @JasonerProperty("phone_number")
    private final Phone phoneNumber;

    private PhoneWithType(Builder builder) {
        phoneType = builder.phoneType;
        phoneNumber = Objects.requireNonNull(builder.phoneNumber);
    }

    @JasonerProperty("phone_type")
    public PhoneType phoneType() {
        return phoneType;
    }

    @JasonerProperty("phone_number")
    public Phone phoneNumber() {
        return phoneNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PhoneType phoneType;
        private Phone phoneNumber;

        @JasonerProperty("phone_type")
        public Builder phoneType(PhoneType value) {
            phoneType = value;
            return this;
        }

        @JasonerProperty("phone_number")
        public Builder phoneNumber(Phone value) {
            phoneNumber = value;
            return this;
        }

        public PhoneWithType build() {
            return new PhoneWithType(this);
        }

    }

}

