package com.alternativepayments.http.error;

import com.alternativepayments.models.ErrorModel;

/**
 * Authentication exception.
 */
public class AuthenticationException extends AlternativePaymentException {

    /**
     * Create authentication exception out of error model.
     *
     * @param errorModel error model from response.
     */
    public AuthenticationException(ErrorModel errorModel) {
        super(errorModel);
    }

}
