package io.github.eealba.payper.invoices.v2;

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class Util {
    private static final Jasoner JASONER = JasonerBuilder.create();

    static String readResource(String path) throws IOException {
        try (var inputStream = Util.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }
    static void execute(String resourceControl, Class<?> clazz) throws IOException, JSONException {
        String control = readResource(resourceControl);
        var pojo = JASONER.fromJson(control, clazz);
        var json = JASONER.toJson(pojo);

        JSONAssert.assertEquals(control, json, true);
    }
}
