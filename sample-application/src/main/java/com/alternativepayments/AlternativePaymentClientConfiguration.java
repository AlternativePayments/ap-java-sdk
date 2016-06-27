package com.alternativepayments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Alternative Payments client.
 *
 */
@Configuration
public class AlternativePaymentClientConfiguration {

    @Value("${alternativePayment.apiBase}")
    private String apiBase;

    @Value("${alternativePayment.apiKey}")
    private String apiKey;

    /**
     * Create Alternative Payment Client which will be used for communication with backend.
     *
     * @return configured Alternative Payment client.
     */
    @Bean
    public AlternativePaymentClient createAlternativePaymentClient() {
        return new AlternativePaymentClient(apiBase, apiKey);
    }

}
