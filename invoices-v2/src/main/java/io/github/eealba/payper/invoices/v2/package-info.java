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

/**
 * This package contains the classes and interfaces for the Payper Invoices API v2.
 * <p>
 * The main entry point for interacting with the API is the
 * {@link io.github.eealba.payper.invoices.v2.api.InvoicesApi} interface.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>
 * {@code
 * InvoicesApi invoicesApi = InvoicingApiClient.create().invoices();
 *
 * // Create an invoice
 * var invoice = invoicesApi.create()
 *   .withBody(Invoice.builder().build())
 *   .retrieve()
 *   .toEntity();
 *
 * // List invoices
 * var listInvoices = invoicesApi.list()
 *   .withPage(1)
 *   .withPageSize(10)
 *   .withTotalRequired(true)
 *   .retrieve()
 *   .toEntity();
 *
 * // Get an invoice
 * invoice = invoicesApi.get().withId("invoice-id").retrieve().toEntity();
 *
 * // Update an invoice
 * var updateInvoice = invoicesApi.update().withId("invoice-id")
 *   .withBody(Invoice.builder().build())
 *   .retrieve()
 *   .toEntity();
 *
 * // Delete an invoice
 * iinvoicesApi.delete().withId("invoice-id").retrieve().toVoid();;
 *
 * }
 * </pre>
 * @since 1.0
 * @version 1.0
 * @see io.github.eealba.payper.invoices.v2.api.InvoicesApi
 *
 * @author Edgar Alba
 *
 */
package io.github.eealba.payper.invoices.v2;