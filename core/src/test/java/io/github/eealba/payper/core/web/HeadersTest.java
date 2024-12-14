package io.github.eealba.payper.core.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeadersTest {
    private Headers headers;

    @BeforeEach
    void setup() {
        Map<String, String> values = Map.of("Content-Type", "application/json",
                "Accept", "application/json",
                "Authorization", "Bearer token");
        headers = new Headers(values);
    }

    @Test
    void getValue() {
        var contentType = headers.getValue("Content-Type");
        assertTrue(contentType.isPresent());
        assertEquals("application/json", contentType.get());

        var accept = headers.getValue("Accept");
        assertTrue(accept.isPresent());
        assertEquals("application/json", accept.get());

        var authorization = headers.getValue("Authorization");
        assertTrue(authorization.isPresent());
        assertEquals("Bearer token", authorization.get());

        var notPresent = headers.getValue("Not-Present");
        assertTrue(notPresent.isEmpty());

    }

    @Test
    void toArray() {
        var array = headers.toArray();
        assertEquals(6, array.length);
    }
}