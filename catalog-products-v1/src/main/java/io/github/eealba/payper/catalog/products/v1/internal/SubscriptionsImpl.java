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
package io.github.eealba.payper.catalog.products.v1.internal;

import io.github.eealba.payper.catalog.products.v1.api.CatalogProducts;
import io.github.eealba.payper.catalog.products.v1.api.Products;
import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
/**
 * Implementation of the CatalogProducts API
 * @see CatalogProducts
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Edgar Alba
 */
class CatalogProductsImpl extends CatalogProducts {
    private final Products products;

    CatalogProductsImpl(PayperConfig config) {
        var payper = Payper.create(config);
        this.products = new ProductsImpl(payper);
    }

    /**
     * Returns the Products instance.
     *
     * @return the Products instance
     */
    @Override
    public Products products() {
        return products;
    }
}