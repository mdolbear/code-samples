package com.cracking;

import java.lang.reflect.Array;
import java.util.Arrays;


/**
 *
 *
 */
public class SetOfStacks<T> {

    
    private Stack<T>[] internalStacks;
    private int thresholdPerStack;
    private int stackIndex;
    
    //Constants
    protected int SIZE_INCREASE_FACTOR = 10;
    
    /**
     * Answer an instance for the following arguments
     */
    public SetOfStacks(int aThreasholdPerStack) {
        
        super();
        this.setThresholdPerStack(aThreasholdPerStack);
        this.initializeFirstStack();
    }
    
    /**
     * Initialize first stack
     */
    @SuppressWarnings("unchecked")
    protected void initializeFirstStack() {
        
        this.setStackIndex(0);
        this.setInternalStacks((Stack<T>[])Array.newInstance(Stack.class, 1));
        this.getInternalStacks()[0] = new Stack<T>();
        
    }
    
    /**
     * Push anElement to the stack
     * @param anElement T
     */
    public void push(T anElement) {
        
        this.validateSpaceOnExistingStackOrCreateNewStack();       
        this.pushElementToCurrentStack(anElement);
        
    }

    /**
     * Make sure we have enough space on the current stack or cause a new one to be created.
     */
    protected void validateSpaceOnExistingStackOrCreateNewStack() {
        
        if (!this.isCurrentStackBelowThreshold()) {
            
            this.createNewStackAndAdjustStackIndex();
        }
        
    }

    /**
     * Push element to current stack
     * @param anElement T
     */
    protected void pushElementToCurrentStack(T anElement) {
        
        this.getInternalStacks()[this.getStackIndex()].push(anElement);
        
    }
    
    
    /**
     * Pop an element from the top of the current stack. Throw an exception if we are empty
     * @return T
     */
    public T pop() {
        
        
        this.validateCurrentStackHasElementsOrDecrementIndex();
        
        //Ignore whether or not we decreased, if we did, we have more elements. If not, let the
        //current stack throw an exception
        return this.getInternalStacks()[this.getStackIndex()].pop();
        
    }
    
    /**
     * Peek an element from the top of the current stack. Throw an exception if we are empty
     * @return T
     */
    public T peek() {
        
        
        this.validateCurrentStackHasElementsOrDecrementIndex();
        
        //Ignore whether or not we decreased, if we did, we have more elements. If not, let the
        //current stack throw an exception
        return this.getInternalStacks()[this.getStackIndex()].peek();
        
    }
    
    /**
     * Answer whether or not we are empty
     * @return boolean
     */
    public boolean isEmpty() {
        
        return (this.getStackIndex() == 0) &&
                    this.isCurrentStackEmpty();
    }
    
    /**
     * Answer my cumulative size
     * @return int
     */
    public int size() {
        
        int tempResult = 0;
        
        for (int i = 0; i < this.getInternalStacks().length; i++) {
            
            if (this.getInternalStacks()[i] != null) {
                
                tempResult +=  this.getInternalStacks()[i].size();
            }
        }
        
        return tempResult;
        
    }
    
    

    /**
     * Validate that we have more elements in the remaining stack, or decrement the current stack index. Don't decrement if we 
     * can't go any lower
     */
    protected void validateCurrentStackHasElementsOrDecrementIndex() {
        
        if (this.isCurrentStackEmpty()) {
            
            this.safelyDecreaseStackIndex();
        }
        
    }
    
    /**
     * Decrease index if possible. Answer true or false based on whether or not it could be done
     * @return boolean
     */
    protected boolean safelyDecreaseStackIndex() {
        
        boolean tempResult;
        
        tempResult = this.getStackIndex() > 0;
        if (tempResult) {
            
            this.deccrementStackIndex();
        }
        
        return tempResult;
        
    }
    
    
    /**
     * Create new stack and adjust stack index, if this is required. At the very least,
     * this methods will result in the stack index being incremented
     * 
     */
    protected void createNewStackAndAdjustStackIndex() {
        
        int tempNewStackIndex;
        
        tempNewStackIndex = this.incrementStackIndex();
        if (tempNewStackIndex >= this.getNumberOfInternalStacks()) {
            
            this.increaseMySizeBy(SIZE_INCREASE_FACTOR);
        }
        
        if (this.getInternalStacks()[tempNewStackIndex] == null) {
            
            this.getInternalStacks()[tempNewStackIndex] = new Stack<T>();
        }
        
    }

    /**
     * Increment stack index
     * @return int
     */
    protected int incrementStackIndex() {
        
        int tempResult;
        
        tempResult = this.getStackIndex() + 1;
        this.setStackIndex(tempResult);
        
        return tempResult;
    }
    
    /**
     * Decrement stack index
     * @return int
     */
    protected int deccrementStackIndex() {
        
        int tempResult;
        
        tempResult = this.getStackIndex() - 1;
        this.setStackIndex(tempResult);
        
        return tempResult;
    }
    
    /**
     * Increase my size by aValue
     * @param aValue int
     */
    protected void increaseMySizeBy(int aValue) {
        
        Stack<T>[]    tempNewArray;
        
        tempNewArray = Arrays.copyOf(this.getInternalStacks(), 
                                     this.getNumberOfInternalStacks() + aValue);
        this.setInternalStacks(tempNewArray);
        
    }
    
    /**
     * Answer my current number of internal stacks
     * @return int
     */
    protected int getNumberOfInternalStacks() {
        
        return this.getInternalStacks().length;
    }
    
    
    /**
     * Answer whether or not the current stack is below the threshold
     * @return boolean
     */
    protected boolean isCurrentStackBelowThreshold() {
        
        return this.getInternalStacks()[this.getStackIndex()].size() < this.getThresholdPerStack();
    }
    
    /**
     * Answer whether or not the current stack is empty
     * @return boolean
     */
    protected boolean isCurrentStackEmpty() {
        
        return this.getInternalStacks()[this.getStackIndex()].isEmpty();
        
    }    
    
    /**
     * Answer my internalStacks
     * @return Stack<T>[]
     */
    protected Stack<T>[] getInternalStacks() {
        return internalStacks;
    }

    /**
     * Set my internalStacks
     * @param internalStacks Stack<T>[]
     */
    protected void setInternalStacks(Stack<T>[] internalStacks) {
        this.internalStacks = internalStacks;
    }

    /**
     * Answer my thresholdPerStack
     * @return int
     */
    protected int getThresholdPerStack() {
        return thresholdPerStack;
    }

    /**
     * Set my thresholdPerStack
     * @param thresholdPerStack int
     */
    protected void setThresholdPerStack(int thresholdPerStack) {
        this.thresholdPerStack = thresholdPerStack;
    }

    /**
     * Answer my stackIndex
     * @return int
     */
    protected int getStackIndex() {
        return stackIndex;
    }

    /**
     * Set my stackIndex
     * @param stackIndex int
     */
    protected void setStackIndex(int stackIndex) {
        this.stackIndex = stackIndex;
    }
    
    

}
