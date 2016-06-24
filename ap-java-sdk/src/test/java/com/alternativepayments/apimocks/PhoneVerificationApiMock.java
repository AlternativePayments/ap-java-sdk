package com.alternativepayments.apimocks;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import javax.ws.rs.core.Response.Status;

import com.alternativepayments.AlternativePaymentClient;
import com.alternativepayments.http.ObjectMapperProvider;
import com.alternativepayments.models.transaction.PhoneVerification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

//Headers are matching as containing as otherwise we need to write custom ResponseTransformer
//See: https://groups.google.com/forum/#!topic/wiremock-user/pSbIn_qMu00
public class PhoneVerificationApiMock {

    private PhoneVerificationApiMock() {

    }

    public static void expectPost(final PhoneVerification phoneVerification) {
        try {
            WireMock.stubFor(
                post(urlMatching("/api/phoneverification/"))
                .withHeader("Authorization", containing(""))
                .withHeader("User-Agent", equalTo("AlternativePayments Java SDK v" + AlternativePaymentClient.VERSION))
                .withRequestBody(containing(new ObjectMapperProvider()
                        .getContext(ObjectMapper.class).writeValueAsString(phoneVerification)))
                .willReturn(aResponse()
                        .withStatus(Status.OK.getStatusCode())
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("phoneverification/phoneverification.json")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
