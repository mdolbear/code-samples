package com.mjdsoftware.haventsince;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class HaventSinceBinarySearchResult {

    private HaventSinceElement element;
    private int startIndex;
    private int midpointIndex;
    private int endIndex;

    private ZonedDateTime midpointDate;


    /**
     * Answer whether or not element was found
     * @return boolean
     */
    public boolean wasElementFound() {

        return this.getElement() != null;

    }
}
