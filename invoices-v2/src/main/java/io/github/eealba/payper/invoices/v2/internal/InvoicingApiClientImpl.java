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
import io.github.eealba.payper.core.client.PayperConfig;
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.client.PayperRequest;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.invoices.v2.api.GenerateNextInvoiceNumber;
import io.github.eealba.payper.invoices.v2.api.InvoicesApi;
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.api.SearchInvoices;
import io.github.eealba.payper.invoices.v2.api.TemplatesApi;
import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.InvoiceNumber;
import io.github.eealba.payper.invoices.v2.model.Invoices;

/**
 * Implementation of the Invoicing API client.
 * <p>
 * Provides access to the invoices, templates, generate next invoice number, and search invoices endpoints.
 * </p>
 *
 * @author Edgar Alba
 * @version 1.0
 * @since 1.0
 */
class InvoicingApiClientImpl extends InvoicingApiClient {
    private final InvoicesApiImpl invoices;
    private final Payper payper;
    private final TemplatesApiImpl templates;


    InvoicingApiClientImpl(PayperConfig config) {
        var payper = Payper.create(config);
        this.payper = payper;
        this.invoices = new InvoicesApiImpl(payper);
        this.templates = new TemplatesApiImpl(payper);
    }

    @Override
    public InvoicesApi invoices() {
        return invoices;
    }

    @Override
    public TemplatesApi templates() {
        return templates;
    }

    @Override
    public GenerateNextInvoiceNumber generateNextInvoiceNumber() {
        var spec = PayperProvider.provider().createSpecBuilder(GenerateNextInvoiceNumber.class, payper,
                                                               "/v2/invoicing/generate-next-invoice-number",
                                                               InvoiceNumber.class, ErrorDefault.class)
                                 .method(PayperRequest.Method.POST)
                                 .header("Content-Type", "application/json")
                                 .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public SearchInvoices searchInvoices() {
        var spec = PayperProvider.provider().createSpecBuilder(SearchInvoices.class, payper,
                                                               "/v2/invoicing/search-invoices", Invoices.class,
                                                               ErrorDefault.class)
                                 .method(PayperRequest.Method.POST)
                                 .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }
}
