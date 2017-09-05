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

    private final String name;
    private final String description;
    private final int amount;
    private final String currency;
    private final Period intervalUnit;
    private final int intervalCount;
    private final int billingCycles;
    private final boolean isConversionRateFixed;
    private final String ipAddress;
    private final int trialPeriod;
    private final boolean cancelSubscriptions;

    /**
     * Create new plan using fields.
     *
     * @param id of plan
     * @param mode mode used
     * @param created when is this plan created
     * @param updated when is this plan updated
     * @param name name of plan
     * @param description description of plan
     * @param amount amount of money
     * @param currency currency for plan
     * @param intervalUnit period to use for interval count (hour, day, month)
     * @param intervalCount number which defines when the charge will be created (in interval units)
     * @param billingCycles number of times plan is to be executed
     * @param isConversionRateFixed are conversions rates fixed
     * @param ipAddress ip address of person doing billing
     * @param trialPeriod how many interval units will trial period last
     * @param cancelSubscriptions option to cancel all associated subscriptions
     */
    @JsonCreator
    public Plan(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final DateTime created,
            @JsonProperty("updated") final DateTime updated,
            @JsonProperty("name") final String name,
            @JsonProperty("description") final String description,
            @JsonProperty("amount") final int amount,
            @JsonProperty("currency") final String currency,
            @JsonProperty("intervalUnit") final Period intervalUnit,
            @JsonProperty("intervalCount") final int intervalCount,
            @JsonProperty("billingCycles") final int billingCycles,
            @JsonProperty("isConversionRateFixed") final boolean isConversionRateFixed,
            @JsonProperty("ipAddress") final String ipAddress,
            @JsonProperty("trialPeriod") final int trialPeriod,
            @JsonProperty("cancelSubscriptions") final boolean cancelSubscriptions) {
        super(id, mode, created, updated);
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.intervalUnit = intervalUnit;
        this.intervalCount = intervalCount;
        this.billingCycles = billingCycles;
        this.isConversionRateFixed = isConversionRateFixed;
        this.ipAddress = ipAddress;
        this.trialPeriod = trialPeriod;
        this.cancelSubscriptions = cancelSubscriptions;
    }

    /**
     * Builder class for Plan.
     *
     */
    public static class Builder {
        // Required parameters
        private final String name;
        private final int amount;
        private Period intervalUnit;
        private int intervalCount;
        private int billingCycles;
        private String ipAddress;

        // Optional parameters - initialize with default values
        private String description;
        private String currency;
        private int trialPeriod;
        private boolean isConversionRateFixed;
        private boolean cancelSubscriptions;

        /**
         * Constructor that takes on all mandatory parameters.
         *
         * @param name name of the plan
         * @param amount amount for the plan
         * @param intervalUnit period to use for interval count (hour, day, month)
         * @param intervalCount number which defines when the charge will be created (in interval units)
         * @param billingCycles number of times plan is to be executed
         * @param ipAddress ip address of person creating plan
         */
        public Builder(final String name, final int amount, final Period intervalUnit, final int intervalCount,
                final int billingCycles, final String ipAddress) {
            this.name = name;
            this.amount = amount;
            this.intervalUnit = intervalUnit;
            this.intervalCount = intervalCount;
            this.billingCycles = billingCycles;
            this.ipAddress = ipAddress;
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
         * Set currency for building object.
         *
         * @param currency of the plan
         *
         * @return Builder
         */
        public Builder currency(final String currency) {
            this.currency = currency;
            return this;
        }

        /**
         * Set trialPeriod for building object.
         *
         * @param trialPeriod of the plan
         *
         * @return Builder
         */
        public Builder trialPeriod(final int trialPeriod) {
            this.trialPeriod = trialPeriod;
            return this;
        }

        /**
         * Set isConversionRateFixed for building object.
         *
         * @param isConversionRateFixed of the plan
         *
         * @return Builder
         */
        public Builder isConversionRateFixed(final boolean isConversionRateFixed) {
            this.isConversionRateFixed = isConversionRateFixed;
            return this;
        }

        /**
         * Set this to true to cancel all associated subscriptions.
         *
         * @param cancelSubscriptions to cancel subscription or not.
         *
         * @return Builder
         */
        public Builder cancelSubscriptions(final boolean cancelSubscriptions) {
            this.cancelSubscriptions = cancelSubscriptions;
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
        this.intervalUnit = builder.intervalUnit;
        this.intervalCount = builder.intervalCount;
        this.billingCycles = builder.billingCycles;
        this.ipAddress = builder.ipAddress;

        // Optional parameters
        this.description = builder.description;
        this.currency = builder.currency;
        this.trialPeriod = builder.trialPeriod;
        this.isConversionRateFixed = builder.isConversionRateFixed;
        this.cancelSubscriptions = builder.cancelSubscriptions;
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
     * @return the intervalUnit
     */
    public Period getIntervalUnit() {
        return intervalUnit;
    }

    /**
     * @return the intervalCount
     */
    public int getIntervalCount() {
        return intervalCount;
    }

    /**
     * @return the billingCycles
     */
    public int getBillingCycles() {
        return billingCycles;
    }

    /**
     * @return the isConversionRateFixed
     */
    public boolean isConversionRateFixed() {
        return isConversionRateFixed;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @return the trialPeriod
     */
    public int getTrialPeriod() {
        return trialPeriod;
    }

    /**
     * @return the cancelSubscriptions
     */
    public boolean isCancelSubscriptions() {
        return cancelSubscriptions;
    }

    /**
     * Enumeration for supported error type.
     */
    public enum Period {
        /**
         * Day period.
         */
        DAY("day"),
        /**
         * Week period.
         */
        WEEK("week"),
        /**
         * Month period.
         */
        MONTH("month"),
        /**
         * Year period.
         */
        YEAR("year");

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
