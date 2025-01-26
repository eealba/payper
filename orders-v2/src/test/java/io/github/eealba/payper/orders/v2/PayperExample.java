package io.github.eealba.payper.orders.v2;

import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.client.PayperConfig;
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;
import io.github.eealba.payper.orders.v2.api.Orders;
import io.github.eealba.payper.orders.v2.model.AmountWithBreakdown;
import io.github.eealba.payper.orders.v2.model.CardRequest;
import io.github.eealba.payper.orders.v2.model.CheckoutPaymentIntent;
import io.github.eealba.payper.orders.v2.model.ConfirmOrderRequest;
import io.github.eealba.payper.orders.v2.model.CurrencyCode;
import io.github.eealba.payper.orders.v2.model.DateYearMonth;
import io.github.eealba.payper.orders.v2.model.OrderRequest;
import io.github.eealba.payper.orders.v2.model.PaymentSource;
import io.github.eealba.payper.orders.v2.model.PurchaseUnitRequest;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class PayperExample {
    private final Orders ordersApi;

    public PayperExample(Orders ordersApi) {
        this.ordersApi = ordersApi;
    }

    public static void main(String[] args) throws IOException {
        var credentialPath = Paths.get(System.getProperty("user.home"), ".payper", "credentials.properties");
        var authenticator = PayperAuthenticator.PayperAuthenticators.ofSandBox(credentialPath);
        var payperConfig = PayperConfig.builder().authenticator(authenticator).build();
        var ordersApi = CheckoutOrdersApiClient.create(payperConfig).orders();
        new PayperExample(ordersApi).run();
    }


    private void run()  {
        var orderId = createOrder();
        getOrderById(orderId);
        confirmPaymentSource(orderId);
        captureOrder(orderId);
    }

    private String createOrder() {
        var orderRequest = OrderRequest.builder()
                .intent(CheckoutPaymentIntent.CAPTURE)
                .purchaseUnits(List.of(PurchaseUnitRequest.builder()
                        .amount(AmountWithBreakdown.builder()
                                .currencyCode(CurrencyCode.USD)
                                .value("100.00")
                                .build())
                        .build()))
                .build();

        var order = ordersApi.create().withBody(orderRequest).retrieve().toEntity();

        System.out.println("Order ID: " + order.id());
        return order.id();
    }
    private void getOrderById(String orderId) {
        var order =  ordersApi.get().withId(orderId).retrieve().toEntity();
        System.out.println("Retrieved order with ID: " + order.id());
    }

    private void confirmPaymentSource(String orderId) {
        var body = ConfirmOrderRequest.builder()

                .paymentSource(PaymentSource.builder()
                        .card(CardRequest.builder()
                                .number("4111111111111111")
                                .expiry(new DateYearMonth("2035-01"))
                                .build())
                        .build())
                .build();
        var response = ordersApi.confirmPaymentSource().withId(orderId).withBody(body).retrieve().toResponse();
        if (response.isError()) {
            System.out.println("Error: " + response.toErrorEntity().message() + "status: " + response.statusCode());
            return;
        }
        System.out.println("Order confirmed");
    }

    private void captureOrder(String orderId) {
        var response = ordersApi.capture().withId(orderId).retrieve().toResponse();
        if (response.isError()) {
            System.out.println("Error: " + response.toErrorEntity().message() + "status: " + response.statusCode());
            return;
        }
        System.out.println("Capture ID: " + response.toEntity().id());
    }

}
