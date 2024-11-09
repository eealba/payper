package io.github.eealba.payper.subscriptions.v1.model;

import java.util.List;
import io.github.eealba.jasoner.JasonerProperty;

/**
 * The update pricing scheme request details.
 */
public record UpdatePricingSchemesListRequest(@JasonerProperty("pricing_schemes") List<UpdatePricingSchemeRequest> pricingSchemes) {

    public UpdatePricingSchemesListRequest(List<UpdatePricingSchemeRequest> pricingSchemes) {
        if (pricingSchemes == null) {
            throw new IllegalArgumentException("Field pricingSchemes can`t be null");
        }
        this.pricingSchemes = pricingSchemes;
    }

}
