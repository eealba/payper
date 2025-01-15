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

import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.payments.v2.model.ErrorDefault;
import io.github.eealba.payper.payments.v2.model.Refund;

/**
 * Interface for refunds.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface Refunds {

    /**
     * Gets a refund.
     * <p>
     * This method initializes a request to retrieve a specific refund from the PayPal Payments API
     * by its ID.
     *
     * @return the get refund request specification
     */
    GetRefund get();

    /**
     * Interface representing the get refund request specification.
     * <p>
     * This interface allows for specifying the refund ID.
     */
    interface GetRefund extends RequestSpec<Refund, ErrorDefault>,
        RequestSpec.IdSpec<GetRefund>{
    }
}
