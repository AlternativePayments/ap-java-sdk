package com.alternativepayments.models.customer;

import java.time.Instant;

import com.alternativepayments.models.BaseModel;
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
    private final String email;
    private final String address;
    private final String address2;
    private final String city;
    private final String zip;
    private final String country;
    private final String state;
    private final String phone;
    private final String birthDate;

    /**
     * Create new customer using fields.
     *
     * @param id first name of customer
     * @param mode mode used
     * @param created when is this customer created
     * @param updated when is this customer updated
     * @param firstName first name of customer
     * @param lastName last name of customer
     * @param email email of customer
     * @param address address of customer
     * @param address2 secondary address of customer
     * @param city city of customer
     * @param zip zip code of customer
     * @param country country code of customer
     * @param state state code of customer
     * @param phone phone number of customer
     * @param birthDate birth date of customer
     */
    @JsonCreator
    public Customer(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final Instant created,
            @JsonProperty("updated") final Instant updated,
            @JsonProperty("firstName") final String firstName,
            @JsonProperty("lastName") final String lastName,
            @JsonProperty("email") final String email,
            @JsonProperty("address") final String address,
            @JsonProperty("address2") final String address2,
            @JsonProperty("city") final String city,
            @JsonProperty("zip") final String zip,
            @JsonProperty("country") final String country,
            @JsonProperty("state") final String state,
            @JsonProperty("phone") final String phone,
            @JsonProperty("birthDate") final String birthDate) {
        super(id, mode, created, updated);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.state = state;
        this.phone = phone;
        this.birthDate = birthDate;
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

    /**
     * @return email of customer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return address of customer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return address2 of customer.
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @return city of customer.
     */
    public String getCity() {
        return city;
    }

    /**
     * @return zip code of customer.
     */
    public String getZip() {
        return zip;
    }

    /**
     * @return country of customer.
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return state of customer.
     */
    public String getState() {
        return state;
    }

    /**
     * @return phone of customer.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return birth date of customer.
     */
    public String getBirthDate() {
        return birthDate;
    }

}
