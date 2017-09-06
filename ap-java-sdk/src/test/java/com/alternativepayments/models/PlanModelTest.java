package com.alternativepayments.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.alternativepayments.apimocks.PlanApiMock;
import com.alternativepayments.base.BaseApiResourceTest;
import com.alternativepayments.models.plan.Plan;
import com.alternativepayments.models.plan.PlanCollection;

public class PlanModelTest extends BaseApiResourceTest {

    @Test
    public void return_plan_for_id() {
        final String id = "pln_a27286a";
        PlanApiMock.expectGet(id);

        final Plan plan = alternativePaymentClient.getById(id, Plan.API_ENDPOINT, Plan.class);

        assertThat(plan.getId()).isEqualTo("pln_a27286a");
        assertThat(plan.getName()).isEqualTo("Test");
        assertThat(plan.getDescription()).isEqualTo("Test plan");
        assertThat(plan.getAmount()).isEqualTo(1000);
        assertThat(plan.getCurrency()).isEqualTo("EUR");
        assertThat(plan.getIntervalUnit()).isEqualTo(Plan.Period.MONTH);
        assertThat(plan.getIntervalCount()).isEqualTo(1);
        assertThat(plan.getBillingCycles()).isEqualTo(12);
        assertThat(plan.getIsConversionRateFixed()).isTrue();
        assertThat(plan.getIpAddress()).isEqualTo("91.218.229.20");
        assertThat(plan.getTrialPeriod()).isEqualTo(7);
        assertThat(plan.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
    }

    @Test
    public void return_all_plans() {
        PlanApiMock.expectGetAll();

        final PlanCollection plans = alternativePaymentClient.getAll(Plan.API_ENDPOINT, PlanCollection.class);

        assertThat(plans.getPlans()).hasSize(2);
        assertThat(plans.getPagination().getOffset()).isEqualTo(10);
        assertThat(plans.getPagination().getLimit()).isEqualTo(3);
        assertThat(plans.getPagination().getCount()).isEqualTo(2504);

        final Plan firstPlan = plans.getPlans().get(0);
        assertThat(firstPlan.getId()).isEqualTo("pln_a27286a");
        assertThat(firstPlan.getName()).isEqualTo("Test");
        assertThat(firstPlan.getDescription()).isEqualTo("Test plan");
        assertThat(firstPlan.getAmount()).isEqualTo(1000);
        assertThat(firstPlan.getCurrency()).isEqualTo("EUR");
        assertThat(firstPlan.getIntervalUnit()).isEqualTo(Plan.Period.MONTH);
        assertThat(firstPlan.getIntervalCount()).isEqualTo(1);
        assertThat(firstPlan.getBillingCycles()).isEqualTo(12);
        assertThat(firstPlan.getIsConversionRateFixed()).isTrue();
        assertThat(firstPlan.getIpAddress()).isEqualTo("91.218.229.20");
        assertThat(firstPlan.getTrialPeriod()).isEqualTo(7);
        assertThat(firstPlan.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");

        final Plan secondPlan = plans.getPlans().get(1);
        assertThat(secondPlan.getId()).isEqualTo("pln_a27286b");
        assertThat(secondPlan.getName()).isEqualTo("Test2");
        assertThat(secondPlan.getDescription()).isEqualTo("Test2 plan");
        assertThat(secondPlan.getAmount()).isEqualTo(1000);
        assertThat(secondPlan.getCurrency()).isEqualTo("EUR");
        assertThat(secondPlan.getIntervalUnit()).isEqualTo(Plan.Period.MONTH);
        assertThat(secondPlan.getIntervalCount()).isEqualTo(1);
        assertThat(secondPlan.getBillingCycles()).isEqualTo(12);
        assertThat(secondPlan.getIsConversionRateFixed()).isTrue();
        assertThat(secondPlan.getIpAddress()).isEqualTo("91.218.229.20");
        assertThat(secondPlan.getTrialPeriod()).isEqualTo(7);
        assertThat(secondPlan.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");

    }

    @Test
    public void create_new_plan() {
        Plan plan = new Plan.Builder("Test", 1000, "EUR", Plan.Period.MONTH, 1, 12, "91.218.229.20")
                .description("Test plan").build();

        PlanApiMock.expectPost(plan);

        final Plan createdPlan = alternativePaymentClient.create(plan, Plan.API_ENDPOINT, Plan.class);

        assertThat(createdPlan.getId()).isEqualTo("pln_a27286a");
        assertThat(createdPlan.getName()).isEqualTo("Test");
        assertThat(createdPlan.getDescription()).isEqualTo("Test plan");
        assertThat(createdPlan.getAmount()).isEqualTo(1000);
        assertThat(createdPlan.getCurrency()).isEqualTo("EUR");
        assertThat(createdPlan.getIntervalUnit()).isEqualTo(Plan.Period.MONTH);
        assertThat(createdPlan.getIntervalCount()).isEqualTo(1);
        assertThat(createdPlan.getBillingCycles()).isEqualTo(12);
        assertThat(createdPlan.getIpAddress()).isEqualTo("91.218.229.20");
        assertThat(createdPlan.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
    }

    @Test
    public void return_plans_with_pagination() {
        PlanApiMock.expectGetWithPagination();

        final PlanCollection plans = alternativePaymentClient.getAllWithPagination(3, 10, Plan.API_ENDPOINT,
                PlanCollection.class);

        assertThat(plans.getPlans()).hasSize(3);
        assertThat(plans.getPagination().getOffset()).isEqualTo(10);
        assertThat(plans.getPagination().getLimit()).isEqualTo(3);
        assertThat(plans.getPagination().getCount()).isEqualTo(2504);

        final Plan firstPlan = plans.getPlans().get(0);
        assertThat(firstPlan.getId()).isEqualTo("pln_a27286a");
        assertThat(firstPlan.getName()).isEqualTo("Test");
        assertThat(firstPlan.getDescription()).isEqualTo("Test plan");
        assertThat(firstPlan.getAmount()).isEqualTo(1000);
        assertThat(firstPlan.getCurrency()).isEqualTo("EUR");
        assertThat(firstPlan.getIntervalUnit()).isEqualTo(Plan.Period.MONTH);
        assertThat(firstPlan.getIntervalCount()).isEqualTo(1);
        assertThat(firstPlan.getBillingCycles()).isEqualTo(12);
        assertThat(firstPlan.getIsConversionRateFixed()).isTrue();
        assertThat(firstPlan.getIpAddress()).isEqualTo("91.218.229.20");
        assertThat(firstPlan.getTrialPeriod()).isEqualTo(7);
        assertThat(firstPlan.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");

        final Plan secondPlan = plans.getPlans().get(1);
        assertThat(secondPlan.getId()).isEqualTo("pln_a27286b");
        assertThat(secondPlan.getId()).isEqualTo("pln_a27286b");
        assertThat(secondPlan.getName()).isEqualTo("Test2");
        assertThat(secondPlan.getDescription()).isEqualTo("Test2 plan");
        assertThat(secondPlan.getAmount()).isEqualTo(1000);
        assertThat(secondPlan.getCurrency()).isEqualTo("EUR");
        assertThat(secondPlan.getIntervalUnit()).isEqualTo(Plan.Period.MONTH);
        assertThat(secondPlan.getIntervalCount()).isEqualTo(1);
        assertThat(secondPlan.getBillingCycles()).isEqualTo(12);
        assertThat(secondPlan.getIsConversionRateFixed()).isTrue();
        assertThat(secondPlan.getIpAddress()).isEqualTo("91.218.229.20");
        assertThat(secondPlan.getTrialPeriod()).isEqualTo(7);
        assertThat(secondPlan.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");

        final Plan thirdPlan = plans.getPlans().get(2);
        assertThat(thirdPlan.getId()).isEqualTo("pln_a27286c");
        assertThat(thirdPlan.getName()).isEqualTo("Test3");
        assertThat(thirdPlan.getDescription()).isEqualTo("Test3 plan");
        assertThat(thirdPlan.getAmount()).isEqualTo(1000);
        assertThat(thirdPlan.getCurrency()).isEqualTo("EUR");
        assertThat(thirdPlan.getIntervalUnit()).isEqualTo(Plan.Period.MONTH);
        assertThat(thirdPlan.getIntervalCount()).isEqualTo(1);
        assertThat(thirdPlan.getBillingCycles()).isEqualTo(12);
        assertThat(thirdPlan.getIsConversionRateFixed()).isTrue();
        assertThat(thirdPlan.getIpAddress()).isEqualTo("91.218.229.20");
        assertThat(thirdPlan.getTrialPeriod()).isEqualTo(7);
        assertThat(thirdPlan.getCreated().toString()).isEqualTo("2012-04-23T18:25:43.511Z");
    }
}
