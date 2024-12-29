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
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.payments.v2.api.Authorizations;
import io.github.eealba.payper.payments.v2.api.Captures;
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;
import io.github.eealba.payper.payments.v2.api.Refunds;

/**
 * Payments API client implementation.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
class PaymentsApiClientImpl extends PaymentsApiClient {
    private final Authorizations authorizations;
    private final Captures captures;
    private final Refunds refunds;

    PaymentsApiClientImpl(PayperConfig config) {
        var payper = Payper.create(config);
        this.authorizations = new AuthorizationsImpl(payper);
        this.captures = new CapturesImpl(payper);
        this.refunds = new RefundsImpl(payper);

    }

    /**
     * Returns the Authorizations API interface.
     *
     * @return the Authorizations API interface
     */
    @Override
    public Authorizations authorizations() {
        return authorizations;
    }

    /**
     * Returns the Captures API interface.
     *
     * @return the Captures API interface
     */
    @Override
    public Captures captures() {
        return null;
    }

    /**
     * Returns the Refunds API interface.
     *
     * @return the Refunds API interface
     */
    @Override
    public Refunds refunds() {
        return null;
    }
}
