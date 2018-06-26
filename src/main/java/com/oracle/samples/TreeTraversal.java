package com.oracle.samples;

import java.io.Serializable;

/**
 *
 *
 */
public class TreeTraversal implements Serializable {


    private StringBuilder    builder;
    private int              index;
    private int              currentDepth;
    private int              maxDepth;
    
    //Constants
    private static final long serialVersionUID = -9150519816192043199L;
    
    /**
     * Answer an instance for the following arguments
     */
    public TreeTraversal() {
        
        super();
        this.setBuilder(new StringBuilder());
        this.setIndex(0);
        this.setCurrentDepth(0);
        this.setMaxDepth(0);
    }

    /**
     * Answer my builder
     * @return StringBuilder
     */
    protected StringBuilder getBuilder() {
        return builder;
    }

    /**
     * Set my builder
     * @param builder StringBuilder
     */
    protected void setBuilder(StringBuilder builder) {
        this.builder = builder;
    }

    /**
     * Answer my index
     * @return int
     */
    public int getIndex() {
        return index;
    }

    /**
     * Set my index
     * @param index int
     */
    protected void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * Increment index
     */
    public void incrementIndex() {
        
        int tempValue;
        
        tempValue = this.getIndex() + 1;
        this.setIndex(tempValue);
    }
    
    
    /**
     * Append data to my underling string builder
     * @param aValue String
     */
    public void append(String aValue) {
        
        this.getBuilder().append(aValue);
    }
    
    /**
     * Answer my treee size
     * @return
     */
    public int getTreeSize() {
        
        return this.getIndex(); //Increments one past on the final part of traversal
    }
    
    /**
     * Answer the string contained in my builder
     * @return String
     */
    public String toString() {
        
        return this.getBuilder().toString();
    }

    
    /**
     * Increment current depth
     */
    public int incrementCurrentDepth() {
        
        int tempValue;
        
        tempValue = this.getCurrentDepth();
        tempValue++;
        this.setCurrentDepth(tempValue);
        
        return tempValue;
        
    }
    
    /**
     * Answer my currentDepth
     * @return int
     */
    public int getCurrentDepth() {
        return currentDepth;
    }

    /**
     * Set my currentDepth
     * @param currentDepth int
     */
    protected void setCurrentDepth(int currentDepth) {
        this.currentDepth = currentDepth;
    }

    /**
     * Answer my maxDepth
     * @return int
     */
    public int getMaxDepth() {
        return maxDepth;
    }

    /**
     * Set my maxDepth
     * @param maxDepth int
     */
    protected void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
    
    /**
     * Record max depth
     */
    public void recordMaxDepth() {
        
        if (this.getMaxDepth() < this.getCurrentDepth()) {
            
            this.setMaxDepth(this.getCurrentDepth());
        }
        
        this.setCurrentDepth(0);
    }
    
    
}
