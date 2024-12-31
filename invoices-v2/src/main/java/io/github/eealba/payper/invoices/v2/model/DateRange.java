package io.github.eealba.payper.invoices.v2.model;

import java.time.LocalDate;

/**
 * The date range. Filters invoices by creation date, invoice date, due date, and payment date.
 */
public record DateRange(LocalDate start, LocalDate end) {

    public DateRange(LocalDate start, LocalDate end) {
        if (start == null) {
            throw new IllegalArgumentException("Field start can`t be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("Field end can`t be null");
        }
        this.start = start;
        this.end = end;
    }

}
