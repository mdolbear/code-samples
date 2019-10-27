package com.mjdsoftware.haventsince;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class HaventSinceAnalyzer {


    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
    private Date cutoff;

    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
    private List<HaventSinceElement> input;

    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
    private List<HaventSinceElement> afterCutoff;

    @Getter @Setter(AccessLevel.PRIVATE)
    private List<HaventSinceElement> result;

    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
    private String dateFormat;

    //Constants
    public static final String YEAR_MN_DY_FORMAT = "yyyy-MM-dd";
    public static final String YEAR_MN_DY_HR_MM_SEC_FORMAT = "yyyy-MM-dd, HH:mm:ss";


    /**
     * Answer an instance of me for the following arguments:
     * @param aCutoff Date
     * @param aDateFormat String
     * @param anInputList List
     */
    public HaventSinceAnalyzer(Date aCutoff,
                               String aDateFormat,
                               List<HaventSinceElement> anInputList) {

        super();
        this.setCutoff(aCutoff);
        this.setDateFormat(aDateFormat);
        this.setInput(anInputList);

    }

    /**
     * Produce result
     * @return List
     */
    public List<HaventSinceElement> produceResult() {

        HaventSinceElement          tempCutoffElement;
        int                         tempCutoffIndex;
        List<HaventSinceElement>    tempInputCopyList;

        //Make copy of input
        tempInputCopyList = new ArrayList<HaventSinceElement>(this.getInput());

        //Sort input copy and find cutoff point
        tempInputCopyList.sort(this.getHaventSinceElementDateComparator());
        tempCutoffElement = this.getCutoffElement(tempInputCopyList);

        //Get elements from cutoff point to end, and remove "all" of those elements
        //This is the haven't since list
        tempCutoffIndex = this.getInput().indexOf(tempCutoffElement);
        this.setAfterCutoff(new ArrayList<HaventSinceElement>(tempInputCopyList.subList(tempCutoffIndex,
                                                              tempInputCopyList.size())));
        this.removalAll(this.getAfterCutoff(), tempInputCopyList);
        this.setResult(tempInputCopyList);


        return tempInputCopyList;

    }

    /**
     * Remove all elements of aRemovalList from anOriginal since java stops at the initial element
     * @param aRemovalList List
     * @param anOriginal List
     */
    private void removalAll(List<HaventSinceElement> aRemovalList,
                            List<HaventSinceElement> anOriginal) {

        boolean                      tempExists;
        Optional<HaventSinceElement> tempFound;

        for (HaventSinceElement anElement: aRemovalList) {

           tempExists = true;
           while (tempExists) {

               tempFound =
                       anOriginal.stream()
                                 .filter(el->el.identifiersEqual(anElement))
                                 .findFirst();
               tempExists = tempFound.isPresent();
               if (tempExists) {

                   tempExists = anOriginal.remove(tempFound.get());
               }

           }

        }

    }

    /**
     * Answer the cutoff element in an input
     * @param anInputList List
     * @return HaventSinceElement
     */
    private HaventSinceElement getCutoffElement(List<HaventSinceElement> anInputList) {

        HaventSinceBinarySearch tempSearch;
        HaventSinceElement      tempResult;

        tempSearch = new HaventSinceBinarySearch(anInputList);
        tempResult = tempSearch.binarySearch(this.getCutoff(),
                                             this.getDateFormat());

        this.validateCutoffElementReturned(tempResult);

        return tempResult;

    }

    /**
     * Validate that a cutoff elemetn was found
     * @param aResult HaventSinceElement
     */
    private void validateCutoffElementReturned(HaventSinceElement aResult) {

        if (aResult == null) {

            throw new IllegalArgumentException("No cutoff element found for the specified date: "
                                                + this.getCutoff().toString());

        }

    }

    /**
     * Answer my date comparato
     * @return Comparator
     */
    private Comparator<HaventSinceElement> getHaventSinceElementDateComparator() {

        return (c1, c2) ->
                c1.getDate(this.getDateFormat()).compareTo(c2.getDate(this.getDateFormat()));

    }

}
