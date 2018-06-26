package com.cracking;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 */
public class DepthLinkedLists<E> {

    private Map<Integer, LinkedNode<BinaryTreeNode<E>, BinaryTreeNode<E>>>  linkedLists;
    
    /**
     * Answer an instance for the following arguments
     */
    public DepthLinkedLists() {
        
        super();
        this.setLinkedLists(new HashMap<Integer, LinkedNode<BinaryTreeNode<E>, BinaryTreeNode<E>>>());
        
    }

    /**
     * Put aTreeNode in the proper list
     * @param aTreeNode BinaryTreeNode<E>
     * @param aDepthValue int
     */
    public void putTreeNodeInProperList(BinaryTreeNode<E> aTreeNode,
                                        int aDepthValue) {
        
        Integer                                             tempKey;
        LinkedNode<BinaryTreeNode<E>, BinaryTreeNode<E>>    tempLinkedNode;
        LinkedNode<BinaryTreeNode<E>, BinaryTreeNode<E>>    tempNewLinkedNode;
        
        tempKey = new Integer(aDepthValue);
        
        //Find existing node and link to it or insert new one directly into map
        tempLinkedNode = this.getNodeForDepth(tempKey);
        if (tempLinkedNode == null) {
            
            tempLinkedNode = new LinkedNode<BinaryTreeNode<E>, BinaryTreeNode<E>>(aTreeNode, aTreeNode, null);
            this.getLinkedLists().put(tempKey, tempLinkedNode);
            
        }
        else {
            
            tempNewLinkedNode = new LinkedNode<BinaryTreeNode<E>, BinaryTreeNode<E>>(aTreeNode, aTreeNode, null);
            tempLinkedNode.insertAsLast(tempNewLinkedNode);
        }
        
    }
    
    

    /**
     * Answer my linkedLists
     * @return Map<Integer,LinkedNode<BinaryTreeNode<E>,BinaryTreeNode<E>>>
     */
    protected Map<Integer, LinkedNode<BinaryTreeNode<E>, BinaryTreeNode<E>>> getLinkedLists() {
        return linkedLists;
    }

    /**
     * Answer my LinkedNode<BinaryTreeNode<E>, BinaryTreeNode<E>> or null
     * for aDepth
     * @param aDepth Integer
     * @return LinkedNode<BinaryTreeNode<E>, BinaryTreeNode<E>>
     */
    protected LinkedNode<BinaryTreeNode<E>, BinaryTreeNode<E>> getNodeForDepth(Integer aDepth) {
        
        return this.getLinkedLists().get(aDepth);
        
    }
    
    /**
     * Set my linkedLists
     * @param linkedLists Map<Integer,LinkedNode<BinaryTreeNode<E>,BinaryTreeNode<E>>>
     */
    protected void setLinkedLists(
            Map<Integer, LinkedNode<BinaryTreeNode<E>, BinaryTreeNode<E>>> linkedLists) {
        
        this.linkedLists = linkedLists;
    }

    
}
