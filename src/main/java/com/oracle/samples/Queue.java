package com.oracle.samples;

/**
 *
 *
 */
public class Queue<V> {

    private LinkedNode<Integer,V> front;
    private LinkedNode<Integer,V> rear;
    private int size;
    
    /**
     * Answer an instance for the following arguments
     */
    public Queue() {
        
        super();
        this.setFront(null);
        this.setRear(null);
        this.setSize(0);
        
    }

    /**
     * Answer my front
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<Integer,V> getFront() {
        return front;
    }

    /**
     * Set my front
     * @param front LinkedNode<K,V>
     */
    protected void setFront(LinkedNode<Integer, V> front) {
        this.front = front;
    }

    /**
     * Answer my rear
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<Integer, V> getRear() {
        return rear;
    }

    /**
     * Set my rear
     * @param rear LinkedNode<K,V>
     */
    protected void setRear(LinkedNode<Integer, V> rear) {
        this.rear = rear;
    }

    /**
     * Answer my size
     * @return int
     */
    protected int getSize() {
        return size;
    }

    /**
     * Increment size
     * @return int
     */
    protected int incrementSize() {
        
        int tempValue;
        
        tempValue = this.getSize();
        tempValue++;
        this.setSize(tempValue);
        
        return tempValue;
        
    }
    
    /**
     * Decrement size
     * @return int
     */
    protected int decrementSize() {
        
        int tempValue;
        
        tempValue = this.getSize();
        tempValue--;
        this.setSize(tempValue);
        
        return tempValue;
        
    }
    
    
    /**
     * Set my size
     * @param size int
     */
    protected void setSize(int size) {
        this.size = size;
    }

    
    /**
     * Add an element to me
     * @param anObject V
     */
    public synchronized void add(V anObject) {
        
        LinkedNode<Integer, V>  tempNewNode;
        
        tempNewNode = new LinkedNode<Integer, V>(new Integer(anObject.hashCode()), anObject);
        this.basicAddElementToRear(tempNewNode);
        
        this.incrementSize();
        
        
    }

    /**
     * Add aNewNode to rear
     * @param aNewNode
     */
    protected void basicAddElementToRear(LinkedNode<Integer, V> aNewNode) {
        
        LinkedNode<Integer, V> tempCurrentNode;
        
        if (this .getRear() == null) {
            
            this.setRear(aNewNode);
            if (this.getFront() == null) {
                
                this.setFront(aNewNode);
                
            }
            
        }
        else {
            
            tempCurrentNode = this.getRear();
            tempCurrentNode.add(aNewNode);
            
            this.setRear(aNewNode);
            
        }
        
    }
    
    /**
     * Remove an element from me
     * @return V
     */
    public synchronized V remove() throws IllegalStateException {
        
        V                       tempResult = null;
        LinkedNode<Integer, V>  tempNode;
        
        tempNode = this.basicRemoveElementFromFront();
        tempResult = tempNode.getValue();
        
        return tempResult;
        
    }
    
    
    /**
     * Add aNewNode to rear
     * @param aNewNode
     */
    protected LinkedNode<Integer, V> basicRemoveElementFromFront() 
                throws IllegalStateException {
        
        LinkedNode<Integer, V> tempCurrentNode;
        
        this.validateNotEmpty();
        tempCurrentNode = this.getFront();
        
        this.setFront(tempCurrentNode.getLink());
        
        if (this .getFront() == null) {
            
            this.setRear(null);
            
        }
        
        return tempCurrentNode;
        
    }

    /**
     * Validate not empty
     */
    protected void validateNotEmpty() {
        
        if (this.isEmpty()) {
            
            throw new IllegalStateException("Queue empty");
        }
        
    }
    
    
    
   
    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    public boolean isEmpty() {
        
        return this.getFront() == null;
    }
    
    
}
