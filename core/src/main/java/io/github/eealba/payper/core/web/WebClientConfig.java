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
package io.github.eealba.payper.core.web;

import java.net.ProxySelector;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executor;

/**
 * The type WebClientConfig.
 * This class is used to store the configuration of the WebClient.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * WebClientConfig config = WebClientConfig.builder()
 *     .executor(Executors.newFixedThreadPool(10))
 *     .connectTimeout(Duration.ofSeconds(10))
 *     .proxySelector(ProxySelector.getDefault())
 *     .build();
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 *
 * @see Object
 * @see Executor
 * @see Duration
 * @see ProxySelector
 */
public class WebClientConfig {
    public static final WebClientConfig DEFAULT = new WebClientConfig.Builder().build();
    private final Executor executor;
    private final Duration connectTimeout;
    private final ProxySelector proxySelector;

    /**
     * Instantiates a new WebClientConfig with a builder.
     *
     * @param builder the builder
     */
    private WebClientConfig(Builder builder) {
        this.executor = builder.executor;
        this.connectTimeout = builder.connectTimeout;
        this.proxySelector = builder.proxySelector;
    }

    /**
     * Gets the executor.
     *
     * @return the optional executor
     */
    public Optional<Executor> executor() {
        return Optional.ofNullable(executor);
    }

    /**
     * Gets the connect timeout.
     *
     * @return the optional connect timeout
     */
    public Optional<Duration> connectTimeout() {
        return Optional.ofNullable(connectTimeout);
    }

    /**
     * Gets the proxy selector.
     *
     * @return the optional proxy selector
     */
    public Optional<ProxySelector> proxySelector() {
        return Optional.ofNullable(proxySelector);
    }

    /**
     * Creates a new builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private Executor executor;
        private Duration connectTimeout;
        public ProxySelector proxySelector;

        /**
         * Sets the executor.
         *
         * @param executor the executor
         * @return the builder
         */
        public Builder executor(Executor executor) {
            this.executor = executor;
            return this;
        }

        /**
         * Sets the connect timeout.
         *
         * @param connectTimeout the connect timeout
         * @return the builder
         */
        public Builder connectTimeout(Duration connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        /**
         * Sets the proxy selector.
         *
         * @param proxySelector the proxy selector
         * @return the builder
         */
        public Builder proxySelector(ProxySelector proxySelector) {
            this.proxySelector = proxySelector;
            return this;
        }

        /**
         * Builds the WebClientConfig.
         *
         * @return the WebClientConfig
         */
        public WebClientConfig build() {
            return new WebClientConfig(this);
        }
    }
}