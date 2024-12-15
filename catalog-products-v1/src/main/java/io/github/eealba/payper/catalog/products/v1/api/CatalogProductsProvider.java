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
package io.github.eealba.payper.catalog.products.v1.api;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.util.Providers;

/**
 * Abstract class representing the provider for CatalogProducts.
 * This class provides methods to create and manage products using a specified configuration.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Create a new CatalogProductsProvider instance
 * CatalogProductsProvider provider = CatalogProductsProvider.provider();
 *
 * // Create a new CatalogProducts instance with default configuration
 * CatalogProducts catalogProducts = provider.create(PayperConfig.builder().build());
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class CatalogProductsProvider {
    private static final String DEFAULT = "io.github.eealba.payper.catalog.products.v1.internal.CatalogProductsProviderImpl";

    /**
     * Constructs a new CatalogProductsProvider.
     */
    public CatalogProductsProvider() {
    }

    /**
     * Returns the default CatalogProductsProvider instance.
     *
     * @return the default CatalogProductsProvider instance
     */
    public static CatalogProductsProvider provider() {
        return Providers.getProvider(CatalogProductsProvider.class, DEFAULT);
    }

    /**
     * Creates a new CatalogProducts instance using the specified configuration.
     *
     * @param config the configuration to be used
     * @return a new CatalogProducts instance
     */
    public abstract CatalogProducts create(PayperConfig config);
}