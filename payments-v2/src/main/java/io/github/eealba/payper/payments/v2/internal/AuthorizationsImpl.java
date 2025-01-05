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

import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.core.client.Spec;
import io.github.eealba.payper.payments.v2.api.Authorizations;
import io.github.eealba.payper.payments.v2.model.Authorization2;
import io.github.eealba.payper.payments.v2.model.Capture2;
import io.github.eealba.payper.payments.v2.model.ErrorDefault;

/**
 * Authorizations implementation.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
class AuthorizationsImpl implements Authorizations {
    private final Payper payper;
    AuthorizationsImpl(Payper payper) {
        this.payper = payper;
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
        var spec = new Spec<>(GetAuthorization.class, payper, "/v2/payments/authorizations/{id}",
                Authorization2.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().get(spec);
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
        var spec = new Spec<>(CaptureAuthorization.class, payper, "/v2/payments/authorizations/{id}/capture",
                Capture2.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
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
        var spec = new Spec<>(ReauthorizeAuthorization.class, payper, "/v2/payments/authorizations/{id}/reauthorize",
                Authorization2.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
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
        var spec = new Spec<>(VoidAuthorization.class, payper, "/v2/payments/authorizations/{id}/void",
                Authorization2.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

}
