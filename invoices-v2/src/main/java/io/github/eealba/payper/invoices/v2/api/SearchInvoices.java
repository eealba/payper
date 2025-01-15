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
import io.github.eealba.payper.invoices.v2.model.Invoices;
import io.github.eealba.payper.invoices.v2.model.SearchData;

/**
 * Interface for searching invoices in the Payper Invoices API v2.
 * <p>
 * This interface provides methods for searching invoices based on various criteria.
 * </p>
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface SearchInvoices extends RequestSpec<Invoices, ErrorDefault>,
    RequestSpec.BodySpec<SearchInvoices, SearchData>, RequestSpec.PaginationSpec<SearchInvoices> {

    /**
     * Sets the search criteria for the invoices.
     * @param searchData the search criteria
     * @return the {@link SearchInvoices} instance
     */
    SearchInvoices withSearchData(SearchData searchData);

    /**
     * Sets the pagination parameters for the search results.
     * @param page the page number
     * @param size the page size
     * @return the {@link SearchInvoices} instance
     */
    SearchInvoices withPagination(int page, int size);
}