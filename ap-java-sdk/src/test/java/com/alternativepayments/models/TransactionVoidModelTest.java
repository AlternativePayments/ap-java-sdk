package com.alternativepayments.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.alternativepayments.apimocks.TransactionVoidApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.models.transaction.ReturnReason;
import com.alternativepayments.models.transaction.TransactionVoid;
import com.alternativepayments.models.transaction.TransactionVoidCollection;

public class TransactionVoidModelTest extends BaseApiResourceTest {

    @Test
    public void return_void_for_id() {
        final String id = "void_0a3f6b2";
        final String transactionId = "trn_41f1487";
        TransactionVoidApiMock.expectGet(id, transactionId);

        final TransactionVoid transactionVoid = alternativePaymentClient.getById(id,
                TransactionVoid.getApiEndpoint(transactionId), TransactionVoid.class);

        assertThat(transactionVoid.getId()).isEqualTo("void_0a3f6b2");
        assertThat(transactionVoid.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(transactionVoid.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(transactionVoid.getAmount()).isEqualTo(BigDecimal.valueOf(4000));
        assertThat(transactionVoid.getCurrency()).isEqualTo("EUR");
        assertThat(transactionVoid.getStatus()).isEqualTo("Approved");
        assertThat(transactionVoid.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(transactionVoid.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(transactionVoid.getOriginalTransaction().getStatus()).isEqualTo("Voided");
    }

    @Test
    public void return_all_voids() {
        final String transactionId = "trn_41f1487";
        TransactionVoidApiMock.expectGetAll(transactionId);

        final TransactionVoidCollection voids = alternativePaymentClient
                .getAll(TransactionVoid.getApiEndpoint(transactionId), TransactionVoidCollection.class);

        assertThat(voids.getVoidTransactions()).hasSize(2);
        assertThat(voids.getPagination().getOffset()).isEqualTo(10);
        assertThat(voids.getPagination().getLimit()).isEqualTo(3);
        assertThat(voids.getPagination().getCount()).isEqualTo(2504);

        final TransactionVoid firstTransactionVoid = voids.getVoidTransactions().get(0);
        assertThat(firstTransactionVoid.getId()).isEqualTo("void_0a3f6b2");
        assertThat(firstTransactionVoid.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(firstTransactionVoid.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(firstTransactionVoid.getAmount()).isEqualTo(BigDecimal.valueOf(4000));
        assertThat(firstTransactionVoid.getCurrency()).isEqualTo("EUR");
        assertThat(firstTransactionVoid.getStatus()).isEqualTo("Approved");
        assertThat(firstTransactionVoid.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(firstTransactionVoid.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(firstTransactionVoid.getOriginalTransaction().getStatus()).isEqualTo("Voided");

        final TransactionVoid secondTransactionVoid = voids.getVoidTransactions().get(1);
        assertThat(secondTransactionVoid.getId()).isEqualTo("void_0a3f6b3");
        assertThat(secondTransactionVoid.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(secondTransactionVoid.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(secondTransactionVoid.getAmount()).isEqualTo(BigDecimal.valueOf(2000));
        assertThat(secondTransactionVoid.getCurrency()).isEqualTo("EUR");
        assertThat(secondTransactionVoid.getStatus()).isEqualTo("Approved");
        assertThat(secondTransactionVoid.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(secondTransactionVoid.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(secondTransactionVoid.getOriginalTransaction().getStatus()).isEqualTo("Voided");

    }

    @Test
    public void create_new_transaction_void() {
        TransactionVoid transactionVoid = new TransactionVoid.Builder(ReturnReason.FRAUD, "trn_41f1487").build();

        TransactionVoidApiMock.expectPost(transactionVoid);

        final TransactionVoid createdVoid = alternativePaymentClient.create(transactionVoid,
                TransactionVoid.getApiEndpoint(transactionVoid.getOriginalTransactionId()), TransactionVoid.class);

        assertThat(createdVoid.getId()).isEqualTo("void_0a3f6b2");
        assertThat(createdVoid.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(createdVoid.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(createdVoid.getAmount()).isEqualTo(BigDecimal.valueOf(4000));
        assertThat(createdVoid.getCurrency()).isEqualTo("EUR");
        assertThat(createdVoid.getStatus()).isEqualTo("Approved");
        assertThat(createdVoid.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(createdVoid.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(createdVoid.getOriginalTransaction().getStatus()).isEqualTo("Voided");
    }

    @Test
    public void return_transaction_voids_with_pagination() {
        final String transactionId = "trn_41f1487";
        TransactionVoidApiMock.expectGetWithPagination(transactionId);

        final TransactionVoidCollection voids = alternativePaymentClient.getAllWithPagination(3, 10,
                TransactionVoid.getApiEndpoint(transactionId), TransactionVoidCollection.class);

        assertThat(voids.getVoidTransactions()).hasSize(3);
        assertThat(voids.getPagination().getOffset()).isEqualTo(10);
        assertThat(voids.getPagination().getLimit()).isEqualTo(3);
        assertThat(voids.getPagination().getCount()).isEqualTo(2504);

        final TransactionVoid firstTransactionVoid = voids.getVoidTransactions().get(0);
        assertThat(firstTransactionVoid.getId()).isEqualTo("void_0a3f6b2");
        assertThat(firstTransactionVoid.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(firstTransactionVoid.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(firstTransactionVoid.getAmount()).isEqualTo(BigDecimal.valueOf(4000));
        assertThat(firstTransactionVoid.getCurrency()).isEqualTo("EUR");
        assertThat(firstTransactionVoid.getStatus()).isEqualTo("Approved");
        assertThat(firstTransactionVoid.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(firstTransactionVoid.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(firstTransactionVoid.getOriginalTransaction().getStatus()).isEqualTo("Voided");

        final TransactionVoid secondTransactionVoid = voids.getVoidTransactions().get(1);
        assertThat(secondTransactionVoid.getId()).isEqualTo("void_0a3f6b3");
        assertThat(secondTransactionVoid.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(secondTransactionVoid.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(secondTransactionVoid.getAmount()).isEqualTo(BigDecimal.valueOf(2000));
        assertThat(secondTransactionVoid.getCurrency()).isEqualTo("EUR");
        assertThat(secondTransactionVoid.getStatus()).isEqualTo("Approved");
        assertThat(secondTransactionVoid.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(secondTransactionVoid.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(secondTransactionVoid.getOriginalTransaction().getStatus()).isEqualTo("Voided");

        final TransactionVoid thirdTransactionVoid = voids.getVoidTransactions().get(2);
        assertThat(thirdTransactionVoid.getId()).isEqualTo("void_0a3f6b4");
        assertThat(thirdTransactionVoid.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(thirdTransactionVoid.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(thirdTransactionVoid.getAmount()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(thirdTransactionVoid.getCurrency()).isEqualTo("EUR");
        assertThat(thirdTransactionVoid.getStatus()).isEqualTo("Approved");
        assertThat(thirdTransactionVoid.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(thirdTransactionVoid.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(thirdTransactionVoid.getOriginalTransaction().getStatus()).isEqualTo("Voided");
    }
}
