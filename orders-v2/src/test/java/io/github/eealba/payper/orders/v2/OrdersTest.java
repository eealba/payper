package io.github.eealba.payper.orders.v2;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.PayperAuthenticator;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;
import io.github.eealba.payper.orders.v2.api.Orders;
import io.github.eealba.payper.orders.v2.model.ConfirmOrderRequest;
import io.github.eealba.payper.orders.v2.model.OrderAuthorizeRequest;
import io.github.eealba.payper.orders.v2.model.OrderCaptureRequest;
import io.github.eealba.payper.orders.v2.model.OrderRequest;
import io.github.eealba.payper.orders.v2.model.OrderTrackerRequest;
import io.github.eealba.payper.orders.v2.model.PatchRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.jsonResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.noContent;
import static com.github.tomakehurst.wiremock.client.WireMock.notFound;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.orders.v2.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest(httpPort = 8080)
class OrdersTest {
    private static final String EXAMPLES = "/examples/";
    private static Orders orders;

    @BeforeAll
    static void setupAll() {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL, "http://localhost:8080");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
        orders = CheckoutOrdersApiClient.create().orders();
    }

    @AfterAll
    static void tearDownAll() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        orders = null;
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
        var jsonResponse = readResource(EXAMPLES + "order.json");
        stubFor(get("/v2/checkout/orders/1").willReturn(okJson(jsonResponse)));

        var res = orders.get().withId("1").retrieve().toEntity();

        assertNotNull(res);
        assertEquals("123", res.id());

    }

    @Test
    void not_found() {
        stubFor(get("/v2/checkout/orders/1").willReturn(notFound()));

        var res = orders.get().withId("1").retrieve().toResponse();
        assertEquals(404, res.statusCode());
    }

    @Test
    void test_create() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "order_request.json");
        var jsonResponse = readResource(EXAMPLES + "order.json");
        var body = Json.create().fromJson(jsonRequest, OrderRequest.class);

        stubFor(post("/v2/checkout/orders")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(okJson(jsonResponse)));

        var res = orders.create()
                .withPrefer("return=representation")
                .withPaypalRequestId("request-id")
                .withBody(body)
                .retrieve().toEntity();

        assertNotNull(res);
        assertEquals("123", res.id());
    }

    @Test
    void update_204() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "patch_request.json");
        var request = Json.create().fromJson(jsonRequest, PatchRequest.class);
        stubFor(patch("/v2/checkout/orders/1")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(noContent()));

        var response = orders.update().withId("1").withBody(request).retrieve().toResponse();

        assertEquals(204, response.statusCode());
    }
    @Test
    void confirm_payment_source_200() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "confirm_order_request.json");
        var jsonResponse = readResource(EXAMPLES + "order.json");

        var request = Json.create().fromJson(jsonRequest, ConfirmOrderRequest.class);
        stubFor(post("/v2/checkout/orders/1/confirm-payment-source")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(okJson(jsonResponse)));

        var response = orders.confirmPaymentSource().withId("1").withBody(request).retrieve().toEntity();

        assertNotNull(response);
        assertEquals("123", response.id());
    }

    @Test
    void confirm_payment_source_400() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "confirm_order_request.json");
        var jsonResponseData = readResource(EXAMPLES + "error.json");

        var request = Json.create().fromJson(jsonRequest, ConfirmOrderRequest.class);
        stubFor(post("/v2/checkout/orders/1/confirm-payment-source")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(jsonResponse(jsonResponseData, 400)));

        var response = orders.confirmPaymentSource().withId("1").withBody(request).retrieve().toResponse();

        assertNotNull(response);
        assertEquals(400, response.statusCode());

        assertEquals("INVALID_REQUEST", response.toErrorEntity().name());
    }

    @Test
    void test_authorize_200() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "order_authorize_request.json");
        var jsonResponse = readResource(EXAMPLES + "order_authorize_response.json");
        var request = Json.create().fromJson(jsonRequest, OrderAuthorizeRequest.class);
        stubFor(post("/v2/checkout/orders/1/authorize")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(okJson(jsonResponse)));

        var response = orders.authorize().withId("1").withBody(request).retrieve().toEntity();

        assertNotNull(response);
        assertEquals("123", response.id());
    }

    @Test
    void test_authorize_400() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "order_authorize_request.json");
        var jsonResponseData = readResource(EXAMPLES + "error.json");
        var request = Json.create().fromJson(jsonRequest, OrderAuthorizeRequest.class);
        stubFor(post("/v2/checkout/orders/1/authorize")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(jsonResponse(jsonResponseData, 400)));

        var response = orders.authorize().withId("1").withBody(request).retrieve().toResponse();

        assertNotNull(response);
        assertEquals(400, response.statusCode());

        assertEquals("INVALID_REQUEST", response.toErrorEntity().name());
    }

    @Test
    void test_capture_200() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "order_capture_request.json");
        var jsonResponse = readResource(EXAMPLES + "order.json");
        var request = Json.create().fromJson(jsonRequest, OrderCaptureRequest.class);
        stubFor(post("/v2/checkout/orders/1/capture")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(okJson(jsonResponse)));

        var response = orders.capture().withId("1").withBody(request).retrieve().toEntity();

        assertNotNull(response);
        assertEquals("123", response.id());
    }

    @Test
    void test_capture_400() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "order_capture_request.json");
        var jsonResponseData = readResource(EXAMPLES + "error.json");
        var request = Json.create().fromJson(jsonRequest, OrderCaptureRequest.class);
        stubFor(post("/v2/checkout/orders/1/capture")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(jsonResponse(jsonResponseData, 400)));

        var response = orders.capture().withId("1").withBody(request).retrieve().toResponse();

        assertNotNull(response);
        assertEquals(400, response.statusCode());

        assertEquals("INVALID_REQUEST", response.toErrorEntity().name());
    }

    @Test
    void test_tracker_200() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "order_tracker_request.json");
        var jsonResponse = readResource(EXAMPLES + "order.json");
        var request = Json.create().fromJson(jsonRequest, OrderTrackerRequest.class);
        stubFor(post("/v2/checkout/orders/1/track")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(okJson(jsonResponse)));

        var response = orders.track().withId("1").withBody(request).retrieve().toEntity();

        assertNotNull(response);
        assertEquals("123", response.id());
    }

    @Test
    void test_tracker_400() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "order_tracker_request.json");
        var jsonResponseData = readResource(EXAMPLES + "error.json");
        var request = Json.create().fromJson(jsonRequest, OrderTrackerRequest.class);
        stubFor(post("/v2/checkout/orders/1/track")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(jsonResponse(jsonResponseData, 400)));

        var response = orders.track().withId("1").withBody(request).retrieve().toResponse();

        assertNotNull(response);
        assertEquals(400, response.statusCode());

        assertEquals("INVALID_REQUEST", response.toErrorEntity().name());
    }

    @Test
    void test_update_tracking_200() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "patch_request.json");
        var request = Json.create().fromJson(jsonRequest, PatchRequest.class);
        stubFor(patch("/v2/checkout/orders/1/trackers/234")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(noContent()));

        var response = orders.updateTracking().withId("1").withTrackingId("234")
                .withBody(request).retrieve().toResponse();

        assertEquals(204, response.statusCode());
    }


}