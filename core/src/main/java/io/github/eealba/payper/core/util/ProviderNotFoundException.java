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
package io.github.eealba.payper.core.util;

import java.io.Serial;

/**
 * The type ProviderNotFoundException.
 * This exception is thrown when a provider cannot be found.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * try {
 *     JsonProvider provider = Providers.getProvider(JsonProvider.class, "some.provider.ClassName");
 * } catch (ProviderNotFoundException e) {
 *     e.printStackTrace();
 * }
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public class ProviderNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new ProviderNotFoundException.
     *
     * @param providerName the name of the provider that was not found
     */
    public ProviderNotFoundException(String providerName) {
        super("Provider for " + providerName + " not found");
    }

    /**
     * Instantiates a new ProviderNotFoundException.
     *
     * @param providerName the name of the provider that was not found
     * @param x the exception that caused this exception to be thrown
     */
    public ProviderNotFoundException(String providerName, Exception x) {
        super("Provider for " + providerName + " not found", x);
    }
}