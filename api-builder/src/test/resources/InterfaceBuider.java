package io.github.eealba.paypal.api;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface CustomerBuilder {

    /**
     * Name of customer
     */
    public CustomerBuilder name(String value);

    /**
     * lastname of customer
     */
    public CustomerBuilder lastName(String value);

    /**
     * customer ID
     */
    public CustomerBuilder id(Integer value);

    /**
     * Birthday
     */
    public CustomerBuilder birthday(LocalDate value);

    /**
     * Credit amount
     */
    public CustomerBuilder credit(BigDecimal value);

    /**
     * Customer active
     */
    public CustomerBuilder active(Boolean value);

    /**
     * created Timestamp
     */
    public CustomerBuilder created(Instant value);

    /**
     * frequency
     */
    public CustomerBuilder frequency(Frequency value);

    /**
     * link array
     */
    public CustomerBuilder links(List<Link> value);

    /**
     * The Customer instance
     */
    public Customer build();

}
