package com.alternativepayments.http.error;

import com.alternativepayments.models.ErrorModel;

/**
 * Api exception.
 */
public class ApiException extends AlternativePaymentException {

    /**
     * Create Api exception out of error model.
     *
     * @param errorModel error model from response.
     */
    public ApiException(ErrorModel errorModel) {
        super(errorModel);
    }

}
