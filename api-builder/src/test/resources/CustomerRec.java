package io.github.eealba.paypal.api;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * This is a customer record
 */
record CustomerRec(String name, String lastName, Integer id, LocalDate birthday, BigDecimal credit, Boolean active, Instant created, Frequency frequency, List<Link> links) {

    CustomerRec(String name, String lastName, Integer id, LocalDate birthday, BigDecimal credit, Boolean active, Instant created, Frequency frequency, List<Link> links) {
        if (name == null) {
            throw new IllegalArgumentException("Field name can`t be null");
        }
        if (lastName == null) {
            throw new IllegalArgumentException("Field lastName can`t be null");
        }
        if (id == null) {
            throw new IllegalArgumentException("Field id can`t be null");
        }
        if (birthday == null) {
            throw new IllegalArgumentException("Field birthday can`t be null");
        }
        if (credit == null) {
            throw new IllegalArgumentException("Field credit can`t be null");
        }
        if (active == null) {
            throw new IllegalArgumentException("Field active can`t be null");
        }
        if (created == null) {
            throw new IllegalArgumentException("Field created can`t be null");
        }
        if (frequency == null) {
            throw new IllegalArgumentException("Field frequency can`t be null");
        }
        if (links == null) {
            throw new IllegalArgumentException("Field links can`t be null");
        }
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.birthday = birthday;
        this.credit = credit;
        this.active = active;
        this.created = created;
        this.frequency = frequency;
        this.links = links;
    }

}