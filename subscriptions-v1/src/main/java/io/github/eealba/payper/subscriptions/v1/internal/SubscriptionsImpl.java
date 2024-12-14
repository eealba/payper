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
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.api.BillingSubscriptions;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;

class SubscriptionsImpl extends Subscriptions {
    private final BillingPlans billingPlans;
    private final BillingSubscriptions billingSubscriptions;

    SubscriptionsImpl(PayperConfig config) {
        var payper = Payper.create(config);
        this.billingPlans = new BillingPlansImpl(payper);
        this.billingSubscriptions = new BillingSubscriptionsImpl(payper);
    }

    @Override
    public BillingPlans billingPlans() {
        return billingPlans;
    }

    @Override
    public BillingSubscriptions billingSubscriptions() {
        return billingSubscriptions;
    }

}