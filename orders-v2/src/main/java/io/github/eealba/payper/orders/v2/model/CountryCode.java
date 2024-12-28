package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The [2-character ISO 3166-1 code](/api/rest/reference/country-codes/) that identifies the country or 
region.<blockquote><strong>Note:</strong> The country code for Great Britain is <code>GB</code> and not <code>UK</code> 
as used in the top-level domain names for that country. Use the `C2` country code for China worldwide for comparable 
uncontrolled price (CUP) method, bank card, and cross-border transactions.</blockquote>
 */
@JasonerSingleVO
public record CountryCode(String value) {

    public CountryCode(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^([A-Z]{2}|C2)$")) {
            throw new IllegalArgumentException("The value: " + value + " does not match the required pattern");
        }
        this.value = value;
    }

}
