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
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.payments.v2.api.Refunds;
import io.github.eealba.payper.payments.v2.model.ErrorDefault;
import io.github.eealba.payper.payments.v2.model.Refund;

class RefundsImpl implements Refunds {
    private final Payper payer;

    RefundsImpl(Payper payper) {
        this.payer = payper;
    }

    /**
     * Gets a refund.
     * <p>
     * This method initializes a request to retrieve a specific refund from the PayPal Payments API
     * by its ID.
     *
     * @return the get refund request specification
     */
    @Override
    public GetRefund get() {
        var spec = PayperProvider.provider().createSpecBuilder(GetRefund.class, payer,
                "/v2/payments/refunds/{id}", Refund.class, ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

}
