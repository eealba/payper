package io.github.eealba.payper.core.web;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class Util {

    static String readResource(String path) throws IOException {
        try (var inputStream = WebClientPostTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }

}
