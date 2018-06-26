package com.cracking;

/**
 * This is an implementation of a queue using two stacks
 *
 */
public class QueueOfStacks<T> {

    private Stack<T> frontStack;
    private Stack<T> rearStack;
    
    /**
     * Answer an instance for the following arguments
     */
    public QueueOfStacks() {
        
       super();
       this.setFrontStack(new VisitedStack<T>());
       this.setRearStack(new VisitedStack<T>());
       
    }
    
    /**
     * Re-initialize front stack in reverse order from rear stack
     */
    protected void reinitializeFrontStackFromRearReversed() {
        
        VisitedStack<T>            tempNewRear;
        LinkedNode<T,T>            tempCurrent;
        
        tempNewRear = (VisitedStack<T>)this.getRearStack().asNewStackWithExistingNodes();
        this.getFrontStack().clear();
        while (!tempNewRear.isEmpty()) {
           
            tempCurrent = tempNewRear.popNode();
            if (!tempCurrent.isVisited()) {
                
                this.getFrontStack().push(tempCurrent.getKey());
                
            }
            
        }
        
    }
    
    /**
     * Add anElement to the end of the queue
     * @param anElement T
     */
    public void add(T anElement) {
        
        this.getRearStack().push(anElement);
        this.reinitializeFrontStackFromRearReversed();
        
    }
    
    /**
     * Remove an element from my front and answer the associated data
     * @return T
     */
    public T remove() {
        
        T                          tempResult;
        LinkedNode<T,T>            tempCurrent;
        LinkedNode<T,T>            tempDeletedNode;
        
        tempCurrent = ((VisitedStack<T>)this.getFrontStack()).popNode();
        tempDeletedNode = this.getRearStack().getTop().deleteLastNode();
        if (tempDeletedNode == null) {
            
            this.getRearStack().clear();
        }
        
        tempResult = tempCurrent.getKey();
        
        return tempResult;
        
    }
    
    /**
     * Answer my size
     * @return int
     */
    public int size() {
        
        return this.getFrontStack().size(); //Note that we need to screen out visited nodes here
        
    }
    
    /**
     * Answer the data from my front element
     * @return T
     */
    public T peek() {
        
        T   tempResult;
        
        tempResult = this.getFrontStack().peek();
        
        return tempResult;
    }
    
    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    public boolean isEmpty() {
        
        return this.getFrontStack().isEmpty(); //Again need to screen out visited nodes
    }

    /**
     * Answer my frontStack
     * @return Stack<T>
     */
    protected Stack<T> getFrontStack() {
        return frontStack;
    }

    /**
     * Set my frontStack
     * @param frontStack Stack<T>
     */
    protected void setFrontStack(Stack<T> frontStack) {
        this.frontStack = frontStack;
    }

    /**
     * Answer my rearStack
     * @return Stack<T>
     */
    protected Stack<T> getRearStack() {
        return rearStack;
    }

    /**
     * Set my rearStack
     * @param rearStack Stack<T>
     */
    protected void setRearStack(Stack<T> rearStack) {
        this.rearStack = rearStack;
    }
    
    

}
