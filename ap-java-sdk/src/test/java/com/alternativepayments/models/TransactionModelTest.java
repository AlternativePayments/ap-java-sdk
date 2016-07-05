package com.alternativepayments.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.alternativepayments.apimocks.TransactionApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.models.customer.Customer;
import com.alternativepayments.models.transaction.Payment;
import com.alternativepayments.models.transaction.RedirectUrls;
import com.alternativepayments.models.transaction.Transaction;
import com.alternativepayments.models.transaction.TransactionCollection;

public class TransactionModelTest extends BaseApiResourceTest {

    @Test
    public void test_create_new_sepa_transaction() {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("SEPA", "John Doe").iban("DE71XXXXX3330").build();

        Transaction sepaTransaction = new Transaction.Builder(payment, null, 500, "EUR")
                .customer(customer).build();

        TransactionApiMock.expectSepaPost(sepaTransaction);

        final Transaction createdSepaTransaction = alternativePaymentClient.create(sepaTransaction,
                Transaction.API_ENDPOINT, Transaction.class);

        assertThat(createdSepaTransaction.getId()).isEqualTo("trn_d12209838b");
        assertThat(createdSepaTransaction.getMode()).isEqualTo("Live");
        assertThat(createdSepaTransaction.getAmount()).isEqualTo(500);
        assertThat(createdSepaTransaction.getCurrency()).isEqualTo("EUR");
        assertThat(createdSepaTransaction.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(createdSepaTransaction.getCustomer().getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(createdSepaTransaction.getCustomer().getMode()).isEqualTo("Live");
        assertThat(createdSepaTransaction.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(createdSepaTransaction.getCustomer().getLastName()).isEqualTo("Doe");
        assertThat(createdSepaTransaction.getCustomer().getEmail()).isEqualTo("john@doe.com");
        assertThat(createdSepaTransaction.getCustomer().getCountry()).isEqualTo("DE");
        assertThat(createdSepaTransaction.getCustomer().getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(createdSepaTransaction.getPayment().getId()).isEqualTo("pay_ffd25121f84e4d249");
        assertThat(createdSepaTransaction.getPayment().getMode()).isEqualTo("Live");
        assertThat(createdSepaTransaction.getPayment().getHolder()).isEqualTo("John Doe");
        assertThat(createdSepaTransaction.getPayment().getPaymentOption()).isEqualTo("SEPA");
        assertThat(createdSepaTransaction.getPayment().getIban()).isEqualTo("DE71XXXXX3330");
    }

    @Test
    public void test_create_new_mistercash_transaction() {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("mistercash", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction mistercashTransaction = new Transaction.Builder(payment, null, 500, "EUR")
                .redirectUrls(redirectUrls).customer(customer).build();

        TransactionApiMock.expectMistercashPost(mistercashTransaction);

        final Transaction createdMistercashTransaction = alternativePaymentClient
                .create(mistercashTransaction, Transaction.API_ENDPOINT, Transaction.class);

        assertThat(createdMistercashTransaction.getId()).isEqualTo("trn_d12209838b");
        assertThat(createdMistercashTransaction.getMode()).isEqualTo("Live");
        assertThat(createdMistercashTransaction.getAmount()).isEqualTo(500);
        assertThat(createdMistercashTransaction.getCurrency()).isEqualTo("EUR");
        assertThat(createdMistercashTransaction.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");
        assertThat(createdMistercashTransaction.getMerchantPassThruData()).isEqualTo("Order #1234958");

        assertThat(createdMistercashTransaction.getCustomer().getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(createdMistercashTransaction.getCustomer().getMode()).isEqualTo("Live");
        assertThat(createdMistercashTransaction.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(createdMistercashTransaction.getCustomer().getLastName()).isEqualTo("Doe");
        assertThat(createdMistercashTransaction.getCustomer().getEmail()).isEqualTo("john@doe.com");
        assertThat(createdMistercashTransaction.getCustomer().getCountry()).isEqualTo("DE");
        assertThat(createdMistercashTransaction.getCustomer().getCreated().toString())
                .isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(createdMistercashTransaction.getPayment().getId()).isEqualTo("pay_ffd25121f84e4d249");
        assertThat(createdMistercashTransaction.getPayment().getMode()).isEqualTo("Live");
        assertThat(createdMistercashTransaction.getPayment().getHolder()).isEqualTo("John Doe");
        assertThat(createdMistercashTransaction.getPayment().getPaymentOption()).isEqualTo("mistercash");

        assertThat(createdMistercashTransaction.getRedirectUrls().getReturnUrl())
                .isEqualTo("http://plugins.alternativepayments.com/message/success.html");
        assertThat(createdMistercashTransaction.getRedirectUrls().getCancelUrl())
                .isEqualTo("http://plugins.alternativepayments.com/message/failure.html");
    }

    @Test
    public void test_create_ideal_transaction() {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("ideal", "John Doe").bankCode("ABN_AMRO").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction idealTransaction = new Transaction.Builder(payment, null, 500, "EUR")
                .redirectUrls(redirectUrls).customer(customer).build();

        TransactionApiMock.expectIdealTranasaction(idealTransaction);

        final Transaction createdIdealTransaction = alternativePaymentClient.create(idealTransaction,
                Transaction.API_ENDPOINT, Transaction.class);

        assertThat(createdIdealTransaction.getId()).isEqualTo("trn_d12209838b");
        assertThat(createdIdealTransaction.getMode()).isEqualTo("Live");
        assertThat(createdIdealTransaction.getAmount()).isEqualTo(500);
        assertThat(createdIdealTransaction.getCurrency()).isEqualTo("EUR");
        assertThat(createdIdealTransaction.getIpAddress()).isEqualTo("89.216.124.9");
        assertThat(createdIdealTransaction.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");
        assertThat(createdIdealTransaction.getRedirectUrl())
                .isEqualTo("http://mybankingsite.com/hRedirection.aspx?transaction_id=trn_1a5f5e0c97");

        assertThat(createdIdealTransaction.getCustomer().getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(createdIdealTransaction.getCustomer().getMode()).isEqualTo("Live");
        assertThat(createdIdealTransaction.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(createdIdealTransaction.getCustomer().getLastName()).isEqualTo("Doe");
        assertThat(createdIdealTransaction.getCustomer().getEmail()).isEqualTo("john@doe.com");
        assertThat(createdIdealTransaction.getCustomer().getCountry()).isEqualTo("DE");
        assertThat(createdIdealTransaction.getCustomer().getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(createdIdealTransaction.getPayment().getId()).isEqualTo("pay_ffd25121f84e4d249");
        assertThat(createdIdealTransaction.getPayment().getMode()).isEqualTo("Live");
        assertThat(createdIdealTransaction.getPayment().getHolder()).isEqualTo("John Doe");
        assertThat(createdIdealTransaction.getPayment().getPaymentOption()).isEqualTo("ideal");
        assertThat(createdIdealTransaction.getPayment().getBankCode()).isEqualTo("ABN_AMRO");

        assertThat(createdIdealTransaction.getRedirectUrls().getReturnUrl())
                .isEqualTo("http://plugins.alternativepayments.com/message/success.html");
        assertThat(createdIdealTransaction.getRedirectUrls().getCancelUrl())
                .isEqualTo("http://plugins.alternativepayments.com/message/failure.html");
    }

    @Test
    public void test_create_brazil_pay_transaction() {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "BR").address("Rua E").address2("1040")
                .city("Maracanaú").zip("61919-230").state("CE").phone("+55A7xxxxxxx").birthDate("12/04/1979").build();
        Payment payment = new Payment.Builder("BrazilPayBankTransfer", "John Doe").bankCode("hsbc")
                .documentId("853.513.468-93").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction brazilPayTransaction = new Transaction.Builder(payment, null, 100, "EUR")
                .redirectUrls(redirectUrls).customer(customer).build();

        TransactionApiMock.expectBrazilPayTranasaction(brazilPayTransaction);

        final Transaction createdBrazilPayTransaction = alternativePaymentClient
                .create(brazilPayTransaction, Transaction.API_ENDPOINT, Transaction.class);

        assertThat(createdBrazilPayTransaction.getId()).isEqualTo("trn_d12209838b");
        assertThat(createdBrazilPayTransaction.getMode()).isEqualTo("Live");
        assertThat(createdBrazilPayTransaction.getAmount()).isEqualTo(100);
        assertThat(createdBrazilPayTransaction.getCurrency()).isEqualTo("EUR");
        assertThat(createdBrazilPayTransaction.getIpAddress()).isEqualTo("89.216.124.9");
        assertThat(createdBrazilPayTransaction.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");
        assertThat(createdBrazilPayTransaction.getRedirectUrl())
                .isEqualTo("http://mybankingsite.com/hRedirection.aspx?transaction_id=trn_1a5f5e0c97");

        assertThat(createdBrazilPayTransaction.getCustomer().getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(createdBrazilPayTransaction.getCustomer().getMode()).isEqualTo("Live");
        assertThat(createdBrazilPayTransaction.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(createdBrazilPayTransaction.getCustomer().getLastName()).isEqualTo("Doe");
        assertThat(createdBrazilPayTransaction.getCustomer().getEmail()).isEqualTo("john@doe.com");
        assertThat(createdBrazilPayTransaction.getCustomer().getCountry()).isEqualTo("BR");
        assertThat(createdBrazilPayTransaction.getCustomer().getCreated().toString())
                .isEqualTo("2016-03-24T15:19:10.780Z");
        assertThat(createdBrazilPayTransaction.getCustomer().getAddress()).isEqualTo("Rua E");
        assertThat(createdBrazilPayTransaction.getCustomer().getAddress2()).isEqualTo("1040");
        assertThat(createdBrazilPayTransaction.getCustomer().getCity()).isEqualTo("Maracanaú");
        assertThat(createdBrazilPayTransaction.getCustomer().getZip()).isEqualTo("61919-230");
        assertThat(createdBrazilPayTransaction.getCustomer().getState()).isEqualTo("CE");
        assertThat(createdBrazilPayTransaction.getCustomer().getPhone()).isEqualTo("+55A7xxxxxxx");
        assertThat(createdBrazilPayTransaction.getCustomer().getBirthDate()).isEqualTo("12/04/1979");

        assertThat(createdBrazilPayTransaction.getPayment().getId()).isEqualTo("pay_ffd25121f84e4d249");
        assertThat(createdBrazilPayTransaction.getPayment().getMode()).isEqualTo("Live");
        assertThat(createdBrazilPayTransaction.getPayment().getHolder()).isEqualTo("John Doe");
        assertThat(createdBrazilPayTransaction.getPayment().getPaymentOption()).isEqualTo("BrazilPayBankTransfer");
        assertThat(createdBrazilPayTransaction.getPayment().getBankCode()).isEqualTo("hsbc");
        assertThat(createdBrazilPayTransaction.getPayment().getDocumentId()).isEqualTo("853.513.468-93");

        assertThat(createdBrazilPayTransaction.getRedirectUrls().getReturnUrl())
                .isEqualTo("http://plugins.alternativepayments.com/message/success.html");
        assertThat(createdBrazilPayTransaction.getRedirectUrls().getCancelUrl())
                .isEqualTo("http://plugins.alternativepayments.com/message/failure.html");
    }

    @Test
    public void test_create_credit_card_transaction() {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "US").build();
        Payment payment = new Payment.Builder("CreditCard", "John Doe").creditCardNumber("XXXXXXXXXXX1111")
                .creditCardType("visa").expirationMonth(12).expirationYear(2009).cvv2(222).build();

        Transaction creditCardTransaction = new Transaction.Builder(payment, null, 100, "EUR")
                .customer(customer).build();

        TransactionApiMock.expectCreditCardTranasaction(creditCardTransaction);

        final Transaction createdCreditCardTransaction = alternativePaymentClient
                .create(creditCardTransaction, Transaction.API_ENDPOINT, Transaction.class);

        assertThat(createdCreditCardTransaction.getId()).isEqualTo("trn_d12209838b");
        assertThat(createdCreditCardTransaction.getMode()).isEqualTo("Live");
        assertThat(createdCreditCardTransaction.getAmount()).isEqualTo(1500);
        assertThat(createdCreditCardTransaction.getCurrency()).isEqualTo("EUR");
        assertThat(createdCreditCardTransaction.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(createdCreditCardTransaction.getCustomer().getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(createdCreditCardTransaction.getCustomer().getMode()).isEqualTo("Live");
        assertThat(createdCreditCardTransaction.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(createdCreditCardTransaction.getCustomer().getLastName()).isEqualTo("Doe");
        assertThat(createdCreditCardTransaction.getCustomer().getEmail()).isEqualTo("john@doe.com");
        assertThat(createdCreditCardTransaction.getCustomer().getCountry()).isEqualTo("US");
        assertThat(createdCreditCardTransaction.getCustomer().getCreated().toString())
                .isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(createdCreditCardTransaction.getPayment().getId()).isEqualTo("pay_ffd25121f84e4d249");
        assertThat(createdCreditCardTransaction.getPayment().getMode()).isEqualTo("Live");
        assertThat(createdCreditCardTransaction.getPayment().getHolder()).isEqualTo("John Doe");
        assertThat(createdCreditCardTransaction.getPayment().getPaymentOption()).isEqualTo("CreditCard");
        assertThat(createdCreditCardTransaction.getPayment().getCreditCardNumber()).isEqualTo("XXXXXXXXXXX1111");
        assertThat(createdCreditCardTransaction.getPayment().getCreditCardType()).isEqualTo("visa");
        assertThat(createdCreditCardTransaction.getPayment().getExpirationMonth()).isEqualTo(12);
        assertThat(createdCreditCardTransaction.getPayment().getExpirationYear()).isEqualTo(2019);
    }

    @Test
    public void return_all_transactions() {
        TransactionApiMock.expectGetAll();

        final TransactionCollection transactions = alternativePaymentClient.getAll(Transaction.API_ENDPOINT,
                TransactionCollection.class);

        assertThat(transactions.getTransactions()).hasSize(2);
        assertThat(transactions.getPagination().getOffset()).isEqualTo(10);
        assertThat(transactions.getPagination().getLimit()).isEqualTo(3);
        assertThat(transactions.getPagination().getCount()).isEqualTo(2504);

        final Transaction firstTransaction = transactions.getTransactions().get(0);
        assertThat(firstTransaction.getId()).isEqualTo("trn_d12209838b");
        assertThat(firstTransaction.getMode()).isEqualTo("Live");
        assertThat(firstTransaction.getAmount()).isEqualTo(300);
        assertThat(firstTransaction.getCurrency()).isEqualTo("EUR");
        assertThat(firstTransaction.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(firstTransaction.getCustomer().getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(firstTransaction.getCustomer().getMode()).isEqualTo("Live");
        assertThat(firstTransaction.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(firstTransaction.getCustomer().getLastName()).isEqualTo("Doe");
        assertThat(firstTransaction.getCustomer().getEmail()).isEqualTo("john@doe.com");
        assertThat(firstTransaction.getCustomer().getCountry()).isEqualTo("US");
        assertThat(firstTransaction.getCustomer().getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(firstTransaction.getPayment().getId()).isEqualTo("pay_ffd25121f84e4d249");
        assertThat(firstTransaction.getPayment().getMode()).isEqualTo("Live");
        assertThat(firstTransaction.getPayment().getHolder()).isEqualTo("John Doe");
        assertThat(firstTransaction.getPayment().getPaymentOption()).isEqualTo("Giropay");
        assertThat(firstTransaction.getPayment().getBic()).isEqualTo("TESTDETT421");

        final Transaction secondTransaction = transactions.getTransactions().get(1);
        assertThat(secondTransaction.getId()).isEqualTo("trn_d12209838c");
        assertThat(secondTransaction.getMode()).isEqualTo("Live");
        assertThat(secondTransaction.getAmount()).isEqualTo(1500);
        assertThat(secondTransaction.getCurrency()).isEqualTo("EUR");
        assertThat(secondTransaction.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(secondTransaction.getCustomer().getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(secondTransaction.getCustomer().getMode()).isEqualTo("Live");
        assertThat(secondTransaction.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(secondTransaction.getCustomer().getLastName()).isEqualTo("Doe");
        assertThat(secondTransaction.getCustomer().getEmail()).isEqualTo("john@doe.com");
        assertThat(secondTransaction.getCustomer().getCountry()).isEqualTo("US");
        assertThat(secondTransaction.getCustomer().getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(secondTransaction.getPayment().getId()).isEqualTo("pay_ffd25121f84e4d249");
        assertThat(secondTransaction.getPayment().getMode()).isEqualTo("Live");
        assertThat(secondTransaction.getPayment().getHolder()).isEqualTo("John Doe");
        assertThat(secondTransaction.getPayment().getPaymentOption()).isEqualTo("CreditCard");
        assertThat(secondTransaction.getPayment().getCreditCardNumber()).isEqualTo("XXXXXXXXXXX1111");
        assertThat(secondTransaction.getPayment().getCreditCardType()).isEqualTo("visa");
        assertThat(secondTransaction.getPayment().getExpirationMonth()).isEqualTo(12);
        assertThat(secondTransaction.getPayment().getExpirationYear()).isEqualTo(2019);

    }

    @Test
    public void return_transactions_with_pagination() {
        TransactionApiMock.expectGetWithPagination();

        final TransactionCollection transactions = alternativePaymentClient.getAllWithPagination(3, 10,
                Transaction.API_ENDPOINT, TransactionCollection.class);

        assertThat(transactions.getTransactions()).hasSize(3);
        assertThat(transactions.getPagination().getOffset()).isEqualTo(10);
        assertThat(transactions.getPagination().getLimit()).isEqualTo(3);
        assertThat(transactions.getPagination().getCount()).isEqualTo(2504);

        final Transaction firstTransaction = transactions.getTransactions().get(0);
        assertThat(firstTransaction.getId()).isEqualTo("trn_d12209838b");
        assertThat(firstTransaction.getMode()).isEqualTo("Live");
        assertThat(firstTransaction.getAmount()).isEqualTo(300);
        assertThat(firstTransaction.getCurrency()).isEqualTo("EUR");
        assertThat(firstTransaction.getStatus()).isEqualTo("Funded");
        assertThat(firstTransaction.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(firstTransaction.getCustomer().getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(firstTransaction.getCustomer().getMode()).isEqualTo("Live");
        assertThat(firstTransaction.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(firstTransaction.getCustomer().getLastName()).isEqualTo("Doe");
        assertThat(firstTransaction.getCustomer().getEmail()).isEqualTo("john@doe.com");
        assertThat(firstTransaction.getCustomer().getCountry()).isEqualTo("US");
        assertThat(firstTransaction.getCustomer().getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(firstTransaction.getPayment().getId()).isEqualTo("pay_ffd25121f84e4d249");
        assertThat(firstTransaction.getPayment().getMode()).isEqualTo("Live");
        assertThat(firstTransaction.getPayment().getHolder()).isEqualTo("John Doe");
        assertThat(firstTransaction.getPayment().getPaymentOption()).isEqualTo("Giropay");
        assertThat(firstTransaction.getPayment().getBic()).isEqualTo("TESTDETT421");

        final Transaction secondTransaction = transactions.getTransactions().get(1);
        assertThat(secondTransaction.getId()).isEqualTo("trn_d12209838c");
        assertThat(secondTransaction.getMode()).isEqualTo("Live");
        assertThat(secondTransaction.getAmount()).isEqualTo(1500);
        assertThat(secondTransaction.getCurrency()).isEqualTo("EUR");
        assertThat(secondTransaction.getStatus()).isEqualTo("Funded");
        assertThat(secondTransaction.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(secondTransaction.getCustomer().getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(secondTransaction.getCustomer().getMode()).isEqualTo("Live");
        assertThat(secondTransaction.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(secondTransaction.getCustomer().getLastName()).isEqualTo("Doe");
        assertThat(secondTransaction.getCustomer().getEmail()).isEqualTo("john@doe.com");
        assertThat(secondTransaction.getCustomer().getCountry()).isEqualTo("US");
        assertThat(secondTransaction.getCustomer().getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(secondTransaction.getPayment().getId()).isEqualTo("pay_ffd25121f84e4d249");
        assertThat(secondTransaction.getPayment().getMode()).isEqualTo("Live");
        assertThat(secondTransaction.getPayment().getHolder()).isEqualTo("John Doe");
        assertThat(secondTransaction.getPayment().getPaymentOption()).isEqualTo("CreditCard");
        assertThat(secondTransaction.getPayment().getCreditCardNumber()).isEqualTo("XXXXXXXXXXX1111");
        assertThat(secondTransaction.getPayment().getCreditCardType()).isEqualTo("visa");
        assertThat(secondTransaction.getPayment().getExpirationMonth()).isEqualTo(12);
        assertThat(secondTransaction.getPayment().getExpirationYear()).isEqualTo(2019);

        final Transaction thirdTransaction = transactions.getTransactions().get(2);
        assertThat(thirdTransaction.getId()).isEqualTo("trn_d12209838dff");
        assertThat(thirdTransaction.getMode()).isEqualTo("Live");
        assertThat(thirdTransaction.getAmount()).isEqualTo(1500);
        assertThat(thirdTransaction.getCurrency()).isEqualTo("EUR");
        assertThat(thirdTransaction.getIpAddress()).isEqualTo("89.216.124.9");
        assertThat(thirdTransaction.getRedirectUrl())
                .isEqualTo("http://mybankingsite.com/hRedirection.aspx?transaction_id=trn_1a5f5e0c97");
        assertThat(thirdTransaction.getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(thirdTransaction.getCustomer().getId()).isEqualTo("cus_bd838e3611d34d598");
        assertThat(thirdTransaction.getCustomer().getMode()).isEqualTo("Live");
        assertThat(thirdTransaction.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(thirdTransaction.getCustomer().getLastName()).isEqualTo("Doe");
        assertThat(thirdTransaction.getCustomer().getEmail()).isEqualTo("john@doe.com");
        assertThat(thirdTransaction.getCustomer().getCountry()).isEqualTo("US");
        assertThat(thirdTransaction.getCustomer().getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(thirdTransaction.getPayment().getId()).isEqualTo("pay_ffd25121f84e4d249");
        assertThat(thirdTransaction.getPayment().getMode()).isEqualTo("Live");
        assertThat(thirdTransaction.getPayment().getHolder()).isEqualTo("John Doe");
        assertThat(thirdTransaction.getPayment().getPaymentOption()).isEqualTo("directpaymax");
        assertThat(thirdTransaction.getPayment().getBankCode()).isEqualTo("POSTBANK");
        assertThat(thirdTransaction.getPayment().getCreated().toString()).isEqualTo("2016-03-24T15:19:10.780Z");

        assertThat(thirdTransaction.getRedirectUrls().getReturnUrl())
                .isEqualTo("http://plugins.alternativepayments.com/message/success.html");
        assertThat(thirdTransaction.getRedirectUrls().getCancelUrl())
                .isEqualTo("http://plugins.alternativepayments.com/message/failure.html");
    }

}
