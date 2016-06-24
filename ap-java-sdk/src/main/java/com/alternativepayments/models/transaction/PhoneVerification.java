package com.alternativepayments.models.transaction;

import java.time.Instant;

import com.alternativepayments.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PhoneVerification for transaction.
 */
public class PhoneVerification extends BaseModel {

    /**
     * Endpoint for this resource.
     */
    public static final String API_ENDPOINT = "phoneverification/";

    private final String key;
    private final String  phone;
    private final String type;
    private final String token;
    private final int pin;

    /**
     * Create new Phone Verification using fields.
     *
     * @param id of Phone Verification
     * @param mode mode used
     * @param created when is this Phone Verification created
     * @param updated when is this Phone Verification updated
     * @param key key for Phone Verification
     * @param phone phone for Phone Verification
     * @param type type of Phone Verification
     * @param token token for Phone Verification
     * @param pin pin used for Phone Verification
     */
    @JsonCreator
    public PhoneVerification(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final Instant created,
            @JsonProperty("updated") final Instant updated,
            @JsonProperty("key") final String key,
            @JsonProperty("phone") final String phone,
            @JsonProperty("type") final String type,
            @JsonProperty("token") final String token,
            @JsonProperty("pin") final int pin) {
        super(id, mode, created, updated);
        this.key = key;
        this.phone = phone;
        this.type = type;
        this.token = token;
        this.pin = pin;
    }

    /**
     * Builder class for Phone Verification.
     *
     */
    public static class Builder {
        // Required parameters
        private final String key;
        private final String phone;

        // Optional parameters - initialize with default values
        private String type;
        private String token;
        private int pin;

        /**
         * Constructor that takes on all mandatory parameters.
         *
         * @param key key of the Phone Verification
         * @param phone used for the Phone Verification
         */
        public Builder(final String key, final String phone) {
            this.key = key;
            this.phone = phone;
        }

        /**
         * Set token for building object.
         *
         * @param token of the phone verification
         *
         * @return Builder
         */
        public Builder token(final String token) {
            this.token = token;
            return this;
        }

        /**
         * Set type for building object.
         *
         * @param type of the phone verification
         *
         * @return Builder
         */
        public Builder type(final String type) {
            this.type = type;
            return this;
        }

        /**
         * Set pin for building object.
         *
         * @param pin of the phone verification
         *
         * @return Builder
         */
        public Builder pin(final int pin) {
            this.pin = pin;
            return this;
        }

        /**
         * Build method needed to be executed in order for Phone verification to be created.
         *
         *
         * @return newly created PhoneVerification
         */
        public PhoneVerification build() {
            return new PhoneVerification(this);
        }

    }

    private PhoneVerification(final Builder builder) {
        // Required parameters
        this.key = builder.key;
        this.phone = builder.phone;

        // Optional parameters
        this.type = builder.type;
        this.token = builder.token;
        this.pin = builder.pin;
    }

    /**
     * @return key for phone verification.
     */
    public String getKey() {
        return key;
    }

    /**
     * @return phone for phone verification.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return type for phone verification.
     */
    public String getType() {
        return type;
    }

    /**
     * @return token for phone verification.
     */
    public String getToken() {
        return token;
    }

    /**
     * @return pin for phone verification.
     */
    public int getPin() {
        return pin;
    }
}
