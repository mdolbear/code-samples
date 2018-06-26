package com.cracking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


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
     * Answer whether aTreeNode1<E> is equal from aStartingTreeNode<E>
     * @param aTreeNode1<E> BinaryTreeNode<E>
     * @param aCurrentTreeNode<E> BinaryTreeNode<E>
     * @return boolean
     */
    public <E> boolean areEqual(BinaryTreeNode<E> aBinaryTreeNode1,
                                BinaryTreeNode<E> aCurrentTreeNode) {
        
        boolean tempResult = true;
        
        if (tempResult &&
                aBinaryTreeNode1 != null && 
                    aCurrentTreeNode != null) {
            
            if (aBinaryTreeNode1.compareTo(aCurrentTreeNode.getData()) != 0) {
                
                //Unequal contents for the same node position
                tempResult = false;
            }
            else {
                
                tempResult = this.areEqual(aBinaryTreeNode1.getLeftChild(), 
                                           aCurrentTreeNode.getLeftChild());
                if (tempResult) {
                    
                    tempResult = this.areEqual(aBinaryTreeNode1.getRightChild(),
                                               aCurrentTreeNode.getRightChild());
                }
                
            }
        }
        else {
            
            //Unequal tree sizes
            if (!(aBinaryTreeNode1 == null && aCurrentTreeNode == null)) {
                
                tempResult = false;
            }
        }
        
        return tempResult;
        
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
    
    
 
    /**
     * Create depth linked lists
     * @param aRootNode BinaryTreeNode
     * @param aVisitor DepthLinkedLists
     * @param aDepthValue int
     */
    public <E> void createDepthLinkedLists(BinaryTreeNode<E> aNode, 
                                           DepthLinkedLists<E> aVisitor,
                                           int aDepthValue) {
        
        if (aNode != null) {
            
            aVisitor.putTreeNodeInProperList(aNode, aDepthValue);
            
            this.createDepthLinkedLists(aNode.getLeftChild(), aVisitor, aDepthValue+1); //Left
            
            this.createDepthLinkedLists(aNode.getRightChild(), aVisitor, aDepthValue+1); //Right
            
        }
        
        
    }
    
    /**
     * Search aBinaryTreeNode and its children for aData. If I can't find, return null. Otherwise, answer the BinaryTreeNode<E>.
     * Assumes aBinaryTreeNode is at the root of a sorted binary tree.
     * @param aData E
     * @return BinaryTreeNode<E>
     */
    public <E> BinaryTreeNode<E> unsortedSearchFor(E aData,
                                                   BinaryTreeNode<E> aCurrentNode) {
        
        BinaryTreeNode<E>   tempResult = null;
        int                 tempCompareResult;
        
        if (aCurrentNode != null &&
                tempResult == null) {
            
            tempCompareResult = aCurrentNode.compareTo(aData);
            if (tempCompareResult == 0) {
                
                tempResult = aCurrentNode;
            }
            else {
                
               tempResult = this.unsortedSearchFor(aData, aCurrentNode.getLeftChild()); //Look in left subtree
               
               if (tempResult == null) {
                   
                   tempResult = this.unsortedSearchFor(aData, aCurrentNode.getRightChild()); //Look in right subtree if we didn't find it
               }
               
            }
            
        }
        
        return tempResult;
        
    }
    
    
    /**
     * Answer the closest common ancestor of aNode1 and aNode2 or null. This is the brute
     * force approach and uses the aNode.getParent() method.....
     * @param aNode1 BinaryTreeNode<E>
     * @param aNode2 BinaryTreeNode<E>
     */
    public <E> BinaryTreeNode<E> findClosestCommonAncestor(BinaryTreeNode<E> aNode1,
                                                           BinaryTreeNode<E> aNode2) {
        
        List<BinaryTreeNode<E>> tempNode1Ancestors;
        List<BinaryTreeNode<E>> tempNode2Ancestors;
        BinaryTreeNode<E>       tempResult = null;
        
        tempNode1Ancestors = aNode1.getAncestors();
        tempNode2Ancestors = aNode1.getAncestors();
        
        if (!tempNode1Ancestors.isEmpty() && !tempNode2Ancestors.isEmpty()) {
            
            tempNode1Ancestors.retainAll(tempNode2Ancestors);
            if (!tempNode1Ancestors.isEmpty()) {
                
                tempResult = tempNode1Ancestors.get(tempNode1Ancestors.size()-1);
                
            }
        }
        
        return tempResult;
        
    }
    
    /**
     * Create and merge all legal permutations for how a bst tree was created
     * @param aRootArray E[]
     * @param aSubtreeArray1List List<E[]>
     * @param aSubtreeArray2List List<E[]>
     * @return List<E[]>
     */
    public <E> List<E[]> produceAllLegalBSTPermutationsFor(E[] aRootArray, 
                                                           List<E[]> aLeftSubtree, 
                                                           List<E[]> aRightSubtree) {
            
        List<E[]>       tempResults = new ArrayList<E[]>();
        List<E[]>       tempIntermediateResults  = new ArrayList<E[]>();

        if (aLeftSubtree.isEmpty() || aRightSubtree.isEmpty()) {
            
            if (aLeftSubtree.isEmpty()) {
                
                tempIntermediateResults = aRightSubtree;
            }
            else {
                
                tempIntermediateResults = aLeftSubtree;
            }
        }
        else {
            
            for (int i = 0; i < aLeftSubtree.size(); i++) {
                
                for (int j = 0; j < aRightSubtree.size(); j++) {
                    
                    tempIntermediateResults.addAll(this.createArraysFor(aLeftSubtree.get(i), aRightSubtree.get(j)));
                }
            }
            
            
            
        }

        
       if (tempIntermediateResults.isEmpty()) {
           
           tempResults.add(aRootArray);
       }
       else {
           
           for (int i = 0; i < tempIntermediateResults.size(); i++) {
               
               tempResults.add(this.createMergedArrayFrom(aRootArray, tempIntermediateResults.get(i)));
               
           }
           
       }
         
            
        return tempResults;
            
   }
 
    /**
     * Create arrays for
     * @param aRootArray
     * @param aLeftSubtree
     * @param aRightSubtree
     * @return List<E>
     */
    @SuppressWarnings("unchecked")
    protected <E> List<E[]> createArraysFor(E[] aLeftSubtree, 
                                            E[] aRightSubtree) {
        
        List<E[]>   tempResults = new ArrayList<E[]>();
        Stack<E[]>  tempWeavedResults = new Stack<E[]>();
        E[]         tempCurrent;
        
        this.<E>weave((E[])Array.newInstance(Object.class,0), 
                       0, 
                       aLeftSubtree, 
                       0, 
                       aRightSubtree, 
                       tempWeavedResults);
        
        while (!tempWeavedResults.isEmpty()) {
            
            tempCurrent = tempWeavedResults.pop();
            tempResults.add(tempCurrent);
        }
        
        return tempResults;
         
    }


    
    /**
     * Merge two arrays into a single array
     * @param aSubtreeArray1 E[]
     * @param aSubtreeArray2 E[]
     * @return E[]
     */
    @SuppressWarnings("unchecked")
    protected <E> E[] createMergedArrayFrom(E[] aSubtreeArray1,
                                            E[] aSubtreeArray2) {
        
        E[]     tempResult;
        int     tempBaseResultIndex;
        
        tempResult = (E[])Array.newInstance(Object.class, aSubtreeArray1.length + aSubtreeArray2.length);
        
        tempBaseResultIndex = 0;
        this.copyArrayContents(tempResult, tempBaseResultIndex, aSubtreeArray1);
        
        tempBaseResultIndex = aSubtreeArray1.length;
        this.copyArrayContents(tempResult, tempBaseResultIndex, aSubtreeArray2);
                
        
        return tempResult;
    }
    
    
    
    
    /**
     * Copy array contents
     * @param aTargetArray E[]
     * @param aBaseTargetIndex int
     * @param aSourceArray E[]
     */
    protected <E> void copyArrayContents(E[] aTargetArray, 
                                         int aBaseTargetIndex,
                                         E[] aSourceArray) {
        
        for (int i = 0; i < aSourceArray.length; i++) {
            
            aTargetArray[i + aBaseTargetIndex] = aSourceArray[i];
        }
        
    }
    
    /**
     * Weave anArray1 and anArray2, preserving the relative order within each array
     * @param anArray1Index int
     * @param anArray1 E[]
     * @param anArray2Index int
     * @param anArray2 E[]
     * @param aStack Stack<E[]>
     */
    public <E> void weave(E[] aPrefixArray, int anArray1Index, E[] anArray1, int anArray2Index, E[] anArray2, Stack<E[]> aStack) {
        
        if (anArray1Index == anArray1.length) {
            
            aStack.push(this.createMergedArrayFrom(aPrefixArray, 
                                                   Arrays.copyOfRange(anArray2, anArray2Index, anArray2.length)));
        }
        else if (anArray2Index == anArray2.length) {
            
            aStack.push(this.createMergedArrayFrom(aPrefixArray, 
                                                   Arrays.copyOfRange(anArray1, anArray1Index, anArray1.length)));
        }
        else {
            
            this.weave(this.createMergedArrayFrom(aPrefixArray, Arrays.copyOfRange(anArray1, anArray1Index, anArray1Index+1)), 
                       anArray1Index+1, 
                       anArray1, 
                       anArray2Index, 
                       anArray2, 
                       aStack);
            
            this.weave(this.createMergedArrayFrom(aPrefixArray, Arrays.copyOfRange(anArray2, anArray2Index, anArray2Index+1)), 
                       anArray1Index, 
                       anArray1, 
                       anArray2Index+1, 
                       anArray2, 
                       aStack);
        }
        
    }
    
}
