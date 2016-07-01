package com.alternativepayments.models.subscription;

import java.util.List;

import com.alternativepayments.models.BaseCollection;
import com.alternativepayments.models.Pagination;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * List of subscriptions.
 */
public class SubscriptionCollection extends BaseCollection {

    private final List<Subscription> subscriptions;

    /**
     * Subscription collection with pagination.
     *
     * @param subscriptions list of subscriptions.
     * @param pagination pagination meta object.
     */
    @JsonCreator
    public SubscriptionCollection(
            @JsonProperty("subscriptions") List<Subscription> subscriptions,
            @JsonProperty("pagination") Pagination pagination) {
        super(pagination);
        this.subscriptions = subscriptions;
    }

    /**
     * @return the subscriptions
     */
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }
}
