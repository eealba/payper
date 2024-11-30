package io.github.eealba.payper.subscriptions.v1.api;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.PayperAuthenticator;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.subscriptions.v1.api.Util.readResource;

@WireMockTest(httpPort = 8080)
class SubscriptionsBillingPlansTest {
    private static final String EXAMPLES = "/examples/";
    private static Subscriptions subscriptions;

    @BeforeAll
    static void setupAll() {
        PayperAuthenticator.PayperAuthenticators.setDefault( PayperAuthenticator.PayperAuthenticators.of(
                        () -> "http://localhost:8080","clientId"::toCharArray,"secret"::toCharArray));
        subscriptions = Subscriptions.create();
    }

    @Test
    void test_get_plan(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, JSONException {
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        stubFor(get("/v1/billing/plans/1").willReturn(ok(jsonResponse)));

        // Get plan
        var plan2 = subscriptions.billingPlans().get().withId("1").retrieve().toEntity();
        //TODO: Fix this
        System.out.println(plan2.name());


    }
}