package io.github.eealba.payper.payments.v2.internal;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;
import io.github.eealba.payper.payments.v2.api.PaymentsProvider;

public class PaymentsProviderImpl extends PaymentsProvider {
    /**
     * Creates a new PaymentsApiClient instance using the specified configuration.
     *
     * @param config the configuration to be used
     * @return a new PaymentsApiClient instance
     */
    @Override
    public PaymentsApiClient create(PayperConfig config) {
        return new PaymentsApiClientImpl(config) {
        };
    }
}
