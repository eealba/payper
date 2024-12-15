/**
 * Provides classes and interfaces for managing subscriptions in the Payper library.
 * <p>
 * This package includes the API client and model classes for interacting with
 * the PayPal Subscriptions API, allowing for the creation, retrieval, and management
 * of subscription plans.
 * <p>
 * Main Classes:
 * - {@link io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient} - The main API client for the Subscriptions API.
 * - {@link io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST}
 * - {@link io.github.eealba.payper.subscriptions.v1.model.Plan}
 * <p>
 * Usage Examples:
 *
 * <pre>
 * {@code
 * import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
 * import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
 *
 * public class PayperExample {
 *     public static void main(String[] args) {
 *         var subscriptionsApiClient = SubscriptionsApiClient.create();
 *         var planRequest = PlanRequestPOST.builder()
 *             .name("Video Streaming Service Plan")
 *             .description("Video Streaming Service basic plan")
 *             .type("SERVICE")
 *             .category("SOFTWARE")
 *             .imageUrl("https://example.com/streaming.jpg")
 *             .homeUrl("https://example.com/home")
 *             .build();
 *
 *         var plan = subscriptionsApiClient.billingPlans().create()
 *             .withPrefer("return=representation")
 *             .withPaypalRequestId("request-id")
 *             .withBody(planRequest)
 *             .retrieve().toEntity();
 *
 *         System.out.println("Created plan ID: " + plan.id());
 *     }
 * }
 * </pre>
 *
 * <pre>
 * {@code
 * import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
 *
 * public class PayperExample {
 *     public static void main(String[] args) {
 *         var subscriptionsApiClient = SubscriptionsApiClient.create();
 *         var plan = subscriptionsApiClient.billingPlans().get().withId("1").retrieve().toEntity();
 *
 *         System.out.println("Retrieved plan ID: " + plan.id());
 *     }
 * }
 * }
 * </pre>
 */
package io.github.eealba.payper.subscriptions.v1;