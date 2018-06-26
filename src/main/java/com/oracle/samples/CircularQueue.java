package com.oracle.samples;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class CircularQueue<E> {

    private int             front;
    private int             rear;
    private List<E>         data;
    private int             size;
    
    /**
     * Answer an instance for the following arguments
     * @param aSize int
     * @param anEmptyElement E
     */
    public CircularQueue(int aSize,
                         E anEmptyElement) {
        
       super();
       this.initializeData(aSize, anEmptyElement);
       this.setFront(0);
       this.setRear(1);
       this.setSize(aSize + 1); //We always waste a spot
       
       
    }

    /**
     * Initialize data
     * @param aSize
     */
    protected void initializeData(int aSize,
                                  E anEmptyElement) {
        
        this.setData(new ArrayList<E>(aSize + 1)); //We always waste a spot
        for (int i = 0; i < aSize + 1; i++) {
            
            this.getData().add(anEmptyElement);
        }
        
    }

    /**
     * Answer my front
     * @return int
     */
    protected int getFront() {
        return front;
    }

    /**
     * Set my front
     * @param front int
     */
    protected void setFront(int front) {
        this.front = front;
    }

    /**
     * Answer my rear
     * @return int
     */
    protected int getRear() {
        return rear;
    }

    /**
     * Set my rear
     * @param rear int
     */
    protected void setRear(int rear) {
        this.rear = rear;
    }

    /**
     * Answer my data
     * @return List<E>
     */
    protected List<E> getData() {
        return data;
    }

    /**
     * Set my data
     * @param data List<E>
     */
    protected void setData(List<E> data) {
        this.data = data;
    }
    
    
    /**
     * Answer whether or not I am full
     * @return boolean
     */
    public boolean isFull() {
        
        return this.getRear() == this.getFront();
    }
    
    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    public boolean isEmpty() {
        
        return this.isEmpty(this.incrementCircularIndex(this.getFront()));
        
    }
    
    /**
     * Answer whether or not I am empty
     * @param anIndex int
     * @return boolean
     */
    protected boolean isEmpty(int anIndex) {
        
        return anIndex == this.getRear();
        
    }
    
    /**
     * Answer the element from the front based on anIndex
     * without adjusting indexes
     * @param anIndex int
     * @return E
     */
    public E peek(int anIndex) {
        
        E   tempResult = null;
        int tempCurrentIdx;
        int tempCurrentCount;
        
        tempCurrentCount = anIndex;
        tempCurrentIdx = this.getFront();
        
        do  {
            
            tempCurrentCount--;
            tempCurrentIdx = this.incrementCircularIndex(tempCurrentIdx, 1);
            
        } while (tempCurrentCount > 0 && !this.isEmpty(tempCurrentIdx));
        
        if (tempCurrentCount == 0) {
            
            tempResult = this.getData().get(tempCurrentIdx);
        }
        
        return tempResult;
        
    }
    
    
    /**
     * Answer my front
     * @return E
     */
    public E get() {
        
        E   tempValue = null;
        int tempIdx;
        
        if (!this.isEmpty()) {
            
            tempIdx = this.incrementFront();
            tempValue = this.getData().get(tempIdx);
        }
        
        return tempValue;
    }
    
    /**
     * Answer my rear
     * @return E
     */
    public E put(E aValue) {
        
        E   tempValue = null;
        int tempIdx;
        
        if (!this.isFull()) {
            
           tempValue = aValue;
           tempIdx = this.getRear();
           this.getData().set(tempIdx, tempValue);
           this.incrementRear();
           
        }
        
        return tempValue;
        
    }
    
    /**
     * Answer my rear
     * @return E
     */
    public E putIgnoreFull(E aValue) {
        
        E   tempValue = null;
        int tempIdx;
        
        if (this.isFull()) {
            
            this.incrementFront();
        }
            
        tempValue = aValue;
        tempIdx = this.getRear();
        this.getData().set(tempIdx, tempValue);
        this.incrementRear();
           

        return tempValue;
        
    }
    
    /**
     * Increment rear. Answer my new value
     * @return int
     */
    protected int incrementRear() {
        
        int tempValue;
        
        tempValue = this.getRear();
        tempValue = this.incrementCircularIndex(tempValue);
        this.setRear(tempValue);
        
        return tempValue;
    }

    /**
     * Increment a circular index
     * @param aValue
     * @return int
     */
    protected int incrementCircularIndex(int aValue) {
        
        return this.incrementCircularIndex(aValue, 1);
        
    }
    
    /**
     * Increment a circular index
     * @param aValue
     * @return int
     */
    protected int incrementCircularIndex(int aValue, 
                                         int anIncrement) {
        
        return (aValue + anIncrement) % this.getSize();
        
    }
    
    /**
     * Increment front. Answer my new value
     * @return int
     */
    protected int incrementFront() {
        
        int tempValue;
        
        tempValue = this.getFront();
        tempValue = incrementCircularIndex(tempValue);
        this.setFront(tempValue);
        
        return tempValue;
    }

    
    

    /**
     * Answer my size
     * @return int
     */
    protected int getSize() {
        return size;
    }

    /**
     * Set my size
     * @param size int
     */
    protected void setSize(int size) {
        this.size = size;
    }
    

    
}
