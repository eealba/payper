package io.github.eealba.payper.webhooks.v1.api;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.client.PayperAuthenticator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.noContent;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.webhooks.v1.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest
class WebhooksLookupApiTest {

    private static final String EXAMPLES = "/examples/";
    private static WebhooksLookupApi webhooksLookupApi;

    @BeforeAll
    static void setupAll(WireMockRuntimeInfo wmRuntimeInfo) {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL,
                "http://localhost:" + wmRuntimeInfo.getHttpPort());
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
        webhooksLookupApi = WebhooksApiClient.create().webhooksLookup();
    }

    @AfterAll
    static void tearDownAll() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        webhooksLookupApi = null;
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
    void test_create() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "webhooks-lookup.post_response.json");

        stubFor(post("/v1/notifications/webhooks-lookup")
                .willReturn(okJson(jsonResponse)));

        var res = webhooksLookupApi.create()
                .retrieve()
                .toEntity();

        assertNotNull(res);
        assertEquals("WL-1234567890", res.id());
    }

    @Test
    void test_list() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "webhooks-lookup.list_response.json");

        stubFor(get("/v1/notifications/webhooks-lookup")
                .willReturn(okJson(jsonResponse)));

        var res = webhooksLookupApi.list()
                .retrieve()
                .toEntity();

        assertNotNull(res);
        assertEquals("WL-1234567890", res.webhooksLookups().get(0).id());
    }

    @Test
    void test_get() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "webhooks-lookup.get_response.json");

        stubFor(get("/v1/notifications/webhooks-lookup/123")
                .willReturn(okJson(jsonResponse)));

        var res = webhooksLookupApi.get().withId("123")
                .retrieve()
                .toEntity();

        assertNotNull(res);
        assertEquals("WL-1234567890", res.id());
    }

    @Test
    void test_delete() {
        stubFor(delete("/v1/notifications/webhooks-lookup/123")
                .willReturn(noContent()));

        var response = webhooksLookupApi.delete().withId("123").retrieve().toResponse();

        assertEquals(204, response.statusCode());
    }
}
