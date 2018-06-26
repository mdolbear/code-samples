package com.cracking;

import java.util.Comparator;

/**
 *
 *
 */
public class SortedStack<T> {

    private Stack<T> sortedStack;
    private Stack<T> temporaryStack;
    private Comparator<T> comparator;
    
    
    /**
     * Answer an instance for the following arguments
     */
    public SortedStack(Comparator<T> aComparator) {
        
        super();
        this.setSortedStack(new Stack<T>());
        this.setTemporaryStack(new Stack<T>());
        this.setComparator(aComparator);
        
    }
    
    /**
     * Push anElement to the stack
     * @param anElement T
     */
    public void push(T anElement) {
        
        this.performSortedPush(anElement);
    }
    
    /**
     * Answer an element from the top of the stack
     * @return T
     */
    public T pop() {
        
        return this.getSortedStack().pop();
    }
    
    /**
     * Peek the data from my top element if it exists
     * @return T
     */
    public T peek() {
        
        return this.getSortedStack().peek();
    }
    
    /**
     * Perform sorted push of anElement
     * @param anElement T
     */
    protected void performSortedPush(T anElement) {
        
        this.moveSortedStackToTemporaryWhileElementIsGreaterThanTop(anElement);        
        this.getSortedStack().push(anElement);        
        this.refillSortedStackFromTemporaryStack();
        
        
    }

    /**
     * Refill the sorted stack from the temporary stack with its sorted elements
     */
    protected void refillSortedStackFromTemporaryStack() {
        
        while (!this.getTemporaryStack().isEmpty()) {
            
            this.getSortedStack().push(this.getTemporaryStack().pop());
        }
        
    }

    /**
     * Store the sorted elements from the sorted stack to the temporary stack while the incoming
     * element is greater that the sorted stack
     * @param anElement
     */
    protected void moveSortedStackToTemporaryWhileElementIsGreaterThanTop(T anElement) {
        
        T tempCurrentTop;
        
        if (!this.getSortedStack().isEmpty()) {
            
            tempCurrentTop = this.getSortedStack().peek();
            while (!this.getSortedStack().isEmpty() &&
                        this.isIncomingElementIsGreaterThanTopOfStack(anElement, tempCurrentTop)) {
                
                this.getTemporaryStack().push(this.getSortedStack().pop());
                if (!this.getSortedStack().isEmpty()) {
                    
                    tempCurrentTop = this.getSortedStack().peek();
                }
            }
            
            
            
        }
        
        
    }

    /**
     * @param anElement
     * @param aCurrentTop
     * @return
     */
    protected boolean isIncomingElementIsGreaterThanTopOfStack(T anElement,
                                                          T aCurrentTop) {
        
        return this.getComparator().compare(anElement, aCurrentTop) > 0;
        
    }


    /**
     * Answer my sortedStack
     * @return Stack<T>
     */
    protected Stack<T> getSortedStack() {
        return sortedStack;
    }


    /**
     * Set my sortedStack
     * @param sortedStack Stack<T>
     */
    protected void setSortedStack(Stack<T> sortedStack) {
        this.sortedStack = sortedStack;
    }


    /**
     * Answer my temporaryStack
     * @return Stack<T>
     */
    protected Stack<T> getTemporaryStack() {
        return temporaryStack;
    }


    /**
     * Set my temporaryStack
     * @param temporaryStack Stack<T>
     */
    protected void setTemporaryStack(Stack<T> temporaryStack) {
        this.temporaryStack = temporaryStack;
    }


    /**
     * Answer my comparator
     * @return Comparator<T>
     */
    protected Comparator<T> getComparator() {
        return comparator;
    }


    /**
     * Set my comparator
     * @param comparator Comparator<T>
     */
    protected void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    
    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    public boolean isEmpty() {
        
        return this.getSortedStack().isEmpty();
        
    }
    
    /**
     * Answer my size
     * @return int
     */
    public int size() {
        
        return this.getSortedStack().size();
    }
    
    /**
     * Answer the current stack contents
     * @return String
     */
    public String toString() {
        
        StringBuilder tempBuilder = new StringBuilder();
        
        this.getSortedStack().getTop().dumpListTo(tempBuilder);
        
        return tempBuilder.toString();
        
    }
    
    
}
