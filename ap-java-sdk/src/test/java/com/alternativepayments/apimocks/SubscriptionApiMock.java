package com.alternativepayments.apimocks;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import javax.ws.rs.core.Response.Status;

import com.alternativepayments.AlternativePaymentClient;
import com.alternativepayments.http.ObjectMapperProvider;
import com.alternativepayments.models.Pagination;
import com.alternativepayments.models.subscription.Subscription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

// Headers are matching as containing as otherwise we need to write custom ResponseTransformer
// See: https://groups.google.com/forum/#!topic/wiremock-user/pSbIn_qMu00
public class SubscriptionApiMock {

    private SubscriptionApiMock() {

    }

    public static void expectGet(final String id) {
        WireMock.stubFor(
                get(urlMatching("/api/subscriptions/" + id))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("subscription/subscription.json")));
    }

    public static void expectGetAll() {
        WireMock.stubFor(
                get(urlMatching("/api/subscriptions/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("subscription/subscriptions.json")));
    }

    public static void expectGetWithPagination() {
        WireMock.stubFor(
                get(urlPathEqualTo("/api/subscriptions/"))
                .withQueryParam(Pagination.LIMIT, containing("3"))
                .withQueryParam(Pagination.OFFSET, containing("10"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("subscription/subscriptions_pagination.json")));
    }

    public static void expectPost(final Subscription subscription) {
        try {
            WireMock.stubFor(
                post(urlMatching("/api/subscriptions/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .withRequestBody(containing(new ObjectMapperProvider()
                        .getContext(ObjectMapper.class).writeValueAsString(subscription)))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("subscription/subscription.json")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
