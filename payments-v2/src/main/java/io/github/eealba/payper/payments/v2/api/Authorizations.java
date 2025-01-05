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
package io.github.eealba.payper.payments.v2.api;

import io.github.eealba.payper.core.RequestSpec;
import io.github.eealba.payper.payments.v2.model.Authorization2;
import io.github.eealba.payper.payments.v2.model.Capture2;
import io.github.eealba.payper.payments.v2.model.CaptureRequest;
import io.github.eealba.payper.payments.v2.model.ErrorDefault;
import io.github.eealba.payper.payments.v2.model.ReauthorizeRequest;

/**
 * Interface for authorizations.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface Authorizations {

    /**
     * Gets an authorization.
     * <p>
     * This method initializes a request to retrieve a specific authorization from the PayPal Payments API
     * by its ID.
     *
     * @return the get authorization request specification
     */
    GetAuthorization get();

    /**
     * Captures an authorization.
     * <p>
     * This method initializes a request to capture an existing authorization in the PayPal Payments API.
     *
     * @return the capture authorization request specification
     */
    CaptureAuthorization capture();

    /**
     * Reauthorizes an authorization.
     * <p>
     * This method initializes a request to reauthorize an existing authorization in the PayPal Payments API.
     *
     * @return the reauthorize authorization request specification
     */
    ReauthorizeAuthorization reauthorize();

    /**
     * Voids an authorization.
     * <p>
     * This method initializes a request to void an existing authorization in the PayPal Payments API.
     *
     * @return the void authorization request specification
     */
    VoidAuthorization voidAuthorization();

    /**
     * Interface for get an authorization.
     * This interface defines the specifications for getting an authorization.
     */
    interface GetAuthorization extends RequestSpec<Authorization2, ErrorDefault>,
            RequestSpec.IdSpec<GetAuthorization> {
    }

    /**
     * Interface for capture an authorization.
     * This interface defines the specifications for capturing an authorization.
     * The request can be customized with various options such as setting the request body,
     * specifying the preferred return representation, and setting a PayPal request ID.
     */
    interface CaptureAuthorization extends RequestSpec<Capture2, ErrorDefault>,
            RequestSpec.IdSpec<CaptureAuthorization>,
            RequestSpec.PreferSpec<CaptureAuthorization>,
            RequestSpec.PaypalRequestIdSpec<CaptureAuthorization>,
            RequestSpec.BodySpec<CaptureAuthorization, CaptureRequest> {
    }

    /**
     * Interface for reauthorize an authorization.
     * This interface defines the specifications for reauthorizing an authorization.
     * The request can be customized with various options such as setting the request body,
     * specifying the preferred return representation, and setting a PayPal request ID.
     */
    interface ReauthorizeAuthorization extends RequestSpec<Authorization2, ErrorDefault>,
            RequestSpec.IdSpec<ReauthorizeAuthorization>,
            RequestSpec.PreferSpec<ReauthorizeAuthorization>,
            RequestSpec.PaypalRequestIdSpec<ReauthorizeAuthorization>,
            RequestSpec.BodySpec<ReauthorizeAuthorization, ReauthorizeRequest> {

    }

    /**
     * Interface for void an authorization.
     * This interface defines the specifications for voiding an authorization.
     * The request can be customized with various options such as setting the request body,
     * specifying the preferred return representation, and setting a PayPal request ID.
     */
    interface VoidAuthorization extends RequestSpec<Authorization2, ErrorDefault>,
            RequestSpec.IdSpec<VoidAuthorization>,
            RequestSpec.PreferSpec<VoidAuthorization>,
            RequestSpec.PayPalAuthAssertionSpec<VoidAuthorization> {

    }
}
