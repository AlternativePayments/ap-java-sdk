package com.alternativepayments.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.alternativepayments.apimocks.PreauthorizationApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.models.customer.Customer;
import com.alternativepayments.models.transaction.Payment;
import com.alternativepayments.models.transaction.Preauthorization;

public class PreauthorizationModelTest extends BaseApiResourceTest {

    @Test
    public void create_new_sepa_preauthorization() {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("SEPA", "John Doe").iban("DE71XXXXX3330").build();

        Preauthorization preauthorization = new Preauthorization(customer, payment, 500, "EUR");

        PreauthorizationApiMock.expectPost(preauthorization);

        final Preauthorization createdPreauthorization = alternativePaymentClient
                .createPreauthorization(preauthorization);

        assertThat(createdPreauthorization.getId()).isEqualTo("preauth_ee146f5315");
        assertThat(createdPreauthorization.getAmount()).isEqualTo(500);
        assertThat(preauthorization.getCurrnecy()).isEqualTo("EUR");
        assertThat(createdPreauthorization.getMode()).isEqualTo("Live");
        assertThat(createdPreauthorization.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(createdPreauthorization.getCustomer().getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(createdPreauthorization.getCustomer().getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(createdPreauthorization.getCustomer().getLastName()).isEqualTo(customer.getLastName());
        assertThat(createdPreauthorization.getCustomer().getEmail()).isEqualTo(customer.getEmail());
        assertThat(createdPreauthorization.getCustomer().getCountry()).isEqualTo(customer.getCountry());
        assertThat(createdPreauthorization.getCustomer().getMode()).isEqualTo("Live");
        assertThat(createdPreauthorization.getCustomer().getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(createdPreauthorization.getPayment().getId()).isEqualTo("pay_ffd25121f84e4d249");
        assertThat(createdPreauthorization.getPayment().getMode()).isEqualTo("Live");
        assertThat(createdPreauthorization.getPayment().getHolder()).isEqualTo(payment.getHolder());
        assertThat(createdPreauthorization.getPayment().getPaymentOption()).isEqualTo(payment.getPaymentOption());
        assertThat(createdPreauthorization.getPayment().getIban()).isEqualTo(payment.getIban());
    }
}
