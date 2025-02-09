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

import io.github.eealba.payper.core.client.PayperConfig;

/**
 * Interface representing the Invoicing API client.
 * <p>
 * Provides access to the invoices, templates, generate next invoice number, and search invoices endpoints.
 * </p>
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class InvoicingApiClient {

    /**
     * Provides access to the invoices API.
     * @return an instance of {@link InvoicesApi}
     */
    public abstract InvoicesApi invoices();

    /**
     * Provides access to the templates API.
     * @return an instance of {@link TemplatesApi}
     */
    public abstract TemplatesApi templates();

    /**
     * Provides access to the generate next invoice number API.
     * @return an instance of {@link GenerateNextInvoiceNumber}
     */
    public abstract GenerateNextInvoiceNumber generateNextInvoiceNumber();

    /**
     * Provides access to the search invoices API.
     * @return an instance of {@link SearchInvoices}
     */
    public abstract SearchInvoices searchInvoices();

    /**
     * Creates a new instance of the Invoicing API client with the default configuration.
     * @return a new instance of {@link InvoicingApiClient}
     */
    public static InvoicingApiClient create() {
        return create(PayperConfig.builder().build());
    }

    /**
     * Creates a new instance of the Invoicing API client with the specified configuration.
     * @param build the configuration to use
     * @return a new instance of {@link InvoicingApiClient}
     */
    public static InvoicingApiClient create(PayperConfig build) {
        return InvoicingProvider.provider().create(build);
    }
}