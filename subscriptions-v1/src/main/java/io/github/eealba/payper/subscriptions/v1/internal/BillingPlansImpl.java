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
package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.client.PayperRequest;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanCollection;


/**
 * Implementation of the BillingPlans API
 * @see BillingPlans
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Edgar Alba
 */
class BillingPlansImpl implements BillingPlans {

    private final Payper payper;

    BillingPlansImpl(Payper payper) {
        this.payper = payper;
    }

    @Override
    public CreatePlan create() {
        var spec = PayperProvider.provider().createSpecBuilder(CreatePlan.class, payper,
                        "/v1/billing/plans", Plan.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public ListPlans list() {
        var spec = PayperProvider.provider().createSpecBuilder(ListPlans.class, payper,
                        "/v1/billing/plans", PlanCollection.class, ErrorDefault.class)
                .alias("withProductId", "query,product_id")
                .alias("withPlanIds", "query,plan_ids")
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public GetPlan get() {
        var spec = PayperProvider.provider().createSpecBuilder(GetPlan.class, payper,
                        "/v1/billing/plans/{id}", Plan.class, ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public UpdatePlan update() {
        var spec = PayperProvider.provider().createSpecBuilder(UpdatePlan.class, payper,
                        "/v1/billing/plans/{id}", Plan.class, ErrorDefault.class)
                .method(PayperRequest.Method.PATCH)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public ActivatePlan activate() {
        var spec = PayperProvider.provider().createSpecBuilder(ActivatePlan.class, payper,
                        "/v1/billing/plans/{id}/activate", Void.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public DeactivatePlan deactivate() {
        var spec = PayperProvider.provider().createSpecBuilder(DeactivatePlan.class, payper,
                        "/v1/billing/plans/{id}/deactivate", Void.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public UpdatePricingSchemes updatePricingSchemes() {
        var spec = PayperProvider.provider().createSpecBuilder(UpdatePricingSchemes.class, payper,
                        "/v1/billing/plans/{id}/update-pricing-schemes", Void.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }


}
