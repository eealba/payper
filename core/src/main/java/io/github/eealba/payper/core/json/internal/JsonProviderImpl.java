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
package io.github.eealba.payper.core.json.internal;

import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.jasoner.JasonerConfig;
import io.github.eealba.jasoner.NamingStrategy;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.core.json.JsonProvider;

/**
 * The class JsonProviderImpl.
 * This class is an implementation of the JsonProvider abstract class.
 * It provides a method to create Json instances using the Jasoner library.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * JsonProvider provider = new JsonProviderImpl();
 * Json json = provider.createJson();
 * String jsonString = json.toJson(new MyObject());
 * MyObject obj = json.fromJson(jsonString, MyObject.class);
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public class JsonProviderImpl extends JsonProvider {
    private static final Json json = new JsonImpl(JasonerBuilder.create(JasonerConfig
            .builder()
            .namingStrategy(NamingStrategy.SNAKE_CASE)
            .build()));

    /**
     * Instantiates a new JsonProviderImpl.
     */
    public JsonProviderImpl() {
    }

    /**
     * Creates a new Json instance.
     *
     * @return a new Json instance
     */
    @Override
    public Json createJson() {
        return json;
    }
}