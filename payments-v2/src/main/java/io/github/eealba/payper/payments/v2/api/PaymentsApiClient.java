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

import io.github.eealba.payper.core.client.PayperConfig;

/**
 * Abstract class for payments API client.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class PaymentsApiClient {

    /**
     * Creates a new PaymentsApiClient instance using the default configuration.
     * <p>
     * This method initializes the client with the default settings and credentials
     * required for communicating with the PayPal Payments API.
     *
     * @return a new PaymentsApiClient instance
     */
    public static PaymentsApiClient create() {
        return create(PayperConfig.builder().build());
    }

    /**
     * Creates a new PaymentsApiClient instance using the specified configuration.
     * <p>
     * This method allows for custom configuration of the client, including setting
     * specific credentials and other settings required for authenticating and
     * communicating with the PayPal Payments API.
     *
     * @param config the configuration to be used
     * @return a new PaymentsApiClient instance
     */
    public static PaymentsApiClient create(PayperConfig config) {
        return PaymentsProvider.provider().create(config);
    }
    /**
     * Returns the Authorizations API interface.
     *
     * @return the Authorizations API interface
     */
    public abstract Authorizations authorizations();

    /**
     * Returns the Captures API interface.
     *
     * @return the Captures API interface
     */
    public abstract Captures captures();

    /**
     * Returns the Refunds API interface.
     *
     * @return the Refunds API interface
     */
    public abstract Refunds refunds();
}
