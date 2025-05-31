package io.github.eealba.payper.webhooks.v1.api;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.webhooks.v1.model.Event;
import io.github.eealba.payper.webhooks.v1.model.SimulateEvent;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.webhooks.v1.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest
class SimulateEventApiTest {

    private static final String EXAMPLES = "/examples/";
    private static SimulateEventApi simulateEventApi;

    @BeforeAll
    static void setupAll(WireMockRuntimeInfo wmRuntimeInfo) {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL,
                "http://localhost:" + wmRuntimeInfo.getHttpPort());
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
        simulateEventApi = WebhooksApiClient.create().simulateEvent();
    }

    @AfterAll
    static void tearDownAll() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        simulateEventApi = null;
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
    void test_simulateEvent() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "simulate-event.json");
        var jsonResponse = readResource(EXAMPLES + "simulate-event-response.json");
        var body = Json.create().fromJson(jsonRequest, SimulateEvent.class);

        stubFor(post("/v1/notifications/simulate-event")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(okJson(jsonResponse)));

        Event res = simulateEventApi.withBody(body)
                .retrieve()
                .toEntity();

        assertNotNull(res);
        assertEquals("WH-EVENT-1234567890", res.id());
        assertEquals("PAYMENT.AUTHORIZATION.CREATED", res.eventType());
    }
}
