package io.github.eealba.payper.core.client;

import java.util.Map;
import java.util.Optional;

/**
 * Interface representing a specification for a request.
 *
 * @param <T> the type of the request specification
 */
public interface Spec<T> {
    /**
     * Gets the class type of the request specification.
     *
     * @return the class type
     */
    Class<T> clazz();

    /**
     * Gets the Payper instance associated with the request.
     *
     * @return the Payper instance
     */
    Payper payper();

    /**
     * Gets the path of the request.
     *
     * @return the request path
     */
    String path();

    /**
     * Gets the class type of the entity.
     *
     * @return the entity class type
     */
    Optional<Class<?>> entityClass();

    /**
     * Gets the class type of the error response.
     *
     * @return the error class type
     */
    Optional<Class<?>> errorClass();

    /**
     * Gets the alias mapping for the request.
     *
     * @return the alias mapping
     */
    Optional<Map<String, String>> alias();

    /**
     * Gets the body handler for the entity.
     *
     * @return the entity body handler
     */
    Optional<PayperResponse.BodyHandler<?>> entityHandler();

    /**
     * Gets the body handler for the error response.
     *
     * @return the error body handler
     */
    Optional<PayperResponse.BodyHandler<?>> errorHandler();

    /**
     * Builder interface for constructing instances of {@link Spec}.
     *
     * @param <T> the type of the request specification
     */
    interface Builder<T> {



        /**
         * Sets the class type of the entity in the response.
         *
         * @param entityClass the entity class type
         * @return the builder
         */
        Builder<T> entityClass(Class<?> entityClass);

        /**
         * Sets the class type of the error response.
         *
         * @param errorClass the error class type
         * @return the builder
         */
        Builder<T> errorClass(Class<?> errorClass);
        /**
         * Sets the alias mapping for the request.
         *
         * @param source the source alias
         * @param target the target alias
         * @return the builder
         */
        Builder<T> alias(String source, String target);

        /**
         * Sets the body handler for the entity.
         *
         * @param entityHandler the entity body handler
         * @return the builder
         */
        Builder<T> entityHandler(PayperResponse.BodyHandler<?> entityHandler);

        /**
         * Sets the body handler for the error response.
         *
         * @param errorHandler the error body handler
         * @return the builder
         */
        Builder<T> errorHandler(PayperResponse.BodyHandler<?> errorHandler);

        /**
         * Builds and returns the {@link Spec} instance.
         *
         * @return the built {@link Spec} instance
         */
        Spec<T> build();
    }
}