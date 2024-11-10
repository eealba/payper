package io.github.eealba.payper.apibuilder;

import java.nio.file.Paths;

public class App {
    private static final String PROJECT_HOME = System.getProperty("user.home") + "/git/payper";

    private static final String RESOURCES_OPENAPI = PROJECT_HOME + "/api-builder/src/main/resources/openapi";
    private static final String PATH_SUBSCRIPTIONS_V1 = PROJECT_HOME + "/subscriptions-v1";
    public static void main(String[] args)  {
        subscriptionsV1();
    }

    private static void subscriptionsV1() {
        var pathOpenApiFile = Paths.get(RESOURCES_OPENAPI, "/billing_subscriptions_v1.json");
        var pathSource = Paths.get(PATH_SUBSCRIPTIONS_V1, "/src/main/java");
        var packageName = "io.github.eealba.payper.subscriptions.v1.model";
        var apiGenerator = ApiGenerator.builder(pathOpenApiFile, pathSource, packageName).build();
        apiGenerator.generate();
    }
}
