package com.oracle.partition;

import java.util.ArrayList;
import java.util.List;

import com.oracle.utils.CombinatoricsUtils;

/**
 *
 *
 */
public class Partition {

    /**
     * Answer an instance for the following arguments
     */
    public Partition() {
        super();
    }
   
    
    /**
     * Find a subset of anInitialSet where the elements add up to aSum
     * @param anInitialSet List<Integer>
     * @param aSum int
     * @return List<List<Integer>>
     */
    public List<List<Integer>> findSubsetsWhereElementsEqualSum(List<Integer> anInitialSet,
                                                                int aSum) {
        
        List<List<Integer>>           tempResults = new ArrayList<List<Integer>>();
        List<List<Integer>>           tempAllCombinations;
        int                           i = 0;
        
        tempAllCombinations = CombinatoricsUtils.getInstance().generateAllPossibleCombinationsForPascal(anInitialSet);
        while( i < tempAllCombinations.size()) {
            
            if (this.computeSumFor(tempAllCombinations.get(i)) == aSum) {
                
                tempResults.add(tempAllCombinations.get(i));
            }
            
            i++;

        }

        
        return tempResults; 
    }
    
    
    /** 
     * Answer whether or not aValues can be broken into two sets where
     * the two sets have the same sum
     */
    public PartitionResult<Integer> canBeBrokenIntoTwoSetsWithEqualSum(List<Integer> anInitialSet) {
        
        PartitionResult<Integer>      tempResult = new PartitionResult<Integer>(false);
        List<List<Integer>>           tempAllCombinations;
        int                           i = 0;
        
        tempAllCombinations = CombinatoricsUtils.getInstance().generateAllPossibleCombinationsForPascal(anInitialSet);
        while( i < tempAllCombinations.size() && 
                                            !tempResult.isSuccessful()) {
            
            tempResult = this.evaluateTwoEqualSets(tempAllCombinations.get(i), anInitialSet);
            i++;

        }

        
        return tempResult;
    }
    
    /**
     * Evaluate if we can produce two equal sets by taking anInitialSet, removing aCurrentCombination, and then adding the remaining two
     * sets to see if they are equal or not
     * @param anInitialSet List<Integer>
     * @param aCurrentCombination List<Integer>
     * @return PartitionResult<Integer>
     */
    protected PartitionResult<Integer> evaluateTwoEqualSets(List<Integer> aCurrentCombination,
                                                            List<Integer> anInitialSet) {
        
        List<Integer>               tempNewSet;
        int                         tempSet1Sum = 0;
        int                         tempSet2Sum = 0;
        PartitionResult<Integer>    tempResult;
        
        tempNewSet = new ArrayList<Integer>(anInitialSet);
        tempNewSet.removeAll(aCurrentCombination);
        
        tempSet1Sum = this.computeSumFor(tempNewSet);
        tempSet2Sum = this.computeSumFor(aCurrentCombination);
               
        tempResult = new PartitionResult<Integer>(tempSet1Sum == tempSet2Sum);
        if (tempResult.isSuccessful()) {
            
            tempResult.setSet1(aCurrentCombination);
            tempResult.setSet2(tempNewSet);
            tempResult.setSetSumDifference(0);
            
        }
        
        return tempResult;
        
    }
    
    /**
     * Compute sum for aSet
     * @param aSet List<Integer>
     * @return int
     */
    protected int computeSumFor(List<Integer> aSet) {
        
        int    aSetSum = 0;
        
        for (int i = 0; i < aSet.size(); i++) {
            
            aSetSum += aSet.get(i).intValue();
        }
        
        return aSetSum;
        
    }
    

    /**
     * Answer the two sets formed from anInitialSet which have a minimum difference between them
     * @param anInitialSet List<Integer>
     * @return PartitionResult<Integer>
     */
    public PartitionResult<Integer> findSetsWithMinimumDifference(List<Integer> anInitialSet) {
        
        PartitionResult<Integer>      tempResult = new PartitionResult<Integer>(false);
        List<List<Integer>>           tempAllCombinations;
        int                           i = 0;
        
        tempResult.setSetSumDifference(Integer.MAX_VALUE);
        
        tempAllCombinations = CombinatoricsUtils.getInstance().generateAllPossibleCombinationsForPascal(anInitialSet);
        while( i < tempAllCombinations.size()) {
            
            tempResult = this.evaluateToFindMinimum(tempResult,
                                                    tempAllCombinations.get(i), 
                                                    anInitialSet);
            i++;

        }

        
        return tempResult;
    }
    
    
    /**
     * Evaluate and find the two sets with the minimum difference
     * @param aCurrentMininimum PartitionResult<Integer>
     * @param anInitialSet List<Integer>
     * @param aCurrentCombination List<Integer>
     * @return PartitionResult<Integer>
     */
    protected PartitionResult<Integer> evaluateToFindMinimum(PartitionResult<Integer> aCurrentMinimum,
                                                             List<Integer> aCurrentCombination,
                                                             List<Integer> anInitialSet) {
        
        int                         tempCurrentMinimum;
        int                         tempMinimum;
        List<Integer>               tempNewSet;
        int                         tempSet1Sum = 0;
        int                         tempSet2Sum = 0;
        PartitionResult<Integer>    tempResult;
        
        tempNewSet = new ArrayList<Integer>(anInitialSet);
        tempNewSet.removeAll(aCurrentCombination);
        
        tempSet1Sum = this.computeSumFor(tempNewSet);
        tempSet2Sum = this.computeSumFor(aCurrentCombination);
        tempCurrentMinimum = aCurrentMinimum.getSetSumDifference();
        tempMinimum = Math.abs(tempSet1Sum - tempSet2Sum);
        
        if (tempMinimum < tempCurrentMinimum) {
            
            tempResult = new PartitionResult<Integer>(true);
            tempResult.setSet1(tempNewSet);
            tempResult.setSet2(aCurrentCombination);
            tempResult.setSetSumDifference(tempMinimum);
            
        }
        else {
            
            tempResult = aCurrentMinimum;
        }
        
        
        return tempResult;
    }
    
    
}
