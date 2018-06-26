package com.cracking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 *
 */
public class Vertex<T> {

    private T data;
    private List<Vertex<T>> children;
    private boolean visited;
    
    
    /**
     * Answer an instance for the following arguments
     * @param aData T
     */
    public Vertex(T aData) {
        
        super();
        this.setData(aData);
        this.setChildren(new ArrayList<Vertex<T>>());
        this.setVisited(false);
        
    }


    /**
     * Answer my data
     * @return T
     */
    protected T getData() {
        return data;
    }


    /**
     * Set my data
     * @param data T
     */
    public void setData(T data) {
        this.data = data;
    }


    /**
     * Add aChild to me
     * @param aChild GraphNode<T>
     */
    public void addChild(Vertex<T> aChild) {
        
        this.getChildren().add(aChild);
        
    }
    
    
    /**
     * Answer my children
     * @return List<GraphNode<T>>
     */
    public List<Vertex<T>> getChildren() {
        return children;
    }


    /**
     * Set my children
     * @param children List<GraphNode<T>>
     */
    protected void setChildren(List<Vertex<T>> children) {
        this.children = children;
    }


    /**
     * Answer my visited
     * @return boolean
     */
    public boolean isVisited() {
        return visited;
    }


    /**
     * Set my visited
     * @param visited boolean
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    
    /**
     * Answer my string representation
     * @return String
     */
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempBuilder.append("data: ");
        tempBuilder.append((this.getData() != null) ? this.getData().toString() : "null");
        
        return tempBuilder.toString();
        
    }
    
    /**
     * Answer whether or not I have aData
     * @param aData T
     * @return boolean
     */
    public boolean hasData(T aData) {
        
        return this.getData() != null
                && this.getData().equals(aData);
        
    }
    
    
    /**
     * Answer the vertex that has aData. Either myself or one of my ancestors
     * @param aData T
     * @return Vertex<T>
     */
    public Vertex<T> getVertexWithData(T aData) {
        
        Vertex<T>           tempResult = null;
        Vertex<T>           tempCurrent;
        Iterator<Vertex<T>> tempChildren;
        
        if (this.hasData(aData))  {
            
            tempResult = this;
        }
        else {
            
            tempChildren = this.getChildren().iterator();
            while (tempResult == null && tempChildren.hasNext()) {
                
                tempCurrent = tempChildren.next();
                tempResult = tempCurrent.getVertexWithData(aData);
            }
            
        }
        
        return tempResult;
        
    }
    
}
