package io.github.eealba.paypal.api;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * This is a customer record
 */
public interface Customer {

    /**
     * Name of customer
     */
    String name();

    /**
     * lastname of customer
     */
    String lastName();

    /**
     * customer ID
     */
    Integer id();

    /**
     * Birthday
     */
    LocalDate birthday();

    /**
     * Credit amount
     */
    BigDecimal credit();

    /**
     * Customer active
     */
    Boolean active();

    /**
     * created Timestamp
     */
    Instant created();

    /**
     * frequency
     */
    Frequency frequency();

    /**
     * link array
     */
    List<Link> links();
}
