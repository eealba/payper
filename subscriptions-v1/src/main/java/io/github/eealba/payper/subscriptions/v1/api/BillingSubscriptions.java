package io.github.eealba.payper.subscriptions.v1.api;

import io.github.eealba.payper.core.spec.RequestSpec;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.Subscription;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionActivateRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionCancelRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionCaptureRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionRequestPost;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionReviseRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionReviseResponse;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionSuspendRequest;
import io.github.eealba.payper.subscriptions.v1.model.Transaction;
import io.github.eealba.payper.subscriptions.v1.model.TransactionsList;

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

    interface CreateSubscription extends RequestSpec.PostRequestSpec<BillingSubscriptions.CreateSubscription,
            SubscriptionRequestPost, Subscription, ErrorDefault> {

    }
    interface GetSubscription extends RequestSpec.GetByIdRequestSpec<BillingSubscriptions.GetSubscription,
            Subscription, ErrorDefault> {
        GetSubscription withFields(String fields);
    }
    interface UpdateSubscription extends RequestSpec.PatchRequestSpec<BillingSubscriptions.UpdateSubscription,
            PatchRequest, Void, ErrorDefault> {
        UpdateSubscription withId(String id);
    }
    interface ReviseSubscription extends RequestSpec.PostRequestSpec<BillingSubscriptions.ReviseSubscription,
            SubscriptionReviseRequest, SubscriptionReviseResponse, ErrorDefault> {
        ReviseSubscription withId(String id);
    }
    interface SuspendSubscription extends RequestSpec.PostRequestSpec<BillingSubscriptions.SuspendSubscription,
            SubscriptionSuspendRequest, Void, ErrorDefault> {
        SuspendSubscription withId(String id);
    }
    interface CancelSubscription extends RequestSpec.PostRequestSpec<BillingSubscriptions.CancelSubscription,
            SubscriptionCancelRequest, Void, ErrorDefault> {
        CancelSubscription withId(String id);
    }

    interface ActivateSubscription extends RequestSpec.PostRequestSpec<BillingSubscriptions.ActivateSubscription,
            SubscriptionActivateRequest, Void, ErrorDefault> {
        ActivateSubscription withId(String id);
    }

    interface CaptureSubscription extends RequestSpec.PostRequestSpec<BillingSubscriptions.CaptureSubscription,
            SubscriptionCaptureRequest, Transaction, ErrorDefault> {
        CaptureSubscription withId(String id);
    }

    interface ListTransactions extends RequestSpec.GetByIdRequestSpec<BillingSubscriptions.ListTransactions,
            TransactionsList, ErrorDefault> {
        ListTransactions withStartTime(Instant startTime);
        ListTransactions withEndTime(Instant endTime);
    }

}
