package com.alternativepayments.http.error;

import com.alternativepayments.models.ErrorModel;

/**
 * Invalid parameter exception.
 */
public class InvalidParameterException extends AlternativePaymentException {

    private String parameter;

    /**
     * Create invalid parameter exception out of error model.
     *
     * @param errorModel error model from response.
     */
    public InvalidParameterException(ErrorModel errorModel) {
        super(errorModel);
        this.parameter = errorModel.getParameter();
    }

    /**
     * @return parameter for this error.
     */
    public String getParameter() {
        return parameter;
    }

}
