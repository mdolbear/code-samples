package com.cracking;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

/**
 *
 *
 */
public class BinaryTreeTest {

    /**
     * Answer an instance for the following arguments
     */
    public BinaryTreeTest() {
        super();
    }
    
    /**
     * Test insert of sorted array to create complete binary tree
     */
    @Test
    public void insertAndCreateCompleteBinaryTreeTest() {
        
        Integer[] tempSortedArray =
                                {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
                                 new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};
        BinaryTree<Integer> tempResult;
        StringBuilder       tempBuilder;
        
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.createBinarySearchTreeFrom(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal: " + tempBuilder);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traversePreorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Preorder tree traversal: " + tempBuilder);
        
        
    }
    
    /**
     * Test depth linked lists
     */
    @Test
    public void depthLinkedListsTest() {
        
        Integer[] tempSortedArray =
                                {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
                                 new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};
        BinaryTree<Integer> tempResult;
        StringBuilder       tempBuilder;
        DepthLinkedLists<Integer>   tempLists;
        
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.insertInto(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal: " + tempBuilder);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traversePreorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Preorder tree traversal: " + tempBuilder);
        
        tempLists = tempResult.createDepthLinkedLists();
        for (LinkedNode<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> aNode: tempLists.getLinkedLists().values()) {
            
            tempBuilder = new StringBuilder();
            aNode.dumpListTo(tempBuilder);
            System.out.println("List: " + tempBuilder.toString());
            
        }
            
    }
    
    
    
    /**
     * Create Integer comparator
     * @return Comparator<Integer>
     */
    protected Comparator<Integer> createComparator() {
        
        return (Integer i1, Integer i2) -> (i1.intValue() - i2.intValue());
    }
    
    /**
     * Test isBalanced binary tree
     */
    @Test
    public void isBalancedTest() {
        
        Integer[] tempSortedArray =
                                {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
                                 new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};
        BinaryTree<Integer> tempResult;
        StringBuilder       tempBuilder;
        boolean             tempIsBalanced;
        
        //Test for balanced tree
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.createBinarySearchTreeFrom(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal: " + tempBuilder);
        
        
        tempIsBalanced = tempResult.getRootNode().isBalanced();
        assertTrue("Tree should be balanced", tempIsBalanced);
        
        //Create and test against unbalanced tree
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.createUnbalancedFrom(tempSortedArray);
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Unbalanced inorder tree traversal: " + tempBuilder);
        
        tempIsBalanced = tempResult.getRootNode().isBalanced();
        assertTrue("Tree should not be balanced", !tempIsBalanced);
        
    }
    
    /**
     * Test is binary search tree
     */
    @Test
    public void isBinarySearchTreeTest() {
        
        Integer[] tempSortedArray =
                                {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
                                 new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};
        BinaryTree<Integer> tempResult;
        StringBuilder       tempBuilder;
        boolean             tempIsBinarySearchTree;
        
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.createBinarySearchTreeFrom(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal: " + tempBuilder);
        
        
        tempIsBinarySearchTree = 
         (new BinarySearchTreeCheck<Integer>(tempResult.getComparator())).isBinarySearchTree(tempResult.getRootNode());
        assertTrue("Tree should be bst", tempIsBinarySearchTree);
        
        //Create and test against unbalanced tree
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.createUnbalancedToRightFrom(tempSortedArray);
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Unbalanced inorder tree traversal: " + tempBuilder);
        
        tempIsBinarySearchTree = 
                (new BinarySearchTreeCheck<Integer>(tempResult.getComparator())).isBinarySearchTree(tempResult.getRootNode());
        assertTrue("Tree should be bst", tempIsBinarySearchTree);
        
        //Create tree that is not a bst
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.insertInto(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal: " + tempBuilder);
        
        tempIsBinarySearchTree = 
                (new BinarySearchTreeCheck<Integer>(tempResult.getComparator())).isBinarySearchTree(tempResult.getRootNode());
               assertTrue("Tree should not be bst", !tempIsBinarySearchTree);
        
    }
    
    /**
     * Test inorder successor
     */
    @Test
    public void indorderSuccessorTest() {
        
        Integer[] tempSortedArray =
                                {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
                                 new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};
        BinaryTree<Integer>         tempResult;
        StringBuilder               tempBuilder;
        BinaryTreeNode<Integer>     tempStartingNode;
        BinaryTreeNode<Integer>     tempSuccessorNode;
        
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.insertInto(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal: " + tempBuilder);
        
        
        //Successor tests - 6 follows 2
        tempStartingNode = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(2), tempResult.getRootNode());
        assertTrue("Didn't find starting node", tempStartingNode != null&& (tempStartingNode.compareTo(new Integer(2)) == 0));
        
        tempSuccessorNode = tempStartingNode.getInorderSuccessor();
        assertTrue("Didn't find successor", tempSuccessorNode != null && (tempSuccessorNode.compareTo(new Integer(6)) == 0));
        
        //Successor tests - 2 follows 5
        tempStartingNode = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(5), tempResult.getRootNode());
        assertTrue("Didn't find starting node", tempStartingNode != null&& (tempStartingNode.compareTo(new Integer(5)) == 0));
        
        tempSuccessorNode = tempStartingNode.getInorderSuccessor();
        assertTrue("Didn't find successor", tempSuccessorNode != null && (tempSuccessorNode.compareTo(new Integer(2)) == 0));
        
        //Successor tests - 6 should not have a successor since its the farthest right
        tempStartingNode = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(6), tempResult.getRootNode());
        assertTrue("Didn't find starting node", tempStartingNode != null&& (tempStartingNode.compareTo(new Integer(6)) == 0));
        
        tempSuccessorNode = tempStartingNode.getInorderSuccessor();
        assertTrue("Found successor for 6 - wrong", tempSuccessorNode == null);
                
        //Successor tests - 1 follows 8
        tempStartingNode = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(8), tempResult.getRootNode());
        assertTrue("Didn't find starting node", tempStartingNode != null&& (tempStartingNode.compareTo(new Integer(8)) == 0));
        
        tempSuccessorNode = tempStartingNode.getInorderSuccessor();
        assertTrue("Didn't find successor", tempSuccessorNode != null && (tempSuccessorNode.compareTo(new Integer(1)) == 0));
        
        //Successor tests - 0 follows 4
        tempStartingNode = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(4), tempResult.getRootNode());
        assertTrue("Didn't find starting node", tempStartingNode != null&& (tempStartingNode.compareTo(new Integer(4)) == 0));
        
        tempSuccessorNode = tempStartingNode.getInorderSuccessor();
        assertTrue("Didn't find successor", tempSuccessorNode != null && (tempSuccessorNode.compareTo(new Integer(0)) == 0));
        
        //Successor tests - 5 follows 0
        tempStartingNode = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(0), tempResult.getRootNode());
        assertTrue("Didn't find starting node", tempStartingNode != null&& (tempStartingNode.compareTo(new Integer(0)) == 0));
        
        tempSuccessorNode = tempStartingNode.getInorderSuccessor();
        assertTrue("Didn't find successor", tempSuccessorNode != null && (tempSuccessorNode.compareTo(new Integer(5)) == 0));
        
        
    }
    
    /**
     * Brute force find common ancestor test
     */
    @Test
    public void bruteForceCommonAncestorTest() {

        Integer[] tempSortedArray =
            {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
             new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};
        BinaryTree<Integer>         tempResult;
        StringBuilder               tempBuilder;
        BinaryTreeNode<Integer>     tempNode1;
        BinaryTreeNode<Integer>     tempNode2;
        BinaryTreeNode<Integer>     tempCommonAncestor;
        
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.insertInto(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal: " + tempBuilder);

        //Find common ancestor test
        tempNode1 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(2), tempResult.getRootNode());
        assertTrue("Didn't find node 1", tempNode1 != null&& (tempNode1.compareTo(new Integer(2)) == 0));
        
        tempNode2 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(1), tempResult.getRootNode());
        assertTrue("Didn't find node 2", tempNode2 != null && (tempNode2.compareTo(new Integer(1)) == 0));
        
        tempCommonAncestor = BinaryTreeUtils.getInstance().findClosestCommonAncestor(tempNode1, tempNode2);
        assertTrue("Root should be common ancestor", tempCommonAncestor != null && (tempCommonAncestor.compareTo(new Integer(0)) == 0));     
        System.out.println("Common ancestor: " + tempCommonAncestor.toString());
        
        //Find common ancestor test
        tempNode1 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(4), tempResult.getRootNode());
        assertTrue("Didn't find node 1", tempNode1 != null&& (tempNode1.compareTo(new Integer(4)) == 0));
        
        tempNode2 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(7), tempResult.getRootNode());
        assertTrue("Didn't find node 2", tempNode2 != null && (tempNode2.compareTo(new Integer(7)) == 0));
        
        tempCommonAncestor = BinaryTreeUtils.getInstance().findClosestCommonAncestor(tempNode1, tempNode2);
        assertTrue("Root should be common ancestor", tempCommonAncestor != null && (tempCommonAncestor.compareTo(new Integer(1)) == 0));     
        System.out.println("Common ancestor: " + tempCommonAncestor.toString());
        
        //Find common ancestor test
        tempNode1 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(0), tempResult.getRootNode());
        assertTrue("Didn't find node 1", tempNode1 != null&& (tempNode1.compareTo(new Integer(0)) == 0));
        
        tempNode2 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(7), tempResult.getRootNode());
        assertTrue("Didn't find node 2", tempNode2 != null && (tempNode2.compareTo(new Integer(7)) == 0));
        
        tempCommonAncestor = BinaryTreeUtils.getInstance().findClosestCommonAncestor(tempNode1, tempNode2);
        assertTrue("Root should be common ancestor", tempCommonAncestor == null);     
        System.out.println("Common ancestor is null");
        
        
    }
    
    /**
     * Less brute force find common ancestor test
     */
    @Test
    public void lessBruteForceCommonAncestorTest() {

        Integer[] tempSortedArray =
            {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
             new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};
        BinaryTree<Integer>         tempResult;
        StringBuilder               tempBuilder;
        BinaryTreeNode<Integer>     tempNode1;
        BinaryTreeNode<Integer>     tempNode2;
        BinaryTreeNode<Integer>     tempCommonAncestor;
        
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.insertInto(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal: " + tempBuilder);

        //Find common ancestor test
        tempNode1 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(2), tempResult.getRootNode());
        assertTrue("Didn't find node 1", tempNode1 != null&& (tempNode1.compareTo(new Integer(2)) == 0));
        
        tempNode2 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(1), tempResult.getRootNode());
        assertTrue("Didn't find node 2", tempNode2 != null && (tempNode2.compareTo(new Integer(1)) == 0));
        
        tempCommonAncestor = tempResult.findCommonAncestor(tempNode1, tempNode2);
        assertTrue("Root should be common ancestor", tempCommonAncestor != null && (tempCommonAncestor.compareTo(new Integer(0)) == 0));     
        System.out.println("Common ancestor: " + tempCommonAncestor.toString());
        
        //Find common ancestor test
        tempNode1 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(4), tempResult.getRootNode());
        assertTrue("Didn't find node 1", tempNode1 != null&& (tempNode1.compareTo(new Integer(4)) == 0));
        
        tempNode2 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(7), tempResult.getRootNode());
        assertTrue("Didn't find node 2", tempNode2 != null && (tempNode2.compareTo(new Integer(7)) == 0));
        
        tempCommonAncestor = tempResult.findCommonAncestor(tempNode1, tempNode2);
        assertTrue("Root should be common ancestor", tempCommonAncestor != null && (tempCommonAncestor.compareTo(new Integer(1)) == 0));     
        System.out.println("Common ancestor: " + tempCommonAncestor.toString());
        
        //Find common ancestor test
        tempNode1 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(0), tempResult.getRootNode());
        assertTrue("Didn't find node 1", tempNode1 != null&& (tempNode1.compareTo(new Integer(0)) == 0));
        
        tempNode2 = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(7), tempResult.getRootNode());
        assertTrue("Didn't find node 2", tempNode2 != null && (tempNode2.compareTo(new Integer(7)) == 0));
        
        tempCommonAncestor = tempResult.findCommonAncestor(tempNode1, tempNode2);
        assertTrue("Root should be common ancestor", tempCommonAncestor == null);     
        System.out.println("Common ancestor is null");
        
        
    }
    
    /**
     * BST permutations test 1
     */
    @Test
    public void bstPermutationsTest1() {
        
//        Integer[] tempSortedArray =
//            {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
//             new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};
//        Integer[] tempSortedArray =
//            {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4)};
        Integer[] tempSortedArray =
            {new Integer(0), new Integer(1), new Integer(2), new Integer(3)};
        BinaryTree<Integer>         tempResult;
        StringBuilder               tempBuilder;
        List<Integer[]>             tempResults;
        
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.createBinarySearchTreeFrom(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal: " + tempBuilder);
        
        tempBuilder = new StringBuilder();
        tempResults = tempResult.producePermutationsThatICouldHaveBeenConstructedFrom(tempBuilder);
        this.dumpPermutationResults(tempResults);
        System.out.println("Message log: " + tempBuilder);
        
    }
    
    /**
     * BST permutations test 2
     */
    @Test
    public void bstPermutationsTest2() {
        
//        Integer[] tempSortedArray =
//            {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
//             new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};
        Integer[] tempSortedArray =
            {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4)};

        BinaryTree<Integer>         tempResult;
        StringBuilder               tempBuilder;
        List<Integer[]>             tempResults;
        
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.createBinarySearchTreeFrom(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal: " + tempBuilder);
        
        tempBuilder = new StringBuilder();
        tempResults = tempResult.producePermutationsThatICouldHaveBeenConstructedFrom(tempBuilder);
        this.dumpPermutationResults(tempResults);
        System.out.println("Message log: " + tempBuilder);
        
    }
    
    
    /**
     * BST permutations test 3
     */
    @Test
    public void bstPermutationsTest3() {
        
        Integer[] tempSortedArray =
            {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
             new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};

        BinaryTree<Integer>         tempResult;
        StringBuilder               tempBuilder;
        List<Integer[]>             tempResults;
        
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.createBinarySearchTreeFrom(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal: " + tempBuilder);
        
        tempBuilder = new StringBuilder();
        tempResults = tempResult.producePermutationsThatICouldHaveBeenConstructedFrom(tempBuilder);
        this.dumpPermutationResults(tempResults);
        System.out.println("Message log: " + tempBuilder);
        
    }
    
    
    
    /**
     * Dump all array permutations
     * @param aResults List<Integer[]>
     */
    protected void dumpPermutationResults(List<Integer[]> aResults) {
        
        System.out.println("Results of all permutations");
        for (int i = 0; i < aResults.size(); i++) {
             
            System.out.print("Permutation[" + i + "]= ");
            for (int j = 0; j <  ((Object[])aResults.get(i)).length; j++) {
               
               System.out.print(((Object[])aResults.get(i))[j].toString());
               if (j < ((Object[])aResults.get(i)).length - 1) {
                   
                   System.out.print(",");
               }
               
            }
            
            System.out.println();
            
        }
        
    }
    
    
    /**
     * Weave test...Tests the array weave which is important to create the bst permutations
     */
    @Test
    public <E> void weaveTest() {
        
        Integer[] tempArray1 = {new Integer(1), new Integer(2)};
        Integer[] tempArray2 = {new Integer(3), new Integer(4)};
        Stack<Integer[]> tempStack = new Stack<Integer[]>();
        Integer[]  tempPrefixArray = new Integer[0];
        
        BinaryTreeUtils.getInstance().<Integer>weave(tempPrefixArray, 0, tempArray1, 0, tempArray2, tempStack);
        this.dumpStackOfIntegerArrays(tempStack);
        
    }
    
    /**
     * Dump stack of Integer arrays
     * @param aStack Stack<Integer>
     */
    protected void dumpStackOfIntegerArrays(Stack<Integer[]> aStack) {
        
        Object[]   tempCurrent;
        int         j = 0;
        
        while (!aStack.isEmpty()) {
            
            tempCurrent = aStack.pop();
            System.out.print("Array[" + j + "]=");
            for (int i = 0; i < tempCurrent.length; i++) {
                
                System.out.print(tempCurrent[i].toString());
                if (i < tempCurrent.length - 1) {
                    
                    System.out.print(",");
                }
            }
            
            System.out.println();
            j++;
            
        }
        
    }
    
    /**
     * isEqual test
     */
    @Test
    public void isEqualTest() {
        
        Integer[] tempSortedArray =
            {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
             new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};
        Integer[] tempSubtreeSortedArray =
            {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4)};
        Integer[] tempSubtreeSortedArrayNotEqual =
            {new Integer(0), new Integer(1), new Integer(2),  new Integer(4)};
        BinaryTree<Integer>             tempResult;
        StringBuilder                   tempBuilder;
        BinaryTreeNode<Integer>         tempStartingPoint;
        BinaryTree<Integer>             tempSubtree;
        boolean                         tempEquals;
        BinaryTree<Integer>             tempNotEqualSubtree;
        
        //Main tree
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.createBinarySearchTreeFrom(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Main tree Inorder tree traversal: " + tempBuilder);
        
        //Equal subtree
        tempSubtree = new BinaryTree<Integer>();
        tempSubtree.setComparator(this.createComparator());
        
        tempSubtree.createBinarySearchTreeFrom(tempSubtreeSortedArray);
        assertTrue("Tree not set up", tempSubtree.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempSubtree.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal on equal tree: " + tempBuilder);
        
        //Not equal subtree
        tempNotEqualSubtree = new BinaryTree<Integer>();
        tempNotEqualSubtree.setComparator(this.createComparator());
        
        tempNotEqualSubtree.createBinarySearchTreeFrom(tempSubtreeSortedArrayNotEqual);
        assertTrue("Tree not set up", tempNotEqualSubtree.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempNotEqualSubtree.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal on not equal tree: " + tempBuilder);
        
        //Perform successful equal test
        tempStartingPoint = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(2), tempResult.getRootNode());
        assertTrue("Could not find starting point", tempStartingPoint != null);
        
        tempEquals = BinaryTreeUtils.getInstance().areEqual(tempSubtree.getRootNode(), tempStartingPoint);
        assertTrue("Trees should be equal from starting point", tempEquals);
        
        //Perform unsuccessful equal test
        tempStartingPoint = BinaryTreeUtils.getInstance().unsortedSearchFor(new Integer(2), tempResult.getRootNode());
        assertTrue("Could not find starting point", tempStartingPoint != null);
        
        tempEquals = BinaryTreeUtils.getInstance().areEqual(tempNotEqualSubtree.getRootNode(), tempStartingPoint);
        assertTrue("Trees should not be equal from starting point", !tempEquals);
        
    }
    
    /**
     * isEqual test
     */
    @Test
    public void findSubtreeTest() {
        
        Integer[] tempSortedArray =
            {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4),
             new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9)};
        Integer[] tempSubtreeSortedArray =
            {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4)};
        Integer[] tempSubtreeSortedArrayNotEqual =
            {new Integer(0), new Integer(1), new Integer(2),  new Integer(4)};
        BinaryTree<Integer>             tempResult;
        StringBuilder                   tempBuilder;
        BinaryTree<Integer>             tempSubtree;
        boolean                         tempEquals;
        BinaryTree<Integer>             tempNotEqualSubtree;
        BinarySubtreeLocator<Integer>   tempLocator;
        
        //Main tree
        tempResult = new BinaryTree<Integer>();
        tempResult.setComparator(this.createComparator());
        
        tempResult.createBinarySearchTreeFrom(tempSortedArray);
        assertTrue("Tree not set up", tempResult.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempResult.getRootNode(), tempBuilder);
        System.out.println("Main tree Inorder tree traversal: " + tempBuilder);
        
        //Equal subtree
        tempSubtree = new BinaryTree<Integer>();
        tempSubtree.setComparator(this.createComparator());
        
        tempSubtree.createBinarySearchTreeFrom(tempSubtreeSortedArray);
        assertTrue("Tree not set up", tempSubtree.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempSubtree.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal on equal tree: " + tempBuilder);
        
        //Not equal subtree
        tempNotEqualSubtree = new BinaryTree<Integer>();
        tempNotEqualSubtree.setComparator(this.createComparator());
        
        tempNotEqualSubtree.createBinarySearchTreeFrom(tempSubtreeSortedArrayNotEqual);
        assertTrue("Tree not set up", tempNotEqualSubtree.getRootNode() != null);
        
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traverseInorder(tempNotEqualSubtree.getRootNode(), tempBuilder);
        System.out.println("Inorder tree traversal on not equal tree: " + tempBuilder);
        
        //Perform successful equal test
        tempLocator = new BinarySubtreeLocator<Integer>(tempResult, tempSubtree);
        tempEquals = tempLocator.hasSubtreeOfMainTree();
        assertTrue("Trees should be equal from starting point", tempEquals && tempLocator.getStartingNodeInMainTree() != null);
        
        //Perform unsuccessful equal test
        tempLocator = new BinarySubtreeLocator<Integer>(tempResult, tempNotEqualSubtree);
        tempEquals = tempLocator.hasSubtreeOfMainTree();
        assertTrue("Trees should not be equal from starting point",  
                        !tempEquals && tempLocator.getStartingNodeInMainTree() == null);
        
        
    }

}
