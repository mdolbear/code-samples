package com.mjdsoftware.haventsince;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class HaventSinceBinarySearch {


    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
    private List<HaventSinceElement> inputList;


    /**
     * Answer an instance of me for anInputList
     * @param anInputList List
     */
    public HaventSinceBinarySearch(List<HaventSinceElement> anInputList) {

        super();
        this.setInputList(anInputList);

    }

    /**
     * Binary search. Answer the HaventSinceElement that
     * contains aValue or null
     * @param  aValue Date
     * @param aDateFormat String
     * @return HaventSinceElement
     */
    public HaventSinceBinarySearchResult binarySearch(Date aValue,
                                                      String aDateFormat) {

        HaventSinceElement              tempResult = null;
        int                             tempStartIndex;
        int                             tempEndIndex;
        int                             tempMidpointIndex = 0;
        Date                            tempMidpointValue = null;

        //Initialize
        tempStartIndex = 0;
        tempEndIndex = this.getSize();

        while (this.shouldContinueBinarySearch(tempResult, tempStartIndex, tempEndIndex)) {

            tempMidpointIndex = (tempStartIndex + tempEndIndex) >>> 1;
            tempMidpointValue = this.getInputList().get(tempMidpointIndex).getDate(aDateFormat);

            if (tempMidpointValue.equals(aValue)) {

                tempResult = this.getInputList().get(tempMidpointIndex);
            }
            else if (aValue.after(tempMidpointValue)){

                tempStartIndex = tempMidpointIndex+1;
            }
            else if (aValue.before(tempMidpointValue)) {

                tempEndIndex = tempMidpointIndex-1;
            }



        }

        return
            new HaventSinceBinarySearchResult(tempResult,
                                              tempStartIndex,
                                              tempMidpointIndex,
                                              tempEndIndex,
                                              tempMidpointValue);


    }


    /**
     * Determine if I should continue binary search
     * @param aResult HaventSinceElement
     * @param aStart int
     * @param aEnd int
     * @return boolean
     */
    protected boolean shouldContinueBinarySearch(HaventSinceElement aResult,
                                                 int aStart,
                                                 int aEnd) {

        return aStart <= aEnd &&
                aStart < this.getSize() &&
                aEnd <= this.getSize() &&
                aResult == null;

    }

    /**
     * Answer my size
     * @return int
     */
    public int getSize() {

        return this.getInputList().size();

    }


}
