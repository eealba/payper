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
package io.github.eealba.payper.core.web.internal;

import io.github.eealba.payper.core.web.Request;
import io.github.eealba.payper.core.web.WebClient;
import io.github.eealba.payper.core.web.WebClientConfig;
import io.github.eealba.payper.core.web.WebProvider;

/**
 * The class WebProviderImpl.
 * This class is an implementation of the WebProvider abstract class.
 * It provides methods to create WebClient and Request.Builder instances.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * WebProvider provider = new WebProviderImpl();
 * WebClientConfig config = new WebClientConfig();
 * WebClient client = provider.createWebClient(config);
 * Request.Builder requestBuilder = provider.createRequestBuilder();
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public class WebProviderImpl extends WebProvider {

    /**
     * Creates a WebClient object with the given configuration.
     *
     * @param config the configuration
     * @return a WebClient object
     */
    @Override
    public WebClient createWebClient(WebClientConfig config) {
        return new WebClientImpl(config);
    }

    /**
     * Creates a new Request.Builder instance.
     *
     * @return a Request.Builder instance
     */
    @Override
    public Request.Builder createRequestBuilder() {
        return new RequestImpl.RequestBuilder();
    }
}