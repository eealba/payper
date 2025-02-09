package io.github.eealba.payper.orders.v2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class Util {

    static String readResource(String path) throws IOException {
        try (var inputStream = Util.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }

}
