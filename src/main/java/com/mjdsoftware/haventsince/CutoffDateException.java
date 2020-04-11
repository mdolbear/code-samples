package com.mjdsoftware.haventsince;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class CutoffDateException extends IllegalArgumentException {

    @Getter @Setter(AccessLevel.PRIVATE)
    private HaventSinceBinarySearchResult searchResult;

    /**
     * Answer an instance of me on aResult and aMessage
     * @param aMessage String
     * @param aResult HaventSinceBinarySearchResult
     */
    public CutoffDateException(String aMessage,
                               HaventSinceBinarySearchResult aResult) {

        super(aMessage);
        this.setSearchResult(aResult);

    }

}
