package io.github.eealba.payper.webhooks.v1.api;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.webhooks.v1.model.AnchorType;
import io.github.eealba.payper.webhooks.v1.model.PatchRequest;
import io.github.eealba.payper.webhooks.v1.model.Webhook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.noContent;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.webhooks.v1.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest
class WebhooksApiTest {

    private static final String EXAMPLES = "/examples/";
    private static WebhooksApi webhooksApi;

    @BeforeAll
    static void setupAll(WireMockRuntimeInfo wmRuntimeInfo) {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL,
                           "http://localhost:" + wmRuntimeInfo.getHttpPort());
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
        webhooksApi = WebhooksApiClient.create().webhooks();
    }

    @AfterAll
    static void tearDownAll() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        webhooksApi = null;
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
    void test_create() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "webhooks.post_request.json");
        var jsonResponse = readResource(EXAMPLES + "webhooks.post_response.json");
        var body = Json.create().fromJson(jsonRequest, Webhook.class);

        stubFor(post("/v1/notifications/webhooks")
                        .withRequestBody(equalToJson(jsonRequest))
                        .willReturn(okJson(jsonResponse)));

        var res = webhooksApi.create()
                             .withBody(body)
                             .retrieve()
                             .toEntity();

        assertNotNull(res);
        assertEquals("WH-1234567890", res.id());

    }

    @Test
    void test_list() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "webhooks.list_response.json");

        stubFor(get("/v1/notifications/webhooks?anchor_type=APPLICATION")
                        .willReturn(okJson(jsonResponse)));

        var res = webhooksApi.list()
                             .withAnchorType(AnchorType.APPLICATION)
                             .retrieve()
                             .toEntity();

        assertNotNull(res);
        assertEquals("WH-1234567890", res.webhooks().get(0).id());
    }

    @Test
    void test_get() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "webhooks.get_response.json");

        stubFor(get("/v1/notifications/webhooks/123")
                        .willReturn(okJson(jsonResponse)));

        var res = webhooksApi.get().withId("123")
                             .retrieve()
                             .toEntity();

        assertNotNull(res);
        assertEquals("WH-1234567890", res.id());
    }

    @Test
    void test_update() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "webhooks.update_request.json");
        var jsonResponse = readResource(EXAMPLES + "webhooks.update_response.json");
        var request = Json.create().fromJson(jsonRequest, PatchRequest.class);
        stubFor(patch("/v1/notifications/webhooks/1")
                        .withRequestBody(equalToJson(jsonRequest))
                        .willReturn(okJson(jsonResponse)));


        var response = webhooksApi.update().withId("1").withBody(request).retrieve().toResponse();

        assertEquals(200, response.statusCode());
        assertNotNull(response.toEntity());
        assertEquals("WH-1234567890", response.toEntity().id());
    }

    @Test
    void test_delete() {
        stubFor(delete("/v1/notifications/webhooks/1")
                        .willReturn(noContent()));


        var response = webhooksApi.delete().withId("1").retrieve().toResponse();

        assertEquals(204, response.statusCode());
    }

    @Test
    void test_listEventTypes() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "webhooks-event-types.list_response.json");

        stubFor(get("/v1/notifications/webhooks/123/event-types")
                        .willReturn(okJson(jsonResponse)));

        var res = webhooksApi.listEventTypes()
                             .withId("123")
                             .retrieve()
                             .toEntity();

        assertNotNull(res);
        assertEquals("PAYMENT.AUTHORIZATION.CREATED", res.eventTypes().get(0).name());

    }
}