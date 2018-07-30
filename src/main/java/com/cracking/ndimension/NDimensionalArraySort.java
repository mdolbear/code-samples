package com.cracking.ndimension;

public class NDimensionalArraySort {


    private NDimensionalArrayIndex index;

    /**
     * Answer a default instance
     */
    public NDimensionalArraySort() {

        super();
    }

    /**
     * Bubble sort
     *
     * @param aStartIndex int
     * @param anEndIndex int
     *
     */
    public void basicBubbleSort(int[][][] anArray,
                                int aStartIndex,
                                int anEndIndex) {


        int                     i;
        int                     j;
        int                     tempCurrentMinimumValue;
        int                     tempCurrentMinimumIdx;
        int                     tempCurrent;
        NDimensionalArrayIndex  tempIndexer;
        int[]                   tempDimensions = { anArray[0][0].length,
                                                   anArray[0].length,
                                                   anArray.length};

        tempIndexer = new NDimensionalArrayIndex(tempDimensions);
        for (i = aStartIndex; i < anEndIndex; i++) {

            tempCurrentMinimumValue =
                    this.getValueAtFlatIndex(anArray, i, tempIndexer);
            tempCurrentMinimumIdx = i;

            for (j = i + 1; j < anEndIndex; j++) {

                tempCurrent = this.getValueAtFlatIndex(anArray, j, tempIndexer);
                if (tempCurrent < tempCurrentMinimumValue) {

                    tempCurrentMinimumValue = tempCurrent;
                    tempCurrentMinimumIdx = j;
                }

            }

            //Perform switch
            this.exchangeValueAt(anArray, i, tempCurrentMinimumIdx, tempIndexer);

        }

    }

    /**
     * Exchange values in an array at indexes i and j
     * @param anArray int[]
     * @param i int
     * @param j int
     */
    protected void exchangeValueAt(int[][][] anArray,
                                   int i,
                                   int j,
                                   NDimensionalArrayIndex tempIndexer) {

        int tempValue;

        tempValue =
                this.getValueAtFlatIndex(anArray,
                                         i,
                                         tempIndexer); //Get value at i

        this.setValueAtFlatIndex(anArray,              //Put value at j into i
                                 i,tempIndexer,
                                 this.getValueAtFlatIndex(anArray, j, tempIndexer));

        this.setValueAtFlatIndex(anArray,              //Put value at i into j
                                 j,
                                 tempIndexer,
                                 tempValue);

    }

    /**
     * Answer the value in anArray at aFlatIndex
     * @param anArray
     * @param aFlatIndex
     * @param anIndexer
     * @return int
     */
    protected int getValueAtFlatIndex(int[][][] anArray,
                                      int aFlatIndex,
                                      NDimensionalArrayIndex anIndexer) {

        int                     tempResult;
        int[]                   tempIndexTuple;

        anIndexer.initializeMyselfFrom(aFlatIndex);
        tempIndexTuple = anIndexer.getDimensionIndexTuple();
        tempResult = anArray[tempIndexTuple[2]][tempIndexTuple[1]][tempIndexTuple[0]]; //Sheet | Row | Column

        return tempResult;
    }

    /**
     * Set the value in anArray at aFlatIndex
     * @param anArray
     * @param aFlatIndex
     * @param anIndexer
     * @param aValue
     */
    protected void setValueAtFlatIndex(int[][][] anArray,
                                       int aFlatIndex,
                                       NDimensionalArrayIndex anIndexer,
                                       int aValue) {

        int[]                   tempIndexTuple;

        anIndexer.initializeMyselfFrom(aFlatIndex);
        tempIndexTuple = anIndexer.getDimensionIndexTuple();
        anArray[tempIndexTuple[2]][tempIndexTuple[1]] [tempIndexTuple[0]] = aValue;

    }


}
