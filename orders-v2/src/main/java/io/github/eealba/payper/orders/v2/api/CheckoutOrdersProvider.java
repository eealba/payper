package io.github.eealba.payper.orders.v2.api;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.util.Providers;

public abstract class CheckoutOrdersProvider {
    private static final String DEFAULT = "io.github.eealba.payper.orders.v2.internal.OrdersProviderImpl";

    /**
     * Constructs a new OrdersProvider.
     */
    public CheckoutOrdersProvider() {
    }

    /**
     * Returns the default OrdersProvider instance.
     *
     * @return the default OrdersProvider instance
     */
    public static CheckoutOrdersProvider provider() {
        return Providers.getProvider(CheckoutOrdersProvider.class, DEFAULT);
    }

    /**
     * Creates a new OrdersApiClient instance using the specified configuration.
     *
     * @param config the configuration to be used
     * @return a new OrdersApiClient instance
     */
    public abstract CheckoutOrdersApiClient create(PayperConfig config);

}
