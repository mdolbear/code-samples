package com.oracle.samples;

import static org.junit.Assert.assertTrue;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 *
 *
 */
public class RecordListTest {

    /**
     * Answer an instance for the following arguments
     */
    public RecordListTest() {
        super();
    }
    
    /**
     * Test merge sort
     */
    @Test
    public void testMergeSortAndRecordRetrieval() {
        
        RecordList                  tempList;
        RecordList                  tempNewList;
        List<Record>                tempRecordsInBounds;
        Map<Boolean, List<Record>>  tempRecordsInBoundsMap;
        IntSummaryStatistics        tempStats;
        
        
        tempList = this.createRecordList();
        System.out.println(tempList.toString());
        System.out.println(" ");
        
        //Perform merge sort
        tempNewList = tempList.mergeSort();
        System.out.println(" ");
        System.out.println(tempNewList.toString());
        
        //Now try filter
        tempRecordsInBounds = tempNewList.getRecordsInBoundaries(1,10, false);
        System.out.println(" ");
        System.out.println("Records in bounds 1 - 10 exclusive: " + tempRecordsInBounds.toString());
        
        tempRecordsInBounds = tempNewList.getRecordsInBoundaries2(1,10, true);
        System.out.println(" ");
        System.out.println("Records in bounds 1 - 10 inclusive: " + tempRecordsInBounds.toString());
        
        tempRecordsInBoundsMap = tempNewList.getRecordsInBoundariesWithGroupBy(1,10, true);
        System.out.println(" ");
        System.out.println("Records in bounds 1 - 10 inclusive with group by: " + tempRecordsInBoundsMap.toString());
        
        //Get statistics
        tempStats = tempNewList.getSummaryOfRecords();
        System.out.println(" ");
        System.out.println("Collection statistics: " + tempStats.toString());
        
        
    }
    
    /**
     * Test bubble sort
     */
    @Test
    public void testBubbleSortAndBinarySearch() {
        
        RecordList                  tempList;
        RecordList                  tempNewList;
        Record                      tempRecord;
        
        tempList = this.createRecordList();
        System.out.println(tempList.toString());
        System.out.println(" ");
        
        
        //Perform bubble sort
        tempNewList = tempList.bubbleSort();
        System.out.println(" ");
        System.out.println(tempNewList.toString());
        
        //Perform binary search
        tempRecord = tempNewList.binarySearch(15);
        assertTrue("Binary search failed", tempRecord != null);
        
        tempRecord = tempNewList.binarySearch(1);
        assertTrue("Binary search failed", tempRecord != null);
        
        tempRecord = tempNewList.binarySearch(102);
        assertTrue("Binary search failed", tempRecord != null);
        
        
        //Perform binary search
        tempRecord = tempNewList.binarySearch(250);
        assertTrue("Binary search failed", tempRecord == null);
        
        //Perform binary search
        tempRecord = tempNewList.binarySearch(14);
        assertTrue("Binary search failed", tempRecord == null);
        
        tempRecord = tempNewList.binarySearch(0);
        assertTrue("Binary search failed", tempRecord == null);
        
    }
    
    
    /**
     * Create record list
     * @return RecordList
     */
    protected RecordList createRecordList() {
        
        int[]       tempValues = {20, 18, 102, 30, 40, 10, 55, 28, 6, 3, 2, 8, 1, 15, 17, 13, 27, 22};
        RecordList  tempResult;
        
        tempResult = RecordList.createFrom(tempValues);
        
        return tempResult;
        
    }
    
    /**
     * Test quick sort
     */
    @Test
    public void testQuickSort() {
        
        RecordList                  tempList;
        
        tempList = this.createRecordList();
        System.out.println("Unsorted: " + tempList.toString());
        System.out.println(" ");
        
        tempList.quickSort(0, tempList.getSize()-1);
        System.out.println(" ");
        System.out.println("Sorted: " + tempList.toString());
        System.out.println(" ");
        
    }
    
    /**
     * Test heap sort.
     */
    @Test
    public void testHeapSort() {
        
        RecordList                  tempList;
        
        tempList = this.createRecordList();
        System.out.println("Unsorted: " + tempList.toString());
        System.out.println(" ");
        
        tempList.heapSort(tempList.getSize());
        System.out.println(" ");
        System.out.println("Sorted: " + tempList.toString());
        System.out.println(" ");
        
        for (int i = 0; i < 20; i++) {System.out.println();}
        
        System.out.println("Insert Test:");
        tempList.insertIntoHeap(101, 18);
        System.out.println(" ");
        System.out.println("After insert: " + tempList.toString());
        System.out.println(" ");
        
        tempList.heapSort(19);
        System.out.println(" ");
        System.out.println("Sorted: " + tempList.toString());
        System.out.println(" ");
        
        
    }
    
    /**
     * Test heap sort.
     */
    @Test
    public void testHeapSor2() {
        
        RecordList                  tempList;
        
        tempList = this.createRecordList2();
        System.out.println("Unsorted: " + tempList.toString());
        System.out.println(" ");
        
        tempList.heapSort(tempList.getSize());
        System.out.println(" ");
        System.out.println("Sorted: " + tempList.toString());
        System.out.println(" ");
        
    }

    
    /**
     * Create record list
     * @return RecordList
     */
    protected RecordList createRecordList2() {
        
        int[]       tempValues = {1,2,8,10,20};
        RecordList  tempResult;
        
        tempResult = RecordList.createFrom(tempValues);
        
        return tempResult;
        
    }
    
    /**
     * Test cormen quick sort
     */
    @Test
    public void testCormenQuickSort() {
        
        RecordList                  tempList;
        
        tempList = this.createRecordList();
        System.out.println("Unsorted: " + tempList.toString());
        System.out.println(" ");
        
        tempList.cormenQuickSort(0, tempList.getSize()-1);
        System.out.println(" ");
        System.out.println("Sorted: " + tempList.toString());
        System.out.println(" ");
        
    }
    
    
    /**
     * Test counting sort.
     */
    @Test
    public void testCountingSort() {
        
        RecordList                  tempList;
        int                         tempMaxKey;
        IntSummaryStatistics        tempStats;
        RecordList                  tempResult;
        
        tempList = this.createRecordList();
        tempStats = tempList.getSummaryOfRecords();
        System.out.println("Unsorted: " + tempList.toString());
        System.out.println(" ");
        
        tempMaxKey = tempStats.getMax();
        tempResult = tempList.countingSort(tempStats.getMax());
        System.out.println("Max key for counting sort: " + tempMaxKey);
        System.out.println("Sorted: " + tempResult.toString());
        System.out.println(" ");
        
    }
    
    /**
     * Test radix sort.
     */
    @Test
    public void testRadixSort() {
        
        RecordList                  tempList;
        RecordList                  tempResult;
        
        tempList = this.createRecordListForRadixSort();
        System.out.println("Unsorted: " + tempList.toString());
        System.out.println(" ");
        
        tempResult = tempList.radixSort(4);
        System.out.println("Number of digits for counting sort: " + 4);
        System.out.println("Sorted: " + tempResult.toString());
        System.out.println(" ");
        
    }
    
    /**
     * Create record list
     * @return RecordList
     */
    protected RecordList createRecordListForRadixSort() {
        
        int[]       tempValues = {2050, 1811, 1025, 3040, 4299, 1001, 5577, 2812, 6082, 
                                  3217, 2222, 8095, 1054, 1512, 1733, 1367, 2722, 2298};
        RecordList  tempResult;
        
        tempResult = RecordList.createFrom(tempValues);
        
        return tempResult;
        
    }
    
    /**
     * Test counting sort.
     */
    @Test
    public void testInsertSort() {
        
        RecordList                  tempList;
        
        tempList = this.createRecordList();
        System.out.println("Unsorted: " + tempList.toString());
        System.out.println(" ");
        
        tempList.insertSort();
        System.out.println("Sorted: " + tempList.toString());
        System.out.println(" ");
        
    }
    
    
    
    

}
