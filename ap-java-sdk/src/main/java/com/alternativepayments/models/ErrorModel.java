package com.alternativepayments.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base model for error.
 */
public class ErrorModel {

    private final ErrorType type;
    private final String code;
    private final int statusCode;
    private final String message;
    private final String parameter;

    /**
     * Create base error model for non 2xx response from API.
     *
     * @param type type of error
     * @param code code of error
     * @param statusCode status code of error
     * @param message detailed message of error
     * @param parameter in case error is attached to specific parameter
     */
    @JsonCreator
    public ErrorModel(
            @JsonProperty("Type") ErrorType type,
            @JsonProperty("Code") String code,
            @JsonProperty("StatusCode") int statusCode,
            @JsonProperty("Message") String message,
            @JsonProperty("Parameter") String parameter) {
        this.type = type;
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
        this.parameter = parameter;
    }

    /**
     * @return type of error.
     */
    public ErrorType getType() {
        return type;
    }

    /**
     * @return code of error.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return status code of error.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @return detailed message for an error.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return parameter attached to error.
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * Enumeration for supported error type.
     */
    public enum ErrorType {
        /**
         * Api level error.
         */
        @JsonProperty("api_error") API_ERROR,
        /**
         * Authentication level error.
         */
        @JsonProperty("authentication_error") AUTHENTICATION_ERROR,
        /**
         * Invalid parameter error.
         */
        @JsonProperty("invalid_parameter_error") INVALID_PARAMETER_ERROR,
        /**
         * Payment error.
         */
        @JsonProperty("payment_error") PAYMENT_ERROR,
        /**
         * Acquirer error.
         */
        @JsonProperty("acquirer_down") ACQUIRER_DOWN;
    }

}
