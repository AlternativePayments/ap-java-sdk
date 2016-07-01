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
import com.alternativepayments.models.plan.Plan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

// Headers are matching as containing as otherwise we need to write custom ResponseTransformer
// See: https://groups.google.com/forum/#!topic/wiremock-user/pSbIn_qMu00
public class PlanApiMock {

    private PlanApiMock() {

    }

    public static void expectGet(final String id) {
        WireMock.stubFor(
                get(urlMatching("/api/plans/" + id))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("plan/plan.json")));
    }

    public static void expectGetAll() {
        WireMock.stubFor(
                get(urlMatching("/api/plans/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("plan/plans.json")));
    }

    public static void expectGetWithPagination() {
        WireMock.stubFor(
                get(urlPathEqualTo("/api/plans/"))
                .withQueryParam(Pagination.LIMIT, containing("3"))
                .withQueryParam(Pagination.OFFSET, containing("10"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("plan/plans_pagination.json")));
    }

    public static void expectPost(final Plan plan) {
        try {
            WireMock.stubFor(
                post(urlMatching("/api/plans/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .withRequestBody(containing(new ObjectMapperProvider()
                        .getContext(ObjectMapper.class).writeValueAsString(plan)))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("plan/plan.json")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
