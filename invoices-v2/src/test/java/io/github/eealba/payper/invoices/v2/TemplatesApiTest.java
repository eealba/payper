package io.github.eealba.payper.invoices.v2;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.api.TemplatesApi;
import io.github.eealba.payper.invoices.v2.model.Template;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.jsonResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static io.github.eealba.payper.invoices.v2.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest
public class TemplatesApiTest {
    private static final String EXAMPLES = "/examples/";
    private static TemplatesApi templatesApi;

    @BeforeAll
    static void setupAll(WireMockRuntimeInfo wmRuntimeInfo) {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL, "http://localhost:" + wmRuntimeInfo.getHttpPort());
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
        templatesApi = InvoicingApiClient.create().templates();
    }

    @AfterAll
    static void tearDownAll() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        templatesApi = null;
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
    void should_get_templaes() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "templates.json");

        stubFor(get(urlPathEqualTo("/v2/invoicing/templates"))
                .withQueryParam("page", equalTo("1"))
                .withQueryParam("page_size", equalTo("10"))
                .willReturn(jsonResponse(jsonResponse, 200)));

        var result = templatesApi.list()
                .withPage(1)
                .withPageSize(10)
                .retrieve()
                .toFuture()
                .join();


        assertNotNull(result);
        assertEquals(200, result.statusCode());
        assertEquals(3, result.toEntity().templates().size());
    }

    @Test
    void should_create_template() throws IOException {
        var template = readResource(EXAMPLES + "template2.json");

        stubFor(post(urlPathEqualTo("/v2/invoicing/templates"))
                .withRequestBody(equalToJson(template))
                .willReturn(jsonResponse(template, 201)));

        var result = templatesApi.create()
                .withBody(Json.create().fromJson(template, Template.class))
                .retrieve()
                .toFuture()
                .join();

        assertNotNull(result);
        assertEquals(201, result.statusCode());
        assertEquals("deal-ref", result.toEntity().templateInfo().detail().reference());
    }
    @Test
    void should_get_template() throws IOException {
        var template = readResource(EXAMPLES + "template.json");

        stubFor(get(urlPathEqualTo("/v2/invoicing/templates/1"))
                .willReturn(jsonResponse(template, 200)));

        var result = templatesApi.get()
                .withId("1")
                .retrieve()
                .toFuture()
                .join();

        assertNotNull(result);
        assertEquals(200, result.statusCode());
        assertEquals("deal-ref", result.toEntity().templateInfo().detail().reference());
    }

    @Test
    void should_update_template() throws IOException {
        var template = readResource(EXAMPLES + "template2.json");

        stubFor(put(urlPathEqualTo("/v2/invoicing/templates/1"))
                .withRequestBody(equalToJson(template))
                .willReturn(jsonResponse(template, 200)));

        var result = templatesApi.update()
                .withId("1")
                .withBody(Json.create().fromJson(template, Template.class))
                .retrieve()
                .toFuture()
                .join();

        assertNotNull(result);
        assertEquals(200, result.statusCode());
        assertEquals("deal-ref", result.toEntity().templateInfo().detail().reference());
    }

    @Test
    void should_delete_template() {
        stubFor(delete(urlPathEqualTo("/v2/invoicing/templates/1"))
                .willReturn(aResponse().withStatus(204)));

        var result = templatesApi.delete()
                .withId("1")
                .retrieve()
                .toFuture()
                .join();

        assertNotNull(result);
        assertEquals(204, result.statusCode());
    }

}
