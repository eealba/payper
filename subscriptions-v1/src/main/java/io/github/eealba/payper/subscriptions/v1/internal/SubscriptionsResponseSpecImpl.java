package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.PayperResponse;
import io.github.eealba.payper.subscriptions.v1.api.ResponseSpec;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

class SubscriptionsResponseSpecImpl<R1, R2> implements ResponseSpec<R1, R2> {
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
    public Response<R1,R2> toResponse() {
        return new SubscriptionsResponseImpl();
    }

    @Override
    public CompletableFuture<Response<R1,R2>> toFuture() {
        return payper.send(request,
                PayperResponse.BodyHandlers.ofClass(entityClass),
                PayperResponse.BodyHandlers.ofClass(errorEntityClass)).toFuture()
                .thenApply(SubscriptionsResponseImpl::new);
    }

    class SubscriptionsResponseImpl implements Response<R1, R2> {
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

        @Override
        public Optional<R1> toOptionalEntity() {
            call();
            return payperResponse.toOptionalEntity();
        }

        @Override
        public Optional<R2> toOptionalErrorEntity() {
            call();
            return payperResponse.toOptionalErrorEntity();
        }

    }
}
