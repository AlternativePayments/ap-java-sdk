package com.alternativepayments;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.alternativepayments.apimocks.CustomerApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.models.customer.Customer;
import com.alternativepayments.models.customer.CustomerCollection;

public class AlternativePaymentClientTest extends BaseApiResourceTest {

    @Test
    public void return_customer_for_id() {
        final String id = "cus_bd838e3611d34d598";
        CustomerApiMock.expectGet(id);

        final Customer customer = alternativePaymentClient.getCustomer(id);

        assertThat(customer.getId()).isEqualTo(customer.getId());
        assertThat(customer.getFirstName()).isEqualTo("John");
        assertThat(customer.getLastName()).isEqualTo("Doe");
        assertThat(customer.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
    }

    @Test
    public void return_all_customers() {
        CustomerApiMock.expectGetAll();

        final CustomerCollection customers = alternativePaymentClient.getAllCustomer();

        assertThat(customers.getCustomers()).hasSize(2);
        assertThat(customers.getPagination().getOffset()).isEqualTo(10);
        assertThat(customers.getPagination().getLimit()).isEqualTo(2);
        assertThat(customers.getPagination().getCount()).isEqualTo(2504);
    }

}
