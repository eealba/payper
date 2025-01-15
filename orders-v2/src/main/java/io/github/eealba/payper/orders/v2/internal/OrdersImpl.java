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
package io.github.eealba.payper.orders.v2.internal;

import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.orders.v2.api.Orders;
import io.github.eealba.payper.orders.v2.model.ErrorDefault;
import io.github.eealba.payper.orders.v2.model.Order;
import io.github.eealba.payper.orders.v2.model.OrderAuthorizeResponse;


class OrdersImpl implements Orders {
    private final Payper payper;

    OrdersImpl(Payper payper) {
        this.payper = payper;
    }

    /**
     * Creates an Order.
     * <p>
     * This method initializes a request to create a new order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body,
     * specifying the preferred return representation, and setting a PayPal request ID.
     *
     * @return the create order request specification
     */
    @Override
    public CreateOrder create() {
        var spec = PayperProvider.provider().createSpecBuilder(CreateOrder.class, payper, "/v2/checkout/orders")
                .entityClass(Order.class)
                .errorClass(ErrorDefault.class).build();
        return RequestSpecsFactory.getInstance().post(spec);
    }

    /**
     * Gets an order.
     * <p>
     * This method initializes a request to retrieve a specific order from the PayPal Orders API
     * by its ID.
     *
     * @return the get order request specification
     */
    @Override
    public GetOrder get() {
        var spec = PayperProvider.provider().createSpecBuilder(GetOrder.class, payper, "/v2/checkout/orders/{id}")
                .entityClass(Order.class)
                .errorClass(ErrorDefault.class).build();
        return RequestSpecsFactory.getInstance().get(spec);
    }

    /**
     * Updates an order.
     * <p>
     * This method initializes a request to update an existing order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the update order request specification
     */
    @Override
    public UpdateOrder update() {
        var spec = PayperProvider.provider().createSpecBuilder(UpdateOrder.class, payper,
                        "/v2/checkout/orders/{id}")
                .entityClass(Void.class)
                .errorClass(ErrorDefault.class).build();
        return RequestSpecsFactory.getInstance().patch(spec);
    }

    /**
     * Confirms a payment source for an order.
     * <p>
     * This method initializes a request to confirm a payment source for an existing order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the confirm payment source request specification
     */
    @Override
    public ConfirmPaymentSourceOrder confirmPaymentSource() {
        var spec = PayperProvider.provider().createSpecBuilder(ConfirmPaymentSourceOrder.class, payper,
                        "/v2/checkout/orders/{id}/confirm-payment-source")
                .entityClass(Order.class).errorClass(ErrorDefault.class).build();
        return RequestSpecsFactory.getInstance().post(spec);
    }

    /**
     * Authorizes an order.
     * <p>
     * This method initializes a request to authorize an order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the authorize order request specification
     */
    @Override
    public AuthorizeOrder authorize() {
        var spec = PayperProvider.provider().createSpecBuilder(AuthorizeOrder.class, payper,
                        "/v2/checkout/orders/{id}/authorize")
                .entityClass(OrderAuthorizeResponse.class)
                .errorClass(ErrorDefault.class).build();
        return RequestSpecsFactory.getInstance().post(spec);
    }

    /**
     * Captures an order.
     * <p>
     * This method initializes a request to capture an order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the capture order request specification
     */
    @Override
    public CaptureOrder capture() {
        var spec = PayperProvider.provider().createSpecBuilder(CaptureOrder.class, payper,
                        "/v2/checkout/orders/{id}/capture")
                .entityClass(Order.class)
                .errorClass(ErrorDefault.class).build();
        return RequestSpecsFactory.getInstance().post(spec);
    }

    /**
     * Tracks an order.
     * <p>
     * This method initializes a request to track an order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the track order request specification
     */
    @Override
    public TrackOrder track() {
        var spec = PayperProvider.provider().createSpecBuilder(TrackOrder.class, payper,
                        "/v2/checkout/orders/{id}/track")
                .entityClass(Order.class).errorClass(ErrorDefault.class).build();
        return RequestSpecsFactory.getInstance().post(spec);
    }

    /**
     * Updates a tracking order.
     * <p>
     * This method initializes a request to update a tracking order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the update tracking order request specification
     */
    @Override
    public UpdateTrackingOrder updateTracking() {
        var spec = PayperProvider.provider().createSpecBuilder(UpdateTrackingOrder.class, payper,
                        "/v2/checkout/orders/{id}/trackers/{id2}")
                .entityClass(Void.class)
                .errorClass(ErrorDefault.class)
                .alias("withTrackingId", "withId2")
                .build();
        return RequestSpecsFactory.getInstance().patch(spec);
    }


}
