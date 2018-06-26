package com.oracle.knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class CombinationEvaluation {

    private         int             value;
    private         int             weight;
    private         List<Item>      items;
    
    /**
     * Answer a default instance
     */
    public CombinationEvaluation() {
        
        super();
        this.setItems(new ArrayList<Item>());
        this.setValue(0);
        this.setWeight(0);
        
    }
    
    
    
    /**
     * Answer an instance for the following arguments
     * @param anItems List<Item>
     * @param aWeight int
     * @param aValue int
     */
    public CombinationEvaluation(List<Item> anItems,
                                 int aWeight,
                                 int aValue) {
        
        this();
        this.setItems(anItems);
        this.setWeight(aWeight);
        this.setValue(aValue);
    }

    /**
     * Answer my value
     * @return int
     */
    public int getValue() {
        return value;
    }

    /**
     * Set my value
     * @param value int
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Answer my weight
     * @return int
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Set my weight
     * @param weight int
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Answer my items
     * @return List<Item>
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Set my items
     * @param items List<Item>
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Answer whether or not I have greater value than anotherCombinationEvaluation
     * @param anotherCombinationEvaluation CombinationEvaluation
     * @return boolean
     */
    public boolean hasGreaterValueThan(CombinationEvaluation anotherCombinationEvaluation) {
        
        return this.getValue() > anotherCombinationEvaluation.getValue();
        
    }
   
    /**
     * Answer my string representation
     * @return String
     */
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempBuilder.append("Combination: ");
        tempBuilder.append(this.getItems().toString());
        tempBuilder.append(" ");
        
        tempBuilder.append("Weight: ");
        tempBuilder.append(this.getWeight());
        tempBuilder.append(" ");
        
        tempBuilder.append("Value: ");
        tempBuilder.append(this.getValue());
        
        
        return tempBuilder.toString();
        
    }
    
}
