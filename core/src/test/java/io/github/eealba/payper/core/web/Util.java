package io.github.eealba.payper.core.web;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class Util {

    static String readResource(String path) throws IOException {
        try (var inputStream = WebClientPostTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }
    static URI getUri(WireMockRuntimeInfo wmRuntimeInfo, String path) {
        var port = wmRuntimeInfo.getHttpPort();
        return URI.create("http://localhost:" + port + path);
    }

}
