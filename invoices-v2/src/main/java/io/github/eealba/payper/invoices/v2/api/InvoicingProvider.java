/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.payper.invoices.v2.api;

import io.github.eealba.payper.core.client.PayperConfig;
import io.github.eealba.payper.core.util.Providers;

/**
 * Abstract class representing the provider for Invoicing.
 * This class provides methods to create and manage invoices using a specified configuration.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Create a new InvoicingProvider instance
 * InvoicingProvider provider = InvoicingProvider.provider();
 *
 * // Create a new InvoicingApiClient instance with default configuration
 * InvoicingApiClient invoices = provider.create(PayperConfig.builder().build());
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class InvoicingProvider {
    private static final String DEFAULT = "io.github.eealba.payper.invoices.v2.internal.InvoicingProviderImpl";

    /**
     * Constructs a new InvoicingProvider.
     */
    public InvoicingProvider() {
    }

    /**
     * Returns the default InvoicingProvider instance.
     *
     * @return the default InvoicingProvider instance
     */
    public static InvoicingProvider provider() {
        return Providers.getProvider(InvoicingProvider.class, DEFAULT);
    }

    /**
     * Creates a new InvoicingApiClient instance using the specified configuration.
     *
     * @param config the configuration to be used
     * @return a new InvoicingApiClient instance
     */
    public abstract InvoicingApiClient create(PayperConfig config);

}
