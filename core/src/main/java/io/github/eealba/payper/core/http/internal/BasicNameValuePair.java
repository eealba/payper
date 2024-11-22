package io.github.eealba.payper.core.http.internal;


import io.github.eealba.paypal.core.http.NameValuePair;

/**
 * The type Basic name value pair.
 */
final class BasicNameValuePair implements NameValuePair {
	private final String name;
	private final String value;

    /**
     * Instantiates a new Basic name value pair.
     *
     * @param name  the name
     * @param value the value
     */
    BasicNameValuePair(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}
}
