package io.github.eealba.paypal.api;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

class CustomerDTO implements Customer {


    private final String name;

    private final String lastName;

    private final Integer id;

    private final LocalDate birthday;

    private final BigDecimal credit;

    private final Boolean active;

    private final Instant created;

    private final Frequency frequency;

    private final List<Link> links;

    private CustomerDTO(Builder builder) {
        lastName = builder.lastName;
        credit = builder.credit;
        active = builder.active;
        created = builder.created;
        frequency = builder.frequency;
        links = builder.links;
        name = Objects.requireNonNull(builder.name);
        id = Objects.requireNonNull(builder.id);
        birthday = Objects.requireNonNull(builder.birthday);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public LocalDate birthday() {
        return birthday;
    }

    @Override
    public BigDecimal credit() {
        return credit;
    }

    @Override
    public Boolean active() {
        return active;
    }

    @Override
    public Instant created() {
        return created;
    }

    @Override
    public Frequency frequency() {
        return frequency;
    }

    @Override
    public List<Link> links() {
        return links;
    }

    static CustomerBuilder builder() {
        return new Builder();
    }

    private static class Builder implements CustomerBuilder {

        private String name;
        private String lastName;
        private Integer id;
        private LocalDate birthday;
        private BigDecimal credit;
        private Boolean active;
        private Instant created;
        private Frequency frequency;
        private List<Link> links;

        @Override
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        @Override
        public Builder lastName(String value) {
            this.lastName = value;
            return this;
        }

        @Override
        public Builder id(Integer value) {
            this.id = value;
            return this;
        }

        @Override
        public Builder birthday(LocalDate value) {
            this.birthday = value;
            return this;
        }

        @Override
        public Builder credit(BigDecimal value) {
            this.credit = value;
            return this;
        }

        @Override
        public Builder active(Boolean value) {
            this.active = value;
            return this;
        }

        @Override
        public Builder created(Instant value) {
            this.created = value;
            return this;
        }

        @Override
        public Builder frequency(Frequency value) {
            this.frequency = value;
            return this;
        }

        @Override
        public Builder links(List<Link> value) {
            this.links = value;
            return this;
        }

        @Override
        public Customer build() {
            return new CustomerDTO(this);
        }

    }

}
