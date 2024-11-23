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

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.notFound;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.core.web.Util.readResource;

@WireMockTest

class WebClientGetTest {
    private static final String EXAMPLES = "/examples/";
    private static WebClient client;

    @BeforeAll
    static void setup() {
        client = WebClient.newWebClient();
    }

    @Test
    void test_get_ok_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, InterruptedException,
            JSONException {
        var jsonResponse = readResource(EXAMPLES + "plan.json");
        stubFor(get("/v1/billing/plans/1").willReturn(ok(jsonResponse)));
        var port = wmRuntimeInfo.getHttpPort();
        var uri = URI.create("http://localhost:" + port + "/v1/billing/plans/1");

        getAndCompare(uri, 200, jsonResponse);
    }
    @Test
    void test_get_not_found_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, InterruptedException,
            JSONException {
        stubFor(get("/v1/billing/plans/1").willReturn(notFound()));
        var port = wmRuntimeInfo.getHttpPort();
        var uri = URI.create("http://localhost:" + port + "/v1/billing/plans/1");
        var request = Request.newBuilder().uri(uri).GET().build();

        getAndCompare(uri, 404, null);

    }
    private static void getAndCompare(URI uri, int expected, String jsonResponse) throws JSONException {
        var request = Request.newBuilder().uri(uri).GET().build();
        var response = client.send(request, Response.BodyHandlers.ofString());
        Assertions.assertEquals(expected, response.statusCode());

        if (jsonResponse != null) {
            JSONAssert.assertEquals(jsonResponse, response.body(), true);
        }
    }
}
