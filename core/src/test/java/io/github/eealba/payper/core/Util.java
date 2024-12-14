package io.github.eealba.payper.core;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Util {

    public static String readResource(String path) throws IOException {
        try (var inputStream = Util.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }
    public static URI getUri(WireMockRuntimeInfo wmRuntimeInfo, String path) {
        var port = wmRuntimeInfo.getHttpPort();
        return URI.create("http://localhost:" + port + path);
    }

}
