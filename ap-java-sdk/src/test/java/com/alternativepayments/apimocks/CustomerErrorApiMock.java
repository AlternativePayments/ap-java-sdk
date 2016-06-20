package com.alternativepayments.apimocks;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

import javax.ws.rs.core.Response.Status;

import com.github.tomakehurst.wiremock.client.WireMock;

public class CustomerErrorApiMock {

    private CustomerErrorApiMock() {

    }

    public static void expectApiErrorUpperCase() {
        WireMock.stubFor(
                get(WireMock.urlMatching("/api/customers/error_upper_case"))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"Type\": \"api_error\",\n"
                            + "  \"Code\": \"api_error\",\n"
                            + "  \"StatusCode\": \"402\",\n"
                            + "  \"Message\": \"Api Error\"\n"
                            + "}")));
    }

    public static void expectApiErrorLowerCase() {
        WireMock.stubFor(
                get(WireMock.urlMatching("/api/customers/error_lower_case"))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"type\": \"api_error\",\n"
                            + "  \"code\": \"api_error\",\n"
                            + "  \"statusCode\": \"402\",\n"
                            + "  \"message\": \"Api Error\"\n"
                            + "}")));
    }

    public static void expectUnsupportedError() {
        WireMock.stubFor(
                get(WireMock.urlMatching("/api/customers/unsupported_error"))
                .willReturn(aResponse()
                        .withStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"type\": \"internal_error\",\n"
                            + "  \"code\": \"internal_error\",\n"
                            + "  \"statusCode\": \"500\",\n"
                            + "  \"message\": \"Internal Error\"\n"
                            + "}")));
    }

    public static void expectPaymentError() {
        WireMock.stubFor(
                get(WireMock.urlMatching("/api/customers/payment_error"))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"type\": \"payment_error\",\n"
                            + "  \"code\": \"void_not_supported\",\n"
                            + "  \"statusCode\": \"402\",\n"
                            + "  \"message\": \"Void is not supported\"\n"
                            + "}")));
    }

    public static void expectCustomerOlderError() {
        WireMock.stubFor(
                get(WireMock.urlMatching("/api/customers/customer_older_error"))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"type\": \"payment_error\",\n"
                            + "  \"code\": \"customer_must_be_at_least_16_years_old\",\n"
                            + "  \"statusCode\": \"402\",\n"
                            + "  \"message\": \"Customer must be at least 16 years old\"\n"
                            + "}")));
    }

    public static void expectApiErrorNotFound() {
        WireMock.stubFor(
                get(WireMock.urlMatching("/api/customers/not_found"))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"type\": \"api_error\",\n"
                            + "  \"code\": \"not_found\",\n"
                            + "  \"statusCode\": \"404\",\n"
                            + "  \"message\": \"Not Found - The requested item doesn’t exist.\"\n"
                            + "}")));
    }

    public static void expectApiErrorInternalServerError() {
        WireMock.stubFor(
                get(WireMock.urlMatching("/api/customers/internal_server_error"))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"type\": \"api_error\",\n"
                            + "  \"code\": \"internal_server_error\",\n"
                            + "  \"statusCode\": \"500\",\n"
                            + "  \"message\": \"Server errors - internal server error.\"\n"
                            + "}")));
    }

    public static void expectApiErrorAcquirerError() {
        WireMock.stubFor(
                get(WireMock.urlMatching("/api/customers/acquirer_error"))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"type\": \"acquirer_down\",\n"
                            + "  \"code\": \"acquirer_down\",\n"
                            + "  \"statusCode\": \"402\",\n"
                            + "  \"message\": \"Acquirer Down\"\n"
                            + "}")));
    }

    public static void expectInvalidParameterSentError() {
        WireMock.stubFor(
                get(WireMock.urlMatching("/api/customers/invalid_object_sent"))
                .willReturn(aResponse()
                        .withStatus(Status.BAD_REQUEST.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"type\": \"invalid_parameter_error\",\n"
                            + "  \"code\": \"invalid_object_sent\",\n"
                            + "  \"statusCode\": \"400\",\n"
                            + "  \"message\": \"Object is not sent or invalid object is sent\",\n"
                            + "  \"parameter\": \"id\"\n"
                            + "}")));
    }

}
