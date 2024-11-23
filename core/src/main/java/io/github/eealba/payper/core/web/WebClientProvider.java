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
package io.github.eealba.payper.core.web;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperException;
import io.github.eealba.payper.core.Providers;

/**
 * The type Payper provider.
 * This class is used to create Payper objects.
 *
 * @since 1.0
 * @version 1.0
 *
 * @see WebClient
 * @see PayperConfig
 * @see PayperException
 * @see WebClientProvider
 *
 * @author Edgar Alba
 */
public abstract class WebClientProvider {
    /**
     * A constant representing the name of the default {@code PayperProvider}
     * implementation class.
     */
    private static final String DEFAULT_PROVIDER = "io.github.eealba.payper.web.internal.WebClientProviderImpl";

    /**
     * Instantiates a new Payper provider.
     */
    public WebClientProvider() {
    }

    /**
     * Creates a Payper provider object. If there are no available service providers,
     * this method returns the default service provider.
     *
     * @return a Payper provider
     */
    public static WebClientProvider provider() {
        return Providers.getProvider(WebClientProvider.class, DEFAULT_PROVIDER);
    }


    /**
     * Creates a Payper object.
     *
     * @param config the configuration
     * @return a Payper object
     */
    public abstract WebClient createWebClient(WebClientConfig config);
}
