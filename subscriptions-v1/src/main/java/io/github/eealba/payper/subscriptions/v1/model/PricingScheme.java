package io.github.eealba.payper.subscriptions.v1.model;

import java.time.Instant;
import java.util.List;
import io.github.eealba.jasoner.JasonerProperty;

public class PricingScheme {

    
    private final Integer version;
    @JasonerProperty("fixed_price")
    private final Money fixedPrice;
    @JasonerProperty("pricing_model")
    private final PricingModel pricingModel;
    
    private final List<PricingTier> tiers;
    @JasonerProperty("create_time")
    private final Instant createTime;
    @JasonerProperty("update_time")
    private final Instant updateTime;

    private PricingScheme(Builder builder) {
        version = builder.version;
        fixedPrice = builder.fixedPrice;
        pricingModel = builder.pricingModel;
        tiers = builder.tiers;
        createTime = builder.createTime;
        updateTime = builder.updateTime;

    }

    
    public Integer version() {
        return version;
    }

    @JasonerProperty("fixed_price")
    public Money fixedPrice() {
        return fixedPrice;
    }

    @JasonerProperty("pricing_model")
    public PricingModel pricingModel() {
        return pricingModel;
    }

    
    public List<PricingTier> tiers() {
        return tiers;
    }

    @JasonerProperty("create_time")
    public Instant createTime() {
        return createTime;
    }

    @JasonerProperty("update_time")
    public Instant updateTime() {
        return updateTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Integer version;
        private Money fixedPrice;
        private PricingModel pricingModel;
        private List<PricingTier> tiers;
        private Instant createTime;
        private Instant updateTime;

        
        public Builder version(Integer value) {
            this.version = value;
            return this;
        }

        @JasonerProperty("fixed_price")
        public Builder fixedPrice(Money value) {
            this.fixedPrice = value;
            return this;
        }

        @JasonerProperty("pricing_model")
        public Builder pricingModel(PricingModel value) {
            this.pricingModel = value;
            return this;
        }

        
        public Builder tiers(List<PricingTier> value) {
            this.tiers = value;
            return this;
        }

        @JasonerProperty("create_time")
        public Builder createTime(Instant value) {
            this.createTime = value;
            return this;
        }

        @JasonerProperty("update_time")
        public Builder updateTime(Instant value) {
            this.updateTime = value;
            return this;
        }

        public PricingScheme build() {
            return new PricingScheme(this);
        }

    }
    /**
     * The pricing model for tiered plan. The `tiers` parameter is required.
     */
    public enum PricingModel {
        VOLUME,
        TIERED
    }
}

