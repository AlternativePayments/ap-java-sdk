package com.alternativepayments.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.alternativepayments.apimocks.CustomerApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.models.customer.Customer;
import com.alternativepayments.models.customer.CustomerCollection;

public class CustomerModelTest extends BaseApiResourceTest {

    @Test
    public void return_customer_for_id() {
        final String id = "cus_bd838e3611d34d598";
        CustomerApiMock.expectGet(id);

        final Customer customer = alternativePaymentClient.getById(id, Customer.API_ENDPOINT, Customer.class);

        assertThat(customer.getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(customer.getFirstName()).isEqualTo("John");
        assertThat(customer.getLastName()).isEqualTo("Doe");
        assertThat(customer.getEmail()).isEqualTo("john@doe.com");
        assertThat(customer.getAddress()).isEqualTo("Rutledge Ave 409");
        assertThat(customer.getAddress2()).isEqualTo(null);
        assertThat(customer.getCity()).isEqualTo("Folsom");
        assertThat(customer.getZip()).isEqualTo("19033");
        assertThat(customer.getCountry()).isEqualTo("US");
        assertThat(customer.getState()).isEqualTo("PA");
        assertThat(customer.getPhone()).isEqualTo("55555555555");
        assertThat(customer.getMode()).isEqualTo("Live");
        assertThat(customer.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
    }

    @Test
    public void return_all_customers() {
        CustomerApiMock.expectGetAll();

        final CustomerCollection customers = alternativePaymentClient.getAll(Customer.API_ENDPOINT,
                CustomerCollection.class);

        assertThat(customers.getCustomers()).hasSize(2);
        assertThat(customers.getPagination().getOffset()).isEqualTo(10);
        assertThat(customers.getPagination().getLimit()).isEqualTo(3);
        assertThat(customers.getPagination().getCount()).isEqualTo(2504);

        final Customer firstCustomer = customers.getCustomers().get(0);
        assertThat(firstCustomer.getId()).isEqualTo("cus_7f0724f3b1d745d49");
        assertThat(firstCustomer.getFirstName()).isEqualTo("Jane");
        assertThat(firstCustomer.getLastName()).isEqualTo("Doe");
        assertThat(firstCustomer.getEmail()).isEqualTo("jane@doe.com");
        assertThat(firstCustomer.getAddress()).isEqualTo(null);
        assertThat(firstCustomer.getAddress2()).isEqualTo(null);
        assertThat(firstCustomer.getCity()).isEqualTo(null);
        assertThat(firstCustomer.getZip()).isEqualTo(null);
        assertThat(firstCustomer.getCountry()).isEqualTo("DE");
        assertThat(firstCustomer.getState()).isEqualTo(null);
        assertThat(firstCustomer.getPhone()).isEqualTo(null);
        assertThat(firstCustomer.getMode()).isEqualTo("Live");
        assertThat(firstCustomer.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");

        final Customer secondCustomer = customers.getCustomers().get(1);
        assertThat(secondCustomer.getId()).isEqualTo("cus_7f0724f3b1d745d49");
        assertThat(secondCustomer.getFirstName()).isEqualTo("John");
        assertThat(secondCustomer.getLastName()).isEqualTo("Doe");
        assertThat(secondCustomer.getEmail()).isEqualTo("john@doe.com");
        assertThat(secondCustomer.getAddress()).isEqualTo("Rutledge Ave 409");
        assertThat(secondCustomer.getAddress2()).isEqualTo(null);
        assertThat(secondCustomer.getCity()).isEqualTo("Folsom");
        assertThat(secondCustomer.getZip()).isEqualTo("19033");
        assertThat(secondCustomer.getCountry()).isEqualTo("US");
        assertThat(secondCustomer.getState()).isEqualTo("PA");
        assertThat(secondCustomer.getPhone()).isEqualTo("55555555555");
        assertThat(secondCustomer.getMode()).isEqualTo("Live");
        assertThat(secondCustomer.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");

    }

    @Test
    public void create_new_customer() {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "US").address("Rutledge Ave 409")
                .city("Folsom").zip("19033").state("PA").phone("55555555555").build();

        CustomerApiMock.expectPost(customer);

        final Customer createdCustomer = alternativePaymentClient.create(customer, Customer.API_ENDPOINT,
                Customer.class);

        assertThat(createdCustomer.getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(createdCustomer.getFirstName()).isEqualTo("John");
        assertThat(createdCustomer.getLastName()).isEqualTo("Doe");
        assertThat(createdCustomer.getEmail()).isEqualTo("john@doe.com");
        assertThat(createdCustomer.getAddress()).isEqualTo("Rutledge Ave 409");
        assertThat(createdCustomer.getAddress2()).isEqualTo(null);
        assertThat(createdCustomer.getCity()).isEqualTo("Folsom");
        assertThat(createdCustomer.getZip()).isEqualTo("19033");
        assertThat(createdCustomer.getCountry()).isEqualTo("US");
        assertThat(createdCustomer.getState()).isEqualTo("PA");
        assertThat(createdCustomer.getPhone()).isEqualTo("55555555555");
        assertThat(createdCustomer.getMode()).isEqualTo("Live");
        assertThat(createdCustomer.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
    }

    @Test
    public void return_customers_with_pagination() {
        CustomerApiMock.expectGetWithPagination();

        final CustomerCollection customers = alternativePaymentClient.getAllWithPagination(3, 10, Customer.API_ENDPOINT,
                CustomerCollection.class);

        assertThat(customers.getCustomers()).hasSize(3);
        assertThat(customers.getPagination().getOffset()).isEqualTo(10);
        assertThat(customers.getPagination().getLimit()).isEqualTo(3);
        assertThat(customers.getPagination().getCount()).isEqualTo(2504);

        final Customer firstCustomer = customers.getCustomers().get(0);
        assertThat(firstCustomer.getId()).isEqualTo("cus_7f0724f3b1d745d49");
        assertThat(firstCustomer.getFirstName()).isEqualTo("Jane");
        assertThat(firstCustomer.getLastName()).isEqualTo("Doe");
        assertThat(firstCustomer.getEmail()).isEqualTo("jane@doe.com");
        assertThat(firstCustomer.getAddress()).isEqualTo(null);
        assertThat(firstCustomer.getAddress2()).isEqualTo(null);
        assertThat(firstCustomer.getCity()).isEqualTo(null);
        assertThat(firstCustomer.getZip()).isEqualTo(null);
        assertThat(firstCustomer.getCountry()).isEqualTo("DE");
        assertThat(firstCustomer.getState()).isEqualTo(null);
        assertThat(firstCustomer.getPhone()).isEqualTo(null);
        assertThat(firstCustomer.getMode()).isEqualTo("Live");
        assertThat(firstCustomer.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");

        final Customer secondCustomer = customers.getCustomers().get(1);
        assertThat(secondCustomer.getId()).isEqualTo("cus_7f0724f3b1d745d49");
        assertThat(secondCustomer.getFirstName()).isEqualTo("John");
        assertThat(secondCustomer.getLastName()).isEqualTo("Doe");
        assertThat(secondCustomer.getEmail()).isEqualTo("john@doe.com");
        assertThat(secondCustomer.getAddress()).isEqualTo("Rutledge Ave 409");
        assertThat(secondCustomer.getAddress2()).isEqualTo(null);
        assertThat(secondCustomer.getCity()).isEqualTo("Folsom");
        assertThat(secondCustomer.getZip()).isEqualTo("19033");
        assertThat(secondCustomer.getCountry()).isEqualTo("US");
        assertThat(secondCustomer.getState()).isEqualTo("PA");
        assertThat(secondCustomer.getPhone()).isEqualTo("55555555555");
        assertThat(secondCustomer.getMode()).isEqualTo("Live");
        assertThat(secondCustomer.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");

    }
}
