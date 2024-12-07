package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemesListRequest;

class UpdatePricingSchemesImpl extends BodyRequestSpecImpl<BillingPlans.UpdatePricingSchemes,
        UpdatePricingSchemesListRequest, Void, ErrorDefault>
        implements BillingPlans.UpdatePricingSchemes {
    UpdatePricingSchemesImpl(Payper payper) {
        super(payper, "/v1/billing/plans/{id}/update-pricing-schemes", Void.class, ErrorDefault.class);
    }

    @Override
    public BillingPlans.UpdatePricingSchemes withId(String id) {
        requestBuilder.pathParameter("id", id);
        return this;
    }
}
