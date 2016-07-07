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
import com.alternativepayments.models.transaction.ReturnReason;
import com.alternativepayments.models.transaction.Transaction;
import com.alternativepayments.models.transaction.TransactionVoid;
import com.alternativepayments.models.transaction.TransactionVoidCollection;

/**
 * Controller for all action on transaction void.
 */
@Controller
public class TransactionVoidController {

    @Autowired
    private AlternativePaymentClient alternativePaymentClient;

    /**
     * Create transaction void page.
     *
     * @param model view model
     * @param transactionId id of transaction
     * @return view to create transaction void.
     */
    @RequestMapping("/create-transaction-void")
    public String createTransactionVoid(Model model,
            @RequestParam(value = "transaction_id", required = false) final String transactionId) {
        if (StringUtils.isNotBlank(transactionId)) {
            Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "DE").build();
            Payment payment = new Payment.Builder("SEPA", "John Doe").iban("BE88271080782541").build();
            Transaction sepaTransaction = new Transaction.Builder(payment, null, 500, "EUR").customer(customer).build();
            Transaction createdTransaction = alternativePaymentClient.create(sepaTransaction, Transaction.API_ENDPOINT,
                    Transaction.class);

            TransactionVoid transactionVoid = new TransactionVoid.Builder(ReturnReason.FRAUD,
                    createdTransaction.getId()).amount(50).currency("EUR").build();
            TransactionVoid createdTransactionVoid = alternativePaymentClient.create(transactionVoid,
                    TransactionVoid.getApiEndpoint(createdTransaction.getId()), TransactionVoid.class);

            model.addAttribute("transactionVoid", createdTransactionVoid);
        }
        return "void/create-transaction-void";
    }

    /**
     * Get transaction void.
     *
     * @param model view model
     * @param voidId id of requested Transaction void.
     * @param transactionId id of transaction.
     * @return view for get transaction void.
     */
    @RequestMapping("/get-transaction-void")
    public String getTransactionVoid(Model model,
            @RequestParam(value = "void_id", required = false) final String voidId,
            @RequestParam(value = "transaction_id", required = false) final String transactionId) {
        if (StringUtils.isNotBlank(transactionId)) {
            TransactionVoid transactionVoid = alternativePaymentClient.getById(voidId,
                    TransactionVoid.getApiEndpoint(transactionId), TransactionVoid.class);
            model.addAttribute("transactionVoid", transactionVoid);
        }

        return "void/get-transaction-void";
    }

    /**
     * Get all plans.
     *
     * @param model view model
     * @param transactionId id of transaction
     * @return view for get plans.
     */
    @RequestMapping("/get-transaction-voids")
    public String getPlans(Model model,
            @RequestParam(value = "transaction_id", required = false) final String transactionId) {
        if (StringUtils.isNotBlank(transactionId)) {
            TransactionVoidCollection transactionVoids = alternativePaymentClient
                    .getAll(TransactionVoid.getApiEndpoint(transactionId), TransactionVoidCollection.class);
            model.addAttribute("transactionVoids", transactionVoids.getVoidTransactions());
        }

        return "void/get-transaction-voids";
    }

}
