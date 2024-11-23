package io.github.eealba.payper.core.internal;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.badRequest;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@WireMockTest
public class HttpWebClientTest {
    private static final String EXAMPLES = "/examples/";
    private static HttpClient client;

    @BeforeAll
    static void setup() {
        client = HttpClient.newBuilder().build();
    }


    @Test
    void test_post_ok_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, InterruptedException,
            JSONException {
        var jsonRequest = readResource(EXAMPLES + "plan_request_POST.json");
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        // The static DSL will be automatically configured for you
        stubFor(post("/v1/billing/plans").willReturn(ok(jsonResponse)));

        // Info such as port numbers is also available
        var port = wmRuntimeInfo.getHttpPort();

        // Do some testing...
        var uri = URI.create("http://localhost:" + port + "/v1/billing/plans");
        postAndCompare(uri, jsonRequest, 200, jsonResponse);
    }
    @Test
    void test_get_ok_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, InterruptedException,
            JSONException {
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        // The static DSL will be automatically configured for you
        stubFor(get("/v1/billing/plans/1").willReturn(ok(jsonResponse)));

        // Info such as port numbers is also available
        var port = wmRuntimeInfo.getHttpPort();

        // Do some testing...
        var uri = URI.create("http://localhost:" + port + "/v1/billing/plans/1");
        getAndCompare(uri, 200, jsonResponse);
    }

    @Test
    void test_post_400_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, InterruptedException,
            JSONException {
        var jsonRequest = readResource(EXAMPLES + "plan_request_POST.json");
        var jsonResponse = readResource(EXAMPLES + "error_plan_request.json");
        // The static DSL will be automatically configured for you
        stubFor(post("/v1/billing/plans").willReturn(badRequest().withBody(jsonResponse)));

        // Info such as port numbers is also available
        var port = wmRuntimeInfo.getHttpPort();

        // Do some testing...
        var uri = URI.create("http://localhost:" + port + "/v1/billing/plans");
        postAndCompare(uri, jsonRequest, 400, jsonResponse);
    }

    private static void postAndCompare(URI uri, String jsonRequest, int expected, String jsonResponse) throws IOException, InterruptedException, JSONException {
        var httpRequest = HttpRequest.newBuilder().uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest)).build();
        var httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(expected, httpResponse.statusCode());
        JSONAssert.assertEquals(jsonResponse, httpResponse.body(), true);
    }

    private static void getAndCompare(URI uri, int expected, String jsonResponse) throws IOException, InterruptedException, JSONException {
        var httpRequest = HttpRequest.newBuilder().uri(uri)
                .GET().build();
        var httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(expected, httpResponse.statusCode());
        JSONAssert.assertEquals(jsonResponse, httpResponse.body(), true);
    }

    private static String readResource(String path) throws IOException {
        try (var inputStream = HttpWebClientTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
