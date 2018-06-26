package com.oracle.knapsack;

import java.util.Iterator;
import java.util.List;

import com.oracle.utils.CombinatoricsUtils;

/**
 *
 *
 */
public class Thief {

    private Knapsack knapsack;
    
    /**
     * Answer an instance for the following arguments
     */
    public Thief(int aKnapsackCapacity) {
        
        super();
        this.setKnapsack(new Knapsack(aKnapsackCapacity));
        
    }

    /**
     * Answer my knapsack
     * @return Knapsack
     */
    protected Knapsack getKnapsack() {
        return knapsack;
    }

    /**
     * Set my knapsack
     * @param knapsack Knapsack
     */
    protected void setKnapsack(Knapsack knapsack) {
        this.knapsack = knapsack;
    }
    
    

    /**
     * Find best solution brute force
     * @param aPossibleItems List<Item>
     * @return CombinationEvaluation
     */
    public CombinationEvaluation bruteForceFindMostValuableItems(List<Item> aPossibleItems) {
        
        CombinationEvaluation  tempBestSolution = new CombinationEvaluation();
        List<List<Item>>       tempAllCombinations;
        CombinationEvaluation  tempCurrentSolution;
        
        tempAllCombinations = CombinatoricsUtils.getInstance().generateAllPossibleCombinationsForPascal(aPossibleItems);
        for (int i = 0; i < tempAllCombinations.size(); i++) {
            
            tempCurrentSolution = this.evaluate(tempAllCombinations.get(i));
            if (tempCurrentSolution.hasGreaterValueThan(tempBestSolution)) {
                
                tempBestSolution = tempCurrentSolution;
            }
        }

        
        return tempBestSolution;
    }
    
    
    /**
     * Evaluate aPossibleItems as a choice for 
     * @param aPossibleItems List<Item>
     * @return CombinationEvaluation
     */
    protected CombinationEvaluation evaluate(List<Item> aPossibleItems) {
        
        CombinationEvaluation   tempEval;
        Iterator<Item>          tempItr;
        boolean                 tempOverweight = false;
        Item                    tempCurrent;
        Knapsack                tempKnapsack;
        
        tempKnapsack = this.getKnapsack();
        tempKnapsack.clearItems();
        tempItr = aPossibleItems.iterator();
        while (tempItr.hasNext()  && !tempOverweight) {
            
            tempCurrent = tempItr.next();
            tempOverweight = !tempKnapsack.accept(tempCurrent); //If the knapsack accepts the item (has the capacity)
                                                                //then we are not overweight - can proceed
        }
        
        if (!tempItr.hasNext()  && !tempOverweight) {
            
            tempEval = new CombinationEvaluation(tempKnapsack.getItems(),
                                                 tempKnapsack.getCurrentItemsWeight(),
                                                 tempKnapsack.getCurrentItemsValue());
        }
        else {
            
            tempEval = new CombinationEvaluation(); //Create an eval with 0 weight that will be rejected
        }
        
        
        return tempEval;
        
        
    }
    
    
}
