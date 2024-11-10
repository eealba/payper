package io.github.eealba.payper.subscriptions.v1.model;



/**
 * The internationalized email address.<blockquote><strong>Note:</strong> Up to 64 characters are allowed before and 255 
characters are allowed after the <code>@</code> sign. However, the generally accepted maximum length for an email 
address is 254 characters. The pattern verifies that an unquoted <code>@</code> sign exists.</blockquote>
 */
public record EmailAddress(String value) {

    public EmailAddress(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^.+@[^\"\\-].+$")) {
            throw new IllegalArgumentException("Invalid pattern for field value");
        }
        this.value = value;
    }

}
