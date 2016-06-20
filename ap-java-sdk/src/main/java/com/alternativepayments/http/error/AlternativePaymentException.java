package com.alternativepayments.http.error;

import com.alternativepayments.models.ErrorModel;

/**
 * Base class for exceptions during API communication.
 */
public class AlternativePaymentException extends RuntimeException {

    private String errorCode;

    private int statusCode;

    /**
     * Create exception out of model.
     *
     * @param errorModel error model for this exception.
     */
    public AlternativePaymentException(final ErrorModel errorModel) {
        super(errorModel.getMessage());
        this.errorCode = errorModel.getCode();
        this.statusCode = errorModel.getStatusCode();
    }

    /**
     * @return error code for this exception.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @return status code for this error.
     */
    public int getStatusCode() {
        return statusCode;
    }
}
