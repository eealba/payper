package io.github.eealba.payper.webhooks.v1.api;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.webhooks.v1.model.EventResend;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.webhooks.v1.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest
class WebhooksEventsApiTest {

    private static final String EXAMPLES = "/examples/";
    private static WebhooksEventsApi webhooksEventsApi;

    @BeforeAll
    static void setupAll(WireMockRuntimeInfo wmRuntimeInfo) {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL,
                "http://localhost:" + wmRuntimeInfo.getHttpPort());
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
        webhooksEventsApi = WebhooksApiClient.create().webhooksEvents();
    }

    @AfterAll
    static void tearDownAll() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        webhooksEventsApi = null;
    }

    @BeforeEach
    void setup() {
        String token = """
                {
                 	"scope": "https://uri.paypal.com/services/invoicing",
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
    void test_list() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "webhooks-events.list_response.json");

        stubFor(get("/v1/notifications/webhooks-events")
                .willReturn(okJson(jsonResponse)));

        var res = webhooksEventsApi.list()
                .retrieve()
                .toEntity();

        assertNotNull(res);
        assertEquals(1, res.count());
        assertEquals("WH-EVENT-1234567890", res.events().get(0).id());
    }

    @Test
    void test_get() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "webhooks-events.get_response.json");

        stubFor(get("/v1/notifications/webhooks-events/WH-EVENT-1234567890")
                .willReturn(okJson(jsonResponse)));

        var res = webhooksEventsApi.get()
                .withId("WH-EVENT-1234567890")
                .retrieve()
                .toEntity();

        assertNotNull(res);
        assertEquals("WH-EVENT-1234567890", res.id());
        assertEquals("PAYMENT.AUTHORIZATION.CREATED", res.eventType());
    }

    @Test
    void test_resend() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "webhooks-events.resend_request.json");
        var jsonResponse = readResource(EXAMPLES + "webhooks-events.resend_response.json");
        var body = Json.create().fromJson(jsonRequest, EventResend.class);

        stubFor(post("/v1/notifications/webhooks-events/WH-EVENT-1234567890/resend")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(okJson(jsonResponse)));

        var res = webhooksEventsApi.resend()
                .withId("WH-EVENT-1234567890")
                .withBody(body)
                .retrieve()
                .toEntity();

        assertNotNull(res);
        assertEquals("WH-4M0448861G563140B-9EX36365822141321", res.id());
        assertEquals("CUSTOMER.DISPUTE.CREATED", res.eventType());
    }
}
