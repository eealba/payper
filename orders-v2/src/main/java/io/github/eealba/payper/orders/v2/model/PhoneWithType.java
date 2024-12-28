package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The phone information.
 */
public class PhoneWithType {


    @JasonerProperty("phone_type")
    private final PhoneType phoneType;
    @JasonerProperty("phone_number")
    private final Phone phoneNumber;

    private PhoneWithType(Builder builder) {
        phoneType = builder.phoneType;
        phoneNumber = Objects.requireNonNull(builder.phoneNumber);
    }

    /**
     * phoneType
     */
    @JasonerProperty("phone_type")
    public PhoneType phoneType() {
        return phoneType;
    }

    /**
     * phoneNumber
     */
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

        /**
         * phoneType
         */
        @JasonerProperty("phone_type")
        public Builder phoneType(PhoneType value) {
            this.phoneType = value;
            return this;
        }

        /**
         * phoneNumber
         */
        @JasonerProperty("phone_number")
        public Builder phoneNumber(Phone value) {
            this.phoneNumber = value;
            return this;
        }

        public PhoneWithType build() {
            return new PhoneWithType(this);
        }

    }

}

