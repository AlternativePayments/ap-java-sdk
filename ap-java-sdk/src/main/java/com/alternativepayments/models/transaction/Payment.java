package com.alternativepayments.models.transaction;

import org.joda.time.DateTime;

import com.alternativepayments.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Payment for transaction.
 */
public class Payment extends BaseModel {

    /**
     * Endpoint for this resource.
     */
    public static final String API_ENDPOINT = "payments/";

    private final String paymentOption;
    private final String holder;
    private final String iban;
    private final String bankCode;
    private final String documentId;
    private final String creditCardNumber;
    private final int CVV2;
    private final int expirationYear;
    private final int expirationMonth;
    private final String creditCardType;
    private final String bic;
    private final String pinCode;

    /**
     * Create new Payment using all fields.
     *
     * @param id of payment
     * @param mode mode used
     * @param created when is this payment created
     * @param updated when is this payment updated
     * @param paymentOption witch option is payment
     * @param holder holder of payment
     * @param iban iban of payment
     * @param bankCode bank code of Bank processing payment
     * @param documentId document id of payment
     * @param creditCardNumber number of credit card for payment
     * @param CVV2 CVV2 code for card
     * @param expirationYear year of expiration for card
     * @param expirationMonth month of expiration for card
     * @param creditCardType type of credit card used for payment
     * @param bic bic for payment
     * @param pinCode pin code for payment
     */
    @JsonCreator
    public Payment(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final DateTime created,
            @JsonProperty("updated") final DateTime updated,
            @JsonProperty("paymentOption") final String paymentOption,
            @JsonProperty("holder") final String holder,
            @JsonProperty("iban") final String iban,
            @JsonProperty("bankCode") final String bankCode,
            @JsonProperty("documentId") final String documentId,
            @JsonProperty("creditCardNumber") final String creditCardNumber,
            @JsonProperty("CVV2") final int CVV2,
            @JsonProperty("expirationYear") final int expirationYear,
            @JsonProperty("expirationMonth") final int expirationMonth,
            @JsonProperty("creditCardType") final String creditCardType,
            @JsonProperty("bic") final String bic,
            @JsonProperty("pinCode") final String pinCode) {
        super(id, mode, created, updated);
        this.paymentOption = paymentOption;
        this.holder = holder;
        this.iban = iban;
        this.bankCode = bankCode;
        this.documentId = documentId;
        this.creditCardNumber = creditCardNumber;
        this.CVV2 = CVV2;
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
        this.creditCardType = creditCardType;
        this.bic = bic;
        this.pinCode = pinCode;
    }

    /**
     * Builder class for Payment.
     *
     */
    public static class Builder {
        // Required parameters
        private final String paymentOption;
        private final String holder;

        // Optional parameters - initialize with default values
        private  String iban;
        private  String bankCode;
        private  String documentId;
        private  String creditCardNumber;
        private  int CVV2;
        private  int expirationYear;
        private  int expirationMonth;
        private  String creditCardType;
        private  String bic;
        private String pinCode;


        /**
         * Constructor that takes on all mandatory parameters.
         *
         * @param paymentOption option of payment
         * @param holder holder for payment
         */
        public Builder(final String paymentOption, final String holder) {
            this.paymentOption = paymentOption;
            this.holder = holder;
        }

        /**
         * Set iban for building object.
         *
         * @param iban of the payment
         *
         * @return Builder
         */
        public Builder iban(final String iban) {
            this.iban = iban;
            return this;
        }

        /**
         * Set bankCode for building object.
         *
         * @param bankCode of the payment
         *
         * @return Builder
         */
        public Builder bankCode(final String bankCode) {
            this.bankCode = bankCode;
            return this;
        }

        /**
         * Set documentId for building object.
         *
         * @param documentId of the payment
         *
         * @return Builder
         */
        public Builder documentId(final String documentId) {
            this.documentId = documentId;
            return this;
        }

        /**
         * Set creditCardNumber for building object.
         *
         * @param creditCardNumber of the payment
         *
         * @return Builder
         */
        public Builder creditCardNumber(final String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
            return this;
        }

        /**
         * Set CVV2 for building object.
         *
         * @param CVV2 of the payment
         *
         * @return Builder
         */
        public Builder cvv2(final int CVV2) {
            this.CVV2 = CVV2;
            return this;
        }

        /**
         * Set expirationYear for building object.
         *
         * @param expirationYear of the payment
         *
         * @return Builder
         */
        public Builder expirationYear(final int expirationYear) {
            this.expirationYear = expirationYear;
            return this;
        }

        /**
         * Set expirationMonth for building object.
         *
         * @param expirationMonth of the payment
         *
         * @return Builder
         */
        public Builder expirationMonth(final int expirationMonth) {
            this.expirationMonth = expirationMonth;
            return this;
        }

        /**
         * Set creditCardType for building object.
         *
         * @param creditCardType of the payment
         *
         * @return Builder
         */
        public Builder creditCardType(final String creditCardType) {
            this.creditCardType = creditCardType;
            return this;
        }

        /**
         * Set bic for building object.
         *
         * @param bic of the payment
         *
         * @return Builder
         */
        public Builder bic(final String bic) {
            this.bic = bic;
            return this;
        }

        /**
         * Set pinCode for building object.
         *
         * @param pinCode of the payment
         *
         * @return Builder
         */
        public Builder pinCode(final String pinCode) {
            this.pinCode = pinCode;
            return this;
        }

        /**
         * Build method needed to be executed in order for Payment to be created.
         *
         *
         * @return newly created payment
         */
        public Payment build() {
            return new Payment(this);
        }

    }

    private Payment(final Builder builder) {
        // Required parameters
        this.paymentOption = builder.paymentOption;
        this.holder = builder.holder;

        // Optional parameters
        this.iban = builder.iban;
        this.bankCode = builder.bankCode;
        this.documentId = builder.documentId;
        this.creditCardNumber = builder.creditCardNumber;
        this.CVV2 = builder.CVV2;
        this.expirationYear = builder.expirationYear;
        this.expirationMonth = builder.expirationMonth;
        this.creditCardType = builder.creditCardType;
        this.bic = builder.bic;
        this.pinCode = builder.pinCode;
    }

    /**
     * @return option for payment.
     */
    public String getPaymentOption() {
        return paymentOption;
    }

    /**
     * @return holder for payment.
     */
    public String getHolder() {
        return holder;
    }

    /**
     * @return iban for payment.
     */
    public String getIban() {
        return iban;
    }

    /**
     * @return bank code for payment.
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * @return document id for payment.
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * @return credit card nubmer for payment.
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * @return CVV2 for payment.
     */
    public int getCVV2() {
        return CVV2;
    }

    /**
     * @return expiration year for payment.
     */
    public int getExpirationYear() {
        return expirationYear;
    }

    /**
     * @return expiration month for payment.
     */
    public int getExpirationMonth() {
        return expirationMonth;
    }

    /**
     * @return type of credit card used for payment.
     */
    public String getCreditCardType() {
        return creditCardType;
    }

    /**
     * @return bic for payment.
     */
    public String getBic() {
        return bic;
    }

    /**
     * @return pinCode for payment.
     */
    public String getPinCode() {
        return pinCode;
    }

}
