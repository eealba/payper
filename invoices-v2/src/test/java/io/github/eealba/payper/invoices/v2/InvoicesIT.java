package io.github.eealba.payper.invoices.v2;

import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.client.PayperConfig;
import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.model.Invoice;
import io.github.eealba.payper.invoices.v2.model.Notification;
import io.github.eealba.payper.invoices.v2.model.QrConfig;
import io.github.eealba.payper.invoices.v2.model.Template;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;

import static io.github.eealba.payper.invoices.v2.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InvoicesIT {
    private static final String EXAMPLES = "/examples/";
    private static InvoicingApiClient invoicingApiClient;
    private static String draftInvoiceNumber;
    private static String invoiceId;
    private static String templateId;

    @BeforeAll
    static void setup() throws IOException {
        var path = Path.of(System.getProperty("user.home"),   "/.payper/", "credentials.properties");
        var config = PayperConfig.builder()
                .authenticator(PayperAuthenticator.PayperAuthenticators.ofSandBox(path))
                .build();
        invoicingApiClient = InvoicingApiClient.create(config);
    }

    /*************************************************************************
     * Invoices API
     *************************************************************************/
    @Test
    @Order(1)
    void generate_next_invoice_number() {
        var response = invoicingApiClient.generateNextInvoiceNumber().retrieve().toResponse();
        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
        draftInvoiceNumber = response.toEntity().invoiceNumber();
        System.out.println("draftInvoiceNumber: " + draftInvoiceNumber);
    }

    @Test
    @Order(2)
    void create_invoice() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "draft_invoice.json")
                .replaceAll("\\{\\{draft_invoice_number}}", draftInvoiceNumber);

        var body = Json.create().fromJson(jsonRequest, Invoice.class);

        var response = invoicingApiClient.invoices()
                .create()
                .withPrefer(RequestSpec.Prefer.RETURN_REPRESENTATION)
                .withBody(body)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
        invoiceId = response.toEntity().id();

    }

    @Test
    @Order(3)
    void get_invoice() {
        var response = invoicingApiClient.invoices()
                .get()
                .withId(invoiceId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(4)
    void list_invoices() {
        var response = invoicingApiClient.invoices()
                .list()
                .withPage(1)
                .withPageSize(10)
                .withTotalRequired(true)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
        assertFalse(response.toEntity().items().isEmpty());
    }

    @Test
    @Order(5)
    void generate_QR_code() {
        var response = invoicingApiClient.invoices()
                .generateQRCode().withId(invoiceId)
                .withBody(QrConfig.builder().action("PAY").height(400).width(400).build())
                .retrieve()
                .toResponse();
        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(6)
    void update_invoice()  {
        var body = invoicingApiClient.invoices()
                .get()
                .withId(invoiceId)
                .retrieve()
                .toEntity();

        var response = invoicingApiClient.invoices()
                .update()
                .withId(invoiceId)
                .withBody(body)
                .retrieve()
                .toResponse();
        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(7)
    void send_invoice() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "send_invoice.json");

        var body = Json.create().fromJson(jsonRequest, Notification.class);

        var response = invoicingApiClient.invoices()
                .send()
                .withId(invoiceId)
                .withBody(body)
                .retrieve()
                .toResponse();
        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(8)
    void send_reminder() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "notification_reminder.json");
        var body = Json.create().fromJson(jsonRequest, Notification.class);

        var response = invoicingApiClient.invoices()
                .remind()
                .withId(invoiceId)
                .withBody(body)
                .retrieve()
                .toResponse();
        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(9)
    void cancel_invoice() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "notification_invoice_cancel.json");
        var body = Json.create().fromJson(jsonRequest, Notification.class);

        var response = invoicingApiClient.invoices()
                .cancel()
                .withId(invoiceId)
                .withBody(body)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(10)
    void delete_invoice() {
        var response = invoicingApiClient.invoices()
                .delete()
                .withId(invoiceId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    /*************************************************************************
     * Template API
     *************************************************************************/

    @Test
    @Order(11)
    void create_template() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "template2.json")
                .replaceAll("<<timestamp>>", Instant.now().toString());
        var body = Json.create().fromJson(jsonRequest, Template.class);

        var response = invoicingApiClient.templates()
                .create()
                .withPrefer(RequestSpec.Prefer.RETURN_REPRESENTATION)
                .withBody(body)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
        templateId = response.toEntity().id();
    }

    @Test
    @Order(12)
    void get_template() {
        var response = invoicingApiClient.templates()
                .get()
                .withId(templateId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(13)
    void list_templates() {
        var response = invoicingApiClient.templates()
                .list()
                .withPage(1)
                .withPageSize(10)
                .withTotalRequired(true)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
        assertFalse(response.toEntity().templates().isEmpty());
    }

    @Test
    @Order(14)
    void update_template() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "template2.json");
        var body = Json.create().fromJson(jsonRequest, Template.class);

        var response = invoicingApiClient.templates()
                .update()
                .withId(templateId)
                .withBody(body)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(15)
    void delete_template() {
        var response = invoicingApiClient.templates()
                .delete()
                .withId(templateId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }
}
