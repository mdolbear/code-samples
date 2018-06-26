package com.cracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/**
 *
 *
 */
public class BinaryTree<E> {

    private BinaryTreeNode<E> rootNode;
    private Comparator<E> comparator;
    
    /**
     * Answer an instance for the following arguments
     */
    public BinaryTree() {
        super();
    }

    /**
     * Answer my rootNode
     * @return BinaryTreeNode<E>
     */
    protected BinaryTreeNode<E> getRootNode() {
        return rootNode;
    }

    /**
     * Set my rootNode
     * @param rootNode BinaryTreeNode<E>
     */
    protected void setRootNode(BinaryTreeNode<E> rootNode) {
        this.rootNode = rootNode;
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
     * Build me from aSortedArray
     * @param aSortedArray E[]
     * 
     */
    public void insertInto(E[] aSortedArray) {
        
        BinaryTreeNode<E>  tempRootNode;
        
        if (aSortedArray.length > 0) {
            
            tempRootNode = new BinaryTreeNode<E>();
            this.setRootNode(tempRootNode);
            
            this.insertInto(tempRootNode,
                            aSortedArray, 
                            this.getComparator(), 
                            0);
        }
        
    }
    
    /**
     * Build me from aSortedArray
     * @param aSortedArray E[]
     * 
     */
    public void createBinarySearchTreeFrom(E[] aSortedArray) {
        
            
        this.createBinarySearchTreeFrom(null,
                                        aSortedArray, 
                                        this.getComparator());
        
    }
    
    
    /**
     * Perform breadth tree creation
     * @param aVertex Vertex<T>
     */
    public void createBinarySearchTreeFrom(BinaryTreeNode<E> aRootNode,
                                           E[] aSortedArray,
                                           Comparator<E> aComparator) {
        
        BinaryTreeNode<E>   tempCurrentNode;
        int                 tempCenterArrayIndex;
        
        if (aSortedArray.length == 0) {
            
            //Get out -- do nothing
        }
        else if (aSortedArray.length == 1) {
            
            tempCurrentNode = new BinaryTreeNode<E>(aSortedArray[0], aComparator);
            this.createNewRootOrAssociateWithExisting(aRootNode, tempCurrentNode);
        }
        else if (aSortedArray.length == 2) {
            
            tempCurrentNode = new BinaryTreeNode<E>(aSortedArray[1], aComparator);
            this.createNewRootOrAssociateWithExisting(aRootNode, tempCurrentNode);
            this.createBinarySearchTreeFrom(tempCurrentNode, 
                                            Arrays.copyOf(aSortedArray, aSortedArray.length-1), 
                                            aComparator);
        }
        else {
            
            tempCenterArrayIndex = aSortedArray.length/2;
            tempCurrentNode = new BinaryTreeNode<E>(aSortedArray[tempCenterArrayIndex], aComparator);
            this.createNewRootOrAssociateWithExisting(aRootNode, tempCurrentNode);
            
            this.createBinarySearchTreeFrom(tempCurrentNode, 
                                            Arrays.copyOf(aSortedArray, tempCenterArrayIndex), 
                                            aComparator);
            
            this.createBinarySearchTreeFrom(tempCurrentNode, 
                                            Arrays.copyOfRange(aSortedArray, tempCenterArrayIndex+1, aSortedArray.length), 
                                            aComparator);
            
        }
        
    }

    /**
     * Create a new node and associate it witht the local root or make aCurrentNode the root
     * @param aCurrentRootNode
     * @param aCurrentNode
     */
    protected void createNewRootOrAssociateWithExisting(BinaryTreeNode<E> aCurrentRootNode,
                                                        BinaryTreeNode<E> aCurrentNode) {
        
        if (aCurrentRootNode == null) {
            
            this.setRootNode(aCurrentNode);
        }
        else {
            
            aCurrentRootNode.associateBasedOnComparison(aCurrentNode);
        }
        
    }
    
    
    /**
     * Perform breadth tree creation
     * @param aVertex Vertex<T>
     */
    public void insertInto(BinaryTreeNode<E> aRootNode,
                           E[] aSortedArray,
                           Comparator<E> aComparator,
                           int aRootIndex) {
        
        Queue<BinaryTreeInsertion<E>> tempQueue = new Queue<BinaryTreeInsertion<E>>();
        BinaryTreeNode<E>             tempCurrentVertex;
        BinaryTreeInsertion<E>        tempCurrentVertexInsertion;
        BinaryTreeInsertion<E>        tempLeftNodeInsertion;
        BinaryTreeNode<E>             tempLeftNode;
        int                           tempLeftChildIndex;
        BinaryTreeInsertion<E>        tempRightNodeInsertion;
        BinaryTreeNode<E>             tempRightNode;
        int                           tempRightChildIndex;
        
        tempCurrentVertex = aRootNode;
        aRootNode.setData(aSortedArray[aRootIndex]);
        aRootNode.setComparator(aComparator);
        tempCurrentVertexInsertion = 
                new BinaryTreeInsertion<E>(tempCurrentVertex,
                                           aRootIndex);
        tempQueue.add(tempCurrentVertexInsertion);
        
        while (!tempQueue.isEmpty()) {
            
            //Visit "breadth" at the same level first
            tempCurrentVertexInsertion = tempQueue.remove();
            tempCurrentVertex = tempCurrentVertexInsertion.getNode();
            
            //Get root index and compute left and right
            aRootIndex = tempCurrentVertexInsertion.getDataIndex();
            tempLeftChildIndex = 2*aRootIndex + 1;
            tempRightChildIndex = 2*aRootIndex + 2;
            
            if (tempLeftChildIndex < aSortedArray.length) {
                
                tempLeftNode = new BinaryTreeNode<E>(aSortedArray[tempLeftChildIndex], aComparator);
                tempLeftNodeInsertion = new BinaryTreeInsertion<E>(tempLeftNode,
                                                                   tempLeftChildIndex);           
                tempCurrentVertex.associateLeftChild(tempLeftNode);
                tempQueue.add(tempLeftNodeInsertion);


            }
                    
            if (tempRightChildIndex < aSortedArray.length) {
                
                tempRightNode = new BinaryTreeNode<E>(aSortedArray[tempRightChildIndex], aComparator);
                tempRightNodeInsertion = new BinaryTreeInsertion<E>(tempRightNode,
                                                                   tempRightChildIndex);           
                tempCurrentVertex.associateRightChild(tempRightNode);
                tempRightNode.setParent(tempCurrentVertex);

                tempQueue.add(tempRightNodeInsertion);
            }
            

        }
        
    }
    
    /**
     * Create depth linked lists
     * @return DepthLinkedLists<E>
     */
    public DepthLinkedLists<E> createDepthLinkedLists() {
        
        DepthLinkedLists<E> tempVisitor;
        
        tempVisitor = new DepthLinkedLists<E>();
        if (this.getRootNode() != null) {
            
            BinaryTreeUtils.getInstance().createDepthLinkedLists(this.getRootNode(), 
                                                                 tempVisitor,
                                                                 0);
        }
        
        return tempVisitor;
        
    }
    
    /**
     * Build me from aSortedArray as an unbalanced tree
     * @param aSortedArray E[]
     * 
     */
    public void createUnbalancedFrom(E[] aSortedArray) {
        
        BinaryTreeNode<E>   tempRootNode;
        BinaryTreeNode<E>   tempCurrentNode;
        BinaryTreeNode<E>   tempNewNode;
        
        if (aSortedArray.length > 0) {
            
            tempRootNode = new BinaryTreeNode<E>(aSortedArray[0], this.getComparator());
            this.setRootNode(tempRootNode);
            
            tempCurrentNode = tempRootNode;
            for (int i = 1; i < aSortedArray.length; i++) {
                
                tempNewNode = new BinaryTreeNode<E>(aSortedArray[i], this.getComparator());
                tempCurrentNode.associateLeftChild(tempNewNode);
                tempCurrentNode = tempNewNode;
                
            }
                
                
        }
        
    }
    
    
    /**
     * Build me from aSortedArray as an unbalanced tree
     * @param aSortedArray E[]
     * 
     */
    public void createUnbalancedToRightFrom(E[] aSortedArray) {
        
        BinaryTreeNode<E>   tempRootNode;
        BinaryTreeNode<E>   tempCurrentNode;
        BinaryTreeNode<E>   tempNewNode;
        
        if (aSortedArray.length > 0) {
            
            tempRootNode = new BinaryTreeNode<E>(aSortedArray[0], this.getComparator());
            this.setRootNode(tempRootNode);
            
            tempCurrentNode = tempRootNode;
            for (int i = 1; i < aSortedArray.length; i++) {
                
                tempNewNode = new BinaryTreeNode<E>(aSortedArray[i], this.getComparator());
                tempCurrentNode.associateRightChild(tempNewNode);
                tempCurrentNode = tempNewNode;
                
            }
                
                
        }
        
    }
    
    
    /**
     * Answer the common ancestor of aNode1 and aNode2 or null if one does not exist
     * @param aNode1 BinaryTreeNode<E>
     * @param aNode2 BinaryTreeNode<E>
     * @return BinaryTreeNode<E>
     */
    public BinaryTreeNode<E> findCommonAncestor(BinaryTreeNode<E> aNode1,
                                               BinaryTreeNode<E> aNode2) {
        
        BinaryTreeNode<E>   tempResult = null;
        E                   tempNode1Data;
        E                   tempNode2Data;
        
        if (aNode1 != null && aNode2 != null) {
            
            tempNode1Data = aNode1.getData();
            tempNode2Data = aNode2.getData();
            
            tempResult = this.getCommonAncestor(this.getRootNode(), 
                                                tempNode1Data, 
                                                tempNode2Data);
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer the common ancestor of aNode1 and aNode2 or null if one does not exist
     * @param aPotentialCommon BinaryTreeNode<E>
     * @param aNode1 BinaryTreeNode<E>
     * @param aNode2 BinaryTreeNode<E>
     * @return BinaryTreeNode<E>
     */
    protected BinaryTreeNode<E> getCommonAncestor(BinaryTreeNode<E> aPotentialCommon,
                                                  E aNode1Data,
                                                  E aNode2Data) {
        
        
        BinaryTreeNode<E>   tempResult = null;
        boolean             tempNode1InLeftSubtree;
        boolean             tempNode1InRightSubtree;
        boolean             tempNode2InLeftSubtree;
        boolean             tempNode2InRightSubtree;
        
        if (aPotentialCommon != null && tempResult == null) {
            
            tempNode1InLeftSubtree = BinaryTreeUtils.getInstance().unsortedSearchFor(aNode1Data, aPotentialCommon.getLeftChild()) != null;
            tempNode1InRightSubtree = BinaryTreeUtils.getInstance().unsortedSearchFor(aNode1Data, aPotentialCommon.getRightChild()) != null;
            
            tempNode2InLeftSubtree = BinaryTreeUtils.getInstance().unsortedSearchFor(aNode2Data, aPotentialCommon.getLeftChild()) != null;
            tempNode2InRightSubtree = BinaryTreeUtils.getInstance().unsortedSearchFor(aNode2Data, aPotentialCommon.getRightChild()) != null;
            
            if (this.areNodesInOppositeSubtrees(tempNode1InLeftSubtree,
                                                tempNode1InRightSubtree, 
                                                tempNode2InLeftSubtree,
                                                tempNode2InRightSubtree)) {
                
                tempResult = aPotentialCommon;
            }
            else if (this.areNodesInSameSubtrees(tempNode1InLeftSubtree, 
                                                 tempNode1InRightSubtree, 
                                                 tempNode2InLeftSubtree, 
                                                 tempNode2InRightSubtree)) {
                
                aPotentialCommon = this.chooseProperChildBasedOnSide(aPotentialCommon, 
                                                                     tempNode1InLeftSubtree);
                
                tempResult = this.getCommonAncestor(aPotentialCommon, aNode1Data, aNode2Data);
                
            }

        }
        
        return tempResult;
        
    }

    /**
     * Choose proper subtree based on side
     * @param aPotentialCommon BinaryTreeNode<E>
     * @param aLeftSide boolean
     * @return BinaryTreeNode<E>
     */
    protected BinaryTreeNode<E> chooseProperChildBasedOnSide(BinaryTreeNode<E> aPotentialCommon, 
                                                             boolean aLeftSide) {
        
        if (aLeftSide) {
            
            aPotentialCommon = aPotentialCommon.getLeftChild();
        }
        else {
            
            aPotentialCommon = aPotentialCommon.getRightChild();
        }
        
        return aPotentialCommon;
        
    }

    /**
     * Answer whether nodes are in opposite subtrees
     * @param aNode1InLeftSubtree
     * @param aNode1InRightSubtree
     * @param aNode2InLeftSubtree
     * @param aNode2InRightSubtree
     * @return boolean
     */
    protected boolean areNodesInOppositeSubtrees(boolean aNode1InLeftSubtree,
                                                 boolean aNode1InRightSubtree, 
                                                 boolean aNode2InLeftSubtree,
                                                 boolean aNode2InRightSubtree) {
        
        return (aNode1InLeftSubtree && aNode2InRightSubtree) ||
                (aNode1InRightSubtree && aNode2InLeftSubtree);
        
    }
    
    /**
     * Answer whether nodes are in same subtrees
     * @param aNode1InLeftSubtree
     * @param aNode1InRightSubtree
     * @param aNode2InLeftSubtree
     * @param aNode2InRightSubtree
     * @return boolean
     */
    protected boolean areNodesInSameSubtrees(boolean aNode1InLeftSubtree,
                                             boolean aNode1InRightSubtree, 
                                             boolean aNode2InLeftSubtree,
                                             boolean aNode2InRightSubtree) {
        
        return (aNode1InLeftSubtree && aNode2InLeftSubtree) ||
                (aNode1InRightSubtree && aNode2InRightSubtree);
        
    }
    
    /**
     * Produce permutations that I could have been built from
     * @param aPermutations List<E[]>
     */
    public List<E[]> producePermutationsThatICouldHaveBeenConstructedFrom(StringBuilder aMessageLog) {
        
        List<E[]> tempPermutations = new ArrayList<E[]>();
        
        if (!this.isEmpty()) {
            
            tempPermutations =
             this
                .producePermutationsThatICouldHaveBeenConstructedFrom(this.getRootNode(),
                                                                      tempPermutations, 
                                                                      aMessageLog);
        }
        
        return tempPermutations;
    }
    
    /**
     * Produce permutations that I could have been built from
     * @param aPermutations List<E[]>
     */
    public List<E[]> producePermutationsThatICouldHaveBeenConstructedFrom(BinaryTreeNode<E> aNode,
                                                                         List<E[]> aPermutations,
                                                                         StringBuilder aMessageLog) {
        
        List<E[]>   tempLeftSubtreePermutations;
        E[]         tempRootArray;
        List<E[]>   tempRightSubtreePermutations;
        
        if (aNode == null) {
            
            aMessageLog.append("Node is null");
            aMessageLog.append(System.getProperty("line.separator"));
            aPermutations = new ArrayList<E[]>();
            
        }
        else  {
            
            if (aNode.isLeaf()) {
                
                aMessageLog.append("Leaf node: " + aNode.toString());
                aMessageLog.append(System.getProperty("line.separator"));
                aPermutations = new ArrayList<E[]>();
                tempRootArray = aNode.createArrayWithMyData();
                aPermutations.add(tempRootArray);
                
                
            }
            else {
                aMessageLog.append("Starting construction for local root: " + aNode.toString());
                aMessageLog.append(System.getProperty("line.separator"));
                
                tempLeftSubtreePermutations = this.producePermutationsThatICouldHaveBeenConstructedFrom(aNode.getLeftChild(),
                                                                          aPermutations, 
                                                                          aMessageLog);
                    
                tempRightSubtreePermutations = this.producePermutationsThatICouldHaveBeenConstructedFrom(aNode.getRightChild(),
                                                                          aPermutations, 
                                                                          aMessageLog);
                
                tempRootArray = aNode.createArrayWithMyData();
                aPermutations = 
                        BinaryTreeUtils.getInstance().produceAllLegalBSTPermutationsFor(tempRootArray, 
                                                                                        tempLeftSubtreePermutations, 
                                                                                        tempRightSubtreePermutations);
            }

        }
        
        return aPermutations;
        
        
    }
    
    /**
     * Answer whether or not I am empty
     * @return boolean
     */
    public boolean isEmpty() {
        
        return this.getRootNode() == null;
    }
    
}
