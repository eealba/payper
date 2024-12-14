package io.github.eealba.payper.catalog.products.v1.model;

import java.util.List;

/**
 * An array of JSON patch objects to apply partial updates to resources.
 */
public record PatchRequest(List<Patch> value) {

    public PatchRequest(List<Patch> value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        this.value = value;
    }

}
