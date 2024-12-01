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
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static io.github.eealba.payper.core.Util.getUri;
import static io.github.eealba.payper.core.Util.readResource;

@WireMockTest

class WebClientPostTest {
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
        stubFor(post("/v1/billing/plans").willReturn(ok(jsonResponse)));
        var uri = getUri(wmRuntimeInfo, "/v1/billing/plans");

        postAndCompare(uri, jsonRequest, 200, jsonResponse, false);
        postAndCompare(uri, jsonRequest, 200, jsonResponse, true);
    }
    @Test
    void test_400_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, JSONException {
        var jsonRequest = readResource(EXAMPLES + "plan_request_POST.json");
        var jsonResponse = readResource(EXAMPLES + "error_plan_request.json");
        stubFor(post("/v1/billing/plans").willReturn(badRequest().withBody(jsonResponse)));
        var uri = getUri(wmRuntimeInfo, "/v1/billing/plans");

        postAndCompare(uri, jsonRequest, 400, jsonResponse, false);
        postAndCompare(uri, jsonRequest, 400, jsonResponse, true);
    }

    private static void postAndCompare(URI uri, String jsonRequest, int expected, String jsonResponse, boolean async)
            throws JSONException {
        var request = Request.newBuilder().uri(uri)
                .POST(Request.BodyPublishers.ofString(jsonRequest))
                .authorization(Request.Authorizations.BEARER("ax123"))
                .contentType(Request.ContentTypes.APPLICATION_JSON)
                .build();
        var bodyHandler = Response.BodyHandlers.ofString();

        var response = async ? client.sendAsync(request, bodyHandler).join() : client.send(request, bodyHandler);

        Assertions.assertEquals(expected, response.statusCode());
        if (jsonResponse == null) {
            Assertions.assertNull(response.body());
            return;
        }
        JSONAssert.assertEquals(jsonResponse, response.body(), true);

        verify(postRequestedFor(urlEqualTo("/v1/billing/plans"))
                .withHeader("Authorization" , equalTo("Bearer ax123"))
                .withHeader("Content-Type", equalTo("application/json")));
    }

}
