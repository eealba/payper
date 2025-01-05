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

import java.util.Optional;
import java.util.function.Supplier;

/**
 * The interface PayperAuthenticator.
 * This interface represents an authenticator for the Payper framework.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * PayperAuthenticator authenticator = PayperAuthenticator.PayperAuthenticators.getDefault();
 * String baseUrl = authenticator.getBaseUrl();
 * char[] clientId = authenticator.getClientId();
 * char[] clientSecret = authenticator.getClientSecret();
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 * @see PayperConfig
 * @see PayperException
 * @see PayperProvider
 */
public interface PayperAuthenticator {

    /**
     * Gets the base URL for the Payper API.
     *
     * @return the base URL
     */
    String getBaseUrl();

    /**
     * Gets the client ID for the Payper API.
     *
     * @return the client ID
     */
    char[] getClientId();

    /**
     * Gets the client secret for the Payper API.
     *
     * @return the client secret
     */
    char[] getClientSecret();

    /**
     * The class PayperAuthenticators.
     * This class provides factory methods for creating PayperAuthenticator instances.
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * PayperAuthenticator authenticator = PayperAuthenticator.PayperAuthenticators.ofLive();
     * String baseUrl = authenticator.getBaseUrl();
     * char[] clientId = authenticator.getClientId();
     * char[] clientSecret = authenticator.getClientSecret();
     * }</pre>
     *
     * @since 1.0
     * @version 1.0
     * @see PayperAuthenticator
     */
    class PayperAuthenticators {

        public static final String PAYPAL_CLIENT_ID = "PAYPAL-CLIENT-ID";
        public static final String PAYPAL_CLIENT_SECRET = "PAYPAL-CLIENT-SECRET";
        public static final String PAYPAL_BASE_URL = "PAYPAL-BASE-URL";
        public static final String API_SANDBOX_PAYPAL_COM = "https://api-m.sandbox.paypal.com";
        public static final String API_PAYPAL_COM = "https://api-m.paypal.com";

        private static final Supplier<String> BASE_URL = baseUrlSupplier();
        private static final Supplier<char[]> CLIENT_ID = systemSupplier(PAYPAL_CLIENT_ID);
        private static final Supplier<char[]> CLIENT_SECRET = systemSupplier(PAYPAL_CLIENT_SECRET);
        private static PayperAuthenticator DEFAULT;

        private static Supplier<char[]> systemSupplier(String key) {
            return () -> Optional.ofNullable(System.getProperty(key))
                    .or(() -> Optional.ofNullable(System.getenv(key)))
                    .orElseThrow(() -> new PayperException("Not found environment variable or System property: " + key))
                    .toCharArray();
        }

        private static Supplier<String> baseUrlSupplier() {
            return () -> Optional.ofNullable(System.getProperty(PAYPAL_BASE_URL))
                    .orElseGet(() -> Optional.ofNullable(System.getenv(PAYPAL_BASE_URL)).orElse(API_PAYPAL_COM));
        }

        /**
         * Creates a PayperAuthenticator with the specified base URL, client ID, and client secret.
         *
         * @param baseUrl the base URL supplier
         * @param clientId the client ID supplier
         * @param clientSecret the client secret supplier
         * @return the PayperAuthenticator
         */
        public static PayperAuthenticator of(Supplier<String> baseUrl,
                                             Supplier<char[]> clientId,
                                             Supplier<char[]> clientSecret) {
            return new PayperAuthenticator() {
                @Override
                public String getBaseUrl() {
                    return baseUrl.get();
                }

                @Override
                public char[] getClientId() {
                    return clientId.get();
                }

                @Override
                public char[] getClientSecret() {
                    return clientSecret.get();
                }
            };
        }

        /**
         * Creates a sandbox PayperAuthenticator with the specified client ID and client secret.
         *
         * @param clientId the client ID supplier
         * @param clientSecret the client secret supplier
         * @return the PayperAuthenticator
         */
        public static PayperAuthenticator ofSandBox(Supplier<char[]> clientId, Supplier<char[]> clientSecret) {
            return of(() -> API_SANDBOX_PAYPAL_COM, clientId, clientSecret);
        }

        /**
         * Creates a sandbox PayperAuthenticator with default client ID and client secret.
         *
         * @return the PayperAuthenticator
         */
        public static PayperAuthenticator ofSandBox() {
            return ofSandBox(CLIENT_ID, CLIENT_SECRET);
        }

        /**
         * Creates a live PayperAuthenticator with the specified client ID and client secret.
         *
         * @param clientId the client ID supplier
         * @param clientSecret the client secret supplier
         * @return the PayperAuthenticator
         */
        public static PayperAuthenticator ofLive(Supplier<char[]> clientId, Supplier<char[]> clientSecret) {
            return of(() -> API_PAYPAL_COM, clientId, clientSecret);
        }

        /**
         * Creates a live PayperAuthenticator with default client ID and client secret.
         *
         * @return the PayperAuthenticator
         */
        public static PayperAuthenticator ofLive() {
            return ofLive(CLIENT_ID, CLIENT_SECRET);
        }

        /**
         * Sets the default PayperAuthenticator.
         *
         * @param authenticator the default authenticator
         */
        public static void setDefault(PayperAuthenticator authenticator) {
            DEFAULT = authenticator;
        }

        /**
         * Gets the default PayperAuthenticator.
         *
         * @return the default authenticator
         */
        public static PayperAuthenticator getDefault() {
            return DEFAULT != null ? DEFAULT : of(BASE_URL, CLIENT_ID, CLIENT_SECRET);
        }
    }
}