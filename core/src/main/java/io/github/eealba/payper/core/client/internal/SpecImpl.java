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
import io.github.eealba.payper.core.client.PayperRequest;
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
 * @param <T1> the type of the request entity
 * @param <R1> the type of the response entity
 * @param <R2> the type of the error entity
 * @author Edgar Alba
 * @version 1.0
 * @see Spec
 * @see Payper
 * @see PayperResponse
 * @since 1.0
 */
class SpecImpl<T1, R1, R2> implements Spec<T1, R1, R2> {
    private final Payper payper;
    private final String path;
    private final Class<R1> entityClass;
    private final Class<R2> errorClass;
    private final Class<T1> clazz;
    private final Map<String, String> alias;
    private final PayperResponse.BodyHandler<R1> entityHandler;
    private final PayperResponse.BodyHandler<R2> errorHandler;
    private final PayperRequest.Method method;

    SpecImpl(SpecBuilder<T1, R1, R2> builder) {
        this.payper = builder.payper;
        this.path = builder.path;
        this.entityClass = builder.entityClass;
        this.errorClass = builder.errorClass;
        this.clazz = builder.clazz;
        this.alias = builder.alias;
        this.entityHandler = Optional.ofNullable(builder.entityHandler)
                .orElseGet(() -> PayperResponse.BodyHandlers.ofClass(entityClass));
        this.errorHandler = Optional.ofNullable(builder.errorHandler)
                .orElseGet(() -> PayperResponse.BodyHandlers.ofClass(errorClass));
        this.method = builder.method;
    }

    @Override
    public Class<T1> clazz() {
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
    public PayperResponse.BodyHandler<R1> entityHandler() {
        return entityHandler;
    }

    /**
     * Gets the body handler for the error response.
     *
     * @return the error body handler
     */
    @Override
    public PayperResponse.BodyHandler<R2> errorHandler() {
        return errorHandler;
    }

    /**
     * Gets the method of the request.
     *
     * @return the method of the request
     */
    @Override
    public PayperRequest.Method getMethod() {
        return method;
    }

    static class SpecBuilder<T1, R1, R2> implements Spec.Builder<T1, R1, R2> {
        private final Class<T1> clazz;
        private final Payper payper;
        private final String path;
        private final Class<R1> entityClass;
        private final Class<R2> errorClass;
        private final Map<String, String> alias = new HashMap<>();
        public PayperResponse.BodyHandler<R1> entityHandler;
        public PayperResponse.BodyHandler<R2> errorHandler;
        public PayperRequest.Method method = PayperRequest.Method.GET;

        SpecBuilder(Class<T1> clazz, Payper payper, String path, Class<R1> entityClass, Class<R2> errorClass) {
            this.clazz = Objects.requireNonNull(clazz);
            this.payper = Objects.requireNonNull(payper);
            this.path = Objects.requireNonNull(path);
            this.entityClass = entityClass;
            this.errorClass = errorClass;
        }


        /**
         * Sets the alias mapping for the request.
         *
         * @param source the source alias
         * @param target the target alias
         * @return the builder
         */
        @Override
        public Builder<T1, R1, R2> alias(String source, String target) {
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
        public Builder<T1, R1, R2> entityHandler(PayperResponse.BodyHandler<R1> entityHandler) {
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
        public Builder<T1, R1, R2> errorHandler(PayperResponse.BodyHandler<R2> errorHandler) {
            this.errorHandler = errorHandler;
            return this;
        }

        /**
         * Sets the method of the request.
         *
         * @param method the method of the request
         * @return the builder
         */
        @Override
        public Builder<T1, R1, R2> method(PayperRequest.Method method) {
            this.method = method;
            return this;
        }

        /**
         * Builds and returns the {@link Spec} instance.
         *
         * @return the built {@link Spec} instance
         */
        @Override
        public Spec<T1, R1, R2> build() {
            return new SpecImpl<>(this);
        }
    }
}
