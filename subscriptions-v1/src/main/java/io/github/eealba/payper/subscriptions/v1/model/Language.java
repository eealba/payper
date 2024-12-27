package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The [language tag](https://tools.ietf.org/html/bcp47#section-2) for the language in which to localize the error-related 
strings, such as messages, issues, and suggested actions. The tag is made up of the [ISO 639-2 language 
code](https://www.loc.gov/standards/iso639-2/php/code_list.php), the optional [ISO-15924 script 
tag](https://www.unicode.org/iso15924/codelists.html), and the [ISO-3166 alpha-2 country 
code](/docs/integration/direct/rest/country-codes/).
 */
@JasonerSingleVO
public record Language(String value) {

    public Language(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^[a-z]{2}(?:-[A-Z][a-z]{3})?(?:-(?:[A-Z]{2}))?$")) {
            throw new IllegalArgumentException("The value: " + value + " does not match the required pattern");
        }
        this.value = value;
    }

}
