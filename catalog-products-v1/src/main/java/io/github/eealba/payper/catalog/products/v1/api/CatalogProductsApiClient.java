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

/**
 * Provides an abstract client for interacting with the PayPal Catalog Products API.
 * <p>
 * This class serves as the main entry point for creating and configuring instances
 * of the Catalog Products API client. It allows for the creation of client instances
 * using either the default configuration or a specified configuration.
 * <p>
 * The Catalog Products API client provides methods for accessing and managing
 * product information, including creating, retrieving, updating, and deleting
 * product details.
 * <p>
 * Usage of this client requires proper configuration, which can be provided
 * through the {@link io.github.eealba.payper.core.PayperConfig} class. The configuration
 * includes necessary credentials and settings for authenticating and communicating
 * with the PayPal API.
 * <p>
 * Main Methods:
 * <ul>
 *   <li>{@link #create()} - Creates a new CatalogProductsApiClient instance using the default configuration.</li>
 *   <li>{@link #create(PayperConfig)} - Creates a new CatalogProductsApiClient instance using the specified configuration.</li>
 *   <li>{@link #products()} - Returns the Products instance for managing product-related operations.</li>
 * </ul>
 * <p>
 * This class is designed to be extended by specific implementations that provide
 * the actual functionality for interacting with the PayPal Catalog Products API.
 *
 * @since 1.0
 * @version 1.0
 * @see io.github.eealba.payper.core.PayperConfig
 * @see io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST
 * @see io.github.eealba.payper.catalog.products.v1.model.Product
 * @see io.github.eealba.payper.catalog.products.v1.api.CatalogProductsProvider
 */
public abstract class CatalogProductsApiClient {

    /**
     * Creates a new CatalogProductsApiClient instance using the default configuration.
     * <p>
     * This method initializes the client with the default settings and credentials
     * required for communicating with the PayPal Catalog Products API.
     *
     * @return a new CatalogProductsApiClient instance
     */
    public static CatalogProductsApiClient create() {
        return create(PayperConfig.builder().build());
    }

    /**
     * Creates a new CatalogProductsApiClient instance using the specified configuration.
     * <p>
     * This method allows for custom configuration of the client, including setting
     * specific credentials and other settings required for authenticating and
     * communicating with the PayPal Catalog Products API.
     *
     * @param config the configuration to be used
     * @return a new CatalogProductsApiClient instance
     */
    public static CatalogProductsApiClient create(PayperConfig config) {
        return CatalogProductsProvider.provider().create(config);
    }

    /**
     * Returns the Products instance for managing product-related operations.
     * <p>
     * The Products instance provides methods for creating, retrieving, updating,
     * and deleting product details. It serves as the main interface for interacting
     * with the product-related endpoints of the PayPal Catalog Products API.
     *
     * @return the Products instance
     */
    public abstract Products products();
}