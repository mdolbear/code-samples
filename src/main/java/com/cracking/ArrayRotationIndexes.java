package com.cracking;

import java.io.Serializable;

/**
 *
 *
 */
public class ArrayRotationIndexes implements Serializable {

    private int upperLeftRowIndex;
    private int upperLeftColumnIndex;
    
    private int lowerLeftRowIndex;
    private int lowerLeftColumnIndex;  
    
    private int lowerRightRowIndex;
    private int lowerRightColumnIndex;
    
    private int upperRightColumnIndex;
    private int upperRightRowIndex;
    
    private int arraySize;
    
    
    //Constants
    private static final long serialVersionUID = 4845391116923279589L;

    /**
     * Answer an instance for the following arguments
     * @param anArraySize int
     */
    public ArrayRotationIndexes(int anArraySize) {
        
        super();
        this.setArraySize(anArraySize);
        
        this.initializeIndexesToFirstCycle();
    }
    
    /**
     * Answer the number of cycles based on the array size
     * @return int
     */
    public int getNumberOfCycles() {
        
        return this.getArraySize()/2;
    }

    /**
     * Increment indexes to new cycle. The cycle number is zero based
     * @param aCycleNumber int
     */
    public void incrementIndexesToNewCycle(int aCycleNumber) {
        
        this.initializeIndexesToFirstCycle();
        
        this.setUpperLeftRowIndex(aCycleNumber);
        this.setUpperLeftColumnIndex(aCycleNumber);
        
        this.setLowerLeftRowIndex(this.getArraySize() - aCycleNumber - 1);
        this.setLowerLeftColumnIndex(aCycleNumber);
        
        this.setLowerRightRowIndex(this.getArraySize() - aCycleNumber - 1);
        this.setLowerRightColumnIndex(this.getArraySize() - aCycleNumber - 1);
        
        this.setUpperRightRowIndex(aCycleNumber);
        this.setUpperRightColumnIndex(this.getArraySize() - aCycleNumber - 1);
        
        
    }

    /**
     * Initialize indexes to first cycle
     */
    protected void initializeIndexesToFirstCycle() {
        
        this.setUpperLeftRowIndex(0);
        this.setUpperLeftColumnIndex(0);
        
        this.setLowerRightRowIndex(this.getArraySize() - 1);
        this.setLowerLeftColumnIndex(0);
        
        this.setLowerRightRowIndex(this.getArraySize() - 1);
        this.setLowerRightColumnIndex(this.getArraySize() - 1);
        
        this.setUpperRightRowIndex(0);
        this.setUpperRightColumnIndex(this.getArraySize() - 1);
        
    }

    
    /**
     * Increment for indexes for counter clockwise rotation
     */
    public void incrementIndexesCounterClockwiseRotation() {
        
        this.setUpperLeftRowIndex(this.getUpperLeftRowIndex() + 1);
        this.setLowerLeftColumnIndex(this.getLowerLeftColumnIndex() + 1);
        this.setLowerRightRowIndex(this.getLowerRightRowIndex() - 1);
        this.setUpperRightColumnIndex(this.getUpperRightColumnIndex() - 1);
        
    }
    
    /**
     * Answer whether or not there are more rotations in the current cycle based on my index values
     * @return boolean
     */
    public boolean hasMoreRotationsInCurrentCycle() {
        
        return (this.getUpperLeftRowIndex() < this.getLowerLeftRowIndex()) 
                && (this.getLowerLeftColumnIndex() < this.getLowerRightColumnIndex())
                    && (this.getLowerRightRowIndex() > this.getUpperRightRowIndex())
                        && (this.getUpperRightColumnIndex() > this.getUpperLeftColumnIndex());
                    
    }
    
    
    /**
     * Answer my upperLeftRowIndex
     * @return int
     */
    protected int getUpperLeftRowIndex() {
        return upperLeftRowIndex;
    }

    /**
     * Set my upperLeftRowIndex
     * @param upperLeftRowIndex int
     */
    protected void setUpperLeftRowIndex(int upperLeftRowIndex) {
        this.upperLeftRowIndex = upperLeftRowIndex;
    }

    /**
     * Answer my lowerLeftColumnIndex
     * @return int
     */
    protected int getLowerLeftColumnIndex() {
        return lowerLeftColumnIndex;
    }

    /**
     * Set my lowerLeftColumnIndex
     * @param lowerLeftColumnIndex int
     */
    protected void setLowerLeftColumnIndex(int lowerLeftColumnIndex) {
        this.lowerLeftColumnIndex = lowerLeftColumnIndex;
    }

    /**
     * Answer my lowerRightRowIndex
     * @return int
     */
    protected int getLowerRightRowIndex() {
        return lowerRightRowIndex;
    }

    /**
     * Set my lowerRightRowIndex
     * @param lowerRightRowIndex int
     */
    protected void setLowerRightRowIndex(int lowerRightRowIndex) {
        this.lowerRightRowIndex = lowerRightRowIndex;
    }

    /**
     * Answer my upperRightColumnIndex
     * @return int
     */
    protected int getUpperRightColumnIndex() {
        return upperRightColumnIndex;
    }

    /**
     * Set my upperRightColumnIndex
     * @param upperRightColumnIndex int
     */
    protected void setUpperRightColumnIndex(int upperRightColumnIndex) {
        this.upperRightColumnIndex = upperRightColumnIndex;
    }

    /**
     * Answer my arraySize
     * @return int
     */
    protected int getArraySize() {
        return arraySize;
    }

    /**
     * Set my arraySize
     * @param arraySize int
     */
    protected void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }

    /**
     * Answer my upperLeftColumnIndex
     * @return int
     */
    protected int getUpperLeftColumnIndex() {
        return upperLeftColumnIndex;
    }

    /**
     * Set my upperLeftColumnIndex
     * @param upperLeftColumnIndex int
     */
    protected void setUpperLeftColumnIndex(int upperLeftColumnIndex) {
        this.upperLeftColumnIndex = upperLeftColumnIndex;
    }

    /**
     * Answer my lowerLeftRowIndex
     * @return int
     */
    protected int getLowerLeftRowIndex() {
        return lowerLeftRowIndex;
    }

    /**
     * Set my lowerLeftRowIndex
     * @param lowerLeftRowIndex int
     */
    protected void setLowerLeftRowIndex(int lowerLeftRowIndex) {
        this.lowerLeftRowIndex = lowerLeftRowIndex;
    }

    /**
     * Answer my lowerRightColumnIndex
     * @return int
     */
    protected int getLowerRightColumnIndex() {
        return lowerRightColumnIndex;
    }

    /**
     * Set my lowerRightColumnIndex
     * @param lowerRightColumnIndex int
     */
    protected void setLowerRightColumnIndex(int lowerRightColumnIndex) {
        this.lowerRightColumnIndex = lowerRightColumnIndex;
    }

    /**
     * Answer my upperRightRowIndex
     * @return int
     */
    protected int getUpperRightRowIndex() {
        return upperRightRowIndex;
    }

    /**
     * Set my upperRightRowIndex
     * @param upperRightRowIndex int
     */
    protected void setUpperRightRowIndex(int upperRightRowIndex) {
        this.upperRightRowIndex = upperRightRowIndex;
    }
    
    

}
