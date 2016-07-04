package com.alternativepayments.models.transaction;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.alternativepayments.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Refund of transaction.
 */
public class TransactionRefund extends BaseModel {

    private static final String API_ENDPOINT = "transactions/%s/refunds/";

    private final BigDecimal amount;
    private final String currency;
    private final ReturnReason reason;
    private final String originalTransactionId;
    private final Transaction originalTransaction;
    private final String status;

    /**
     * Create new Refund using all fields.
     *
     * @param id of refund
     * @param mode mode used
     * @param created when is this payment created
     * @param updated when is this payment updated
     * @param amount amount to refund
     * @param currency currency for amount
     * @param reason return reason
     * @param originalTransactionId id of transaction being refunded
     * @param originalTransaction transaction being refunded
     * @param status of this refund
     */
    @JsonCreator
    public TransactionRefund(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final DateTime created,
            @JsonProperty("updated") final DateTime updated,
            @JsonProperty("amount") final BigDecimal amount,
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
     * Builder class for Refund.
     *
     */
    public static class Builder {
        // Required parameters
        private final String originalTransactionId;
        private final ReturnReason reason;

        // Optional parameters - initialize with default values
        private Transaction originalTransaction;
        private BigDecimal amount;
        private String currency;
        private String status;


        /**
         * Constructor that takes on all mandatory parameters.
         *
         * @param reason reason to refund transaction
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
         * @param amount amount to refund
         *
         * @return Builder
         */
        public Builder amount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Set currency for building object.
         *
         * @param currency currency of amount being refunded
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
         * @param status status of this refund
         *
         * @return Builder
         */
        public Builder status(final String status) {
            this.status = status;
            return this;
        }

        /**
         * Build method needed to be executed in order for refund to be created.
         *
         *
         * @return newly created payment
         */
        public TransactionRefund build() {
            return new TransactionRefund(this);
        }

    }

    private TransactionRefund(final Builder builder) {
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
    public BigDecimal getAmount() {
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
     * Get endpoint for refund which is sub resource of transaction.
     *
     * @param transactionId Id of transaction.
     * @return endpoint URL built for this transaction.
     */
    public static String getApiEndpoint(final String transactionId) {
        return String.format(API_ENDPOINT, transactionId);
    }
}
