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
package io.github.eealba.payper.core.spec;

import io.github.eealba.payper.core.util.Providers;

/**
 * The type SpecProvider.
 * This class is used to create Spec objects and provides utility methods to manage them.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * SpecProvider provider = SpecProvider.provider();
 * SpecConfig config = new SpecConfig();
 * Spec payper = provider.createSpec(config);
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @see SpecProvider
 * @author Edgar Alba
 */
public abstract class SpecProvider {
    /**
     * A constant representing the name of the default {@code SpecProvider}
     * implementation class.
     */
    private static final String DEFAULT_PROVIDER = "io.github.eealba.payper.core.spec.internal.SpecProviderImpl";

    /**
     * Instantiates a new Spec provider.
     */
    public SpecProvider() {
    }

    /**
     * Creates a Spec provider object. If there are no available service providers,
     * this method returns the default service provider.
     *
     * @return a Spec provider
     */
    public static SpecProvider provider() {
        return Providers.getProvider(SpecProvider.class, DEFAULT_PROVIDER);
    }

    
    public abstract RequestSpecsFactory createRequestSpecsFactory();
}