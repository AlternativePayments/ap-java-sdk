package com.alternativepayments.models;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Customer for transaction.
 */
public class Customer extends BaseModel {

    /**
     * Endpoint for this resource.
     */
    public static final String API_ENDPOINT = "customers/";

    private final String firstName;
    private final String lastName;

    /**
     * Create new customer using fields.
     *
     * @param id first name of customer
     * @param mode mode used
     * @param created when is this customer created
     * @param updated when is this customer updated
     * @param firstName first name of customer
     * @param lastName last name of customer
     */
    @JsonCreator
    public Customer(
            @JsonProperty("id") String id,
            @JsonProperty("mode") String mode,
            @JsonProperty("created") Instant created,
            @JsonProperty("updated") Instant updated,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName) {
        super(id, mode, created, updated);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return first name of customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return last name of customer.
     */
    public String getLastName() {
        return lastName;
    }

}
