/**
 * Provides classes and interfaces for managing catalog products in the Payper library.
 * <p>
 * This package includes the API client and model classes for interacting with
 * the PayPal Catalog Products API, allowing for the creation, retrieval, and management
 * of product information.
 * <p>
 * Main Classes:
 * - {@link io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient} - The main API client for the Catalog Products API.
 * - {@link io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST}
 * - {@link io.github.eealba.payper.catalog.products.v1.model.Product}
 * <p>
 * Usage Examples:
 *
 * <pre>
 * {@code
 * import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
 * import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;
 *
 * public class PayperExample {
 *     public static void main(String[] args) {
 *          var catalogProductsApiClient = CatalogProductsApiClient.create();
 *
 *          var productRequest = ProductRequestPOST.builder()
 *              .name("Product Name")
 *              .description("Product Description")
 *              .type(ProductRequestPOST.Type.PHYSICAL)
 *              .category(ProductCategory.ACCESSORIES)
 *              .imageUrl("https://example.com/image.jpg")
 *              .build();
 *
 *          var product = catalogProductsApiClient.products().create().withBody(productRequest).retrieve().toEntity();
 *
 *          System.out.println("Created product ID: " + product.id());
 *     }
 *
 * }
 * </pre>
 *
 * <pre>
 * {@code
 * import io.github.eealba.payper.catalog.products.v1.api.CatalogProducts;
 *
 * public class PayperExample {
 *     public static void main(String[] args) {
 *         var products = CatalogProducts.create().products();
 *         var product = products.get().withId("1").retrieve().toEntity();
 *
 *         System.out.println("Retrieved product ID: " + product.id());
 *     }
 * }
 * }
 * </pre>
 */
package io.github.eealba.payper.catalog.products.v1;