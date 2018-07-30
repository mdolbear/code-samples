package com.cracking.ndimension;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class NDimensionalArraySortTest {

    /**
     * Answer a default instance
     */
    public NDimensionalArraySortTest() {

        super();
    }

    /**
     * Test N-Dimensional sort
     */
    @Test
    public void nDimensionalSortTest() {

        int[][][]               tempTestArray;
        NDimensionalArraySort   tempSort = new NDimensionalArraySort();

        //Test 1
        tempTestArray = this.createTestArray(3,3,3); //Array is randomized
        tempSort.basicBubbleSort(tempTestArray, 0, 3*3*3);
        assertTrue("Failed sort - Test 1", this.isSorted(tempTestArray));

        //Test 2
        tempTestArray = this.createTestArray(10,9,9); //Array is randomized
        tempSort.basicBubbleSort(tempTestArray, 0, 10*9*9);
        assertTrue("Failed sort - Test 2", this.isSorted(tempTestArray));

    }

    /**
     * Answer whether or not the result is sorted
     * @param anArray int[][][]
     * @return boolean
     */
    protected boolean isSorted(int[][][] anArray) {

        NDimensionalArrayIndex  tempIndexer;
        int[]                   tempDimensions = {anArray[0][0].length, anArray[0].length, anArray.length};
        int[]                   tempIndexTuple;
        int                     tempFlatIndex = 0;
        boolean                 tempIsSorted = true;

        tempIndexer = new NDimensionalArrayIndex(tempDimensions);
        while (tempIsSorted &&
                    tempFlatIndex < tempIndexer.getTargetSize()) {

            tempIndexer.initializeMyselfFrom(tempFlatIndex);
            tempIndexTuple = tempIndexer.getDimensionIndexTuple();
            tempIsSorted = anArray[tempIndexTuple[2]][tempIndexTuple[1]][tempIndexTuple[0]] == tempFlatIndex;
            tempFlatIndex++;

        }

        return tempIsSorted;

    }

    /**
     * Create initial test array
     * @param aSheetSize int
     * @param aRowSize int
     * @param aColumnSize int
     * @return int[]
     */
    protected int[][][] createTestArray(int aSheetSize,
                                        int aRowSize,
                                        int aColumnSize) {

        List<Integer>       tempBigResult = new ArrayList<Integer>();
        int[][][]           tempResult;
        int                 tempFlatIndex = 0;
        int                 tempSize;

        tempSize = aSheetSize*aRowSize*aColumnSize;

        for (int i = 0; i < tempSize; i++) {

            tempBigResult.add(new Integer(i));
        }

        Collections.shuffle(tempBigResult);

        tempResult = new int[aSheetSize][aRowSize][aColumnSize];
        for (int i = 0; i < aSheetSize; i++) {

            for (int j = 0; j < aRowSize; j++) {

                for (int k = 0; k < aColumnSize; k++) {

                    tempResult[i][j][k] = tempBigResult.get(tempFlatIndex).intValue();
                    tempFlatIndex++;
                }
            }
        }

        return tempResult;

    }


}
