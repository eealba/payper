package io.github.eealba.payper.invoices.v2.model;

import java.time.Instant;

/**
 * The date and time range. Filters invoices by creation date, invoice date, due date, and payment date.
 */
public record DateTimeRange(Instant start, Instant end) {

    public DateTimeRange(Instant start, Instant end) {
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
