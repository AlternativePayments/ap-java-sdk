package com.alternativepayments.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.alternativepayments.apimocks.PhoneVerificationApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.models.transaction.PhoneVerification;

public class PhoneVerificationModelTest extends BaseApiResourceTest {

    @Test
    public void create_new_phoneverification() {
        String phoneNumber = "+15555555555";
        PhoneVerification phoneverification = new PhoneVerification.Builder(API_PUBLIC_KEY, phoneNumber).build();

        PhoneVerificationApiMock.expectPost(phoneverification);

        final PhoneVerification createdPphoneverification = alternativePaymentClient
                .createPhoneVerification(phoneNumber, PhoneVerification.API_ENDPOINT, PhoneVerification.class);

        assertThat(createdPphoneverification.getKey()).isEqualTo("pk_live_cyelxxxxxxxxddddddddBQuFeAGDDNG");
        assertThat(createdPphoneverification.getType()).isEqualTo("SMS");
        assertThat(createdPphoneverification.getPhone()).isEqualTo("15555555555");
        assertThat(createdPphoneverification.getToken()).isEqualTo("8b340ecdfc63ccccccc1fe59310");
        assertThat(createdPphoneverification.getMode()).isEqualTo("Live");
        assertThat(createdPphoneverification.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");
    }
}
