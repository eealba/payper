package io.github.eealba.payper.subscriptions.v1.api;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.rest.ResponseSpec;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class SubscriptionsImpl implements Subscriptions {
    private final PayperConfig config;
    private final HttpClient httpClient;

    public SubscriptionsImpl(PayperConfig config) {
        this.config = config;
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public BillingPlans billingPlans() {
        return new BillingPlansImpl();
    }

    private class BillingPlansImpl implements BillingPlans {
        @Override
        public CreatePlan create() {
            return new CreatePlanImpl();
        }

        @Override
        public ListPlans list() {
            return null;
        }

        @Override
        public GetPlan get() {
            return null;
        }

        @Override
        public UpdatePlan update() {
            return null;
        }

        @Override
        public ActivatePlan activate() {
            return null;
        }

        @Override
        public DeactivatePlan deactivate() {
            return null;
        }

        @Override
        public UpdatePricingSchemes updatePricingSchemes() {
            return null;
        }
    }

    private class CreatePlanImpl implements CreatePlan {
        private PlanRequestPOST body;

        @Override
        public CreatePlan withBody(PlanRequestPOST body) {
            this.body = body;
            return this;
        }

        @Override
        public CreatePlan withPrefer(String prefer) {
            // Implement as needed
            return this;
        }

        @Override
        public CreatePlan withPaypalRequestId(String paypalRequestId) {
            // Implement as needed
            return this;
        }

        @Override
        public ResponseSpec<Plan, ErrorDefault> retrieve() {
            return new ResponseSpec<>() {
                @Override
                public Plan toEntity() {
                    try {
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(new URI(config.getBaseUrl() + "/v1/billing/plans"))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(config.getJson().toJson(body)))
                                .build();

                        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                        return config.getJson().fromJson(response.body(), Plan.class);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void toVoid() {
                    toEntity();
                }

                @Override
                public Response<Plan, ErrorDefault> toResponse() {
                    Plan plan = toEntity();
                    return new Response<>(plan, null);
                }

                @Override
                public CompletableFuture<Response<Plan, ErrorDefault>> toFuture() {
                    return CompletableFuture.supplyAsync(this::toResponse);
                }

                @Override
                public void consume(Consumer<Response<Plan, ErrorDefault>> consumer) {
                    consumer.accept(toResponse());
                }

                @Override
                public void consumeAsync(Consumer<Response<Plan, ErrorDefault>> consumer) {
                    toFuture().thenAccept(consumer);
                }
            };
        }
    }

    // Implement other inner classes (ListPlansImpl, GetPlanImpl, UpdatePlanImpl, ActivatePlanImpl, DeactivatePlanImpl, UpdatePricingSchemesImpl) similarly
}