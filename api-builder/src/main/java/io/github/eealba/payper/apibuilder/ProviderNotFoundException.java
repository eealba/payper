package io.github.eealba.payper.apibuilder;

import java.io.Serial;

/**
 * The type Provider not found exception.
 */
public class ProviderNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Provider not found exception.
     *
     * @param providerName the provider name
     */
    public ProviderNotFoundException(String providerName) {
        super("Provider for " + providerName + " not found");
    }

    /**
     * Instantiates a new Provider not found exception.
     *
     * @param providerName the provider name
     * @param x            the x
     */
    public ProviderNotFoundException(String providerName, Exception x) {
        super("Provider for " + providerName + " not found", x);
    }

}
