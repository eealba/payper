package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The internationalized email address.<blockquote><strong>Note:</strong> Up to 64 characters are allowed before and 255 
characters are allowed after the <code>@</code> sign. However, the generally accepted maximum length for an email 
address is 254 characters. The pattern verifies that an unquoted <code>@</code> sign exists.</blockquote>
 */
@JasonerSingleVO
public record Email(String value) {

    public Email(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^.+@[^\"\\-].+$")) {
            throw new IllegalArgumentException("The value: " + value + " does not match the required pattern");
        }
        this.value = value;
    }

}
