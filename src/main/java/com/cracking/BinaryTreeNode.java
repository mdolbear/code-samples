package com.cracking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;




/**
 *
 *
 */
public class BinaryTreeNode<E> {

    private BinaryTreeNode<E> leftChild;
    private BinaryTreeNode<E> rightChild;
    private E data;
    private Comparator<E> comparator;
    private BinaryTreeNode<E> parent;
    
    /**
     * Answer an instance for the following arguments
     */
    public BinaryTreeNode() {
        
        super();
        
    }
    
    /**
     * Answer an instance for the following arguments
     */
    public BinaryTreeNode(E aData, 
                          Comparator<E> aComparator) {
        
        super();
        this.setData(aData);
        this.setComparator(aComparator);
        
    }

    /**
     * Answer my leftChild
     * @return BinaryTreeNode<E>
     */
    public BinaryTreeNode<E> getLeftChild() {
        return leftChild;
    }

    
    /**
     * Associate based on compare
     * @param aNode BinaryTreeNode<E>
     */
    public void associateBasedOnComparison(BinaryTreeNode<E> aNode) {
        
        if (this.compareTo(aNode.getData()) < 0) {
            
            this.associateRightChild(aNode);
        }
        else {
            
            this.associateLeftChild(aNode);
        }
        
    }
    
    
    /**
     * Associate left child
     * @param leftChild BinaryTreeNode<E>
     */
    public void associateLeftChild(BinaryTreeNode<E> leftChild) {
        
        this.setLeftChild(leftChild);
        leftChild.setParent(this);
    }
    
    
    /**
     * Set my leftChild
     * @param leftChild BinaryTreeNode<E>
     */
    protected void setLeftChild(BinaryTreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Answer my rightChild
     * @return BinaryTreeNode<E>
     */
    public BinaryTreeNode<E> getRightChild() {
        return rightChild;
    }

    /**
     * Associate right child
     * @param rightChild BinaryTreeNode<E>
     */
    public void associateRightChild(BinaryTreeNode<E> rightChild) {
        
        this.setRightChild(rightChild);
        rightChild.setParent(this);
    }
    
    /**
     * Set my rightChild
     * @param rightChild BinaryTreeNode<E>
     */
    protected void setRightChild(BinaryTreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Answer my data
     * @return E
     */
    public E getData() {
        return data;
    }

    /**
     * Set my data
     * @param data E
     */
    protected void setData(E data) {
        this.data = data;
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

 
    /**
     * Compare to anotherNode. Answer -, 0, + depending on whether I am before, equal, or after respectively
     * @param aData E
     * @return int
     */
    public int compareTo(E aData) {
        
        return this.getComparator().compare(this.getData(), aData);
        
        
    }
    
    /**
     * Answer my string representation
     * @return String
     */
    public String toString() {
        
        StringBuilder  tempBuilder = new StringBuilder();
        
        tempBuilder.append("data: " );
        if (this.getData() == null) {
            
            tempBuilder.append(" null ");
        }
        else {
            
            tempBuilder.append(" " + this.getData().toString() + " ");
        }
        
        return tempBuilder.toString();
        
    }
    
 
    /**
     * Answer whether or not I am balanced
     * @return boolean
     */
    public boolean isBalanced() {
        
        int tempBalanceFactor;
        
        tempBalanceFactor = this.getBalanceFactor();
        
        return Math.abs(tempBalanceFactor) <= 1;
        
    }
    
    
    /**
     * Answer my balance factor
     * @return int
     */
    protected int getBalanceFactor() {
        
        return this.getHeight(this.getLeftChild()) -
                this.getHeight(this.getRightChild());
        
    }
    
    /**
     * Answer the height of aNode
     * @param aNode BinaryNode<E>
     * @return int
     */
    protected int getHeight(BinaryTreeNode<E> aNode) {
        
        int tempResult = 0;
        
        if (aNode != null) {
            
            tempResult = aNode.getHeight();
        }
        
        return tempResult;
    }
    
    /**
     * Answer my height based on the height of my left and right subtrees
     * @return int
     */
    protected int getHeight() {
        
        return  Math.max(this.getHeight(this.getLeftChild()), 
                         this.getHeight(this.getRightChild())) + 1;
        
    }

    /**
     * Answer my inorder successor. This assumes I am sorted.
     * @return BinnaryNode<E>
     */
    public BinaryTreeNode<E> getInorderSuccessor() {
        
        BinaryTreeNode<E> tempResult = null;
        
        //Follow path to my successor
        if (this.getRightChild() != null) {
            
            tempResult = this.getRightChild().getLeftmostNode();
        }
        else {
            
            tempResult = this.getInorderSuccessorWhenNoRightSubtree();
            
        }
        
        return tempResult;
                                                
    }

    /**
     * Answer my inorder successor when there is no right subtree
     * @return
     */
    protected BinaryTreeNode<E> getInorderSuccessorWhenNoRightSubtree() {
        
        BinaryTreeNode<E> tempResult;
        BinaryTreeNode<E> tempParent;
        BinaryTreeNode<E> tempChild;
        
        tempParent = this.getParent();
        tempChild = this;
        
        while (tempParent != null && 
                    tempParent.getRightChild() != null && 
                        tempParent.getRightChild().equals(tempChild)) {
            
            tempChild = tempParent;
            tempParent = tempParent.getParent();
        }
        
        tempResult = tempParent;
        
        return tempResult;
        
    }
    
    /**
     * Answer my leftmost node, possibly myself if I have no left subtree
     * @return BinaryTreeNode<E>
     */
    protected BinaryTreeNode<E> getLeftmostNode() {
        
        BinaryTreeNode<E>   tempResult;
        
        tempResult = this;
        
        while (tempResult.getLeftChild() != null) {
            
            tempResult = tempResult.getLeftChild();
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer my parent
     * @return BinaryTreeNode<E>
     */
    public BinaryTreeNode<E> getParent() {
        return parent;
    }

    /**
     * Set my parent
     * @param parent BinaryTreeNode<E>
     */
    protected void setParent(BinaryTreeNode<E> parent) {
        this.parent = parent;
    }
    
    
    /**
     * Answer my ancestors as a list.  My closest ancestor will be at the end of the list and my oldest ancestor
     * will be at the beginning
     * @return List<BinaryTreeNode<E>>
     */
    public List<BinaryTreeNode<E>> getAncestors() {
        
        List<BinaryTreeNode<E>> tempAncestors = new ArrayList<BinaryTreeNode<E>>();
        BinaryTreeNode<E>       tempCurrentAncestor;
        
        tempCurrentAncestor = this.getParent();
        while (tempCurrentAncestor != null) {
            
            tempAncestors.add(tempCurrentAncestor);
            tempCurrentAncestor = tempCurrentAncestor.getParent();
        }
        
        Collections.reverse(tempAncestors);
        
        return tempAncestors;
        
    }


    /**
     * Create array with local root
     * @return E[]
     */
    @SuppressWarnings("unchecked")
    public E[] createArrayWithMyData() {
        
        E[]         tempRootArray;
        
        tempRootArray = (E[])Array.newInstance(Object.class,1);
        tempRootArray[0] = this.getData();
        
        return tempRootArray;
        
    }
    
    /**
     * Answer whether or not I am a leaf node
     * @return boolean
     */
    public boolean isLeaf() {
        
        return this.getLeftChild() == null &&
                this.getRightChild() == null;
        
    }
    
}
