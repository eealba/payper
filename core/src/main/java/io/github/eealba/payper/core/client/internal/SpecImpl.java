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

import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.PayperResponse;
import io.github.eealba.payper.core.client.Spec;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * The type SpecImpl.
 * This class represents a request specification.
 * It contains the configuration of the request.
 * It is used to build a request.
 *
 * @param <T> the type parameter
 * @author Edgar Alba
 * @version 1.0
 * @see Spec
 * @see Payper
 * @see PayperResponse
 * @since 1.0
 */
class SpecImpl<T> implements Spec<T> {
    private final Payper payper;
    private final String path;
    private final Class<?> entityClass;
    private final Class<?> errorClass;
    private final Class<T> clazz;
    private final Map<String, String> alias;
    private final PayperResponse.BodyHandler<?> entityHandler;
    private final PayperResponse.BodyHandler<?> errorHandler;

    SpecImpl(SpecBuilder<T> builder) {
        this.payper = builder.payper;
        this.path = builder.path;
        this.entityClass = builder.entityClass;
        this.errorClass = builder.errorClass;
        this.clazz = builder.clazz;
        this.alias = builder.alias;
        this.entityHandler = builder.entityHandler;
        this.errorHandler = builder.errorHandler;
    }

    @Override
    public Class<T> clazz() {
        return clazz;
    }

    @Override
    public Payper payper() {
        return payper;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public Optional<Class<?>> entityClass() {
        return Optional.ofNullable(entityClass);
    }

    @Override
    public Optional<Class<?>> errorClass() {
        return Optional.ofNullable(errorClass);
    }

    /**
     * Gets the alias mapping for the request.
     *
     * @return the alias mapping
     */
    @Override
    public Optional<Map<String, String>> alias() {
        return Optional.ofNullable(alias);
    }

    /**
     * Gets the body handler for the entity.
     *
     * @return the entity body handler
     */
    @Override
    public Optional<PayperResponse.BodyHandler<?>> entityHandler() {
        return Optional.ofNullable(entityHandler);
    }

    /**
     * Gets the body handler for the error response.
     *
     * @return the error body handler
     */
    @Override
    public Optional<PayperResponse.BodyHandler<?>> errorHandler() {
        return Optional.ofNullable(errorHandler);
    }

    static class SpecBuilder<T> implements Spec.Builder<T> {
        private final Class<T> clazz;
        private final Payper payper;
        private final String path;
        public PayperResponse.BodyHandler<?> entityHandler;
        public PayperResponse.BodyHandler<?> errorHandler;
        private Class<?> entityClass;
        private Class<?> errorClass;
        private final Map<String, String> alias = new HashMap<>();

        SpecBuilder(Class<T> clazz, Payper payper, String path) {
            this.clazz = Objects.requireNonNull(clazz);
            this.payper = Objects.requireNonNull(payper);
            this.path = Objects.requireNonNull(path);
        }


        /**
         * Sets the class type of the entity in the response.
         *
         * @param entityClass the entity class type
         * @return the builder
         */
        @Override
        public Builder<T> entityClass(Class<?> entityClass) {
            this.entityClass = entityClass;
            return this;
        }

        /**
         * Sets the class type of the error response.
         *
         * @param errorClass the error class type
         * @return the builder
         */
        @Override
        public Builder<T> errorClass(Class<?> errorClass) {
            this.errorClass = errorClass;
            return this;
        }

        /**
         * Sets the alias mapping for the request.
         *
         * @param source the source alias
         * @param target the target alias
         * @return the builder
         */
        @Override
        public Builder<T> alias(String source, String target) {
            alias.put(source, target);
            return this;
        }


        /**
         * Sets the body handler for the entity.
         *
         * @param entityHandler the entity body handler
         * @return the builder
         */
        @Override
        public Builder<T> entityHandler(PayperResponse.BodyHandler<?> entityHandler) {
            this.entityHandler = entityHandler;
            return this;
        }

        /**
         * Sets the body handler for the error response.
         *
         * @param errorHandler the error body handler
         * @return the builder
         */
        @Override
        public Builder<T> errorHandler(PayperResponse.BodyHandler<?> errorHandler) {
            this.errorHandler = errorHandler;
            return this;
        }

        /**
         * Builds and returns the {@link Spec} instance.
         *
         * @return the built {@link Spec} instance
         */
        @Override
        public Spec<T> build() {
            return new SpecImpl<>(this);
        }
    }
}
