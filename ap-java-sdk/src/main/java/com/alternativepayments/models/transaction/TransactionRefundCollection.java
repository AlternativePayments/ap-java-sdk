package com.alternativepayments.models.transaction;

import java.util.List;

import com.alternativepayments.models.BaseCollection;
import com.alternativepayments.models.Pagination;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * List of transaction refunds.
 */
public class TransactionRefundCollection extends BaseCollection {

    private final List<TransactionVoid> refundTransactions;

    /**
     * Transaction refund collection with pagination.
     *
     * @param refundTransactions list of transaction refunds.
     * @param pagination pagination meta object.
     */
    @JsonCreator
    public TransactionRefundCollection(
            @JsonProperty("refundTransactions") final List<TransactionVoid> refundTransactions,
            @JsonProperty("pagination") final Pagination pagination) {
        super(pagination);
        this.refundTransactions = refundTransactions;
    }

    /**
     * @return the transaction refunds
     */
    public List<TransactionVoid> getRefundTransactions() {
        return refundTransactions;
    }
}
