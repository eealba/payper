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

import io.github.eealba.payper.core.spec.RequestSpec;
import io.github.eealba.payper.payments.v2.model.Capture2;
import io.github.eealba.payper.payments.v2.model.ErrorDefault;
import io.github.eealba.payper.payments.v2.model.Refund;
import io.github.eealba.payper.payments.v2.model.RefundRequest;

/**
 * Interface for captures.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface Captures {

    /**
     * Gets a capture.
     * <p>
     * This method initializes a request to retrieve a specific capture from the PayPal Payments API
     * by its ID.
     *
     * @return the get capture request specification
     */
    GetCapture get();

    /**
     * Refunds a capture.
     * <p>
     * This method initializes a request to refund an existing capture in the PayPal Payments API.
     *
     * @return the refund capture request specification
     */
    RefundCapture refund();

    /**
     * Interface representing the get capture request specification.
     * <p>
     * This interface allows for specifying the capture ID.
     */
    interface GetCapture extends RequestSpec<Capture2, ErrorDefault>,
        RequestSpec.IdSpec<GetCapture>{
    }

    /**
     * Interface representing the refund capture request specification.
     * <p>
     * This interface allows for specifying the capture ID.
     */
    interface RefundCapture extends RequestSpec<Refund, ErrorDefault>,
        RequestSpec.IdSpec<RefundCapture>,
        RequestSpec.PreferSpec<RefundCapture>,
        RequestSpec.PaypalRequestIdSpec<RefundCapture>,
        RequestSpec.PayPalAuthAssertionSpec<RefundCapture>,
        RequestSpec.BodySpec<RefundCapture, RefundRequest>{

    }
}
