package io.github.eealba.payper.payments.v2;

import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.client.PayperConfig;
import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;
import io.github.eealba.payper.orders.v2.model.AmountWithBreakdown;
import io.github.eealba.payper.orders.v2.model.CardRequest;
import io.github.eealba.payper.orders.v2.model.CheckoutPaymentIntent;
import io.github.eealba.payper.orders.v2.model.CurrencyCode;
import io.github.eealba.payper.orders.v2.model.DateYearMonth;
import io.github.eealba.payper.orders.v2.model.OrderRequest;
import io.github.eealba.payper.orders.v2.model.PaymentSource;
import io.github.eealba.payper.orders.v2.model.PurchaseUnitRequest;
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;
import io.github.eealba.payper.payments.v2.model.CaptureRequest;
import io.github.eealba.payper.payments.v2.model.ReauthorizeRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static io.github.eealba.payper.orders.v2.model.CheckoutPaymentIntent.AUTHORIZE;
import static io.github.eealba.payper.orders.v2.model.CheckoutPaymentIntent.CAPTURE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentsIT {

    private static PaymentsApiClient paymentsApiClient;
    private static CheckoutOrdersApiClient checkoutOrdersApiClient;
    private static String orderId;
    private static String captureId;

    private static String orderId2;
    private static String authorizeId2;
    private static String refundId;
    private static String captureId2;

    @BeforeAll
    static void setup() throws IOException {
        var path = Path.of(System.getProperty("user.home"), "/.payper/", "credentials.properties");
        var config = PayperConfig.builder()
                .authenticator(PayperAuthenticator.PayperAuthenticators.ofSandBox(path))
                .build();
        paymentsApiClient = PaymentsApiClient.create(config);
        checkoutOrdersApiClient = CheckoutOrdersApiClient.create(config);

    }

    @Test
    @Order(1)
    void create_order() {
        var orderRequest = getOrderRequest(CAPTURE);
        var response = checkoutOrdersApiClient.orders().create()
                .withPaypalRequestId(UUID.randomUUID().toString())
                .withPrefer(RequestSpec.Prefer.RETURN_REPRESENTATION)
                .withBody(orderRequest)
                .retrieve().toResponse();

        assertTrue(response.isSuccessful(), response::toRawString);
        var order1 = response.toEntity();
        orderId = order1.id();
        captureId = order1.purchaseUnits().get(0).payments().captures().get(0).id();

        orderRequest = getOrderRequest(AUTHORIZE);
        response = checkoutOrdersApiClient.orders().create()
                .withPaypalRequestId(UUID.randomUUID().toString())
                .withPrefer(RequestSpec.Prefer.RETURN_REPRESENTATION)
                .withBody(orderRequest)
                .retrieve().toResponse();

        assertTrue(response.isSuccessful());
        var order2 = response.toEntity();
        orderId2 = order2.id();
        authorizeId2 = order2.purchaseUnits().get(0).payments().authorizations().get(0).id();

    }

    private static OrderRequest getOrderRequest(CheckoutPaymentIntent value) {
        var orderRequest = OrderRequest.builder()
                .intent(value)
                .purchaseUnits(List.of(PurchaseUnitRequest.builder()
                        .amount(AmountWithBreakdown.builder()
                                .currencyCode(CurrencyCode.USD)
                                .value("100.00")
                                .build())
                        .build()))
                .paymentSource(PaymentSource.builder()
                        .card(CardRequest.builder()
                                .number("4111111111111111")
                                .expiry(new DateYearMonth("2035-12"))
                                .build())
                        .build())
                .build();
        return orderRequest;
    }
    /*************************************************************************
     * Authorizations API
     *************************************************************************/

    @Test
    @Order(3)
    void Show_details_for_authorized_payment() {
        var response = paymentsApiClient.authorizations()
                .get()
                .withId(authorizeId2)
                .retrieve()
                .toResponse();

        assertEquals(200, response.statusCode(), response::toRawString);
        assertEquals("100.00", response.toEntity().amount().value());

    }
    @Test
    @Order(10)
    void capture_authorized_payment() {
        var response = paymentsApiClient.authorizations()
                .capture()
                .withId(authorizeId2)
                .withPrefer(RequestSpec.Prefer.RETURN_REPRESENTATION)
                .withBody(CaptureRequest.builder().build())
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), response::toRawString);
        captureId2 = response.toEntity().id();

    }
    @Test
    @Order(11)
    void reauthorize_authorized_payment() {
        var response = paymentsApiClient.authorizations()
                .reauthorize()
                .withId(authorizeId2)
                .withBody(ReauthorizeRequest.builder().build())
                .retrieve()
                .toResponse();

        assertEquals(422, response.statusCode(), response::toRawString);

    }
    @Test
    @Order(12)
    void void_authorized_payment() {
        var response = paymentsApiClient.authorizations()
                .voidAuthorization()
                .withId(authorizeId2)
                .retrieve()
                .toResponse();

        assertEquals(422, response.statusCode(), response::toRawString);

    }

    /*************************************************************************
     * Captures API
     *************************************************************************/
    @Test
    @Order(20)
    void Show_details_for_captured_payment() {
        var response = paymentsApiClient.captures()
                .get()
                .withId(captureId)
                .retrieve()
                .toResponse();

        assertEquals(200, response.statusCode(), response::toRawString);
        assertEquals("100.00", response.toEntity().amount().value());

    }

    @Test
    @Order(21)
    void refund_captured_payment() {
        var response = paymentsApiClient.captures()
                .refund()
                .withPrefer(RequestSpec.Prefer.RETURN_REPRESENTATION)
                .withId(captureId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), response::toRawString);
        refundId = response.toEntity().id();

    }

    /*************************************************************************
     * Refunds API
     *************************************************************************/
    @Test
    @Order(30)
    void Show_details_for_refunded_payment() {
        var response = paymentsApiClient.refunds()
                .get()
                .withId(refundId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), response::toRawString);
        assertEquals("100.00", response.toEntity().amount().value());

    }


    private static String readResource(String path) throws IOException {
        try (var inputStream = ModelTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
