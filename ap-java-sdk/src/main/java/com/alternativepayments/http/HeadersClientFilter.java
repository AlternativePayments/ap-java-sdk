package com.alternativepayments.http;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;

import org.glassfish.jersey.internal.util.Base64;

import com.alternativepayments.AlternativePaymentClient;
import com.alternativepayments.http.error.AuthenticationException;

/**
 * Filter class responsible for ensuring every request has Authentication token and other custom headers.
 */
public class HeadersClientFilter implements ClientRequestFilter {

    private String apiKey;

    /**
     * Create headers filter class for requests.
     *
     * @param apiKey token for talking to server.
     */
    public HeadersClientFilter(final String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void filter(final ClientRequestContext requestContext) throws IOException {

        if (apiKey.equals("")) {
            throw new AuthenticationException("No API key provided.", null, 401);
        }

        requestContext.getHeaders().putSingle(HttpHeaders.AUTHORIZATION, "Basic " + Base64.encodeAsString(apiKey));
        requestContext.getHeaders().putSingle(HttpHeaders.USER_AGENT,
                "AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION);
    }

}
