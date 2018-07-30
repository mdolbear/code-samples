package com.cracking.ndimension;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MultiDimensionalArrayIndexTest {

    /**
     * Answer a default instance
     *
     */
    public MultiDimensionalArrayIndexTest() {

        super();
    }

    /**
     * Perform three dimensional array index test
     */
    @Test
    public void threeDimensionalArrayIndexTest() {

        int[][][]                   tempThreeDimensionalArray;
        int                         tempNumberOfSheets;
        int                         tempNumberOfRows;
        int                         tempNumberOfColumns;
        int                         tempFlatIndex;
        ThreeDimensionalArrayIndex  tempIndex;
        int                         tempValue;


        //Test array
        tempNumberOfSheets = 4;
        tempNumberOfRows = 20;
        tempNumberOfColumns = 6;
        tempThreeDimensionalArray =
                this.createThreeDimensionalArray(tempNumberOfColumns,
                                                 tempNumberOfRows,
                                                 tempNumberOfSheets);

        tempIndex = new ThreeDimensionalArrayIndex(tempNumberOfColumns,
                                                   tempNumberOfRows,
                                                   tempNumberOfSheets);

        //Test 1
        tempFlatIndex = 0;
        tempValue = initializeIndexAndAccessArrayValue(tempThreeDimensionalArray, tempFlatIndex, tempIndex);
        assertTrue("Test 1", tempFlatIndex == tempValue);

        //Test 2
        tempFlatIndex = tempNumberOfColumns * tempNumberOfRows + 1; //Access on sheet 1
        tempValue = initializeIndexAndAccessArrayValue(tempThreeDimensionalArray, tempFlatIndex, tempIndex);
        assertTrue("Test 2", tempFlatIndex == tempValue);

        //Test 3
        tempFlatIndex = tempNumberOfColumns + 1;
        tempValue = initializeIndexAndAccessArrayValue(tempThreeDimensionalArray, tempFlatIndex, tempIndex);
        assertTrue("Test 3", tempFlatIndex == tempValue);


    }

    /**
     * Initialize index and then access array to obtain value
     * @param aThreeDimensionalArray
     * @param aFlatIndex
     * @param anIndex
     * @return
     */
    protected int initializeIndexAndAccessArrayValue(int[][][] aThreeDimensionalArray,
                                                     int aFlatIndex,
                                                     ThreeDimensionalArrayIndex anIndex) {

        int tempValue;

        anIndex.initializeMyselfFrom(aFlatIndex);
        tempValue = aThreeDimensionalArray[anIndex.getSheetIndex()]
                                          [anIndex.getRowIndex()]
                                          [anIndex.getColumnIndex()];

        return tempValue;
    }

    /**
     * Answer a three dimensional int[][][]
     * array for aNumberOfColumns, aNumberOfRows, and aNumberOfSheets
     * @param aNumberOfColumns int
     * @param aNumberOfRows int
     * @param aNumberOfSheets int
     * @return int[][][]
     */
    protected int[][][] createThreeDimensionalArray(int aNumberOfColumns,
                                                    int aNumberOfRows,
                                                    int aNumberOfSheets) {

        int[][][] tempResult = new int[aNumberOfSheets][aNumberOfRows][aNumberOfColumns];
        int tempFlatValue = 0;

        for (int i = 0; i < aNumberOfSheets; i++) {

            for (int j = 0; j < aNumberOfRows; j++) {

                for (int k = 0; k < aNumberOfColumns; k++) {

                    tempResult[i][j][k] = tempFlatValue;
                    tempFlatValue +=1;
                }
            }
        }

        return tempResult;

    }

    /**
     * Perform three dimensional array index test
     */
    @Test
    public void nDimensionalArrayIndexTest() {

        int[][][]                   tempThreeDimensionalArray;
        int                         tempNumberOfSheets = 4;
        int                         tempNumberOfRows = 20;
        int                         tempNumberOfColumns = 6;
        int                         tempFlatIndex;
        NDimensionalArrayIndex      tempIndex;
        int                         tempValue;
        int[]                       tempDimensionSizes= {tempNumberOfColumns, tempNumberOfRows,tempNumberOfSheets};


        //Test array
        tempThreeDimensionalArray =
                this.createThreeDimensionalArray(tempNumberOfColumns,
                                                 tempNumberOfRows,
                                                 tempNumberOfSheets);

        tempIndex = new NDimensionalArrayIndex(tempDimensionSizes);

        //Test 1
        tempFlatIndex = 0;
        tempValue = initializeIndexAndAccessArrayValue(tempThreeDimensionalArray, tempFlatIndex, tempIndex);
        assertTrue("Test 1", tempFlatIndex == tempValue);

        //Test 2
        tempFlatIndex = tempNumberOfColumns * tempNumberOfRows + 1; //Access on sheet 1
        tempValue = initializeIndexAndAccessArrayValue(tempThreeDimensionalArray, tempFlatIndex, tempIndex);
        assertTrue("Test 2", tempFlatIndex == tempValue);

        //Test 3
        tempFlatIndex = tempNumberOfColumns + 1;
        tempValue = initializeIndexAndAccessArrayValue(tempThreeDimensionalArray, tempFlatIndex, tempIndex);
        assertTrue("Test 3", tempFlatIndex == tempValue);


    }

    /**
     * Initialize index and then access array to obtain value
     * @param aThreeDimensionalArray
     * @param aFlatIndex
     * @param anIndex
     * @return
     */
    protected int initializeIndexAndAccessArrayValue(int[][][] aThreeDimensionalArray,
                                                     int aFlatIndex,
                                                     NDimensionalArrayIndex anIndex) {

        int     tempValue;
        int[]   tempIndexTuple;

        anIndex.initializeMyselfFrom(aFlatIndex);
        tempIndexTuple = anIndex.getDimensionIndexTuple();
        tempValue = aThreeDimensionalArray[tempIndexTuple[2]]
                                          [tempIndexTuple[1]]
                                          [tempIndexTuple[0]];

        return tempValue;
    }



}
