package com.alternativepayments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alternativepayments.AlternativePaymentClient;
import com.alternativepayments.models.customer.Customer;

/**
 * Controller for all action on controller.
 */
@Controller
public class CustomerController {

    @Autowired
    private AlternativePaymentClient alternativePaymentClient;

    /**
     * Root controller showing all menu options..
     *
     * @param model view model
     * @return index view.
     */
    @RequestMapping("/create-customer")
    public String createCustomer(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "US").address("Rutledge Ave 409")
                .city("Folsom").zip("19033").state("PA").phone("55555555555").build();
        Customer createdCustomer = alternativePaymentClient.createCustomer(customer);
        model.addAttribute("customer", createdCustomer);
        return "customer/create-customer";
    }

}
