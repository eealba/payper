package io.github.eealba.payper.catalog.products.v1.model;
/**
 * The location of the field that caused the error. Value is `body`, `path`, or `query`.
 */
public enum ErrorLocation {
    BODY,
    PATH,
    QUERY
}