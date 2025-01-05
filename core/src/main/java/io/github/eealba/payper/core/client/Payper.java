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
package io.github.eealba.payper.core.client;

/**
 * The interface Payper.
 * This interface is used to handle requests in the Payper library.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Payper payper = Payper.newPayper();
 * PayperRequest request = PayperRequest.newBuilder()
 *     .uri(new URI("http://example.com"))
 *     .header("Content-Type", "application/json")
 *     .GET()
 *     .build();
 * PayperResponse.PayperResponseSpec<String, String> responseSpec = payper.send(request,
 *     PayperResponse.BodyHandlers.ofClass(String.class),
 *     PayperResponse.BodyHandlers.ofClass(String.class));
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @see PayperProvider
 * @see PayperConfig
 * @see PayperRequest
 * @see PayperResponse
 * @see PayperResponse.PayperResponseSpec
 * @see PayperAuthenticator
 * @see PayperException
 * @author Edgar Alba
 */
public abstract class Payper {

    /**
     * Creates a new Payper instance with the default configuration.
     *
     * @return the Payper instance
     */
    public static Payper create() {
        return create(PayperConfig.builder().build());
    }

    /**
     * Creates a new Payper instance with the specified configuration.
     *
     * @param config the Payper configuration
     * @return the Payper instance
     */
    public static Payper create(PayperConfig config) {
        return PayperProvider.provider().createPayper(config);
    }

    /**
     * Sends a request and returns a response specification.
     *
     * @param <R1> the type of the entity in the response
     * @param <R2> the type of the error entity in the response
     * @param request the request
     * @param bodyHandler the body handler for the response entity
     * @param bodyHandler2 the body handler for the error entity
     * @return the response specification
     */
    public abstract <R1, R2> PayperResponse.PayperResponseSpec<R1, R2> send(PayperRequest request,
                                     PayperResponse.BodyHandler<R1> bodyHandler,
                                     PayperResponse.BodyHandler<R2> bodyHandler2);
}