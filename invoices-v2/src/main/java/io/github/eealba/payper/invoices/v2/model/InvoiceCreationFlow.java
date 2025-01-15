package io.github.eealba.payper.invoices.v2.model;
/**
 * The frequency at which the invoice is sent:<ul><li>Multiple recipient. Sent to multiple recipients.</li><li>Batch. Sent 
in a batch.</li><li>Regular single. Sent one time to a single recipient.</li></ul>
 */
public enum InvoiceCreationFlow {
    MULTIPLE_RECIPIENTS_GROUP,
    BATCH,
    REGULAR_SINGLE
}
