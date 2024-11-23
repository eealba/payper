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

import io.github.eealba.payper.core.Credential;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executor;

/**
 * The type Payper config.
 * This class is used to store the configuration of the Payper client.
 *
 * @since 1.0
 * @version 1.0
 *
 * @see Object
 * @see Credential
 * @see Executor
 * @see Duration
 *
 * @author Edgar Alba
 */
public class WebClientConfig {
    private final Executor executor;
    private final Duration connectTimeout;
    /**
     * Instantiates a new Payper config with a builder.
     *
     * @param builder the builder
     */
    private WebClientConfig(Builder builder) {
        this.executor = builder.executor;
        this.connectTimeout = builder.connectTimeout;
    }
    /**
     * Executor.
     *
     * @return the optional
     */
    public Optional<Executor> executor() {
        return Optional.ofNullable(executor);
    }
    /**
     * Connect timeout.
     *
     * @return the optional
     */
    public Optional<Duration> connectTimeout() {
        return Optional.ofNullable(connectTimeout);
    }
    /**
     * Builder builder.
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

        /**
         * Executor builder.
         *
         * @param executor the executor
         * @return the builder
         */
        public Builder executor(Executor executor) {
            this.executor = executor;
            return this;
        }
        /**
         * Connect timeout builder.
         *
         * @param connectTimeout the connect timeout
         * @return the builder
         */
        public Builder connectTimeout(Duration connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }
        /**
         * Build payper config.
         *
         * @return the payper config
         */
        public WebClientConfig build() {
            return new WebClientConfig(this);
        }
    }
}
