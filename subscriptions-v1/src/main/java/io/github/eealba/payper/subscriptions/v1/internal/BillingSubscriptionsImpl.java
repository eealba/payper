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
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.subscriptions.v1.api.BillingSubscriptions;
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

class BillingSubscriptionsImpl implements BillingSubscriptions {


    private final Payper payper;

    BillingSubscriptionsImpl(Payper payper) {
        this.payper = payper;
    }

    @Override
    public CreateSubscription create() {
        return new CreateSubscriptionImpl(payper);
    }

    @Override
    public GetSubscription get() {
        return new GetSubscriptionImpl(payper);
    }

    @Override
    public UpdateSubscription update() {
        return new UpdateSubscriptionImpl(payper);
    }

    @Override
    public ReviseSubscription revise() {
        return new ReviseSubscriptionImpl(payper);
    }

    @Override
    public SuspendSubscription suspend() {
        return new SuspendSubscriptionImpl(payper);
    }

    @Override
    public CancelSubscription cancel() {
        return new CancelSubscriptionImpl(payper);
    }

    @Override
    public ActivateSubscription activate() {
        return new ActivateSubscriptionImpl(payper);
    }

    @Override
    public CaptureSubscription capture() {
        return new CaptureSubscriptionImpl(payper);
    }

    @Override
    public ListTransactions listTransactions() {
        return new ListTransactionsImpl(payper);
    }
    private static class CreateSubscriptionImpl extends
            RequestSpecImpl<CreateSubscription, SubscriptionRequestPost, Subscription, ErrorDefault> implements
            CreateSubscription {
        private CreateSubscriptionImpl(Payper payper) {
            super(payper, "/v1/billing/subscriptions", Subscription.class, ErrorDefault.class);
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }
    private static class GetSubscriptionImpl extends RequestSpecImpl<GetSubscription, Void, Subscription, ErrorDefault>
            implements GetSubscription {
        private GetSubscriptionImpl(Payper payper) {
            super(payper, "/v1/billing/subscriptions/{id}", Subscription.class, ErrorDefault.class);
        }

        @Override
        public GetSubscriptionImpl withFields(String fields) {
            query("fields", fields);
            return this;
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.GET;
        }
    }

    private static class UpdateSubscriptionImpl extends
            RequestSpecImpl<UpdateSubscription, PatchRequest, Void, ErrorDefault> implements
            UpdateSubscription {
        private UpdateSubscriptionImpl(Payper payper) {
            super(payper, "/v1/billing/subscriptions/{id}", Void.class, ErrorDefault.class);
        }

        @Override
        public PayperRequest.Method getMethod() {
            return PayperRequest.Method.PATCH;
        }
    }

    private static class ReviseSubscriptionImpl extends
            RequestSpecImpl<ReviseSubscription, SubscriptionReviseRequest, SubscriptionReviseResponse, ErrorDefault>
            implements   ReviseSubscription {
        private ReviseSubscriptionImpl(Payper payper) {
            super(payper, "/v1/billing/subscriptions/{id}/revise", SubscriptionReviseResponse.class,
                    ErrorDefault.class);
        }

        @Override
        public PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class SuspendSubscriptionImpl extends RequestSpecImpl<SuspendSubscription, Void, Void, ErrorDefault>
            implements SuspendSubscription {
        private SuspendSubscriptionImpl(Payper payper) {
            super(payper, "/v1/billing/subscriptions/{id}/suspend", Void.class, ErrorDefault.class);
        }

        @Override
        public PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class CancelSubscriptionImpl extends RequestSpecImpl<CancelSubscription, Void, Void, ErrorDefault>
            implements CancelSubscription {
        private CancelSubscriptionImpl(Payper payper) {
            super(payper, "/v1/billing/subscriptions/{id}/cancel", Void.class, ErrorDefault.class);
        }

        @Override
        public PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class ActivateSubscriptionImpl extends RequestSpecImpl<ActivateSubscription, Void, Void, ErrorDefault>
            implements ActivateSubscription {
        private ActivateSubscriptionImpl(Payper payper) {
            super(payper, "/v1/billing/subscriptions/{id}/activate", Void.class, ErrorDefault.class);
        }

        @Override
        public PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class CaptureSubscriptionImpl extends RequestSpecImpl<CaptureSubscription,
            SubscriptionCaptureRequest, Transaction, ErrorDefault>
            implements CaptureSubscription {
        private CaptureSubscriptionImpl(Payper payper) {
            super(payper, "/v1/billing/subscriptions/{id}/capture", Transaction.class, ErrorDefault.class);
        }

        @Override
        public PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class ListTransactionsImpl extends RequestSpecImpl<ListTransactions, Void, TransactionsList, ErrorDefault>
            implements ListTransactions {
        private ListTransactionsImpl(Payper payper) {
            super(payper, "/v1/billing/subscriptions/{id}/transactions", TransactionsList.class, ErrorDefault.class);
        }

        @Override
        public PayperRequest.Method getMethod() {
            return PayperRequest.Method.GET;
        }

        @Override
        public ListTransactions withStartTime(Instant startTime) {
            query("start_time", startTime.toString());
            return this;
        }

        @Override
        public ListTransactions withEndTime(Instant endTime) {
            query("end_time", endTime.toString());
            return this;
        }
    }

}
