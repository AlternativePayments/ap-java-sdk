package com.alternativepayments.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base pagination class for meta data when working with collections.
 */
public class Pagination {

    /**
     * String for limit parameter for pagination.
     */
    public static final String LIMIT = "limit";

    /**
     * String for offset parameter for pagination.
     */
    public static final String OFFSET = "offset";

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
            @JsonProperty("offset") final int offset,
            @JsonProperty("limit") final int limit,
            @JsonProperty("count") final int count) {
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
