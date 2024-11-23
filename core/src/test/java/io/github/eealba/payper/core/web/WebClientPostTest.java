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
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.core.web.Util.readResource;

@WireMockTest

class WebClientPostTest {
    private static final String EXAMPLES = "/examples/";
    private static WebClient client;

    @BeforeAll
    static void setup() {
        client = WebClient.newWebClient();
    }


    @Test
    void test_post_ok_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, InterruptedException,
            JSONException {
        var jsonRequest = readResource(EXAMPLES + "plan_request_POST.json");
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        stubFor(post("/v1/billing/plans").willReturn(ok(jsonResponse)));
        var port = wmRuntimeInfo.getHttpPort();
        var uri = URI.create("http://localhost:" + port + "/v1/billing/plans");

        postAndCompare(uri, jsonRequest, 200, jsonResponse);
    }
    @Test
    void test_post_400_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, InterruptedException,
            JSONException {
        var jsonRequest = readResource(EXAMPLES + "plan_request_POST.json");
        var jsonResponse = readResource(EXAMPLES + "error_plan_request.json");
        stubFor(post("/v1/billing/plans").willReturn(badRequest().withBody(jsonResponse)));
        var port = wmRuntimeInfo.getHttpPort();
        var uri = URI.create("http://localhost:" + port + "/v1/billing/plans");

        postAndCompare(uri, jsonRequest, 400, jsonResponse);
    }

    private static void postAndCompare(URI uri, String jsonRequest, int expected, String jsonResponse) throws JSONException {
        var request = Request.newBuilder().uri(uri)
                .POST(Request.BodyPublishers.ofString(jsonRequest)).build();
        var response = client.send(request, Response.BodyHandlers.ofString());
        Assertions.assertEquals(expected, response.statusCode());
        if (jsonResponse == null) {
            Assertions.assertNull(response.body());
            return;
        }
        JSONAssert.assertEquals(jsonResponse, response.body(), true);
    }

}
