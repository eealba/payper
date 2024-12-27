package io.github.eealba.payper.orders.v2.api;

import io.github.eealba.payper.core.PayperConfig;

public abstract class CheckoutOrdersApiClient {

    /**
     * Creates a new OrdersApiClient instance using the default configuration.
     * <p>
     * This method initializes the client with the default settings and credentials
     * required for communicating with the PayPal Orders API.
     *
     * @return a new OrdersApiClient instance
     */
    public static CheckoutOrdersApiClient create() {
        return create(PayperConfig.builder().build());
    }

    /**
     * Creates a new OrdersApiClient instance using the specified configuration.
     * <p>
     * This method allows for custom configuration of the client, including setting
     * specific credentials and other settings required for authenticating and
     * communicating with the PayPal Orders API.
     *
     * @param config the configuration to be used
     * @return a new OrdersApiClient instance
     */
    public static CheckoutOrdersApiClient create(PayperConfig config) {
        return CheckoutOrdersProvider.provider().create(config);
    }

    public abstract Orders orders();

}
