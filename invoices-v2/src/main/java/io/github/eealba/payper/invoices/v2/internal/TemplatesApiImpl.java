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
package io.github.eealba.payper.invoices.v2.internal;

import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.client.PayperRequest;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.invoices.v2.api.TemplatesApi;
import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.Template;
import io.github.eealba.payper.invoices.v2.model.Templates;
/**
 * Implementation of the Templates API.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
class TemplatesApiImpl implements TemplatesApi {
    private final Payper payper;

    TemplatesApiImpl(Payper payper) {
        this.payper = payper;
    }

    /**
     * Returns the list of templates.
     *
     * @return the list of templates
     */
    @Override
    public ListTemplates list() {
        var spec = PayperProvider.provider().createSpecBuilder(ListTemplates.class, payper,
                        "/v2/invoicing/templates",Templates.class, ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Creates a new template.
     *
     * @return the create template
     */
    @Override
    public CreateTemplate create() {
        var spec = PayperProvider.provider().createSpecBuilder(CreateTemplate.class, payper,
                        "/v2/invoicing/templates", Template.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Updates a template.
     *
     * @return the update template
     */
    @Override
    public UpdateTemplate update() {
        var spec = PayperProvider.provider().createSpecBuilder(UpdateTemplate.class, payper,
                        "/v2/invoicing/templates/{id}",Template.class, ErrorDefault.class)
                .method(PayperRequest.Method.PUT)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Deletes a template.
     *
     * @return the delete template
     */
    @Override
    public DeleteTemplate delete() {
        var spec = PayperProvider.provider().createSpecBuilder(DeleteTemplate.class, payper,
                        "/v2/invoicing/templates/{id}", Void.class, ErrorDefault.class)
                .method(PayperRequest.Method.DELETE)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    /**
     * Gets a template.
     *
     * @return the get template
     */
    @Override
    public GetTemplate get() {
        var spec = PayperProvider.provider().createSpecBuilder(GetTemplate.class, payper,
                        "/v2/invoicing/templates/{id}", Template.class, ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }
}
