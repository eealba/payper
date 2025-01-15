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
import io.github.eealba.payper.subscriptions.v1.api.BillingSubscriptions;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.Subscription;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionReviseResponse;
import io.github.eealba.payper.subscriptions.v1.model.Transaction;
import io.github.eealba.payper.subscriptions.v1.model.TransactionsList;


/**
 * Implementation of the BillingSubscriptions API
 *
 * @author Edgar Alba
 * @version 1.0.0
 * @see BillingSubscriptions
 * @since 1.0.0
 */
class BillingSubscriptionsImpl implements BillingSubscriptions {


    private final Payper payper;

    BillingSubscriptionsImpl(Payper payper) {
        this.payper = payper;
    }

    @Override
    public CreateSubscription create() {
        var spec = PayperProvider.provider().createSpecBuilder(CreateSubscription.class, payper,
                        "/v1/billing/subscriptions", Subscription.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);

    }

    @Override
    public GetSubscription get() {
        var spec = PayperProvider.provider().createSpecBuilder(GetSubscription.class, payper,
                        "/v1/billing/subscriptions/{id}", Subscription.class, ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public UpdateSubscription update() {
        var spec = PayperProvider.provider().createSpecBuilder(UpdateSubscription.class, payper,
                        "/v1/billing/subscriptions/{id}",Subscription.class,ErrorDefault.class)
                .method(PayperRequest.Method.PATCH)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public ReviseSubscription revise() {
        var spec = PayperProvider.provider().createSpecBuilder(ReviseSubscription.class, payper,
                        "/v1/billing/subscriptions/{id}/revise", SubscriptionReviseResponse.class,
                        ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public SuspendSubscription suspend() {
        var spec = PayperProvider.provider().createSpecBuilder(SuspendSubscription.class, payper,
                        "/v1/billing/subscriptions/{id}/suspend", Void.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public CancelSubscription cancel() {
        var spec = PayperProvider.provider().createSpecBuilder(CancelSubscription.class, payper,
                        "/v1/billing/subscriptions/{id}/cancel", Void.class,ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public ActivateSubscription activate() {
        var spec = PayperProvider.provider().createSpecBuilder(ActivateSubscription.class, payper,
                        "/v1/billing/subscriptions/{id}/activate",Void.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public CaptureSubscription capture() {
        var spec = PayperProvider.provider().createSpecBuilder(CaptureSubscription.class, payper,
                        "/v1/billing/subscriptions/{id}/capture", Transaction.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public ListTransactions listTransactions() {
        var spec = PayperProvider.provider().createSpecBuilder(ListTransactions.class, payper,
                        "/v1/billing/subscriptions/{id}/transactions", TransactionsList.class, ErrorDefault.class)
                .alias("withStartTime", "query,start_time")
                .alias("withEndTime", "query,end_time")
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

}
