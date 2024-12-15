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

import io.github.eealba.payper.core.spec.RequestSpec;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.Subscription;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionCaptureRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionRequestPost;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionReviseRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionReviseResponse;
import io.github.eealba.payper.subscriptions.v1.model.Transaction;
import io.github.eealba.payper.subscriptions.v1.model.TransactionsList;

import java.time.Instant;

/**
 * Interface representing Billing Subscriptions.
 * This interface provides methods to create, get, update, revise, suspend, cancel, activate, capture, and list transactions for subscriptions.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Create a new subscription
 * SubscriptionRequestPost subscriptionRequest = new SubscriptionRequestPost();
 * Subscription subscription = billingSubscriptions.create()
 *                 .withPrefer("return=representation")
 *                 .withPaypalRequestId("request-id")
 *                 .withBody(subscriptionRequest)
 *                 .retrieve().toFuture().join();
 *
 * // Get a subscription
 * Subscription subscription = billingSubscriptions.get()
 *                 .withId("1")
 *                 .retrieve().toEntity();
 *
 * // Update a subscription
 * PatchRequest patchRequest = new PatchRequest();
 * billingSubscriptions.update()
 *             .withId("1")
 *             .withBody(patchRequest)
 *             .retrieve().toFuture().join();
 *
 * // Revise a subscription
 * SubscriptionReviseRequest reviseRequest = new SubscriptionReviseRequest();
 * SubscriptionReviseResponse reviseResponse = billingSubscriptions.revise()
 *             .withId("1")
 *             .withBody(reviseRequest)
 *             .retrieve().toFuture().join();
 *
 * // Suspend a subscription
 * billingSubscriptions.suspend()
 *             .withId("1")
 *             .retrieve().toFuture().join();
 *
 * // Cancel a subscription
 * billingSubscriptions.cancel()
 *             .withId("1")
 *             .retrieve().toFuture().join();
 *
 * // Activate a subscription
 * billingSubscriptions.activate()
 *             .withId("1")
 *             .retrieve().toFuture().join();
 *
 * // Capture a subscription
 * SubscriptionCaptureRequest captureRequest = new SubscriptionCaptureRequest();
 * Transaction transaction = billingSubscriptions.capture()
 *             .withId("1")
 *             .withBody(captureRequest)
 *             .retrieve().toFuture().join();
 *
 * // List transactions
 * TransactionsList transactionsList = billingSubscriptions.listTransactions()
 *             .withId("1")
 *             .withStartTime(Instant.now().minus(30, ChronoUnit.DAYS))
 *             .withEndTime(Instant.now())
 *             .retrieve().toEntity();
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface BillingSubscriptions {
    /**
     * Creates a new subscription.
     *
     * @return the create subscription request specification
     */
    CreateSubscription create();

    /**
     * Gets a subscription by ID.
     *
     * @return the get subscription request specification
     */
    GetSubscription get();

    /**
     * Updates a subscription.
     *
     * @return the update subscription request specification
     */
    UpdateSubscription update();

    /**
     * Revises a subscription.
     *
     * @return the revise subscription request specification
     */
    ReviseSubscription revise();

    /**
     * Suspends a subscription.
     *
     * @return the suspend subscription request specification
     */
    SuspendSubscription suspend();

    /**
     * Cancels a subscription.
     *
     * @return the cancel subscription request specification
     */
    CancelSubscription cancel();

    /**
     * Activates a subscription.
     *
     * @return the activate subscription request specification
     */
    ActivateSubscription activate();

    /**
     * Captures a subscription.
     *
     * @return the capture subscription request specification
     */
    CaptureSubscription capture();

    /**
     * Lists transactions for a subscription.
     *
     * @return the list transactions request specification
     */
    ListTransactions listTransactions();

    /**
     * Interface for creating a subscription.
     */
    interface CreateSubscription extends RequestSpec<Subscription, ErrorDefault>,
            RequestSpec.BodySpec<CreateSubscription, SubscriptionRequestPost>,
            RequestSpec.PreferSpec<CreateSubscription>,
            RequestSpec.PaypalRequestIdSpec<CreateSubscription> {
    }

    /**
     * Interface for getting a subscription.
     */
    interface GetSubscription extends RequestSpec<Subscription, ErrorDefault>, RequestSpec.IdSpec<GetSubscription> {
        /**
         * Sets the fields to be retrieved for the request.
         *
         * @param fields the fields to be retrieved
         * @return the get subscription request specification
         */
        GetSubscription withFields(String fields);
    }

    /**
     * Interface for updating a subscription.
     */
    interface UpdateSubscription extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.BodySpec<UpdateSubscription, PatchRequest>,
            RequestSpec.IdSpec<UpdateSubscription> {
    }

    /**
     * Interface for revising a subscription.
     */
    interface ReviseSubscription extends RequestSpec<SubscriptionReviseResponse, ErrorDefault>,
            RequestSpec.BodySpec<ReviseSubscription, SubscriptionReviseRequest>,
            RequestSpec.IdSpec<ReviseSubscription> {
    }

    /**
     * Interface for suspending a subscription.
     */
    interface SuspendSubscription extends RequestSpec<Void, ErrorDefault>, RequestSpec.IdSpec<SuspendSubscription> {
    }

    /**
     * Interface for canceling a subscription.
     */
    interface CancelSubscription extends RequestSpec<Void, ErrorDefault>, RequestSpec.IdSpec<CancelSubscription> {
    }

    /**
     * Interface for activating a subscription.
     */
    interface ActivateSubscription extends RequestSpec<Void, ErrorDefault>, RequestSpec.IdSpec<ActivateSubscription> {
    }

    /**
     * Interface for capturing a subscription.
     */
    interface CaptureSubscription extends RequestSpec<Transaction, ErrorDefault>,
            RequestSpec.BodySpec<CaptureSubscription, SubscriptionCaptureRequest>,
            RequestSpec.IdSpec<CaptureSubscription>,
            RequestSpec.PreferSpec<CaptureSubscription>,
            RequestSpec.PaypalRequestIdSpec<CaptureSubscription> {
    }

    /**
     * Interface for listing transactions for a subscription.
     */
    interface ListTransactions extends RequestSpec<TransactionsList, ErrorDefault>,
            RequestSpec.IdSpec<ListTransactions> {
        /**
         * Sets the start time for the request.
         *
         * @param startTime the start time
         * @return the list transactions request specification
         */
        ListTransactions withStartTime(Instant startTime);

        /**
         * Sets the end time for the request.
         *
         * @param endTime the end time
         * @return the list transactions request specification
         */
        ListTransactions withEndTime(Instant endTime);
    }
}