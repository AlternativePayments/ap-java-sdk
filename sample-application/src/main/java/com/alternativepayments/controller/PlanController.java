package com.alternativepayments.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alternativepayments.AlternativePaymentClient;
import com.alternativepayments.models.plan.Plan;
import com.alternativepayments.models.plan.PlanCollection;

/**
 * Controller for all action on plan.
 */
@Controller
public class PlanController {

    @Autowired
    private AlternativePaymentClient alternativePaymentClient;

    /**
     * Create plan page.
     *
     * @param model view model
     * @return view to create plan.
     */
    @RequestMapping("/create-plan")
    public String createPlan(Model model) {
        Plan plan = new Plan.Builder("Test", 1000, "EUR", 5, Plan.Period.DAY).description("Test plan").build();

        Plan createdPlan = alternativePaymentClient.create(plan, Plan.API_ENDPOINT, Plan.class);
        model.addAttribute("plan", createdPlan);
        return "plan/create-plan";
    }

    /**
     * Get plan.
     *
     * @param model view model
     * @param planId id of requested plan.
     * @return view for get plan.
     */
    @RequestMapping("/get-plan")
    public String getPlan(Model model,
            @RequestParam(value = "plan_id", required = false) final String planId) {
        if (StringUtils.isNotBlank(planId)) {
            Plan plan = alternativePaymentClient.getById(planId, Plan.API_ENDPOINT, Plan.class);
            model.addAttribute("plan", plan);
        }

        return "plan/get-plan";
    }

    /**
     * Get all plans.
     *
     * @param model view model
     * @return view for get plans.
     */
    @RequestMapping("/get-plans")
    public String getPlans(Model model) {
        PlanCollection plans = alternativePaymentClient.getAll(Plan.API_ENDPOINT, PlanCollection.class);
        model.addAttribute("plans", plans.getPlans());

        return "plan/get-plans";
    }

}
