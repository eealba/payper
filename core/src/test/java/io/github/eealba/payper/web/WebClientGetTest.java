package io.github.eealba.payper.web;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.core.web.Request;
import io.github.eealba.payper.core.web.Response;
import io.github.eealba.payper.core.web.WebClient;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.notFound;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.Util.getUri;
import static io.github.eealba.payper.Util.readResource;

@WireMockTest
class WebClientGetTest {
    private static final String EXAMPLES = "/examples/";
    private static final String V_1_BILLING_PLANS_1 = "/v1/billing/plans/1";
    private static WebClient client;

    @BeforeAll
    static void setup() {
        client = WebClient.create();
    }

    @Test
    void test_ok_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, JSONException {
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        stubFor(get(V_1_BILLING_PLANS_1).willReturn(ok(jsonResponse)));
        var uri = getUri(wmRuntimeInfo, V_1_BILLING_PLANS_1);

        getAndCompare(uri, 200, jsonResponse, false);
        getAndCompare(uri, 200, jsonResponse, true);
    }
    @Test
    void test_not_found_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws  JSONException {
        stubFor(get(V_1_BILLING_PLANS_1).willReturn(notFound()));
        var uri = getUri(wmRuntimeInfo, V_1_BILLING_PLANS_1);

        getAndCompare(uri, 404, null, false);
        getAndCompare(uri, 404, null, true);

    }


    private static void getAndCompare(URI uri, int expected, String jsonResponse, boolean async) throws JSONException {
        var request = Request.newBuilder().uri(uri).GET().build();
        var bodyHandler = Response.BodyHandlers.ofString();
        var response = async ? client.sendAsync(request, bodyHandler).join() : client.send(request, bodyHandler);
        Assertions.assertEquals(expected, response.statusCode());

        if (jsonResponse != null) {
            JSONAssert.assertEquals(jsonResponse, response.body(), true);
        }
    }
}
