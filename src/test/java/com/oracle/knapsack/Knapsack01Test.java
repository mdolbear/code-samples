package com.oracle.knapsack;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.oracle.utils.CombinatoricsUtils;

/**
 *
 *
 */
public class Knapsack01Test {

    /**
     * Answer an instance for the following arguments
     */
    public Knapsack01Test() {
        super();
    }
    
    /**
     * Test producing of combinations
     */
    @Test
    public void testProducingCombinations() {
        
        CombinatoricsUtils  tempUtils = CombinatoricsUtils.getInstance();
        List<List<Item>>    tempResults;
        List<Item>          tempPossibleItems;
        List<Item>          tempComboItems;

        
        tempPossibleItems = this.createPossibleItemList();
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(1);
        tempUtils.getPossibleCombinationsOf(tempPossibleItems, tempComboItems,  0, tempPossibleItems.size()-1, 0, 1, tempResults);
        assertTrue("Number of combinations incorrect 0", tempResults.size() == 6);
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(2);
        tempUtils.getPossibleCombinationsOf(tempPossibleItems, tempComboItems,   0, tempPossibleItems.size()-1, 0, 2, tempResults);
        assertTrue("Number of combinations incorrect 1", tempResults.size() == 15);
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(3);
        tempUtils.getPossibleCombinationsOf(tempPossibleItems, tempComboItems,    0, tempPossibleItems.size()-1, 0, 3, tempResults);
        assertTrue("Number of combinations incorrect 2", tempResults.size() == 20);
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(4);
        tempUtils.getPossibleCombinationsOf(tempPossibleItems,  tempComboItems,  0, tempPossibleItems.size()-1, 0,  4, tempResults);
        assertTrue("Number of combinations incorrect 3", tempResults.size() == 15);
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(5);
        tempUtils.getPossibleCombinationsOf(tempPossibleItems,  tempComboItems, 0, tempPossibleItems.size()-1 , 0,  5, tempResults);
        assertTrue("Number of combinations incorrect 4", tempResults.size() == 6);
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(6);
        tempUtils.getPossibleCombinationsOf(tempPossibleItems,  tempComboItems, 0, tempPossibleItems.size()-1 , 0,  6, tempResults);
        assertTrue("Number of combinations incorrect 5", tempResults.size() == 1);
        
        
    }
    
    /**
     * Test producing of combinations
     */
    @Test
    public void testProducingCombinationsPascal() {
        
        CombinatoricsUtils  tempUtils = CombinatoricsUtils.getInstance();
        List<List<Item>>    tempResults;
        List<Item>          tempPossibleItems;
        List<Item>          tempComboItems;

        
        tempPossibleItems = this.createPossibleItemList();
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(1);       
        tempUtils.getPossibleCombinationsOfPascal(tempPossibleItems, tempComboItems,  0, tempPossibleItems.size(), 0, 1, tempResults);
        assertTrue("Number of combinations incorrect 0", tempResults.size() == 6);
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(2);        
        tempUtils.getPossibleCombinationsOfPascal(tempPossibleItems, tempComboItems,   0, tempPossibleItems.size(), 0, 2, tempResults);
        assertTrue("Number of combinations incorrect 1", tempResults.size() == 15);
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(3);       
        tempUtils.getPossibleCombinationsOfPascal(tempPossibleItems, tempComboItems,    0, tempPossibleItems.size(), 0, 3, tempResults);
        assertTrue("Number of combinations incorrect 2", tempResults.size() == 20);
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(4);       
        tempUtils.getPossibleCombinationsOfPascal(tempPossibleItems,  tempComboItems,  0, tempPossibleItems.size(), 0,  4, tempResults);
        assertTrue("Number of combinations incorrect 3", tempResults.size() == 15);
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(5);        
        tempUtils.getPossibleCombinationsOfPascal(tempPossibleItems,  tempComboItems, 0, tempPossibleItems.size() , 0,  5, tempResults);
        assertTrue("Number of combinations incorrect 4", tempResults.size() == 6);
        
        tempResults = new ArrayList<List<Item>>();
        tempComboItems = tempUtils.createChoiceCollection(6);
        tempUtils.getPossibleCombinationsOfPascal(tempPossibleItems,  tempComboItems, 0, tempPossibleItems.size() , 0,  6, tempResults);
        assertTrue("Number of combinations incorrect 5", tempResults.size() == 1);
        
        tempResults = tempUtils.generateAllPossibleCombinationsForPascal(tempPossibleItems);
        assertTrue("Number of combinations incorrect 6", tempResults.size() == (6 + 15 + 20 + 15 + 6 + 1));
        
        
    }
    
    /**
     * Test best selection by thief
     */
    @Test
    public void testBestSelectionByThief() {
        
        List<Item>              tempPossibleItems;
        Thief                   tempThief;
        CombinationEvaluation   tempEval;
        
        tempPossibleItems = this.createPossibleItemList();
        
        tempThief = new Thief(100); //Can carry 100 lbs
        tempEval = tempThief.bruteForceFindMostValuableItems(tempPossibleItems);
        assertTrue("Attempt 1", tempEval != null && !tempEval.getItems().isEmpty());
        System.out.println("Evaluation for test 1: " + tempEval.toString());
        
        tempThief = new Thief(5); //Can carry 5 lbs
        tempEval = tempThief.bruteForceFindMostValuableItems(tempPossibleItems);
        assertTrue("Attempt 1", tempEval != null && tempEval.getItems().isEmpty());
        System.out.println("Evaluation for test 2: " + tempEval.toString());
        
        
        
    }
    
    /**
     * Create possible item list
     * @return List<Item>
     */
    protected List<Item> createPossibleItemList() {
        
        List<Item>          tempResults = new ArrayList<Item>();
        Item                tempItem;
        
        tempItem = new Item(0, 10, 1000);
        tempResults.add(tempItem);
        
        tempItem = new Item(1, 50, 100);
        tempResults.add(tempItem);
        
        tempItem = new Item(2, 50, 50);
        tempResults.add(tempItem);
        
        tempItem = new Item(3, 15, 10);
        tempResults.add(tempItem);
        
        tempItem = new Item(4, 68, 20);
        tempResults.add(tempItem);
        
        tempItem = new Item(5, 20, 100);
        tempResults.add(tempItem);
        
        return tempResults;
        
        
    }

}
