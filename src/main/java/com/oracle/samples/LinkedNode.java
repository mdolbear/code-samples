package com.oracle.samples;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class LinkedNode<K,V> {

    private LinkedNode<K,V> link;
    private V value;
    private K key;
    
    /**
     * Answer an instance for the following arguments
     */
    public LinkedNode() {
        
        super();
        this.setValue(null);
        this.setLink(null);
    }
    
    /**
     * Answer an instance of me for the arguments below
     * @param aKey K
     * @param aValue V
     */
    public LinkedNode(K aKey, V aValue) {
        
        this();
        this.setKey(aKey);
        this.setValue(aValue);
        
    }
    
    
    /**
     * Answer my link
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K,V> getLink() {
        return link;
    }
    
    /**
     * Set my link
     * @param link LinkedNode<K,V>
     */
    protected void setLink(LinkedNode<K,V> link) {
        this.link = link;
    }
    
    /**
     * Answer my value
     * @return V
     */
    protected V getValue() {
        return value;
    }
    
    /**
     * Set my value
     * @param value V
     */
    protected void setValue(V value) {
        this.value = value;
    }
    

    /**
     * Answer my key
     * @return K
     */
    protected K getKey() {
        return key;
    }


    /**
     * Set my key
     * @param key K
     */
    protected void setKey(K key) {
        this.key = key;
    }


    /**
     * Add aLinkedNode to me
     * @param aLinkedNode LinkedNode<V>
     */
    public void add(LinkedNode<K,V> aLinkedNode) {
        
        LinkedNode<K,V>   tempEmptyLink;
        
        tempEmptyLink = this.getEmptyLink();        
        tempEmptyLink.setLink(aLinkedNode);
        
    }
    

    /**
     * Delete aLinkedNode from me. Answer true if it was deleted
     * @param aTarget LinkedNode<K,V>
     * @return boolean
     */
    public boolean delete(LinkedNode<K,V> aTarget) {
        
        LinkedNode<K,V> tempPred;
        
        tempPred = this.getPredecessorOf(aTarget);
        if (tempPred != null) {
            
            tempPred.setLink(aTarget.getLink());
        }
        
        
        return tempPred != null;
    }
    
    /**
     * Answer the predecessor node of aLinkedNode or null
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K,V> getPredecessorOf(LinkedNode<K,V> aLinkedNode) {
        
        LinkedNode<K,V> tempResult = null;
        LinkedNode<K,V> tempCurrent;
        
        tempCurrent = this;
        
        while (tempCurrent.getLink() != null &&
                    !tempCurrent.getLink().hasKey(aLinkedNode.getKey())) {
            
            tempCurrent = tempCurrent.getLink();
        }
        
        if (tempCurrent.getLink() != null) {
            
            tempResult = tempCurrent;
        }
        
        return tempResult;
        
    }
    /**
     * Answer a node with aValue
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> getNodeWithValue(V aValue) {
        
        LinkedNode<K,V> tempCurrent;
        LinkedNode<K,V> tempResult = null;
        
        tempCurrent = this;
        while (!tempCurrent.isEmpty() && 
                !tempCurrent.hasValue(aValue)) {
            
            tempCurrent = tempCurrent.getLink();
        }
        
        if (tempCurrent.hasValue(aValue)) {
            
            tempResult = tempCurrent;
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer a node with aKey
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> getNodeWithKey(K aKey) {
        
        LinkedNode<K,V> tempCurrent;
        LinkedNode<K,V> tempResult = null;
        
        tempCurrent = this;
        while (!tempCurrent.isEmpty() && 
                !tempCurrent.hasKey(aKey)) {
            
            tempCurrent = tempCurrent.getLink();
        }
        
        if (tempCurrent.hasKey(aKey)) {
            
            tempResult = tempCurrent;
        }
        
        return tempResult;
        
    }
    
  
    /**
     * Answer my non-empty nodes
     * @return LinkedNode<K,V>
     */
    public List<LinkedNode<K,V>> getNonEmptyNodes() {
        
        List<LinkedNode<K,V>> tempResult = new ArrayList<LinkedNode<K,V>>();
        LinkedNode<K,V> tempCurrent;
        
        tempCurrent = this;
        while (tempCurrent != null) {
            
            if (tempCurrent.isPopulated()) {
                
                tempResult.add(tempCurrent);
            }
            
            tempCurrent = tempCurrent.getLink();
        }
        
        
        return tempResult;
        
    }
    
    /**
     * Answer whether or not I am populated - I have a defined key and value
     * @return boolean
     */
    protected boolean isPopulated() {
        

        return this.getKey() != null && 
                        this.getValue() != null;
        
    }
    
    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    protected boolean isEmpty() {
        
        return this.getLink() == null;
    }
    
    
    /**
     * Answer an empty link
     * @return LinkedNode<V>
     */
    protected LinkedNode<K,V> getEmptyLink() {
        
        LinkedNode<K,V> tempCurrent;
        
        tempCurrent = this;
        while (!tempCurrent.isEmpty()) {
            
            tempCurrent = tempCurrent.getLink();
        }
        
        
        return tempCurrent;
    }
    
    /**
     * Answer whether or not my value is equal to aValue
     * @param aValue V
     * @return boolean
     */
    protected boolean hasValue(V aValue) {
        
        return this.getValue() != null &&
                this.getValue().equals(aValue);
    }
  
    /**
     * Answer whether or not my key is equal to aKey
     * @param aValue K
     * @return boolean
     */
    protected boolean hasKey(K aKey) {
        
        return this.getKey() != null && 
                this.getKey().equals(aKey);
    }
    
    /**
     * Dump contents
     * @return String
     * 
     */
    public String dumpContents(StringBuilder aBuilder) {
        
        LinkedNode<K,V>     tempCurrent;
        
        tempCurrent = this;
        aBuilder.append(tempCurrent.toString());
        
        while (!tempCurrent.isEmpty()) {
            
            tempCurrent = tempCurrent.getLink();
            aBuilder.append(tempCurrent.toString());
        }
        
        
        return aBuilder.toString();
        
    }
    
    
    /**
     * Answer my string representation
     * @return String
     */
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        String          tempCurrent;
        
        tempCurrent = (this.getKey() != null) ? " key: " + this.getKey() + " " : " key: null";
        tempBuilder.append(tempCurrent);
        
        tempCurrent = (this.getValue() != null) ? " value: " + this.getValue() + " " : " value: null";
        tempBuilder.append(tempCurrent);
        
        return tempBuilder.toString();
        
    }
    
}
