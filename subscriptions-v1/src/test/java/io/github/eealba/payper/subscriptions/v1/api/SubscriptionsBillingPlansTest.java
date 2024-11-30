package io.github.eealba.payper.subscriptions.v1.api;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.Credential;
import io.github.eealba.payper.core.PayperConfig;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.subscriptions.v1.api.Util.readResource;

@WireMockTest
class SubscriptionsBillingPlansTest {
    private static final String EXAMPLES = "/examples/";


    static Subscriptions getSubscriptions(int port) {
        Credential credential = new Credential("clientId", "secret".toCharArray());
        PayperConfig config = PayperConfig
                .builder(credential, true, "http://localhost:" + port)
                .build();

        return Subscriptions.create(config);
    }
    @Test
    void test_get_plan(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, JSONException {
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        stubFor(get("/v1/billing/plans/1").willReturn(ok(jsonResponse)));
        var port = wmRuntimeInfo.getHttpPort();
        var uri = URI.create("http://localhost:" + port + "/v1/billing/plans/1");
        var subscriptions = getSubscriptions(port);

        // Get plan
        var plan2 = subscriptions
                .billingPlans()
                .get()
                .withId("1")
                .retrieve()
                .toEntity();

        System.out.println(plan2.name());


    }
}