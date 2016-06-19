package com.alternativepayments.base;

import org.junit.BeforeClass;
import org.junit.Rule;

import com.alternativepayments.AlternativePaymentClient;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class BaseApiResourceTest {

    protected static AlternativePaymentClient alternativePaymentClient;

    private static final String HOST = "http://localhost";
    private static final int PORT = 8089;
    private static final String API_BASE = "/api/";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    @BeforeClass
    public static void init() {
        alternativePaymentClient = new AlternativePaymentClient(HOST + ":" + PORT + API_BASE, "whatever");
    }

}
