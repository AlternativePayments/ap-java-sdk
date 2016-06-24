package com.alternativepayments.models.transaction;

import java.time.Instant;

import com.alternativepayments.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Redirect urls for transaction.
 */
public class RedirectUrls extends BaseModel {

    private final String returnUrl;
    private final String cancelUrl;

    /**
     * Create new Redirect URLs using all fields.
     *
     * @param id id for urls
     * @param mode mode used
     * @param created when is this Redirect urls created
     * @param updated when is this Redirect urls updated
     * @param returnUrl return url
     * @param cancelUrl cancel url
     */
    @JsonCreator
    public RedirectUrls(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final Instant created,
            @JsonProperty("updated") final Instant updated,
            @JsonProperty("returnUrl") final String returnUrl,
            @JsonProperty("cancelUrl") final String cancelUrl) {
        super(id, mode, created, updated);
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
    }

    /**
     * Create new Redirect URLs using all fields.
     *
     * @param returnUrl return url
     * @param cancelUrl cancel url
     */
    public RedirectUrls(final String returnUrl, final String cancelUrl) {
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
    }


    /**
     * @return url.
     */
    public String getReturnUrl() {
        return returnUrl;
    }

    /**
     * @return cancel url.
     */
    public String getCancelUrl() {
        return cancelUrl;
    }



}
