package com.oracle.samples;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class ArrayChallenges {

    /**
     * Answer an instance for the following arguments
     */
    public ArrayChallenges() {
       super();
    }
    
    /**
     * Find the pairs from two arrays whose sum equals some value k
     * @param anArray1 int[]
     * @param anArray2 int[]
     * @param k int
     * @return List<List<Integer>
     */
    public List<List<Integer>> findPairsWhoSumEqualsFrom(int[] anArray1, 
                                                         int[] anArray2, 
                                                         int k) 
            throws IllegalArgumentException {
        
        List<List<Integer>> tempResults = new ArrayList<List<Integer>>();
        int                 tempCurrentCandidateValue;
        Integer             tempReturnFromSearch;
        
        
        this.validateArraysSameSize(anArray1, anArray2);
        
        for (int i = 0; i < anArray1.length; i++) {
            
            tempCurrentCandidateValue = k - anArray1[i];
            if (!this.isOutsideOf(anArray2, tempCurrentCandidateValue)) {
                
                tempReturnFromSearch = this.binarySearch(anArray2, tempCurrentCandidateValue);
                
                if (tempReturnFromSearch != null) {
                    
                    this.captureCurrentPair(anArray1, 
                                            anArray2, 
                                            tempResults,
                                            tempReturnFromSearch, 
                                            i);
                    
                }
                
            }
            
            
        }
        
        return tempResults;
        
    }



    /**
     * Capture current pair into aResults
     * @param anArray1
     * @param anArray2
     * @param aResults
     * @param aReturnFromSearch
     * @param i
     */
    protected void captureCurrentPair(int[] anArray1, 
                                      int[] anArray2,
                                      List<List<Integer>> aResults, 
                                      Integer aReturnFromSearch,
                                      int i) {
        
        List<Integer> tempCurrentPair;
        
        tempCurrentPair = new ArrayList<Integer>();
        tempCurrentPair.add(new Integer(anArray1[i]));
        tempCurrentPair.add(new Integer(anArray2[aReturnFromSearch.intValue()]));
        aResults.add(tempCurrentPair);
        
    }
    
    /**
     * Validate that arrays are the same size
     * @param anArray1 int[]
     * @param anArray2 int[]
     * 
     */
    protected void validateArraysSameSize(int[] anArray1, int[] anArray2) {
        
        if (anArray1.length != anArray2.length) {
            
            throw new IllegalArgumentException("Arrays must be the same size");
        }
        
    }
    
    /**
     * Answer whether or not aValue is outside of the range of a sorted anArray
     * @param anArray int[]
     * @param aValue int
     * @return boolean
     */
    protected boolean isOutsideOf(int[] anArray, int aValue) {
        
        return aValue < anArray[0] || aValue > anArray[anArray.length - 1];
    }
    
    /**
     * Linear search for aValue in anArray. Assumes anArray is sorted
     * @param anArray int[]
     * @param aValue int
     * @return Integer
     */
    protected Integer linearSearch(int[] anArray, int aValue, int aStartingIndex) {
        
        Integer tempResult = null;
        int     i;
        
        i = aStartingIndex;
        while (i < anArray.length 
                    && (anArray[i] < aValue)
                        && tempResult == null) {
            
            if (anArray[i] == aValue) {
                
                tempResult = new Integer(i);
            }
            else {
                i++;
            }
            
        }
        
        
        return tempResult;
    }
    
    /**
     * Binary search. Answer the index of anArray that
     * contains aValue or null
     * @param anArray int[]
     * @param  aValue int
     * @return Integer
     */
    protected Integer binarySearch(int[] anArray, 
                                   int aValue) {
        
        Integer     tempResult = null;
        int         tempStart;
        int         tempEnd;
        int         tempMidpoint;
        int         tempMidpointValue;
        
        //Initialize
        tempStart = 0;
        tempEnd = anArray.length;
        
        while (this.shouldContinueBinarySearch(anArray, tempStart, tempEnd, tempResult)) {
            
            tempMidpoint = (tempStart + tempEnd) >>> 1;
            tempMidpointValue = anArray[tempMidpoint];
            
            if (tempMidpointValue == aValue) {
                
                tempResult = new Integer(tempMidpoint);
            }
            else if (aValue > tempMidpointValue) {
                
                tempStart = tempMidpoint+1;
            }
            else if (aValue < tempMidpointValue) {
                
                tempEnd = tempMidpoint-1;
            }
            
            
            
        }
        
        return tempResult;
        
        
    }


   /**
    * Determine if I should continue binary search
    * @param anArray
    * @param aStart
    * @param aEnd
    * @param aResult
    * @return boolean
    */
   protected boolean shouldContinueBinarySearch(int[] anArray, 
                                                int aStart,
                                                int aEnd,
                                                Integer aResult) {
       
       return aStart <= aEnd && 
                    aStart < anArray.length &&
                        aEnd <= anArray.length &&
                            aResult == null;
       
   }
  
 
   
   

}
