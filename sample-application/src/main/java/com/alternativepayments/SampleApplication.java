package com.alternativepayments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Sample Application (showcase for Alternative Payments Java SDK).
 */
@SpringBootApplication
public class SampleApplication {

    /**
     * Main method starting Spring application which uses Alternative Payments Java SDK.
     * @param args command line arguments
     * @throws Exception exception while starting Spring application
     */
    public static void main(final String[] args) throws Exception {
        SpringApplication.run(SampleApplication.class, args);
    }
}
