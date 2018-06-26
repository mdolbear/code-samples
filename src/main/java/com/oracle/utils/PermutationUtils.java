package com.oracle.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 *
 */
public class PermutationUtils {

    /**
     * Answer an instance for the following arguments
     */
    public PermutationUtils() {
       super();
    }


    
    /**
     * Find permutations in aSource of aSubstring
     * @param aSource String
     * @param aSubstring String
     * @param aPermutations List<String>
     */
    public void findPermutationsIn(String           aSource, 
                                   String           aSubstring,
                                   List<String>     aPermutations) {
        
        Map<String, Integer> tempSubstringCountMap;
        Map<String, Integer> tempCurrentSourceCountMap;
        int                  tempWindowSize;
        String               tempCurrentCandidate;
        int                  i = 0;
        
        this.validateSubstringLessThanSource(aSource, aSubstring);
        
        tempWindowSize = aSubstring.length();
        tempSubstringCountMap = this.createCoutMapFrom(aSubstring);
        
        while (this.morePermutationsFitInSource(aSource, tempWindowSize, i)) {
            
            tempCurrentCandidate = aSource.substring(i, i + tempWindowSize);
            tempCurrentSourceCountMap = this.createCoutMapFrom(tempCurrentCandidate);
            
            if (this.haveSameLettersAndCounts(tempSubstringCountMap,
                                              tempCurrentSourceCountMap)) {
                
                aPermutations.add(tempCurrentCandidate);
            }
            
            
            
            i += 1;
            
        }
        
    }


    /**
     * Answer whether the candidate map and current source string window size map
     * are basically the same
     * @param aSubstringCountMap
     * @param aCurrentSourceCountMap
     * @return
     */
    protected boolean haveSameLettersAndCounts(Map<String, Integer> aSubstringCountMap,
                                               Map<String, Integer> aCurrentSourceCountMap) {
        
        return aSubstringCountMap.equals(aCurrentSourceCountMap);
    }



    /**
     * Answer whether any more permutations of aWindowSize fit in aSource
     * @param aSource
     * @param aWindowSize
     * @param i
     * @return boolean
     */
    protected boolean morePermutationsFitInSource(String aSource,
                                                  int aWindowSize, 
                                                  int i) {
        
        return i < aSource.length() &&
                (i + aWindowSize) <= aSource.length();
        
    }
    
    /**
     * Validate substring less than source string
     * @param aSource String
     * @param aSubstring String
     */
    protected void validateSubstringLessThanSource(String aSource, 
                                                   String aSubstring) {
        
        if (aSubstring.length() > aSource.length()) {
            
            throw new IllegalArgumentException("Substring length must be less than source");
        }
        
    }
    
    
    /**
     * Create the counting map for aString
     * @param aValue String
     * @return aMap <String, Integer>
     */
    protected Map<String, Integer> createCoutMapFrom(String aString) {
        
        Map<String, Integer>        tempMap = new HashMap<String, Integer>();
        Integer                     tempEntry;
        String                      tempLetterKey;
        
        
        for (int i = 0; i < aString.length(); i++) {
            
            tempLetterKey = aString.substring(i, i+1);
            tempEntry = tempMap.get(tempLetterKey);
            if (tempEntry == null) {
                
               tempEntry = new Integer(1);
            }
            else {
                
                tempEntry = new Integer(tempEntry.intValue() + 1);
            }
            
            tempMap.put(tempLetterKey, tempEntry);
            
        }
        
        return tempMap;
    }

    
    /**
     * Answer all permutations of aValue
     * @param aValue String
     * @param aPermutations List<String>
     */
    public void findAllPermutationsOf(String aValue,
                                      List<String> aPermutations) {
        
        String tempChopped;
        
        if (aValue.isEmpty()) {
            //Do nothing
        }
        else if (aValue.length() == 1) {
            
            aPermutations.add(aValue);
        }
        else if (aValue.length() == 2) {
            
            aPermutations.add(aValue);
            aPermutations.add((new StringBuilder()).append(aValue).reverse().toString());
        }
        else {
            
            tempChopped = aValue.substring(aValue.length()-1);
            this.findAllPermutationsOf(aValue.substring(0, aValue.length()-1), aPermutations);
            
            this.producePermutationsRotatingAround(tempChopped, aPermutations);
        }
        
        
    }
    
    
    /**
     * Produce permutations by adding aStringToInsert to aValue at all possible positions
     * @param aStringToInsert String
     * @return List<String>
     */
    protected void producePermutationsRotatingAround(String aStringToInsert,
                                                     List<String> aPermutations) {
        
        String                  tempFirstHalf;
        String                  tempSecondHalf;
        String                  tempCurrentValue;
        Iterator<String>        tempItr;
        
        
        tempItr = new ArrayList<String>(aPermutations).iterator();
        aPermutations.clear();
        
        while (tempItr.hasNext()) {
            
            tempCurrentValue = tempItr.next();
            
            aPermutations.add(aStringToInsert + tempCurrentValue);
            for (int i = 1; i < tempCurrentValue.length(); i++) {
                
                tempFirstHalf = tempCurrentValue.substring(0, i);
                tempSecondHalf = tempCurrentValue.substring(i);
                aPermutations.add(tempFirstHalf + aStringToInsert + tempSecondHalf);
            }
            
            aPermutations.add(tempCurrentValue + aStringToInsert);
        
        }
        
        
    }


    
    /**
     * Answer all permutations of aValue which is an array of int[]
     * @param aValue int[]
     * @param aLength int
     * @param aPermutations List<int[]>
     */
    public void findAllPermutationsOf(int[] aValue,
                                      int aLength,
                                      List<int[]> aPermutations) {
        
        int     tempChopped;
        
        if (aLength == 0) {
            //Do nothing
        }
        else if (aLength == 1) {
            
            this.createAllPermuationsOfSingleElementArray(aValue, aPermutations);
            
        }
        else if (aLength == 2) {
            
            this.createAllPermuationsOfTwoElementArray(aValue, aPermutations);
            
        }
        else {
            
            tempChopped = aValue[aLength-1];
            this.findAllPermutationsOf(aValue, aLength-1, aPermutations);
            
            this.producePermutationsRotatingAround(tempChopped, aPermutations);
        }
        
        
    }
    
    /**
     * Produce permutations by adding aValueToInsert to aValue at all possible positions
     * @param aValueToInsert int
     * @return List<int[]>
     */
    protected void producePermutationsRotatingAround(int aValueToInsert,
                                                     List<int[]> aPermutations) {
        
        int[]                   tempCurrentValue;
        Iterator<int[]>         tempItr;
        
        
        tempItr = new ArrayList<int[]>(aPermutations).iterator();
        aPermutations.clear();
        
        while (tempItr.hasNext()) {
            
            tempCurrentValue = tempItr.next();
            
            aPermutations.add(this.insertIntoNewFrom(tempCurrentValue, aValueToInsert, 0));
            
            for (int i = 1; i < tempCurrentValue.length; i++) {
                
                aPermutations.add(this.insertIntoNewFrom(tempCurrentValue, aValueToInsert, i));
            }
            
            aPermutations.add(this.insertIntoNewFrom(tempCurrentValue, aValueToInsert, tempCurrentValue.length));
        
        }
        
        
    }
    
    
    /**
     * Produce new array from anArray by inserting aValue into aPositionIdx
     * @param anArray int[]
     * @param aValue int
     * @param aPositionIdx int
     * @return int[]
     */
    protected int[] insertIntoNewFrom(int[] anArray, int aValue, int aPositionIdx) {
        
        int[]   tempNewArray;
        
        tempNewArray = new int[anArray.length + 1];
        
        for (int i = 0; i < aPositionIdx; i++) {
            
            tempNewArray[i] = anArray[i];
        }
        
        tempNewArray[aPositionIdx] = aValue;
        

        for (int i = aPositionIdx; i < anArray.length; i++) {
            
            tempNewArray[i+1] = anArray[i];
       
        }
        
        return tempNewArray;
        
    }



    /**
     * Create all permutations of two element array. Just the normal order and its revers
     * @param aValue
     * @param aPermutations
     */
    protected void createAllPermuationsOfTwoElementArray(int[] aValue,
                                                         List<int[]> aPermutations) {
        
        int[] tempArray;
        
        tempArray = new int[2];
        tempArray[0] = aValue[0];
        tempArray[1] = aValue[1];
        aPermutations.add(tempArray);
        
        tempArray = new int[2];
        tempArray[0] = aValue[1];
        tempArray[1] = aValue[0];
        aPermutations.add(tempArray);
        
    }



    /**
     * Create new array with single value
     * @param aValue
     * @param aPermutations
     */
    protected void createAllPermuationsOfSingleElementArray(int[] aValue,
                                                            List<int[]> aPermutations) {
        
        int[] tempArray;
        
        tempArray = new int[1];
        
        tempArray[0] = aValue[0];
        aPermutations.add(tempArray);
        
    }
    
    

}
