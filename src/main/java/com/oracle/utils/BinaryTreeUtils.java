package com.oracle.utils;

import java.util.Stack;

import com.oracle.samples.BinaryTreeNode;

/**
 *
 *
 */
public class BinaryTreeUtils {

    private static BinaryTreeUtils instance;
    
    /**
     * Answer my default instance
     * @return BinaryTreeUtils
     */
    public synchronized static BinaryTreeUtils getInstance() {
        
        if (instance == null) {
            
            instance = new BinaryTreeUtils();
        }
        
        return instance;
        
    }
    
    
    /**
     * Answer an instance for the following arguments
     */
    protected BinaryTreeUtils() {
        super();
    }


    /**
     * Perform inorder traversal
     * @param aRootNode BinaryTreeNode
     * @param aBuilder StringBuilder
     */
    public <E> void traverseInorder(BinaryTreeNode<E> aNode, 
                                    StringBuilder aBuilder) {
        
        if (aNode != null) {
            
            this.traverseInorder(aNode.getLeftChild(), aBuilder); //Left
            aBuilder.append(" " + aNode.getData().toString() + " "); //Data
            this.traverseInorder(aNode.getRightChild(), aBuilder); //Right
            
        }
        
        
    }
    
    /**
     * Perform preorder traversal
     * @param aRootNode BinaryTreeNode
     * @param aBuilder StringBuilder
     */
    public <E> void traversePreorder(BinaryTreeNode<E> aNode, 
                                     StringBuilder aBuilder) {
        
        if (aNode != null) {
            
            aBuilder.append(" " + aNode.getData().toString() + " "); //Data
            this.traversePreorder(aNode.getLeftChild(), aBuilder); //Left
            this.traversePreorder(aNode.getRightChild(), aBuilder); //Right
            
        }
        
        
    }
    
    /**
     * Perform postorder traversal
     * @param aRootNode BinaryTreeNode
     * @param aBuilder StringBuilder
     */
    public <E> void traversePostorder(BinaryTreeNode<E> aNode, 
                                      StringBuilder aBuilder) {
        
        if (aNode != null) {
            
            this.traversePostorder(aNode.getLeftChild(), aBuilder); //Left
            this.traversePostorder(aNode.getRightChild(), aBuilder); //Right
            aBuilder.append(" " + aNode.getData().toString() + " "); //Data
            
        }
        
        
    }
    
    
    
    
    
    
    /**
     * Perform inorder traversal iteratively
     * @param aRootNode BinaryTreeNode
     * @param aBuilder StringBuilder
     */
    public <E> void traverseInorderIteratively(BinaryTreeNode<E> aNode, 
                                               StringBuilder aBuilder) {
        
        Stack<BinaryTreeNode<E>> tempStack = new Stack<BinaryTreeNode<E>>();
        BinaryTreeNode<E>        tempCurrent;
        
        tempCurrent = aNode;     
        while (!tempStack.isEmpty() || tempCurrent != null) {
                            
            if (tempCurrent != null) {
                
                //Left
                tempStack.push(tempCurrent);
                tempCurrent = tempCurrent.getLeftChild();
                
            }                
            else if (tempCurrent == null && !tempStack.isEmpty() ) {
                
                //Data
                tempCurrent = tempStack.pop();
                aBuilder.append(" " + tempCurrent.getData().toString() + " ");
                
                //Right
                tempCurrent = tempCurrent.getRightChild();
                
            }
           
            
        }
        
        
    }
    
    /**
     * Perform preorder traversal iteratively
     * @param aRootNode BinaryTreeNode
     * @param aBuilder StringBuilder
     */
    public <E> void traversePreorderIteratively(BinaryTreeNode<E> aNode, 
                                               StringBuilder aBuilder) {
        
        Stack<BinaryTreeNode<E>> tempStack = new Stack<BinaryTreeNode<E>>();
        BinaryTreeNode<E>        tempCurrent;
        
        tempCurrent = aNode;
        tempStack.push(tempCurrent);
        while (!tempStack.isEmpty()) {
            
            //Data
            tempCurrent = tempStack.pop();
            aBuilder.append(" " + tempCurrent.getData().toString() + " ");
            
            //This is backwards since we want the left to be pulled off the stack first above on the next iteration -- we push right then left
            //
            //Right
            if (tempCurrent.getRightChild() != null) {
                
                tempStack.push(tempCurrent.getRightChild());
            }
            
            //Left
            if (tempCurrent.getLeftChild() != null) {
                
                tempStack.push(tempCurrent.getLeftChild());
            }
            
        }
        
        
    }
    
    /**
     * Search aBinaryTreeNode and its children for aData. If I can't find, return null. Otherwise, answer the BinaryTreeNode<E>.
     * Assumes aBinaryTreeNode is at the root of a sorted binary tree.
     * @param aData E
     * @return BinaryTreeNode<E>
     */
    public <E> BinaryTreeNode<E> searchFor(E aData,
                                           BinaryTreeNode<E> aCurrentNode) {
        
        BinaryTreeNode<E>   tempResult = null;
        int                 tempCompareResult;
        
        if (aCurrentNode != null) {
            
            tempCompareResult = aCurrentNode.compareTo(aData);
            if (tempCompareResult == 0) {
                
                tempResult = aCurrentNode;
            }
            else if (tempCompareResult > 0) {
                
               tempResult = this.searchFor(aData, aCurrentNode.getLeftChild()); //Find lesser values in tree
            }
            else {
                
                tempResult = this.searchFor(aData, aCurrentNode.getRightChild()); //Find greater values in tree
            }
        }
        
        return tempResult;
        
    }
    
 
    
}
