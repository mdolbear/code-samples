package com.oracle.samples;

import java.lang.reflect.Array;

/**
 *
 *
 */
public class Stack<T> {

    private T[] data;
    private int top;
    private int size;
    
    /**
     * Answer an instance for the following arguments
     * @param aSize int
     */
    @SuppressWarnings("unchecked")
    public Stack(int aSize) {
        
        super();       
        this.setTop(-1);
        this.setSize(aSize);
        this.setData((T[])Array.newInstance(Object.class, aSize));
        
    }
    
    /**
     * Push anElement
     * @param anElement T
     */
    public void push(T anElement) {
        
        int tempTop;
        
        this.validateNotFull();
        tempTop = this.incrementTop();
        this.getData()[tempTop] = anElement;      
       
    }
    
    /**
     * Pop an element
     * @return T
     */
    public T pop() {
        
        T   tempResult;
        
        this.validateNotEmpty();
        tempResult = this.getData()[this.getTop()];
        this.decrementTop();
        
        return tempResult;
        
    }
    
    /**
     * Thrown an exception if I am full
     */
    protected void validateNotFull() {
        
        if (this.isFull()) {
            
            throw new IllegalStateException("Stack is full");
        }
        
    }
    
    /**
     * Thrown an exception if I am empty
     */
    protected void validateNotEmpty() {
        
        if (this.isEmpty()) {
            
            throw new IllegalStateException("Stack is empty");
        }
        
    }


    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    public boolean isEmpty() {
        
        return this.getTop() == -1;
    }
    
    /**
     * Answer whether or not I am full
     * @return boolean
     */
    public boolean isFull() {
        
        return this.getTop() == (this.getSize() - 1);
    }

    /**
     * Answer my data
     * @return T[]
     */
    protected T[] getData() {
        return data;
    }

    /**
     * Set my data
     * @param data T[]
     */
    protected void setData(T[] data) {
        this.data = data;
    }

    
    /**
     * Increment top
     * @return int
     */
    protected int incrementTop() {
        
        int tempResult;
        
        tempResult = this.getTop();
        tempResult++;
        this.setTop(tempResult);
        
        return tempResult;
    }
    
    /**
     * Decrement top
     */
    protected int decrementTop() {
        
        int tempResult;
        
        tempResult = this.getTop();
        tempResult--;
        this.setTop(tempResult);
        
        return tempResult;
        
    }
    
    
    /**
     * Answer my top
     * @return int
     */
    protected int getTop() {
        return top;
    }

    /**
     * Set my top
     * @param top int
     */
    protected void setTop(int top) {
        this.top = top;
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
