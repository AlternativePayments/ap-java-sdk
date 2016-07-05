package com.alternativepayments.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.alternativepayments.apimocks.TransactionRefundApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.models.transaction.ReturnReason;
import com.alternativepayments.models.transaction.TransactionRefund;
import com.alternativepayments.models.transaction.TransactionRefundCollection;

public class TransactionRefundModelTest extends BaseApiResourceTest {

    @Test
    public void return_refund_for_id() {
        final String id = "ref_f811756";
        final String transactionId = "trn_41f1487";
        TransactionRefundApiMock.expectGet(id, transactionId);

        final TransactionRefund transactionRefund = alternativePaymentClient.getById(id,
                TransactionRefund.getApiEndpoint(transactionId), TransactionRefund.class);

        assertThat(transactionRefund.getId()).isEqualTo("ref_f811756");
        assertThat(transactionRefund.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(transactionRefund.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(transactionRefund.getAmount()).isEqualTo(100);
        assertThat(transactionRefund.getCurrency()).isEqualTo("EUR");
        assertThat(transactionRefund.getStatus()).isEqualTo("Approved");
        assertThat(transactionRefund.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(transactionRefund.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(transactionRefund.getOriginalTransaction().getStatus()).isEqualTo("Voided");
    }

    @Test
    public void return_all_refunds() {
        final String transactionId = "trn_41f1487";
        TransactionRefundApiMock.expectGetAll(transactionId);

        final TransactionRefundCollection refunds = alternativePaymentClient
                .getAll(TransactionRefund.getApiEndpoint(transactionId), TransactionRefundCollection.class);

        assertThat(refunds.getRefundTransactions()).hasSize(2);
        assertThat(refunds.getPagination().getOffset()).isEqualTo(10);
        assertThat(refunds.getPagination().getLimit()).isEqualTo(3);
        assertThat(refunds.getPagination().getCount()).isEqualTo(2504);

        final TransactionRefund firstTransactionRefund = refunds.getRefundTransactions().get(0);
        assertThat(firstTransactionRefund.getId()).isEqualTo("ref_f811756");
        assertThat(firstTransactionRefund.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(firstTransactionRefund.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(firstTransactionRefund.getAmount()).isEqualTo(4000);
        assertThat(firstTransactionRefund.getCurrency()).isEqualTo("EUR");
        assertThat(firstTransactionRefund.getStatus()).isEqualTo("Approved");
        assertThat(firstTransactionRefund.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(firstTransactionRefund.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(firstTransactionRefund.getOriginalTransaction().getStatus()).isEqualTo("Voided");

        final TransactionRefund secondTransactionRefund = refunds.getRefundTransactions().get(1);
        assertThat(secondTransactionRefund.getId()).isEqualTo("ref_f811757");
        assertThat(secondTransactionRefund.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(secondTransactionRefund.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(secondTransactionRefund.getAmount()).isEqualTo(2000);
        assertThat(secondTransactionRefund.getCurrency()).isEqualTo("EUR");
        assertThat(secondTransactionRefund.getStatus()).isEqualTo("Approved");
        assertThat(secondTransactionRefund.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(secondTransactionRefund.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(secondTransactionRefund.getOriginalTransaction().getStatus()).isEqualTo("Voided");

    }

    @Test
    public void create_new_transaction_refund() {
        TransactionRefund transactionRefund = new TransactionRefund.Builder(ReturnReason.FRAUD, "trn_41f1487").build();

        TransactionRefundApiMock.expectPost(transactionRefund);

        final TransactionRefund createdRefund = alternativePaymentClient.create(transactionRefund,
                TransactionRefund.getApiEndpoint(transactionRefund.getOriginalTransactionId()),
                TransactionRefund.class);

        assertThat(createdRefund.getId()).isEqualTo("ref_f811756");
        assertThat(createdRefund.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(createdRefund.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(createdRefund.getAmount()).isEqualTo(100);
        assertThat(createdRefund.getCurrency()).isEqualTo("EUR");
        assertThat(createdRefund.getStatus()).isEqualTo("Approved");
        assertThat(createdRefund.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(createdRefund.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(createdRefund.getOriginalTransaction().getStatus()).isEqualTo("Voided");
    }

    @Test
    public void return_transaction_refunds_with_pagination() {
        final String transactionId = "trn_41f1487";
        TransactionRefundApiMock.expectGetWithPagination(transactionId);

        final TransactionRefundCollection refunds = alternativePaymentClient.getAllWithPagination(3, 10,
                TransactionRefund.getApiEndpoint(transactionId), TransactionRefundCollection.class);

        assertThat(refunds.getRefundTransactions()).hasSize(3);
        assertThat(refunds.getPagination().getOffset()).isEqualTo(10);
        assertThat(refunds.getPagination().getLimit()).isEqualTo(3);
        assertThat(refunds.getPagination().getCount()).isEqualTo(2504);

        final TransactionRefund firstTransactionVoid = refunds.getRefundTransactions().get(0);
        assertThat(firstTransactionVoid.getId()).isEqualTo("ref_f811756");
        assertThat(firstTransactionVoid.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(firstTransactionVoid.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(firstTransactionVoid.getAmount()).isEqualTo(4000);
        assertThat(firstTransactionVoid.getCurrency()).isEqualTo("EUR");
        assertThat(firstTransactionVoid.getStatus()).isEqualTo("Approved");
        assertThat(firstTransactionVoid.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(firstTransactionVoid.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(firstTransactionVoid.getOriginalTransaction().getStatus()).isEqualTo("Voided");

        final TransactionRefund secondTransactionRefund = refunds.getRefundTransactions().get(1);
        assertThat(secondTransactionRefund.getId()).isEqualTo("ref_f811757");
        assertThat(secondTransactionRefund.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(secondTransactionRefund.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(secondTransactionRefund.getAmount()).isEqualTo(2000);
        assertThat(secondTransactionRefund.getCurrency()).isEqualTo("EUR");
        assertThat(secondTransactionRefund.getStatus()).isEqualTo("Approved");
        assertThat(secondTransactionRefund.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(secondTransactionRefund.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(secondTransactionRefund.getOriginalTransaction().getStatus()).isEqualTo("Voided");

        final TransactionRefund thirdTransactionRefund = refunds.getRefundTransactions().get(2);
        assertThat(thirdTransactionRefund.getId()).isEqualTo("ref_f811758");
        assertThat(thirdTransactionRefund.getOriginalTransactionId()).isEqualTo("trn_41f1487");
        assertThat(thirdTransactionRefund.getReason()).isEqualTo(ReturnReason.FRAUD);
        assertThat(thirdTransactionRefund.getAmount()).isEqualTo(1000);
        assertThat(thirdTransactionRefund.getCurrency()).isEqualTo("EUR");
        assertThat(thirdTransactionRefund.getStatus()).isEqualTo("Approved");
        assertThat(thirdTransactionRefund.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
        assertThat(thirdTransactionRefund.getOriginalTransaction().getId()).isEqualTo("trn_41f1487");
        assertThat(thirdTransactionRefund.getOriginalTransaction().getStatus()).isEqualTo("Voided");
    }
}
