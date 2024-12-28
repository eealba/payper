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
package io.github.eealba.payper.orders.v2.api;

import io.github.eealba.payper.core.spec.RequestSpec;
import io.github.eealba.payper.orders.v2.model.ConfirmOrderRequest;
import io.github.eealba.payper.orders.v2.model.ErrorDefault;
import io.github.eealba.payper.orders.v2.model.Order;
import io.github.eealba.payper.orders.v2.model.OrderAuthorizeRequest;
import io.github.eealba.payper.orders.v2.model.OrderAuthorizeResponse;
import io.github.eealba.payper.orders.v2.model.OrderCaptureRequest;
import io.github.eealba.payper.orders.v2.model.OrderRequest;
import io.github.eealba.payper.orders.v2.model.OrderTrackerRequest;
import io.github.eealba.payper.orders.v2.model.PatchRequest;
/**
 * Interface representing the Orders API.
 *
 * @since 1.0
 * @version 1.0
 */
public interface Orders {

    /**
     * Creates an Order.
     * <p>
     * This method initializes a request to create a new order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body,
     * specifying the preferred return representation, and setting a PayPal request ID.
     *
     * @return the create order request specification
     */
    CreateOrder create();


    /**
     * Gets an order.
     * <p>
     * This method initializes a request to retrieve a specific order from the PayPal Orders API
     * by its ID.
     *
     * @return the get order request specification
     */
    GetOrder get();

    /**
     * Updates an order.
     * <p>
     * This method initializes a request to update an existing order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the update order request specification
     */
    UpdateOrder update();

    /**
     * Confirms a payment source for an order.
     * <p>
     * This method initializes a request to confirm a payment source for an existing order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the confirm payment source request specification
     */
    ConfirmPaymentSourceOrder confirmPaymentSource();

    /**
     * Authorizes an order.
     * <p>
     * This method initializes a request to authorize an order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the authorize order request specification
     */
    AuthorizeOrder authorize();

    /**
     * Captures an order.
     * <p>
     * This method initializes a request to capture an order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the capture order request specification
     */
    CaptureOrder capture();

    /**
     * Tracks an order.
     * <p>
     * This method initializes a request to track an order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the track order request specification
     */
    TrackOrder track();

    /**
     * Updates a tracking order.
     * <p>
     * This method initializes a request to update a tracking order in the PayPal Orders API.
     * The request can be customized with various options such as setting the request body and specifying the order ID.
     *
     * @return the update tracking order request specification
     */
    UpdateTrackingOrder updateTracking();



    /**
     * Interface for creating a order.
     * <p>
     * This interface defines the specifications for creating a order, including setting the request body,
     * specifying the preferred return representation, and setting a PayPal request ID.
     */
    interface CreateOrder extends RequestSpec<Order, ErrorDefault>,
            RequestSpec.BodySpec<CreateOrder, OrderRequest>,
            RequestSpec.PreferSpec<CreateOrder>,
            RequestSpec.PaypalRequestIdSpec<CreateOrder>,
            RequestSpec.PayPalPartnerAttributionIdSpec<CreateOrder>,
            RequestSpec.PayPalClientMetadataIdSpec<CreateOrder> {
    }


    /**
     * Interface for getting a order.
     * <p>
     * This interface defines the specifications for retrieving a specific order by its ID.
     */
    interface GetOrder extends RequestSpec<Order, ErrorDefault>,
            RequestSpec.IdSpec<GetOrder> {
        void withFields(String fields);
    }

    /**
     * Interface for updating a order.
     * <p>
     * This interface defines the specifications for updating an existing order, including setting the request body
     * and specifying the order ID.
     */
    interface UpdateOrder extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.BodySpec<UpdateOrder, PatchRequest>,
            RequestSpec.IdSpec<UpdateOrder> {
    }

    /**
     * Interface for confirming a payment source.
     * <p>
     * This interface defines the specifications for confirming a payment source for an existing order.
     */
    interface ConfirmPaymentSourceOrder extends RequestSpec<Order, ErrorDefault>,
            RequestSpec.BodySpec<ConfirmPaymentSourceOrder, ConfirmOrderRequest>,
            RequestSpec.PreferSpec<ConfirmPaymentSourceOrder>,
            RequestSpec.PayPalClientMetadataIdSpec<ConfirmPaymentSourceOrder>,
            RequestSpec.IdSpec<ConfirmPaymentSourceOrder> {
    }

    /**
     * Interface for authorizing an order.
     * <p>
     * This interface defines the specifications for authorizing an order.
     */
    interface AuthorizeOrder extends RequestSpec<OrderAuthorizeResponse, ErrorDefault>,
            RequestSpec.BodySpec<AuthorizeOrder, OrderAuthorizeRequest>,
            RequestSpec.PaypalRequestIdSpec<AuthorizeOrder>,
            RequestSpec.PreferSpec<AuthorizeOrder>,
            RequestSpec.PayPalClientMetadataIdSpec<AuthorizeOrder>,
            RequestSpec.PayPalAuthAssertionSpec<AuthorizeOrder>,
            RequestSpec.IdSpec<AuthorizeOrder> {
    }

    /**
     * Interface for capturing an order.
     * <p>
     * This interface defines the specifications for capturing an order.
     */
    interface CaptureOrder extends RequestSpec<Order, ErrorDefault>,
            RequestSpec.BodySpec<CaptureOrder, OrderCaptureRequest>,
            RequestSpec.PaypalRequestIdSpec<CaptureOrder>,
            RequestSpec.PreferSpec<CaptureOrder>,
            RequestSpec.PayPalClientMetadataIdSpec<CaptureOrder>,
            RequestSpec.PayPalAuthAssertionSpec<CaptureOrder>,
            RequestSpec.IdSpec<CaptureOrder> {
    }

    /**
     * Interface for tracking an order.
     * <p>
     * This interface defines the specifications for tracking an order.
     */
    interface TrackOrder extends RequestSpec<Order, ErrorDefault>,
            RequestSpec.BodySpec<TrackOrder, OrderTrackerRequest>,
            RequestSpec.PayPalAuthAssertionSpec<TrackOrder>,
            RequestSpec.IdSpec<TrackOrder> {
    }

    /**
     * Interface for updating a tracking order.
     * <p>
     * This interface defines the specifications for updating a tracking order.
     */
    interface UpdateTrackingOrder extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.BodySpec<UpdateTrackingOrder, PatchRequest>,
            RequestSpec.IdSpec<UpdateTrackingOrder> {
        void withTrackingId(String trackingId);
    }
}
