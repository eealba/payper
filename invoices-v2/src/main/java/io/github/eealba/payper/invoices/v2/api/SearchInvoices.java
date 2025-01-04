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

import io.github.eealba.payper.core.spec.RequestSpec;
import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.SearchData;

public interface SearchInvoices extends RequestSpec<InvoicesApi, ErrorDefault>,
    RequestSpec.BodySpec<SearchInvoices, SearchData> {
    SearchInvoices withPageSize(int pageSize);
    SearchInvoices withPage(int page);
    SearchInvoices withTotalRequired(boolean totalRequired);

}