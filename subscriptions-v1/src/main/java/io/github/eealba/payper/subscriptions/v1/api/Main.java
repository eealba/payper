package io.github.eealba.payper.subscriptions.v1.api;

import io.github.eealba.payper.core.Credential;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.subscriptions.v1.model.Patch;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
import io.github.eealba.payper.subscriptions.v1.model.PricingScheme;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemeRequest;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemesListRequest;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Credential credential = new Credential("clientId", "secret".toCharArray());
        PayperConfig config = PayperConfig.builder(credential, true).build();
        Subscriptions subscriptions = Subscriptions.create(config);

        // Create plan
        var plan = subscriptions
                .billingPlans()
                .create()
                .withBody(PlanRequestPOST.builder().build())
                .retrieve()
                .toEntity();

        System.out.println(plan.name());


        // List plans
        var plans = subscriptions
                .billingPlans()
                .list()
                .withProductId("product_id")
                .withPlanIds("plan_id")
                .withStatuses("status")
                .retrieve()
                .toEntity();
        System.out.println(plans.size());

        // Get plan
        var plan2 = subscriptions
                .billingPlans()
                .get()
                .withId("plan_id")
                .retrieve()
                .toEntity();

        System.out.println(plan2.name());

        // Update plan
        var path = new PatchRequest(Collections.singletonList(
                Patch.builder()
                        .op(Patch.Op.replace)
                        .path("/payment_preferences/payment_failure_threshold")
                        .value("5")
                        .build()));
        subscriptions
                .billingPlans()
                .update()
                .withId("plan_id")
                .withBody(path)
                .retrieve()
                .toVoid();

        // Activate plan
        subscriptions
                .billingPlans()
                .activate()
                .withId("plan_id")
                .retrieve()
                .toVoid();

        // Deactivate plan
        subscriptions
                .billingPlans()
                .deactivate()
                .withId("plan_id")
                .retrieve()
                .toVoid();

        // Update pricing schemes
        List<UpdatePricingSchemeRequest> pricingSchemesList = List.of(
                new UpdatePricingSchemeRequest(1, PricingScheme.builder().build())
        );
        subscriptions
                .billingPlans()
                .updatePricingSchemes()
                .withId("plan_id")
                .withBody(new UpdatePricingSchemesListRequest(pricingSchemesList))
                .retrieve()
                .toVoid();


    }
}
