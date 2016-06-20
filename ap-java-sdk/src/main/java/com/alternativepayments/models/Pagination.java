package com.alternativepayments.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base pagination class for meta data when working with collections.
 */
public class Pagination {

    private final int offset;
    private final int limit;
    private final int count;

    /**
     * Create pagination object.
     *
     * @param offset offset.
     * @param limit requested limit.
     * @param count total count.
     */
    @JsonCreator
    public Pagination(
            @JsonProperty("offset") int offset,
            @JsonProperty("limit") int limit,
            @JsonProperty("count") int count) {
        this.offset = offset;
        this.limit = limit;
        this.count = count;
    }

    /**
     * @return offset.
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @return limit.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @return count of all items.
     */
    public int getCount() {
        return count;
    }

}
