package com.alternativepayments.models.transaction;

import org.joda.time.DateTime;

import com.alternativepayments.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Void of transaction.
 */
public class TransactionVoid extends BaseModel {

    private static final String API_ENDPOINT = "transactions/%s/voids/";

    private final int amount;
    private final String currency;
    private final ReturnReason reason;
    private final String originalTransactionId;
    private final Transaction originalTransaction;
    private final String status;

    /**
     * Create new Void using all fields.
     *
     * @param id of void
     * @param mode mode used
     * @param created when is this payment created
     * @param updated when is this payment updated
     * @param amount amount to void
     * @param currency currency for amount
     * @param reason return reason
     * @param originalTransactionId id of transaction being voided
     * @param originalTransaction transaction being voided
     * @param status of this void
     */
    @JsonCreator
    public TransactionVoid(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final DateTime created,
            @JsonProperty("updated") final DateTime updated,
            @JsonProperty("amount") final int amount,
            @JsonProperty("currency") final String currency,
            @JsonProperty("reason") final ReturnReason reason,
            @JsonProperty("originalTransactionId") final String originalTransactionId,
            @JsonProperty("originalTransaction") final Transaction originalTransaction,
            @JsonProperty("status") final String status) {
        super(id, mode, created, updated);
        this.amount = amount;
        this.currency = currency;
        this.reason = reason;
        this.originalTransactionId = originalTransactionId;
        this.originalTransaction = originalTransaction;
        this.status = status;
    }

    /**
     * Builder class for Void.
     *
     */
    public static class Builder {
        // Required parameters
        private final String originalTransactionId;
        private final ReturnReason reason;

        // Optional parameters - initialize with default values
        private Transaction originalTransaction;
        private int amount;
        private String currency;
        private String status;


        /**
         * Constructor that takes on all mandatory parameters.
         *
         * @param reason reason to void transaction
         * @param originalTransactionId id of original transaction
         */
        public Builder(final ReturnReason reason, final String originalTransactionId) {
            this.reason = reason;
            this.originalTransactionId = originalTransactionId;
        }

        /**
         * Set originalTransaction for building object.
         *
         * @param originalTransaction original transaction
         *
         * @return Builder
         */
        public Builder originalTransaction(final Transaction originalTransaction) {
            this.originalTransaction = originalTransaction;
            return this;
        }

        /**
         * Set amount for building object.
         *
         * @param amount amount to void
         *
         * @return Builder
         */
        public Builder amount(final int amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Set currency for building object.
         *
         * @param currency currency of amount being voided
         *
         * @return Builder
         */
        public Builder currency(final String currency) {
            this.currency = currency;
            return this;
        }

        /**
         * Set status for building object.
         *
         * @param status status of this void
         *
         * @return Builder
         */
        public Builder status(final String status) {
            this.status = status;
            return this;
        }

        /**
         * Build method needed to be executed in order for Void to be created.
         *
         *
         * @return newly created payment
         */
        public TransactionVoid build() {
            return new TransactionVoid(this);
        }

    }

    private TransactionVoid(final Builder builder) {
        // Required parameters
        this.originalTransactionId = builder.originalTransactionId;
        this.reason = builder.reason;

        // Optional parameters
        this.originalTransaction = builder.originalTransaction;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.status = builder.status;
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
     * @return the reason
     */
    public ReturnReason getReason() {
        return reason;
    }

    /**
     * @return the originalTransactionId
     */
    public String getOriginalTransactionId() {
        return originalTransactionId;
    }

    /**
     * @return the originalTransaction
     */
    public Transaction getOriginalTransaction() {
        return originalTransaction;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get endpoint for void which is sub resource of transaction.
     *
     * @param transactionId Id of transaction.
     * @return endpoint URL built for this transaction.
     */
    public static String getApiEndpoint(final String transactionId) {
        return String.format(API_ENDPOINT, transactionId);
    }
}
