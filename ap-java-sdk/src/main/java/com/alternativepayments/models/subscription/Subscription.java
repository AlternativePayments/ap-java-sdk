package com.alternativepayments.models.subscription;

import org.joda.time.DateTime;

import com.alternativepayments.models.BaseModel;
import com.alternativepayments.models.customer.Customer;
import com.alternativepayments.models.plan.Plan;
import com.alternativepayments.models.transaction.Payment;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Subscription with plan and payment.
 */
public class Subscription extends BaseModel {

    /**
     * Endpoint for this resource.
     */
    public static final String API_ENDPOINT = "subscriptions/";

    private final Customer customer;
    private final String customerId;
    private final Payment payment;
    private final String paymentId;
    private final Plan plan;
    private final String planId;
    private final int amount;
    private final boolean isConversionRateFixed;
    private final int quantity;
    private final int currentBillingCycle;
    private final String ipAddress;
    private final String status;

    /**
     * Create new subscription using fields.
     *
     * @param id of subscription
     * @param mode mode used
     * @param created when is this subscription created
     * @param updated when is this subscription updated
     * @param customer customer of this subscription
     * @param customerId of customer
     * @param payment payment of this subscription
     * @param paymentId of payment option
     * @param plan plan of this subscription
     * @param planId of this plan
     * @param status status of this subscription
     * @param amount amount payed
     * @param quantity supports multiple payment amounts
     * @param isConversionRateFixed whether conversion rate is fixed or not
     * @param currentBillingCycle what is current billing cycle
     * @param ipAddress ip address of person creating subscription
     */
    @JsonCreator
    public Subscription(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final DateTime created,
            @JsonProperty("updated") final DateTime updated,
            @JsonProperty("customer") final Customer customer,
            @JsonProperty("customerId") final String customerId,
            @JsonProperty("payment") final Payment payment,
            @JsonProperty("paymentId") final String paymentId,
            @JsonProperty("plan") final Plan plan,
            @JsonProperty("planId") final String planId,
            @JsonProperty("amount") final int amount,
            @JsonProperty("isConversionRateFixed") final boolean isConversionRateFixed,
            @JsonProperty("quantity") final int quantity,
            @JsonProperty("currentBillingCycle") final int currentBillingCycle,
            @JsonProperty("ipAddress") final String ipAddress,
            @JsonProperty("status") final String status) {
        super(id, mode, created, updated);
        this.customer = customer;
        this.customerId = customerId;
        this.payment = payment;
        this.paymentId = paymentId;
        this.plan = plan;
        this.planId = planId;
        this.amount = amount;
        this.isConversionRateFixed = isConversionRateFixed;
        this.quantity = quantity;
        this.currentBillingCycle = currentBillingCycle;
        this.ipAddress = ipAddress;
        this.status = status;
    }

    /**
     * Builder class for Subscription.
     *
     */
    public static class Builder {
        // Required parameters
        private final int quantity;
        private final String ipAddress;

        // Optional parameters - initialize with default values
        private String planId;
        private String customerId;
        private String paymentId;
        private String status;
        private int amount;
        private boolean isConversionRateFixed;
        private int currentBillingCycle;
        private Plan plan;
        private Payment payment;
        private Customer customer;

        /**
         * Constructor that takes on all mandatory parameters.
         *
         * @param quantity supports multiple payment amounts
         * @param ipAddress ip address of person creating subscription
         */
        public Builder(final int quantity, final String ipAddress) {
            this.quantity = quantity;
            this.ipAddress = ipAddress;
        }

        /**
         * Set plan id for building object.
         *
         * @param planId id of plan for subscription
         *
         * @return Builder
         */
        public Builder planId(final String planId) {
            this.planId = planId;
            return this;
        }

        /**
         * Set plan for building object.
         *
         * @param plan of the subscription
         *
         * @return Builder
         */
        public Builder plan(final Plan plan) {
            this.plan = plan;
            return this;
        }

        /**
         * Set status for building object.
         *
         * @param status of the subscription
         *
         * @return Builder
         */
        public Builder status(final String status) {
            this.status = status;
            return this;
        }

        /**
         * Set customerId for building object.
         *
         * @param customerId of the subscription
         *
         * @return Builder
         */
        public Builder customerId(final String customerId) {
            this.customerId = customerId;
            return this;
        }

        /**
         * Set customer for building object.
         *
         * @param customer of the subscription
         *
         * @return Builder
         */
        public Builder customer(final Customer customer) {
            this.customer = customer;
            return this;
        }

        /**
         * Set paymentId for building object.
         *
         * @param paymentId of the subscription
         *
         * @return Builder
         */
        public Builder paymentId(final String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        /**
         * Set payment for building object.
         *
         * @param payment of the subscription
         *
         * @return Builder
         */
        public Builder payment(final Payment payment) {
            this.payment = payment;
            return this;
        }

        /**
         * Set amount for building object.
         *
         * @param amount of the subscription
         *
         * @return Builder
         */
        public Builder amount(final int amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Set whether conversion rate is fixed or not.
         *
         * @param isConversionRateFixed boolean flag for fixed conversion rate
         *
         * @return Builder
         */
        public Builder isConversionRateFixed(final boolean isConversionRateFixed) {
            this.isConversionRateFixed = isConversionRateFixed;
            return this;
        }

        /**
         * Set current billing cycle of subscription.
         *
         * @param currentBillingCycle current billing cycle of subscription
         *
         * @return Builder
         */
        public Builder currentBillingCycle(final int currentBillingCycle) {
            this.currentBillingCycle = currentBillingCycle;
            return this;
        }

        /**
         * Build method needed to be executed in order for Subscription to be created.
         *
         *
         * @return newly created Plan
         */
        public Subscription build() {
            return new Subscription(this);
        }

    }

    private Subscription(final Builder builder) {
        // Required parameters
        this.customer = builder.customer;
        this.payment = builder.payment;
        this.planId = builder.planId;
        this.quantity = builder.quantity;
        this.ipAddress = builder.ipAddress;

        // Optional parameters
        this.customerId = builder.customerId;
        this.paymentId = builder.paymentId;
        this.plan = builder.plan;
        this.status = builder.status;
        this.amount = builder.amount;
        this.isConversionRateFixed = builder.isConversionRateFixed;
        this.currentBillingCycle = builder.currentBillingCycle;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @return the payment
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * @return the paymentId
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * @return the plan
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * @return the planId
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return the isConversionRateFixed
     */
    public boolean isConversionRateFixed() {
        return isConversionRateFixed;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return the currentBillingCycle
     */
    public int getCurrentBillingCycle() {
        return currentBillingCycle;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
}
