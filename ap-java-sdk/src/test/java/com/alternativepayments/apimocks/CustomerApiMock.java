package com.alternativepayments.apimocks;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

import javax.ws.rs.core.Response.Status;

import com.github.tomakehurst.wiremock.client.WireMock;

public class CustomerApiMock {

    private CustomerApiMock() {

    }

    public static void expectGet(final String id) {
        WireMock.stubFor(
                get(WireMock.urlMatching("/api/customers/" + id))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"id\": \"" + id + "\",\n"
                            + "  \"firstName\": \"John\",\n"
                            + "  \"lastName\": \"Doe\",\n"
                            + "  \"created\": \"2012-04-23T18:25:43.511Z\"\n"
                            + "}")));
    }

    public static void expectGetAll() {
        WireMock.stubFor(
                get(WireMock.urlMatching("/api/customers/"))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"customers\": [\n"
                            + "  {\n"
                            + "  \"id\": \"cus_7f0724f3b1d745d49\",\n"
                            + "  \"firstName\": \"John\",\n"
                            + "  \"lastName\": \"Doe\",\n"
                            + "  \"created\": \"2012-04-23T18:25:43.511Z\"\n"
                            + "  },\n"
                            + "  {\n"
                            + "  \"id\": \"cus_7f0724f3b1d745d49\",\n"
                            + "  \"firstName\": \"John\",\n"
                            + "  \"lastName\": \"Doe\",\n"
                            + "  \"created\": \"2012-04-23T18:25:43.511Z\"\n"
                            + "  }\n"
                            + "  ],\n"
                            + "  \"pagination\": {\n"
                            + "  \"offset\": 10,\n"
                            + "  \"limit\": 2,\n"
                            + "  \"count\": 2504\n"
                            + "  }\n"
                            + "}")));
    }
}
