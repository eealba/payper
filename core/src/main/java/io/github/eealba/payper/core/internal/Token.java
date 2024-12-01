package io.github.eealba.payper.core.internal;

import lombok.Getter;

@Getter
class Token {
    final long created;
    final String accessToken;
    final String tokenType;
    final String scope;
    final String appId;
    final String nonce;
    final int expiresIn;

    private Token(Builder builder) {
        this.created = builder.created;
        this.accessToken = builder.accessToken;
        this.tokenType = builder.tokenType;
        this.scope = builder.scope;
        this.appId = builder.appId;
        this.nonce = builder.nonce;
        this.expiresIn = builder.expiresIn;
    }

    boolean isValid() {
        return (System.currentTimeMillis() < this.created + (expiresIn * 1000L));
    }

    // generate Builder pattern
    static class Builder {
        private long created;
        private String accessToken;
        private String tokenType;
        private String scope;
        private String appId;
        private String nonce;
        private int expiresIn;

        Builder created(long created) {
            this.created = created;
            return this;
        }

        Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        Builder tokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        Builder appId(String appId) {
            this.appId = appId;
            return this;
        }

        Builder nonce(String nonce) {
            this.nonce = nonce;
            return this;
        }

        Builder expiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        Token build() {
            return new Token(this);
        }
    }
}
