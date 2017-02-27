package com.alternativepayments.models.website;

import org.joda.time.DateTime;

import com.alternativepayments.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Payment Options for website.
 *
 */
public class PaymentOption extends BaseModel {

    /**
     * Endpoint for this resource.
     */
    public static final String API_ENDPOINT = "paymentoptions/%s";

    private final boolean hasSmsVerification;
    private final String url;

    /**
     * Create new Void using all fields.
     *
     * @param id of void
     * @param mode mode used
     * @param created when is this payment created
     * @param updated when is this payment updated
     * @param hasSmsVerification is SMS verification on for payment option
     * @param url url
     */
    @JsonCreator
    public PaymentOption(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final DateTime created,
            @JsonProperty("updated") final DateTime updated,
            @JsonProperty("hasSmsVerification") final boolean hasSmsVerification,
            @JsonProperty("url") final String url) {
        super(id, mode, created, updated);
        this.hasSmsVerification = hasSmsVerification;
        this.url = url;
    }

    /**
     * Builder class for PaymentOption.
     *
     */
    public static class Builder {
        // Optional parameters - initialize with default values
        private boolean hasSmsVerification;
        private String url;

        /**
         * Constructor that takes on all mandatory parameters.
         *
         */
        public Builder() {
        }

        /**
         * Set hasSmsVerification for building object.
         *
         * @param hasSmsVerification sms verification
         *
         * @return Builder
         */
        public Builder hasSmsVerification(final boolean hasSmsVerification) {
            this.hasSmsVerification = hasSmsVerification;
            return this;
        }

        /**
         * Set url for building object.
         *
         * @param url url of website
         *
         * @return Builder
         */
        public Builder url(final String url) {
            this.url = url;
            return this;
        }

        /**
         * Build method needed to be executed in order for Void to be created.
         *
         *
         * @return newly created payment
         */
        public PaymentOption build() {
            return new PaymentOption(this);
        }

    }

    private PaymentOption(final Builder builder) {
        this.hasSmsVerification = builder.hasSmsVerification;
        this.url = builder.url;
    }

    /**
     * @return the hasSmsVerification
     */
    public boolean isHasSmsVerification() {
        return hasSmsVerification;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get endpoint for payment options which is sub resource of website.
     *
     * @param paymentOption payment option type.
     *
     * @return paymentOption payment option.
     */
    public static String getApiEndpoint(final String paymentOption) {
        return String.format(API_ENDPOINT, paymentOption);
    }

}
