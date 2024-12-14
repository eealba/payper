package io.github.eealba.payper.core.web;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.noContent;
import static com.github.tomakehurst.wiremock.client.WireMock.notFound;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.core.Util.getUri;

@WireMockTest
class WebClientDeleteTest {
    private static WebClient client;

    @BeforeAll
    static void setup() {
        client = WebClient.create();
    }


    @Test
    void test_204_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, InterruptedException,
            JSONException {
        stubFor(delete("/v1/billing/plans").willReturn(noContent()));
        var uri = getUri(wmRuntimeInfo, "/v1/billing/plans");

        deleteAndCompare(uri, 204, false);
        deleteAndCompare(uri,  204, true);
    }
    @Test
    void test_404_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) throws IOException, JSONException {
        stubFor(delete("/v1/billing/plans").willReturn(notFound()));
        var uri = getUri(wmRuntimeInfo, "/v1/billing/plans");

        deleteAndCompare(uri, 404, false);
        deleteAndCompare(uri,  404, true);
    }

    private static void deleteAndCompare(URI uri, int expected, boolean async) {
        var request = Request.newBuilder().uri(uri).DELETE().build();
        var bodyHandler = Response.BodyHandlers.discarding();

        var response = async ? client.sendAsync(request, bodyHandler).join() : client.send(request, bodyHandler);

        Assertions.assertEquals(expected, response.statusCode());
    }

}
