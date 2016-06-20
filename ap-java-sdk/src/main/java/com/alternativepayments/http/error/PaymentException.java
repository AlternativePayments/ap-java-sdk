package com.alternativepayments.http.error;

import com.alternativepayments.models.ErrorModel;

/**
 * Payment exception.
 */
public class PaymentException extends AlternativePaymentException {

    /**
     * Create Payment exception out of error model.
     *
     * @param errorModel error model from response.
     */
    public PaymentException(ErrorModel errorModel) {
        super(errorModel);
    }

}
