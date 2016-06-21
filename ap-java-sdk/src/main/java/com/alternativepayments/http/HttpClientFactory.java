package com.alternativepayments.http;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * Factory to create HTTP client for communication. All configuration for HTTP communication and object mapping is
 * wrapped in this class.
 */
public class HttpClientFactory {

    private HttpClientFactory() {

    }

    /**
     * Create default HTTP client with connection timeout of 10000 and read timeout of 60000.
     *
     * @param apiKey authentication token for client
     *
     * @return HTTP client to use
     */
    public static Client createDefault(final String apiKey) {
        return create(10000, 60000, apiKey);
    }

    /**
     * Create HTTP client for HTTP communication.
     *
     * @param connectionTimeout Timeout for connection
     * @param readTimeout Timeout for read
     * @param apiKey authentication token for client
     *
     * @return fully configured HTTP client
     */
    public static Client create(final int connectionTimeout, final int readTimeout, final String apiKey) {
        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonJsonProvider.class);
        clientConfig.register(new ObjectMapperProvider());
        clientConfig.register(new AuthorizationClientFilter(apiKey));

        final Client client = ClientBuilder.newClient(clientConfig);
        client.property(ClientProperties.CONNECT_TIMEOUT, connectionTimeout);
        client.property(ClientProperties.READ_TIMEOUT, readTimeout);
        client.property(ClientProperties.FOLLOW_REDIRECTS, false);
        return client;
    }
}
