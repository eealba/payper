/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.payper.core.client.internal;

import io.github.eealba.payper.core.client.PayperToken;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Implementation of the PayperToken interface.
 * This class represents a token with various attributes.
 *
 * @author Edgar Alba
 * @since 1.0
 */
class TokenImpl implements PayperToken {
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

    /**
     * Builder class for creating TokenImpl instances.
     */
    static class Builder {
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

    /**
     * Parses the nonce to extract the creation time.
     *
     * @param nonce the nonce
     * @return the creation time as an Instant
     */
    static Instant parseNonce(String nonce) {
        String dateTimePart = nonce.substring(0, nonce.indexOf('Z') + 1);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        TemporalAccessor temporalAccessor = formatter.parse(dateTimePart);
        return Instant.from(temporalAccessor);
    }
}