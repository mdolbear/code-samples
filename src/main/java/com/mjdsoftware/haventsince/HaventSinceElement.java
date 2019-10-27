package com.mjdsoftware.haventsince;

import java.util.Date;
import java.util.List;

public interface HaventSinceElement {

    /**
     * Answer my date
     * @param aDateFormat String
     * @return Date
     */
    public Date getDate(String aDateFormat);

    /**
     * Answer my characteristics
     * @return List
     */
    public List<String> getCharacteristics();


    /**
     * Answer my identifier
     * @return String
     */
    public String getIdentifier();

    /**
     * Answer whether identifiers are equal
     * @param anotherElement HaventSinceElement
     * @return boolean
     */
    default public boolean identifiersEqual(HaventSinceElement anotherElement) {

        boolean tempResult = false;

        if (anotherElement != null &&
                anotherElement.getIdentifier() != null &&
                this.getIdentifier() != null) {

            tempResult = this.getIdentifier().equals(anotherElement.getIdentifier());
        }

        return tempResult;

    }


}
