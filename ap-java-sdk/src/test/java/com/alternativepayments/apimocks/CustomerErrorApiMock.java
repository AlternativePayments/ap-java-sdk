package com.alternativepayments.apimocks;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import javax.ws.rs.core.Response.Status;

import com.alternativepayments.AlternativePaymentClient;
import com.github.tomakehurst.wiremock.client.WireMock;

public class CustomerErrorApiMock {

    private CustomerErrorApiMock() {

    }

    public static void expectApiErrorUpperCase() {
        WireMock.stubFor(
                get(urlMatching("/api/customers/error_upper_case"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("error/api_error.json")));
    }

    public static void expectApiErrorLowerCase() {
        WireMock.stubFor(
                get(urlMatching("/api/customers/error_lower_case"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("error/api_error.json")));
    }

    public static void expectUnsupportedError() {
        WireMock.stubFor(
                get(urlMatching("/api/customers/unsupported_error"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("error/internal_server_error.json")));
    }

    public static void expectPaymentError() {
        WireMock.stubFor(
                get(urlMatching("/api/customers/payment_error"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("error/payment_error_void.json")));
    }

    public static void expectCustomerOlderError() {
        WireMock.stubFor(
                get(urlMatching("/api/customers/customer_older_error"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("error/payment_error_customer_age.json")));
    }

    public static void expectApiErrorNotFound() {
        WireMock.stubFor(
                get(urlMatching("/api/customers/not_found"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("error/api_error_not_found.json")));
    }

    public static void expectApiErrorInternalServerError() {
        WireMock.stubFor(
                get(urlMatching("/api/customers/internal_server_error"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("error/api_error_internal.json")));
    }

    public static void expectApiErrorAcquirerError() {
        WireMock.stubFor(
                get(urlMatching("/api/customers/acquirer_error"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.PAYMENT_REQUIRED.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("error/acquirer_down_error.json")));
    }

    public static void expectInvalidParameterSentError() {
        WireMock.stubFor(
                get(urlMatching("/api/customers/invalid_object_sent"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.BAD_REQUEST.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("error/invalid_parameter_error.json")));
    }

}
