package com.alternativepayments.http.error;

import com.alternativepayments.models.ErrorModel;
import com.alternativepayments.models.ErrorModel.ErrorType;

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
     * Create exception out of error type, message, error code and status code.
     *
     * @param errorType type of error
     * @param message error message
     * @param errorCode error code defined by APIs
     * @param statusCode HTTP code of error.
     */
    public AlternativePaymentException(final ErrorType errorType, final String message, final String errorCode,
            final int statusCode) {
        this(new ErrorModel(errorType, errorCode, statusCode, message, null));
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
