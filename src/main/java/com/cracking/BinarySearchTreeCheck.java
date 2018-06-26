package com.cracking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 *
 */
public class BinarySearchTreeCheck<E> {

    private List<E> nodes;
    private Comparator<E> comparator;
    
    /**
     * Answer an instance for the following arguments
     * @param aComparator Comparator<E>
     */
    public BinarySearchTreeCheck(Comparator<E> aComparator) {
        
        super();
        this.setNodes(new ArrayList<E>());
        this.setComparator(aComparator);
    }

    
    /**
     * Add data for node
     * @param aData E
     */
    public void addDataFromNode(E aData) {
        
        this.getNodes().add(aData);
        
    }
    
    /**
     * Answer whether or not the tree rooted at a Node represents a Binary
     * Search Tree
     * @param aNode BinaryTreeNode<E>
     */
    public boolean isBinarySearchTree(BinaryTreeNode<E> aNode) {
        
        this.traverseInorder(aNode);
        return this.isBinarySearchTree();
        
    }
    
    /**
     * Perform inorder traversal
     * @param aRootNode BinaryTreeNode
     */
    protected void traverseInorder(BinaryTreeNode<E> aNode) {
        
        if (aNode != null) {
            
            this.traverseInorder(aNode.getLeftChild()); //Left
            this.addDataFromNode(aNode.getData());
            this.traverseInorder(aNode.getRightChild()); //Right
            
        }
        
        
    }
    
    
    /**
     * Answer whether I am a binary search tree
     * @return boolean
     */
    protected boolean isBinarySearchTree() {
        
        return this.isSorted();
    }
    
    
    /**
     * Answer whether or not my nodes are sorted
     * @return boolean
     */
    protected boolean isSorted() {
        
        boolean tempResult = true;
        E       tempCurrent;
        E       tempPrevious;
        int     i = 0;
        
        if (!this.getNodes().isEmpty()) {
            
            tempPrevious = this.getNodes().get(i++);
            while ((i < this.getNodes().size()) &&
                                        tempResult) {
                
                tempCurrent = this.getNodes().get(i++);
                tempResult = this.compare(tempPrevious, tempCurrent) <= 0;

                tempPrevious = tempCurrent;
                
            }
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer comparison value for aValue1 and aValue2
     * @param aValue1 E
     * @param aValue2 E
     * @return int
     */
    protected int compare(E aValue1, E aValue2) {
        
        return this.getComparator().compare(aValue1, aValue2);
    }
    
    
    /**
     * Answer my nodes
     * @return List<E>
     */
    protected List<E> getNodes() {
        return nodes;
    }

    /**
     * Set my nodes
     * @param nodes List<E>
     */
    protected void setNodes(List<E> nodes) {
        this.nodes = nodes;
    }


    /**
     * Answer my comparator
     * @return Comparator<E>
     */
    protected Comparator<E> getComparator() {
        return comparator;
    }


    /**
     * Set my comparator
     * @param comparator Comparator<E>
     */
    protected void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    
    

}
