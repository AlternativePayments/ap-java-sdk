package com.alternativepayments.http;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
     * @return HTTP client to use
     */
    public static Client createDefault() {
        return create(10000, 60000);
    }

    /**
     * Create HTTP client for HTTP communication.
     *
     * @param connectionTimeout Timeout for connection
     * @param readTimeout Timeout for read
     * @return fully configured HTTP client
     */
    public static Client create(final int connectionTimeout, final int readTimeout) {
        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonJsonProvider.class);
        clientConfig.register(createObjectMapper());

        final Client client = ClientBuilder.newClient(clientConfig);
        client.property(ClientProperties.CONNECT_TIMEOUT, connectionTimeout);
        client.property(ClientProperties.READ_TIMEOUT, readTimeout);
        client.property(ClientProperties.FOLLOW_REDIRECTS, false);
        return client;
    }

    private static ObjectMapper createObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(Include.NON_NULL);

        return objectMapper;
    }

}
