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
import io.github.eealba.payper.subscriptions.v1.model.Subscription;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionActivateRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionCancelRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionRequestPost;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionReviseRequest;
import io.github.eealba.payper.subscriptions.v1.model.SubscriptionSuspendRequest;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemesListRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
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
    private static String subscriptionId;

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

    /*************************************************************************
     * Subscriptions API - Subscriptions
     *************************************************************************/

    @Test
    @Order(9)
    void create_subscription() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "subscription_request_post2.json")
                .replaceAll("\\{\\{billing_plan_id}}", planId)
                .replaceAll("\\{\\{tomorrow}}", Instant.now().plusSeconds(86400).toString());


        var body = Json.create().fromJson(jsonRequest, SubscriptionRequestPost.class);

        var response = subscriptionsApiClient.billingSubscriptions().create()
                .withPrefer("return=representation")
                .withBody(body)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
        var subscription = response.toEntity();

        assertNotNull(subscription.id());
        subscriptionId = subscription.id();
    }

    @Test
    @Order(10)
    void get_subscription() {
        var subscriptions = subscriptionsApiClient.billingSubscriptions().get()
                .withId(subscriptionId).retrieve().toEntity();

        assertEquals(Subscription.Status.APPROVAL_PENDING, subscriptions.status());
    }

    @Test
    @Order(11)
    @Disabled
    void update_subscription() {
        var patch = Patch.builder()
                .op(Patch.Op.ADD)
                .path("/custom_id")
                .value("custom-id")
                .build();
        var body = new PatchRequest(List.of(patch));

        var response = subscriptionsApiClient.billingSubscriptions().update()
                .withId(subscriptionId)
                .withBody(body)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(12)
    @Disabled
    void revise_subscription() throws IOException {
        var jsonRequest = readResource(EXAMPLES + "subscription_revise_request.json")
                .replaceAll("P-5ML4271244454362WXNWU5NQ", planId);

        var body = Json.create().fromJson(jsonRequest, SubscriptionReviseRequest.class);

        var response = subscriptionsApiClient.billingSubscriptions().revise()
                .withId(subscriptionId)
                .withBody(body)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(13)
    @Disabled
    void suspend_subscription() {
        var response = subscriptionsApiClient.billingSubscriptions().suspend()
                .withId(subscriptionId)
                .withBody(new SubscriptionSuspendRequest("Suspended by user"))
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(14)
    @Disabled
    void activate_subscription() {
        var response = subscriptionsApiClient.billingSubscriptions().activate()
                .withId(subscriptionId)
                .withBody(SubscriptionActivateRequest.builder().reason("Activated by user").build())
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(15)
    @Disabled
    void cancel_subscription() {
        var response = subscriptionsApiClient.billingSubscriptions().cancel()
                .withId(subscriptionId)
                .withBody(new SubscriptionCancelRequest("Canceled by user"))
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(16)
    @Disabled
    void capture_subscription() {
        var response = subscriptionsApiClient.billingSubscriptions().capture()
                .withId(subscriptionId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }

    @Test
    @Order(17)
    @Disabled
    void list_transactions() {
        var response = subscriptionsApiClient.billingSubscriptions().listTransactions()
                .withId(subscriptionId)
                .retrieve()
                .toResponse();

        assertTrue(response.isSuccessful(), () -> response.toErrorEntity().message());
    }
}
