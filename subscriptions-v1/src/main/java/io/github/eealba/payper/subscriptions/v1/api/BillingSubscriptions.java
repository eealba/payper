package io.github.eealba.payper.subscriptions.v1.api;

import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.Subscription;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionCaptureRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionRequestPost;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionReviseRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionReviseResponse;
import io.github.eealba.payper.subscriptions.v1.model.Transaction;
import io.github.eealba.payper.subscriptions.v1.model.TransactionsList;
import io.github.eealba.payper.subscriptions.v1.spec.RequestSpec;

import java.time.Instant;

public interface BillingSubscriptions {
    CreateSubscription create();
    GetSubscription get();
    UpdateSubscription update();
    ReviseSubscription revise();
    SuspendSubscription suspend();
    CancelSubscription cancel();
    ActivateSubscription activate();
    CaptureSubscription capture();
    ListTransactions listTransactions();


    interface CreateSubscription extends RequestSpec<Subscription, ErrorDefault>,
                RequestSpec.BodySpec<CreateSubscription, SubscriptionRequestPost>,
                RequestSpec.PreferSpec<CreateSubscription>,
                RequestSpec.PaypalRequestIdSpec<CreateSubscription> {

    }
    interface GetSubscription extends RequestSpec<Subscription, ErrorDefault>, RequestSpec.IdSpec<GetSubscription> {
        GetSubscription withFields(String fields);
    }
    interface UpdateSubscription extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.BodySpec<UpdateSubscription, PatchRequest>,
            RequestSpec.IdSpec<UpdateSubscription> {
    }
    interface ReviseSubscription extends RequestSpec<SubscriptionReviseResponse, ErrorDefault>,
            RequestSpec.BodySpec<ReviseSubscription, SubscriptionReviseRequest>,
            RequestSpec.IdSpec<ReviseSubscription> {
    }
    interface SuspendSubscription extends RequestSpec<Void, ErrorDefault>, RequestSpec.IdSpec<SuspendSubscription> {
    }
    interface CancelSubscription extends RequestSpec<Void, ErrorDefault>, RequestSpec.IdSpec<CancelSubscription> {
    }
    interface ActivateSubscription extends RequestSpec<Void, ErrorDefault>, RequestSpec.IdSpec<ActivateSubscription> {
    }

    interface CaptureSubscription extends RequestSpec<Transaction, ErrorDefault>,
            RequestSpec.BodySpec<CaptureSubscription, SubscriptionCaptureRequest>,
            RequestSpec.IdSpec<CaptureSubscription>,
        RequestSpec.PreferSpec<CaptureSubscription>,
                RequestSpec.PaypalRequestIdSpec<CaptureSubscription> {
    }


    interface ListTransactions extends RequestSpec<TransactionsList, ErrorDefault>,
        RequestSpec.IdSpec<ListTransactions>{
        ListTransactions withStartTime(Instant startTime);
        ListTransactions withEndTime(Instant endTime);
    }

}
