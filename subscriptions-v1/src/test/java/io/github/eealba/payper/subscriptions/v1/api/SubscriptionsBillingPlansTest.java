package io.github.eealba.payper.subscriptions.v1.api;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.PayperAuthenticator;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.noContent;
import static com.github.tomakehurst.wiremock.client.WireMock.notFound;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.subscriptions.v1.api.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest(httpPort = 8080)
class SubscriptionsBillingPlansTest {
    private static final String EXAMPLES = "/examples/";
    private static Subscriptions subscriptions;

    @BeforeAll
    static void setupAll() {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL, "http://localhost:8080");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
        subscriptions = Subscriptions.create();
    }
    @AfterAll
    static void tearDownAll() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        subscriptions = null;
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
    void test_get_plan() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        stubFor(get("/v1/billing/plans/1").willReturn(okJson(jsonResponse)));

        // Get plan
        var plan2 = subscriptions.billingPlans().get().withId("1").retrieve().toEntity();
        assertNotNull(plan2);
        assertEquals("P-5ML4271244454362WXNWU5NQ", plan2.id());
    }
    @Test
    void test_nof_found_plan() throws IOException {
        stubFor(get("/v1/billing/plans/1").willReturn(notFound()));

        // Get plan
        var plan2 = subscriptions.billingPlans().get().withId("1").retrieve().toOptionalEntity();
        assertFalse(plan2.isPresent());
    }

    @Test
    void test_create_plan() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "plan_request_POST.json");
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        var body = Json.newJson().fromJson(jsonRequest, PlanRequestPOST.class);
        stubFor(post("/v1/billing/plans").willReturn(okJson(jsonResponse)));

        var plan = subscriptions.billingPlans().create().withBody(body).retrieve().toFuture().join();

        assertNotNull(plan);
        assertEquals("P-5ML4271244454362WXNWU5NQ", plan.toEntity().id());
    }

    @Test
    void test_list_plan() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "planCollection.json");
        stubFor(get("/v1/billing/plans").willReturn(okJson(jsonResponse)));

        var planCollection = subscriptions.billingPlans().list().retrieve().toEntity();

        assertNotNull(planCollection);
        assertEquals(1, planCollection.plans().size());
    }
    @Test
    void update_plan_204() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "patch_request.json");
        var request = Json.newJson().fromJson(jsonRequest, PatchRequest.class);
        stubFor(patch("/v1/billing/plans/1").willReturn(noContent()));

        var response = subscriptions.billingPlans().update().withId("1").withBody(request).retrieve().toFuture().join();

        assertEquals(204, response.statusCode());

    }
    @Test
    void update_plan_400() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "bad_request.json");
        var jsonRequest = readResource(EXAMPLES + "patch_request.json");
        var request = Json.newJson().fromJson(jsonRequest, PatchRequest.class);
        stubFor(patch("/v1/billing/plans/4").willReturn(aResponse().withStatus(400).withBody(jsonResponse)));

        var response = subscriptions.billingPlans().update().withId("4").withBody(request).retrieve().toFuture().join();

        assertEquals(400, response.statusCode());
        assertEquals("INVALID_REQUEST", response.toErrorEntity().name());
        assertEquals("Request is not well-formed, syntactically incorrect, or violates schema.",
                response.toErrorEntity().message());

    }

}