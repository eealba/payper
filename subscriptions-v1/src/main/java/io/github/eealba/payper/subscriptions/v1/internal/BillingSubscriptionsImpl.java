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

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.RequestSpecsFactory;
import io.github.eealba.payper.core.Spec;
import io.github.eealba.payper.subscriptions.v1.api.BillingSubscriptions;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.Subscription;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionReviseResponse;
import io.github.eealba.payper.subscriptions.v1.model.Transaction;
import io.github.eealba.payper.subscriptions.v1.model.TransactionsList;

import java.util.HashMap;

/**
 * Implementation of the BillingSubscriptions API
 * @see BillingSubscriptions
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Edgar Alba
 */
class BillingSubscriptionsImpl implements BillingSubscriptions {


    private final Payper payper;

    BillingSubscriptionsImpl(Payper payper) {
        this.payper = payper;
    }

    @Override
    public CreateSubscription create() {
        var spec = new Spec<>(CreateSubscription.class, payper, "/v1/billing/subscriptions",
                Subscription.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);

    }

    @Override
    public GetSubscription get() {
        var spec = new Spec<>(GetSubscription.class, payper, "/v1/billing/subscriptions/{id}",
                Subscription.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().get(spec);
    }

    @Override
    public UpdateSubscription update() {
        var spec = new Spec<>(UpdateSubscription.class, payper, "/v1/billing/subscriptions/{id}",
                Void.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().patch(spec);
    }

    @Override
    public ReviseSubscription revise() {
        var spec = new Spec<>(ReviseSubscription.class, payper, "/v1/billing/subscriptions/{id}/revise",
                SubscriptionReviseResponse.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public SuspendSubscription suspend() {
        var spec = new Spec<>(SuspendSubscription.class, payper, "/v1/billing/subscriptions/{id}/suspend",
                Void.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public CancelSubscription cancel() {
        var spec = new Spec<>(CancelSubscription.class, payper, "/v1/billing/subscriptions/{id}/cancel",
                Void.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public ActivateSubscription activate() {
        var spec = new Spec<>(ActivateSubscription.class, payper, "/v1/billing/subscriptions/{id}/activate",
                Void.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public CaptureSubscription capture() {
        var spec = new Spec<>(CaptureSubscription.class, payper, "/v1/billing/subscriptions/{id}/capture",
                Transaction.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public ListTransactions listTransactions() {
        var spec = new Spec<>(ListTransactions.class, payper, "/v1/billing/subscriptions/{id}/transactions",
                TransactionsList.class, ErrorDefault.class);
        var map = new HashMap<String, String>();
        map.put("withStartTime", "query,start_time");
        map.put("withEndTime", "query,end_time");
        return RequestSpecsFactory.getInstance().get(spec, map);
    }

}
