package com.oracle.knapsack;

/**
 *
 *
 */
public class Item {

    private int id;
    private int weight;
    private int value;
    
    
    /**
     * Answer an instance for the following arguments
     * @param anId int
     * @param aWeight int
     * @param aValue int
     */
    public Item(int anId,
                int aWeight,
                int aValue) {
        
        super();
        this.setId(anId);
        this.setWeight(aWeight);
        this.setValue(aValue);
        
    }


    /**
     * Answer wether or not this item has anId
     * @param anId int
     * @return boolean
     */
    public boolean hasId(int anId) {
        
        return this.getId() == anId;
    }
    
    
    /**
     * Answer my id
     * @return int
     */
    protected int getId() {
        return id;
    }


    /**
     * Set my id
     * @param id int
     */
    protected void setId(int id) {
        this.id = id;
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
    protected void setWeight(int weight) {
        this.weight = weight;
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
    protected void setValue(int value) {
        this.value = value;
    }

    
    /**
     * Answer my string representation
     * @return String 
     */
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempBuilder.append("Item: ");
        
        tempBuilder.append("id: ");
        tempBuilder.append(this.getId());
        tempBuilder.append(" ");
        
        tempBuilder.append("weight: ");
        tempBuilder.append(this.getWeight());
        tempBuilder.append(" ");
        
        tempBuilder.append("value: ");
        tempBuilder.append(this.getValue());
        tempBuilder.append(" ");
        
        return tempBuilder.toString();
        
    }
    
    
}
