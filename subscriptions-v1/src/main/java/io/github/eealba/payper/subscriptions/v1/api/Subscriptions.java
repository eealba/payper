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

import io.github.eealba.payper.core.PayperConfig;

/**
 * Abstract class representing Subscriptions.
 * This class provides methods to create and manage billing plans and subscriptions.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Create a new Subscriptions instance with default configuration
 * Subscriptions subscriptions = Subscriptions.create();
 *
 * // Create a new Subscriptions instance with custom configuration
 * PayperConfig config = PayperConfig.builder().build();
 * Subscriptions subscriptionsWithConfig = Subscriptions.create(config);
 *
 * // Get plan
 * var plan = subscriptions.billingPlans().get()
 *                 .withId("1")
 *                 .retrieve().toEntity();
 * // Create plan
 * var plan = subscriptions.billingPlans().create()
 *                 .withPrefer("return=representation")
 *                 .withPaypalRequestId("request-id")
 *                 .withBody(body)
 *                 .retrieve().toFuture().join();
 *
 * // List plans
 *
 * var planCollection = subscriptions.billingPlans().list()
 *                 .withPageSize(10)
 *                 .withPage(1)
 *                 .withProductId("1")
 *                 .withTotalRequired(true)
 *                 .retrieve().toEntity();
 * // Update plan
 *
 * var response = subscriptions.billingPlans().update()
 *                  .withId("1")
 *                  .withBody(request)
 *                  .retrieve()
 *                  .toFuture()
 *                  .join();
 * // Activate plan
 *
 * var response = subscriptions.billingPlans().activate()
 *              .withId("1")
 *              .retrieve()
 *              .toFuture()
 *              .join();
 *
 * // Deactivate plan
 *
 * var response = subscriptions.billingPlans().deactivate()
 *             .withId("1")
 *             .retrieve()
 *             .toFuture()
 *             .join();
 *
 * // Update pricing schemes
 *
 * var response = subscriptions.billingPlans().updatePricingSchemes()
 *                  .withId("18")
 *                  .withBody(body)
 *                  .retrieve()
 *                  .toFuture()
 *                  .join();
 *
 * // Access billing subscriptions
 * BillingSubscriptions billingSubscriptions = subscriptions.billingSubscriptions();
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class Subscriptions {

    /**
     * Creates a new Subscriptions instance using the default configuration.
     *
     * @return a new Subscriptions instance
     */
    public static Subscriptions create() {
        return create(PayperConfig.builder().build());
    }

    /**
     * Creates a new Subscriptions instance using the specified configuration.
     *
     * @param config the configuration to be used
     * @return a new Subscriptions instance
     */
    public static Subscriptions create(PayperConfig config) {
        return SubscriptionsProvider.provider().createSubscriptions(config);
    }

    /**
     * Provides access to billing plans.
     *
     * @return the billing plans
     */
    public abstract BillingPlans billingPlans();

    /**
     * Provides access to billing subscriptions.
     *
     * @return the billing subscriptions
     */
    public abstract BillingSubscriptions billingSubscriptions();
}