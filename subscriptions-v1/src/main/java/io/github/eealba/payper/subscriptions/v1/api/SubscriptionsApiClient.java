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
package io.github.eealba.payper.subscriptions.v1.api;

import io.github.eealba.payper.core.client.PayperConfig;

/**
 * Abstract class representing Subscriptions.
 * <p>
 * This class provides methods to create and manage billing plans and subscriptions.
 * It serves as the main entry point for creating and configuring instances of the Subscriptions API client.
 * <p>
 * The Subscriptions API client provides methods for accessing and managing billing plans and subscriptions,
 * including creating, retrieving, updating, activating, deactivating, and listing plans, as well as managing subscriptions.
 * <p>
 * Usage of this client requires proper configuration, which can be provided through the {@link PayperConfig} class.
 * The configuration includes necessary credentials and settings for authenticating and communicating with the PayPal API.
 * <p>
 * Example usage:
 * <pre>{@code
 * // Create a new Subscriptions instance with default configuration
 * SubscriptionsApiClient subscriptionsApiClient = SubscriptionsApiClient.create();
 *
 * // Create a new Subscriptions instance with custom configuration
 * PayperConfig config = PayperConfig.builder().build();
 * SubscriptionsApiClient subscriptionsApiClientWithConfig = SubscriptionsApiClient.create(config);
 *
 * // Get plan
 * var plan = subscriptionsApiClient.billingPlans().get()
 *                 .withId("1")
 *                 .retrieve().toEntity();
 * // Create plan
 * var plan = subscriptionsApiClient.billingPlans().create()
 *                 .withPrefer("return=representation")
 *                 .withPaypalRequestId("request-id")
 *                 .withBody(body)
 *                 .retrieve().toFuture().join();
 *
 * // List plans
 * var planCollection = subscriptionsApiClient.billingPlans().list()
 *                 .withPageSize(10)
 *                 .withPage(1)
 *                 .withProductId("1")
 *                 .withTotalRequired(true)
 *                 .retrieve().toEntity();
 * // Update plan
 * var response = subscriptionsApiClient.billingPlans().update()
 *                  .withId("1")
 *                  .withBody(request)
 *                  .retrieve()
 *                  .toFuture()
 *                  .join();
 * // Activate plan
 * var response = subscriptionsApiClient.billingPlans().activate()
 *              .withId("1")
 *              .retrieve()
 *              .toFuture()
 *              .join();
 *
 * // Deactivate plan
 * var response = subscriptionsApiClient.billingPlans().deactivate()
 *             .withId("1")
 *             .retrieve()
 *             .toFuture()
 *             .join();
 *
 * // Update pricing schemes
 * var response = subscriptionsApiClient.billingPlans().updatePricingSchemes()
 *                  .withId("18")
 *                  .withBody(body)
 *                  .retrieve()
 *                  .toFuture()
 *                  .join();
 *
 * // Access billing subscriptions
 * BillingSubscriptions billingSubscriptions = subscriptionsApiClient.billingSubscriptions();
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @see PayperConfig
 * @see io.github.eealba.payper.subscriptions.v1.api.BillingPlans
 * @see io.github.eealba.payper.subscriptions.v1.api.BillingSubscriptions
 */
public abstract class SubscriptionsApiClient {

    /**
     * Creates a new Subscriptions instance using the default configuration.
     * <p>
     * This method initializes the client with the default settings and credentials
     * required for communicating with the PayPal Subscriptions API.
     *
     * @return a new Subscriptions instance
     */
    public static SubscriptionsApiClient create() {
        return create(PayperConfig.builder().build());
    }

    /**
     * Creates a new Subscriptions instance using the specified configuration.
     * <p>
     * This method allows for custom configuration of the client, including setting
     * specific credentials and other settings required for authenticating and
     * communicating with the PayPal Subscriptions API.
     *
     * @param config the configuration to be used
     * @return a new Subscriptions instance
     */
    public static SubscriptionsApiClient create(PayperConfig config) {
        return SubscriptionsProvider.provider().create(config);
    }

    /**
     * Provides access to billing plans.
     * <p>
     * The BillingPlans instance provides methods for creating, retrieving, updating,
     * activating, deactivating, and listing billing plans. It serves as the main interface
     * for interacting with the billing plan-related endpoints of the PayPal Subscriptions API.
     *
     * @return the billing plans
     */
    public abstract BillingPlans billingPlans();

    /**
     * Provides access to billing subscriptions.
     * <p>
     * The BillingSubscriptions instance provides methods for creating, retrieving, updating,
     * suspending, canceling, activating, capturing, and listing transactions for subscriptions.
     * It serves as the main interface for interacting with the subscription-related endpoints
     * of the PayPal Subscriptions API.
     *
     * @return the billing subscriptions
     */
    public abstract BillingSubscriptions billingSubscriptions();
}