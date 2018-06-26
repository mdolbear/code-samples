package com.cracking;

/**
 *
 *
 */
public class ArrayEntry {

    private int row;
    private int column;
    private int value;
    
    /**
     * Answer an instance for the following arguments
     */
    public ArrayEntry(int aRow,
                      int aColumn,
                      int aValue) {
        
       super();
       this.setRow(aRow);
       this.setColumn(aColumn);
       this.setValue(aValue);
       
    }

    /**
     * Answer my row
     * @return int
     */
    public int getRow() {
        return row;
    }

    /**
     * Set my row
     * @param row int
     */
    protected void setRow(int row) {
        this.row = row;
    }

    /**
     * Answer my column
     * @return int
     */
    public int getColumn() {
        return column;
    }

    /**
     * Set my column
     * @param column int
     */
    protected void setColumn(int column) {
        this.column = column;
    }

    /**
     * Answer my value
     * @return int
     */
    public int getValue() {
        return value;
    }

    /**
     * Set my value
     * @param value int
     */
    protected void setValue(int value) {
        this.value = value;
    }
    
    

}
