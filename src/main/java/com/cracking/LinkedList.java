package com.cracking;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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
        this.setHead(null);
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
     * Insert a new node with aKey and aValue
     * @param aKey K
     * @param aValue V
     * @param aComparator Comparator<K>
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> insertNodeWith(K aKey, 
                                          V aValue,
                                          Comparator<K> aComparator) {
        
        LinkedNode<K,V> tempNewNode;
        
        tempNewNode = new LinkedNode<K,V>(aKey, 
                                          aValue,
                                          aComparator);
        if (this.isEmpty()) {
            
            this.setHead(tempNewNode);
        }
        else {
            
            this.insertNode(tempNewNode);
        }
        
        return tempNewNode;
        
    }
    
    
    /**
     * Insert aNode. Always insert at the beginning of the list
     * @param aNode LinkedNode<K,V>
     */
    public void insertNode(LinkedNode<K,V> aNode) {
        

        this.getHead().setPreviousNode(aNode);
        aNode.setNextNode(this.getHead());
        
        this.setHead(aNode);        
        this.getHead().setPreviousNode(null);

    }
    
    /**
     * Answer the node that contains aKey or null
     * @param aKey K
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> findNodeWithKey(K aKey) {
        
        LinkedNode<K,V> tempResult = null;
        
        if (!this.isEmpty()) {
            
            tempResult = this.getHead().findNodeWithKey(aKey);
        }
        
        return tempResult;
        
    }
    
    /**
     * Delete a node from me with aKey (the first one encountered). Answer the LinkedNode<K,V> that was
     * deleted or null if nothing was deleted
     * @param aKey K
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> deleteNodeWitKey(K aKey) {
        
        LinkedNode<K,V> tempDeletedNode = null;
        
        if (!this.isEmpty()) {
            
            //If the list allows duplicate keys, we have to rely on object equals to detect that the
            //head and deleted node are the "same"
            tempDeletedNode = this.getHead().deleteNodeWithKey(aKey);
            if (tempDeletedNode != null) {
                
                if (this.getHead().equals(tempDeletedNode)) {
                    
                    this.setHead(tempDeletedNode.getNextNode());
                }

            }
        }
        
        return tempDeletedNode;
        
    }
    
    /**
     * Remove duplicate keys
     * @param aSet Set<K>
     */
    public void deleteNodesWithDuplicateKeys() {
        
        Set<K> tempDuplicates = new HashSet<K>();
        
        if (!this.isEmpty()) {
            
            this.getHead().deleteNodesWithDuplicateKeys(tempDuplicates);
        }
        
    }
    
    /**
     * Delete a node from me with aKey. Answer the LinkedNode<K,V> that was
     * deleted or null if nothing was deleted
     * @param aKey K
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> deleteCenterNode(LinkedNode<K,V> aNode) {
        
        LinkedNode<K,V> tempDeletedNode = null;
        
        if (!this.isEmpty() && !this.getHead().equals(aNode)) {
            
            tempDeletedNode = this.getHead().deleteCenterNode(aNode);
        }
        
        return tempDeletedNode;
        
    }
    
    /**
     * Dump my list to aBuilder
     * @param aBuilder StringBuilder
     */
    public void dumpListTo(StringBuilder aBuilder) {
        
        if (this.isEmpty()) {
            
            aBuilder.append("empty");
        }
        else {
            
            this.getHead().dumpListTo(aBuilder);
        }
        
    }
    
    /**
     * Answer the kth element from the end or null. Assume k is 1 based.
     * @param k int
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> getNodeFromEnd(int k) {
        
        LinkedNode<K,V>             tempCurrent = null;
        LinkedNode<K,V>             tempResult = null;
        int                         i = 0;
        Stack<LinkedNode<K,V>>      tempStack;
        
        if (!this.isEmpty()) {
            
            tempStack = this.getHead().pushElementsUntilEmpty();
            
            while (!tempStack.isEmpty() && i < k) {
                
                tempCurrent = tempStack.pop();
                i++;
            }
            
            if (i == k && tempCurrent != null) {
                
                tempResult = tempCurrent;
            }
            
        }
        
        return tempResult;
        
    }

    
    /**
     * Partition me. I need a doubly linked list to pull this off it seems.....
     * @param aKey K
     */
    public void partitionAround(K aKey) {
        
        LinkedNode<K,V> tempPivotNode;
        LinkedNode<K,V> tempLastNode;
        LinkedNode<K,V> tempFirstNode;
        LinkedNode<K,V> tempGreaterNode = null;
        LinkedNode<K,V> tempLesserNode;
        
        tempPivotNode = this.findNodeWithKey(aKey);           
        tempLastNode = this.getHead().getLastNode();
        tempFirstNode = this.getHead();
        
        while (tempFirstNode != null &&
                    tempLastNode != null &&
                        tempFirstNode.isBefore(tempLastNode)) {
            
            tempGreaterNode = tempFirstNode.findFirstNodeWithKeyGreaterThan(aKey); //This search starts from the beginning and increases
            tempLesserNode = tempLastNode.findFirstNodeWithKeyLessThan(aKey); //This search starts from the end of the list and decreases
            
            if (tempGreaterNode != null &&
                    tempLesserNode != null &&
                        tempGreaterNode.isBefore(tempLesserNode)) {
                
                tempLesserNode.interchangeKeysAndDataWith(tempGreaterNode);

            }
            
            tempFirstNode = tempGreaterNode;
            tempLastNode = tempLesserNode;
            
            
        }
        
        //This last switch is critical since it needs to switch the pivot point with the last known "greatest value" near the start of the partition
        //The else if handles the error case where the biggest node is at the start of the list.
        if (tempGreaterNode != null && tempPivotNode != null) {
            
            tempGreaterNode.interchangeKeysAndDataWith(tempPivotNode);
        }
        else if (tempPivotNode != null &&
                    tempPivotNode.equals(this.getHead())) {
            
            this.getHead().interchangeKeysAndDataWith(this.getHead().getLastNode());
        }
            

    }
    
    
    /**
     * Answer my element which is roughly after the "midpoint" that has aKey
     * @param aKey K
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K,V> getElementAfterMidpointWith(K aKey) {
        
        LinkedNode<K,V> tempResult = null;
        
        if (!this.isEmpty()) {
            
            tempResult = this.getHead().getElementAfterMidpointWith(aKey);
        }
        
        return tempResult;
        
    }
    
    /**
     * Reverse me
     */
    public void reverse() {
        
        LinkedNode<K,V> tempNewHead;
        
        if (!this.isEmpty()) {
            
            tempNewHead = this.getHead().reverse();
            this.setHead(tempNewHead);
            
        }
        
    }
    
    /**
     * Answer the last node
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> getLastNode() {
        
        LinkedNode<K,V> tempResult = null;
        
        if (!this.isEmpty()) {
            
            tempResult = this.getHead().getLastNode();
        }
        
        return tempResult;
    }
  
    
    /**
     * Answer the sum of me and anotherLinkedList assuming that my digits are stored in least significant first order
     * @param anotherList LinkedList<String, Integer>
     */
    @SuppressWarnings("unchecked")
    public LinkedList<String, Integer> newListAsSumOf(LinkedList<String, Integer> anotherList,
                                                      Comparator<String> aComparator) {
        
        LinkedList<String, Integer>                  tempSumResult = new LinkedList<String, Integer>();
        int                                          tempCurrentCarry;
        int                                          tempCurrentSumOfNodes;
        LinkedNode<String, Integer>                  tempMeCurrent;
        LinkedNode<String, Integer>                  tempAnotherListCurrent;
        int                                          i;
        
        if (!this.isEmpty() || !anotherList.isEmpty()) {
            
            tempMeCurrent = (LinkedNode<String, Integer>)this.getHead();
            tempAnotherListCurrent = (LinkedNode<String, Integer>)anotherList.getHead();
            
            tempCurrentCarry = 0;
            i = 0;
            while (tempMeCurrent != null ||
                    tempAnotherListCurrent != null) {
                
                tempCurrentSumOfNodes = 0;
                
                if (tempMeCurrent != null) {
                    
                    tempCurrentSumOfNodes += tempMeCurrent.getValue().intValue();
                    tempMeCurrent = tempMeCurrent.getNextNode();
                }
                
                if (tempAnotherListCurrent != null) {
                    
                    tempCurrentSumOfNodes += tempAnotherListCurrent.getValue().intValue();
                    tempAnotherListCurrent = tempAnotherListCurrent.getNextNode();
                }
                
                if (tempCurrentCarry != 0) {
                    
                    tempCurrentSumOfNodes += tempCurrentCarry;
                }
                
                tempCurrentCarry = tempCurrentSumOfNodes/10;
                tempCurrentSumOfNodes = tempCurrentSumOfNodes%10;
                
                this.addSumNodeToNewList(aComparator, 
                                         tempSumResult,
                                         tempCurrentSumOfNodes, 
                                         i);
                
                i++;
                
            }
            
            //On a carry out of the last digit, add another digit
            if (tempCurrentCarry != 0) {
                
                this.addSumNodeToNewList(aComparator, 
                                         tempSumResult,
                                         tempCurrentCarry, 
                                         i);
            }
            
        }
        
        
        return tempSumResult;
        
    }

    /**
     * Add a new sum note to aSumResult list
     * @param aComparator
     * @param aSumResult
     * @param aCurrentSumOfNodes
     * @param i
     * @return
     */
    protected void addSumNodeToNewList(Comparator<String> aComparator,
                                        LinkedList<String, Integer> aSumResult, 
                                        int aCurrentSumOfNodes,
                                        int i) {
        
        LinkedNode<String, Integer>                  tempCurrentSumDigit;
        
        
        tempCurrentSumDigit = 
                new LinkedNode<String, Integer>(String.valueOf(i),
                                                new Integer(aCurrentSumOfNodes),
                                                aComparator);
        
        aSumResult.insertAsLast(tempCurrentSumDigit);

    }
    
    /**
     * Insert aNode as the last node
     * @param aLinkedNode LinkedNode<K,V>
     */
    public void insertAsLast(LinkedNode<K,V> aNode) { 
        
        if (this.isEmpty()) {
            
            this.setHead(aNode);
        }
        else {
            
            this.getHead().insertAsLast(aNode);
        }
        
    }
    
    /**
     * Answer whether or not I am a palindrome
     * @return boolean
     */
    public boolean isPalindrome() {
        
        boolean             tempResult = false;
        LinkedList<K,V>     tempReveresedCopy;
        
        tempReveresedCopy = this.copy();
        tempReveresedCopy.reverse();
        
        tempResult = this.areKeysEqualTo(tempReveresedCopy);
        
        return tempResult;
        
    }
    
    /**
     * Answer a copy of myself as a new linked list
     * @return LinkedList<K,V>
     */
    protected LinkedList<K,V> copy() {
        
        LinkedList<K,V> tempResult = new LinkedList<K,V>();
        LinkedNode<K,V> tempCurrent;
        
        if (!this.isEmpty()) {
            
            tempResult.setHead(this.getHead().shallowCopy());
            
            tempCurrent = this.getHead().getNextNode();
            while (tempCurrent != null) {
                
                tempResult.insertAsLast(tempCurrent.shallowCopy());
                tempCurrent = tempCurrent.getNextNode();
                
            }
            
        }
        
        return tempResult;
    }
    
    
    /**
     * Answer whether my keys are the same as anotherList and are the lists of the same size
     * @param anotherList LinkedList<K,V>
     */
    protected boolean areKeysEqualTo(LinkedList<K,V> anotherList) {
        
        boolean                             tempResult;
        LinkedNode<K,V>                     tempMyCurrentNode;
        LinkedNode<K,V>                     tempTheirCurrentNode;
        
        tempResult = this.isEmpty() && anotherList.isEmpty();
        if (!tempResult) {
            
            tempMyCurrentNode = this.getHead();
            tempTheirCurrentNode = anotherList.getHead();
            tempResult = true;
            while ((tempMyCurrentNode != null || tempTheirCurrentNode != null) && tempResult) {
                
                tempResult = (tempMyCurrentNode != null) && (tempTheirCurrentNode != null) && tempMyCurrentNode.hasKey(tempTheirCurrentNode.getKey());
                
                if (tempResult) {
                    
                    tempMyCurrentNode = tempMyCurrentNode.getNextNode();
                    tempTheirCurrentNode = tempTheirCurrentNode.getNextNode();
                }
                
            }
            
        }
        
        return tempResult;
    }
    
    
    /**
     * Find loop if one exists. Otherwise, answer null if one does not exist
     * @return LinkedNode<K,V>
     */
    public LinkedNode<K,V> findLoopIfExists() {
        
        LinkedNode<K,V> tempOneStep;
        LinkedNode<K,V> tempDoubleStep;
        LinkedNode<K,V> tempResult = null;
        
        if (!this.isEmpty()) {
            
            tempOneStep = this.getHead();
            tempDoubleStep = this.getHead();
            do {
                
                tempOneStep = this.safelyMoveNextLink(tempOneStep, 1);
                tempDoubleStep = this.safelyMoveNextLink(tempDoubleStep, 2);
                
                
            } while (tempDoubleStep != null &&
                        tempOneStep != null &&
                            !tempDoubleStep.equals(tempOneStep));
            

            
            if (tempDoubleStep != null &&
                    tempOneStep != null &&
                        tempDoubleStep.equals(tempOneStep)) {
                
                tempResult = this.discoverPositionOfLoop(tempDoubleStep);
            }
            
        }
        
        return tempResult;
    }

    /**
     * Discover position of loop since we have verified that there is one
     * @param aDoubleStep
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K, V> discoverPositionOfLoop(LinkedNode<K, V> aDoubleStep) {
        
        LinkedNode<K, V> tempOneStep;
        LinkedNode<K, V> tempResult;
        
        tempOneStep = this.getHead();
        
        while (!tempOneStep.equals(aDoubleStep)) {
            
            tempOneStep = tempOneStep.getNextNode();
            aDoubleStep = aDoubleStep.getNextNode();
        }
        
        tempResult = tempOneStep;
        
        return tempResult;
    }
    
    /**
     * Safely move my next link aNumberOfSteps. If null is encountered before, exit. Answer
     * the next link or null
     * @param aStartingLink LinkedNode<K,V>
     * @param aNumberOfSteps int
     * @return LinkedNode<K,V>
     */
    protected LinkedNode<K,V> safelyMoveNextLink(LinkedNode<K,V> aStartingLink, 
                                                 int aNumberOfSteps) {
        
        int             i = 0;
        LinkedNode<K,V> tempResult;
        
        tempResult = aStartingLink;
        while (tempResult != null && i < aNumberOfSteps) {
            
            tempResult = tempResult.getNextNode();
            i++;
        }
        
        return tempResult;
    }
    
    /**
     * Answer my size
     * @return int
     */
    public int size() {
        
        int tempResult = 0;
        
        if (!this.isEmpty()) {
            
            tempResult = this.getHead().size();
        }
        
        return tempResult;
        
    }
    
}
