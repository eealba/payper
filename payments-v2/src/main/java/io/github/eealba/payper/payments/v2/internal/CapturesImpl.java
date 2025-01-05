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
import io.github.eealba.payper.core.RequestSpecsFactory;
import io.github.eealba.payper.core.Spec;
import io.github.eealba.payper.payments.v2.api.Captures;
import io.github.eealba.payper.payments.v2.model.Capture2;
import io.github.eealba.payper.payments.v2.model.ErrorDefault;
import io.github.eealba.payper.payments.v2.model.Refund;

/**
 * Captures implementation.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
class CapturesImpl implements Captures {
    private final Payper payer;

    CapturesImpl(Payper payper) {
        this.payer = payper;
    }

    /**
     * Gets a capture.
     * <p>
     * This method initializes a request to retrieve a specific capture from the PayPal Payments API
     * by its ID.
     *
     * @return the get capture request specification
     */
    @Override
    public GetCapture get() {
        var spec = new Spec<>(GetCapture.class, payer, "/v2/payments/captures/{id}",
                Capture2.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().get(spec);
    }

    /**
     * Refunds a capture.
     * <p>
     * This method initializes a request to refund an existing capture in the PayPal Payments API.
     *
     * @return the refund capture request specification
     */
    @Override
    public RefundCapture refund() {
        var spec = new Spec<>(RefundCapture.class, payer, "/v2/payments/captures/{id}/refund",
                Refund.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

}
