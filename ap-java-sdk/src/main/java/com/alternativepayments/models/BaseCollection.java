package com.alternativepayments.models;

/**
 * Base model for all collections.
 */
public class BaseCollection {

    private final Pagination pagination;

    /**
     * Create collection with pagination.
     *
     * @param pagination pagination meta object.
     */
    public BaseCollection(Pagination pagination) {
        this.pagination = pagination;
    }

    /**
     * @return the pagination
     */
    public Pagination getPagination() {
        return pagination;
    }

}
