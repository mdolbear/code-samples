package com.cracking;

/**
 *
 *
 */
public class BinarySubtreeLocator<E> {

    private BinaryTree<E>   mainTree;
    private BinaryTree<E>   candidateSubtree;
    
    private BinaryTreeNode<E> startingNodeInMainTree;
    
    
    /**
     * Answer an instance for the following arguments
     * @param aMainTree
     * @param aCandidateSubtree
     */
    public BinarySubtreeLocator(BinaryTree<E>   aMainTree,
                                BinaryTree<E> aCandidateSubtree) {
        
        super();
        this.setMainTree(aMainTree);
        this.setCandidateSubtree(aCandidateSubtree);
        
    }


    /**
     * Answer my mainTree
     * @return BinaryTree<E>
     */
    protected BinaryTree<E> getMainTree() {
        return mainTree;
    }


    /**
     * Set my mainTree
     * @param mainTree BinaryTree<E>
     */
    protected void setMainTree(BinaryTree<E> mainTree) {
        this.mainTree = mainTree;
    }


    /**
     * Answer my candidateSubtree
     * @return BinaryTree<E>
     */
    protected BinaryTree<E> getCandidateSubtree() {
        return candidateSubtree;
    }


    /**
     * Set my candidateSubtree
     * @param candidateSubtree BinaryTree<E>
     */
    protected void setCandidateSubtree(BinaryTree<E> candidateSubtree) {
        this.candidateSubtree = candidateSubtree;
    }


    /**
     * Answer my startingNoodeInMainTree
     * @return BinaryTreeNode<E>
     */
    public BinaryTreeNode<E> getStartingNodeInMainTree() {
        return startingNodeInMainTree;
    }


    /**
     * Set my startingNoodeInMainTree
     * @param startingNodeInMainTree BinaryTreeNode<E>
     */
    protected void setStartingNodeInMainTree(
            BinaryTreeNode<E> startingNodeInMainTree) {
        this.startingNodeInMainTree = startingNodeInMainTree;
    }

    
    /**
     * Perform subtree test
     * @return boolean
     */
    public boolean hasSubtreeOfMainTree() {
        
        E                       tempRootDataForSubtree;
        BinaryTreeNode<E>       tempCandidateStartingNode;
        boolean                 tempResult = false;
        
        tempRootDataForSubtree = this.getCandidateSubtree().getRootNode().getData();
        tempCandidateStartingNode = this.findStartingNodeInMainTree(this.getMainTree().getRootNode(), 
                                                                    tempRootDataForSubtree);
        this.setStartingNodeInMainTree(null);
        
        while (tempCandidateStartingNode != null && !tempResult) {
            
            tempResult = BinaryTreeUtils.getInstance().areEqual(this.getCandidateSubtree().getRootNode(), 
                                                                tempCandidateStartingNode);
            if (!tempResult) {
                
                tempCandidateStartingNode = tempCandidateStartingNode.getInorderSuccessor();
                tempCandidateStartingNode = this.findStartingNodeInMainTree(tempCandidateStartingNode, 
                                                                            tempRootDataForSubtree);
            }
            else {
                
                this.setStartingNodeInMainTree(tempCandidateStartingNode);
            }
            
        }
        
        return tempResult;
        
    }
    
    
    /**
     * Find starting node in main tree
     * @param aStartingNode BinaryTreeNode<E>
     * @return aData E
     * @return BinaryTreeNode<E>
     */
    protected BinaryTreeNode<E> findStartingNodeInMainTree(BinaryTreeNode<E> aStartingNode,
                                                           E aData) {
        
        BinaryTreeNode<E>    tempResult = null;
        
        if (!this.getMainTree().isEmpty()) {
            
            if (this.getStartingNodeInMainTree() == null) {
                
                tempResult = BinaryTreeUtils.getInstance().unsortedSearchFor(aData, aStartingNode);
            }
            else {
                
                throw new IllegalStateException("Must clear starting node in me before attempting this search");
            }
        }
        
        return tempResult;
        
    }
    
    
}
