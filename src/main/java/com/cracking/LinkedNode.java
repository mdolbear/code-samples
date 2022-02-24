package com.cracking;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 *
 *
 */
public class LinkedNode<K, V> {

    private K key;
    private V value;
    private Comparator<K> comparator;
    
    private LinkedNode<K,V> nextNode;
    private LinkedNode<K,V> previousNode;
    private boolean visited;
    

    /**
     * 
     * Answer an instance for the following arguments.
     * @param aKey
     * @param aValue
     * @param aComparator
     */
    public LinkedNode(K aKey, 
                      V aValue, 
                      Comparator<K> aComparator) {
        
        super();
        this.setKey(aKey);
        this.setValue(aValue);
        this.setComparator(aComparator);
        this.setVisited(false);
        
    }
    
    
    
    /**
     * Answer the node that contains aKey or null
     * @param aKey K
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> findNodeWithKey(K aKey) {
        
        LinkedNode<K,V> tempResult = null;
        LinkedNode<K,V> tempCurrent;
        
        tempCurrent = this;
        
        do {
            
            if (!tempCurrent.hasKey(aKey)) {
                
                tempCurrent = tempCurrent.getNextNode();
            }
            else {
                
                tempResult = tempCurrent;
            }
            
        } while (tempCurrent != null &&
                                tempResult == null);
        
        
        
        return tempResult;
        
    }

    /**
     * Answer whether or not I have aKey
     * @param aKey K
     */
    public boolean hasKey(K aKey) {
        
        return this.getKey() != null &&
                    this.getKey().equals(aKey);
        
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
     * Answer whether or not I am the last node
     * @return boolean
     */
    public boolean isLastNode() {
        
        return this.getNextNode() == null;
        
    }
    

    /**
     * Answer whether or not I am the first node
     * @return boolean
     */
    public boolean isFirstNode() {
        
        return this.getPreviousNode() == null;
        
    }
    
    
    /**
     * Delete a node from me with aKey. Answer the LinkedNode<K,V> that was
     * deleted or null if nothing was deleted
     * @param aKey K
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> deleteNodeWithKey(K aKey) {
        
        LinkedNode<K,V> tempResult = null;
        LinkedNode<K,V> tempCurrent;
        LinkedNode<K,V> tempTrailing;
        
        tempCurrent = this;
        tempTrailing = null;
        
        do {
            
            if (!tempCurrent.hasKey(aKey)) {
                
                tempTrailing = tempCurrent;
                tempCurrent = tempCurrent.getNextNode();
            }
            else {
                
                tempResult = tempCurrent;
            }
            
        } while (tempCurrent != null && tempResult == null);
        
        if (tempResult != null & tempTrailing != null) {
            
            this.basicRemoveNodeFromList(tempResult, tempTrailing);
        }
        else {
            tempResult = null; //Not really removed
        }
        
        return tempResult;
        
    }
    
    /**
     * Delete last node from me. Answer the LinkedNode<K,V> that was
     * deleted or null if nothing was deleted. Won't delete the very last node
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> deleteLastNode() {
        
        LinkedNode<K,V> tempResult = null;
        LinkedNode<K,V> tempCurrent;
        LinkedNode<K,V> tempTrailing;
        
        tempCurrent = this;
        tempTrailing = null;
        
        do {
                           
            tempTrailing = tempCurrent;
            tempCurrent = tempCurrent.getNextNode();

            
        } while (tempCurrent != null && !tempCurrent.isLastNode());
        
        if (tempCurrent != null & tempTrailing != null) {
            
            tempResult = tempCurrent;
            this.basicRemoveNodeFromList(tempResult, tempTrailing);
            
        }
        else {
            tempResult = null; //Not really removed
        }
        
        return tempResult;
        
    }
    
    
    
    /**
     * Delete a node from me with aKey. Answer the LinkedNode<K,V> that was
     * deleted or null if nothing was deleted
     * @param aNode LinkedNode
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> deleteCenterNode(LinkedNode<K,V> aNode) {
        
        LinkedNode<K,V> tempDeletedNode = null;
        LinkedNode<K,V> tempCurrent;
        LinkedNode<K,V> tempTrailing;
        
        tempCurrent = this;
        tempTrailing = null;
        
        //Never delete last node
        while (!tempCurrent.isLastNode() && tempDeletedNode == null) {
            
           if (tempCurrent.equals(aNode)) {
                    
                    this.basicRemoveNodeFromList(tempCurrent, tempTrailing);
                    tempDeletedNode = tempCurrent;
                    
            }
           else {
               
               tempTrailing = tempCurrent;
               tempCurrent = tempCurrent.getNextNode();
           }
            
        }
        
        return tempDeletedNode;
        
    }
    
    
    
    /**
     * Delete nodes with duplicate keys
     * @param aDuplicates Set<K>
     */
    public void deleteNodesWithDuplicateKeys(Set<K> aDuplicates) {
        
        LinkedNode<K,V> tempCurrent;
        LinkedNode<K,V> tempTrailing;
        
        tempCurrent = this;
        tempTrailing = null;
        
       do {
            
            if (aDuplicates.contains(tempCurrent.getKey())) {
                
                this.basicRemoveNodeFromList(tempCurrent, tempTrailing);
                tempCurrent = tempTrailing.getNextNode();
                
            }
            else {
                
                aDuplicates.add(tempCurrent.getKey());
                tempTrailing = tempCurrent;
                tempCurrent = tempCurrent.getNextNode();
                
            }
            
        }  while (tempCurrent != null);
        
    }

    /**
     * Remove a node from the list by pointing its non-null trailer one beyond the current
     * @param aCurrent LinkedNode<K, V>
     * @param aTrailing LinkedNode<K, V>
     */
    protected void basicRemoveNodeFromList(LinkedNode<K, V> aCurrent,
                                           LinkedNode<K, V> aTrailing) {
        
        if (aTrailing != null) {
            
            aTrailing.setNextNode(aCurrent.getNextNode());
            if (aCurrent.getNextNode() != null) {
                
                aCurrent.getNextNode().setPreviousNode(aTrailing);
                
            }
            
            //            aCurrent.setNextNode(null); -- Don't remove pointer from deleted node to next node if you want to start from where you left off on a delete
            //            aCurrent.setPreviousNode(null);
            
        }
        
    }
    
    
    /**
     * Answer my nextNode
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K, V> getNextNode() {
        return nextNode;
    }

    /**
     * Set my nextNode
     * @param nextNode LinkedNode<K,V>
     */
    protected void setNextNode(LinkedNode<K, V> nextNode) {
        this.nextNode = nextNode;
    }
    
    
    /**
     * Dump my list to aBuilder
     * @param aBuilder StringBuilder
     */
    public void dumpListTo(StringBuilder aBuilder) {
        
        LinkedNode<K,V> tempCurrent;
        
        tempCurrent = this;
        do {
            
            aBuilder.append(tempCurrent.toString());
            tempCurrent = tempCurrent.getNextNode();
            
        } while (tempCurrent != null);
        
    }
    
    /**
     * Push elements onto aStack until empty
     * @return Stack
     */
    public Stack<LinkedNode<K,V>> pushElementsUntilEmpty() {
        
        Stack<LinkedNode<K,V>>  tempStack = new Stack<LinkedNode<K,V>>();
        LinkedNode<K,V>         tempCurrent;
        
        tempCurrent = this;
        do {
            
            tempStack.push(tempCurrent);
            tempCurrent = tempCurrent.getNextNode();
            
        } while (tempCurrent != null);
        
        return tempStack;
        
    }
    
    /**
     * Answer my string representation
     * @return String
     */
    @Override
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempBuilder.append("key: ");
        tempBuilder.append(this.getKey() != null ? this.getKey().toString() + " " : "null ");
        
        tempBuilder.append("value: ");
        tempBuilder.append(this.getValue() != null ? this.getValue().toString() + " "  : "null ");
        
        
        return tempBuilder.toString();
        
    }
    
    /**
     * Answer my element which is roughly after the "midpoint" that has aKey
     * @param aKey K
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K,V> getElementAfterMidpointWith(K aKey) {
        
        LinkedNode<K,V> tempMidValue;
        LinkedNode<K,V> tempResult = null;
        LinkedNode<K,V> tempCurrent;
        
        tempCurrent = this;
        tempMidValue = this;
        
        while (tempCurrent != null &&
                !tempCurrent.isLastNode()) {
            
            tempMidValue = tempMidValue.getNextNode();
            tempCurrent = tempCurrent.getNextNode();
            tempCurrent = this.safelyGetNextNodeFrom(tempCurrent);
            
        }
        
        //Now start the search for aKey from tempMidValue;
        if (tempMidValue != null) {
            
            tempResult = this.findNodeWithKey(aKey);
            
        }
        
        return tempResult;
        
        
    }
    
    
    /**
     * Safely get next element from aNode. If aNode is null, return null. Otherwise, return the next node from a Node
     * @param aNode LinkedNode<K,V>
     */
    protected LinkedNode<K,V> safelyGetNextNodeFrom(LinkedNode<K,V> aNode) {
        
        LinkedNode<K,V> tempResult = null;
        
        if (aNode != null) {
            
            tempResult = aNode.getNextNode();
        }
        
        return tempResult;
        
    }
    
    /**
     * Interchange keys and data with anotherNode
     * @param anotherNode LinkedNode<K,V>
     */
    public void interchangeKeysAndDataWith(LinkedNode<K,V> anotherNode) {
        
        K tempKey;
        V tempData;
        
        tempKey = this.getKey();
        tempData = this.getValue();
        
        this.setKey(anotherNode.getKey());
        this.setValue(anotherNode.getValue());
        
        anotherNode.setKey(tempKey);
        anotherNode.setValue(tempData);
        
    }
    
    /**
     * Find first node with key greater than aKey
     * @param aKey K
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K,V> findFirstNodeWithKeyGreaterThan(K aKey) {
        
        LinkedNode<K,V> tempMaxNode;
        LinkedNode<K,V> tempCurrent;
        
        tempMaxNode = null;
        tempCurrent = this;
        
        while (tempCurrent != null && tempMaxNode == null) {
            
            if (this.getComparator().compare(tempCurrent.getKey(), aKey) > 0) {
                
                tempMaxNode = tempCurrent;
            }
            else {
                
                tempCurrent = tempCurrent.getNextNode();
            }
        }
        
        return tempMaxNode;
    }
    
    /**
     * Find first node with key less than aKey
     * @param aKey K
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K,V> findFirstNodeWithKeyLessThan(K aKey) {
        
        LinkedNode<K,V> tempMinNode;
        LinkedNode<K,V> tempCurrent;
        
        tempMinNode = null;
        tempCurrent = this;
        
        while (tempCurrent !=null && tempMinNode == null) {
            
            if (this.getComparator().compare(tempCurrent.getKey(), aKey) < 0) {
                
                tempMinNode = tempCurrent;
            }
            else {
                
                tempCurrent = tempCurrent.getPreviousNode();
            }
        }
        
        return tempMinNode;
    }
    
    

    /**
     * Answer my comparator
     * @return Comparator<K>
     */
    protected Comparator<K> getComparator() {
        return comparator;
    }

    /**
     * Set my comparator
     * @param comparator Comparator<K>
     */
    protected void setComparator(Comparator<K> comparator) {
        this.comparator = comparator;
    }
    
    /**
     * Answer whether or not I am before anotherNode
     * @param anotherNode LinkedNode<K,V>
     */
    public boolean isBefore(LinkedNode<K,V> anotherNode) {
        
        boolean                 tempResult = false;
        LinkedNode<K,V>         tempCurrent;
        
        tempCurrent = this.getNextNode();
        while (tempCurrent != null &&
                             !tempResult) {
            
            tempResult = tempCurrent.equals(anotherNode);
            tempCurrent = tempCurrent.getNextNode();
            
        }
        
        return tempResult;
    }
    
    /**
     * Answer the last node
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> getLastNode() {
        
        LinkedNode<K,V> tempResult;
        
        tempResult = this;
        while (!tempResult.isLastNode()) {
            
           tempResult = tempResult.getNextNode();
        }
        
        return tempResult;
    }

    /**
     * Answer my previousNode
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K, V> getPreviousNode() {
        return previousNode;
    }

    /**
     * Set my previousNode
     * @param previousNode LinkedNode<K,V>
     */
    protected void setPreviousNode(LinkedNode<K, V> previousNode) {
        this.previousNode = previousNode;
    }
    
    /**
     * Reverse me. Answer my new first node
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> reverse() {
        
        LinkedNode<K,V> tempCurrent;
        LinkedNode<K,V> tempPrevious;
        LinkedNode<K,V> tempNewNextNode;
        
        tempPrevious = this;
        tempCurrent = this.getNextNode();
        while (tempCurrent != null) {
            
            tempPrevious.reverseMyLinks();
            tempNewNextNode = tempPrevious;
            tempPrevious = tempCurrent;
            
            tempCurrent = tempCurrent.getNextNode();
            tempPrevious.setNextNode(tempNewNextNode);
            
        }
        
        return tempPrevious;
                
    }

    /**
     * Reverse me, acting like I am a singly linked list. Answer my new first node
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> reverseSingle() {

        LinkedNode<K,V> tempCurrent;
        LinkedNode<K,V> tempPrevious;
        LinkedNode<K,V> tempNewNextNode;

        tempPrevious = this;
        tempCurrent = this.getNextNode();
        while (tempCurrent != null) {

            tempNewNextNode = tempPrevious;
            tempPrevious = tempCurrent;

            tempCurrent = tempCurrent.getNextNode();
            tempPrevious.setNextNode(tempNewNextNode);

        }

        //Set the original head next node to point to null
        this.setNextNode(null);

        return tempPrevious;

    }
    
    /**
     * Swap links
     */
    protected void reverseMyLinks() {
        
        LinkedNode<K,V> tempTemporaryNode;
        
        tempTemporaryNode = this.getNextNode();
        this.setNextNode(this.getPreviousNode());
        this.setPreviousNode(tempTemporaryNode);
        
        
    }

    
    /**
     * Insert aNode as the last node
     * @param aNode LinkedNode<K,V>
     */
    public void insertAsLast(LinkedNode<K,V> aNode) {
        
        LinkedNode<K,V> tempLastNode;
        
        tempLastNode = this.getLastNode();
        tempLastNode.setNextNode(aNode);
        aNode.setPreviousNode(tempLastNode);
        aNode.setNextNode(null);
        
    }
    
    /**
     * Answer a copy of myself. Note that this can be dangerous if the keys are values of the copy are manipulated
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> shallowCopy() {
        
        return new LinkedNode<K,V>(this.getKey(),this.getValue(), this.getComparator());
        
    }
    
    /**
     * Add my keys to aSet
     * @param aSet Set<K>
     */
    protected void addMyKeysTo(Set<K> aSet) {
        
        LinkedNode<K,V> tempCurrentNode;
        
        tempCurrentNode = this;
        while (tempCurrentNode != null) {
            
            aSet.add(tempCurrentNode.getKey());
            tempCurrentNode = tempCurrentNode.getNextNode();
        }
        
    }
    
    /**
     * Add my values to aList
     * @param aList List<V>
     */
    protected void addMyValuesTo(List<V> aList) {

        LinkedNode<K,V> tempCurrentNode;
        
        tempCurrentNode = this;
        while (tempCurrentNode != null) {
            
            aList.add(tempCurrentNode.getValue());
            tempCurrentNode = tempCurrentNode.getNextNode();
        }
        
    }
    
    /**
     * Answer my size (i.e. my number of nodes)
     * @return int
     */
    public int size() {
        
        int              tempResult = 0;
        LinkedNode<K,V>  tempCurrent;
        
        tempCurrent = this;
        while (tempCurrent != null) {
            
            tempResult++;
            tempCurrent = tempCurrent.getNextNode();
        }
        
        
        return tempResult;
        
    }
    
    /**
     * Answer my size (i.e. my number of nodes)
     * @return int
     */
    public int sizeWithoutVisited() {
        
        int              tempResult = 0;
        LinkedNode<K,V>  tempCurrent;
        
        tempCurrent = this;
        while (tempCurrent != null) {
            
            if (!tempCurrent.isVisited()) {
                
                tempResult++;
            }
            
            tempCurrent = tempCurrent.getNextNode();
            
        }
        
        
        return tempResult;
        
    }



    /**
     * Answer my visited
     * @return boolean
     */
    public boolean isVisited() {
        return visited;
    }


    /**
     * Mark as visited
     */
    public void markAsVisited() {
        
        this.setVisited(true);
    }
    
    
    /**
     * Set my visited
     * @param visited boolean
     */
    protected void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    
    
}
