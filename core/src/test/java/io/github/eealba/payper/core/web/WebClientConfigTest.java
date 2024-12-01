package io.github.eealba.payper.core.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WebClientConfigTest {
    @Test
    void defaultWebClientConfig() {
        var config = WebClientConfig.builder().build();
        assertFalse(config.connectTimeout().isPresent());
        assertFalse(config.executor().isPresent());
        assertFalse(config.proxySelector().isPresent());
    }
    @Test
    void webClientConfigWithValues() {
        var config = WebClientConfig.builder()
                .executor(Runnable::run)
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .proxySelector(java.net.ProxySelector.getDefault())
                .build();
        assertTrue(config.connectTimeout().isPresent());
        assertTrue(config.executor().isPresent());
        assertTrue(config.proxySelector().isPresent());
    }

}