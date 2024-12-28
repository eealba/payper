package io.github.eealba.payper.subscriptions.v1;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.PayperAuthenticator;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionRequestPost;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionReviseRequest;
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
import static com.github.tomakehurst.wiremock.client.WireMock.noContent;
import static com.github.tomakehurst.wiremock.client.WireMock.notFound;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.subscriptions.v1.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest(httpPort = 8080)
class BillingSubscriptionsApiClientTest {
    private static final String EXAMPLES = "/examples/";
    private static SubscriptionsApiClient subscriptionsApiClient;

    @BeforeAll
    static void setupAll() {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL, "http://localhost:8080");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
        subscriptionsApiClient = SubscriptionsApiClient.create();
    }
    @AfterAll
    static void tearDownAll() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        subscriptionsApiClient = null;
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
    void test_get_subscription() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "subscription.json");
        stubFor(get("/v1/billing/subscriptions/1").willReturn(okJson(jsonResponse)));

        // Get subscription
        var subscription = subscriptionsApiClient.billingSubscriptions().get()
                .withId("1")
                .retrieve().toEntity();

        assertNotNull(subscription);
        assertEquals("I-BW452GLLEP1G", subscription.id());
        assertEquals("P-5ML4271244454362WXNWU5NQ", subscription.planId());
    }
    @Test
    void test_not_found_subscription() {
        stubFor(get("/v1/billing/subscriptions/1").willReturn(notFound()));

        // Get subscription
        var subscription = subscriptionsApiClient.billingSubscriptions().get().withId("1").retrieve().toOptionalEntity();
        assertFalse(subscription.isPresent());
    }

    @Test
    void test_create_subscription() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "subscription_request_post.json");
        var jsonResponse = readResource(EXAMPLES + "subscription.json");
        var body = Json.create().fromJson(jsonRequest, SubscriptionRequestPost.class);

        stubFor(post("/v1/billing/subscriptions")
                .withRequestBody(equalToJson(jsonRequest))
                .withHeader("Prefer", equalTo("return=representation"))
                .withHeader("Paypal-Request-Id", equalTo("request-id"))
                .willReturn(okJson(jsonResponse)));

        var subscription = subscriptionsApiClient.billingSubscriptions().create()
                .withPrefer("return=representation")
                .withPaypalRequestId("request-id")
                .withBody(body)
                .retrieve().toFuture().join();

        assertNotNull(subscription);
        assertEquals("I-BW452GLLEP1G", subscription.toEntity().id());

    }

    @Test
    void update_subscription_204() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "patch_request.json");
        var request = Json.create().fromJson(jsonRequest, PatchRequest.class);
        stubFor(patch("/v1/billing/subscriptions/1").willReturn(noContent()));

        var response = subscriptionsApiClient.billingSubscriptions().update().withId("1").withBody(request).retrieve().toFuture().join();

        assertEquals(204, response.statusCode());

    }
    @Test
    void update_subscription_400() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "bad_request.json");
        var jsonRequest = readResource(EXAMPLES + "patch_request.json");
        var request = Json.create().fromJson(jsonRequest, PatchRequest.class);
        stubFor(patch("/v1/billing/subscriptions/4").willReturn(aResponse().withStatus(400).withBody(jsonResponse)));

        var response = subscriptionsApiClient.billingSubscriptions().update().withId("4").withBody(request).retrieve().toFuture().join();

        assertEquals(400, response.statusCode());
        assertEquals("INVALID_REQUEST", response.toErrorEntity().name());
        assertEquals("Request is not well-formed, syntactically incorrect, or violates schema.",
                response.toErrorEntity().message());

    }
    @Test
    void activate_subscription(){
        stubFor(post("/v1/billing/subscriptions/1/activate").willReturn(noContent()));

        var response = subscriptionsApiClient.billingSubscriptions().activate().withId("1").retrieve().toFuture().join();

        assertEquals(204, response.statusCode());
    }
    @Test
    void suspend_subscription(){
        stubFor(post("/v1/billing/subscriptions/1/suspend").willReturn(noContent()));

        var response = subscriptionsApiClient.billingSubscriptions().suspend().withId("1").retrieve().toFuture().join();

        assertEquals(204, response.statusCode());
    }

    @Test
    void cancel_subscription(){
        stubFor(post("/v1/billing/subscriptions/1/cancel").willReturn(noContent()));

        var response = subscriptionsApiClient.billingSubscriptions().cancel().withId("1").retrieve().toFuture().join();

        assertEquals(204, response.statusCode());
    }

    @Test
    void revise_subscription() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "subscription_revise_request.json");
        var jsonResponse = readResource(EXAMPLES + "subscription_revise_response.json");
        var body = Json.create().fromJson(jsonRequest, SubscriptionReviseRequest.class);
        stubFor(post("/v1/billing/subscriptions/18/revise")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(okJson(jsonResponse)));

        var response = subscriptionsApiClient.billingSubscriptions().revise().withId("18")
                .withBody(body).retrieve().toFuture().join();

        assertEquals(200, response.statusCode());
    }

}