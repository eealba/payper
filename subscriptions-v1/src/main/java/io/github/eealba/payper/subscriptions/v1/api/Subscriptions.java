package io.github.eealba.payper.subscriptions.v1.api;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.web.Method;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanCollection;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemesListRequest;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface Subscriptions {
    static Subscriptions create() {
        return create(PayperConfig.builder().build());
    }
    static Subscriptions create(PayperConfig config) {
        return SubscriptionsProvider.provider().createSubscriptions(config);
    }

    BillingPlans billingPlans();

    interface BillingPlans {
        CreatePlan create();

        ListPlans list();

        GetPlan get();

        UpdatePlan update();

        ActivatePlan activate();

        DeactivatePlan deactivate();

        UpdatePricingSchemes updatePricingSchemes();
    }

    interface CreatePlan extends PostRequestSpec<CreatePlan, PlanRequestPOST, Plan, ErrorDefault> {

    }

    interface ListPlans extends RequestSpec<ListPlans, PlanCollection, ErrorDefault> {
        ListPlans withProductId(String productId);

        ListPlans withPlanIds(String planIds);

        ListPlans withPageSize(int pageSize);

        ListPlans withPage(int page);

        ListPlans withTotalRequired(boolean totalRequired);
    }

    interface GetPlan extends GetByIdRequestSpec<GetPlan, Plan, ErrorDefault> {
        GetPlan withFields(String fields);
    }

    interface UpdatePlan extends PatchRequestSpec<UpdatePlan, PatchRequest, Void, ErrorDefault> {
        UpdatePlan withId(String id);
    }

    interface ActivatePlan extends PostRequestSpec<ActivatePlan, Void, Void, ErrorDefault> {
        ActivatePlan withId(String id);
    }

    interface DeactivatePlan extends PostRequestSpec<DeactivatePlan, Void, Void, ErrorDefault> {
        DeactivatePlan withId(String id);
    }

    interface UpdatePricingSchemes extends PostRequestSpec<UpdatePricingSchemes, UpdatePricingSchemesListRequest,
            Void, ErrorDefault> {
        UpdatePricingSchemes withId(String id);
    }

    interface RequestSpec<T, R1, R2> {
        T withPrefer(String prefer);
        T withPaypalRequestId(String paypalRequestId);
        ResponseSpec<R1, R2> retrieve();
        default Method getMethod() {
            return Method.GET;
        };
        Class<R1> getEntityClass();
        Class<R2> getErrorEntityClass();
    }

    interface BodyRequestSpec<T extends BodyRequestSpec<T, T2, T3, T4>, T2, T3, T4> extends RequestSpec<T, T3, T4> {
        T withBody(T2 body);

    }

    interface PostRequestSpec<T extends PostRequestSpec<T, T2, T3, T4>, T2, T3, T4> extends BodyRequestSpec<T, T2, T3, T4> {
        default Method getMethod() {
            return Method.POST;
        }
    }

    interface GetByIdRequestSpec<T, T2, T3> extends RequestSpec<T, T2, T3> {
        T withId(String id);
    }

    interface PatchRequestSpec<T extends PatchRequestSpec<T, T2, T3, T4>, T2, T3, T4> extends BodyRequestSpec<T, T2, T3, T4> {
        default Method getMethod() {
            return Method.PATCH;
        }
    }

    interface ResponseSpec<T, T2> {
        Response<T, T2> toResponse();

        CompletableFuture<Response<T, T2>> toFuture();

        default T toEntity() {
            return toResponse().toEntity();
        }
        default Optional<T> toOptionalEntity() {
            return toResponse().toOptionalEntity();
        }

        default void toVoid() {
            toResponse().toVoid();

        }

        default void consume(Consumer<Response<T, T2>> consumer) {
            consumer.accept(toResponse());

        }

        default void consumeAsync(Consumer<Response<T, T2>> consumer) {
            toFuture().thenAccept(consumer);
        }


    }
    interface Response<T,T2> {
        int statusCode();
        T toEntity();
        T2 toErrorEntity();
        void toVoid();
        Optional<T> toOptionalEntity();
        Optional<T2> toOptionalErrorEntity();
    }
}
