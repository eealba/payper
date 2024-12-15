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
package io.github.eealba.payper.catalog.products.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.PayperResponse;
import io.github.eealba.payper.core.spec.ResponseSpec;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
/**
 * Implementation of the ResponseSpec interface.
 * This class represents a response specification with various configurations.
 *
 * @param <R1> the type of the response entity
 * @param <R2> the type of the error entity
 * @since 1.0
 * @version 1.0
 * author Edgar Alba
 */
class ResponseSpecImpl<R1, R2> implements ResponseSpec<R1, R2> {
    private final Payper payper;
    private final Class<R1> entityClass;
    private final Class<R2> errorEntityClass;
    private final PayperRequest request;

    ResponseSpecImpl(Payper payper,
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

    private class SubscriptionsResponseImpl implements Response<R1, R2> {
        PayperResponse<R1, R2> payperResponse;
        private SubscriptionsResponseImpl() {
        }
        private SubscriptionsResponseImpl(PayperResponse<R1, R2> payperResponse) {
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
