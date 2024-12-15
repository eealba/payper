package io.github.eealba.payper.catalog.products.v1.api;

import io.github.eealba.payper.catalog.products.v1.model.ErrorDefault;
import io.github.eealba.payper.catalog.products.v1.model.PatchRequest;
import io.github.eealba.payper.catalog.products.v1.model.Product;
import io.github.eealba.payper.catalog.products.v1.model.ProductCollection;
import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;
import io.github.eealba.payper.core.spec.RequestSpec;
/**
 * The Products API.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Create a new product
 * ProductRequestPOST productRequest = new ProductRequestPOST();
 * Product product = products.create()
 *                .withPrefer("return=representation")
 *                .withPaypalRequestId("request-id")
 *                .withBody(productRequest)
 *                .retrieve().toFuture().join();
 * // List products
 *  ProductCollection productCollection = products.list()
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
 *}</pre>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Edgar Alba
 */
public interface Products {
    /**
     * Creates a product.
     *
     * @return the create product request specification
     */
    CreateProduct create();
    /**
     * Lists products.
     *
     * @return the list products request specification
     */
    ListProducts list();
    /**
     * Gets a product.
     *
     * @return the get product request specification
     */
    GetProduct get();
    /**
     * Updates a product.
     *
     * @return the update product request specification
     */
    UpdateProduct update();

    /**
     * Interface for create a product.
     */
    interface CreateProduct extends RequestSpec<Product, ErrorDefault>,
            RequestSpec.BodySpec<CreateProduct, ProductRequestPOST>,
            RequestSpec.PreferSpec<CreateProduct>,
            RequestSpec.PaypalRequestIdSpec<CreateProduct> {
    }
    /**
     * Interface for getting a product.
     */
    interface ListProducts extends RequestSpec<ProductCollection, ErrorDefault> {
       
        /**
         * Sets the page size for the request.
         *
         * @param pageSize the page size
         * @return the list products request specification
         */
        ListProducts withPageSize(int pageSize);

        /**
         * Sets the page number for the request.
         *
         * @param page the page number
         * @return the list products request specification
         */
        ListProducts withPage(int page);

        /**
         * Sets whether the total is required for the request.
         *
         * @param totalRequired whether the total is required
         * @return the list products request specification
         */
        ListProducts withTotalRequired(boolean totalRequired);
    }
    /**
     * Interface for getting a product.
     */
    interface GetProduct extends RequestSpec<Product, ErrorDefault>, RequestSpec.IdSpec<GetProduct> {

    }
    /**
     * Interface for updating a product.
     */
    interface UpdateProduct extends RequestSpec<Void, ErrorDefault>, RequestSpec.BodySpec<UpdateProduct, PatchRequest>,
            RequestSpec.IdSpec<UpdateProduct> {
    }
}
