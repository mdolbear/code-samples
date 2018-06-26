package com.oracle.samples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 *
 */
public class BinaryTree<E> {
    
    private BinaryTreeNode<E> rootNode;
    private E emptyObject;
    private Comparator<E> comparator;
    
    
    
    /**
     * Answer an instance for the following arguments
     * @param aRootNode ParameterizedBinaryNode<E>
     */
    public BinaryTree(BinaryTreeNode<E> aRootNode,
                      Comparator<E> aComparator,
                      E anEmptyObject) {
        
       this();
       this.setRootNode(aRootNode);
       this.setComparator(aComparator);
       this.setEmptyObject(anEmptyObject);
       this.numberMePreorder();
       
    }

    /**
     * Answer an instance for the following arguments
     */
    public BinaryTree() {
       super();
    }
    
    /**
     * Answer an instance for the following arguments
     * @param aList List<E>
     */
    public BinaryTree(List<E> aList,
                      Comparator<E> aComparator,
                      E anEmptyObject) {
        
       this();
       this.setComparator(aComparator);
       this.setEmptyObject(anEmptyObject);
       this.initializeAsComplete(aList);
    }
    
    /**
     * Answer an instance for the following arguments
     * @param aComparator Comparator<E>
     * @param anEmptyObject E
     */
    public BinaryTree(Comparator<E> aComparator,
                      E anEmptyObject) {
        
       this();
       this.setComparator(aComparator);
       this.setEmptyObject(anEmptyObject);

    }
    
    
    
    
    /**
     * Initialize complete
     * @param aData List<Eg>
     */
    protected void initializeAsComplete(List<E> aData) {
        
        int                             tempDepth;
        BinaryTreeNode<E>                   tempRoot;
        List<E>                         tempData;
        
        if (!aData.isEmpty()) {
            
            tempData = new ArrayList<E>(aData);
            tempDepth = (int)(Math.log10((double)aData.size())/Math.log10(2d)) + 1;
            tempRoot = new BinaryTreeNode<E>(this.getComparator(), null);
            this.setRootNode(tempRoot);
            tempRoot.buildComplete(tempData, tempDepth);
            
            this.numberMePreorder();
        }
        
    }
    
    /**
     * Number me inorder
     * @#return TreeTraversal
     */
    public TreeTraversal numberMePreorder() {
        
        TreeTraversal   tempTraversal = null;
        
        if (this.getRootNode() != null) {
            
            tempTraversal = new TreeTraversal();
            this.getRootNode().numberMePreorder(tempTraversal);
        }
        
        return tempTraversal;
        
    }
    
    /**
     * Perform heap sort
     * @return aList List<E>
     */
    public List<E> performHeapSort() {
        
        List<E>        tempResult = new ArrayList<E>();
        int            tempSize;
        
        tempSize = this.getSize();
        for (int i = 0; i < tempSize; i++) {
            
            this.getRootNode().orderAsHeap();
            
            System.out.println();
            System.out.println("Result Set: " + tempResult.toString());
            System.out.println("Remaining Tree Before Delete: " + this.toString());
            
            if (this.getRootNode() != null) {
                
                tempResult.add(this.getRootNode().getData());
                System.out.println("About to delete: " + this.getRootNode());
                this.deleteNodeWith(this.getRootNode().getData());
                
            }
            System.out.println("Remaining Tree After Delete: " + this.toString());
            System.out.println();
            
            
        }
        
        return tempResult;
        
    }
    
    /**
     * Perform insert sort
     * @param aDatas List<E>
     * @param aComparator Comparator<E>
     */
    public void performInsertSort(List<E> aDatas,
                                  Comparator<E> aComparator) {
        
        BinaryTreeNode<E>         tempNode;
        
        for (E aData: aDatas) {
            
            if (this.isEmpty()) {
                
                tempNode = this.createTreeNode(aData,
                                              aComparator,
                                              null);
                this.setRootNode(tempNode);
            }
            else {
                
                tempNode = this.insertSorted(this.getRootNode(), aData);
                tempNode.setParent(null);
                this.setRootNode(tempNode);
            }
            
        }
        
        this.getRootNode().numberMePreorder(new TreeTraversal());
        
    }
    
    /**
     * Insert aNode into me based on sort order. Answer a BinaryNode<E> that was inserted
     * and null if a node for aData already exists
     * @param aData E
     * @return BinaryNode<E>
     */
    public BinaryTreeNode<E> insertSorted(BinaryTreeNode<E> aNode,
                                          E aData) {
        
        int                 tempCompareResult;
        BinaryTreeNode<E>   tempResult; 
        
        
        if (aNode == null) {
            
            tempResult = this.createTreeNode(aData);
        }
        else {
            
            tempCompareResult  = aNode.compareTo(aData);
            if (tempCompareResult != 0) {
                
                
                if (tempCompareResult > 0) {
                                       
                        tempResult = this.insertSorted(aNode.getLeftChild(), aData);
                        aNode.setLeftChild(tempResult);

                }
                else {
                                            
                        tempResult = this.insertSorted(aNode.getRightChild(), aData);
                        aNode.setRightChild(tempResult);
                    
                }
                
                tempResult.setParent(aNode);
                tempResult = this.balance(aNode, aData);
                
                
            }
            else {
                
                tempResult = aNode; //Answer the node that already has this data
            }
            
        }
        
        return tempResult;

        
    }

    /**
     * Answer a new instance of BinaryTreeNode<E>
     * @param aData
     * @return BinaryTreeNode<E>
     */
    protected BinaryTreeNode<E> createTreeNode(E aData) {
        
        return new BinaryTreeNode<E>(aData, 
                                     this.getComparator(),
                                     null);
        
    }
    
    /**
     * Create tree node
     * @param aData E
     * @param aComparator Comparator<E>
     * @param aParent BinaryTreeNode<E> aParent
     */
    protected BinaryTreeNode<E> createTreeNode(E aData,
                                               Comparator<E> aComparator,
                                               BinaryTreeNode<E> aParent) {
        
        return new BinaryTreeNode<E>(aData,
                                     aComparator,
                                     aParent);
    }
    
    /**
     * Balance me. Return the new root node
     */
    protected BinaryTreeNode<E> balance(BinaryTreeNode<E> aNode, E aData) {
        
        BinaryTreeNode<E> tempResult;
        int           tempBalance;
        
        //Default return if in balance
        tempResult = aNode;
        
        //Update height and compute balance factor for me
        tempResult.setHeight(Math.max(tempResult.getHeight(tempResult.getLeftChild()),
                                      tempResult.getHeight(tempResult.getRightChild())) + 1);
        
        
        tempBalance = tempResult.getBalanceFactor();
        
        //Perform balancing if I am unbalanced
        
        //LL case
        if ( (tempBalance > 1 ) &&
                    (tempResult.getLeftChild().compareTo(aData) > 0)) {
            
            tempResult = tempResult.rightRotate();
        }
        //RR case
        else if ((tempBalance < -1 ) &&
                        (tempResult.getRightChild().compareTo(aData) < 0)) {
            
            tempResult = tempResult.leftRotate();
        }
        //Left Right Case
        else if ((tempBalance > 1) &&
                    (tempResult.getLeftChild().compareTo(aData) < 0)) {
                
                tempResult.setLeftChild(tempResult.getLeftChild().leftRotate());
                tempResult = tempResult.rightRotate();
            
            
        }
        //Right Left Case
        else if ((tempBalance < -1 ) && 
                    (tempResult.getRightChild().compareTo(aData) > 0)) {
                
                tempResult.setRightChild(tempResult.getRightChild().rightRotate());
                tempResult = tempResult.leftRotate();
            
        }
        
        
        return tempResult;
    }
    
    
    
    /**
     * Order as heap sort
     */
    protected void orderAsHeap() {
        
        if (this.getRootNode() != null) {
            
            this.getRootNode().orderAsHeap();
        }
        
    }

    /**
     * Answer my root node
     * @return ParameterizedBinaryNode<E>
     */
    protected BinaryTreeNode<E> getRootNode() {
        return rootNode;
    }

    /**
     * Set my root node
     * @param rootNode ParameterizedBinaryNode<E>
     */
    protected void setRootNode(BinaryTreeNode<E> rootNode) {
        this.rootNode = rootNode;
    }

    
    
    /**
     * Traverse inorder
     * @return String
     */
    public String traverseInorder() {
        
        StringBuilder tempBuilder;
        
        tempBuilder = new StringBuilder();
        if (this.getRootNode() != null) {
            
            this.getRootNode().traverseInorder(tempBuilder);
        }
        
       return tempBuilder.toString();
        
    }
    
    /**
     * Traverse preorder
     */
    public String traversePreorder() {
        
        StringBuilder tempBuilder;
        
        tempBuilder = new StringBuilder();
        if (this.getRootNode() != null) {
            
            this.getRootNode().traversePreorder(tempBuilder);
        }
        
        return tempBuilder.toString();
    }
    
    
    /**
     * Traverse postorder
     */
    public String traversePostorder() {
        
        StringBuilder tempBuilder;
        
        tempBuilder = new StringBuilder();
        if (this.getRootNode() != null) {
            
            this.getRootNode().traversePostorder(tempBuilder);
        }
        
        return tempBuilder.toString();
        
    }
    
    /**
     * Copy tree
     */
    public BinaryTree<E> copy() {
        
        BinaryTreeNode<E>  tempNewRoot;
        BinaryTreeNode<E>  tempOldRoot;
        BinaryTree<E>  tempResult;
        
        tempOldRoot = this.getRootNode();
        tempNewRoot = tempOldRoot.copyTree();
        
        tempResult = new BinaryTree<E>(tempNewRoot,
                                                    this.getComparator(),
                                                    this.getEmptyObject());
        
        return tempResult;
        
    }
    
    /**
     * Answer whether or not I am equal to anotherTree
     * @param anotherTree ParameterizedBinaryTree<E>
     * @return boolean
     */
    public boolean equals(Object anotherTree) {
        
        boolean     tempResult = false;
        BinaryTree<E>  tempAnotherTree;
        
        if (anotherTree != null &&
                anotherTree instanceof BinaryTree) {
            
            tempAnotherTree = (BinaryTree<E>)anotherTree;
            if (tempAnotherTree.isEmpty() && this.isEmpty()) {
                tempResult = true;
            }
            else if (!tempAnotherTree.isEmpty() &&
                        !this.isEmpty()) {
                
                tempResult = this.getRootNode().allNodesEqual(tempAnotherTree.getRootNode());
            }
        }
        
        return tempResult;
        
    }
    
    
    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    public boolean isEmpty() {
        
        return this.getRootNode() == null;
    }
    
    /**
     * Answer my size
     * @return int
     */
    public int getSize() {
        
        TreeTraversal   tempTraversal;
        int             tempResult = 0;
        
        tempTraversal = this.numberMePreorder();
        
        if (tempTraversal != null) {
            
            tempResult = tempTraversal.getIndex();
        }
        
        return tempResult;
    }
    
    /**
     * Answer my node at anIndex
     * @param anIndex int
     * @return ParameterizedBinaryNode<E>
     */
    public BinaryTreeNode<E> getNodeAt(int anIndex) {
        
        BinaryTreeNode<E>  tempResult = null;
        
        if (this.getRootNode() != null) {
            
            tempResult = this.getRootNode().getNodeAt(anIndex);
        }
        
        return tempResult;
    }
    
    /**
     * Answer my string representation after an inorder tree traversal
     * @return String
     */
    public String toString() {
        
        String              tempResult;
        TreeTraversal       tempTraversal;
        
        tempTraversal = this.numberMePreorder();
        if (tempTraversal == null) {
            
            tempResult = "No root node";
        }
        else {
            tempResult = tempTraversal.toString();
        }
        
        return tempResult;
    }


    /**
     * Answer my emptyObject
     * @return E
     */
    protected E getEmptyObject() {
        return emptyObject;
    }


    /**
     * Set my emptyObject
     * @param emptyObject E
     */
    protected void setEmptyObject(E emptyObject) {
        this.emptyObject = emptyObject;
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
     * Perform search. Answer a BinaryNode that contains aData or null
     * @param aData E
     * @return BinaryNode<E>
     */
    public BinaryTreeNode<E> performSearch(E aData) {
        
        BinaryTreeNode<E>   tempNode = null;
        
        if (this.getRootNode() != null) {
            
            tempNode = this.getRootNode().search(aData, this.getSize());
        }
        
        return tempNode;
    }
    
    /**
     * Delete node with E data
     * @param aData E
     */
    public void deleteNodeWith(E aData) {
        
        if (!this.isEmpty()) {
          
            if (this.getSize() == 1 &&
                    this.getRootNode().getData().equals(aData)) {
                
                this.setRootNode(null);
            }
            else {
                
                this.getRootNode().deleteNodeWith(aData);
                
                
            }
            
        }
        
        
    }

    /**
     * Answer whether aNode is the last node in the tree
     * @return boolean
     */
    protected boolean isLastNodeInTree() {
        
        return this.getRootNode() != null &&
                    this.getSize() < 1;
        
    }
    
    /**
     * Answer myself as a linked list
     * @return LinedList<E,E>
     */
    public LinkedList<E,E> asLinkedList() {
        
        LinkedList<E,E> tempResult = new LinkedList<E,E>();
        
        if (!this.isEmpty()) {
            
            this.getRootNode().asLinkedList(tempResult);
        }
        
        return tempResult;
        
    }
    
    /**
     * Implementation of an interactive tree insert for me
     * @param aNewNode ColoredBinaryTeeNode<E>
     */
    public void insertSortedIterative(BinaryTreeNode<E> aNewNode) {
        
        BinaryTreeNode<E> tempX;
        BinaryTreeNode<E> tempY = null;
        
        tempX = this.getRootNode();
        
        //Find position where node must be inserted
        while (tempX != null) {
            
            tempY = tempX;
            if (aNewNode.compareTo(tempX) < 0) {
                
                tempX = tempX.getLeftChild();
            }
            else {
                
                tempX = tempX.getRightChild();
            }
            
        }
        
        //Perform insertion
        aNewNode.setParent(tempY);        
        if (tempY == null) {
            
            this.setRootNode(aNewNode);
        }
        else if (aNewNode.compareTo(tempY) < 0) {
            
            tempY.setLeftChild(aNewNode);
        }
        else {
            
            tempY.setRightChild(aNewNode);
        }
        
        
    }
    
    
}
