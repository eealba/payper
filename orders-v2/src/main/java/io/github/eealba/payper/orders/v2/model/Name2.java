package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * The name of the party.
 */
public class Name2 {


    
    private final String prefix;
    @JasonerProperty("given_name")
    private final String givenName;
    
    private final String surname;
    @JasonerProperty("middle_name")
    private final String middleName;
    
    private final String suffix;
    @JasonerProperty("full_name")
    private final String fullName;

    private Name2(Builder builder) {
        prefix = builder.prefix;
        givenName = builder.givenName;
        surname = builder.surname;
        middleName = builder.middleName;
        suffix = builder.suffix;
        fullName = builder.fullName;

    }

    /**
     * The prefix, or title, to the party's name.
     */
    
    public String prefix() {
        return prefix;
    }

    /**
     * When the party is a person, the party's given, or first, name.
     */
    @JasonerProperty("given_name")
    public String givenName() {
        return givenName;
    }

    /**
     * When the party is a person, the party's surname or family name. Also known as the last name. Required when the 
party is a person. Use also to store multiple surnames including the matronymic, or mother's, surname.
     */
    
    public String surname() {
        return surname;
    }

    /**
     * When the party is a person, the party's middle name. Use also to store multiple middle names including the 
patronymic, or father's, middle name.
     */
    @JasonerProperty("middle_name")
    public String middleName() {
        return middleName;
    }

    /**
     * The suffix for the party's name.
     */
    
    public String suffix() {
        return suffix;
    }

    /**
     * When the party is a person, the party's full name.
     */
    @JasonerProperty("full_name")
    public String fullName() {
        return fullName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String prefix;
        private String givenName;
        private String surname;
        private String middleName;
        private String suffix;
        private String fullName;

        /**
         * The prefix, or title, to the party's name.
         */
        
        public Builder prefix(String value) {
            this.prefix = value;
            return this;
        }

        /**
         * When the party is a person, the party's given, or first, name.
         */
        @JasonerProperty("given_name")
        public Builder givenName(String value) {
            this.givenName = value;
            return this;
        }

        /**
         * When the party is a person, the party's surname or family name. Also known as the last name. Required when the 
party is a person. Use also to store multiple surnames including the matronymic, or mother's, surname.
         */
        
        public Builder surname(String value) {
            this.surname = value;
            return this;
        }

        /**
         * When the party is a person, the party's middle name. Use also to store multiple middle names including the 
patronymic, or father's, middle name.
         */
        @JasonerProperty("middle_name")
        public Builder middleName(String value) {
            this.middleName = value;
            return this;
        }

        /**
         * The suffix for the party's name.
         */
        
        public Builder suffix(String value) {
            this.suffix = value;
            return this;
        }

        /**
         * When the party is a person, the party's full name.
         */
        @JasonerProperty("full_name")
        public Builder fullName(String value) {
            this.fullName = value;
            return this;
        }

        public Name2 build() {
            return new Name2(this);
        }

    }


}

