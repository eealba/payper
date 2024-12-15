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
package io.github.eealba.payper.catalog.products.v1.api;

import io.github.eealba.payper.core.PayperConfig;
/**
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class CatalogProducts {

    /**
     * Creates a new CatalogProducts instance using the default configuration.
     *
     * @return a new CatalogProducts instance
     */
    public static CatalogProducts create() {
        return create(PayperConfig.builder().build());
    }

    /**
     * Creates a new CatalogProducts instance using the specified configuration.
     *
     * @param config the configuration to be used
     * @return a new CatalogProducts instance
     */
    public static CatalogProducts create(PayperConfig config) {
        return CatalogProductsProvider.provider().create(config);
    }

    /**
     * Returns the Products instance.
     *
     * @return the Products instance
     */
    public abstract Products products();
}
