package com.cracking;

import java.util.Random;

/**
 *
 *
 */
public class ZeroValueSort {

    /**
     * Answer an instance for the following arguments
     */
    public ZeroValueSort() {
        super();
    }
    
    /**
     * Perform sort
     * @param anArray int[]
     * @param aSortBound int
     */
    public int sort(int[] anArray,
                    int aSortBound) {
        
        int     tempZeroIndex;
        int     tempValueIndex;
        int     tempNumberOfIterations = 0;
        Random  tempRandom = new Random();
        
        tempZeroIndex = this.findIndexForValue(anArray, 0);
        while (!this.isSorted(anArray) &&
                    tempNumberOfIterations < aSortBound) {
            
            tempValueIndex = this.pickNextIndex(anArray, tempZeroIndex, tempRandom);
                        
            this.switchValuesIn(anArray, 
                                tempZeroIndex, 
                                tempValueIndex);
            
            tempZeroIndex = tempValueIndex;
            System.out.println("Zero is now in index: " + tempZeroIndex + " value: " + anArray[tempZeroIndex]);
            
            
            tempNumberOfIterations++;
        }
        
        
        return tempNumberOfIterations;
        
    }

    /**
     * @param anArray
     * @param aZeroIndex
     * @param aRandom
     * @return
     */
    protected int pickNextIndex(int[] anArray, int aZeroIndex, Random aRandom) {
        int tempValueIndex;
        if (aZeroIndex == 0) {
            
            tempValueIndex = aRandom.nextInt(anArray.length);
            System.out.println("Zero value index was zero. So randomly picked index: " + tempValueIndex);
            
        }
        else {
            
            tempValueIndex = this.findIndexForValue(anArray, aZeroIndex);
        }
        return tempValueIndex;
    }
    
    /**
     * Answer whether or not anArray is sorted for this particular algorithm
     * @param anArray int[]
     * @return boolean
     */
    public boolean isSorted(int[] anArray) {
        
        boolean tempResult = true;
        int     i = 0;
        
        while (tempResult &&
                i < anArray.length) {
            
            tempResult = (anArray[i] == i);
            i++;
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer the index in anArray for aValue
     * @param aValue int
     * @return int
     */
    protected int findIndexForValue(int[] anArray,
                                    int aValue) {
        
        int tempIndexResult = -1;
        int tempCurrentIndex = 0;
        
        while (tempIndexResult == -1 && tempCurrentIndex < anArray.length) {
            
            if (anArray[tempCurrentIndex] == aValue) {
                
                tempIndexResult = tempCurrentIndex;
            }
            else {
                
                tempCurrentIndex++;
            }
            
        }
        
        return tempIndexResult;
        
        
    }
    
    /**
     * Switch values in anArray
     * @param anArray int[]
     * @param anIndex1 int
     * @param anIndex2 int
     * 
     */
    protected void switchValuesIn(int[] anArray, 
                                  int anIndex1, 
                                  int anIndex2) {
        
        int tempValue;
        
        tempValue = anArray[anIndex1];
        anArray[anIndex1] = anArray[anIndex2];
        anArray[anIndex2] = tempValue;
        
    }

}
