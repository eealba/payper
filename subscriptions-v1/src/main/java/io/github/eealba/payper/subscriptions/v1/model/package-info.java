/**
 * Provides model classes for managing subscriptions in the Payper library.
 * <p>
 * This package includes the model classes for interacting with
 * the PayPal Subscriptions API, allowing for the creation, retrieval, and management
 * of subscription plans.
 * <p>
 * Main Classes:
 * - {@link io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST} - Represents a request to create a subscription plan.
 * - {@link io.github.eealba.payper.subscriptions.v1.model.Plan} - Represents a subscription plan.
 * <p>
 * Usage Examples:
 *
 * <pre>{@code
 * import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
 *
 * public class PayperModelExample {
 *     public static void main(String[] args) {
 *         var planRequest = PlanRequestPOST.builder()
 *             .name("Video Streaming Service Plan")
 *             .description("Video Streaming Service basic plan")
 *             .type("SERVICE")
 *             .category("SOFTWARE")
 *             .imageUrl("https://example.com/streaming.jpg")
 *             .homeUrl("https://example.com/home")
 *             .build();
 *
 *         System.out.println("Plan Request: " + planRequest);
 *     }
 * }
 * }</pre>
 *
 * <pre>{@code
 * import io.github.eealba.payper.subscriptions.v1.model.Plan;
 *
 * public class PayperModelExample {
 *     public static void main(String[] args) {
 *         var plan = new Plan();
 *         plan.setId("1");
 *         plan.setName("Video Streaming Service Plan");
 *
 *         System.out.println("Plan ID: " + plan.getId());
 *         System.out.println("Plan Name: " + plan.getName());
 *     }
 * }
 * }</pre>
 */
package io.github.eealba.payper.subscriptions.v1.model;