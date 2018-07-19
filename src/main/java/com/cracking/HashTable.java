package com.cracking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 *
 */
public class HashTable<K, V> {

    
    private LinkedNode<K,V>[] table;
    private int size;

    /**
     * Answer an instance of me for aSize
     * @param aSize int
     */
    public HashTable(int aSize) {
        
        super();
        this.setSize(aSize);
        this.initialize(aSize);
    }


    /**
     * Initialize my internal array
     * @param aSize
     */
    @SuppressWarnings("unchecked")
    protected void initialize(int aSize) {
        
        this.setTable((LinkedNode<K,V>[])Array.newInstance(LinkedNode.class, aSize));
    }
    
    
    /**
     * Answer my table
     * @return LinkedNode<K,V>[]
     */
    protected LinkedNode<K, V>[] getTable() {
        return table;
    }

    /**
     * Set my table
     * @param table LinkedNode<K,V>[]
     */
    protected void setTable(LinkedNode<K, V>[] table) {
        this.table = table;
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
     * Put aValue at aKey. If I contain an element with aKey already,
     * replace its value
     * @param aKey K
     * @param aValue V
     */
    public void put(K aKey, V aValue) {
        
        LinkedNode<K,V> tempExisting = null;
        int             tempHashValue;
        
        tempHashValue = this.computeHashFor(aKey);
        if (this.getTable()[tempHashValue] != null) {
            
            tempExisting = this.getTable()[tempHashValue].findNodeWithKey(aKey);
        }
        
        if (tempExisting != null) {
            
            tempExisting.setValue(aValue);
        }
        else {
            
            this.insertNewNode(aKey, aValue, tempHashValue);
        }
        
        
    }
    
    /**
     * Answer the value for aKey or null
     * @param aKey K
     * @return V
     */
    public V get(K aKey) {
        
        LinkedNode<K,V> tempExisting = null;
        int             tempHashValue;
        V               tempResult = null;
        
        tempHashValue = this.computeHashFor(aKey);
        if (this.getTable()[tempHashValue] != null) {
            
            tempExisting = this.getTable()[tempHashValue].findNodeWithKey(aKey);
        }
        
        if (tempExisting != null) {
            
            tempResult = tempExisting.getValue();
        }
        
        return tempResult;
        
    }
    
    /**
     * Remove the element at aKey
     * @param aKey K
     * @return V
     */
    public V remove(K aKey) {
        
        LinkedNode<K,V> tempExisting = null;
        int             tempHashValue;
        V               tempResult = null;
        
        tempHashValue = this.computeHashFor(aKey);
        if (this.getTable()[tempHashValue] != null &&
                !this.getTable()[tempHashValue].hasKey(aKey)) {
            
            tempExisting = this.getTable()[tempHashValue].deleteNodeWithKey(aKey);
        }
        else if (this.getTable()[tempHashValue] != null &&
                    this.getTable()[tempHashValue].hasKey(aKey)) {
            
            //remove head node of list -- if there are any remaining
            //nodes beyond the head, keep them
            tempExisting = this.removeFirstNodeAtHashValue(tempHashValue);
            
        }
        
        if (tempExisting != null) {
            
            tempResult = tempExisting.getValue();
        }
        
        return tempResult;
        
    }


    /**
     * Remove first node at hash value
     * @param aHashValue
     * @return
     */
    protected LinkedNode<K, V> removeFirstNodeAtHashValue(int aHashValue) {
        
        LinkedNode<K, V> tempExisting;
        
        tempExisting = this.getTable()[aHashValue];
        
        if (tempExisting.getNextNode() != null) {
            
            this.getTable()[aHashValue] = tempExisting.getNextNode();
        }
        else {
            
            this.getTable()[aHashValue] = null;
        }
        
        return tempExisting;
        
    }
    
    
    /**
     * Insert new node
     * @param aKey K
     * @param aValue V
     * @param aHashValue int
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K,V> insertNewNode(K aKey,
                                            V aValue,
                                            int aHashValue) {
        
        LinkedNode<K,V> tempNewNode;
        LinkedNode<K,V> tempCurrentNode;
        
        tempNewNode = new LinkedNode<K,V>(aKey, aValue, null);
        tempCurrentNode = this.getTable()[aHashValue];
        
        if (tempCurrentNode != null) {
            
            tempNewNode.setNextNode(tempCurrentNode);
            
        }
        
        this.getTable()[aHashValue] = tempNewNode;
        
        return tempNewNode;
    }
    
    
    /**
     * Answer a computed hash for aK
     * @param aK K
     * @return int
     */
    protected int computeHashFor(K aK) {
        
        return Math.abs(aK.hashCode()) % this.getSize();
    }
    
    /**
     * Answer whether or not I have aKey
     * @param aKey K
     * @return boolean
     */
    public boolean hasKey(K aKey) {
        
        return this.getKeys().contains(aKey);
    }
    
    
    /**
     * Answer a set of my keys
     * @return Set<K>
     */
    public Set<K> getKeys() {
        
        Set<K>              tempKeys = new HashSet<K>();
        
        for (int i = 0; i < this.getSize(); i++) {
            
            if (this.getTable()[i] != null) {
                
                this.getTable()[i].addMyKeysTo(tempKeys);
            }
            
        }
        
        return tempKeys;
    }
    
    /**
     * Answer a list of my values
     * @return List<K>
     */
    public List<V> getValues() {
        
        List<V> tempValues = new ArrayList<V>();
        
        for (int i = 0; i < this.getSize(); i++) {
            
            if (this.getTable()[i] != null) {
                
                this.getTable()[i].addMyValuesTo(tempValues);
            }
            
        }
        
        return tempValues;
    }
    
    /**
     * Answer my number of elements
     * @return int
     */
    public int getNumberOfElements() {
        
        int tempResult = 0;
        
        for (int i = 0; i < this.getSize(); i++) {
            
            if (this.getTable()[i] != null) {
            
                tempResult +=  this.getTable()[i].size();
                
            }
            
        }
        
        return tempResult;
        
    }
    
    
    
}
