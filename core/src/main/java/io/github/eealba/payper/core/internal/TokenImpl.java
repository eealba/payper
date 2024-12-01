package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.PayperToken;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class TokenImpl implements PayperToken {
    final Instant created;
    final String scope;
    final String accessToken;
    final String tokenType;
    final String appId;
    final int expiresIn;
    final String nonce;

    private TokenImpl(Builder builder) {
        this.accessToken = builder.accessToken;
        this.tokenType = builder.tokenType;
        this.scope = builder.scope;
        this.appId = builder.appId;
        this.nonce = builder.nonce;
        this.expiresIn = builder.expiresIn;
        this.created = parseNonce(this.nonce);
    }

    @Override
    public Instant created() {
        return created;
    }

    @Override
    public String scope() {
        return scope;
    }

    @Override
    public String accessToken() {
        return accessToken;
    }

    @Override
    public String tokenType() {
        return tokenType;
    }

    @Override
    public String appId() {
        return appId;
    }

    @Override
    public int expiresIn() {
        return expiresIn;
    }

    @Override
    public String nonce() {
        return nonce;
    }


    // generate Builder pattern
    public static class Builder {
        private String accessToken;
        private String tokenType;
        private String scope;
        private String appId;
        private String nonce;
        private int expiresIn;


        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder tokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public Builder nonce(String nonce) {
            this.nonce = nonce;
            return this;
        }

        public Builder expiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        public TokenImpl build() {
            return new TokenImpl(this);
        }
    }
    public static Instant parseNonce(String nonce) {
        // Extract the date-time part from the nonce
        String dateTimePart = nonce.substring(0, nonce.indexOf('Z') + 1);

        // Define the formatter for the date-time part
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

        // Parse the date-time part to an Instant
        TemporalAccessor temporalAccessor = formatter.parse(dateTimePart);
        return Instant.from(temporalAccessor);
    }

}
