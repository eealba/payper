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
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.badRequest;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
@WireMockTest

class WebClientTest {
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
        // The static DSL will be automatically configured for you
        stubFor(post("/v1/billing/plans").willReturn(ok(jsonResponse)));

        // Info such as port numbers is also available
        var port = wmRuntimeInfo.getHttpPort();

        // Do some testing...
        var uri = URI.create("http://localhost:" + port + "/v1/billing/plans");
        executeAndCompare(uri, jsonRequest, 200, jsonResponse);
    }

    @Test
    void test_400_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, InterruptedException,
            JSONException {
        var jsonRequest = readResource(EXAMPLES + "plan_request_POST.json");
        var jsonResponse = readResource(EXAMPLES + "error_plan_request.json");
        // The static DSL will be automatically configured for you
        stubFor(post("/v1/billing/plans").willReturn(badRequest().withBody(jsonResponse)));

        // Info such as port numbers is also available
        var port = wmRuntimeInfo.getHttpPort();

        // Do some testing...
        var uri = URI.create("http://localhost:" + port + "/v1/billing/plans");
        executeAndCompare(uri, jsonRequest, 400, jsonResponse);
    }

    private static void executeAndCompare(URI uri, String jsonRequest, int expected, String jsonResponse) throws IOException, InterruptedException, JSONException {
        var request = Request.newBuilder().uri(uri)
                .POST(Request.BodyPublishers.ofString(jsonRequest)).build();
        var response = client.send(request, Response.BodyHandlers.ofString());
        Assertions.assertEquals(expected, response.statusCode());
        JSONAssert.assertEquals(jsonResponse, response.body(), true);
    }


    private static String readResource(String path) throws IOException {
        try (var inputStream = WebClientTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
