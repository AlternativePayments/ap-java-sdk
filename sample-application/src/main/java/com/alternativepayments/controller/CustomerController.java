package com.alternativepayments.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alternativepayments.AlternativePaymentClient;
import com.alternativepayments.models.customer.Customer;
import com.alternativepayments.models.customer.CustomerCollection;

/**
 * Controller for all action on controller.
 */
@Controller
public class CustomerController {

    @Autowired
    private AlternativePaymentClient alternativePaymentClient;

    /**
     * Create customer page.
     *
     * @param model view model
     * @return view to create customer.
     */
    @RequestMapping("/create-customer")
    public String createCustomer(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "US").address("Rutledge Ave 409")
                .city("Folsom").zip("19033").state("PA").phone("55555555555").build();
        Customer createdCustomer = alternativePaymentClient.create(customer, Customer.API_ENDPOINT, Customer.class);
        model.addAttribute("customer", createdCustomer);
        return "customer/create-customer";
    }

    /**
     * Get customer.
     *
     * @param model view model
     * @param customerId id of requested customer.
     * @return view for get customer.
     */
    @RequestMapping("/get-customer")
    public String getCustomer(Model model,
            @RequestParam(value = "customer_id", required = false) final String customerId) {
        if (StringUtils.isNotBlank(customerId)) {
            Customer customer = alternativePaymentClient.getById(customerId, Customer.API_ENDPOINT, Customer.class);
            model.addAttribute("customer", customer);
        }

        return "customer/get-customer";
    }

    /**
     * Get all customers.
     *
     * @param model view model
     * @return view for get customers.
     */
    @RequestMapping("/get-customers")
    public String getCustomers(Model model) {
        CustomerCollection customers = alternativePaymentClient.getAll(Customer.API_ENDPOINT, CustomerCollection.class);
        model.addAttribute("customers", customers.getCustomers());

        return "customer/get-customers";
    }

}
