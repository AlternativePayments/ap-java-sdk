package com.alternativepayments;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.alternativepayments.http.HttpClientFactory;
import com.alternativepayments.http.error.AlternativePaymentException;
import com.alternativepayments.http.error.ApiException;
import com.alternativepayments.http.error.AuthenticationException;
import com.alternativepayments.http.error.InvalidParameterException;
import com.alternativepayments.http.error.PaymentException;
import com.alternativepayments.models.ErrorModel;
import com.alternativepayments.models.Pagination;
import com.alternativepayments.models.customer.Customer;
import com.alternativepayments.models.customer.CustomerCollection;

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
        this.httpClient = HttpClientFactory.createDefault(apiKey);
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
     * Return all customers.
     *
     * @return all customers.
     */
    public CustomerCollection getAllCustomers() {
        return get(apiTarget.path(Customer.API_ENDPOINT), CustomerCollection.class);
    }

    /**
     * Return customers taking pagination in consideration.
     *
     * @param limit how many records we want
     * @param offset offset of the requested results
     *
     * @return all customers.
     */
    public CustomerCollection getCustomersWithPagination(final int limit, final int offset) {
        return get(apiTarget.path(Customer.API_ENDPOINT).queryParam(Pagination.LIMIT, limit)
                .queryParam(Pagination.OFFSET, offset), CustomerCollection.class);
    }

    /**
     * Create new customer.
     *
     * @param customer you want to create
     *
     * @return newly created customer.
     */
    public Customer createCustomer(final Customer customer) {
        return post(apiTarget.path(Customer.API_ENDPOINT), customer, Customer.class);
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
            throw buildException(webApplicationException);
        }
    }

    /**
     * Post request entity and deserialize to response class.
     *
     * @param <T> type of class doing post.
     * @param target target of endpoint.
     * @param responseClass response class doing request.
     * @param requestEntity for post.
     * @return deserialized response after post.
     */
    protected static <T> T post(final WebTarget target, final Object requestEntity, final Class<T> responseClass) {
        try {
            return target.request(MediaType.APPLICATION_JSON).post(Entity.json(requestEntity), responseClass);
        } catch (final WebApplicationException webApplicationException) {
            throw buildException(webApplicationException);
        }
    }

    private static RuntimeException buildException(final WebApplicationException webApplicationException) {
        try {
            ErrorModel errorModel = webApplicationException.getResponse().readEntity(ErrorModel.class);

            if (errorModel.getType() == null) {
                return new RuntimeException("Cannot deserialize error from server.");
            }

            switch (errorModel.getType()) {
            case API_ERROR:
                return new ApiException(errorModel);
            case ACQUIRER_DOWN:
                return new ApiException(errorModel);
            case AUTHENTICATION_ERROR:
                return new AuthenticationException(errorModel);
            case INVALID_PARAMETER_ERROR:
                return new InvalidParameterException(errorModel);
            case PAYMENT_ERROR:
                return new PaymentException(errorModel);
            default:
                return new AlternativePaymentException(errorModel);
            }
        } catch (final ProcessingException processingException) {
            return new RuntimeException("Error while processing response body: " + processingException.getMessage());
        }
    }

}
