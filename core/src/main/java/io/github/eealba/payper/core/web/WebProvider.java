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
import io.github.eealba.payper.core.util.Providers;

/**
 * The type WebProvider.
 * This class is used to create WebClient objects.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 *
 * @see WebClient
 * @see PayperConfig
 * @see PayperException
 * @see WebProvider
 * @see Providers
 */
public abstract class WebProvider {
    /**
     * A constant representing the name of the default {@code WebProvider}
     * implementation class.
     */
    private static final String DEFAULT_PROVIDER = "io.github.eealba.payper.core.web.internal.WebProviderImpl";

    /**
     * Instantiates a new WebProvider.
     */
    public WebProvider() {
    }

    /**
     * Creates a WebProvider object. If there are no available service providers,
     * this method returns the default service provider.
     *
     * @return a WebProvider
     */
    public static WebProvider provider() {
        return Providers.getProvider(WebProvider.class, DEFAULT_PROVIDER);
    }

    /**
     * Creates a WebClient object.
     *
     * @param config the configuration
     * @return a WebClient object
     */
    public abstract WebClient createWebClient(WebClientConfig config);

    /**
     * Creates a Request.Builder object.
     *
     * @return a Request.Builder object
     */
    public abstract Request.Builder createRequestBuilder();
}