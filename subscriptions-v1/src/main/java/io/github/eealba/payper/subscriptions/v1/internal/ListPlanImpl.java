package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PlanCollection;

public class ListPlanImpl extends RequestSpecImpl<Subscriptions.ListPlans, PlanCollection, ErrorDefault>
        implements Subscriptions.ListPlans {
    public ListPlanImpl(Payper payper) {
        super(payper,"/v1/billing/plans",  PlanCollection.class, ErrorDefault.class);
    }


    @Override
    public Subscriptions.ListPlans withProductId(String productId) {
        requestBuilder.query("productId", productId);
        return this;
    }

    @Override
    public Subscriptions.ListPlans withPlanIds(String planIds) {
        requestBuilder.query("planIds", planIds);
        return this;
    }

    @Override
    public Subscriptions.ListPlans withPageSize(int pageSize) {
        requestBuilder.query("pageSize", String.valueOf(pageSize));
        return this;
    }

    @Override
    public Subscriptions.ListPlans withPage(int page) {
        requestBuilder.query("page", String.valueOf(page));
        return this;
    }

    @Override
    public Subscriptions.ListPlans withTotalRequired(boolean totalRequired) {
        requestBuilder.query("totalRequired", String.valueOf(totalRequired));
        return this;
    }

}
