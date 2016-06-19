package com.alternativepayments;

import javax.ws.rs.client.Client;

import com.alternativepayments.http.HttpClientFactory;

/**
 * Alternative Payments Java SDK Client.
 */
public class AlternativePaymentClient {

    private Client httpClient;

    /**
     * Create default HTTP client with API base and API key.
     *
     * @param apiBase base URL of API.
     * @param apiKey API key to use.
     */
    public AlternativePaymentClient(final String apiBase, final String apiKey) {
        this.httpClient = HttpClientFactory.createDefault();
    }

}
