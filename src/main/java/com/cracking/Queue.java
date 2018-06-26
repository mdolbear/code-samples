package com.cracking;

/**
 *
 *
 */
public class Queue<T> {

    private LinkedNode<T,T> front;
    private LinkedNode<T,T> rear;
    
    /**
     * Answer a default instance
     */
    public Queue() {
        
        super();
        this.setFront(null);
        this.setRear(null);
    }
    
    /**
     * Add anElement to the end of the queue
     * @param anElement T
     */
    public void add(T anElement) {
        
        LinkedNode<T,T> tempNewNode;
        
        tempNewNode = new LinkedNode<T,T>(anElement, anElement, null);
        
        if (this.getRear() != null) {
            
            this.getRear().setNextNode(tempNewNode);
        }        
        this.setRear(tempNewNode);
        
        if (this.getFront() == null) {
            
            this.setFront(this.getRear());
        }
        
    }
    
    /**
     * Remove an element from my front and answer the associated data
     * @return T
     */
    public T remove() {
        
        T   tempResult;
        
        this.validateNotEmpty();
        
        tempResult = this.getFront().getKey();
        this.setFront(this.getFront().getNextNode());
        
        if (this.getFront() == null) {
            
            this.setRear(null);
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer the data from my front element
     * @return T
     */
    public T peek() {
        
        T   tempResult;
        
        this.validateNotEmpty();        
        tempResult = this.getFront().getKey();
        
        return tempResult;
        
    }
    
    /**
     * Validate not empty
     * 
     */
    protected void validateNotEmpty() {
        
        if (this.isEmpty()) {
            
            throw new IllegalStateException("Queue is empty");
        }
    }
    
    
    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    public boolean isEmpty() {
        
        return this.getFront() == null;
    }
    
    
    /**
     * Answer my front
     * @return LinkedNode<T,T>
     */
    protected LinkedNode<T, T> getFront() {
        return front;
    }
    
    /**
     * Set my front
     * @param front LinkedNode<T,T>
     */
    protected void setFront(LinkedNode<T, T> front) {
        this.front = front;
    }
    
    /**
     * Answer my rear
     * @return LinkedNode<T,T>
     */
    protected LinkedNode<T, T> getRear() {
        return rear;
    }
    
    /**
     * Set my rear
     * @param rear LinkedNode<T,T>
     */
    protected void setRear(LinkedNode<T, T> rear) {
        this.rear = rear;
    }
    
    /**
     * Answer my size
     * @return int
     */
    public int size() {
        
        int tempResult = 0;
        
        if (!this.isEmpty()) {
            
            tempResult = this.getFront().size();
        }
        
        return tempResult;
        
    }
    
    
}
