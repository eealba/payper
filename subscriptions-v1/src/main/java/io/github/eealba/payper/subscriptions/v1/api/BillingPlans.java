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

import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanCollection;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemesListRequest;

/**
 * Interface representing Billing Plans.
 * This interface provides methods to create, list, get, update, activate, deactivate, and update pricing schemes for billing plans.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Create a new plan
 * PlanRequestPOST planRequest = new PlanRequestPOST();
 * Plan plan = billingPlans.create()
 *                 .withPrefer("return=representation")
 *                 .withPaypalRequestId("request-id")
 *                 .withBody(planRequest)
 *                 .retrieve().toFuture().join();
 *
 * // List plans
 * PlanCollection planCollection = billingPlans.list()
 *                 .withProductId("1")
 *                 .withPlanIds("1")
 *                 .withPageSize(10)
 *                 .withPage(1)
 *                 .withTotalRequired(true)
 *                 .retrieve().toEntity();
 *
 * // Get a plan
 * Plan plan = billingPlans.get()
 *                 .withId("1")
 *                 .retrieve().toEntity();
 *
 * // Update a plan
 * PatchRequest patchRequest = new PatchRequest();
 * billingPlans.update()
 *             .withId("1")
 *             .withBody(patchRequest)
 *             .retrieve().toFuture().join();
 *
 * // Activate a plan
 * billingPlans.activate()
 *             .withId("1")
 *             .retrieve().toFuture().join();
 *
 * // Deactivate a plan
 * billingPlans.deactivate()
 *             .withId("1")
 *             .retrieve().toFuture().join();
 *
 * // Update pricing schemes
 * UpdatePricingSchemesListRequest updateRequest = new UpdatePricingSchemesListRequest();
 * billingPlans.updatePricingSchemes()
 *             .withId("18")
 *             .withBody(updateRequest)
 *             .retrieve().toFuture().join();
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface BillingPlans {
    /**
     * Creates a new plan.
     *
     * @return the create plan request specification
     */
    CreatePlan create();

    /**
     * Lists plans.
     *
     * @return the list plans request specification
     */
    ListPlans list();

    /**
     * Gets a plan by ID.
     *
     * @return the get plan request specification
     */
    GetPlan get();

    /**
     * Updates a plan.
     *
     * @return the update plan request specification
     */
    UpdatePlan update();

    /**
     * Activates a plan.
     *
     * @return the activate plan request specification
     */
    ActivatePlan activate();

    /**
     * Deactivates a plan.
     *
     * @return the deactivate plan request specification
     */
    DeactivatePlan deactivate();

    /**
     * Updates pricing schemes for a plan.
     *
     * @return the update pricing schemes request specification
     */
    UpdatePricingSchemes updatePricingSchemes();

    /**
     * Interface for creating a plan.
     */
    interface CreatePlan extends RequestSpec<Plan, ErrorDefault>,
            RequestSpec.BodySpec<CreatePlan, PlanRequestPOST>,
            RequestSpec.PreferSpec<CreatePlan>,
            RequestSpec.PaypalRequestIdSpec<CreatePlan> {
    }

    /**
     * Interface for listing plans.
     */
    interface ListPlans extends RequestSpec<PlanCollection, ErrorDefault>, RequestSpec.PreferSpec<ListPlans>,
    RequestSpec.PaginationSpec<ListPlans>{
        /**
         * Sets the product ID for the request.
         *
         * @param productId the product ID
         * @return the list plans request specification
         */
        ListPlans withProductId(String productId);

        /**
         * Sets the plan IDs for the request.
         *
         * @param planIds the plan IDs
         * @return the list plans request specification
         */
        ListPlans withPlanIds(String planIds);



    }

    /**
     * Interface for getting a plan.
     */
    interface GetPlan extends RequestSpec<Plan, ErrorDefault>, RequestSpec.IdSpec<GetPlan>,
    RequestSpec.FieldsSpec<GetPlan>{
    }

    /**
     * Interface for updating a plan.
     */
    interface UpdatePlan extends RequestSpec<Void, ErrorDefault>, RequestSpec.BodySpec<UpdatePlan, PatchRequest>,
            RequestSpec.IdSpec<UpdatePlan> {
    }

    /**
     * Interface for activating a plan.
     */
    interface ActivatePlan extends RequestSpec<Void, ErrorDefault>, RequestSpec.IdSpec<ActivatePlan> {
    }

    /**
     * Interface for deactivating a plan.
     */
    interface DeactivatePlan extends RequestSpec<Void, ErrorDefault>, RequestSpec.IdSpec<DeactivatePlan> {
    }

    /**
     * Interface for updating pricing schemes for a plan.
     */
    interface UpdatePricingSchemes extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.BodySpec<UpdatePricingSchemes, UpdatePricingSchemesListRequest>,
            RequestSpec.IdSpec<UpdatePricingSchemes> {
    }
}