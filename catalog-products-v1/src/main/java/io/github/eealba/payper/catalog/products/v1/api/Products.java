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

import io.github.eealba.payper.catalog.products.v1.model.ErrorDefault;
import io.github.eealba.payper.catalog.products.v1.model.PatchRequest;
import io.github.eealba.payper.catalog.products.v1.model.Product;
import io.github.eealba.payper.catalog.products.v1.model.ProductCollection;
import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;
import io.github.eealba.payper.core.spec.RequestSpec;

/**
 * The Products API.
 * <p>
 * This interface provides methods for creating, listing, retrieving, and updating products
 * in the PayPal Catalog Products API. It serves as the main interface for interacting with
 * product-related endpoints.
 * <p>
 * Example usage:
 * <pre>{@code
 * // Create a new product
 * ProductRequestPOST productRequest = new ProductRequestPOST();
 * Product product = products.create()
 *                .withPrefer("return=representation")
 *                .withPaypalRequestId("request-id")
 *                .withBody(productRequest)
 *                .retrieve().toFuture().join();
 * // List products
 * ProductCollection productCollection = products.list()
 *              .withPageSize(10)
 *              .withPage(1)
 *              .withTotalRequired(true)
 *              .retrieve().toEntity();
 *
 * // Get a product
 * Product product = products.get()
 *             .withId("1")
 *             .retrieve().toEntity();
 *
 * // Update a product
 * PatchRequest patchRequest = new PatchRequest();
 * products.update()
 *            .withId("1")
 *            .withBody(patchRequest)
 *            .retrieve().toFuture().join();
 * }</pre>
 *
 * @since 1.0.0
 * @version 1.0.0
 */
public interface Products {

    /**
     * Creates a product.
     * <p>
     * This method initializes a request to create a new product in the PayPal Catalog Products API.
     * The request can be customized with various options such as setting the request body,
     * specifying the preferred return representation, and setting a PayPal request ID.
     *
     * @return the create product request specification
     */
    CreateProduct create();

    /**
     * Lists products.
     * <p>
     * This method initializes a request to list products in the PayPal Catalog Products API.
     * The request can be customized with various options such as setting the page size,
     * specifying the page number, and indicating whether the total count is required.
     *
     * @return the list products request specification
     */
    ListProducts list();

    /**
     * Gets a product.
     * <p>
     * This method initializes a request to retrieve a specific product from the PayPal Catalog Products API
     * by its ID.
     *
     * @return the get product request specification
     */
    GetProduct get();

    /**
     * Updates a product.
     * <p>
     * This method initializes a request to update an existing product in the PayPal Catalog Products API.
     * The request can be customized with various options such as setting the request body and specifying the product ID.
     *
     * @return the update product request specification
     */
    UpdateProduct update();

    /**
     * Interface for creating a product.
     * <p>
     * This interface defines the specifications for creating a product, including setting the request body,
     * specifying the preferred return representation, and setting a PayPal request ID.
     */
    interface CreateProduct extends RequestSpec<Product, ErrorDefault>,
            RequestSpec.BodySpec<CreateProduct, ProductRequestPOST>,
            RequestSpec.PreferSpec<CreateProduct>,
            RequestSpec.PaypalRequestIdSpec<CreateProduct> {
    }

    /**
     * Interface for listing products.
     * <p>
     * This interface defines the specifications for listing products, including setting the page size,
     * specifying the page number, and indicating whether the total count is required.
     */
    interface ListProducts extends RequestSpec<ProductCollection, ErrorDefault>, RequestSpec.PaginationSpec<ListProducts> {
    }

    /**
     * Interface for getting a product.
     * <p>
     * This interface defines the specifications for retrieving a specific product by its ID.
     */
    interface GetProduct extends RequestSpec<Product, ErrorDefault>, RequestSpec.IdSpec<GetProduct> {
    }

    /**
     * Interface for updating a product.
     * <p>
     * This interface defines the specifications for updating an existing product, including setting the request body
     * and specifying the product ID.
     */
    interface UpdateProduct extends RequestSpec<Void, ErrorDefault>, RequestSpec.BodySpec<UpdateProduct, PatchRequest>,
            RequestSpec.IdSpec<UpdateProduct> {
    }
}