package com.alternativepayments.models;

import java.time.Instant;

/**
 * Base entity with common properties.
 */
public class BaseModel {

    private final String id;
    private final String mode;
    private final Instant created;
    private final Instant updated;

    /**
     * Create base model for each resource holding common properties.
     *
     * @param id id of resource.
     * @param mode mode which was used (test or live)
     * @param created when resource is created.
     * @param updated when resource was updated.
     */
    public BaseModel(String id, String mode, Instant created, Instant updated) {
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
    public Instant getCreated() {
        return created;
    }

    /**
     * @return when this resource was updated.
     */
    public Instant getUpdated() {
        return updated;
    }

}
