package com.cracking.ndimension;

public class ThreeDimensionalArrayIndex {

    private int flatIndex;
    private int[] dimensionIndexes;
    private int numberOfColumns;
    private int numberOfRows;
    private int numberOfSheets;

    //Constants
    protected static final int COLUMN_INDEX = 0;
    protected static final int ROW_INDEX = 1;
    protected static final int SHEET_INDEX = 2;


    /**
     * Answer a default instance
     */
    public ThreeDimensionalArrayIndex() {

        super();
        this.setDimensionIndexes(new int[3]);

    }

    /**
     * Answer an instance of me on
     * @param aNumberOfColumns int
     * @param aNumberOfRows int
     * @param aNumberOfSheets int
     */
    public ThreeDimensionalArrayIndex(int aNumberOfColumns,
                                      int aNumberOfRows,
                                      int aNumberOfSheets) {

        this();
        this.setNumberOfColumns(aNumberOfColumns);
        this.setNumberOfRows(aNumberOfRows);
        this.setNumberOfSheets(aNumberOfSheets);

    }

    /**
     * Initialize myself from aFlatIndex
     * @param aFlatIndex int
     */
    public void initializeMyselfFrom(int aFlatIndex) {

        int[]   tempIndexes;

        this.validateFlatIndex(aFlatIndex);
        tempIndexes = this.getDimensionIndexes();
        tempIndexes[COLUMN_INDEX] = this.computeColumnIndex(aFlatIndex);
        tempIndexes[ROW_INDEX]  = this.computeRowIndex(aFlatIndex);
        tempIndexes[SHEET_INDEX] = this.computeSheetIndex(aFlatIndex);
    }

    /**
     * Answer my sheet index
     * @return int
     */
    public int getSheetIndex() {

        return this.getDimensionIndexes()[SHEET_INDEX];
    }

    /**
     * Answer my row index
     * @return int
     */
    public int getRowIndex() {

        return this.getDimensionIndexes()[ROW_INDEX];
    }

    /**
     * Answer my column index
     * @return int
     */
    public int getColumnIndex() {

        return this.getDimensionIndexes()[COLUMN_INDEX];
    }




    /**
     * Answer my flatIndex
     *
     * @return int
     */
    public int getFlatIndex() {
        return flatIndex;
    }

    /**
     * Set my flatIndex
     *
     * @param flatIndex int
     */
    protected void setFlatIndex(int flatIndex) {
        this.flatIndex = flatIndex;
    }


    /**
     * Answer my dimensionIndexes
     *
     * @return int[]
     */
    public int[] getDimensionIndexes() {
        return dimensionIndexes;
    }

    /**
     * Set my dimensionIndexes
     *
     * @param dimensionIndexes int[]
     */
    protected void setDimensionIndexes(int[] dimensionIndexes) {
        this.dimensionIndexes = dimensionIndexes;
    }

    /**
     * Answer my numberOfColumns
     *
     * @return int
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * Set my numberOfColumns
     *
     * @param numberOfColumns int
     */
    protected void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    /**
     * Answer my numberOfRows
     *
     * @return int
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Set my numberOfRows
     *
     * @param numberOfRows int
     */
    protected void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    /**
     * Answer my numberOfSheets
     *
     * @return int
     */
    public int getNumberOfSheets() {
        return numberOfSheets;
    }

    /**
     * Set my numberOfSheets
     *
     * @param numberOfSheets int
     */
    protected void setNumberOfSheets(int numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
    }

    /**
     * Compute column index from aFlatIndex
     * @param aFlatIndex int
     * @return int
     */
    protected int computeColumnIndex(int aFlatIndex) {

        return aFlatIndex % this.getNumberOfColumns();
    }

    /**
     * Compute row index from aFlatIndex
     * @param aFlatIndex int
     * @return int
     */
    protected int computeRowIndex(int aFlatIndex) {

        return (aFlatIndex / this.getNumberOfColumns()) % this.getNumberOfRows();
    }


    /**
     * Compute sheet index from aFlatIndex
     * @param aFlatIndex int
     * @return int
     */
    protected int computeSheetIndex(int aFlatIndex) {

        return (aFlatIndex / (this.getNumberOfColumns() * this.getNumberOfRows())) % this.getNumberOfSheets();
    }


    /**
     * Validate flat index
     * @param aFlatIndex int
     *
     */
    protected void validateFlatIndex(int aFlatIndex) {

        if (aFlatIndex >= this.getTargetSize()) {

            throw new IllegalArgumentException("Invalid flat index: " + aFlatIndex +
                                                " for target size: " + this.getTargetSize());

        }

    }

    /**
     * Answer my target size -- i.e. the size of the array that I index
     * @return int
     */
    public int getTargetSize() {

        return this.getNumberOfColumns() *
                    this.getNumberOfRows() *
                        this.getNumberOfSheets();
    }

}
