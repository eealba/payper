package io.github.eealba.payper.subscriptions.v1;

import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.catalog.products.v1.model.ProductCategory;
import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;
import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.client.PayperConfig;
import io.github.eealba.payper.core.json.Json;
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
import io.github.eealba.payper.subscriptions.v1.model.Patch;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemesListRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static io.github.eealba.payper.subscriptions.v1.Util.readResource;
import static io.github.eealba.payper.subscriptions.v1.model.Plan.Status.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubscriptionsIT {
    private static CatalogProductsApiClient catalogProductsApiClient;
    private static SubscriptionsApiClient subscriptionsApiClient;
    private static final String EXAMPLES = "/examples/";
    private static String productId;
    private static String planId;

    @BeforeAll
    static void setup() throws IOException {
        productId = UUID.randomUUID().toString();
        var path = Path.of(System.getProperty("user.home"),   "/.payper/", "credentials.properties");
        var config = PayperConfig.builder()
                .authenticator(PayperAuthenticator.PayperAuthenticators.ofSandBox(path))
                .build();
        catalogProductsApiClient = CatalogProductsApiClient.create(config);
        subscriptionsApiClient = SubscriptionsApiClient.create(config);
    }


    /*************************************************************************
     * Catalog Products API
     *************************************************************************/
    @Test
    @Order(1)
    void create_product() {
        var body = ProductRequestPOST.builder()
                .id(productId)
                .name("Video Streaming Service")
                .description("Video Streaming Service basic plan")
                .type(ProductRequestPOST.Type.PHYSICAL)
                .category(ProductCategory.SOFTWARE)
                .imageUrl("https://example.com/streaming.jpg")
                .homeUrl("https://example.com/home")
                .build();
        
        var response = catalogProductsApiClient.products().create().withBody(body).retrieve().toResponse();
        
        assertEquals(201, response.statusCode());

    }
    /*************************************************************************
     * Subscriptions API - Billing Plans
     *************************************************************************/

    @Test
    @Order(2)
    void create_plan() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "plan_request_POST.json")
                .replaceAll("PROD-XXCD1234QWER65782", productId);

        var body = Json.create().fromJson(jsonRequest, PlanRequestPOST.class);

        var plan = subscriptionsApiClient.billingPlans().create()
                .withPrefer("return=representation")
                .withBody(body)
                .retrieve()
                .toOptionalEntity()
                .orElseThrow();

        assertEquals(ACTIVE, plan.status());
        assertNotNull(plan.id());
        planId = plan.id();
    }

    @Test
    @Order(3)
    void get_plan() {
        var plan = subscriptionsApiClient.billingPlans().get()
                .withId(planId)
                .retrieve()
                .toEntity();

        assertEquals(ACTIVE, plan.status());
    }

    @Test
    @Order(4)
    void list_plans() {
        var plans = subscriptionsApiClient.billingPlans().list()
                .withPage(1)
                .withPageSize(10)
                .withTotalRequired(true)
                .retrieve()
                .toEntity();

        assertFalse(plans.plans().isEmpty());
    }

    @Test
    @Order(5)
    void update_plan() {
        var patch = Patch.builder()
                .op(Patch.Op.REPLACE)
                .path("/description")
                .value("Updated description")
                .build();
        var body = new PatchRequest(List.of(patch));


        var response = subscriptionsApiClient.billingPlans().update()
                .withId(planId)
                .withBody(body)
                .retrieve()
                .toResponse();

        assertEquals(204, response.statusCode());

        var response2 = subscriptionsApiClient.billingPlans().get()
                .withId(planId)
                .retrieve()
                .toEntity();

        assertEquals("Updated description", response2.description());
    }

    @Test
    @Order(6)
    void update_pricing_schemes() throws IOException {

        var jsonRequest = readResource(EXAMPLES + "update_pricing_schemes.json");
        var body = Json.create().fromJson(jsonRequest, UpdatePricingSchemesListRequest.class);

        var response = subscriptionsApiClient.billingPlans()
                .updatePricingSchemes()
                .withId(planId)
                .withBody(body)
                .retrieve()
                .toFuture()
                .join();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }


    @Test
    @Order(7)
    void deactivate_plan() {
        var response = subscriptionsApiClient.billingPlans()
                .deactivate()
                .withId(planId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }
    @Test
    @Order(8)
    void activate_plan() {
        var response = subscriptionsApiClient.billingPlans()
                .activate()
                .withId(planId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

}
