package io.github.eealba.payper.catalog.products.v1.model;

import io.github.eealba.jasoner.JasonerSingleVO;

import java.util.List;

/**
 * An array of JSON patch objects to apply partial updates to resources.
 */
@JasonerSingleVO
public record PatchRequest(List<Patch> value) {

    public PatchRequest(List<Patch> value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        this.value = value;
    }

}
