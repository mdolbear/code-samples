package com.cracking.ndimension;

public class NDimensionalArrayIndex {

    private int flatIndex;
    private int numberOfDimensions;
    private int[] dimensionIndexes;
    private int[] dimensionSizes;


    /**
     * Answer a default instance
     */
    public NDimensionalArrayIndex() {

        super();
    }

    /**
     * Answer an instance of me for the arguments below
     * @param aDimensionSizes int[]
     */
    public NDimensionalArrayIndex(int[] aDimensionSizes) {

        this();
        this.initialize(aDimensionSizes);

    }

    /**
     * Initialize me
     * @param aDimensionSizes int[]
     */
    protected void initialize(int[] aDimensionSizes) {

        this.setNumberOfDimensions(aDimensionSizes.length);
        this.setDimensionSizes(new int[aDimensionSizes.length]);
        this.setDimensionIndexes(new int[aDimensionSizes.length]);

        for (int i = 0; i < this.getNumberOfDimensions(); i++) {

            this.getDimensionSizes()[i] = aDimensionSizes[i];
        }

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
     * Answer my numberOfDimensions
     *
     * @return int
     */
    public int getNumberOfDimensions() {
        return numberOfDimensions;
    }

    /**
     * Set my numberOfDimensions
     *
     * @param numberOfDimensions int
     */
    protected void setNumberOfDimensions(int numberOfDimensions) {
        this.numberOfDimensions = numberOfDimensions;
    }

    /**
     * Set the value of a dimension index at anIndex to
     * anIndexValue
     * @param anIndex int
     * @param anIndexValue int
     */
    protected void setDimensionIndexAt(int anIndex, int anIndexValue) {

        this.getDimensionIndexes()[anIndex] = anIndexValue;

    }

    /**
     * Answer the tuple that represents the indexes for all of the dimensions that I hold.
     * This method assumes that a prior initialize has already been run
     * @return int[]
     */
    public int[] getDimensionIndexTuple() {

        int[] tempResult = new int[this.getNumberOfDimensions()];

        for (int i = 0; i < this.getNumberOfDimensions(); i++) {

            tempResult[i] = this.getDimensionIndexAt(i);
        }

        return tempResult;
    }

    /**
     * Initialize myself from aFlatIndex
     * @param aFlatIndex int
     */
    public void initializeMyselfFrom(int aFlatIndex) {

        int[]   tempIndexes;

        this.validateFlatIndex(aFlatIndex);
        tempIndexes = this.getDimensionIndexes();
        for (int i = 0; i < this.getNumberOfDimensions(); i++) {

            tempIndexes[i] = this.computeIndexFor(aFlatIndex, i);
        }
    }


    /**
     * Answer dimension index at anIndex
     * @param anIndex int
     * @return int
     */
    public int getDimensionIndexAt(int anIndex) {

        return this.getDimensionIndexes()[anIndex];

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
     * Answer my dimensionSizes
     *
     * @return int[]
     */
    public int[] getDimensionSizes() {
        return dimensionSizes;
    }

    /**
     * Set my dimensionSizes
     *
     * @param dimensionSizes int[]
     */
    protected void setDimensionSizes(int[] dimensionSizes) {
        this.dimensionSizes = dimensionSizes;
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
     * Compute sheet index from aFlatIndex
     * @param aFlatIndex int
     * @param aDimension int
     * @return int
     */
    protected int computeIndexFor(int aFlatIndex,
                                  int aDimension) {

        int tempSizeForDimension;

        tempSizeForDimension = this.getDimensionSizes()[aDimension];
        return (aFlatIndex / this.computeSizeUpTo(aDimension)) % tempSizeForDimension;

    }



    /**
     * Compute size up to aDimensionIndex
     * @param aDimensionIndex int
     * @return int
     */
    protected int computeSizeUpTo(int aDimensionIndex) {

        int tempResult = 0;

        if (this.getNumberOfDimensions() > 0) {

            tempResult = 1; //Note that the first index (dimension 0) is automatically of size 1
            for (int i = 0; i < aDimensionIndex; i++) {

                tempResult = tempResult * this.getDimensionSizes()[i];
            }

        }

        return tempResult;

    }


    /**
     * Answer my target size -- i.e. the size of the array that I index
     * @return int
     */
    public int getTargetSize() {

        int tempResult = 0;

        if (this.getNumberOfDimensions() > 0) {

            tempResult = 1;
            for (int i = 0; i < this.getNumberOfDimensions(); i++) {

                tempResult = tempResult * this.getDimensionSizes()[i];
            }


        }

        return tempResult;
    }

}
