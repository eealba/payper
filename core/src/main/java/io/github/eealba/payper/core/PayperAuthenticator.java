package io.github.eealba.payper.core;

import java.util.Optional;
import java.util.function.Supplier;

public interface PayperAuthenticator {

    String getBaseUrl();
    char[] getClientId();
    char[] getClientSecret();

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
                    .or(() -> Optional.ofNullable(System.getenv(key))).orElseThrow().toCharArray();
        }
        private static Supplier<String> baseUrlSupplier() {
            return () -> Optional.ofNullable(System.getProperty(PAYPAL_BASE_URL))
                    .orElseGet(() -> Optional.ofNullable(System.getenv(PAYPAL_BASE_URL)).orElse(API_PAYPAL_COM));
        }

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

        public static PayperAuthenticator ofSandBox(Supplier<char[]> clientId, Supplier<char[]> clientSecret) {
            return of(() -> API_SANDBOX_PAYPAL_COM, clientId, clientSecret);
        }
        public static PayperAuthenticator ofSandBox() {
            return ofSandBox(CLIENT_ID,CLIENT_SECRET);

        }
        public static PayperAuthenticator ofLive(Supplier<char[]> clientId, Supplier<char[]> clientSecret) {
            return of(() -> API_PAYPAL_COM, clientId, clientSecret);
        }

        public static PayperAuthenticator ofLive() {
            return ofLive(CLIENT_ID,CLIENT_SECRET);
        }
        public static void setDefault(PayperAuthenticator authenticator) {
            DEFAULT = authenticator;

        }
        public static PayperAuthenticator getDefault() {
            return DEFAULT != null ? DEFAULT : of(BASE_URL, CLIENT_ID, CLIENT_SECRET);
        }
    }
}
