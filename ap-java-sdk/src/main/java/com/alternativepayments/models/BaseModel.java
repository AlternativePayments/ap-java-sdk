package com.alternativepayments.models;

import org.joda.time.DateTime;

/**
 * Base entity with common properties.
 */
public class BaseModel {

    private final String id;
    private final String mode;
    private final DateTime created;
    private final DateTime updated;

    /**
     * Create base model for each resource holding common as nulls.
     *
     */
    public BaseModel() {
        this.id = null;
        this.mode = null;
        this.created = null;
        this.updated = null;
    }

    /**
     * Create base model for each resource holding common properties.
     *
     * @param id id of resource.
     * @param mode mode which was used (test or live)
     * @param created when resource is created.
     * @param updated when resource was updated.
     */
    public BaseModel(final String id, final String mode, final DateTime created, final DateTime updated) {
        this.id = id;
        this.mode = mode;
        this.created = created;
        this.updated = updated;
    }

    /**
     * @return id of resource.
     */
    public String getId() {
        return id;
    }

    /**
     * @return mode used.
     */
    public String getMode() {
        return mode;
    }

    /**
     * @return when this resource was created.
     */
    public DateTime getCreated() {
        return created;
    }

    /**
     * @return when this resource was updated.
     */
    public DateTime getUpdated() {
        return updated;
    }

}
