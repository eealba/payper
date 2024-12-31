package io.github.eealba.payper.invoices.v2;

import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.Invoice;
import io.github.eealba.payper.invoices.v2.model.InvoiceNumber;
import io.github.eealba.payper.invoices.v2.model.Invoices;
import io.github.eealba.payper.invoices.v2.model.LinkDescription;
import io.github.eealba.payper.invoices.v2.model.Notification;
import io.github.eealba.payper.invoices.v2.model.PaymentDetail;
import io.github.eealba.payper.invoices.v2.model.PaymentReference;
import io.github.eealba.payper.invoices.v2.model.QrConfig;
import io.github.eealba.payper.invoices.v2.model.RefundDetail;
import io.github.eealba.payper.invoices.v2.model.RefundReference;
import io.github.eealba.payper.invoices.v2.model.SearchData;
import io.github.eealba.payper.invoices.v2.model.Template;
import io.github.eealba.payper.invoices.v2.model.Templates;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.github.eealba.payper.invoices.v2.Util.execute;

public class ModelTest {

    @Test
    void invoice_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/invoice.json", Invoice.class);
    }
    @Test
    void invoices_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/invoices.json", Invoices.class);
    }
    @Test
    void notification_invoice_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/notification_invoice.json", Notification.class);
    }
    @Test
    void link_description_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/link_description.json", LinkDescription.class);
    }
    @Test
    void payment_detail_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/payment_detail.json", PaymentDetail.class);
    }
    @Test
    void payment_reference_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/payment_reference.json", PaymentReference.class);
    }
    @Test
    void error_default_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/error_default.json", ErrorDefault.class);
    }

    @Test
    void refund_detail_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/refund_detail.json", RefundDetail.class);
    }
    @Test
    void refund_reference_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/refund_reference.json", RefundReference.class);
    }
    @Test
    void qr_config_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/qr_config.json", QrConfig.class);
    }
    @Test
    void invoice_number_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/invoice_number.json", InvoiceNumber.class);
    }
    @Test
    void search_data_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/search_data.json", SearchData.class);
    }

    @Test
    void template_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/template.json", Template.class);
    }
    @Test
    void template2_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/templates.json", Templates.class);
    }
}
