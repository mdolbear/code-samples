package com.oracle.utils;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *
 */
public class CombinatoricsUtils {

    private static CombinatoricsUtils instance;
    
    /**
     * Answer my default instance
     * @return CombinatoricsUtils
     */
    public synchronized static CombinatoricsUtils getInstance() {
        
        if (instance == null) {
            
            instance = new CombinatoricsUtils();
        }
        
        return instance;
        
    }
    
    
    /**
     * Answer an instance for the following arguments
     */
    protected CombinatoricsUtils() {
        super();
    }
    
    /**
     * Answer the possible combinations for aPossibleItems given aChoiceSize
     * @param aPossibleItems List<T>
     * @param aComboItems List<T>
     * @param aChoiceSize int
     * @param aResults List<List<T>>
     */
    public <T> void getPossibleCombinationsOf(List<T> aPossibleItems,
                                              List<T> aComboItems,
                                              int        aStartIdx,
                                              int        anEndIdx,
                                              int        aCurrentIdx,
                                              int        aChoiceSize,
                                              List<List<T>> aResults) {
        
        if (aCurrentIdx == aChoiceSize) {
                
            aResults.add(new ArrayList<T>(aComboItems));
        }
        else {
            
            for (int i = aStartIdx; (i <= anEndIdx) && (anEndIdx - i + 1 >= aChoiceSize - aCurrentIdx); i++) {
                
                aComboItems.set(aCurrentIdx, aPossibleItems.get(i));
                this.getPossibleCombinationsOf(aPossibleItems, 
                                               aComboItems, 
                                               i+1, 
                                               anEndIdx, 
                                               aCurrentIdx+1, 
                                               aChoiceSize, 
                                               aResults);
            }
        }

    }
    
    /**
     * Answer the possible combinations for aPossibleItems given aChoiceSize
     * @param aPossibleItems List<T>
     * @param aComboItems List<T>
     * @param aChoiceSize int
     * @param aResults List<List<T>>
     */
    public  <T> void getPossibleCombinationsOfPascal(List<T> aPossibleItems,
                                                     List<T> aComboItems,
                                                     int        aStartIdx,
                                                     int        anEndIdx,
                                                     int        aCurrentIdx,
                                                     int        aChoiceSize,
                                                     List<List<T>> aResults) {
        
        if (aCurrentIdx == aChoiceSize) {
                
            aResults.add(new ArrayList<T>(aComboItems));
        }
        else {
            
            if ( aStartIdx < anEndIdx) {
                
                aComboItems.set(aCurrentIdx, aPossibleItems.get(aStartIdx));
                this.getPossibleCombinationsOfPascal(aPossibleItems, 
                                                     aComboItems, 
                                                     aStartIdx+1, 
                                                     anEndIdx, 
                                                     aCurrentIdx+1, 
                                                     aChoiceSize, 
                                                     aResults);
                
                this.getPossibleCombinationsOfPascal(aPossibleItems, 
                                                     aComboItems, 
                                                     aStartIdx+1, 
                                                     anEndIdx, 
                                                     aCurrentIdx, 
                                                     aChoiceSize, 
                                                     aResults);
            }
            
        }

    }
    
    
    
    /**
     * Answer a choice collection for a ChoiceSize. We need to have a choice collection with nulls for the above
     * algorithms
     * @param aChoiceSize int
     * @return List<T>
     */
    public <T> List<T> createChoiceCollection(int aChoiceSize) {
        
        List<T> tempResult = new ArrayList<T>(aChoiceSize);
        
        for (int i = 0; i < aChoiceSize; i++) {
            
            tempResult.add(null);
        }
        
        return tempResult;
    }
    
    /**
     * Answer all possible combinations of aPossibleItems
     * @return List<List<T>>>
     */
    public <T> List<List<T>> generateAllPossibleCombinationsForPascal(List<T> aPossibleItems) {
        
        List<List<T>>    tempCumulativeResults = new ArrayList<List<T>>();
        List<List<T>>    tempIndividualResults;
        List<T>          tempComboItems;

        
        for (int tempChoiceSize = 1; tempChoiceSize <= aPossibleItems.size(); tempChoiceSize++) {
            
            tempIndividualResults = new ArrayList<List<T>>();
            tempComboItems = this.createChoiceCollection(tempChoiceSize);
            this.getPossibleCombinationsOfPascal(aPossibleItems, 
                                                  tempComboItems,
                                                  0,  
                                                  aPossibleItems.size(), 
                                                  0, 
                                                  tempChoiceSize, 
                                                  tempIndividualResults);
            tempCumulativeResults.addAll(tempIndividualResults);
            
        }
        
        return tempCumulativeResults;
        
    }

}
