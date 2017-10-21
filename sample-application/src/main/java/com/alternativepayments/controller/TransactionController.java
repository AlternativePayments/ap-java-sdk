package com.alternativepayments.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alternativepayments.AlternativePaymentClient;
import com.alternativepayments.models.customer.Customer;
import com.alternativepayments.models.transaction.*;
import com.alternativepayments.models.website.PaymentOption;

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

        Transaction sepaTransaction = new Transaction.Builder(500, "EUR", "127.0.0.1").payment(payment)
                .customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(sepaTransaction, Transaction.API_ENDPOINT,
                Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Check if phone verification for SEPA is active.
     *
     * @param model view model
     * @return view to SMS verification check.
     */
    @RequestMapping("/is-sepa-phone-verification-active")
    public String isSepaPhoneVerificationActive(Model model) {
        final String paymentOptionType = "SEPA";

        PaymentOption paymentOption = alternativePaymentClient
                .getForWebsite(PaymentOption.getApiEndpoint(paymentOptionType), PaymentOption.class);

        model.addAttribute("paymentOption", paymentOption);
        return "transaction/sepa-phone-verification";
    }

    /**
     * Create phone verification for a number.
     *
     * @param model view model
     * @return view to created phone verification for a number.
     */
    @RequestMapping("/create-phone-verification")
    public String createPhoneVerification(Model model) {
        PhoneVerification phoneVerification = alternativePaymentClient.createPhoneVerification("+15555555555",
                PhoneVerification.API_ENDPOINT, PhoneVerification.class);

        model.addAttribute("phoneVerification", phoneVerification);
        return "transaction/create-phone-verification";
    }

    /**
     * Create transaction using hosted pages.
     *
     * @param model view model
     * @return view to create transaction using hosted pages.
     */
    @RequestMapping("/create-transaction-hosted-pages")
    public String createTransactionHostedPages(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "NL").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction hostedPagesTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").redirectUrls(redirectUrls)
                .customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(hostedPagesTransaction,
                Transaction.API_HOSTED_PAGES_ENDPOINT, Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "hosted-pages/create-transaction";
    }

    /**
     * Create sepa transaction with phone verification page.
     *
     * @param model view model
     * @return view to create sepa transaction.
     */
    @RequestMapping("/create-sepa-transaction-phone-verification")
    public String createSepaTransactionPhoneVerification(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("SEPA", "John Doe").iban("BE88271080782541").build();
        PhoneVerification phoneVerification = alternativePaymentClient.createPhoneVerification("+15555555555",
                PhoneVerification.API_ENDPOINT, PhoneVerification.class);
        PhoneVerification transactionPhoneVerification = new PhoneVerification.Builder(phoneVerification.getKey(),
                phoneVerification.getPhone()).token(phoneVerification.getToken()).pin(1234).build();

        Transaction sepaTransaction = new Transaction.Builder(500, "EUR", "127.0.0.1").payment(payment)
                .customer(customer).phoneVerification(transactionPhoneVerification).build();
        Transaction createdTransaction = alternativePaymentClient.create(sepaTransaction, Transaction.API_ENDPOINT,
                Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create alipay transaction page.
     *
     * @param model view model
     * @return view to create alipay transaction.
     */
    @RequestMapping("/create-alipay-transaction")
    public String createAlipayTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "CN").build();
        Payment payment = new Payment.Builder("alipay", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction alipayTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(alipayTransaction,
                Transaction.API_ENDPOINT, Transaction.class);
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

        Transaction mistercashTransaction = new Transaction.Builder(500, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(mistercashTransaction,
                Transaction.API_ENDPOINT, Transaction.class);
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

        Transaction brazilPayTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(brazilPayTransaction,
                Transaction.API_ENDPOINT, Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create brazilpay boleto bancario transaction page.
     *
     * @param model view model
     * @return view to create brazilpay boleto bancario transaction.
     */
    @RequestMapping("/create-brazilpay-boleto-transaction")
    public String createBrazilPayBoletoTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "BR").address("Av Max Teixeira")
                .address2("1040").city("Manaus").zip("69050-240").state("AM").phone("+5572222312")
                .birthDate("12/04/1979").build();
        Payment payment = new Payment.Builder("brazilpayboleto", "John Doe").documentId("853.513.468-93").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction brazilPayBoletoTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(brazilPayBoletoTransaction,
                Transaction.API_ENDPOINT, Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create brazilpay charge card transaction page.
     *
     * @param model view model
     * @return view to create brazilpay charge card transaction.
     */
    @RequestMapping("/create-brazilpay-chargecard-transaction")
    public String createBrazilChargeCardTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "BR").address("Av Max Teixeira")
                .address2("1040").city("Manaus").zip("69050-240").state("AM").phone("+5572222312")
                .birthDate("12/04/1979").build();
        Payment payment = new Payment.Builder("BrazilPayChargeCard", "John Doe").documentId("853.513.468-93")
                .bankCode("Banrisul").creditCardType("visa").creditCardNumber("4111111111111111").cvv2(123)
                .expirationMonth(12).expirationYear(2017).build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction brazilPayChargeCardTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(brazilPayChargeCardTransaction,
                Transaction.API_ENDPOINT, Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create CashU transaction page.
     *
     * @param model view model
     * @return view to create cashU transaction.
     */
    @RequestMapping("/create-cashu-transaction")
    public String createCachUTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "EG").build();
        Payment payment = new Payment.Builder("cashu", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction cashUTransaction = new Transaction.Builder(500, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(cashUTransaction,
                Transaction.API_ENDPOINT, Transaction.class);
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

        Transaction creditCardTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(creditCardTransaction,
                Transaction.API_ENDPOINT, Transaction.class);
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
        Payment payment = new Payment.Builder("DirectPayEU", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction directPayTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(directPayTransaction, Transaction.API_ENDPOINT,
                Transaction.class);

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
        Payment payment = new Payment.Builder("DirectPayMaxEU", "John Doe").bankCode("POSTBANK").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction directPayMaxTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(directPayMaxTransaction,
                Transaction.API_ENDPOINT, Transaction.class);

        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create EPS transaction page.
     *
     * @param model view model
     * @return view to create EPS transaction.
     */
    @RequestMapping("/create-eps-transaction")
    public String createEPSTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("eps", "John Doe").bic("TESTDETT421").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction epsTransaction = new Transaction.Builder(500, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(epsTransaction, Transaction.API_ENDPOINT,
                Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create Giropay transaction page.
     *
     * @param model view model
     * @return view to create Giropay transaction.
     */
    @RequestMapping("/create-giropay-transaction")
    public String createGiropayTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("Giropay", "John Doe").bic("TESTDETT421").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction giropayTransaction = new Transaction.Builder(500, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(giropayTransaction, Transaction.API_ENDPOINT,
                Transaction.class);
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

        Transaction idealTransaction = new Transaction.Builder(500, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(idealTransaction,
                Transaction.API_ENDPOINT, Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create mCoinz transaction page.
     *
     * @param model view model
     * @return view to create mCoinz transaction.
     */
    @RequestMapping("/create-mcoinz-transaction")
    public String createMCoinzTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "SA").build();
        Payment payment = new Payment.Builder("mcoinz", "John Doe").pinCode("CEEXXXXXXXXXC7").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");

        Transaction mCoinzTransaction = new Transaction.Builder(500, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();
        Transaction createdTransaction = alternativePaymentClient.create(mCoinzTransaction, Transaction.API_ENDPOINT,
                Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create paysafe transaction page.
     *
     * @param model view model
     * @return view to create teleingreso transaction.
     */
    @RequestMapping("/create-paysafe-transaction")
    public String createPaysafeTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("paysafe", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction paysafeTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(paysafeTransaction, Transaction.API_ENDPOINT,
                Transaction.class);
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
        Transaction poliTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(poliTransaction, Transaction.API_ENDPOINT,
                Transaction.class);

        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create Przelewy24 transaction page.
     *
     * @param model view model
     * @return view to create Przelewy24 transaction.
     */
    @RequestMapping("/create-przelewy24-transaction")
    public String createPrzelewy24Transaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "PL").build();
        Payment payment = new Payment.Builder("przelewy24", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction przelewy24Transaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(przelewy24Transaction,
                Transaction.API_ENDPOINT,
                Transaction.class);

        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create QIWI transaction page.
     *
     * @param model view model
     * @return view to create QIWI transaction.
     */
    @RequestMapping("/create-qiwi-transaction")
    public String createQiwiTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "RU").phone("+5572222312").build();
        Payment payment = new Payment.Builder("qiwi", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction qiwiTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(qiwiTransaction, Transaction.API_ENDPOINT,
                Transaction.class);

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
        Transaction safetyPayTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(safetyPayTransaction,
                Transaction.API_ENDPOINT, Transaction.class);

        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create sofort uberweisung transaction page.
     *
     * @param model view model
     * @return view to create teleingreso transaction.
     */
    @RequestMapping("/create-sofort-uberweisung-transaction")
    public String createSofortUberweisungTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
        Payment payment = new Payment.Builder("sofortuberweisung", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction sofortTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(sofortTransaction, Transaction.API_ENDPOINT,
                Transaction.class);
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
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "EE").build();
        Payment payment = new Payment.Builder("TrustPay", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction trustPayTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(trustPayTransaction,
                Transaction.API_ENDPOINT, Transaction.class);

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
        Transaction teleingresoTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(teleingresoTransaction,
                Transaction.API_ENDPOINT, Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create ten pay transaction page.
     *
     * @param model view model
     * @return view to create ten pay transaction.
     */
    @RequestMapping("/create-tenpay-transaction")
    public String createTenPayTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "CN").build();
        Payment payment = new Payment.Builder("TenPay", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction tenPayTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(tenPayTransaction, Transaction.API_ENDPOINT,
                Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create union pay transaction page.
     *
     * @param model view model
     * @return view to create union pay transaction.
     */
    @RequestMapping("/create-unionpay-transaction")
    public String createUnionPayTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "CN").build();
        Payment payment = new Payment.Builder("unionpay", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction unionPayTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(unionPayTransaction, Transaction.API_ENDPOINT,
                Transaction.class);
        model.addAttribute("transaction", createdTransaction);
        return "transaction/create-transaction";
    }

    /**
     * Create Verkkopankki transaction page.
     *
     * @param model view model
     * @return view to create Verkkopankki transaction.
     */
    @RequestMapping("/create-verkkopankki-transaction")
    public String createVerkkopankkiTransaction(Model model) {
        Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "FI").build();
        Payment payment = new Payment.Builder("verkkopankki", "John Doe").build();
        RedirectUrls redirectUrls = new RedirectUrls("http://plugins.alternativepayments.com/message/success.html",
                "http://plugins.alternativepayments.com/message/failure.html");
        Transaction verkkopankkiTransaction = new Transaction.Builder(100, "EUR", "127.0.0.1").payment(payment)
                .redirectUrls(redirectUrls).customer(customer).build();

        Transaction createdTransaction = alternativePaymentClient.create(verkkopankkiTransaction,
                Transaction.API_ENDPOINT,
                Transaction.class);
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
            Transaction transaction = alternativePaymentClient.getById(transactionId, Transaction.API_ENDPOINT,
                    Transaction.class);
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
        TransactionCollection transactions = alternativePaymentClient.getAll(Transaction.API_ENDPOINT,
                TransactionCollection.class);
        model.addAttribute("transactions", transactions.getTransactions());

        return "transaction/get-transactions";
    }

}
