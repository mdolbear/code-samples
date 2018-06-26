package com.cracking;

/**
 *
 *
 */
public class BinaryTreeInsertion<E> {

    private BinaryTreeNode<E> node;
    private int               dataIndex;
    
    /**
     * Answer an instance for the following arguments
     * @param aNode BinaryTreeNode<E>
     * @param aDataIndex int
     */
    public BinaryTreeInsertion(BinaryTreeNode<E> aNode, 
                               int aDataIndex) {
        
       super();
       this.setNode(aNode);
       this.setDataIndex(aDataIndex);
    }

    /**
     * Answer my node
     * @return BinaryTreeNode<E>
     */
    public BinaryTreeNode<E> getNode() {
        return node;
    }

    /**
     * Set my node
     * @param node BinaryTreeNode<E>
     */
    protected void setNode(BinaryTreeNode<E> node) {
        this.node = node;
    }

    /**
     * Answer my dataIndex
     * @return int
     */
    public int getDataIndex() {
        return dataIndex;
    }

    /**
     * Set my dataIndex
     * @param dataIndex int
     */
    protected void setDataIndex(int dataIndex) {
        this.dataIndex = dataIndex;
    }
    
    

}
