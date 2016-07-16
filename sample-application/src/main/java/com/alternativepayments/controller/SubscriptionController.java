package com.alternativepayments.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alternativepayments.AlternativePaymentClient;
import com.alternativepayments.models.customer.Customer;
import com.alternativepayments.models.plan.Plan;
import com.alternativepayments.models.subscription.Subscription;
import com.alternativepayments.models.subscription.SubscriptionCollection;
import com.alternativepayments.models.transaction.Payment;
import com.alternativepayments.models.transaction.Transaction;

/**
 * Controller for all action on subscription.
 */
@Controller
public class SubscriptionController {

    @Autowired
    private AlternativePaymentClient alternativePaymentClient;

    /**
     * Create subscription page.
     *
     * @param model view model
     * @param planName name of the plan for subscription
     * @param planAmount amount of the plan for subscription
     * @param customerEmail email of the customer for witch we create subscription
     * @return view to create subscription.
     */
    @RequestMapping("/create-subscription")
    public String createSubscription(final Model model,
            @RequestParam(value = "plan_name", required = false) final String planName,
            @RequestParam(value = "plan_amount", required = false) final Integer planAmount,
            @RequestParam(value = "customer_email", required = false) final String customerEmail) {

        if (StringUtils.isNotBlank(planName) && StringUtils.isNotBlank(String.valueOf(planAmount))
                && StringUtils.isNotBlank(customerEmail)) {
            Plan plan = new Plan.Builder(planName, planAmount, "EUR", 5, Plan.Period.DAY)
                    .description("Abc").build();
            Plan createdPlan = alternativePaymentClient.create(plan, Plan.API_ENDPOINT, Plan.class);

            Customer customer = new Customer.Builder("John", "Smith", "johnsmith@johnsmith.com", "US").build();
            Payment payment = new Payment.Builder("SEPA", "John Doe").iban("BE88271080782541").build();
            Transaction transaction = new Transaction.Builder(payment, null, 500, "EUR").customer(customer).build();
            Transaction createdTransaction = alternativePaymentClient.create(transaction, Transaction.API_ENDPOINT,
                    Transaction.class);

            Subscription subscription = new Subscription.Builder(createdTransaction.getCustomer().getId(),
                    createdTransaction.getPayment().getId(), createdPlan.getId()).build();
            Subscription createdSubscription = alternativePaymentClient.create(subscription, Subscription.API_ENDPOINT,
                    Subscription.class);

            model.addAttribute("subscription", createdSubscription);
        }
        return "subscription/create-subscription";
    }

    /**
     * Get subscription.
     *
     * @param model view model
     * @param subscriptionId id of requested subscription.
     * @return view for get subscription.
     */
    @RequestMapping("/get-subscription")
    public String getSubscription(final Model model,
            @RequestParam(value = "subscription_id", required = false) final String subscriptionId) {
        if (StringUtils.isNotBlank(subscriptionId)) {
            Subscription subscription = alternativePaymentClient.getById(subscriptionId, Subscription.API_ENDPOINT,
                    Subscription.class);
            model.addAttribute("subscription", subscription);
        }

        return "subscription/get-subscription";
    }

    /**
     * Get all subscriptions.
     *
     * @param model view model
     * @return view for get subscriptions.
     */
    @RequestMapping("/get-subscriptions")
    public String getSubscriptions(final Model model) {
        SubscriptionCollection subscriptions = alternativePaymentClient.getAll(Subscription.API_ENDPOINT,
                SubscriptionCollection.class);
        model.addAttribute("subscriptions", subscriptions.getSubscriptions());

        return "subscription/get-subscriptions";
    }

}
