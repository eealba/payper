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
package io.github.eealba.payper.invoices.v2.api;

import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.Template;
import io.github.eealba.payper.invoices.v2.model.Templates;

/**
 * Interface representing a template.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface TemplatesApi {
    /**
     * Returns the list of templates.
     *
     * @return the list of templates
     */
    ListTemplates list();
    /**
     * Creates a new template.
     *
     * @return the create template
     */
    CreateTemplate create();

    /**
     * Updates a template.
     *
     * @return the update template
     */
    UpdateTemplate update();

    /**
     * Deletes a template.
     *
     * @return the delete template
     */
    DeleteTemplate delete();

    /**
     * Gets a template.
     *
     * @return the get template
     */
    GetTemplate get();

    /**
     * Interface representing a list of templates.
     *
     * @since 1.0
     * @version 1.0
     */
    interface ListTemplates extends RequestSpec<Templates, ErrorDefault>,
            RequestSpec.PaginationSpec<ListTemplates>,
            RequestSpec.FieldsSpec<ListTemplates>{

    }

    /**
     * Interface representing a create template.
     *
     * @since 1.0
     * @version 1.0
     */
    interface CreateTemplate extends RequestSpec<Template, ErrorDefault>,
            RequestSpec.BodySpec<CreateTemplate, Template>,
            RequestSpec.PreferSpec<CreateTemplate> {
    }

    /**
     * Interface representing an update template.
     *
     * @since 1.0
     * @version 1.0
     */
    interface UpdateTemplate extends RequestSpec<Template, ErrorDefault>,
            RequestSpec.IdSpec<UpdateTemplate>,
            RequestSpec.BodySpec<UpdateTemplate, Template> {
    }

    /**
     * Interface representing a delete template.
     *
     * @since 1.0
     * @version 1.0
     */
    interface DeleteTemplate extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.IdSpec<DeleteTemplate> {
    }

    /**
     * Interface representing a get template.
     *
     * @since 1.0
     * @version 1.0
     */
    interface GetTemplate extends RequestSpec<Template, ErrorDefault>,
            RequestSpec.IdSpec<GetTemplate> {
    }
}
