package io.github.eealba.payper.core.client;

import java.util.Map;
import java.util.Optional;

/**
 * Interface representing a specification for a request.
 *
 * @param <T1> the type of the request specification
 */
public interface Spec<T1, R1, R2> {
    /**
     * Gets the class type of the request specification.
     *
     * @return the class type
     */
    Class<T1> clazz();

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
    PayperResponse.BodyHandler<R1> entityHandler();

    /**
     * Gets the body handler for the error response.
     *
     * @return the error body handler
     */
    PayperResponse.BodyHandler<R2> errorHandler();


    /**
     * Gets the method of the request.
     *
     * @return the method of the request
     */
    PayperRequest.Method getMethod();

    /**
     * Builder interface for constructing instances of {@link Spec}.
     *
     * @param <T1> the type of the request specification
     */
    interface Builder<T1, R1, R2> {


        /**
         * Sets the alias mapping for the request.
         *
         * @param source the source alias
         * @param target the target alias
         * @return the builder
         */
        Builder<T1, R1, R2> alias(String source, String target);

        /**
         * Sets the body handler for the entity.
         *
         * @param entityHandler the entity body handler
         * @return the builder
         */
        Builder<T1, R1, R2> entityHandler(PayperResponse.BodyHandler<R1> entityHandler);

        /**
         * Sets the body handler for the error response.
         *
         * @param errorHandler the error body handler
         * @return the builder
         */
        Builder<T1, R1, R2> errorHandler(PayperResponse.BodyHandler<R2> errorHandler);

        /**
         * Sets the method of the request.
         *
         * @param method the method of the request
         * @return the builder
         */
        Builder<T1, R1, R2> method(PayperRequest.Method method);

        /**
         * Builds and returns the {@link Spec} instance.
         *
         * @return the built {@link Spec} instance
         */
        Spec<T1, R1, R2> build();
    }
}