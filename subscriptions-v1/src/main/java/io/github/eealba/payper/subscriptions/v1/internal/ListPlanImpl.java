package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PlanCollection;

class ListPlanImpl extends RequestSpecImpl<BillingPlans.ListPlans, PlanCollection, ErrorDefault>
        implements BillingPlans.ListPlans {
    ListPlanImpl(Payper payper) {
        super(payper,"/v1/billing/plans",  PlanCollection.class, ErrorDefault.class);
    }


    @Override
    public BillingPlans.ListPlans withProductId(String productId) {
        requestBuilder.query("productId", productId);
        return this;
    }

    @Override
    public BillingPlans.ListPlans withPlanIds(String planIds) {
        requestBuilder.query("planIds", planIds);
        return this;
    }

    @Override
    public BillingPlans.ListPlans withPageSize(int pageSize) {
        requestBuilder.query("pageSize", String.valueOf(pageSize));
        return this;
    }

    @Override
    public BillingPlans.ListPlans withPage(int page) {
        requestBuilder.query("page", String.valueOf(page));
        return this;
    }

    @Override
    public BillingPlans.ListPlans withTotalRequired(boolean totalRequired) {
        requestBuilder.query("totalRequired", String.valueOf(totalRequired));
        return this;
    }

}
