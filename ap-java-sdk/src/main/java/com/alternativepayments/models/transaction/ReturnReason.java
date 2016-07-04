package com.alternativepayments.models.transaction;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Return reason for void and refund.
 */
public enum ReturnReason {

    /**
     * Chargeback avoidance.
     */
    CHARGEBACK_AVOIDANCE("CHARGEBACK_AVOIDANCE"),
    /**
     * End user error.
     */
    END_USER_ERROR("END_USER_ERROR"),
    /**
     * Fraud.
     */
    FRAUD("FRAUD"),
    /**
     * Unsatisfied customer.
     */
    UNSATISFIED_CUSTOMER("UNSATISFIED_CUSTOMER"),
    /**
     * Invalid transaction.
     */
    INVALID_TRANSACTION("INVALID_TRANSACTION");

    private final String value;

    private ReturnReason(final String value) {
        this.value = value;
    }

    @JsonValue
    final String value() {
        return this.value;
    }

}
