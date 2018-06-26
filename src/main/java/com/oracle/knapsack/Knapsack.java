package com.oracle.knapsack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 *
 */
public class Knapsack {

    private List<Item> items;
    private int capacity; //In the weight units
    
    /**
     * Answer an instance for the following arguments
     */
    public Knapsack(int aCapacity) {
        
        super();
        this.clearItems();
        this.setCapacity(aCapacity);
        
    }


    /**
     * Remove history of all items from me
     */
    public void clearItems() {
        this.setItems(new ArrayList<Item>());
    }

    
    /**
     * Accept anItem. If I accept it answer true and add it to me. If not
     * answer false
     * @param anItem Item
     * 
     */
    public boolean accept(Item anItem) {
        
        boolean tempResult;
        
        tempResult = !this.hasItem(anItem) &&
                        this.getCapacity() >= (this.getCurrentItemsWeight() + anItem.getWeight());
        if (tempResult) {
            
            this.addItem(anItem);
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer whether or not I have an item
     * @param anItem Item
     * @return boolean
     */
    protected boolean hasItem(Item anItem) {
        
        Iterator<Item> tempItems;
        Item           tempCurrent;
        Item           tempResult = null;
        
        tempItems = this.getItems().iterator();
        while (tempItems.hasNext() && tempResult == null) {
            
            tempCurrent = tempItems.next();
            if (tempCurrent.hasId(anItem.getId())) {
                
                tempResult = tempCurrent;
            }
            
        }
        
        return tempResult != null;
        
        
    }
    

    /**
     * Answer the value of my current items
     * @return int
     */
    public int getCurrentItemsValue() {
        
        int tempResult = 0;
        
        for (Item anItem: this.getItems()) {
            
            tempResult += anItem.getValue();
        }
        
        return tempResult;
        
        
    }
    
    /**
     * Answer the weight of my current items
     * @return int
     */
    public int getCurrentItemsWeight() {
        
        int tempResult = 0;
        
        for (Item anItem: this.getItems()) {
            
            tempResult += anItem.getWeight();
        }
        
        return tempResult;
        
        
    }
    
    
    /**
     * Answer my items
     * @return List<Item>
     */
    protected List<Item> getItems() {
        return items;
    }

    
    /**
     * Add anItem to me
     * @param anItem Item
     */
    protected void addItem(Item anItem) {
        
        this.getItems().add(anItem);
    }
    
    
    /**
     * Set my items
     * @param items List<Item>
     */
    protected void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Answer my capacity
     * @return int
     */
    protected int getCapacity() {
        return capacity;
    }

    /**
     * Set my capacity
     * @param capacity int
     */
    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    

}
