package com.oracle.samples;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class LinkedList<K, V> {

    
    private LinkedNode<K,V> head;
    
    /**
     * Answer an instance for the following arguments
     */
    public LinkedList() {
        super();
    }

    /**
     * Answer my head
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K, V> getHead() {
        return head;
    }

    /**
     * Set my head
     * @param head LinkedNode<K,V>
     */
    protected void setHead(LinkedNode<K, V> head) {
        this.head = head;
    }

    
    
    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    public boolean isEmpty() {
        
        return this.getHead() == null;
    }
    
    
    /**
     * Add a node with aKey and aValue
     * @param aKey K
     * @param aValue V
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> add(K aKey, V aValue) {
        
        LinkedNode<K,V> tempResult;
        
        if (this.isEmpty()) {
            
            tempResult = new LinkedNode<K,V>(aKey, aValue);
            this.setHead(tempResult);
        }
        else {
            
            tempResult = this.getHead().getNodeWithKey(aKey);
            if (tempResult == null) {
                
                tempResult = new LinkedNode<K,V>(aKey, aValue);
                this.getHead().add(tempResult);
            }
        }
        
        return tempResult;
        
    }
    
    /**
     * Delete node that has aKey 
     * @param aKey K
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> delete(K aKey) {
        
        LinkedNode<K,V> tempDeletedNode = null;
        
        if (!this.isEmpty()) {
            
            if (this.getHead().hasKey(aKey)) {
                
                if (!this.getHead().isEmpty()) {
                    
                    this.setHead(this.getHead().getLink());
                }
                else {
                    
                    this.setHead(null);
                }
                
            }
            else {
                
                tempDeletedNode = this.getHead().getNodeWithKey(aKey);
                if (tempDeletedNode != null) {
                    
                    this.getHead().delete(tempDeletedNode);
                    
                }
            }
            
        }
        
        return tempDeletedNode;
        
    }
    
    /**
     * Answer a node with aKey or null
     * @param aKey K
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> getNodeFor(K aKey) {
        
        LinkedNode<K,V> tempResult = null;
        
        if (!this.isEmpty()) {
            
            tempResult = this.getHead().getNodeWithKey(aKey);
        }
    
        return tempResult;
        
    }
    
    /**
     * Dump contents
     * @return String
     * 
     */
    public String dumpContents() {
        
        StringBuilder tempBuilder = new StringBuilder();
        
        if (this.isEmpty()) {
            
            tempBuilder.append("LinkedList: empty" );
        }
        else {
            
            tempBuilder.append("LinkedList:");
            this.getHead().dumpContents(tempBuilder);
            
        }
        
        return tempBuilder.toString();
    }
    
    /**
     * Perform a reverse of the list
     */
    public void reverse() {
        
        List<LinkedNode<K,V>>   tempNodes = new ArrayList<LinkedNode<K,V>>();
        LinkedNode<K,V>         tempCurrent;
        LinkedNode<K,V>         tempPrevious;
        
        tempCurrent = this.getHead();
        
        while (tempCurrent != null) {
            
            tempNodes.add(tempCurrent);
            tempCurrent = tempCurrent.getLink();
        }
        
        if (!tempNodes.isEmpty()) {
            
            tempCurrent = this.getAndRemoveNextNode(tempNodes);
            
            this.setHead(tempCurrent);
            tempPrevious = tempCurrent;
            
            while (!tempNodes.isEmpty()) {
                
                tempCurrent = getAndRemoveNextNode(tempNodes);
                tempPrevious.setLink(tempCurrent);
                tempPrevious = tempCurrent;
                
            }
            
        }
        
        if (tempCurrent != null) {
            
            tempCurrent.setLink(null);
        }
        
         
    }

    /**
     * @param tempNodes
     * @return
     */
    protected LinkedNode<K, V> getAndRemoveNextNode(List<LinkedNode<K, V>> tempNodes) {
        
        LinkedNode<K, V> tempCurrent;
        
        tempCurrent = tempNodes.get(tempNodes.size() - 1);
        tempNodes.remove(tempCurrent);
        
        return tempCurrent;
        
    }
    
    
}
