package io.github.eealba.payper.core;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.notFound;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static io.github.eealba.payper.core.Util.readResource;

@WireMockTest
class PayperGetTest {
    private static final String EXAMPLES = "/examples/";
    private static final String V_1_BILLING_PLANS_1 = "/v1/billing/plans/{id}";

    static Payper payper(WireMockRuntimeInfo wmRuntimeInfo) {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL, wmRuntimeInfo.getHttpBaseUrl());
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
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
        return Payper.create();
    }

    @AfterEach
    void tearDown() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        removeStub(post("/v1/oauth2/token"));
    }


    @Test
    void test_ok_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, JSONException {
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        stubFor(get(V_1_BILLING_PLANS_1.replace("{id}", "1")).willReturn(okJson(jsonResponse)));

        getAndCompare(wmRuntimeInfo, V_1_BILLING_PLANS_1, 200, null, false);
    }
    @Test
    void test_ok_with_wiremock_async(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, JSONException {
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        stubFor(get(V_1_BILLING_PLANS_1.replace("{id}", "1")).willReturn(okJson(jsonResponse)));

        getAndCompare(wmRuntimeInfo, V_1_BILLING_PLANS_1, 200, null, true);
    }

    @Test
    void test_not_found_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws JSONException {
        stubFor(get(V_1_BILLING_PLANS_1).willReturn(notFound()));

        getAndCompare(wmRuntimeInfo, V_1_BILLING_PLANS_1, 404, null, false);

    }

    @Test
    void test_not_found_with_wiremock_async(WireMockRuntimeInfo wmRuntimeInfo) throws JSONException {
        stubFor(get(V_1_BILLING_PLANS_1).willReturn(notFound()));

        getAndCompare(wmRuntimeInfo, V_1_BILLING_PLANS_1, 404, null, true);

    }

    private static void getAndCompare(WireMockRuntimeInfo wmRuntimeInfo, String path,
                                      int expected, String jsonResponse, boolean async) throws JSONException {

        var payper = payper(wmRuntimeInfo);
        var request = PayperRequest.newBuilder().path(path).pathParameter("id", "1").GET().build();
        var bodyHandler = PayperResponse.BodyHandlers.ofString();
        var responseSpec = payper.send(request, bodyHandler, bodyHandler);
        var response = async ? responseSpec.toFuture().join() : responseSpec.toResponse();
        Assertions.assertEquals(expected, response.statusCode());

        if (jsonResponse != null) {
            JSONAssert.assertEquals(jsonResponse, response.toEntity(), true);
        }

        verify(1, postRequestedFor(urlEqualTo("/v1/oauth2/token")));

    }
}
