package com.alternativepayments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;

import com.alternativepayments.apimocks.CustomerErrorApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.http.error.ApiException;
import com.alternativepayments.http.error.InvalidParameterException;
import com.alternativepayments.http.error.PaymentException;

public class ExceptionHandlingClientTest extends BaseApiResourceTest {

    @Test
    public void return_payment_error_void_not_supported() {
        final String id = "payment_error";
        CustomerErrorApiMock.expectPaymentError();

        final Throwable thrown = catchThrowable(() -> alternativePaymentClient.getCustomer(id));

        assertThat(thrown).isInstanceOf(PaymentException.class);
        PaymentException paymentException = (PaymentException) thrown;
        assertThat(paymentException.getMessage()).isEqualTo("Void is not supported");
        assertThat(paymentException.getErrorCode()).isEqualTo("void_not_supported");
        assertThat(paymentException.getStatusCode()).isEqualTo(402);
    }

    @Test
    public void return_payment_error_customer_older_than_16() {
        final String id = "customer_older_error";
        CustomerErrorApiMock.expectCustomerOlderError();

        final Throwable thrown = catchThrowable(() -> alternativePaymentClient.getCustomer(id));

        assertThat(thrown).isInstanceOf(PaymentException.class);
        PaymentException paymentException = (PaymentException) thrown;
        assertThat(paymentException.getMessage()).isEqualTo("Customer must be at least 16 years old");
        assertThat(paymentException.getErrorCode()).isEqualTo("customer_must_be_at_least_16_years_old");
        assertThat(paymentException.getStatusCode()).isEqualTo(402);
    }

    @Test
    public void return_api_error() {
        final String id = "error_upper_case";
        CustomerErrorApiMock.expectApiErrorUpperCase();

        final Throwable thrown = catchThrowable(() -> alternativePaymentClient.getCustomer(id));

        assertThat(thrown).isInstanceOf(ApiException.class);
        ApiException apiException = (ApiException) thrown;
        assertThat(apiException.getMessage()).isEqualTo("Api Error");
        assertThat(apiException.getErrorCode()).isEqualTo("api_error");
        assertThat(apiException.getStatusCode()).isEqualTo(402);
    }

    @Test
    public void return_api_error_lower_case() {
        final String id = "error_lower_case";
        CustomerErrorApiMock.expectApiErrorLowerCase();

        final Throwable thrown = catchThrowable(() -> alternativePaymentClient.getCustomer(id));

        assertThat(thrown).isInstanceOf(ApiException.class);
        ApiException apiException = (ApiException) thrown;
        assertThat(apiException.getMessage()).isEqualTo("Api Error");
        assertThat(apiException.getErrorCode()).isEqualTo("api_error");
        assertThat(apiException.getStatusCode()).isEqualTo(402);
    }

    @Test
    public void return_not_found() {
        final String id = "not_found";
        CustomerErrorApiMock.expectApiErrorNotFound();

        final Throwable thrown = catchThrowable(() -> alternativePaymentClient.getCustomer(id));

        assertThat(thrown).isInstanceOf(ApiException.class);
        ApiException apiException = (ApiException) thrown;
        assertThat(apiException.getMessage()).isEqualTo("Not Found - The requested item doesn’t exist.");
        assertThat(apiException.getErrorCode()).isEqualTo("not_found");
        assertThat(apiException.getStatusCode()).isEqualTo(404);
    }

    @Test
    public void return_internal_server_error() {
        final String id = "internal_server_error";
        CustomerErrorApiMock.expectApiErrorInternalServerError();

        final Throwable thrown = catchThrowable(() -> alternativePaymentClient.getCustomer(id));

        assertThat(thrown).isInstanceOf(ApiException.class);
        ApiException apiException = (ApiException) thrown;
        assertThat(apiException.getMessage()).isEqualTo("Server errors - internal server error.");
        assertThat(apiException.getErrorCode()).isEqualTo("internal_server_error");
        assertThat(apiException.getStatusCode()).isEqualTo(500);
    }

    @Test
    public void return_acquirer_error() {
        final String id = "acquirer_error";
        CustomerErrorApiMock.expectApiErrorAcquirerError();

        final Throwable thrown = catchThrowable(() -> alternativePaymentClient.getCustomer(id));

        assertThat(thrown).isInstanceOf(ApiException.class);
        ApiException apiException = (ApiException) thrown;
        assertThat(apiException.getMessage()).isEqualTo("Acquirer Down");
        assertThat(apiException.getErrorCode()).isEqualTo("acquirer_down");
        assertThat(apiException.getStatusCode()).isEqualTo(402);
    }

    @Test
    public void return_invalid_object_sent() {
        final String id = "invalid_object_sent";
        CustomerErrorApiMock.expectInvalidParameterSentError();

        final Throwable thrown = catchThrowable(() -> alternativePaymentClient.getCustomer(id));

        assertThat(thrown).isInstanceOf(InvalidParameterException.class);
        InvalidParameterException invalidParameterException = (InvalidParameterException) thrown;
        assertThat(invalidParameterException.getMessage()).isEqualTo("Object is not sent or invalid object is sent");
        assertThat(invalidParameterException.getErrorCode()).isEqualTo("invalid_object_sent");
        assertThat(invalidParameterException.getStatusCode()).isEqualTo(400);
        assertThat(invalidParameterException.getParameter()).isEqualTo("id");
    }

    @Test
    public void return_unsupported_error() {
        final String id = "unsupported_error";
        CustomerErrorApiMock.expectUnsupportedError();

        final Throwable thrown = catchThrowable(() -> alternativePaymentClient.getCustomer(id));

        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

}
