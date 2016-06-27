package com.alternativepayments.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Base model for error.
 */
public class ErrorModel {

    private ErrorType type;
    private String code;
    private int statusCode;
    private String message;
    private String parameter;

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
        API_ERROR("api_error"),
        /**
         * Authentication level error.
         */
        AUTHENTICATION_ERROR("authentication_error"),
        /**
         * Invalid parameter error.
         */
        INVALID_PARAMETER_ERROR("invalid_parameter_error"),
        /**
         * Payment error.
         */
        PAYMENT_ERROR("payment_error"),
        /**
         * Acquirer error.
         */
        ACQUIRER_DOWN("acquirer_down");

        private final String value;

        private ErrorType(final String description) {
            this.value = description;
        }

        @JsonValue
        final String value() {
            return this.value;
        }
    }

}
