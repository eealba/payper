package io.github.eealba.paypal.api;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

public class CustomerDTO {


    private final String name;
    @JasonerProperty("last_name")
    private final String lastName;

    private final Integer id;

    private final LocalDate birthday;

    private final BigDecimal credit;

    private final Boolean active;

    private final Instant created;

    private final Frequency frequency;

    private final List<Link> links;

    private final value value;

    private CustomerDTO(Builder builder) {
        lastName = builder.lastName;
        credit = builder.credit;
        active = builder.active;
        created = builder.created;
        frequency = builder.frequency;
        links = builder.links;
        value = builder.value;
        name = Objects.requireNonNull(builder.name);
        id = Objects.requireNonNull(builder.id);
        birthday = Objects.requireNonNull(builder.birthday);
    }


    public String name() {
        return name;
    }

    @JasonerProperty("last_name")
    public String lastName() {
        return lastName;
    }


    public Integer id() {
        return id;
    }


    public LocalDate birthday() {
        return birthday;
    }


    public BigDecimal credit() {
        return credit;
    }


    public Boolean active() {
        return active;
    }


    public Instant created() {
        return created;
    }


    public Frequency frequency() {
        return frequency;
    }


    public List<Link> links() {
        return links;
    }


    public value value() {
        return value;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String lastName;
        private Integer id;
        private LocalDate birthday;
        private BigDecimal credit;
        private Boolean active;
        private Instant created;
        private Frequency frequency;
        private List<Link> links;
        private value value;


        public Builder name(String value) {
            this.name = value;
            return this;
        }

        @JasonerProperty("last_name")
        public Builder lastName(String value) {
            this.lastName = value;
            return this;
        }


        public Builder id(Integer value) {
            this.id = value;
            return this;
        }


        public Builder birthday(LocalDate value) {
            this.birthday = value;
            return this;
        }


        public Builder credit(BigDecimal value) {
            this.credit = value;
            return this;
        }


        public Builder active(Boolean value) {
            this.active = value;
            return this;
        }


        public Builder created(Instant value) {
            this.created = value;
            return this;
        }


        public Builder frequency(Frequency value) {
            this.frequency = value;
            return this;
        }


        public Builder links(List<Link> value) {
            this.links = value;
            return this;
        }


        public Builder value(value value) {
            this.value = value;
            return this;
        }

        public CustomerDTO build() {
            return new CustomerDTO(this);
        }

    }
    /**
     * The Card brand
     */
    public enum value {
        VISA,
        MASTERCARD,
        AMEX,
        DISCOVER
    }
}
