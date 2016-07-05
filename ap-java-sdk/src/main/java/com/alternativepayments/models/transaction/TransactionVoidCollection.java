package com.alternativepayments.models.transaction;

import java.util.List;

import com.alternativepayments.models.BaseCollection;
import com.alternativepayments.models.Pagination;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * List of transaction voids.
 */
public class TransactionVoidCollection extends BaseCollection {

    private final List<TransactionVoid> voidTransactions;

    /**
     * Transaction void collection with pagination.
     *
     * @param voidTransactions list of transaction voids.
     * @param pagination pagination meta object.
     */
    @JsonCreator
    public TransactionVoidCollection(
            @JsonProperty("voidTransactions") final List<TransactionVoid> voidTransactions,
            @JsonProperty("pagination") final Pagination pagination) {
        super(pagination);
        this.voidTransactions = voidTransactions;
    }

    /**
     * @return the transaction voids
     */
    public List<TransactionVoid> getVoidTransactions() {
        return voidTransactions;
    }
}
