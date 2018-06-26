package com.oracle.samples;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 *
 */
public class HashMap<K, V> {

    
    private LinkedNode<K,V>[]   nodes;
    private int size;
    
    /**
     * Answer an instance for the following arguments
     * @param aSize int
     */
    public HashMap(int aSize) {
        
        super();
        this.initialize(aSize);
        
    }


    /**
     * Initialize my underlying array
     * @param aSize int
     */
    @SuppressWarnings({"unchecked"})
    protected void initialize(int aSize) {
        
        this.setSize(aSize);
        this.setNodes((LinkedNode<K,V>[])Array.newInstance(LinkedNode.class, aSize));
        
        for (int i = 0; i < aSize; i++) {
            
            this.getNodes()[i] = new LinkedNode<K,V>();
        }
        
    }
    
    
    /**
     * Answer my nodes
     * @return LinkedNode<K,V>[]
     */
    protected LinkedNode<K, V>[] getNodes() {
        return nodes;
    }

    /**
     * Set my nodes
     * @param nodes LinkedNode<K,V>[]
     */
    protected void setNodes(LinkedNode<K, V>[] nodes) {
        this.nodes = nodes;
    }

    /**
     * Answer my size
     * @return int
     */
    protected int getSize() {
        return size;
    }

    /**
     * Set my size
     * @param size int
     */
    protected void setSize(int size) {
        this.size = size;
    }

    /**
     * Put aValue at aKey
     * @param aKey K
     * @param aValue V
     */
    public V put(K aKey, V aValue) {
        
        LinkedNode<K,V>     tempOldNode;
        V                   tempOldValue = null;
        LinkedNode<K,V>     tempHeadNode;
        
        tempHeadNode = this.getNodes()[this.getHashFor(aKey)];
        tempOldNode = tempHeadNode.getNodeWithKey(aKey);
        
        if (tempOldNode != null) {
            
            tempOldValue = tempOldNode.getValue();
            tempOldNode.setValue(aValue);
        }
        else {
            
           tempOldNode = tempHeadNode.getEmptyLink();
           tempOldNode.setLink(new LinkedNode<K,V>(aKey,aValue));
        }
        
        return tempOldValue;
    }
    
    /**
     * Answer a value for aKey
     * @param aKey K
     * @return V
     */
    public V get(K aKey) {
        
        V                   tempValue = null;
        LinkedNode<K,V>     tempHeadNode;
        LinkedNode<K,V>     tempOldNode;
        
        tempHeadNode = this.getNodes()[this.getHashFor(aKey)];
        if (!tempHeadNode.isEmpty()) {
            
            tempOldNode = tempHeadNode.getNodeWithKey(aKey);
            if (tempOldNode != null) {
                
                tempValue = tempOldNode.getValue();
            }
        }
        
        return tempValue;
        
    }
    
    /**
     * Answer my key set
     * @return Set<K>
     */
    public Set<K> getKeySet() {
        
        Set<K> tempResult = new HashSet<K>();
        
        for (LinkedNode<K,V> aNode: this.getNonEmptyNodes()) {
            
            tempResult.add(aNode.getKey());
        }
        
        return tempResult;
                  
    }



    
    /**
     * Answer my values
     * @return Set<K>
     */
    public List<V> getValues() {
        
        List<V> tempResult = new ArrayList<V>();
        
        for (LinkedNode<K,V> aNode: this.getNonEmptyNodes()) {
            
            tempResult.add(aNode.getValue());
        }
        
        return tempResult;
    }
    
    
    
    
    /**
     * Answer my non-empty nodes
     * @return List<Node<K,V>>
     */
    protected List<LinkedNode<K,V>> getNonEmptyNodes() {
        
        List<LinkedNode<K,V>> tempResult = new ArrayList<LinkedNode<K,V>>();
        List<LinkedNode<K,V>> tempCurrent;
        
        for (LinkedNode<K,V> aNode: this.getNodes()) {
            
            tempCurrent = aNode.getNonEmptyNodes();
            if (!tempCurrent.isEmpty()) {
                
                tempResult.addAll(tempCurrent);
            }
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer my index based on my hash function for aKey
     * @param aKey K
     */
    protected int getHashFor(K aKey) {
        
        return (aKey.hashCode()) % this.getSize();
        
    }
}
