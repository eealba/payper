package io.github.eealba.paypal.api;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * This is a customer Pojo
 */
public class Customer {

    /**
     * Name of customer
     */
    private String name;

    /**
     * lastname of customer
     */
    private String lastName;

    /**
     * customer ID
     */
    private Integer id;

    /**
     * Birthday
     */
    private LocalDate birthday;

    /**
     * Credit amount
     */
    private BigDecimal credit;

    /**
     * Customer active
     */
    private Boolean active;

    /**
     * created Timestamp
     */
    private Instant created;

    /**
     * frequency
     */
    private Frequency frequency;

    /**
     * link array
     */
    private List<Link> links;
    /**
     * Name of customer
     * @return The current value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * lastname of customer
     * @return The current value of lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * customer ID
     * @return The current value of id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Birthday
     * @return The current value of birthday.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Credit amount
     * @return The current value of credit.
     */
    public BigDecimal getCredit() {
        return credit;
    }

    /**
     * Customer active
     * @return The current value of active.
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * created Timestamp
     * @return The current value of created.
     */
    public Instant getCreated() {
        return created;
    }

    /**
     * frequency
     * @return The current value of frequency.
     */
    public Frequency getFrequency() {
        return frequency;
    }

    /**
     * link array
     * @return The current value of links.
     */
    public List<Link> getLinks() {
        return links;
    }
    /**
     * Name of customer
     * @param name New value for name.
     */
    public Customer name(String name) {
        this.name = name;
        return this;
    }

    /**
     * lastname of customer
     * @param lastName New value for lastName.
     */
    public Customer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * customer ID
     * @param id New value for id.
     */
    public Customer id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Birthday
     * @param birthday New value for birthday.
     */
    public Customer birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    /**
     * Credit amount
     * @param credit New value for credit.
     */
    public Customer credit(BigDecimal credit) {
        this.credit = credit;
        return this;
    }

    /**
     * Customer active
     * @param active New value for active.
     */
    public Customer active(Boolean active) {
        this.active = active;
        return this;
    }

    /**
     * created Timestamp
     * @param created New value for created.
     */
    public Customer created(Instant created) {
        this.created = created;
        return this;
    }

    /**
     * frequency
     * @param frequency New value for frequency.
     */
    public Customer frequency(Frequency frequency) {
        this.frequency = frequency;
        return this;
    }

    /**
     * link array
     * @param links New value for links.
     */
    public Customer links(List<Link> links) {
        this.links = links;
        return this;
    }
}
