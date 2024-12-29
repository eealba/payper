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

package io.github.eealba.payper.payments.v2.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.payments.v2.api.Authorizations;
import io.github.eealba.payper.payments.v2.model.Authorization2;
import io.github.eealba.payper.payments.v2.model.Capture2;
import io.github.eealba.payper.payments.v2.model.CaptureRequest;
import io.github.eealba.payper.payments.v2.model.ErrorDefault;
import io.github.eealba.payper.payments.v2.model.ReauthorizeRequest;

/**
 * Authorizations implementation.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
class AuthorizationsImpl implements Authorizations {
    private final Payper payer;
    AuthorizationsImpl(Payper payper) {
        this.payer = payper;
    }

    /**
     * Gets an authorization.
     * <p>
     * This method initializes a request to retrieve a specific authorization from the PayPal Payments API
     * by its ID.
     *
     * @return the get authorization request specification
     */
    @Override
    public GetAuthorization get() {
        return new GetAuthorizationImpl(payer);
    }

    /**
     * Captures an authorization.
     * <p>
     * This method initializes a request to capture an existing authorization in the PayPal Payments API.
     *
     * @return the capture authorization request specification
     */
    @Override
    public CaptureAuthorization capture() {
        return new CaptureAuthorizationImpl(payer);
    }

    /**
     * Reauthorizes an authorization.
     * <p>
     * This method initializes a request to reauthorize an existing authorization in the PayPal Payments API.
     *
     * @return the reauthorize authorization request specification
     */
    @Override
    public ReauthorizeAuthorization reauthorize() {
        return new ReauthorizeAuthorizationImpl(payer);
    }

    /**
     * Voids an authorization.
     * <p>
     * This method initializes a request to void an existing authorization in the PayPal Payments API.
     *
     * @return the void authorization request specification
     */
    @Override
    public VoidAuthorization voidAuthorization() {
        return new VoidAuthorizationImpl(payer);
    }

    private static class GetAuthorizationImpl extends RequestSpecImpl<GetAuthorization, Void, Authorization2, ErrorDefault>
            implements GetAuthorization {
        GetAuthorizationImpl(Payper payper) {
            super(payper, "/v2/payments/authorizations/{id}", Authorization2.class, ErrorDefault.class);
        }
        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.GET;
        }
    }

    private static class CaptureAuthorizationImpl extends RequestSpecImpl<CaptureAuthorization, CaptureRequest, Capture2, ErrorDefault>
            implements CaptureAuthorization {
        CaptureAuthorizationImpl(Payper payper) {
            super(payper, "/v2/payments/authorizations/{id}/capture", Capture2.class, ErrorDefault.class);
        }
        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class ReauthorizeAuthorizationImpl extends RequestSpecImpl<ReauthorizeAuthorization, ReauthorizeRequest, Authorization2, ErrorDefault>
            implements ReauthorizeAuthorization {
        ReauthorizeAuthorizationImpl(Payper payper) {
            super(payper, "/v2/payments/authorizations/{id}/reauthorize", Authorization2.class, ErrorDefault.class);
        }
        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class VoidAuthorizationImpl extends RequestSpecImpl<VoidAuthorization, Void, Authorization2, ErrorDefault>
            implements VoidAuthorization {
        VoidAuthorizationImpl(Payper payper) {
            super(payper, "/v2/payments/authorizations/{id}/void", Authorization2.class, ErrorDefault.class);
        }
        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }
}
