package com.alternativepayments.http;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;

import org.glassfish.jersey.internal.util.Base64;

import com.alternativepayments.http.error.AuthenticationException;

/**
 * Authorization class responsible for ensuring every request has Authentication token.
 */
public class AuthorizationClientFilter implements ClientRequestFilter {

    private String apiKey;

    /**
     * Create authorization filter for requests.
     *
     * @param apiKey token for talking to server.
     */
    public AuthorizationClientFilter(final String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void filter(final ClientRequestContext requestContext) throws IOException {

        if (apiKey.equals("")) {
            throw new AuthenticationException("No API key provided.", null, 401);
        }

        requestContext.getHeaders().putSingle(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64.encodeAsString(apiKey));
    }

}
