package com.cracking;

import java.util.Comparator;

/**
 *
 *
 */
public class MinStack<T> {

    
    private Stack<T> dataStack;
    private Stack<T> minimumStack;
    private Comparator<T> comparator;
    
    /**
     * Answer an instance for the following arguments
     */
    public MinStack(Comparator<T> aComparator) {
        
        super();
        this.setDataStack(new Stack<T>());
        this.setMinimumStack(new Stack<T>());
        this.setComparator(aComparator);
    }

    /**
     * Answer my dataStack
     * @return Stack<T>
     */
    protected Stack<T> getDataStack() {
        return dataStack;
    }

    /**
     * Set my dataStack
     * @param dataStack Stack<T>
     */
    protected void setDataStack(Stack<T> dataStack) {
        this.dataStack = dataStack;
    }

    /**
     * Answer my minimumStack
     * @return Stack<T>
     */
    protected Stack<T> getMinimumStack() {
        return minimumStack;
    }

    /**
     * Set my minimumStack
     * @param minimumStack Stack<T>
     */
    protected void setMinimumStack(Stack<T> minimumStack) {
        this.minimumStack = minimumStack;
    }
    
    
    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    public boolean isEmpty() {
        
        return this.getDataStack().isEmpty();
    }

    /**
     * Push anElement to the stack
     * @param anElement T
     */
    public void push(T anElement) {
        
        this.getDataStack().push(anElement);
        this.pushIfLessThanMin(anElement);
        
    }
    
    /**
     * Push if anElement is less than the current minimum
     * @param anElement
     */
    private void pushIfLessThanMin(T anElement) {
        
        if (this.getMinimumStack().isEmpty() ||
                this.getComparator().compare(anElement, this.getMinimumStack().peek()) < 0) {
            
            this.getMinimumStack().push(anElement);
            
        }
        
    }
    
    /**
     * Answer an element from the top of the stack
     * @return T
     */
    public T pop() {
        
        T   tempResult;
        
        tempResult = this.getDataStack().pop();
        this.popIfEqualsTop(tempResult);
        
        return tempResult;
    }
    
    /**
     * Answer my current minimum
     * @return T
     */
    public T getMinimum() {
        
        return this.getMinimumStack().peek();
        
        
    }

    /**
     * Pop if anElment is equal to the current top of the minimum stack
     * @param aResult
     */
    private void popIfEqualsTop(T aResult) {
        
        if (this.getComparator().compare(this.getMinimumStack().peek(), aResult) == 0) {
            
            this.getMinimumStack().pop();
        }
        
    }

    /**
     * Answer my size
     * @return int
     */
    public int size() {
        
        return this.getDataStack().size();
    }
    
    /**
     * Peek the data from my top element if it exists
     * @return T
     */
    public T peek() {
        
        return this.getDataStack().peek();
        
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
    
    
    
    
}
