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
    private final String status;

    /**
     * Create new subscription using fields.
     *
     * @param id of plan
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
            @JsonProperty("status") final String status) {
        super(id, mode, created, updated);
        this.customer = customer;
        this.customerId = customerId;
        this.payment = payment;
        this.paymentId = paymentId;
        this.plan = plan;
        this.planId = planId;
        this.status = status;
    }

    /**
     * Builder class for Subscription.
     *
     */
    public static class Builder {
        // Required parameters
        private final String customerId;
        private final String paymentId;
        private final String planId;

        // Optional parameters - initialize with default values
        private String status;
        private Plan plan;
        private Customer customer;
        private Payment payment;

        /**
         * Constructor that takes on all mandatory parameters.
         *
         * @param customerId customer id for the subscription
         * @param paymentId payment id for the subscription
         * @param planId plan id for the subscription
         */
        public Builder(final String customerId, final String paymentId, final String planId) {
            this.customerId = customerId;
            this.paymentId = paymentId;
            this.planId = planId;
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
        this.customerId = builder.customerId;
        this.paymentId = builder.paymentId;
        this.planId = builder.planId;

        // Optional parameters
        this.customer = builder.customer;
        this.payment = builder.payment;
        this.plan = builder.plan;
        this.status = builder.status;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }
}
