package com.alternativepayments.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.alternativepayments.apimocks.PaymentOptionApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.models.website.PaymentOption;

public class PaymentOptionModelTest extends BaseApiResourceTest {

    @Test
    public void return_payment_option_for_SEPA() {
        final String paymentOptionType = "SEPA";
        PaymentOptionApiMock.expectGet(API_PUBLIC_KEY, paymentOptionType);

        final PaymentOption paymentOption = alternativePaymentClient
                .getForWebsite(PaymentOption.getApiEndpoint(paymentOptionType), PaymentOption.class);

        assertThat(paymentOption.getId()).isEqualTo("web_6547gfhu67yrru43");
        assertThat(paymentOption.getMode()).isEqualTo("Test");
        assertThat(paymentOption.isHasSmsVerification()).isTrue();
        assertThat(paymentOption.getUrl()).isEqualTo("http://www.mywebshop.com");
    }
}
