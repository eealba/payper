package io.github.eealba.payper.catalog.products.v1;

import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.catalog.products.v1.model.Patch;
import io.github.eealba.payper.catalog.products.v1.model.PatchRequest;
import io.github.eealba.payper.catalog.products.v1.model.ProductCategory;
import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;
import io.github.eealba.payper.core.client.PayperAuthenticator;
import io.github.eealba.payper.core.client.PayperConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CatalogProductsIT {
    private static CatalogProductsApiClient catalogProductsApiClient;
    private static String productId;

    @BeforeAll
    static void setup() throws IOException {
        productId = UUID.randomUUID().toString();
        var path = Path.of(System.getProperty("user.home"),   "/.payper/", "credentials.properties");
        var config = PayperConfig.builder()
                .authenticator(PayperAuthenticator.PayperAuthenticators.ofSandBox(path))
                .build();
        catalogProductsApiClient = CatalogProductsApiClient.create(config);
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

    @Test
    @Order(2)
    void get_product() {
        var response = catalogProductsApiClient.products().get().withId(productId).retrieve().toResponse();

        assertEquals(200, response.statusCode());
        var product = response.toEntity();
        assertEquals(productId, product.id());
    }

    @Test
    @Order(3)
    void list_products() {
        var response = catalogProductsApiClient.products().list()
                .withPage(1)
                .withPageSize(10)
                .withTotalRequired(true)
                .retrieve()
                .toResponse();

        assertEquals(200, response.statusCode());

        assertFalse(response.toEntity().products().isEmpty());

    }

    @Test
    @Order(4)
    void update_product() {
        var patch = Patch.builder()
                .op(Patch.Op.REPLACE)
                .path("/description")
                .value("Updated description")
                .build();
        var body = new PatchRequest(List.of(patch));

        var response = catalogProductsApiClient.products().update()
                .withId(productId)
                .withBody(body)
                .retrieve()
                .toResponse();

        assertEquals(204, response.statusCode());

        var response2 = catalogProductsApiClient.products().get().withId(productId).retrieve().toResponse();
        assertEquals(200, response2.statusCode());
        assertEquals("Updated description", response2.toEntity().description());


    }
}
