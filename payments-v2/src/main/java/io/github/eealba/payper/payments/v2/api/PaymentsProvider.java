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
import io.github.eealba.payper.core.util.Providers;

/**
 * Abstract class for payments provider.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class PaymentsProvider {
    private static final String DEFAULT = "io.github.eealba.payper.payments.v2.internal.PaymentsProviderImpl";

    /**
     * Constructs a new PaymentsProvider.
     */
    public PaymentsProvider() {

    }

    /**
     * Returns the default PaymentsProvider instance.
     *
     * @return the default PaymentsProvider instance
     */
    public static PaymentsProvider provider() {
        return Providers.getProvider(PaymentsProvider.class, DEFAULT);
    }

    /**
     * Creates a new PaymentsApiClient instance using the specified configuration.
     *
     * @param config the configuration to be used
     * @return a new PaymentsApiClient instance
     */
    public abstract PaymentsApiClient create(PayperConfig config);
}
