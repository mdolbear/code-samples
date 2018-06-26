package com.oracle.samples;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.oracle.utils.BinaryTreeUtils;

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
     * Test tree traversals
     */
    @Test
    public void testTreeTraversals() {
        
        BinaryTree<String>  tempTree;
        String              tempResult;
        StringBuilder       tempBuilder;
        
        tempTree = this.constructParameterizedBinaryTree();
        assertTrue("Tree not constructed", tempTree != null);
        
        //Test tree traversals -- recursive
        tempResult = tempTree.traverseInorder();
        assertTrue("Inorder traversal failed", !tempResult.isEmpty());
        System.out.println("Inorder result: " + tempResult);
        
        tempResult = tempTree.traversePostorder();
        assertTrue("Postorder traversal failed", !tempResult.isEmpty());
        System.out.println("Postorder result: " + tempResult);
        
        tempResult = tempTree.traversePreorder();
        assertTrue("Preorder traversal failed", !tempResult.isEmpty());
        System.out.println("Preorder result: " + tempResult);
        
        //Utility class traversals
        tempBuilder = new StringBuilder();
        tempTree = this.constructParameterizedBinaryTree();
        BinaryTreeUtils.getInstance().traversePreorder(tempTree.getRootNode(), 
                                                       tempBuilder);
        System.out.println("Preorder result utility class: " + tempBuilder.toString());
        
        
        tempBuilder = new StringBuilder();
        tempTree = this.constructParameterizedBinaryTree();
        BinaryTreeUtils.getInstance().traverseInorder(tempTree.getRootNode(), 
                                                      tempBuilder);
        System.out.println("Inorder result utility class: " + tempBuilder.toString());
        
        tempBuilder = new StringBuilder();
        tempTree = this.constructParameterizedBinaryTree();
        BinaryTreeUtils.getInstance().traversePostorder(tempTree.getRootNode(), 
                                                        tempBuilder);
        System.out.println("Postorder result utility class: " + tempBuilder.toString());
        
        
        
        //Test tree traversals -- iterative
        tempBuilder = new StringBuilder();
        tempTree = this.constructParameterizedBinaryTree();
        BinaryTreeUtils.getInstance().traverseInorderIteratively(tempTree.getRootNode(), 
                                                                 tempBuilder);
        System.out.println("Inorder result iteratively: " + tempBuilder.toString());
        
        tempTree = this.constructParameterizedBinaryTree();
        tempBuilder = new StringBuilder();
        BinaryTreeUtils.getInstance().traversePreorderIteratively(tempTree.getRootNode(), 
                                                                 tempBuilder);
        System.out.println("Preorder result iteratively: " + tempBuilder.toString());
        
        
        
    }
    
    /**
     * Copy tree test
     */
    @Test
    public void copyTreeTest() {
        
        BinaryTree<String>  tempOldTree;
        BinaryTree<String>  tempNewTree;
        String      tempOldResult;
        String      tempNewResult;
        
        tempOldTree = this.constructParameterizedBinaryTree();
        tempNewTree = tempOldTree.copy();
        
        
        //Dump old tree
        tempOldResult = tempOldTree.traverseInorder();
        assertTrue("Inorder traversal failed", !tempOldResult.isEmpty());
        System.out.println("Old result: " + tempOldResult);
        
        //Dump old tree
        tempNewResult = tempNewTree.traverseInorder();
        assertTrue("Inorder traversal failed", !tempNewResult.isEmpty());
        System.out.println("Copied result: " + tempNewResult);
        
        //Determine that both are equal
        assertTrue("Copied tree not equal with original", tempNewTree.equals(tempOldTree));
        
        
    }
    
    /**
     * Test equals
     */
    @Test
    public void testEquals() {
       
        BinaryTree<String>  tempTree1;
        BinaryTree<String>  tempTree2;
        String      tempDump;
        
        tempTree1 = this.constructParameterizedBinaryTree();
        tempTree2 = this.constructAnotherParameterizedBinaryTree();
        
        //Determine that both are equal
        tempDump = tempTree1.traverseInorder();
        System.out.println("Tree1: " + tempDump);
        
        tempDump = tempTree2.traverseInorder();
        System.out.println("Tree2: " + tempDump);
        
        assertTrue("Copied tree equal with original", !tempTree1.equals(tempTree2));
        
    }
    
    /**
     * Construct sorted tree
     */
    @Test
    public void constructSortedTreeTest() {
        
        String[]                            tempArr = {"Z", "Y", "Q", "R", "S", "D", "B", "A"};
        List<String>                        tempList;
        BinaryTree<String>                  tempTree;
        String                              tempDump;
        BinaryTreeNode<String>              tempNode;
        String[]                            tempArr2 = {"Zebra", "Zoe", "Yokahama", "Quest", "Quest",
                                                        "Robert", "Sam", "Dolbear", "Dudley", "Bat", "Adam", "Atom"};
        BinaryTreeNode<String>              tempCurrent;
        TreeTraversal                       tempTraversal;
        
        //First test
        tempList = Arrays.asList(tempArr);
        tempTree = this.constructSorted(tempList,
                                        this.getComparator(),
                                        this.getEmptyObject());
        
        
        
        tempDump = tempTree.traverseInorder();
        System.out.println("Tree inorder: " + tempDump);
        
        tempCurrent = BinaryTreeUtils.getInstance().searchFor("S", tempTree.getRootNode());
        assertTrue("Did not find", tempCurrent != null);
        
        tempTraversal = new TreeTraversal();
        tempTree.getRootNode().getDepth(tempTraversal);
        System.out.println("Maximum depth: " + tempTraversal.getMaxDepth());
        
        //Second test
        tempList = Arrays.asList(tempArr2);
        tempTree = this.constructSorted(tempList,
                                        this.getComparator(),
                                        this.getEmptyObject());
        
        tempDump = tempTree.traverseInorder();
        System.out.println("Tree inorder: " + tempDump);
        
        tempTraversal = new TreeTraversal();
        tempTree.getRootNode().getDepth(tempTraversal);
        System.out.println("Maximum depth: " + tempTraversal.getMaxDepth());
        
        
        //Test inorder successor method
        tempNode = tempTree.getRootNode();
        tempCurrent = tempNode.getInorderSuccessor();
        System.out.println("Node picked to start from: " + tempNode.toString());
        System.out.println("Inorder successor: " + tempCurrent.toString());
        assertTrue("Inorder successor failed", tempCurrent != null);
        
        
        tempNode = tempTree.performSearch("Dolbear");
        assertTrue("Missing node 1", tempNode != null && 
                                        tempNode.getData() != null && 
                                            tempNode.getData().equals("Dolbear"));
        
        tempCurrent = BinaryTreeUtils.getInstance().searchFor("Dolbear", tempTree.getRootNode());
        assertTrue("Did not find", tempCurrent != null);
        
        tempCurrent = tempTree.getRootNode().getInorderSuccessor();
        System.out.println("Node picked to start from: " + tempTree.getRootNode().toString());
        System.out.println("Inorder successor: " + tempCurrent.toString());
        assertTrue("Inorder successor failed", tempCurrent != null);
        
        
        tempCurrent = tempTree.performSearch("Sam");
        tempCurrent = tempCurrent.getInorderSuccessor();
        System.out.println("Node picked to start from: " + tempTree.performSearch("Sam"));
        System.out.println("Inorder successor: " + tempCurrent.toString());
        assertTrue("Inorder successor failed", tempCurrent != null);
        
        tempNode = tempTree.performSearch("Quest");
        assertTrue("Missing node 2", tempNode != null && 
                                        tempNode.getData() != null && 
                                            tempNode.getData().equals("Quest"));
        
        tempNode = tempTree.performSearch("Atom");
        assertTrue("Missing node 3", tempNode != null && 
                                        tempNode.getData() != null && 
                                            tempNode.getData().equals("Atom"));
        
        tempNode = tempTree.performSearch("Zebra");
        assertTrue("Missing node 4", tempNode != null && 
                                        tempNode.getData() != null && 
                                            tempNode.getData().equals("Zebra"));
        
        tempNode = tempTree.performSearch("Zoo");
        assertTrue("Found node not there", tempNode == null);
        
        
        //Test unbalanced tree part
        tempList = new ArrayList<String>();
        tempList.add("Abraham");
        tempTree.performInsertSort(tempList,
                                   this.getComparator());
        
        tempDump = tempTree.traverseInorder();
        System.out.println("Tree inorder: " + tempDump);
        
        tempList = new ArrayList<String>();
        tempList.add("Abe");
        tempTree.performInsertSort(tempList,
                                   this.getComparator());
        
        tempDump = tempTree.traverseInorder();
        System.out.println("Tree inorder: " + tempDump);
        
        tempList = new ArrayList<String>();
        tempList.add("Aaron");
        tempTree.performInsertSort(tempList,
                                   this.getComparator());
        
        tempDump = tempTree.traverseInorder();
        System.out.println("Tree inorder: " + tempDump);
        
        
        
    }
    
    /**
     * Create a linked list from a binary tree test
     */
    @Test
    public void createLinkedListFromBinaryTreeTest() {
        
        String[]    tempArr2 = {"Zebra", "Zoe", "Yokahama", "Quest", "Quest",
                                "Robert", "Sam", "Dolbear", "Dudley", "Bat", "Adam", "Atom"};
        List<String>                        tempList;
        BinaryTree<String>                  tempTree;
        String                              tempDump;
        LinkedList<String, String>          tempLinkedList;
        
        tempList = Arrays.asList(tempArr2);
        tempTree = this.constructSorted(tempList,
                                        this.getComparator(),
                                        this.getEmptyObject());
        
        tempDump = tempTree.traverseInorder();
        System.out.println("Tree inorder: " + tempDump);
        
        tempLinkedList = tempTree.asLinkedList();
        System.out.println("Linked List: " + tempLinkedList.dumpContents());
        
    }
    
    
    /**
     * Construct sorted tree
     */
    @Test
    public void deleteTest() {
        
        List<String>                        tempList;
        BinaryTree<String>                  tempTree;
        String                              tempDump;
        BinaryTreeNode<String>                  tempNode;
        String[]                            tempArr2 = {"Zebra", "Zoe", "Yokahama", "West", "Quest",
                                                        "Robert", "Sam", "Dolbear", "Dudley", "Bat", "Adam", "Atom"};
        BinaryTreeNode<String>                  tempCurrent;
        String                              tempData;
        int                                 tempTreeSize;
        
        
        
        //Second test
        tempList = Arrays.asList(tempArr2);
        tempTree = this.constructSorted(tempList,
                                        this.getComparator(),
                                        this.getEmptyObject());
        
        tempDump = tempTree.traverseInorder();
        System.out.println("Tree inorder: " + tempDump);
        
        //Test inorder successor method
        tempNode = tempTree.getRootNode();
        tempCurrent = tempNode.getInorderSuccessor();
        System.out.println("Node picked to start from: " + tempNode.toString());
        System.out.println("Inorder successor: " + tempCurrent.toString());
        assertTrue("Inorder successor failed", tempCurrent != null);
        
        
        tempNode = tempTree.performSearch("Dolbear");
        assertTrue("Missing node 1", tempNode != null && 
                                        tempNode.getData() != null && 
                                            tempNode.getData().equals("Dolbear"));
        
        tempCurrent = tempTree.getRootNode().getInorderSuccessor();
        System.out.println("Node picked to start from: " + tempTree.getRootNode().toString());
        System.out.println("Inorder successor: " + tempCurrent.toString());
        assertTrue("Inorder successor failed", tempCurrent != null);
        
        //Simple delete test
        tempTree.deleteNodeWith("Dolbear");
        tempNode = tempTree.performSearch("Dolbear");
        assertTrue("Delete failed", tempNode == null);
        
        tempDump = tempTree.traverseInorder();
        System.out.println("Tree inorder after delete: " + tempDump);
        
        //Delete nodes one at a time --- starting with new tree every time
        tempTreeSize= tempTree.getSize();
        for (int i = 0; i < tempTreeSize; i++) {
            
            tempList = Arrays.asList(tempArr2);
            tempTree = this.constructSorted(tempList,
                                            this.getComparator(),
                                            this.getEmptyObject());
            tempData = tempList.get(i);
            tempTree.deleteNodeWith(tempData);
            
            tempNode = tempTree.performSearch(tempData);
            assertTrue("Delete failed", tempNode == null);
            
        }
        
        //Delete root node from existing tree
        tempList = Arrays.asList(tempArr2);
        tempTree = this.constructSorted(tempList,
                                        this.getComparator(),
                                        this.getEmptyObject());
        
        System.out.println();
        System.out.println("Starting tree: " + tempTree);
        System.out.println();
        tempTreeSize= tempTree.getSize();
        for (int i = 0; i < tempTreeSize; i++) {
            
            tempData = tempTree.getRootNode().getData();
            tempTree.deleteNodeWith(tempData);
            
            System.out.println();
            System.out.println("Just deleted node with data: " + tempData);
            System.out.println("Current state of tree: " + tempTree);
            System.out.println("Current tree size: " + tempTree.getSize());
            System.out.println();
            
            tempNode = tempTree.performSearch(tempData);
            assertTrue("Delete failed", tempNode == null);
            
        }
        
        
        
        
    }
    
    
    /**
     * Test heap sort
     */
    @Test
    public void testHeapSort() {
        
        List<String>        tempInputList;
        List<String>        tempResult;
        String[]            tempInput = {"Eric","Joe","Jane","Aki","Tucker","Manuel","Javier","Sam",
                                         "Betty", "Zoe", "MoonUnit", "Anna", "Yoshiko", "Mike", "Harold"};

        tempInputList = Arrays.asList(tempInput);
        System.out.println("InitialList: " + tempInputList.toString());
        tempResult = this.performHeapSort(tempInputList, 
                                          this.getComparator(), 
                                          this.getEmptyObject());
        
        System.out.println();
        System.out.println("Results of sort in descending order: " + tempResult.toString());
        
    }
    
    
    /**
     * Construct ParameterizedBinary tree
     * @return ParameterizedBinaryTree<String>
     */
    protected BinaryTree<String> constructParameterizedBinaryTree() {
        
        BinaryTreeNode<String>  tempLNode;
        BinaryTreeNode<String>  tempRNode;
        BinaryTreeNode<String>  tempParent;
        
        tempParent = new BinaryTreeNode<String>("**", this.getComparator(), null);
        tempLNode = new BinaryTreeNode<String>("B", this.getComparator(), tempParent);
        tempRNode = new BinaryTreeNode<String>("C", this.getComparator(), tempParent);
        
        tempParent.setLeftChild(tempLNode);
        tempParent.setRightChild(tempRNode);
        tempRNode = tempParent;
        
        tempParent = new BinaryTreeNode<String>("/", this.getComparator(), null);
        tempRNode.setParent(tempParent);
        tempLNode = new BinaryTreeNode<String>("A", this.getComparator(), tempParent);
        tempParent.setLeftChild(tempLNode);
        tempParent.setRightChild(tempRNode);
        tempLNode = tempParent;
        
        tempParent = new BinaryTreeNode<String>("*", this.getComparator(),null);
        tempLNode.setParent(tempParent);
        tempParent.setLeftChild(tempLNode);
        tempRNode = new BinaryTreeNode<String>("D", this.getComparator(),tempParent);
        tempParent.setRightChild(tempRNode);
        tempLNode = tempParent;
        
        tempParent = new BinaryTreeNode<String>("+", this.getComparator(),null);
        tempLNode.setParent(tempParent);
        tempRNode = new BinaryTreeNode<String>("E", this.getComparator(), tempParent);
        tempParent.setLeftChild(tempLNode);
        tempParent.setRightChild(tempRNode);
        
        return new BinaryTree<String>(tempParent, 
                                      this.getComparator(),
                                      this.getEmptyObject());
        
    }
    
    /**
     * Construct ParameterizedBinary tree
     * @return ParameterizedBinaryTree<String>
     */
    protected BinaryTree<String> constructAnotherParameterizedBinaryTree() {
        
        BinaryTreeNode<String>  tempLNode;
        BinaryTreeNode<String>  tempRNode;
        BinaryTreeNode<String>  tempParent;
        
        tempParent = new BinaryTreeNode<String>("**", this.getComparator(),null);
        tempLNode = new BinaryTreeNode<String>("B", this.getComparator(), tempParent);
        tempRNode = new BinaryTreeNode<String>("C", this.getComparator(), tempParent);
        
        tempParent.setLeftChild(tempLNode);
        tempParent.setRightChild(tempRNode);
        tempRNode = tempParent;
        
        tempParent = new BinaryTreeNode<String>("/", this.getComparator(), null);
        tempRNode.setParent(tempParent);
        tempLNode = new BinaryTreeNode<String>("A", this.getComparator(), tempParent);
        tempParent.setLeftChild(tempLNode);
        tempParent.setRightChild(tempRNode);
        tempLNode = tempParent;
        
        tempParent = new BinaryTreeNode<String>("+", this.getComparator(), null);
        tempLNode.setParent(tempParent);
        tempParent.setLeftChild(tempLNode);
        
        tempRNode = new BinaryTreeNode<String>("D", this.getComparator(), tempParent);
        tempParent.setRightChild(tempRNode);
        tempLNode = tempParent;
        
        tempParent = new BinaryTreeNode<String>("+", this.getComparator(), null);
        tempLNode.setParent(tempParent);
        tempRNode = new BinaryTreeNode<String>("E", this.getComparator(), tempParent);
        tempParent.setLeftChild(tempLNode);
        tempParent.setRightChild(tempRNode);
        
        return new BinaryTree<String>(tempParent,
                                      this.getComparator(),
                                      this.getEmptyObject());
        
    }
    
 
    /**
     * Answer my comparator
     * @return Comparator<String>
     */
    protected Comparator<String> getComparator() {
        
        return new Comparator<String>() {
            
            public int compare(String aValue1, String aValue2) {
                
                return aValue1.compareTo(aValue2);
            }
        };
        
    }
    
    /**
     * Answer an empty value for String
     * @return String
     */
    protected String getEmptyObject() {
        
        return new String();
    }

    
    /**
     * Perform heap sort
     * @param aList List<E>
     * @return List<E>
     */
    protected List<String> performHeapSort(List<String> aList,
                                           Comparator<String> aComparator,
                                           String anEmptyObject) {
        
        List<String>                         tempResult;
        BinaryTree<String>      tempTree;
        
        tempTree = new BinaryTree<String>(aList, 
                                          aComparator, 
                                          anEmptyObject);
        tempResult = tempTree.performHeapSort();
        
        return tempResult;
        
    }
    
    
    /**
     * Construct sorted binary tree
     * @param aDatas List<String>
     * @return BinaryTree
     */
    protected BinaryTree<String> constructSorted(List<String> aDatas,
                                                              Comparator<String> aComparator,
                                                              String anEmptyObject) {
        
        BinaryTree<String>         tempTree;
        
        tempTree = new BinaryTree<String>(aComparator, anEmptyObject);
        tempTree.performInsertSort(aDatas, aComparator);
        
        return tempTree;
        
    }
        
}
