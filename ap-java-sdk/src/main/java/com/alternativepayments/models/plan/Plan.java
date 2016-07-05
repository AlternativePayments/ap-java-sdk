package com.alternativepayments.models.plan;

import org.joda.time.DateTime;

import com.alternativepayments.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Plan for subscription.
 */
public class Plan extends BaseModel {

    /**
     * Endpoint for this resource.
     */
    public static final String API_ENDPOINT = "plans/";

    private final int interval;
    private final Period period;
    private final int amount;
    private final String currency;
    private final String name;
    private final String description;

    /**
     * Create new plan using fields.
     *
     * @param id of plan
     * @param mode mode used
     * @param created when is this plan created
     * @param updated when is this plan updated
     * @param interval interval in defined period
     * @param period period (day, month)
     * @param amount amount of money
     * @param currency currency for plan
     * @param name name of plan
     * @param description description of plan
     */
    @JsonCreator
    public Plan(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final DateTime created,
            @JsonProperty("updated") final DateTime updated,
            @JsonProperty("interval") final int interval,
            @JsonProperty("period") final Period period,
            @JsonProperty("amount") final int amount,
            @JsonProperty("currency") final String currency,
            @JsonProperty("name") final String name,
            @JsonProperty("description") final String description) {
        super(id, mode, created, updated);
        this.interval = interval;
        this.period = period;
        this.amount = amount;
        this.currency = currency;
        this.name = name;
        this.description = description;
    }

    /**
     * Builder class for Plan.
     *
     */
    public static class Builder {
        // Required parameters
        private final String name;
        private final int amount;
        private final String currency;
        private final int interval;
        private final Period period;

        // Optional parameters - initialize with default values
        private String description;

        /**
         * Constructor that takes on all mandatory parameters.
         *
         * @param name name of the plan
         * @param amount amount for the plan
         * @param currency currency for the plan
         * @param interval interval for the plan
         * @param period period for the plan
         */
        public Builder(final String name, final int amount, final String currency, final int interval,
                final Period period) {
            this.name = name;
            this.amount = amount;
            this.currency = currency;
            this.interval = interval;
            this.period = period;
        }

        /**
         * Set description for building object.
         *
         * @param description of the plan
         *
         * @return Builder
         */
        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * Build method needed to be executed in order for Plan to be created.
         *
         *
         * @return newly created Plan
         */
        public Plan build() {
            return new Plan(this);
        }

    }

    private Plan(final Builder builder) {
        // Required parameters
        this.name = builder.name;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.interval = builder.interval;
        this.period = builder.period;

        // Optional parameters
        this.description = builder.description;
    }

    /**
     * @return the interval
     */
    public int getInterval() {
        return interval;
    }

    /**
     * @return the period
     */
    public Period getPeriod() {
        return period;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Enumeration for supported error type.
     */
    public enum Period {
        /**
         * Day period.
         */
        DAY("Day"),
        /**
         * Week period.
         */
        WEEK("Week"),
        /**
         * Month period.
         */
        MONTH("Month"),
        /**
         * Year period.
         */
        YEAR("Year");

        private final String value;

        private Period(final String value) {
            this.value = value;
        }

        @JsonValue
        final String value() {
            return this.value;
        }
    }
}
