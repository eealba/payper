package io.github.eealba.payper.orders.v2.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.orders.v2.api.Orders;
import io.github.eealba.payper.orders.v2.model.ConfirmOrderRequest;
import io.github.eealba.payper.orders.v2.model.ErrorDefault;
import io.github.eealba.payper.orders.v2.model.Order;
import io.github.eealba.payper.orders.v2.model.OrderAuthorizeRequest;
import io.github.eealba.payper.orders.v2.model.OrderAuthorizeResponse;
import io.github.eealba.payper.orders.v2.model.OrderCaptureRequest;
import io.github.eealba.payper.orders.v2.model.OrderRequest;
import io.github.eealba.payper.orders.v2.model.OrderTrackerRequest;
import io.github.eealba.payper.orders.v2.model.PatchRequest;

class OrdersImpl implements Orders {
    private final Payper payer;

    public OrdersImpl(Payper payper) {
        this.payer = payper;
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
        return new CreateOrderImpl(payer);
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
        return new GetOrderImpl(payer);
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
        return new UpdateOrderImpl(payer);
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
        return new ConfirmPaymentSourceOrderImpl(payer);
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
        return new AuthorizeOrderImpl(payer);
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
        return new CaptureOrderImpl(payer);
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
        return new TrackOrderImpl(payer);
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
        return new UpdateTrackingOrderImpl(payer);
    }

    private static class CreateOrderImpl extends RequestSpecImpl<CreateOrder, OrderRequest, Order, ErrorDefault>
            implements CreateOrder {
        CreateOrderImpl(Payper payper) {
            super(payper, "/v2/checkout/orders", Order.class, ErrorDefault.class);
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class GetOrderImpl extends RequestSpecImpl<GetOrder, Void, Order, ErrorDefault>
            implements GetOrder {
        GetOrderImpl(Payper payper) {
            super(payper, "/v2/checkout/orders/{id}", Order.class, ErrorDefault.class);
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.GET;
        }

        @Override
        public void withFields(String fields) {
            query("fields", fields);
        }
    }

    private static class UpdateOrderImpl extends RequestSpecImpl<UpdateOrder, PatchRequest, Void, ErrorDefault>
            implements UpdateOrder {
        UpdateOrderImpl(Payper payper) {
            super(payper, "/v2/checkout/orders/{id}", Void.class, ErrorDefault.class);
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.PATCH;
        }
    }

    private static class ConfirmPaymentSourceOrderImpl extends RequestSpecImpl<ConfirmPaymentSourceOrder,
            ConfirmOrderRequest, Order, ErrorDefault>
            implements ConfirmPaymentSourceOrder {
        ConfirmPaymentSourceOrderImpl(Payper payper) {
            super(payper, "/v2/checkout/orders/{id}/confirm-payment-source", Order.class, ErrorDefault.class);
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class AuthorizeOrderImpl extends RequestSpecImpl<AuthorizeOrder, OrderAuthorizeRequest,
            OrderAuthorizeResponse, ErrorDefault>
            implements AuthorizeOrder {
        AuthorizeOrderImpl(Payper payper) {
            super(payper, "/v2/checkout/orders/{id}/authorize", OrderAuthorizeResponse.class, ErrorDefault.class);
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class CaptureOrderImpl extends RequestSpecImpl<CaptureOrder, OrderCaptureRequest, Order, ErrorDefault>
            implements CaptureOrder {
        CaptureOrderImpl(Payper payper) {
            super(payper, "/v2/checkout/orders/{id}/capture", Order.class, ErrorDefault.class);
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class TrackOrderImpl extends RequestSpecImpl<TrackOrder, OrderTrackerRequest, Order, ErrorDefault>
            implements TrackOrder {
        TrackOrderImpl(Payper payper) {
            super(payper, "/v2/checkout/orders/{id}/track", Order.class, ErrorDefault.class);
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class UpdateTrackingOrderImpl extends RequestSpecImpl<UpdateTrackingOrder, PatchRequest,
            Void, ErrorDefault>
            implements UpdateTrackingOrder {
        UpdateTrackingOrderImpl(Payper payper) {
            super(payper, "/v2/checkout/orders/{id}/trackers/{tracker_id}", Void.class, ErrorDefault.class);
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.PATCH;
        }

        @Override
        public void withTrackingId(String trackingId) {
            pathParameter("track_id", trackingId);
        }
    }
}
