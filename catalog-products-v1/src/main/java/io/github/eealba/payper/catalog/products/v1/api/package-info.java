/**
 * Provides classes and interfaces for managing catalog products in the Payper library.
 * <p>
 * This package includes the API client and model classes for interacting with
 * the PayPal Catalog Products API, allowing for the creation, retrieval, and management
 * of product details.
 * <p>
 * Main Classes:
 * - {@link io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient} - The main API client for the Catalog Products API.
 * - {@link io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST} - Represents a request to create a product.
 * - {@link io.github.eealba.payper.catalog.products.v1.model.Product} - Represents a product.
 * <p>
 * Usage Examples:
 *
 * <pre>{@code
 * import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
 * import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;
 *
 * public class PayperExample {
 *     public static void main(String[] args) {
 *         var catalogProductsApiClient = CatalogProductsApiClient.create();
 *         var productRequest = ProductRequestPOST.builder()
 *             .name("Streaming Service")
 *             .description("Streaming Service product")
 *             .type("SERVICE")
 *             .category("SOFTWARE")
 *             .imageUrl("https://example.com/streaming.jpg")
 *             .homeUrl("https://example.com/home")
 *             .build();
 *
 *         var product = catalogProductsApiClient.products().create()
 *             .withPrefer("return=representation")
 *             .withPaypalRequestId("request-id")
 *             .withBody(productRequest)
 *             .retrieve().toEntity();
 *
 *         System.out.println("Created product ID: " + product.id());
 *     }
 * }
 * }</pre>
 *
 * <pre>{@code
 * import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
 *
 * public class PayperExample {
 *     public static void main(String[] args) {
 *         var catalogProductsApiClient = CatalogProductsApiClient.create();
 *         var product = catalogProductsApiClient.products().get().withId("1").retrieve().toEntity();
 *
 *         System.out.println("Retrieved product ID: " + product.id());
 *     }
 * }
 * }</pre>
 */
package io.github.eealba.payper.catalog.products.v1.api;