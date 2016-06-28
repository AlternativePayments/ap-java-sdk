package com.alternativepayments.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alternativepayments.AlternativePaymentClient;
import com.alternativepayments.models.customer.Customer;
import com.alternativepayments.models.transaction.Payment;
import com.alternativepayments.models.transaction.RedirectUrls;
import com.alternativepayments.models.transaction.Transaction;
import com.alternativepayments.models.transaction.TransactionCollection;

/**
 * Controller for all action for transactions.
 */
@Controller
public class TransactionController {

    @Autowired
    private AlternativePaymentClient alternativePaymentClient;

    /**
     * Create sepa transaction page.
     *
     * @param model view model
     * @return view to create sepa transaction.
     */
    @RequestMapping("/create-sepa-transaction")
    public String createSepaTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("SEPA", "John Doe").iban("BE88271080782541").build();

        Transaction sepaTransaction = new Transaction.Builder(payment, null, 500, "EUR").customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.createTransaction(sepaTransaction);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create mistercach transaction page.
     *
     * @param model view model
     * @return view to create mistercach transaction.
     */
    @RequestMapping("/create-mistercach-transaction")
    public String createMisterCachTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "BE").build();
        Payment payment = new Payment.Builder("mistercash", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction mistercashTransaction = new Transaction.Builder(payment, null, 500, "EUR")
                .redirectUrls(redirectUrls).customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.createTransaction(mistercashTransaction);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create IDEAL transaction page.
     *
     * @param model view model
     * @return view to create ideal transaction.
     */
    @RequestMapping("/create-ideal-transaction")
    public String createIDealTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "NL").build();
        Payment payment = new Payment.Builder("ideal", "John Doe").bankCode("ABN_AMRO").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction idealTransaction = new Transaction.Builder(payment, null, 500, "EUR").redirectUrls(redirectUrls)
                .customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.createTransaction(idealTransaction);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create brazilpay transaction page.
     *
     * @param model view model
     * @return view to create brazilpay transaction.
     */
    @RequestMapping("/create-brazilpay-transaction")
    public String createBrazilPayTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "BR").address("Rua E").address2("1040")
                .city("Maracanaú").zip("61919-230").state("CE").phone("+5572222312").birthDate("12/04/1979").build();
        Payment payment = new Payment.Builder("BrazilPayBankTransfer", "John Doe").bankCode("hsbc")
                .documentId("853.513.468-93").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction brazilPayTransaction = new Transaction.Builder(payment, null, 100, "EUR").redirectUrls(redirectUrls)
                .customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.createTransaction(brazilPayTransaction);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create credit card transaction page.
     *
     * @param model view model
     * @return view to create credit card transaction.
     */
    @RequestMapping("/create-creditcard-transaction")
    public String createCreditCardTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("CreditCard", "John Doe").creditCardNumber("4111111111111111")
                .creditCardType("visa").expirationMonth(12).expirationYear(2019).cvv2(222).build();

        Transaction creditCardTransaction = new Transaction.Builder(payment, null, 100, "EUR").customer(customer)
                .build();

        Transaction createdTransaction = alternativePaymentClient.createTransaction(creditCardTransaction);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create teleingreso transaction page.
     *
     * @param model view model
     * @return view to create teleingreso transaction.
     */
    @RequestMapping("/create-teleingreso-transaction")
    public String createTeleingresoTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "ES").build();
        Payment payment = new Payment.Builder("Teleingreso", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction teleingresoTransaction = new Transaction.Builder(payment, null, 100, "EUR")
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.createTransaction(teleingresoTransaction);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create safetypay transaction page.
     *
     * @param model view model
     * @return view to create safetypay transaction.
     */
    @RequestMapping("/create-safetypay-transaction")
    public String createSafetypayTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "ES").build();
        Payment payment = new Payment.Builder("Teleingreso", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction safetyPayTransaction = new Transaction.Builder(payment, null, 100, "EUR").redirectUrls(redirectUrls)
                .customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.createTransaction(safetyPayTransaction);

        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create POLi transaction page.
     *
     * @param model view model
     * @return view to create POLi transaction.
     */
    @RequestMapping("/create-poli-transaction")
    public String createPoliTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "AU").build();
        Payment payment = new Payment.Builder("POLi", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction poliTransaction = new Transaction.Builder(payment, null, 100, "EUR").redirectUrls(redirectUrls)
                .customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.createTransaction(poliTransaction);

        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create TrustPay transaction page.
     *
     * @param model view model
     * @return view to create TrustPay transaction.
     */
    @RequestMapping("/create-trustpay-transaction")
    public String createTrustPayTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "BA").build();
        Payment payment = new Payment.Builder("TrustPay", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction trustPayTransaction = new Transaction.Builder(payment, null, 100, "EUR").redirectUrls(redirectUrls)
                .customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.createTransaction(trustPayTransaction);

        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create DirectPay transaction page.
     *
     * @param model view model
     * @return view to create DirectPay transaction.
     */
    @RequestMapping("/create-directpay-transaction")
    public String createDirectPayTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("directpay", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction directPayTransaction = new Transaction.Builder(payment, null, 100, "EUR").redirectUrls(redirectUrls)
                .customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.createTransaction(directPayTransaction);

        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create DirectPay Max transaction page.
     *
     * @param model view model
     * @return view to create DirectPay Max transaction.
     */
    @RequestMapping("/create-directpay-max-transaction")
    public String createDirectPayMaxTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("directpaymax", "John Doe").bankCode("POSTBANK").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction directPayMaxTransaction = new Transaction.Builder(payment, null, 100, "EUR")
                .redirectUrls(redirectUrls)
                .customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.createTransaction(directPayMaxTransaction);

        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Get transaction.
     *
     * @param model view model
     * @param transactionId id of requested transaction.
     * @return view for get transaction.
     */
    @RequestMapping("/get-transaction")
    public String getTransaction(Model model,
            @RequestParam(value = "transaction_id", required = false) final String transactionId) {
        if (StringUtils.isNotBlank(transactionId)) {
            Transaction transaction = alternativePaymentClient.getTransaction(transactionId);
            model.addAttribute("transaction", transaction);
        }

        return "transaction/get-transaction";
    }

    /**
     * Get all transactions.
     *
     * @param model view model
     * @return view for get transactions.
     */
    @RequestMapping("/get-transactions")
    public String getTransactions(Model model) {
        TransactionCollection transactions = alternativePaymentClient.getAllTransactions();
        model.addAttribute("transactions", transactions.getTransactions());

        return "transaction/get-transactions";
    }

}
