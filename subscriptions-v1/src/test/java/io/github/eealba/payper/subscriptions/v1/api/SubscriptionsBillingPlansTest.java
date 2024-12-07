package io.github.eealba.payper.subscriptions.v1.api;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.PayperAuthenticator;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.subscriptions.v1.model.BillingCycle;
import io.github.eealba.payper.subscriptions.v1.model.CurrencyCode;
import io.github.eealba.payper.subscriptions.v1.model.Frequency;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemesListRequest;
import org.json.JSONException;
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
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
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
        var plan = subscriptions.billingPlans().get()
                .withId("1")
                .retrieve().toEntity();
        assertNotNull(plan);
        assertEquals("P-5ML4271244454362WXNWU5NQ", plan.id());
        assertEquals("PROD-XXCD1234QWER65782", plan.productId());
        assertEquals("Video Streaming Service Plan", plan.name());
        assertEquals("Video Streaming Service basic plan", plan.description());
        assertEquals(Plan.Status.ACTIVE, plan.status());
        assertEquals(3, plan.billingCycles().size());
        assertEquals(Frequency.IntervalUnit.MONTH, plan.billingCycles().get(0).frequency().intervalUnit());
        assertEquals(1, plan.billingCycles().get(0).frequency().intervalCount());
        assertEquals(BillingCycle.TenureType.TRIAL, plan.billingCycles().get(0).tenureType());


        assertEquals("2020-05-27T12:13:51Z", plan.createTime().toString());
        assertEquals("2020-05-27T12:13:51Z", plan.updateTime().toString());
        assertEquals("10", plan.taxes().percentage().value());
        assertEquals("10", plan.paymentPreferences().setupFee().value());
        assertEquals(CurrencyCode.USD, plan.paymentPreferences().setupFee().currencyCode());

        assertFalse(plan.taxes().inclusive());
    }
    @Test
    void test_nof_found_plan() throws IOException {
        stubFor(get("/v1/billing/plans/1").willReturn(notFound()));

        // Get plan
        var plan2 = subscriptions.billingPlans().get().withId("1").retrieve().toOptionalEntity();
        assertFalse(plan2.isPresent());
    }

    @Test
    void test_create_plan() throws IOException, JSONException {
        var jsonRequest = readResource(EXAMPLES + "plan_request_POST.json");
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        var body = Json.newJson().fromJson(jsonRequest, PlanRequestPOST.class);
        stubFor(post("/v1/billing/plans")
                .withRequestBody(equalToJson(jsonRequest))
                .withHeader("Prefer", equalTo("return=representation"))
                .withHeader("Paypal-Request-Id", equalTo("request-id"))
                .willReturn(okJson(jsonResponse)));

        var plan = subscriptions.billingPlans().create()
                .withPrefer("return=representation")
                .withPaypalRequestId("request-id")
                .withBody(body)
                .retrieve().toFuture().join();

        assertNotNull(plan);
        assertEquals("P-5ML4271244454362WXNWU5NQ", plan.toEntity().id());
        /*
        // Verify the request body
        verify(postRequestedFor(urlEqualTo("/v1/billing/plans")));

        // Retrieve the actual request body
        String actualRequestBody = getAllServeEvents().get(0).getRequest().getBodyAsString();

        // Compare the expected and actual JSON
        JSONAssert.assertEquals(jsonRequest, actualRequestBody, JSONCompareMode.STRICT);

         */
    }

    @Test
    void test_list_plan() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "planCollection.json");
        stubFor(get(urlPathEqualTo("/v1/billing/plans"))
                .withQueryParam("plan_ids", equalTo("1"))
                .withQueryParam("page_size", equalTo("10"))
                .withQueryParam("page", equalTo("1"))
                .withQueryParam("product_id", equalTo("1"))
                .withQueryParam("total_required", equalTo("true"))
                .willReturn(okJson(jsonResponse)));

        var planCollection = subscriptions.billingPlans().list()
                .withPlanIds("1")
                .withPageSize(10)
                .withPage(1)
                .withProductId("1")
                .withTotalRequired(true)
                .retrieve().toEntity();

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
    @Test
    void activate_plan(){
        stubFor(post("/v1/billing/plans/1/activate").willReturn(noContent()));

        var response = subscriptions.billingPlans().activate().withId("1").retrieve().toFuture().join();

        assertEquals(204, response.statusCode());
    }
    @Test
    void deactivate_plan(){
        stubFor(post("/v1/billing/plans/1/deactivate").willReturn(noContent()));

        var response = subscriptions.billingPlans().deactivate().withId("1").retrieve().toFuture().join();

        assertEquals(204, response.statusCode());
    }
    @Test
    void update_pricing_schemes() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "update_pricing_schemes_list_request.json");
        var body = Json.newJson().fromJson(jsonRequest, UpdatePricingSchemesListRequest.class);
        stubFor(post("/v1/billing/plans/18/update-pricing-schemes")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(noContent()));

        var response = subscriptions.billingPlans().updatePricingSchemes().withId("18")
                .withBody(body).retrieve().toFuture().join();

        assertEquals(204, response.statusCode());
    }

}