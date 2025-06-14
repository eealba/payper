package io.github.eealba.payper.catalog.products.v1;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.catalog.products.v1.api.Products;
import io.github.eealba.payper.catalog.products.v1.model.PatchRequest;
import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;
import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.client.RequestSpec;
import io.github.eealba.payper.core.json.Json;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.noContent;
import static com.github.tomakehurst.wiremock.client.WireMock.notFound;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.github.eealba.payper.catalog.products.v1.Util.readResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest
class ProductsTest {
    private static final String EXAMPLES = "/examples/";
    private static Products products;

    @BeforeAll
    static void setupAll(WireMockRuntimeInfo wmRuntimeInfo) {
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL, "http://localhost:" + wmRuntimeInfo.getHttpPort());
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, "client-id");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, "client-secret");
        products = CatalogProductsApiClient.create().products();
    }

    @AfterAll
    static void tearDownAll() {
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID);
        System.clearProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET);
        products = null;
    }

    @BeforeEach
    void setup() {
        String token = """
                {
                 	"scope": "https://uri.paypal.com/services/invoicing https://uri.paypal.com/services/vault/payment-tokens/read https://uri.paypal.com/services/disputes/read-buyer https://uri.paypal.com/services/payments/realtimepayment https://uri.paypal.com/services/disputes/update-seller https://uri.paypal.com/services/paypalhere https://uri.paypal.com/services/payments/payment/authcapture openid https://uri.paypal.com/services/disputes/read-seller Braintree:Vault https://uri.paypal.com/services/payments/refund https://uri.paypal.com/services/identity/activities https://api.paypal.com/v1/vault/credit-card https://api.paypal.com/v1/payments/.* https://uri.paypal.com/payments/payouts https://uri.paypal.com/services/vault/payment-tokens/readwrite https://api.paypal.com/v1/vault/credit-card/.* https://uri.paypal.com/services/shipping/trackers/readwrite https://uri.paypal.com/services/subscriptions https://uri.paypal.com/services/applications/webhooks https://api.paypal.com/v1/payments/refund https://api.paypal.com/v1/payments/sale/.*/refund",
                 	"access_token": "A21AALonQd7hsKc-mvj1Ik8v3HrP3e5YY5V7C4__KM08zH1RqHpgAvsdW6_kRRTM5AWO0WnIyyOgXz_Ki6ytB126Gjn8uBekg",
                 	"token_type": "Bearer",
                 	"app_id": "APP-80W284485P519543T",
                 	"expires_in": 30840,
                 	"nonce": "{now}dt-959IViiJFKwTQETYAg_tAcv7byuLRQyt9C8Dh0Rg"
                 }
                """.replace("{now}", Instant.now().toString());
        stubFor(post("/v1/oauth2/token").willReturn(okJson(token)));
    }

    @AfterEach
    void tearDown() {
        removeStub(post("/v1/oauth2/token"));
    }

    @Test
    void test_get_product() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "product.json");
        stubFor(get("/v1/catalogs/products/1").willReturn(okJson(jsonResponse)));

        var product = products.get().withId("1").retrieve().toEntity();

        assertNotNull(product);
        assertEquals("123", product.id());

    }

    @Test
    void not_found_product() {
        stubFor(get("/v1/catalogs/products/1").willReturn(notFound()));

        var product = products.get().withId("1").retrieve().toResponse();
        assertEquals(404, product.statusCode());
    }

    @Test
    void test_list_products() throws IOException {
        var jsonResponse = readResource(EXAMPLES + "productsCollection.json");
        stubFor(get("/v1/catalogs/products").willReturn(okJson(jsonResponse)));

        var productsCollection = products.list().retrieve().toEntity();

        assertNotNull(productsCollection);
        assertEquals(1, productsCollection.products().size());
    }

    @Test
    void test_create_product() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "product_request_post.json");
        var jsonResponse = readResource(EXAMPLES + "product.json");
        var body = Json.create().fromJson(jsonRequest, ProductRequestPOST.class);

        stubFor(post("/v1/catalogs/products")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(okJson(jsonResponse)));

        var product = products.create()
                .withPrefer(RequestSpec.Prefer.RETURN_REPRESENTATION)
                .withPaypalRequestId("request-id")
                .withBody(body)
                .retrieve().toEntity();

        assertNotNull(product);
        assertEquals("123", product.id());
    }

    @Test
    void update_product_204() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "patch_request.json");
        var request = Json.create().fromJson(jsonRequest, PatchRequest.class);
        stubFor(patch("/v1/catalogs/products/1")
                .withRequestBody(equalToJson(jsonRequest))
                .willReturn(noContent()));

        var response = products.update().withId("1").withBody(request).retrieve().toResponse();

        assertEquals(204, response.statusCode());
    }


}
