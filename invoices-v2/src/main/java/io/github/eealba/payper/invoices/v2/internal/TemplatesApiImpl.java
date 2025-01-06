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
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.core.client.Spec;
import io.github.eealba.payper.invoices.v2.api.TemplatesApi;
import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.Template;
import io.github.eealba.payper.invoices.v2.model.Templates;

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
        var spec = new Spec<>(ListTemplates.class, payper, "v2/invoicing/templates",
                Templates.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().get(spec);
    }

    /**
     * Creates a new template.
     *
     * @return the create template
     */
    @Override
    public CreateTemplate create() {
        var spec = new Spec<>(CreateTemplate.class, payper, "v2/invoicing/templates",
                Template.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

    /**
     * Updates a template.
     *
     * @return the update template
     */
    @Override
    public UpdateTemplate update() {
        var spec = new Spec<>(UpdateTemplate.class, payper, "v2/invoicing/templates",
                Template.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().put(spec);
    }

    /**
     * Deletes a template.
     *
     * @return the delete template
     */
    @Override
    public DeleteTemplate delete() {
        var spec = new Spec<>(DeleteTemplate.class, payper, "v2/invoicing/templates",
                Void.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().delete(spec);
    }

    /**
     * Gets a template.
     *
     * @return the get template
     */
    @Override
    public GetTemplate get() {
        return null;
    }
}
