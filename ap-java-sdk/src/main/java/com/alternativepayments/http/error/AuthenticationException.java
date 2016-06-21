package com.alternativepayments.http.error;

import com.alternativepayments.models.ErrorModel;
import com.alternativepayments.models.ErrorModel.ErrorType;

/**
 * Authentication exception.
 */
public class AuthenticationException extends AlternativePaymentException {

    /**
     * Create authentication exception out of error model.
     *
     * @param errorModel error model from response.
     */
    public AuthenticationException(final ErrorModel errorModel) {
        super(errorModel);
    }

    /**
     * Create exception out of error type, message, error code and status code.
     *
     * @param message error message
     * @param errorCode error code defined by APIs
     * @param statusCode HTTP code of error.
     */
    public AuthenticationException(final String message, final String errorCode, final int statusCode) {
        super(ErrorType.AUTHENTICATION_ERROR, message, errorCode, statusCode);
    }
}
