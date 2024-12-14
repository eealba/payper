package io.github.eealba.payper.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PayperConfigTest {

    @org.junit.jupiter.api.Test
    void defaultPayperConfig() {
        var config = PayperConfig.builder().build();
        assertFalse(config.connectTimeout().isPresent());
        assertFalse(config.executor().isPresent());
        assertNotNull(config.authenticator());
        assertFalse(config.proxySelector().isPresent());
    }

    @org.junit.jupiter.api.Test
    void payperConfigWithValues() {
        var config = PayperConfig.builder()
                .authenticator(PayperAuthenticator.PayperAuthenticators.getDefault())
                .executor(Runnable::run)
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .proxySelector(java.net.ProxySelector.getDefault())
                .build();
        assertTrue(config.connectTimeout().isPresent());
        assertTrue(config.executor().isPresent());
        assertNotNull(config.authenticator());
        assertTrue(config.proxySelector().isPresent());
    }

    @org.junit.jupiter.api.Test
    void payperConfigWithNullValues() {
        var config = PayperConfig.builder()
                .executor(null)
                .connectTimeout(null)
                .proxySelector(null)
                .build();
        assertFalse(config.connectTimeout().isPresent());
        assertFalse(config.executor().isPresent());
        assertNotNull(config.authenticator());
        assertFalse(config.proxySelector().isPresent());
    }

}