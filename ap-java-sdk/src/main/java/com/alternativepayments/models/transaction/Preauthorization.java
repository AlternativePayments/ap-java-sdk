package com.alternativepayments.models.transaction;

import java.time.Instant;

import com.alternativepayments.models.BaseModel;
import com.alternativepayments.models.customer.Customer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Preauthorization for transaction.
 */
public class Preauthorization extends BaseModel {

    /**
     * Endpoint for this resource.
     */
    public static final String API_ENDPOINT = "preauthorizations/";

    private final Customer customer;
    private final Payment payment;
    private final int amount;
    private final String currnecy;

    /**
     * Create new Preauthorization using fields.
     *
     * @param id of Preauthorization
     * @param mode mode used
     * @param created when is this Preauthorization created
     * @param updated when is this Preauthorization updated
     * @param customer key for Phone Verification
     * @param payment payment for Phone Verification
     * @param amount for Phone Verification
     * @param currnecy for Phone Verification
     */
    @JsonCreator
    public Preauthorization(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final Instant created,
            @JsonProperty("updated") final Instant updated,
            @JsonProperty("customer") final Customer customer,
            @JsonProperty("payment") final Payment payment,
            @JsonProperty("amount") final int amount,
            @JsonProperty("currnecy") final String currnecy) {
        super(id, mode, created, updated);
        this.customer = customer;
        this.payment = payment;
        this.amount = amount;
        this.currnecy = currnecy;
    }

    /**
     * Create new Preauthorization using fields.
     *
     * @param customer key for Phone Verification
     * @param payment payment for Phone Verification
     * @param amount for Phone Verification
     * @param currnecy for Phone Verification
     */
    public Preauthorization(final Customer customer, final Payment payment, final int amount,
            final String currnecy) {
        this.customer = customer;
        this.payment = payment;
        this.amount = amount;
        this.currnecy = currnecy;
    }

    /**
     * @return customer for Preauthorization.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @return payment for Preauthorization.
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * @return amount for Preauthorization.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return currency for Preauthorization.
     */
    public String getCurrnecy() {
        return currnecy;
    }


}
