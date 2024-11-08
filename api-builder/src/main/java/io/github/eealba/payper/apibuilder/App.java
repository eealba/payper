package io.github.eealba.payper.apibuilder;

import java.nio.file.Paths;

public class App {

    private static final String RESOURCES_OPENAPI = "/Users/enrique/git/paypal-builder/src/main/resources/openapi";
    private static final String PATH_SUBSCRIPTIONS_V1 = "/Users/enrique/git/paypal/subscriptions-v1";
    public static void main(String[] args)  {
        subscriptionsV1();
    }

    private static void subscriptionsV1() {
        var pathOpenApiFile = Paths.get(RESOURCES_OPENAPI, "/billing_subscriptions_v1.json");
        var pathSource = Paths.get(PATH_SUBSCRIPTIONS_V1, "/src/main/java");
        var packageName = "io.github.eealba.paypal.subscriptions.v1.model";
        var apiGenerator = ApiGenerator.builder(pathOpenApiFile, pathSource, packageName).build();
        apiGenerator.generate();
    }
}
