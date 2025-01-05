package io.github.eealba.payper.invoices.v2;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.PayperAuthenticator;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.invoices.v2.api.InvoicesApi;
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.model.Invoice;
import io.github.eealba.payper.invoices.v2.model.Notification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.jsonResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static io.github.eealba.payper.invoices.v2.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest(httpPort = 8080)
public class InvoicesApiTest {
    private static final String EXAMPLES = "/examples/";
    private static InvoicesApi invoicesApi;

    @BeforeAll
    static void setupAll() {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL, "http://localhost:8080");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
        invoicesApi = InvoicingApiClient.create().invoices();
    }

    @AfterAll
    static void tearDownAll() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        invoicesApi = null;
    }

    @BeforeEach
    void setup() {
        String token = """
                {
                 	"scope": "https://uri.paypal.com/services/invoicing https://uri.paypal.com/services/vault/payment-tokens/read https://uri.paypal.com/services/disputes/read-buyer https://uri.paypal.com/services/payments/realtimepayment https://uri.paypal.com/services/disputes/update-seller https://uri.paypal.com/services/paypalhere https://uri.paypal.com/services/payments/payment/authcapture openid https://uri.paypal.com/services/disputes/read-seller Braintree:Vault https://uri.paypal.com/services/payments/refund https://uri.paypal.com/services/identity/activities https://api.paypal.com/v1/vault/credit-card https://api.paypal.com/v1/payments/.* https://uri.paypal.com/payments/payouts https://uri.paypal.com/services/vault/payment-tokens/readwrite https://api.paypal.com/v1/vault/credit-card/.* https://uri.paypal.com/services/shipping/trackers/readwrite https://uri.paypal.com/services/subscriptions https://uri.paypal.com/services/applications/webhooks https://api.paypal.com/v1/payments/refund https://api.paypal.com/v1/payments/sale/.*/refund",
                 	"access_token": "A21AALonQd7hsKc-mvj1Ik8v3HrP3e5YY5V7C4__KM08zH1RqHpgAvsdW6_kRRTM5AWO0WnIyyOgXz_Ki6ytB126Gjn8uBekg",
                 	"token_type": "Bearer",
                 	"app_id": "APP-80W284485P519543T",
                 	"expires_in": 30840,
                 	"nonce": "{now}dt-959IViiJFKwTQETYAg_tAcv7byuLRQyt9C8Dh0Rg"
                 }
                """.replace("{now}", Instant.now().toString());
        stubFor(post("/v1/oauth2/token").willReturn(okJson(token)));
    }

    @AfterEach
    void tearDown() {
        removeStub(post("/v1/oauth2/token"));
    }

    @Test
    void test_create_invoice() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "invoice.json");
        var jsonResponse = readResource(EXAMPLES + "invoice.json");
        var body = Json.create().fromJson(jsonRequest, Invoice.class);

        stubFor(post("/v2/invoicing/invoices")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(jsonResponse(jsonResponse, 201)));

        var result = invoicesApi.create()
                .withBody(body)
                .retrieve().toFuture().join();


        assertNotNull(result);
        assertEquals(201, result.statusCode());
        assertEquals("INV2-GWNR-QBAW-496B-4Y5P", result.toEntity().id());

    }
    @Test
    void test_list_invoices() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "invoices.json");
        stubFor(get(urlPathEqualTo("/v2/invoicing/invoices"))
                .withQueryParam("page_size", equalTo("10"))
                .withQueryParam("page", equalTo("1"))
                .withQueryParam("total_required", equalTo("true"))
                .withQueryParam("fields", equalTo("all"))
                .willReturn(okJson(jsonResponse)));

        var result = invoicesApi.list()
                .withPageSize(10)
                .withPage(1)
                .withTotalRequired(true)
                .withFields("all")
                .retrieve()
                .toEntity();

        assertNotNull(result);
        assertEquals(1, result.totalItems());
        assertEquals("INV2-GWNR-QBAW-496B-4Y5P", result.items().get(0).id());
    }

    @Test
    void test_send_invoice() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "notification_reminder.json");
        var jsonResponse = readResource(EXAMPLES + "link_description.json");
        stubFor(post("/v2/invoicing/invoices/INV2-GWNR-QBAW-496B-4Y5P/send")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(jsonResponse(jsonResponse, 200)));

        var body = Json.create().fromJson(jsonRequest, Notification.class);
        var result = invoicesApi.send()
                .withId("INV2-GWNR-QBAW-496B-4Y5P")
                .withBody(body)
                .retrieve()
                .toResponse();

        assertNotNull(result);
        assertEquals(200, result.statusCode());
        assertEquals("string", result.toEntity().rel());
    }

    @Test
    void test_remind_invoice() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "notification_reminder.json");
        stubFor(post("/v2/invoicing/invoices/INV2-GWNR-QBAW-496B-4Y5P/remind")
                .willReturn(aResponse().withStatus(204)));

        var result = invoicesApi.remind()
                .withId("INV2-GWNR-QBAW-496B-4Y5P")
                .retrieve()
                .toResponse();

        assertNotNull(result);
        assertEquals(204, result.statusCode());
    }

    @Test
    void test_cancel_invoice() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "notification_invoice_cancel.json");
        stubFor(post("/v2/invoicing/invoices/INV2-GWNR-QBAW-496B-4Y5P/cancel")
                .willReturn(aResponse().withStatus(204)));

        var result = invoicesApi.cancel()
                .withId("INV2-GWNR-QBAW-496B-4Y5P")
                .retrieve()
                .toResponse();

        assertNotNull(result);
        assertEquals(204, result.statusCode());
    }
}
