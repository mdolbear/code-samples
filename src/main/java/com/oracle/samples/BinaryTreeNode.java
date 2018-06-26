package com.oracle.samples;

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
    private int index;
    private Comparator<E> comparator;
    private BinaryTreeNode<E> parent;
    private int height;
 
    /**
     * Answer an instance for the following arguments
     * @param aData E
     */
    public BinaryTreeNode(E aData,
                          Comparator<E> aComparator,
                          BinaryTreeNode<E> aParent) {
        
        this(aComparator, aParent);
        this.setData(aData);

    }
    
    /**
     * Answer an instance for the following arguments
     */
    public BinaryTreeNode(Comparator<E> aComparator,
                          BinaryTreeNode<E> aParent) {
        
        super();
        this.setComparator(aComparator);
        this.setParent(aParent);
        this.setIndex(0);
        this.setHeight(1);
        
    }


    /**
     * Answer my leftChild
     * @return Node
     */
    public BinaryTreeNode<E> getLeftChild() {
        return leftChild;
    }


    /**
     * Set my leftChild
     * @param leftChild Node
     */
    public void setLeftChild(BinaryTreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }


    /**
     * Answer my rightChild
     * @return Node
     */
    public BinaryTreeNode<E> getRightChild() {
        return rightChild;
    }


    /**
     * Set my rightChild
     * @param rightChild Node
     */
    public void setRightChild(BinaryTreeNode<E> rightChild) {
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
    public void setData(E data) {
        this.data = data;
    }
    
    /**
     * Answer my string representation
     * @return String
     */
    @Override
    public String toString() {
        
        StringBuilder   tempBuilder;
        
        tempBuilder = new StringBuilder();
        
        if (this.getLeftChild() != null) {
            
            tempBuilder.append(" leftChild: not null ");

        }
        else {
            
            tempBuilder.append(" leftChild: null ");
        }
        
        tempBuilder.append(" Node Index: " + this.getIndex() + " ");
        
        if (this.getData() != null) {
            
            tempBuilder.append("data: ");
            tempBuilder.append(this.getData());
            tempBuilder.append(" ");
        }
        else {
            
            tempBuilder.append("data: null ");
        }
        
        
        
        if (this.getRightChild() != null) {
            
            tempBuilder.append(" rightChild: not null ");

        }
        else {
            
            tempBuilder.append(" rightChild: null ");
        }
        
        
        if (this.getParent() != null) {
            
            tempBuilder.append(" parent: " + this.getParent().getData());
        }
        else {
            
            tempBuilder.append(" parent null ");
        }
        
        return tempBuilder.toString();
        
    }
    
    /**
     * Traverse inorder
     * @param aBuilder StringBuilder
     */
    public void traverseInorder(StringBuilder aBuilder) {
        
        //Inorder traversal is L - D - R
        //
        if (this.getLeftChild() != null) {
            
            this.getLeftChild().traverseInorder(aBuilder);
        }
        
        //Dump data
        aBuilder.append(" ");
        aBuilder.append(this.getData() != null ? this.getData()
                                               : "null");
        aBuilder.append(" ");
        
        if (this.getRightChild() != null) {
            
            this.getRightChild().traverseInorder(aBuilder);
        }
                                                
    }
    
    /**
     * Traverse preorder
     * @param aBuilder StringBuilder
     */
    public void traversePreorder(StringBuilder aBuilder) {
        
        //Preorder traversal is D-L-R
        //
        
        //Dump data
        aBuilder.append(" ");
        aBuilder.append(this.getData() != null ? this.getData()
                                               : "null");
        aBuilder.append(" ");
        
        if (this.getLeftChild() != null) {
            
            this.getLeftChild().traversePreorder(aBuilder);
        }
        
        
        if (this.getRightChild() != null) {
            
            this.getRightChild().traversePreorder(aBuilder);
        }
                                                
    }
    
    /**
     * Traverse postorder
     * @param aBuilder StringBuilder
     */
    public void traversePostorder(StringBuilder aBuilder) {
        
        //Postorder traversal is L-R-D
        //
        
        if (this.getLeftChild() != null) {
            
            this.getLeftChild().traversePostorder(aBuilder);
        }
        
        
        if (this.getRightChild() != null) {
            
            this.getRightChild().traversePostorder(aBuilder);
        }
        
        //Dump data
        aBuilder.append(" ");
        aBuilder.append(this.getData() != null ? this.getData()
                                               : "null");
        aBuilder.append(" ");
                                                
    }
    

    /**
     * Copy me
     * @param aBuilder StringBuilder
     */
    public BinaryTreeNode<E> copyTree() {
        
        BinaryTreeNode<E>  tempLeftNode = null;
        BinaryTreeNode<E>  tempRightNode = null;
        BinaryTreeNode<E>  tempCurrentNode =  null;
        
        //
        //Postorder traversal is L-R-D
        //
        
        if (this.getLeftChild() != null) {
            
            tempLeftNode = this.getLeftChild().copyTree();
        }
        
        
        if (this.getRightChild() != null) {
            
            tempRightNode = this.getRightChild().copyTree();
        }
        
        //Copy data
        tempCurrentNode = new BinaryTreeNode<E>(this.getData(),
                                            this.getComparator(),
                                            this);
        tempCurrentNode.setLeftChild(tempLeftNode);
        tempCurrentNode.setRightChild(tempRightNode);
        
        
        return tempCurrentNode;
        
    }
    
    /**
     * Answer whether or not I am equal to anotherNode
     * @param anotherNode ParameterizedBinaryNode
     * @return boolean
     */
    public boolean equals(Object anotherNode) {
        
        boolean                     tempResult = false;
        BinaryTreeNode<E>               tempAnotherNode;
        
        if (anotherNode != null &&
                anotherNode instanceof BinaryTreeNode) {
            
            tempAnotherNode = (BinaryTreeNode<E>)anotherNode;
            tempResult = 
                    (this.getData() == null && tempAnotherNode.getData() == null) ||
                        (this.getData() != null && tempAnotherNode.getData() != null && (this.compareTo(tempAnotherNode) == 0));
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer my hashcode
     * @return int
     */
    @Override
    public int hashCode() {
        
        int tempResult;
        
        tempResult = this.getClass().hashCode();
        if (this.getData() != null) {
            
            tempResult += this.getData().hashCode();
        }
        
        return tempResult;
    }
    
    /**
     * Answer whether my nodes are equal starting with anotherNode
     * @param aBuilder StringBuilder
     */
    protected boolean allNodesEqual(BinaryTreeNode<E> anotherNode) {
        
        boolean tempResult = false;
        
        if (anotherNode != null) {
           
               tempResult = this.getData().equals(anotherNode.getData());
               
               if (tempResult) {
                   
                   tempResult = this.evaluateChildForEquals(tempResult,
                                                   this.getLeftChild(), 
                                                   anotherNode.getLeftChild());
                   
                   tempResult = this.evaluateChildForEquals(tempResult,
                                                   this.getRightChild(), 
                                                   anotherNode.getRightChild());
                   
               }
                                        

        }
        
        return tempResult;
                                                
    }
    
    
    /**
     * Evaluate child
     * @param myNode ParameterizedBinaryNode
     * @param anotherNode ParameterizedBinaryNode
     * @return boolean
     */
    protected boolean evaluateChildForEquals(boolean aResult,
                                             BinaryTreeNode<E> myNode, 
                                             BinaryTreeNode<E> anotherNode) {
        
        if (aResult &&
                myNode != null &&
                    anotherNode != null) {
        
            aResult = myNode.allNodesEqual(anotherNode);
        
        }
        else if (myNode == null && anotherNode == null) {
            
            aResult = true;
        }
        else {
            
            aResult = false;
        }

        
        return aResult;
        
    }
    
    
    /**
     * Answer the value at anIndex
     * @param anIndex int
     * @return String
     */
    public BinaryTreeNode<E> getNodeAt(int anIndex) {
        
        BinaryTreeNode<E>  tempResult = null;
                
        if (this.getIndex() != anIndex) {
            
            //Look on left side if we haven't found it
            if (this.getLeftChild() != null && tempResult == null) {
                    
                tempResult = this.getLeftChild().getNodeAt(anIndex);
                    
            }
            
            //Look on right side if we haven't found it
            if (this.getRightChild() != null && tempResult == null) {
                   
                   tempResult = this.getRightChild().getNodeAt(anIndex);
            }

        }
        else {
            
            //Node found
            tempResult = this;
            
        }
        
        return tempResult;
        
    }

    /**
     * Answer my index
     * @return int
     */
    protected int getIndex() {
        return index;
    }

    /**
     * Set my index
     * @param index int
     */
    protected void setIndex(int index) {
        this.index = index;
    }
    
    
    /**
     * Traverse preorder
     * @param aTraversal
     */
    public void numberMePreorder(TreeTraversal aTraversal) {
        
        
        //Inorder traversal is D - L - R
        //Update index
        this.setIndex(aTraversal.getIndex());
        aTraversal.incrementIndex();
        
        //Dump data
        aTraversal.append(" index: " + this.getIndex() + " data: ");
        aTraversal.append(this.getData() != null ? this.getData().toString()
                                               : "null");
        aTraversal.append(", ");
        
        //
        if (this.getLeftChild() != null) {
            
            this.getLeftChild().numberMePreorder(aTraversal);
        }
        
        
        if (this.getRightChild() != null) {
            
            this.getRightChild().numberMePreorder(aTraversal);
        }
                                                
    }

    
    /**
     * Perform heap sort
     */
    public void orderAsHeap() {
                
        this.switchMeWitMaximumValue();
        
    }
   
    /**
     * Switch me with maximum value
     */
    protected void switchMeWitMaximumValue() {
        
        BinaryTreeNode<E>  tempMax;

        
        //Get my max node and substitute it for me if it exists
        tempMax = this.getMaxNode();
        if (tempMax != null && 
                (!tempMax.getData().equals(this.getData()))) {
            
            this.interchangeMyValueWith(tempMax);
            
        }
        
        if (this.getLeftChild() != null) {
            
            this.getLeftChild().switchMeWitMaximumValue();
        }
        
        if (this.getRightChild() != null) {
            
            this.getRightChild().switchMeWitMaximumValue();
        }

    }

    /**
     * Interchange my values with anotherNode
     * @param anotherNode BinaryNode<E>
     */
    protected void interchangeMyValueWith(BinaryTreeNode<E> anotherNode) {
        
        E      tempValue;
        
        tempValue = anotherNode.getData();
        anotherNode.setData(this.getData());
        this.setData(tempValue);
        
    }
    
    
    /**
     * Answer my max node
     * @return ParameterizedBinaryNode
     */
    protected BinaryTreeNode<E> getMaxNode() {
        
        BinaryTreeNode<E>  tempMax;
        BinaryTreeNode<E>  tempLeftMax = null;
        BinaryTreeNode<E>  tempRightMax = null;
        
        //Get max nodes based on where I am in the tree
        if (this.getLeftChild() != null) {
            
            tempLeftMax = this.getLeftChild().getMaxNode();
        }
        
        if (this.getRightChild() != null) {
            
            tempRightMax = this.getRightChild().getMaxNode();
        }
        
        //Answer max of three possible values
        tempMax = this.getMaxOfMeLeftAndRight(tempLeftMax, tempRightMax);
        
        return tempMax;
        
        
    }
    
    /**
     * Answer my min node
     * @return BinaryNode
     */
    protected BinaryTreeNode<E> getMinNode() {
        
        BinaryTreeNode<E>  tempMin;
        BinaryTreeNode<E>  tempLeftMin = null;
        BinaryTreeNode<E>  tempRightMin = null;
        
        //Get min nodes based on where I am in the tree
        if (this.getLeftChild() != null) {
            
            tempLeftMin = this.getLeftChild().getMinNode();
        }
        
        if (this.getRightChild() != null) {
            
            tempRightMin = this.getRightChild().getMinNode();
        }
        
        //Answer min of three possible values
        tempMin = this.getMinOfMeLeftAndRight(tempLeftMin, tempRightMin);
        
        return tempMin;
        
        
    }
    
    

    /**
     * Answer max of three possible values
     * @param aLeftMax
     * @param aRightMax
     * @return ParameterizedBinaryNode<E>
     */
    protected BinaryTreeNode<E> getMaxOfMeLeftAndRight(BinaryTreeNode<E> aLeftMax, 
                                                   BinaryTreeNode<E> aRightMax) {
        
        BinaryTreeNode<E>  tempResult;
        
        tempResult = this;
        if (aLeftMax != null) {
            
            if (tempResult.compareTo(aLeftMax) < 0) {
                
                tempResult = aLeftMax;
            }
        }
        
        if (aRightMax != null) {
            
            if (tempResult.compareTo(aRightMax) < 0) {
                
                tempResult = aRightMax;
            }
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer max of three possible values
     * @param aLeftMin
     * @param aRightMin
     * @return ParameterizedBinaryNode<E>
     */
    protected BinaryTreeNode<E> getMinOfMeLeftAndRight(BinaryTreeNode<E> aLeftMin, 
                                                   BinaryTreeNode<E> aRightMin) {
        
        BinaryTreeNode<E>  tempResult;
        
        tempResult = this;
        if (aLeftMin != null) {
            
            if (tempResult.compareTo(aLeftMin) > 0) {
                
                tempResult = aLeftMin;
            }
        }
        
        if (aRightMin != null) {
            
            if (tempResult.compareTo(aRightMin) > 0) {
                
                tempResult = aRightMin;
            }
        }
        
        return tempResult;
        
    }
    
    
    
    /**
     * Compare to anotherNode. Answer -, 0, + depending on whether I am before, equal, or after respectively
     * @return int
     */
    public int compareTo(BinaryTreeNode<E> anotherNode) {
        
        return this.getComparator().compare(this.getData(), anotherNode.getData());
        
        
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
     * Build complete tree according to the definition of "complete" based on aDepth.
     * The assumption is that I have no data initialized
     * 
     * @param aData List<E>
     * @param aDepth int
     */
    public void buildComplete(List<E> aData,
                              int aDepth) {
        
        
        if (this.shouldContinue(aData, aDepth)) {
            
            //Initialize my data
            this.initializeMeFrom(aData);
             
            //Go to next depth
            aDepth--;
            
            //Create subtrees if remaining data and depth
            this.createLeftSubtree(aData, aDepth);           
            this.createRightSubtree(aData, aDepth);
            
            
        }
        
        
        
        
    }

    /**
     * Create subtree
     * @param aData
     * @param aDepth
     */
    protected void createLeftSubtree(List<E> aData, 
                                     int aDepth) {
        
        BinaryTreeNode<E>  tempLeftNode;
        
        if (this.shouldContinue(aData, aDepth)) {
            
            tempLeftNode = this.createLeftChild(aData, aDepth);     
            tempLeftNode.buildComplete(aData, aDepth);
        }
        
    }
    
    /**
     * Create subtree
     * @param aData
     * @param aDepth
     */
    protected void createRightSubtree(List<E> aData, 
                                      int aDepth) {
        
        BinaryTreeNode<E>  tempRightNode;
        
        if (this.shouldContinue(aData, aDepth)) {
            
            tempRightNode = this.createRightChild(aData, aDepth);
            tempRightNode.buildComplete(aData, aDepth);
        }
        
    }
    
    

    /**
     * Create left node
     * @param aData
     * @param aDepth
     *
     * @return ParameterizedBinaryNode<E>
     */
    protected BinaryTreeNode<E> createLeftChild(List<E> aData, int aDepth) {
        
        BinaryTreeNode<E>  tempLeftNode = null;
            
        tempLeftNode = new BinaryTreeNode<E>(this.getComparator(), this);
        this.setLeftChild(tempLeftNode);
        
        return tempLeftNode;
    }
    
    /**
     * Create right node
     * @param aData
     * @param aDepth
     *
     * @return ParameterizedBinaryNode
     */
    protected BinaryTreeNode<E> createRightChild(List<E> aData, int aDepth) {
        
        BinaryTreeNode<E>  tempRightNode = null;
        
            
        tempRightNode = new BinaryTreeNode<E>(this.getComparator(), this);
        this.setRightChild(tempRightNode);
        
        return tempRightNode;
    }
    

    /**
     * Answer whether I should continue
     * @param aData
     * @param aDepth
     * @return boolean
     */
    protected boolean shouldContinue(List<E> aData, int aDepth) {
        
        return !aData.isEmpty() && (aDepth > 0);
    }
    
    
    
    /**
     * Initialize me from
     * @param aData List<E>
     */
    protected void initializeMeFrom(List<E> aData) {
        
        E      tempCurrentData;
        
        tempCurrentData = this.getNextDataElementFrom(aData);
        this.setData(tempCurrentData);

    }

    /**
     * Answer the next data element from aData, remove it from aData, and return it
     * @param aData List<E>
     * @return E
     */
    protected E getNextDataElementFrom(List<E> aData) {
        
        E tempCurrentData;
        
        tempCurrentData = aData.get(0);
        aData.remove(0);
        
        return tempCurrentData;
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
     * Search for aData. Answer a BinaryNode that contains aData or null
     * @param aData E
     * @return BinaryNode<E>
     */
    public BinaryTreeNode<E> search(E aData,
                                int aSize) {
        
        BinaryTreeNode<E>  tempResult = null;
        BinaryTreeNode<E>  tempCurrent;
       
        
        tempCurrent = this;
        while (tempResult == null &&
                    aSize > 0) {
            
            if (tempCurrent.compareTo(aData) == 0) {
                
                tempResult = tempCurrent;
            }
            else if (tempCurrent.compareTo(aData) > 0) {
                    
                if (tempCurrent.getLeftChild() != null) {
                    
                   tempCurrent = tempCurrent.getLeftChild();
                }
    
            }
            else {
                
                if (tempCurrent.getRightChild() != null) {
                    
                    tempCurrent = tempCurrent.getRightChild();
                }
            }
            
            aSize--;
            
        }
        
        return tempResult;
        
    }
    
    
    /**
     * Perform search. Answer a BinaryNode<V> or null
     * @return BinaryNode<V>
     */
    protected BinaryTreeNode<E> recursiveSearch(E aData) {
        
        BinaryTreeNode<E> tempResult = null;
        
        int tempCompareResult;
        
        if (!this.getData().equals(aData)) {
            
            tempCompareResult  = this.compareTo(aData);
            
            if (tempCompareResult > 0) {
                
                if (this.getLeftChild() != null) {
                    
                    tempResult = this.getLeftChild().recursiveSearch(aData);
                }
                    
            }
            else {
                
                if (this.getRightChild() != null) {
                    
                    tempResult = this.getRightChild().recursiveSearch(aData);
                }
                
            }
        }
        else {
            
            tempResult = this;
        }
        
        return tempResult;
        
    }
    

    
    
    /**
     * Delete node with E data
     * @param aData E
     */
    public BinaryTreeNode<E> deleteNodeWith(E aData) {
        
        BinaryTreeNode<E>   tempNode;
        
        tempNode = this.recursiveSearch(aData);
        if (tempNode != null) {
            
            tempNode.delete();
        }
        
        return tempNode;
        
    }
    
    /**
     * Delete me
     */
    protected void delete() {
        
        if (this.isLeafNode()) {
            
            this.removeLeafNode();
        }
        else if (this.hasBothChildren()) {
            
            this.removeNodeWithTwoChildren();
        }
        else {
            
            this.removeNodeWithSingleChild();
        }
        
    }
    
    
    /**
     * Remove node with single child
     */
    protected void removeNodeWithSingleChild() {
        
        BinaryTreeNode<E> tempSingleChild;
        
        tempSingleChild = (this.getLeftChild() != null) ? this.getLeftChild() : this.getRightChild();
        this.interchangeMyValueWith(tempSingleChild);
        tempSingleChild.delete();
        
    }
    
    
    /**
     * Remove node with two children
     */
    protected void removeNodeWithTwoChildren() {
        
        BinaryTreeNode<E> tempSuccessor;
        
        tempSuccessor = this.getInorderSuccessor();
        this.interchangeMyValueWith(tempSuccessor);
        tempSuccessor.delete();
        
    }
    
    
    /**
     * Remove leaf node
     */
    protected void removeLeafNode() {
        
       if (this.getParent() != null) {
           
           this.getParent().removeLinkFor(this);
           this.setParent(null);
       }
       else {
           
           System.out.println("Leaf node not being removed: " + this);
       }

    }
    
    /**
     * Answer whether or not I am a leaf node
     * @return boolean
     */
    protected boolean isLeafNode() {
        
        return this.getLeftChild() == null &&
                this.getRightChild() == null;
    }
    
    /**
     * Answer whether or not I have both children
     * @return boolean
     */
    protected boolean hasBothChildren() {
        
        return this.getLeftChild() != null &&
                this.getRightChild() != null;
    }
    
    
    
    /**
     * Remove link for aNode. aNode must be either a left or right child
     * @param aNode BinaryNode<E>
     */
    protected void removeLinkFor(BinaryTreeNode<E> aNode) {
        
        
        if (this.getLeftChild() != null && this.getLeftChild().equals(aNode)) {
            
            this.setLeftChild(null);
        }
        else if (this.getRightChild() != null && this.getRightChild().equals(aNode)) {
            
            this.setRightChild(null);
        }
        
    }
    
    
    
    /**
     * Answer my inorder successor. This assumes I am sorted.
     * @return BinnaryNode<E>
     */
    protected BinaryTreeNode<E> getInorderSuccessor() {
        
        BinaryTreeNode<E> tempResult = null;
        BinaryTreeNode<E> tempParent;
        BinaryTreeNode<E> tempRightChild;
        
        //Follow path to my successor
        if (this.getRightChild() != null) {
            
            tempResult = this.getRightChild().getMinNode();
        }
        else {
            
            tempParent = this.getParent();
            tempRightChild = this;
            
            while (tempParent != null && tempParent.getRightChild() != null
                        && tempParent.getRightChild().equals(tempRightChild)) {
                
                tempRightChild = tempParent;
                tempParent = tempParent.getParent();
            }
            
            tempResult = tempParent;
            
        }
        
        return tempResult;
                                                
    }
    

    /**
     * Answer my parent
     * @return BinaryNode<E>
     */
    protected BinaryTreeNode<E> getParent() {
        return parent;
    }

    /**
     * Set my parent
     * @param parent BinaryNode<E>
     */
    protected void setParent(BinaryTreeNode<E> parent) {
        this.parent = parent;
    }
    
   
    /**
     * Answer my max depth
     * @param aTreeTraversal
     */
    public void getDepth(TreeTraversal aTraversal) {
        
        aTraversal.incrementCurrentDepth();
        
        if (this.isLeafNode()) {
            
            aTraversal.recordMaxDepth();
        }
        else {
            
            if (this.getLeftChild() != null) {
                
                this.getLeftChild().getDepth(aTraversal);
            }
            
            if (this.getRightChild() != null) {
                
                this.getRightChild().getDepth(aTraversal);
            }
            
        }
        
    }

    /**
     * Answer my height
     * @return int
     */
    protected int getHeight() {
        return height;
    }

    /**
     * Set my height
     * @param height int
     */
    protected void setHeight(int height) {
        this.height = height;
    }
    
    
    
    /**
     * Answer whether I have valid right rotate conditions
     */
    public void validateRightRotateConditions() {
        
        if (this.getLeftChild() == null ) {
            
            throw new IllegalStateException("Tree is in invalid state for right rotate");
        }
    }
    
    
    /**
     * Right rotate me. Answer the new root node (no longer me)
     * @return BinaryNode<E>
     */
    protected BinaryTreeNode<E> rightRotate() {
        
        BinaryTreeNode<E>    tempNodeY;
        BinaryTreeNode<E>    tempNodeT3;
        BinaryTreeNode<E>    tempMyParent;
        
        
        this.validateRightRotateConditions();
        
        tempNodeY = this.getLeftChild();
        tempNodeT3 = tempNodeY.getRightChild();
        
        //Rotation
        tempMyParent = this.getParent();
        tempNodeY.setRightChild(this); //this becomes the right child of new root Y
        this.setParent(tempNodeY);
        tempNodeY.setParent(tempMyParent);
        
        this.setLeftChild(tempNodeT3);  //Right child of Y (T3) becomes the left child of this
        if (tempNodeT3 != null) {
            
            tempNodeT3.setParent(this);
        }
        
        //Update heights
        this.setHeight(Math.max(this.getHeight(this.getLeftChild()), 
                                this.getHeight(this.getRightChild())) + 1);
        
        tempNodeY.setHeight(Math.max(this.getHeight(tempNodeY.getLeftChild()), 
                                     this.getHeight(tempNodeY.getRightChild())) + 1);
        
        return tempNodeY; //new root node after rotation
    }
    
    /**
     * Answer whether I have valid left rotate conditions
     */
    public void validateLeftRotateConditions() {
        
       if (this.getRightChild() == null) {
           
           throw new IllegalStateException("Tree is in invalid state for left rotate");
       }
    }
    
    
    
    /**
     * Left rotate me. Answer my new root node (no longer me)
     * @return BinaryNode<E>
     */
    protected BinaryTreeNode<E> leftRotate() {
        
        BinaryTreeNode<E>   tempNodeY;
        BinaryTreeNode<E>    tempNodeT2;
        BinaryTreeNode<E>    tempMyParent;
        
        this.validateLeftRotateConditions();
        
        tempNodeY = this.getRightChild();
        tempNodeT2 = tempNodeY.getLeftChild();
        
        //Rotation
        tempMyParent = this.getParent();
        tempNodeY.setLeftChild(this); //this becomes the left child of new root Y
        this.setParent(tempNodeY);
        tempNodeY.setParent(tempMyParent);
        
        this.setRightChild(tempNodeT2); //Left child of Y (NodeT2) becomes right child of this
        if (tempNodeT2 != null) {
            
            tempNodeT2.setParent(this);
        }
        
        //Update heights
        this.setHeight(Math.max(this.getHeight(this.getLeftChild()), 
                                this.getHeight(this.getRightChild())) + 1);
        this.setHeight(Math.max(this.getHeight(tempNodeY.getLeftChild()), 
                                this.getHeight(tempNodeY.getRightChild())) + 1);
        
        return tempNodeY;
        
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
     * Answer me and my children as a linked list
     * @param LinkedList<K,V>
     */
    public void asLinkedList(LinkedList<E,E> aList) {
        
        //Create list in inorder fashion
        if (this.getLeftChild() != null) {
            
            this.getLeftChild().asLinkedList(aList);
        }
        
        aList.add(this.getData(), this.getData());
        
        if (this.getRightChild() != null) {
            
            this.getRightChild().asLinkedList(aList);
        }
        
    }
    
}
