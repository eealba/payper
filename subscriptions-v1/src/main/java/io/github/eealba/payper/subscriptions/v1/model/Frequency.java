package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The frequency of the billing cycle.
 */
public class Frequency {


    @JasonerProperty("interval_unit")
    private final IntervalUnit intervalUnit;
    @JasonerProperty("interval_count")
    private final Integer intervalCount;

    private Frequency(Builder builder) {
        intervalCount = builder.intervalCount;
        intervalUnit = Objects.requireNonNull(builder.intervalUnit);
    }

    /**
     * The interval at which the subscription is charged or billed.
     */
    @JasonerProperty("interval_unit")
    public IntervalUnit intervalUnit() {
        return intervalUnit;
    }

    /**
     * The number of intervals after which a subscriber is billed. For example, if the `interval_unit` is `DAY` with
     * an `interval_count` of `2`, the subscription is billed once every two days. The following table lists the
     * maximum allowed values for the `interval_count` for each `interval_unit`:
     * <table>
     * <caption>Maximum interval count for each interval unit</caption>
     * <thead>
     * <tr><th><code>Interval unit</code></th><th>Maximum interval count</th></tr>
     * </thead>
     * <tbody>
     * <tr><td><code>DAY</code></td><td>365</td></tr>
     * <tr><td><code>WEEK</code></td><td>52</td></tr>
     * <tr><td><code>MONTH</code></td><td>12</td></tr>
     * <tr><td><code>YEAR</code></td><td>1</td></tr>
     * </tbody>
     * </table>
     */
    @JasonerProperty("interval_count")
    public Integer intervalCount() {
        return intervalCount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private IntervalUnit intervalUnit;
        private Integer intervalCount;

        /**
         * The interval at which the subscription is charged or billed.
         */
        @JasonerProperty("interval_unit")
        public Builder intervalUnit(IntervalUnit value) {
            this.intervalUnit = value;
            return this;
        }

        /**
         * The number of intervals after which a subscriber is billed. For example, if the `interval_unit` is `DAY` with
         * an `interval_count` of `2`, the subscription is billed once every two days. The following table lists the
         * maximum allowed values for the `interval_count` for each `interval_unit`:
         * <table>
         * <caption>Maximum interval count for each interval unit</caption>
         * <thead>
         * <tr><th><code>Interval unit</code></th><th>Maximum interval count</th></tr>
         * </thead>
         * <tbody>
         * <tr><td><code>DAY</code></td><td>365</td></tr>
         * <tr><td><code>WEEK</code></td><td>52</td></tr>
         * <tr><td><code>MONTH</code></td><td>12</td></tr>
         * <tr><td><code>YEAR</code></td><td>1</td></tr>
         * </tbody>
         * </table>
         */
        @JasonerProperty("interval_count")
        public Builder intervalCount(Integer value) {
            this.intervalCount = value;
            return this;
        }

        public Frequency build() {
            return new Frequency(this);
        }

    }
    /**
     * The interval at which the subscription is charged or billed.
     */
    public enum IntervalUnit {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }
}

