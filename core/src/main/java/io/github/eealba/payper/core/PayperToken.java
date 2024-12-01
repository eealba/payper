package io.github.eealba.payper.core;

import java.time.Instant;

public interface PayperToken {
    Instant created();
    String scope();
    String accessToken();
    String tokenType();
    String appId();
    int expiresIn();
    String nonce();
    default boolean isValid() {
        return Instant.now().isBefore(created().plusSeconds(expiresIn()));
    }

}
