package io.github.eealba.payper.core.web;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
class RequestTest {

    @Test
    void uri() {
        var request = Request.newBuilder().uri(URI.create("http://localhost:8080")).GET().build();
        assertEquals(URI.create("http://localhost:8080"), request.uri());
    }

    @Test
    void method() {
        var request = Request.newBuilder().uri(URI.create("http://localhost:8080")).GET().build();
        assertEquals(Method.GET, request.method());
    }

    @Test
    void timeout() {
        var request = Request.newBuilder().uri(URI.create("http://localhost:8080")).timeout(Duration.ofSeconds(10))
                .GET().build();
        assertEquals(Duration.ofSeconds(10), request.timeout().orElseThrow());

    }

    @Test
    void bodyPublisherOfString() {
        var request = Request.newBuilder().uri(URI.create("http://localhost:8080"))
                .POST(Request.BodyPublishers.ofString("pepe")).build();
        assertNotNull(request.bodyPublisher().orElseThrow());

        assertEquals("pepe", new String(request.bodyPublisher().orElseThrow().get(), StandardCharsets.UTF_8));

    }
    @Test
    void bodyPublisherOfByteArray() {
        var request = Request.newBuilder().uri(URI.create("http://localhost:8080"))
                .POST(Request.BodyPublishers.ofByteArray("pepe".getBytes(StandardCharsets.UTF_8))).build();
        assertNotNull(request.bodyPublisher().orElseThrow());

        assertEquals("pepe", new String(request.bodyPublisher().orElseThrow().get(), StandardCharsets.UTF_8));

    }

    @Test
    void headerContentType() {
        var request = Request.newBuilder().uri(URI.create("http://localhost:8080"))
                .header("Content-Type", "application/json").GET().build();
        assertEquals("application/json", request.headers().getValue("Content-Type").orElseThrow());
    }
    @Test
    void headerContentType2() {
        var request = Request.newBuilder().uri(URI.create("http://localhost:8080"))
                .contentType(Request.ContentTypes.APPLICATION_JSON).GET().build();
        assertEquals("application/json", request.headers().getValue("Content-Type").orElseThrow());
    }

    @Test
    void headerAuthorization() {
        var request = Request.newBuilder().uri(URI.create("http://localhost:8080"))
                .authorization(Request.Authorizations.BEARER("qwerty")).GET().build();
        assertEquals("Bearer qwerty", request.headers().getValue("Authorization").orElseThrow());
    }

}