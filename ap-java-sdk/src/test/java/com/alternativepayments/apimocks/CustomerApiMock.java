package com.alternativepayments.apimocks;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import javax.ws.rs.core.Response.Status;

import com.alternativepayments.http.ObjectMapperProvider;
import com.alternativepayments.models.Pagination;
import com.alternativepayments.models.customer.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

// Headers are matching as containing as otherwise we need to write custom ResponseTransformer
// See: https://groups.google.com/forum/#!topic/wiremock-user/pSbIn_qMu00
public class CustomerApiMock {

    private CustomerApiMock() {

    }

    public static void expectGet(final String id) {
        WireMock.stubFor(
                get(urlMatching("/api/customers/" + id))
                .withHeader("Authorization", containing(""))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"id\": \"" + id + "\",\n"
                            + "  \"firstName\": \"John\",\n"
                            + "  \"lastName\": \"Doe\",\n"
                            + "  \"mode\": \"Live\",\n"
                            + "  \"email\": \"john@doe.com\",\n"
                            + "  \"address\": \"Rutledge Ave 409\",\n"
                            + "  \"city\": \"Folsom\",\n"
                            + "  \"zip\": \"19033\",\n"
                            + "  \"country\": \"US\",\n"
                            + "  \"state\": \"PA\",\n"
                            + "  \"phone\": \"55555555555\",\n"
                            + "  \"created\": \"2012-04-23T18:25:43.511Z\"\n"
                            + "}")));
    }

    public static void expectGetAll() {
        WireMock.stubFor(
                get(urlMatching("/api/customers/"))
                .withHeader("Authorization", containing(""))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"customers\": [\n"
                            + "  {\n"
                            + "  \"id\": \"cus_7f0724f3b1d745d49\",\n"
                            + "  \"mode\": \"Live\",\n"
                            + "  \"firstName\": \"Jane\",\n"
                            + "  \"lastName\": \"Doe\",\n"
                            + "  \"email\": \"jane@doe.com\",\n"
                            + "  \"country\": \"DE\",\n"
                            + "  \"created\": \"2012-04-23T18:25:43.511Z\"\n"
                            + "  },\n"
                            + "  {\n"
                            + "  \"id\": \"cus_7f0724f3b1d745d49\",\n"
                            + "  \"firstName\": \"John\",\n"
                            + "  \"lastName\": \"Doe\",\n"
                            + "  \"mode\": \"Live\",\n"
                            + "  \"email\": \"john@doe.com\",\n"
                            + "  \"address\": \"Rutledge Ave 409\",\n"
                            + "  \"city\": \"Folsom\",\n"
                            + "  \"zip\": \"19033\",\n"
                            + "  \"country\": \"US\",\n"
                            + "  \"state\": \"PA\",\n"
                            + "  \"phone\": \"55555555555\",\n"
                            + "  \"created\": \"2012-04-23T18:25:43.511Z\"\n"
                            + "  }\n"
                            + "  ],\n"
                            + "  \"pagination\": {\n"
                            + "  \"offset\": 10,\n"
                            + "  \"limit\": 3,\n"
                            + "  \"count\": 2504\n"
                            + "  }\n"
                            + "}")));
    }

    public static void expectGetWithPagination() {
        WireMock.stubFor(
                get(urlPathEqualTo("/api/customers/"))
                .withQueryParam(Pagination.LIMIT, containing("3"))
                .withQueryParam(Pagination.OFFSET, containing("10"))
                .withHeader("Authorization", containing(""))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                            + "  \"customers\": [\n"
                            + "  {\n"
                            + "  \"id\": \"cus_7f0724f3b1d745d49\",\n"
                            + "  \"mode\": \"Live\",\n"
                            + "  \"firstName\": \"Jane\",\n"
                            + "  \"lastName\": \"Doe\",\n"
                            + "  \"email\": \"jane@doe.com\",\n"
                            + "  \"country\": \"DE\",\n"
                            + "  \"created\": \"2012-04-23T18:25:43.511Z\"\n"
                            + "  },\n"
                            + "  {\n"
                            + "  \"id\": \"cus_7f0724f3b1d745d49\",\n"
                            + "  \"firstName\": \"John\",\n"
                            + "  \"lastName\": \"Doe\",\n"
                            + "  \"mode\": \"Live\",\n"
                            + "  \"email\": \"john@doe.com\",\n"
                            + "  \"address\": \"Rutledge Ave 409\",\n"
                            + "  \"city\": \"Folsom\",\n"
                            + "  \"zip\": \"19033\",\n"
                            + "  \"country\": \"US\",\n"
                            + "  \"state\": \"PA\",\n"
                            + "  \"phone\": \"55555555555\",\n"
                            + "  \"created\": \"2012-04-23T18:25:43.511Z\"\n"
                            + "  },\n"
                            + "  {\n"
                            + "  \"id\": \"cus_15b154da233sxcvby\",\n"
                            + "  \"firstName\": \"John\",\n"
                            + "  \"lastName\": \"Wayne\",\n"
                            + "  \"mode\": \"Live\",\n"
                            + "  \"email\": \"wayne.john@doe.com\",\n"
                            + "  \"address\": \"Wild wild west\",\n"
                            + "  \"city\": \"Texas\",\n"
                            + "  \"zip\": \"19033\",\n"
                            + "  \"country\": \"US\",\n"
                            + "  \"state\": \"Texas\",\n"
                            + "  \"created\": \"2012-04-23T18:25:43.511Z\"\n"
                            + "  }\n"
                            + "  ],\n"
                            + "  \"pagination\": {\n"
                            + "  \"offset\": 10,\n"
                            + "  \"limit\": 3,\n"
                            + "  \"count\": 2504\n"
                            + "  }\n"
                            + "}")));
    }

    public static void expectPost(final Customer customer) {
        try {
            WireMock.stubFor(
                    post(urlMatching("/api/customers/"))
                    .withHeader("Authorization", containing(""))
                    .withRequestBody(containing(new ObjectMapperProvider()
                            .getContext(ObjectMapper.class).writeValueAsString(customer)))
                    .willReturn(aResponse()
                            .withStatus(Status.OK.getStatusCode())
                            .withHeader("Content-Type", "application/json")
                            .withBody("{\n"
                                + "  \"id\": \"cus_bd838e3611d34d598\",\n"
                                + "  \"firstName\": \"John\",\n"
                                + "  \"lastName\": \"Doe\",\n"
                                + "  \"mode\": \"Live\",\n"
                                + "  \"email\": \"john@doe.com\",\n"
                                + "  \"address\": \"Rutledge Ave 409\",\n"
                                + "  \"city\": \"Folsom\",\n"
                                + "  \"zip\": \"19033\",\n"
                                + "  \"country\": \"US\",\n"
                                + "  \"state\": \"PA\",\n"
                                + "  \"phone\": \"55555555555\",\n"
                                + "  \"created\": \"2012-04-23T18:25:43.511Z\"\n"
                                + "}")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
