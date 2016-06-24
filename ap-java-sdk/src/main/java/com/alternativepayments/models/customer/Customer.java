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
     * @param id of customer
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
     * Builder class for Customer.
     *
     */
    public static class Builder {
        // Required parameters
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String country;

        // Optional parameters - initialize with default values
        private String address;
        private String address2;
        private String city;
        private String zip;
        private String state;
        private String phone;
        private String birthDate;

        /**
         * Constructor that takes on all mandatory parameters.
         *
         * @param firstName name of the customer
         * @param lastName last name of the customer
         * @param email email of the customer
         * @param country country of the customer
         */
        public Builder(final String firstName, final String lastName, final String email, final String country) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.country = country;
        }

        /**
         * Set address for building object.
         *
         * @param address of the customer
         *
         * @return Builder
         */
        public Builder address(final String address) {
            this.address = address;
            return this;
        }

        /**
         * Set secondary address for building object.
         *
         * @param address2 of the customer
         *
         * @return Builder
         */
        public Builder address2(final String address2) {
            this.address2 = address2;
            return this;
        }

        /**
         * Set city for building object.
         *
         * @param city of the customer
         *
         * @return Builder
         */
        public Builder city(final String city) {
            this.city = city;
            return this;
        }

        /**
         * Set zip for building object.
         *
         * @param zip of the customer
         *
         * @return Builder
         */
        public Builder zip(final String zip) {
            this.zip = zip;
            return this;
        }

        /**
         * Set state for building object.
         *
         * @param state of the customer
         *
         * @return Builder
         */
        public Builder state(final String state) {
            this.state = state;
            return this;
        }

        /**
         * Set phone for building object.
         *
         * @param phone of the customer
         *
         * @return Builder
         */
        public Builder phone(final String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * Set birthDate for building object.
         *
         * @param birthDate of the customer
         *
         * @return Builder
         */
        public Builder birthDate(final String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        /**
         * Build method needed to be executed in order for Customer to be created.
         *
         *
         * @return newly created Customer
         */
        public Customer build() {
            return new Customer(this);
        }

    }

    private Customer(final Builder builder) {
        // Required parameters
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.country = builder.country;

        // Optional parameters
        this.address = builder.address;
        this.address2 = builder.address2;
        this.city = builder.city;
        this.zip = builder.zip;
        this.state = builder.state;
        this.phone = builder.phone;
        this.birthDate = builder.birthDate;
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
