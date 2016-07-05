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
import com.alternativepayments.models.transaction.TransactionVoid;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

//Headers are matching as containing as otherwise we need to write custom ResponseTransformer
//See: https://groups.google.com/forum/#!topic/wiremock-user/pSbIn_qMu00
public class TransactionVoidApiMock {

    private TransactionVoidApiMock() {

    }

    public static void expectPost(final TransactionVoid transactionVoid) {
        try {
            WireMock.stubFor(
                post(urlMatching("/api/transactions/" + transactionVoid.getOriginalTransactionId() + "/voids/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .withRequestBody(containing(new ObjectMapperProvider()
                        .getContext(ObjectMapper.class).writeValueAsString(transactionVoid)))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("void/void.json")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void expectGet(final String id, final String transactionId) {
        WireMock.stubFor(
                get(urlMatching("/api/transactions/" + transactionId + "/voids/" + id))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("void/void.json")));
    }


    public static void expectGetAll(String transactionId) {
        WireMock.stubFor(
                get(urlMatching("/api/transactions/" + transactionId + "/voids/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("void/voids.json")));
    }

    public static void expectGetWithPagination(String transactionId) {
        WireMock.stubFor(
                get(urlPathEqualTo("/api/transactions/" + transactionId + "/voids/"))
                .withQueryParam(Pagination.LIMIT, containing("3"))
                .withQueryParam(Pagination.OFFSET, containing("10"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("void/voids_pagination.json")));
    }

}
