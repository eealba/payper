package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The amount range.
 */
public record AmountRange(@JasonerProperty("lower_amount") Money lowerAmount, @JasonerProperty("upper_amount") Money upperAmount) {

    public AmountRange(Money lowerAmount, Money upperAmount) {
        if (lowerAmount == null) {
            throw new IllegalArgumentException("Field lowerAmount can`t be null");
        }
        if (upperAmount == null) {
            throw new IllegalArgumentException("Field upperAmount can`t be null");
        }
        this.lowerAmount = lowerAmount;
        this.upperAmount = upperAmount;
    }

}
