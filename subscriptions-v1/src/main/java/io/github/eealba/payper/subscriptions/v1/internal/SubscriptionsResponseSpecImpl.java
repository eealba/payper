package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.PayperResponse;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;

import java.util.concurrent.CompletableFuture;

class SubscriptionsResponseSpecImpl<R1, R2> implements Subscriptions.ResponseSpec<R1, R2> {
    private final Payper payper;
    private final Class<R1> entityClass;
    private final Class<R2> errorEntityClass;
    private final PayperRequest request;

    SubscriptionsResponseSpecImpl(Payper payper,
                                         PayperRequest request,
                                         Class<R1> entityClass,
                                         Class<R2> errorEntityClass) {
        this.payper = payper;
        this.request = request;
        this.entityClass = entityClass;
        this.errorEntityClass = errorEntityClass;

    }
    @Override
    public Subscriptions.Response<R1,R2> toResponse() {
        return new SubscriptionsResponseImpl();
    }

    @Override
    public CompletableFuture<Subscriptions.Response<R1,R2>> toFuture() {
        return payper.send(request,
                PayperResponse.BodyHandlers.ofClass(entityClass),
                PayperResponse.BodyHandlers.ofClass(errorEntityClass)).toFuture()
                .thenApply(SubscriptionsResponseImpl::new);
    }

    class SubscriptionsResponseImpl implements Subscriptions.Response<R1, R2> {
        PayperResponse<R1, R2> payperResponse;
        SubscriptionsResponseImpl() {
        }
        SubscriptionsResponseImpl(PayperResponse<R1, R2> payperResponse) {
            this.payperResponse = payperResponse;
        }
        private void call(){
            if(payperResponse == null) {
                payperResponse = payper.send(request,
                        PayperResponse.BodyHandlers.ofClass(entityClass),
                        PayperResponse.BodyHandlers.ofClass(errorEntityClass)).toResponse();
            }
        }
        @Override
        public int statusCode() {
            call();
            return payperResponse.statusCode();
        }

        @Override
        public R1 toEntity() {
            call();
            return payperResponse.toEntity();
        }

        @Override
        public R2 toErrorEntity() {
            call();
            return payperResponse.toErrorEntity();
        }

        @Override
        public void toVoid() {
            call();
            payperResponse.toVoid();

        }

    }
}
