package com.cracking;

/**
 *
 *
 */
public class VisitedStack<T> extends Stack<T> {

    /**
     * Answer an instance for the following arguments
     */
    public VisitedStack() {
        super();
    }
    
    /**
     * Answer an element from the top of the stack
     * @return T
     */
    public LinkedNode<T,T> popNode() {
        
        LinkedNode<T,T>   tempResult;
        
        this.validateNotEmpty();
        tempResult = this.getTop();
        this.setTop(this.getTop().getNextNode());
        
        return tempResult;
        
    }
    
    /**
     * Answer my size
     * @return int
     */
    @Override
    public int size() {
        
        int tempResult = 0;
        
        if (!this.isEmpty()) {
            
            tempResult = this.getTop().sizeWithoutVisited();
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        
        return super.isEmpty() || this.getTop().sizeWithoutVisited() == 0;

    }
    
    /**
     * Answer myself as a new stack, but keeping my internal nodes so that they can be referenced anew
     * @return Stack<T> 
     */
    @Override
    public Stack<T> asNewStackWithExistingNodes() {
        
        VisitedStack<T> tempResult;
        
        tempResult = new VisitedStack<T>();
        tempResult.setTop(this.getTop());
        
        return tempResult;
        
    }
    
    

}
