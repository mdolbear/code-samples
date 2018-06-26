package com.cracking;

/**
 *
 *
 */
public class Stack<T> {

    private LinkedNode<T,T> top;
    
    /**
     * Answer an instance for the following arguments
     */
    public Stack() {
        
        super();
        this.clear();
    }

    /**
     * Clear my contents
     */
    public void clear() {
        
        this.setTop(null);
    }
    
    /**
     * Push anElement to the stack
     * @param anElement T
     */
    public void push(T anElement) {
        
        LinkedNode<T, T> tempNewNode;
        
        tempNewNode = this.createNewNodeFor(anElement);
        tempNewNode.setNextNode(this.getTop());
        this.setTop(tempNewNode);
        
    }

    /**
     * Create new node for anElement
     * @param anElement
     * @return LinkedNode<T, T>
     */
    public LinkedNode<T, T> createNewNodeFor(T anElement) {
        
        return new LinkedNode<T,T>(anElement, anElement, null);
    }
    
    /**
     * Answer an element from the top of the stack
     * @return T
     */
    public T pop() {
        
        T   tempResult;
        
        this.validateNotEmpty();
        tempResult = this.getTop().getKey();
        this.setTop(this.getTop().getNextNode());
        
        return tempResult;
        
    }
    
    /**
     * Peek the data from my top element if it exists
     * @return T
     */
    public T peek() {
        
        T   tempResult;
        
        this.validateNotEmpty();
        tempResult = this.getTop().getKey();
        
        return tempResult;
        
    }
    
    /**
     * Validate not empty
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
        
        return this.getTop() == null;
    }

    /**
     * Answer my top
     * @return LinkedNode<T,T>
     */
    protected LinkedNode<T, T> getTop() {
        return top;
    }

    /**
     * Set my top
     * @param top LinkedNode<T,T>
     */
    protected void setTop(LinkedNode<T, T> top) {
        this.top = top;
    }
    
    
    /**
     * Answer my size
     * @return int
     */
    public int size() {
        
        int tempResult = 0;
        
        if (!this.isEmpty()) {
            
            tempResult = this.getTop().size();
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer myself as a new stack, but keeping my internal nodes so that they can be referenced anew
     * @return Stack<T> 
     */
    public Stack<T> asNewStackWithExistingNodes() {
        
        Stack<T> tempResult;
        
        tempResult = new Stack<T>();
        tempResult.setTop(this.getTop());
        
        return tempResult;
        
    }
    
    
}
