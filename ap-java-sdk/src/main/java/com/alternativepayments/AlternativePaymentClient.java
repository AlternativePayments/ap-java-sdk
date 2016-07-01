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
import com.alternativepayments.models.BaseCollection;
import com.alternativepayments.models.BaseModel;
import com.alternativepayments.models.ErrorModel;
import com.alternativepayments.models.Pagination;

/**
 * Alternative Payments Java SDK Client.
 */
public class AlternativePaymentClient {

    /**
     * Version of the library. Should be updated together with version from inside maven.
     */
    public static final String VERSION = "0.1.0";

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
     * Create new entity.
     *
     * @param <T> This is the type parameter
     * @param entity entity which will be created
     * @param endpoint endpoint to query
     * @param clazz class for deserialization
     * @return
     * @return newly created entity
     */
    public <T extends BaseModel> T create(final T entity, final String endpoint, Class<T> clazz) {
        return post(apiTarget.path(endpoint), entity, clazz);
    }

    /**
     * Get single entity by ID.
     *
     * @param <T> This is the type parameter
     * @param id id of entity
     * @param endpoint endpoint to query
     * @param clazz class for deserialization
     * @return single entity by id
     */
    public <T extends BaseModel> T getById(final String id, final String endpoint, Class<T> clazz) {
        return get(apiTarget.path(endpoint + id), clazz);
    }

    /**
     * Get all entities.
     *
     * @param <T> This is the type parameter
     * @param endpoint endpoint to query
     * @param clazz class for deserialization
     * @return all entities for given type
     */
    public <T extends BaseCollection> T getAll(final String endpoint, Class<T> clazz) {
        return get(apiTarget.path(endpoint), clazz);
    }

    /**
     * Get all entities.
     *
     * @param <T> This is the type parameter
     * @param limit how many records we want
     * @param offset offset of the requested results
     * @param endpoint endpoint to query
     * @param clazz class for deserialization
     * @return all entities for given type
     */
    public <T extends BaseCollection> T getAllWithPagination(final int limit, final int offset, final String endpoint,
            Class<T> clazz) {
        return get(apiTarget.path(endpoint).queryParam(Pagination.LIMIT, limit).queryParam(Pagination.OFFSET, offset),
                clazz);
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
