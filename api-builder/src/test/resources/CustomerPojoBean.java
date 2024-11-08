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
    public String name() {
        return name;
    }

    /**
     * lastname of customer
     * @return The current value of lastName.
     */
    public String lastName() {
        return lastName;
    }

    /**
     * customer ID
     * @return The current value of id.
     */
    public Integer id() {
        return id;
    }

    /**
     * Birthday
     * @return The current value of birthday.
     */
    public LocalDate birthday() {
        return birthday;
    }

    /**
     * Credit amount
     * @return The current value of credit.
     */
    public BigDecimal credit() {
        return credit;
    }

    /**
     * Customer active
     * @return The current value of active.
     */
    public Boolean active() {
        return active;
    }

    /**
     * created Timestamp
     * @return The current value of created.
     */
    public Instant created() {
        return created;
    }

    /**
     * frequency
     * @return The current value of frequency.
     */
    public Frequency frequency() {
        return frequency;
    }

    /**
     * link array
     * @return The current value of links.
     */
    public List<Link> links() {
        return links;
    }
    /**
     * Name of customer
     * @param name New value for name.
     */
    public void name(String name) {
        this.name = name;
    }

    /**
     * lastname of customer
     * @param lastName New value for lastName.
     */
    public void lastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * customer ID
     * @param id New value for id.
     */
    public void id(Integer id) {
        this.id = id;
    }

    /**
     * Birthday
     * @param birthday New value for birthday.
     */
    public void birthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Credit amount
     * @param credit New value for credit.
     */
    public void credit(BigDecimal credit) {
        this.credit = credit;
    }

    /**
     * Customer active
     * @param active New value for active.
     */
    public void active(Boolean active) {
        this.active = active;
    }

    /**
     * created Timestamp
     * @param created New value for created.
     */
    public void created(Instant created) {
        this.created = created;
    }

    /**
     * frequency
     * @param frequency New value for frequency.
     */
    public void frequency(Frequency frequency) {
        this.frequency = frequency;
    }

    /**
     * link array
     * @param links New value for links.
     */
    public void links(List<Link> links) {
        this.links = links;
    }
}
