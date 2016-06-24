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
import com.alternativepayments.models.transaction.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

//Headers are matching as containing as otherwise we need to write custom ResponseTransformer
//See: https://groups.google.com/forum/#!topic/wiremock-user/pSbIn_qMu00
public class TransactionApiMock {

    private TransactionApiMock() {

    }

    public static void expectSepaPost(final Transaction sepaTransaction) {
        try {
            WireMock.stubFor(
                post(urlMatching("/api/transactions/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .withRequestBody(containing(new ObjectMapperProvider()
                        .getContext(ObjectMapper.class).writeValueAsString(sepaTransaction)))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("transaction/sepa_transaction.json")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void expectMistercashPost(final Transaction mistercashTransaction) {
        try {
            WireMock.stubFor(
                post(urlMatching("/api/transactions/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .withRequestBody(containing(new ObjectMapperProvider()
                        .getContext(ObjectMapper.class).writeValueAsString(mistercashTransaction)))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("transaction/mistercash_transaction.json")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void expectIdealTranasaction(final Transaction idealTransaction) {
        try {
            WireMock.stubFor(
                post(urlMatching("/api/transactions/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .withRequestBody(containing(new ObjectMapperProvider()
                        .getContext(ObjectMapper.class).writeValueAsString(idealTransaction)))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("transaction/ideal_transaction.json")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void expectBrazilPayTranasaction(final Transaction brazilPayTransaction) {
        try {
            WireMock.stubFor(
                post(urlMatching("/api/transactions/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .withRequestBody(containing(new ObjectMapperProvider()
                        .getContext(ObjectMapper.class).writeValueAsString(brazilPayTransaction)))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("transaction/brazil_pay_transaction.json")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void expectCreditCardTranasaction(final Transaction creditCardTransaction) {
        try {
            WireMock.stubFor(
                post(urlMatching("/api/transactions/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .withRequestBody(containing(new ObjectMapperProvider()
                        .getContext(ObjectMapper.class).writeValueAsString(creditCardTransaction)))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("transaction/credit_card_transaction.json")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void expectGetAll() {
        WireMock.stubFor(
                get(urlMatching("/api/transactions/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("transaction/transactions.json")));
    }

    public static void expectGetWithPagination() {
        WireMock.stubFor(
                get(urlPathEqualTo("/api/transactions/"))
                .withQueryParam(Pagination.LIMIT, containing("3"))
                .withQueryParam(Pagination.OFFSET, containing("10"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("transaction/transactions_pagination.json")));
    }

}
