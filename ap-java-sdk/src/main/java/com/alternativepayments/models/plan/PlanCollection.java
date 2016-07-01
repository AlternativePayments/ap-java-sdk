package com.alternativepayments.models.plan;

import java.util.List;

import com.alternativepayments.models.BaseCollection;
import com.alternativepayments.models.Pagination;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * List of plans.
 */
public class PlanCollection extends BaseCollection {

    private final List<Plan> plans;

    /**
     * Plan collection with pagination.
     *
     * @param plans list of plans.
     * @param pagination pagination meta object.
     */
    @JsonCreator
    public PlanCollection(
            @JsonProperty("plans") List<Plan> plans,
            @JsonProperty("pagination") Pagination pagination) {
        super(pagination);
        this.plans = plans;
    }

    /**
     * @return the plans
     */
    public List<Plan> getPlans() {
        return plans;
    }
}
