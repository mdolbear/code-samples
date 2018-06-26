package com.oracle.randomizedalg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.oracle.utils.PermutationUtils;

/**
 *
 *
 */
public class RandomizedAlgorithmUtils {

    private static RandomizedAlgorithmUtils instance;
    
    /**
     * Answer my default instance
     * @return RandomizedAlgorithmUtls
     */
    public synchronized static RandomizedAlgorithmUtils getInstance() {
        
        if (instance == null) {
            
            instance = new RandomizedAlgorithmUtils();
        }
        
        return instance;
        
    }
    
    
    /**
     * Answer an instance for the following arguments
     */
    protected RandomizedAlgorithmUtils() {
        super();
    }
    
    /**
     * Randomize a list of E in place
     * @param aList List<E>
     */
    public <E> List<E> randomize(List<E> aList) {
        
        
        E       tempValue;
        Random  tempRandom = new Random();
        int     tempRandomIdx;
        List<E> tempResult;
        
        tempResult = new ArrayList<E>(aList);
        for (int i = 0; i < tempResult.size(); i++) {
            
            tempRandomIdx = tempRandom.nextInt(tempResult.size());
            
            tempValue = tempResult.get(tempRandomIdx);
            tempResult.set(tempRandomIdx, tempResult.get(i));
            tempResult.set(i, tempValue);
            
        }
        
        return tempResult;

    }

    
    /**
     * Answer a randomized index list based on aSize of a collection of objects that will be accessed
     * @param aSize int
     * @return int[]
     */
    public int[] createRandomizedIndexList(int aSize) {
        
        int[]           tempInitialList;
        Random          tempRandom;
        List<int[]>     tempPossibleIndexArrays;
        int             tempRandomChoice;
        
        tempInitialList = this.createInitialIndexListFor(aSize);
        tempRandom = new Random();
        tempPossibleIndexArrays = this.produceAllPermutationsOf(tempInitialList);
        System.out.println("Number of possible permutations: " + tempPossibleIndexArrays.size());
        
        //Pick an random array given the possible permutations of tempInitialList
        tempRandomChoice = tempRandom.nextInt(tempPossibleIndexArrays.size());
        
        return tempPossibleIndexArrays.get(tempRandomChoice);
        
    }
    
    /**
     * Answer an index list give aSize
     * @param aSize int
     * @return int[]
     */
    protected int[] createInitialIndexListFor(int aSize) {
        
        int[]   tempResults;
        
        tempResults = new int[aSize];
        for (int i = 0; i < aSize; i++) {
            
            tempResults[i] = i;
        }
        
        return tempResults;
    }
    
    /**
     * Answer all permutations of a List of int[]. This would normally be used for coming up with all possible
     * ways an array can be indexed so that all orders are considered when picking a randomized order.
     * @param anArray int[]
     * @return int[][]
     */
    protected List<int[]> produceAllPermutationsOf(int[] anIndexes) {
        
        List<int[]> tempAllPermutations = new ArrayList<int[]>();
        
        (new PermutationUtils()).findAllPermutationsOf(anIndexes, anIndexes.length, tempAllPermutations);
        
        return tempAllPermutations;
        
    }
    
    
}
