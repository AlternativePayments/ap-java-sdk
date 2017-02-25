package com.alternativepayments.apimocks;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import javax.ws.rs.core.Response.Status;

import com.alternativepayments.AlternativePaymentClient;
import com.github.tomakehurst.wiremock.client.WireMock;

//Headers are matching as containing as otherwise we need to write custom ResponseTransformer
//See: https://groups.google.com/forum/#!topic/wiremock-user/pSbIn_qMu00
public class PaymentOptionApiMock {

    private PaymentOptionApiMock() {

    }

    public static void expectGet(final String apiPublicKey, final String paymentOption) {
        WireMock.stubFor(
                get(urlMatching("/api/websites/" + apiPublicKey + "/paymentoptions/" + paymentOption))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("paymentoption/paymentoption.json")));
    }
}
