package io.github.eealba.payper.payments.v2;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.payments.v2.api.Captures;
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;
import io.github.eealba.payper.payments.v2.model.RefundRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.jsonResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest(httpPort = 8080)
class CapturesTest {
    private static final String EXAMPLES = "/examples/";
    private static Captures captures;

    @BeforeAll
    static void setupAll() {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL, "http://localhost:8080");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
        captures = PaymentsApiClient.create().captures();
    }

    @AfterAll
    static void tearDownAll() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        captures = null;
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
    void test_get() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "capture_2.json");
        stubFor(get("/v2/payments/captures/1").willReturn(okJson(jsonResponse)));

        var res = captures.get().withId("1").retrieve().toEntity();

        assertNotNull(res);
        assertEquals("1", res.id());

    }
    @Test
    void test_get_401() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "error_default.json");
        stubFor(get("/v2/payments/captures/1").willReturn(jsonResponse(jsonResponse, 401)));

        var response = captures.get().withId("1").retrieve().toResponse();

        assertNotNull(response);
        assertEquals(401, response.statusCode());
        assertEquals("AUTHENTICATION_FAILURE", response.toErrorEntity().name());


    }

    @Test
    void test_refund() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "refund_request.json");
        var jsonResponse = readResource(EXAMPLES + "refund.json");

        stubFor(post("/v2/payments/captures/1/refund")
                .withRequestBody(equalToJson(jsonRequest))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("PayPal-Request-Id", containing("123"))
                .withHeader("Prefer", containing("return=representation"))
                .withHeader("PayPal-Auth-Assertion", containing("PayPal-Auth-Assertion"))
                .willReturn(okJson(jsonResponse)));

        var body = Json.create().fromJson(jsonRequest, RefundRequest.class);

        var res = captures.refund()
                .withId("1")
                .withPaypalRequestId("123")
                .withPayPalAuthAssertion("PayPal-Auth-Assertion")
                .withPrefer(RequestSpec.Prefer.RETURN_REPRESENTATION)
                .withBody(body)
                .retrieve()
                .toEntity();

        assertNotNull(res);
        assertEquals("1", res.id());

    }



    private static String readResource(String path) throws IOException {
        try (var inputStream = ModelTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }

}