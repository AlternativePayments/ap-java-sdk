package com.alternativepayments.models.customer;

import java.util.List;

import com.alternativepayments.models.BaseCollection;
import com.alternativepayments.models.Pagination;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * List of customers.
 */
public class CustomerCollection extends BaseCollection {

    private final List<Customer> customers;

    /**
     * Customer collection with pagination.
     *
     * @param customers list of customers.
     * @param pagination pagination meta object.
     */
    @JsonCreator
    public CustomerCollection(
            @JsonProperty("customers") List<Customer> customers,
            @JsonProperty("pagination") Pagination pagination) {
        super(pagination);
        this.customers = customers;
    }

    /**
     * @return the customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }
}
