package com.alternativepayments;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.alternativepayments.apimocks.CustomerApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.models.Customer;

public class AlternativePaymentClientTest extends BaseApiResourceTest {

    @Test
    public void return_customer_for_id() {
        final String id = "cus_bd838e3611d34d598";
        CustomerApiMock.expectGet(id);

        final Customer customer = alternativePaymentClient.getCustomer(id);

        assertEquals(id, customer.getId());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("2012-04-23T18:25:43.511Z", customer.getCreated());
    }

}
