package com.alternativepayments.models.transaction;

import java.util.List;

import com.alternativepayments.models.BaseCollection;
import com.alternativepayments.models.Pagination;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * List of transactions.
 */
public class TransactionCollection extends BaseCollection {

    private final List<Transaction> transactions;

    /**
     * Transaction collection with pagination.
     *
     * @param transactions list of transactions.
     * @param pagination pagination meta object.
     */
    @JsonCreator
    public TransactionCollection(
            @JsonProperty("transactions") final List<Transaction> transactions,
            @JsonProperty("pagination") final Pagination pagination) {
        super(pagination);
        this.transactions = transactions;
    }

    /**
     * @return the transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
