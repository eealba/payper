package io.github.eealba.payper.core.web;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.badRequest;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.core.web.Util.getUri;
import static io.github.eealba.payper.core.web.Util.readResource;

@WireMockTest

class WebClientPutTest {
    private static final String EXAMPLES = "/examples/";
    private static WebClient client;

    @BeforeAll
    static void setup() {
        client = WebClient.newWebClient();
    }


    @Test
    void test_ok_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, InterruptedException,
            JSONException {
        var jsonRequest = readResource(EXAMPLES + "plan_request_POST.json");
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        stubFor(put("/v1/billing/plans").willReturn(ok(jsonResponse)));
        var uri = getUri(wmRuntimeInfo, "/v1/billing/plans");

        putAndCompare(uri, jsonRequest, 200, jsonResponse, false);
        putAndCompare(uri, jsonRequest, 200, jsonResponse, true);
    }
    @Test
    void test_400_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, JSONException {
        var jsonRequest = readResource(EXAMPLES + "plan_request_POST.json");
        var jsonResponse = readResource(EXAMPLES + "error_plan_request.json");
        stubFor(put("/v1/billing/plans").willReturn(badRequest().withBody(jsonResponse)));
        var uri = getUri(wmRuntimeInfo, "/v1/billing/plans");

        putAndCompare(uri, jsonRequest, 400, jsonResponse, false);
        putAndCompare(uri, jsonRequest, 400, jsonResponse, true);
    }

    private static void putAndCompare(URI uri, String jsonRequest, int expected, String jsonResponse, boolean async)
            throws JSONException {
        var request = Request.newBuilder().uri(uri)
                .PUT(Request.BodyPublishers.ofString(jsonRequest)).build();
        var bodyHandler = Response.BodyHandlers.ofString();

        var response = async ? client.sendAsync(request, bodyHandler).join() : client.send(request, bodyHandler);

        Assertions.assertEquals(expected, response.statusCode());
        if (jsonResponse == null) {
            Assertions.assertNull(response.body());
            return;
        }
        JSONAssert.assertEquals(jsonResponse, response.body(), true);
    }

}
