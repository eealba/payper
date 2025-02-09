package io.github.eealba.payper.orders.v2;

import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.client.PayperConfig;
import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;
import io.github.eealba.payper.orders.v2.model.AddressPortable;
import io.github.eealba.payper.orders.v2.model.ConfirmOrderRequest;
import io.github.eealba.payper.orders.v2.model.CountryCode;
import io.github.eealba.payper.orders.v2.model.OrderRequest;
import io.github.eealba.payper.orders.v2.model.Patch;
import io.github.eealba.payper.orders.v2.model.PatchRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static io.github.eealba.payper.orders.v2.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdersIT {
    private static CheckoutOrdersApiClient checkoutOrdersApiClient;
    private static String orderId;
    private static String orderId3;

    @BeforeAll
    static void setup() throws IOException {
        var path = Path.of(System.getProperty("user.home"),   "/.payper/", "credentials.properties");
        var config = PayperConfig.builder()
                .authenticator(PayperAuthenticator.PayperAuthenticators.ofSandBox(path))
                .build();
        checkoutOrdersApiClient = CheckoutOrdersApiClient.create(config);
    }
    
    @Test
    @Order(1)
    void create_order() throws IOException {
        var jsonRequest = readResource("/examples/order_request_2.json");
        var body = Json.create().fromJson(jsonRequest, OrderRequest.class);
        var response = checkoutOrdersApiClient.orders().create()

                .withPrefer(RequestSpec.Prefer.RETURN_REPRESENTATION)
                .withBody(body)
                .retrieve().toResponse();
        
        assertTrue(response.isSuccessful());
        orderId = response.toEntity().id();

        jsonRequest = readResource("/examples/order_request_3.json");
        body = Json.create().fromJson(jsonRequest, OrderRequest.class);
        response = checkoutOrdersApiClient.orders().create()

                .withPrefer(RequestSpec.Prefer.RETURN_REPRESENTATION)
                .withBody(body)
                .retrieve().toResponse();

        assertTrue(response.isSuccessful());
        orderId3 = response.toEntity().id();
    }

    @Test
    @Order(2)
    void confirm_payment_source() throws IOException {
        var jsonRequest = readResource("/examples/confirm_order_request_2.json");
        var body = Json.create().fromJson(jsonRequest, ConfirmOrderRequest.class);

        var response = checkoutOrdersApiClient.orders()
                .confirmPaymentSource()
                .withBody(body)
                .withId(orderId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful());
    }

    @Test
    @Order(3)
    void get_order() {
        var response = checkoutOrdersApiClient.orders().get()
                .withId(orderId)
                .retrieve()
                .toResponse();
        assertTrue(response.isSuccessful());

        System.out.println("Receive order: " + response.toRawString());
    }

    @Test
    @Order(4)
    void update_order() throws IOException {
        var address = AddressPortable.builder()
                .addressLine1("123 Townsend St")
                .addressLine2("Floor 6")
                .adminArea1("CA")
                .adminArea2("San Francisco")
                .postalCode("94107")
                .countryCode(CountryCode.US)
                .build();

        var path = Patch.builder()
                .op(Patch.Op.ADD)
                .path("/purchase_units/@reference_id=='default'/shipping/address")
                .value(address)
                .build();
        var patchRequest = new PatchRequest(List.of(path));

        var response = checkoutOrdersApiClient.orders()
                .update()
                .withBody(patchRequest)
                .withId(orderId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }


    @Test
    @Order(5)
    void capture_order() {
        var response = checkoutOrdersApiClient.orders()
                .capture()
                .withId(orderId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), response::toRawString);
        assertNotNull(response.toEntity().purchaseUnits().get(0).payments().captures().get(0).id());
    }
    @Test
    @Order(6)
    void authorize_order() {
        var response = checkoutOrdersApiClient.orders()
                .authorize()
                .withPrefer(RequestSpec.Prefer.RETURN_REPRESENTATION)
                .withId(orderId3)
                .retrieve()
                .toResponse();

        assertTrue(response.isClientError(), response::toRawString);
    }

    @Test
    @Order(7)
    void confirm_payment_source_3ds() throws IOException {
        var jsonRequest = readResource("/examples/confirm_order_request_3ds.json");
        var body = Json.create().fromJson(jsonRequest, ConfirmOrderRequest.class);

        var response = checkoutOrdersApiClient.orders()
                .confirmPaymentSource()
                .withBody(body)
                .withId(orderId3)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), response::toRawString);
    }
}
