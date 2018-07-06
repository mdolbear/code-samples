package com.cracking.huffmancodes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.cracking.BinaryTree;
import com.cracking.BinaryTreeNode;

/**
 *
 *
 */
public class HuffmanPrefixCodeGenerator {

    private String[] codeCharacters;
    private int[] frequencies;
    private BinaryTree<CodeElement> resultingTree;
    
    //Constants
    protected String LEFT_TREE_VALUE = "0";
    protected String RIGHT_TREE_VALUE = "1";
    
    /**
     * Answer an instance for the following arguments
     */
    public HuffmanPrefixCodeGenerator() {
       super();
    }

    /**
     * Create code mapping for 
     * @param aCodeCharacters String[]
     * @param aFrequencies int[]
     */
    public void createCodeMappingFor(String[] aCodeCharacters,
                                     int[] aFrequencies) {
        
        List<BinaryTreeNode<CodeElement>>   tempCandidateTreeNodes;
        
        //Create leaves of code tree and sort by appearance frequency
        this.validateInputs(aCodeCharacters, 
                            aFrequencies);
        
        this.setResultingTree(new BinaryTree<CodeElement>());
        tempCandidateTreeNodes = this.createListOfInitalLeaves(aCodeCharacters, aFrequencies);
        
        //Create code tree
        this.createCodeTree(tempCandidateTreeNodes);
        
        //Create generated codes
        if (tempCandidateTreeNodes.size() == 1) {
            
            this.getResultingTree().setRootNode(tempCandidateTreeNodes.get(0));
            this.generateCodesBasedOnTreePosition(this.getResultingTree().getRootNode(), 
                                                  new String());
            
        }
        
    }

    /**
     * Create code tree mapping for huffman codes
     * @param aCandidateTreeNodes
     */
    protected void createCodeTree(List<BinaryTreeNode<CodeElement>> aCandidateTreeNodes) {
        
        
        BinaryTreeNode<CodeElement>                         tempNewNode;
        BinaryTreeNode<CodeElement>                         tempLeftChild;
        BinaryTreeNode<CodeElement>                         tempRightChild;
        Comparator<BinaryTreeNode<CodeElement>>             tempComparator;
        
        tempComparator = this.createComparator();
        aCandidateTreeNodes.sort(tempComparator);
        
        while (aCandidateTreeNodes.size() > 1) {
            
            //Get left and right children to be merged
            tempLeftChild = aCandidateTreeNodes.remove(0);
            tempRightChild = aCandidateTreeNodes.remove(0);
            
            //Create merged element
            tempNewNode = this.createTreeNode(null, tempLeftChild.getData().getAppearanceFrequency()
                                                    + tempRightChild.getData().getAppearanceFrequency());
            tempNewNode.associateLeftChild(tempLeftChild);
            tempNewNode.associateRightChild(tempRightChild);
            
            //Add new element
            aCandidateTreeNodes.add(tempNewNode);
            if (aCandidateTreeNodes.size() > 1) {
                
                aCandidateTreeNodes.sort(tempComparator);
            }
            
        }
        
    }
    
    /**
     * Perform inorder traversal and generate codes in leaves
     * @param aRootNode BinaryTreeNode
     * @param aCurrentCodeString
     */
    protected void generateCodesBasedOnTreePosition(BinaryTreeNode<CodeElement> aNode, 
                                                    String aCurrentCodeString) {
        
        if (aNode != null) {
            
            this.generateCodesBasedOnTreePosition(aNode.getLeftChild(), 
                                aCurrentCodeString + LEFT_TREE_VALUE); //Left
            
            if (aNode.isLeaf()) {
                
                aNode.getData().setGeneratedCode(aCurrentCodeString);
            }
            
            this.generateCodesBasedOnTreePosition(aNode.getRightChild(), 
                                 aCurrentCodeString + RIGHT_TREE_VALUE); //Right
            
        }
        
        
    }
    
    
    /**
     * Dump results
     */
    public String   dumpResults() {
        
        StringBuilder   tempResults = new StringBuilder();
        
        if (this.getResultingTree() != null) {
            
            this.dumpResults(this.getResultingTree().getRootNode(), tempResults);
        }
        
        return tempResults.toString();
        
    }
    
    
    /**
     * Perform inorder traversal
     * @param aRootNode BinaryTreeNode
     * @param aBuilder StringBuilder
     */
    protected void dumpResults(BinaryTreeNode<CodeElement> aNode, 
                               StringBuilder aBuilder) {
        
        if (aNode != null) {
            
            this.dumpResults(aNode.getLeftChild(), aBuilder); //Left
            
            if (aNode.isLeaf()) {
                
                aBuilder.append(" " + aNode.getData().toString() + " "); //Data
                aBuilder.append(System.getProperty("line.separator"));
                
            }
            
            this.dumpResults(aNode.getRightChild(), aBuilder); //Right
            
        }
        
        
    }
    
    /**
     * Answer a comparator to sort CodeElements by frequency
     * @return Comparator<CodeElement>
     */
    protected Comparator<BinaryTreeNode<CodeElement>> createComparator() {
        
        return (BinaryTreeNode<CodeElement> anElement1, BinaryTreeNode<CodeElement> anElement2) ->
                    (anElement1.getData().getAppearanceFrequency() 
                            - anElement2.getData().getAppearanceFrequency());
                    
    }
    
    /**
     * Build initial leaves for the tree
     * @param aCodeCharacters String[]
     * @param aFrequencies int[]
     * @return List<BinaryTreeNode<CodeElement>
     */
    protected List<BinaryTreeNode<CodeElement>> createListOfInitalLeaves(String[] aCodeCharacters,
                                                                         int[] aFrequencies) {
        
        List<BinaryTreeNode<CodeElement>> tempCandidateTreeNodes = 
                new ArrayList<BinaryTreeNode<CodeElement>>();
        BinaryTreeNode<CodeElement>       tempCurrentNode;
        
        for (int i = 0; i < aCodeCharacters.length; i++) {
            
            tempCurrentNode = this.createTreeNode(aCodeCharacters[i], aFrequencies[i]);
            tempCandidateTreeNodes.add(tempCurrentNode);
            
        }
        
        return tempCandidateTreeNodes;
        
    }
    
    /**
     * Validate inputs
     */
    protected void validateInputs(String[] aCodeCharacters,
                                  int[] aFrequencies) {
        
        if (aCodeCharacters.length != aFrequencies.length) {
            
            throw new IllegalArgumentException("The number of code characters must match the"
                                               + " the number of frequencies");
            
        }
        
        if (aCodeCharacters.length < 2) {
            
            throw new IllegalArgumentException("Can't produce a tree for less than two characters in the alphabet");
        }
        
    }
    
    /**
     * Create BinaryTreeNode<CodeElement>
     * @param aCodeCharacter String
     * @param aFrequency int
     * @return BinaryTreeNode<CodeElement>
     */
    protected BinaryTreeNode<CodeElement> createTreeNode(String aCodeCharacter,
                                                         int aFrequency) {
        
        BinaryTreeNode<CodeElement> tempNode;
        CodeElement                 tempElement;
        
        tempElement = new CodeElement(aCodeCharacter, aFrequency);
        tempNode = new BinaryTreeNode<CodeElement>(tempElement, null);
        
        return tempNode;
        
    }
    
    
    /**
     * Answer my codeCharacters
     * @return String[]
     */
    protected String[] getCodeCharacters() {
        return codeCharacters;
    }

    /**
     * Set my codeCharacters
     * @param codeCharacters String[]
     */
    protected void setCodeCharacters(String[] codeCharacters) {
        this.codeCharacters = codeCharacters;
    }

    /**
     * Answer my frequencies
     * @return int[]
     */
    protected int[] getFrequencies() {
        return frequencies;
    }

    /**
     * Set my frequencies
     * @param frequencies int[]
     */
    protected void setFrequencies(int[] frequencies) {
        this.frequencies = frequencies;
    }

    /**
     * Answer my resultingTree
     * @return BinaryTree<CodeElement>
     */
    protected BinaryTree<CodeElement> getResultingTree() {
        return resultingTree;
    }

    /**
     * Set my resultingTree
     * @param resultingTree BinaryTree<CodeElement>
     */
    protected void setResultingTree(BinaryTree<CodeElement> resultingTree) {
        this.resultingTree = resultingTree;
    }
    
    

}
