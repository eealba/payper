package io.github.eealba.payper.webhooks.v1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Util {

    public static String readResource(String path) throws IOException {
        try (var inputStream = Util.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }

}
