package com.alternativepayments;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.alternativepayments.http.HttpClientFactory;
import com.alternativepayments.models.Customer;

/**
 * Alternative Payments Java SDK Client.
 */
public class AlternativePaymentClient {

    private Client httpClient;
    private WebTarget apiTarget;

    /**
     * Create default HTTP client with API base and API key.
     *
     * @param apiBase base URL of API.
     * @param apiKey API key to use.
     */
    public AlternativePaymentClient(final String apiBase, final String apiKey) {
        this.httpClient = HttpClientFactory.createDefault();
        this.apiTarget = httpClient.target(apiBase);
    }

    /**
     * Return customer for provided ID.
     *
     * @param id id of requested customer.
     * @return Customer with given ID.
     */
    public Customer getCustomer(final String id) {
        return get(apiTarget.path(Customer.API_ENDPOINT + id), Customer.class);
    }

    /**
     * Return single resource for provided web target and class.
     *
     * @param <T> type of class doing get.
     * @param target target of endpoint.
     * @param responseClass response class doing request.
     * @return single object or exception.
     */
    protected static <T> T get(final WebTarget target, final Class<T> responseClass) {
        try {
            return target.request(MediaType.APPLICATION_JSON).get(responseClass);
        } catch (final WebApplicationException webApplicationException) {
            throw new RuntimeException(webApplicationException);
        }
    }

}
