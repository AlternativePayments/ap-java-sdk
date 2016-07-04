package com.alternativepayments.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.alternativepayments.apimocks.SubscriptionApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.models.plan.Plan;
import com.alternativepayments.models.subscription.Subscription;
import com.alternativepayments.models.subscription.SubscriptionCollection;

public class SubscriptionModelTest extends BaseApiResourceTest {

    @Test
    public void return_subscription_for_id() {
        final String id = "sbs_c475040";
        SubscriptionApiMock.expectGet(id);

        final Subscription subscription = alternativePaymentClient.getById(id, Subscription.API_ENDPOINT,
                Subscription.class);

        assertThat(subscription.getId()).isEqualTo("sbs_c475040");
        assertThat(subscription.getPlanId()).isEqualTo("pln_a27286a");
        assertThat(subscription.getPlan().getName()).isEqualTo("Test");
        assertThat(subscription.getPlan().getDescription()).isEqualTo("Abc");
        assertThat(subscription.getPlan().getAmount()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(subscription.getPlan().getCurrency()).isEqualTo("EUR");
        assertThat(subscription.getPlan().getPeriod()).isEqualTo(Plan.Period.DAY);
        assertThat(subscription.getPlan().getInterval()).isEqualTo(5);
        assertThat(subscription.getCustomerId()).isEqualTo("cus_70ac08b06b4949bfb");
        assertThat(subscription.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(subscription.getCustomer().getLastName()).isEqualTo("Smith");
        assertThat(subscription.getCustomer().getEmail()).isEqualTo("johnsmith@johnsmith.com");
        assertThat(subscription.getPaymentId()).isEqualTo("25275");
        assertThat(subscription.getStatus()).isEqualTo("InRecur");
        assertThat(subscription.getCreated().toString()).isEqualTo("2016-06-02T08:44:16.963Z");
    }

    @Test
    public void return_all_subscriptions() {
        SubscriptionApiMock.expectGetAll();

        final SubscriptionCollection subscriptions = alternativePaymentClient.getAll(Subscription.API_ENDPOINT,
                SubscriptionCollection.class);

        assertThat(subscriptions.getSubscriptions()).hasSize(2);
        assertThat(subscriptions.getPagination().getOffset()).isEqualTo(10);
        assertThat(subscriptions.getPagination().getLimit()).isEqualTo(3);
        assertThat(subscriptions.getPagination().getCount()).isEqualTo(2504);

        Subscription firstSubscription = subscriptions.getSubscriptions().get(0);
        assertThat(firstSubscription.getId()).isEqualTo("sbs_c475040");
        assertThat(firstSubscription.getPlanId()).isEqualTo("pln_a27286a");
        assertThat(firstSubscription.getPlan().getName()).isEqualTo("Test");
        assertThat(firstSubscription.getPlan().getDescription()).isEqualTo("Abc");
        assertThat(firstSubscription.getPlan().getAmount()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(firstSubscription.getPlan().getCurrency()).isEqualTo("EUR");
        assertThat(firstSubscription.getPlan().getPeriod()).isEqualTo(Plan.Period.DAY);
        assertThat(firstSubscription.getPlan().getInterval()).isEqualTo(5);
        assertThat(firstSubscription.getCustomerId()).isEqualTo("cus_70ac08b06b4949bfb");
        assertThat(firstSubscription.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(firstSubscription.getCustomer().getLastName()).isEqualTo("Smith");
        assertThat(firstSubscription.getCustomer().getEmail()).isEqualTo("johnsmith@johnsmith.com");
        assertThat(firstSubscription.getPaymentId()).isEqualTo("25275");
        assertThat(firstSubscription.getStatus()).isEqualTo("InRecur");
        assertThat(firstSubscription.getCreated().toString()).isEqualTo("2016-06-02T08:44:16.963Z");

        Subscription secondSubscription = subscriptions.getSubscriptions().get(1);
        assertThat(secondSubscription.getId()).isEqualTo("sbs_c475041");
        assertThat(secondSubscription.getPlanId()).isEqualTo("pln_a27286b");
        assertThat(secondSubscription.getPlan().getName()).isEqualTo("Test");
        assertThat(secondSubscription.getPlan().getDescription()).isEqualTo("Abc");
        assertThat(secondSubscription.getPlan().getAmount()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(secondSubscription.getPlan().getCurrency()).isEqualTo("EUR");
        assertThat(secondSubscription.getPlan().getPeriod()).isEqualTo(Plan.Period.DAY);
        assertThat(secondSubscription.getPlan().getInterval()).isEqualTo(5);
        assertThat(secondSubscription.getCustomerId()).isEqualTo("cus_70ac08b06b4949bfc");
        assertThat(secondSubscription.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(secondSubscription.getCustomer().getLastName()).isEqualTo("Smith");
        assertThat(secondSubscription.getCustomer().getEmail()).isEqualTo("johnsmith@johnsmith.com");
        assertThat(secondSubscription.getPaymentId()).isEqualTo("25276");
        assertThat(secondSubscription.getStatus()).isEqualTo("InRecur");
        assertThat(secondSubscription.getCreated().toString()).isEqualTo("2016-06-02T08:44:16.963Z");

    }

    @Test
    public void create_new_customer() {
        Subscription subscriptionToCreate = new Subscription.Builder("cus_70ac08b06b4949bfb", "25275", "pln_a27286a")
                .build();

        SubscriptionApiMock.expectPost(subscriptionToCreate);

        final Subscription subscription = alternativePaymentClient.create(subscriptionToCreate,
                Subscription.API_ENDPOINT, Subscription.class);

        assertThat(subscription.getId()).isEqualTo("sbs_c475040");
        assertThat(subscription.getPlanId()).isEqualTo("pln_a27286a");
        assertThat(subscription.getPlan().getName()).isEqualTo("Test");
        assertThat(subscription.getPlan().getDescription()).isEqualTo("Abc");
        assertThat(subscription.getPlan().getAmount()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(subscription.getPlan().getCurrency()).isEqualTo("EUR");
        assertThat(subscription.getPlan().getPeriod()).isEqualTo(Plan.Period.DAY);
        assertThat(subscription.getPlan().getInterval()).isEqualTo(5);
        assertThat(subscription.getCustomerId()).isEqualTo("cus_70ac08b06b4949bfb");
        assertThat(subscription.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(subscription.getCustomer().getLastName()).isEqualTo("Smith");
        assertThat(subscription.getCustomer().getEmail()).isEqualTo("johnsmith@johnsmith.com");
        assertThat(subscription.getPaymentId()).isEqualTo("25275");
        assertThat(subscription.getStatus()).isEqualTo("InRecur");
        assertThat(subscription.getCreated().toString()).isEqualTo("2016-06-02T08:44:16.963Z");
    }

    @Test
    public void return_subscription_with_pagination() {
        SubscriptionApiMock.expectGetWithPagination();

        final SubscriptionCollection subscriptions = alternativePaymentClient.getAllWithPagination(3, 10,
                Subscription.API_ENDPOINT, SubscriptionCollection.class);

        assertThat(subscriptions.getSubscriptions()).hasSize(3);
        assertThat(subscriptions.getPagination().getOffset()).isEqualTo(10);
        assertThat(subscriptions.getPagination().getLimit()).isEqualTo(3);
        assertThat(subscriptions.getPagination().getCount()).isEqualTo(2504);

        Subscription firstSubscription = subscriptions.getSubscriptions().get(0);
        assertThat(firstSubscription.getId()).isEqualTo("sbs_c475040");
        assertThat(firstSubscription.getPlanId()).isEqualTo("pln_a27286a");
        assertThat(firstSubscription.getPlan().getName()).isEqualTo("Test");
        assertThat(firstSubscription.getPlan().getDescription()).isEqualTo("Abc");
        assertThat(firstSubscription.getPlan().getAmount()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(firstSubscription.getPlan().getCurrency()).isEqualTo("EUR");
        assertThat(firstSubscription.getPlan().getPeriod()).isEqualTo(Plan.Period.DAY);
        assertThat(firstSubscription.getPlan().getInterval()).isEqualTo(5);
        assertThat(firstSubscription.getCustomerId()).isEqualTo("cus_70ac08b06b4949bfb");
        assertThat(firstSubscription.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(firstSubscription.getCustomer().getLastName()).isEqualTo("Smith");
        assertThat(firstSubscription.getCustomer().getEmail()).isEqualTo("johnsmith@johnsmith.com");
        assertThat(firstSubscription.getPaymentId()).isEqualTo("25275");
        assertThat(firstSubscription.getStatus()).isEqualTo("InRecur");
        assertThat(firstSubscription.getCreated().toString()).isEqualTo("2016-06-02T08:44:16.963Z");

        Subscription secondSubscription = subscriptions.getSubscriptions().get(1);
        assertThat(secondSubscription.getId()).isEqualTo("sbs_c475041");
        assertThat(secondSubscription.getPlanId()).isEqualTo("pln_a27286b");
        assertThat(secondSubscription.getPlan().getName()).isEqualTo("Test");
        assertThat(secondSubscription.getPlan().getDescription()).isEqualTo("Abc");
        assertThat(secondSubscription.getPlan().getAmount()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(secondSubscription.getPlan().getCurrency()).isEqualTo("EUR");
        assertThat(secondSubscription.getPlan().getPeriod()).isEqualTo(Plan.Period.DAY);
        assertThat(secondSubscription.getPlan().getInterval()).isEqualTo(5);
        assertThat(secondSubscription.getCustomerId()).isEqualTo("cus_70ac08b06b4949bfc");
        assertThat(secondSubscription.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(secondSubscription.getCustomer().getLastName()).isEqualTo("Smith");
        assertThat(secondSubscription.getCustomer().getEmail()).isEqualTo("johnsmith@johnsmith.com");
        assertThat(secondSubscription.getPaymentId()).isEqualTo("25276");
        assertThat(secondSubscription.getStatus()).isEqualTo("InRecur");
        assertThat(secondSubscription.getCreated().toString()).isEqualTo("2016-06-02T08:44:16.963Z");

        Subscription thirdSubscription = subscriptions.getSubscriptions().get(2);
        assertThat(thirdSubscription.getId()).isEqualTo("sbs_c475042");
        assertThat(thirdSubscription.getPlanId()).isEqualTo("pln_a27286c");
        assertThat(thirdSubscription.getPlan().getName()).isEqualTo("Test");
        assertThat(thirdSubscription.getPlan().getDescription()).isEqualTo("Abc");
        assertThat(thirdSubscription.getPlan().getAmount()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(thirdSubscription.getPlan().getCurrency()).isEqualTo("EUR");
        assertThat(thirdSubscription.getPlan().getPeriod()).isEqualTo(Plan.Period.DAY);
        assertThat(thirdSubscription.getPlan().getInterval()).isEqualTo(5);
        assertThat(thirdSubscription.getCustomerId()).isEqualTo("cus_70ac08b06b4949bfd");
        assertThat(thirdSubscription.getCustomer().getFirstName()).isEqualTo("John");
        assertThat(thirdSubscription.getCustomer().getLastName()).isEqualTo("Smith");
        assertThat(thirdSubscription.getCustomer().getEmail()).isEqualTo("johnsmith@johnsmith.com");
        assertThat(thirdSubscription.getPaymentId()).isEqualTo("25277");
        assertThat(thirdSubscription.getStatus()).isEqualTo("InRecur");
        assertThat(thirdSubscription.getCreated().toString()).isEqualTo("2016-06-02T08:44:16.963Z");
    }
}
