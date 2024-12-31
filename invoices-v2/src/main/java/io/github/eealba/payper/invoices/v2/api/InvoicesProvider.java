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
package io.github.eealba.payper.invoices.v2.api;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.util.Providers;

/**
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class InvoicesProvider {
    private static final String DEFAULT = "io.github.eealba.payper.invoices.v2.internal.InvoicesProviderImpl";

    /**
     * Constructs a new InvoicesProvider.
     */
    public InvoicesProvider() {
    }

    /**
     * Returns the default InvoicesProvider instance.
     *
     * @return the default InvoicesProvider instance
     */
    public static InvoicesProvider provider() {
        return Providers.getProvider(InvoicesProvider.class, DEFAULT);
    }

    /**
     * Creates a new InvoicesApiClient instance using the specified configuration.
     *
     * @param config the configuration to be used
     * @return a new InvoicesApiClient instance
     */
    public abstract InvoicesApiClient create(PayperConfig config);

}
