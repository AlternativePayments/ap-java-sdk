package com.alternativepayments;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;

import com.alternativepayments.apimocks.CustomerErrorApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.http.error.ApiException;
import com.alternativepayments.http.error.InvalidParameterException;
import com.alternativepayments.http.error.PaymentException;
import com.alternativepayments.models.customer.Customer;

public class ExceptionHandlingClientTest extends BaseApiResourceTest {

    @Test
    public void return_payment_error_void_not_supported() {
        final String id = "payment_error";
        CustomerErrorApiMock.expectPaymentError();

        try {
            alternativePaymentClient.getById(id, Customer.API_ENDPOINT, Customer.class);
            Assert.fail("Exception must not be thrown.");
        } catch (PaymentException paymentException) {
            assertThat(paymentException.getMessage()).isEqualTo("Void is not supported");
            assertThat(paymentException.getErrorCode()).isEqualTo("void_not_supported");
            assertThat(paymentException.getStatusCode()).isEqualTo(402);
        }
    }

    @Test
    public void return_payment_error_customer_older_than_16() {
        final String id = "customer_older_error";
        CustomerErrorApiMock.expectCustomerOlderError();

        try {
            alternativePaymentClient.getById(id, Customer.API_ENDPOINT, Customer.class);
            Assert.fail("Exception must not be thrown.");
        } catch (PaymentException paymentException) {
            assertThat(paymentException.getMessage()).isEqualTo("Customer must be at least 16 years old");
            assertThat(paymentException.getErrorCode()).isEqualTo("customer_must_be_at_least_16_years_old");
            assertThat(paymentException.getStatusCode()).isEqualTo(402);
        }
    }

    @Test
    public void return_api_error() {
        final String id = "error_upper_case";
        CustomerErrorApiMock.expectApiErrorUpperCase();

        try {
            alternativePaymentClient.getById(id, Customer.API_ENDPOINT, Customer.class);
            Assert.fail("Exception must not be thrown.");
        } catch (ApiException apiException) {
            assertThat(apiException.getMessage()).isEqualTo("Api Error");
            assertThat(apiException.getErrorCode()).isEqualTo("api_error");
            assertThat(apiException.getStatusCode()).isEqualTo(402);
        }
    }

    @Test
    public void return_api_error_lower_case() {
        final String id = "error_lower_case";
        CustomerErrorApiMock.expectApiErrorLowerCase();

        try {
            alternativePaymentClient.getById(id, Customer.API_ENDPOINT, Customer.class);
            Assert.fail("Exception must not be thrown.");
        } catch (ApiException apiException) {
            assertThat(apiException.getMessage()).isEqualTo("Api Error");
            assertThat(apiException.getErrorCode()).isEqualTo("api_error");
            assertThat(apiException.getStatusCode()).isEqualTo(402);
        }
    }

    @Test
    public void return_not_found() {
        final String id = "not_found";
        CustomerErrorApiMock.expectApiErrorNotFound();

        try {
            alternativePaymentClient.getById(id, Customer.API_ENDPOINT, Customer.class);
            Assert.fail("Exception must not be thrown.");
        } catch (ApiException apiException) {
            assertThat(apiException.getMessage()).isEqualTo("Not Found - The requested item doesn’t exist.");
            assertThat(apiException.getErrorCode()).isEqualTo("not_found");
            assertThat(apiException.getStatusCode()).isEqualTo(404);
        }
    }

    @Test
    public void return_internal_server_error() {
        final String id = "internal_server_error";
        CustomerErrorApiMock.expectApiErrorInternalServerError();

        try {
            alternativePaymentClient.getById(id, Customer.API_ENDPOINT, Customer.class);
            Assert.fail("Exception must not be thrown.");
        } catch (ApiException apiException) {
            assertThat(apiException.getMessage()).isEqualTo("Server errors - internal server error.");
            assertThat(apiException.getErrorCode()).isEqualTo("internal_server_error");
            assertThat(apiException.getStatusCode()).isEqualTo(500);
        }
    }

    @Test
    public void return_acquirer_error() {
        final String id = "acquirer_error";
        CustomerErrorApiMock.expectApiErrorAcquirerError();

        try {
            alternativePaymentClient.getById(id, Customer.API_ENDPOINT, Customer.class);
            Assert.fail("Exception must not be thrown.");
        } catch (ApiException apiException) {
            assertThat(apiException.getMessage()).isEqualTo("Acquirer Down");
            assertThat(apiException.getErrorCode()).isEqualTo("acquirer_down");
            assertThat(apiException.getStatusCode()).isEqualTo(402);
        }
    }

    @Test
    public void return_invalid_object_sent() {
        final String id = "invalid_object_sent";
        CustomerErrorApiMock.expectInvalidParameterSentError();

        try {
            alternativePaymentClient.getById(id, Customer.API_ENDPOINT, Customer.class);
            Assert.fail("Exception must not be thrown.");
        } catch (InvalidParameterException invalidParameterException) {
            assertThat(invalidParameterException.getMessage())
                    .isEqualTo("Object is not sent or invalid object is sent");
            assertThat(invalidParameterException.getErrorCode()).isEqualTo("invalid_object_sent");
            assertThat(invalidParameterException.getStatusCode()).isEqualTo(400);
            assertThat(invalidParameterException.getParameter()).isEqualTo("id");
        }
    }

    @Test(expected = RuntimeException.class)
    public void return_unsupported_error() {
        final String id = "unsupported_error";
        CustomerErrorApiMock.expectUnsupportedError();

        alternativePaymentClient.getById(id, Customer.API_ENDPOINT, Customer.class);
    }

}
