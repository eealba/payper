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
package io.github.eealba.payper.core.json;

import io.github.eealba.payper.core.util.Providers;

/**
 * The abstract class JsonProvider.
 * This class provides a mechanism to obtain a JsonProvider implementation.
 * It is used to create Json instances with a given configuration.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * JsonProvider provider = JsonProvider.provider();
 * Json json = provider.createJson();
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class JsonProvider {

    private static final String DEFAULT_PROVIDER = "io.github.eealba.payper.core.json.internal.JsonProviderImpl";

    /**
     * Instantiates a new JsonProvider.
     */
    public JsonProvider() {
    }

    /**
     * Gets the default JsonProvider implementation.
     *
     * @return the JsonProvider implementation
     */
    public static JsonProvider provider() {
        return Providers.getProvider(JsonProvider.class, DEFAULT_PROVIDER);
    }

    /**
     * Creates a new Json instance.
     *
     * @return a new Json instance
     */
    public abstract Json createJson();

}