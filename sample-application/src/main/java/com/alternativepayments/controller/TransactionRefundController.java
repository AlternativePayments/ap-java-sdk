package com.alternativepayments.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alternativepayments.AlternativePaymentClient;
import com.alternativepayments.models.transaction.ReturnReason;
import com.alternativepayments.models.transaction.TransactionRefund;
import com.alternativepayments.models.transaction.TransactionRefundCollection;

/**
 * Controller for all action on transaction refund.
 */
@Controller
public class TransactionRefundController {

    @Autowired
    private AlternativePaymentClient alternativePaymentClient;

    /**
     * Create transaction refund page.
     *
     * @param model view model
     * @param transactionId id of transaction
     * @return view to create transaction refund.
     */
    @RequestMapping("/create-transaction-refund")
    public String createTransactionRefund(Model model,
            @RequestParam(value = "transaction_id", required = false) final String transactionId) {
        if (StringUtils.isNotBlank(transactionId)) {
            TransactionRefund transactionRefund = new TransactionRefund.Builder(ReturnReason.FRAUD,
                    transactionId).build();
            TransactionRefund createdTransactionRefund = alternativePaymentClient.create(transactionRefund,
                    TransactionRefund.getApiEndpoint(transactionId), TransactionRefund.class);

            model.addAttribute("transactionRefund", createdTransactionRefund);
        }

        return "refund/create-transaction-refund";
    }

    /**
     * Get transaction refund.
     *
     * @param model view model
     * @param refundId id of requested Transaction refund.
     * @param transactionId id of transaction.
     * @return view for get transaction refund.
     */
    @RequestMapping("/get-transaction-refund")
    public String getTransactionRefund(Model model,
            @RequestParam(value = "refund_id", required = false) final String refundId,
            @RequestParam(value = "transaction_id", required = false) final String transactionId) {
        if (StringUtils.isNotBlank(transactionId)) {
            TransactionRefund transactionRefund = alternativePaymentClient.getById(refundId,
                    TransactionRefund.getApiEndpoint(transactionId), TransactionRefund.class);
            model.addAttribute("transactionRefund", transactionRefund);
        }

        return "refund/get-transaction-refund";
    }

    /**
     * Get all plans.
     *
     * @param model view model
     * @param transactionId id of transaction
     * @return view for get plans.
     */
    @RequestMapping("/get-transaction-refunds")
    public String getPlans(Model model,
            @RequestParam(value = "transaction_id", required = false) final String transactionId) {
        if (StringUtils.isNotBlank(transactionId)) {
            TransactionRefundCollection transactionRefunds = alternativePaymentClient
                    .getAll(TransactionRefund.getApiEndpoint(transactionId), TransactionRefundCollection.class);
            model.addAttribute("transactionRefunds", transactionRefunds.getRefundTransactions());
        }

        return "refund/get-transaction-refunds";
    }

}
