package com.oracle.samples;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 *
 */
public class RecordList {

    private Record[] records;
    private int heapSize;
    
    /**
     * Create from aList
     * @param aValues int[]
     * @return RecordList
     */
    public static RecordList createFrom(int[] aValues) {
        
        int         tempSize;
        Record      tempCurrent = null;
        RecordList  tempList;
        
        tempSize = aValues.length;
        tempList = new RecordList(new Record[tempSize]);
        
        for (int i = 0; i < tempSize; i++) {
            
            tempCurrent = new Record(aValues[i]);
            tempList.setRecord(i, tempCurrent);
        }
        
        
        return tempList;
        
    }
    
    
    /**
     * Answer an instance for the following arguments
     * @param aRecords Record[]
     */
    public RecordList(Record[] aRecords) {
        
        super();
        this.setRecords(aRecords);
        this.setHeapSize(0);
        
    }
    

  
    /**
     * Answer a sorted RecordList based on me
     * @return RecordList
     */
    public RecordList mergeSort() {
        
       RecordList   tempResult;
       
       tempResult = new RecordList(new Record[this.getSize()]);
       this.basicMerge(0,
                       this.getSize(),
                       tempResult);
       
       
       return tempResult;
       
    }
    
    /**
     * Answer a sorted RecordList based on me
     * @return RecordList
     */
    public RecordList bubbleSort() {
        
       this.basicBubbleSort(0,
                            this.getSize());
       
       
       return this;
       
    }
    
    
    
    /**
     * Basic merge
     * @param aStartIndex int
     * @param anEndIndex int
     * @return Record
     */
    protected void basicMerge(int aStartIndex,
                              int anEndIndex,
                              RecordList aResult) {
        int         tempMidpoint;
        RecordList  tempStartingResult;
        RecordList  tempMidResult;
        int         tempIntervalLength;
        
        tempIntervalLength = anEndIndex - aStartIndex;
        
        if (tempIntervalLength > 1) {
            
            tempMidpoint = (aStartIndex + anEndIndex)/2;
            
            tempStartingResult = new RecordList(new Record[tempMidpoint - aStartIndex]);
            tempStartingResult.copyOntoMe(this, 0, aStartIndex,tempMidpoint - aStartIndex);
            this.basicMerge(aStartIndex, tempMidpoint, tempStartingResult);
            
            tempMidResult = new RecordList(new Record[anEndIndex - tempMidpoint]);
            tempMidResult.copyOntoMe(this, 0, tempMidpoint, anEndIndex - tempMidpoint);
            this.basicMerge(tempMidpoint, anEndIndex, tempMidResult);
            
            aResult.merge(tempStartingResult, tempMidResult);
        }
        

        
    }
    
    /**
     * Answer my size
     * @return int
     */
    public int getSize() {
        
       return this.getRecords().length;
        
    }
    
    
    /**
     * Answer my string representation
     * @return String
     */
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempBuilder.append("{");
        if (this.getRecords() != null) {
            
            for (int i = 0; i < this.getSize(); i++) {
                
                tempBuilder.append(this.getRecord(i));
            }
        }
        
        tempBuilder.append(" }");
        
        return tempBuilder.toString();
    }


    /**
     * Set record at anIndex
     * @param anIndex int
     * @param aRecord Record
     */
    public void setRecord(int anIndex, 
                          Record aRecord) {
        
        this.getRecords()[anIndex] = aRecord;
    }
    
    /**
     * Answer my record at anIndex
     * @param anIndex
     * @return Record
     */
    public Record getRecord(int anIndex) {
        
        return this.getRecords()[anIndex];
    }
    
    /**
     * Answer my records
     * @return Record[]
     */
    public Record[] getRecords() {
        return records;
    }


    /**
     * Set my records
     * @param records Object[]
     */
    public void setRecords(Record[] records) {
        this.records = records;
    }
    
    
    
    /**
     * Answer a third list of records by merging me and another list that begins with aRecord
     * @param aStartingRecordList RecordList
     * @param aMidpointRecordList RecordList
     */
    protected void merge(RecordList aStartingRecordList,
                         RecordList aMidpointRecordList) {
        
        int         tempFirstListIdx;
        int         tempSecondListIdx;
        Record      tempNewData;
        int         tempFirstKey;
        int         tempSecondKey;
        int         tempTargetDataIndex;
        
        
        tempFirstListIdx = 0;
        tempSecondListIdx = 0;
        tempTargetDataIndex = 0;
        
        System.out.println(" ");
        System.out.println("First half: " + aStartingRecordList.toString());
        System.out.println("Second half: " + aMidpointRecordList.toString());
        
        //Merge lists
        while (tempFirstListIdx < aStartingRecordList.getSize() && 
                    tempSecondListIdx < aMidpointRecordList.getSize()) {
            
            tempFirstKey = aStartingRecordList.getRecord(tempFirstListIdx).getKey();
            tempSecondKey = aMidpointRecordList.getRecord(tempSecondListIdx).getKey();
            
            if (tempFirstKey <= tempSecondKey) {
                
                tempNewData = new Record(tempFirstKey);
                this.setRecord(tempTargetDataIndex, tempNewData);
                
                tempTargetDataIndex++;             
                tempFirstListIdx++;
                
            }
            else {
                
                tempNewData = new Record(tempSecondKey);
                this.setRecord(tempTargetDataIndex, tempNewData);
                
                tempTargetDataIndex++;
                tempSecondListIdx++;
                
            }
            

            
        }
        
        //Move remainder
        if (tempFirstListIdx >= aStartingRecordList.getSize()) {
            this.copyRemainingOntoMe(aMidpointRecordList,
                                     tempTargetDataIndex,
                                     tempSecondListIdx);
        }
        else {
            this.copyRemainingOntoMe(aStartingRecordList,
                                     tempTargetDataIndex,
                                     tempFirstListIdx);
        }
        
        
        System.out.println("Results at end of merge: " + this.toString());
        System.out.println(" ");
        
    }
    
    /**
     * Copy remaining onto me from aRecord
     * @param aRecordList Record
     */
    protected void copyOntoMe(RecordList aRecordList,
                              int aTargetStartPointIdx,
                              int aSourceStartPointIdx,
                              int aSize) {
        
        int i;
        
        i = aTargetStartPointIdx;
       
        while (aSize > 0) {
            
            this.setRecord(i++, aRecordList.getRecord(aSourceStartPointIdx++));
            aSize--;
        }
        
    }
    

    /**
     * Copy remaining onto me from aRecord
     * @param aRecordList Record
     */
    protected void copyRemainingOntoMe(RecordList aRecordList,
                                       int aTargetStartPointIdx,
                                       int aSourceStartPointIdx) {
        
       
        for (int i = aTargetStartPointIdx; (i < this.getSize() && aSourceStartPointIdx < aRecordList.getSize()); i++) {
            
            this.setRecord(i, aRecordList.getRecord(aSourceStartPointIdx++));
        }
        
    }
    
    
  
    /**
     * Answer my records in bounds. Just an example use of streams and filters
     * @param aLowerBound
     * @param anUpperBound
     * @return
     */
    public Map<Boolean, List<Record>> getRecordsInBoundariesWithGroupBy(int aLowerBound, 
                                                                        int anUpperBound,
                                                                        boolean inclusive) {
        
        Map<Boolean,List<Record>>        tempResults;
        Predicate<Record>                tempRecordsInBounds = (Record r) -> ((r.getKey() > aLowerBound 
                                                                                            && r.getKey() < anUpperBound) ||
                                                                                            inclusive && (r.getKey() == aLowerBound ||
                                                                                                         r.getKey() == anUpperBound));

        List<Record>         tempInitialRecords;
        
        tempInitialRecords = Arrays.asList(this.getRecords());
        
        tempResults = 
                tempInitialRecords.stream()
                            .filter(tempRecordsInBounds)
                            .collect(Collectors.partitioningBy(tempRecordsInBounds));
        
        return tempResults;
        
    }
    
    
    /**
     * Answer my records in bounds. Just an example use of streams and filters
     * @param aLowerBound
     * @param anUpperBound
     * @return
     */
    public List<Record> getRecordsInBoundaries(int aLowerBound, 
                                               int anUpperBound,
                                               boolean inclusive) {
        
        List<Record>         tempResults;
        Predicate<Record>    tempRecordsInBounds = (Record r) -> ((r.getKey() > aLowerBound 
                                                                    && r.getKey() < anUpperBound) ||
                                                                    inclusive && (r.getKey() == aLowerBound ||
                                                                                 r.getKey() == anUpperBound));

        List<Record>         tempInitialRecords;
        
        tempInitialRecords = Arrays.asList(this.getRecords());
        
        tempResults = 
                tempInitialRecords.stream()
                            .filter(tempRecordsInBounds)
                            .collect(Collectors.toList());
        
        return tempResults;
        
    }
    
    /**
     * Answer my records in bounds. Just an example use of streams and filters
     * @param aLowerBound
     * @param anUpperBound
     * @return
     */
    public List<Record> getRecordsInBoundaries2(int aLowerBound, 
                                                int anUpperBound,
                                                boolean inclusive) {
        
        List<Record>         tempResults;
        List<Record>         tempInitialRecords;
        Predicate<Record>    tempRecordsInBounds;
        
        tempRecordsInBounds = this.basicCreatePredicateForInboundsRecords(aLowerBound, anUpperBound, inclusive);
        tempInitialRecords = Arrays.asList(this.getRecords());
        
        tempResults = 
                tempInitialRecords.stream()
                            .filter(tempRecordsInBounds)
                            .collect(Collectors.toList());
        
        return tempResults;
        
    }
    
    /**
     * Answer my int summary statistics
     * @return IntSummaryStatistics
     */
    public IntSummaryStatistics getSummaryOfRecords() {
        
        IntSummaryStatistics tempResult;
        List<Record>         tempInitialRecords;

        tempInitialRecords = Arrays.asList(this.getRecords()); 
        tempResult = 
                tempInitialRecords.stream()
                                  .collect(Collectors.summarizingInt(Record::getKey));
        
        return tempResult;
        
    }
    


    /**
     * Create predicate for records in bounds test
     * @param aLowerBound
     * @param anUpperBound
     * @param inclusive
     * @return
     */
    protected Predicate<Record> basicCreatePredicateForInboundsRecords(int aLowerBound,
                                                                       int anUpperBound, 
                                                                       boolean inclusive) {
        
        return new Predicate<Record> () {
            
             public boolean test(Record r) {
                 
                 return ((r.getKey() > aLowerBound && r.getKey() < anUpperBound) ||
                          inclusive && (r.getKey() == aLowerBound || r.getKey() == anUpperBound));
                 
             }
             
        };
        
    }
    
    /**
     * Basic bubble sort
     *
     * @param aStartIndex int
     * @param anEndIndex int
     *
     */
     protected void basicBubbleSort(int aStartIndex,
                                    int anEndIndex) {
            
            
        int     i;
        int     j;
        Record  tempCurrentMinimumRecord;
        Record  tempCurrent;
        
        for (i = aStartIndex; i < anEndIndex; i++) {
            
            tempCurrentMinimumRecord = this.getRecord(i);
            
            for (j = i + 1; j < anEndIndex; j++) {
                
                tempCurrent = this.getRecord(j);
                if (tempCurrent.getKey() < tempCurrentMinimumRecord.getKey()) {
                    
                    tempCurrentMinimumRecord = tempCurrent;
                }
                
            }
            
            //Perform switch        
            tempCurrent = this.getRecord(i);
            tempCurrent.interchangeKeysAndData(tempCurrentMinimumRecord);
            
        }

    }
    
    
     /**
      * Binary search. Answer the Record that
      * contains aValue or null
      * @param  aValue int
      * @return Record
      */
     public Record binarySearch(int aValue) {
         
         Record      tempResult = null;
         int         tempStartIndex;
         int         tempEndIndex;
         int         tempMidpointIndex;
         int         tempMidpointValue;
         
         //Initialize
         tempStartIndex = 0;
         tempEndIndex = this.getSize();
         
         while (this.shouldContinueBinarySearch(tempResult, tempStartIndex, tempEndIndex)) {
             
             tempMidpointIndex = (tempStartIndex + tempEndIndex) >>> 1;
             tempMidpointValue = this.getRecord(tempMidpointIndex).getKey();
             if (tempMidpointValue == aValue) {
                 
                 tempResult = this.getRecord(tempMidpointIndex);
             }
             else if (aValue > tempMidpointValue) {
                 
                 tempStartIndex = tempMidpointIndex+1;
             }
             else if (aValue < tempMidpointValue) {
                 
                 tempEndIndex = tempMidpointIndex-1;
             }
             
             
             
         }
         
         return tempResult;
         
         
     }


    /**
     * Determine if I should continue binary search
     * @param aResult
     * @param aStart
     * @param aEnd
     * @return boolean
     */
    protected boolean shouldContinueBinarySearch(Record aResult, 
                                                 int aStart,
                                                 int aEnd) {
        
        return aStart <= aEnd && 
                     aStart < this.getSize() &&
                         aEnd <= this.getSize() &&
                             aResult == null;
        
    }
   
    
    /**
     * Perform quicksort on me
     * @param aSelectedRecordIndex int
     * @param anEndIndex int
     */
    public void quickSort(int aSelectedRecordIndex, int anEndIndex) {
        
        int             j;
        
        if (aSelectedRecordIndex < anEndIndex) {
            
            j = this.partition(aSelectedRecordIndex, anEndIndex);
            
            this.quickSort(aSelectedRecordIndex, j-1);
            this.quickSort(j+1, anEndIndex);
            
        }
        else {
            
            this.logQuickSortIfExit(aSelectedRecordIndex, anEndIndex);
        }

        
    }
    
    /**
     * Partition me based on the control key
     * @param aSelectedRecordIndex int
     * @param anEndIndex int
     */
    protected int partition(int aSelectedRecordIndex, int anEndIndex) {
        
        int             i;
        int             j;
        int             tempSelectedKey;
        int             tempCurrentKeyI;
        int             tempCurrentKeyJ;
        
        i = aSelectedRecordIndex;
        j = anEndIndex+1;
        tempSelectedKey = this.getRecord(aSelectedRecordIndex).getKey();       
        this.logStartOfQuickSort(i, j, tempSelectedKey);
        
        while (i < j) {
            
            //Look for a key that is > the mid key picked in lower partition
            do {
                
                i++;
                tempCurrentKeyI = this.getRecord(i).getKey();
                
            } while (tempCurrentKeyI < tempSelectedKey &&
                    this.isIndexInBounds(i+1));
            
            
            //Look for a key that is < the mid key picked in upper partition
            do {
                
                j--;
                tempCurrentKeyJ = this.getRecord(j).getKey();
                
            } while(tempCurrentKeyJ > tempSelectedKey && this.isIndexInBounds(j-1));
            
            //Switch lower value from upper part of file and higher value from lower part of file
            if (i < j) {
                
                System.out.println("Interchanging["+i+"]: " + this.getRecord(i) + " with ["+ j + "]: " + this.getRecord(j));
                this.getRecord(i).interchangeKeysAndData(this.getRecord(j));
            }
            
            
        }
        
        this.logBeforeRecursiveCalls(aSelectedRecordIndex, anEndIndex, j);
        this.getRecord(aSelectedRecordIndex).interchangeKeysAndData(this.getRecord(j));
        
        return j;
        
    }


    /**
     * @param aSelectedRecordIndex
     * @param anEndIndex
     */
    protected void logQuickSortIfExit(int aSelectedRecordIndex,
            int anEndIndex) {
        
        System.out.println();
        System.out.println("QuickSort: exited if");
        System.out.println("aSelectedIndex: " + aSelectedRecordIndex);
        System.out.println("anEndIndex: " + anEndIndex);
        System.out.println("Result: " + this.toString());
        System.out.println();
        
    }


    /**
     * @param i
     * @param j
     * @param tempMidKey
     */
    protected void logStartOfQuickSort(int i, int j, int tempMidKey) {
        
        System.out.println();
        System.out.println("Lower half index: " + i);
        System.out.println("Upper half index: " + j);
        System.out.println("Key compare value: " + tempMidKey);
        
    }


    /**
     * @param aSelectedRecordIndex
     * @param anEndIndex
     * @param j
     */
    protected void logBeforeRecursiveCalls(int aSelectedRecordIndex, int anEndIndex, int j) {
        
        System.out.println("QuickSort: pass through while interchange");
        System.out.println("aMidIndex: " + aSelectedRecordIndex);
        System.out.println("anEndIndex: " + anEndIndex);
        System.out.println("Result: " + this.toString());
        System.out.println("Interchanging["+aSelectedRecordIndex+"]: " + this.getRecord(aSelectedRecordIndex) 
                            + " with ["+ j + "]: " + this.getRecord(j));
        
    }
    

    
    /**
     * Answer whether index is in bounds
     * @param anIndex int
     * @return boolean
     */
    protected boolean isIndexInBounds(int anIndex) {
        
        return (anIndex >= 0) && (anIndex < this.getSize());
    }
    
    //Methods to do a heap sort using an list
    
    /**
     * Answer left index
     * @param anIndex int
     * @return int
     */
    protected int getLeftTreeIndex(int anIndex) {
        
        return 2 * anIndex + 1;
    }

    /**
     * Answer right index
     * @param anIndex int
     * @return int
     */
    protected int getRightTreeIndex(int anIndex) {
        
        return 2 * anIndex + 2;
    }
    
    /**
     * Answer parent index
     * @param anIndex nit
     * @return int
     */
    protected int getParentTreeIndex(int anIndex) {
        
        return anIndex/2;
        
    }


    /**
     * Answer my heapSize
     * @return int
     */
    protected int getHeapSize() {
        return heapSize;
    }


    /**
     * Set my heapSize
     * @param heapSize int
     */
    protected void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }
    
    
    /**
     * Insert into heap
     * @param aKey int
     */
    public void insertIntoHeap(int aKey, int aSize) {
        
        Record  tempRecord = new Record(aKey);
        int     i;
        
        this.setHeapSize(aSize);
        this.increaseMySizeBy(1);
        this.setHeapSize(this.getHeapSize()+1);
        i = this.getHeapSize()-1;
        
        while (i > 0 && (this.getRecord(this.getParentTreeIndex(i)).getKey() < aKey)) {
            
            this.setRecord(i, this.getRecord(this.getParentTreeIndex(i)));
            i = this.getParentTreeIndex(i);
        }
        
        this.setRecord(i, tempRecord);
        
    }

    /**
     * Increase my size by aValue
     * @param aValue int
     */
    protected void increaseMySizeBy(int aValue) {
        
        Record[]    tempNewArray;
        
        tempNewArray = Arrays.copyOf(this.getRecords(), this.getSize() + aValue);
        this.setRecords(tempNewArray);
        
    }

    
    /**
     * Heap sort
     * @param aSize int
     */
    public void heapSort(int aSize) {
        
        this.buildHeap(aSize);
        
        System.out.println("After first call to build heap: " + this.toString());
        
        for (int i = aSize - 1; i >= 0; i--) {
            
            this.getRecord(0).interchangeKeysAndData(this.getRecord(i)); //This puts the root on the end of the list
                                                                         //since its the biggest after the creation of
                                                                         //the heap
            this.setHeapSize(i); //heapSize = heapSize -1 on each iteration
            
            System.out.println("Calling max heapify: " + this.toString());
            this.maxHeapify(0);
            System.out.println("Output after max heapify: " + this.toString());
            
        }
        
    }
    
    
    /**
     * Build heap
     */
    protected void buildHeap(int aSize) {
        
        this.setHeapSize(aSize);
        for (int i = (aSize)/2 - 1; i >= 0; i--) {
            
            this.maxHeapify(i);
        }
        
    }
    
    
    /**
     * Max heapify me
     * @param aRootNodeIndex int
     */
    protected void maxHeapify(int aRootNodeIndex) {
        
        int tempLeftIndex;
        int tempRightIndex;
        int tempLargestIndex;
        
        tempLeftIndex = this.getLeftTreeIndex(aRootNodeIndex);
        tempRightIndex = this.getRightTreeIndex(aRootNodeIndex);
        tempLargestIndex = aRootNodeIndex; //Start out assuming the root is the largest
        
        System.out.println("Current heap size: " + this.getHeapSize());
        System.out.println("Current Root index: " + aRootNodeIndex + " value: " +  this.getRecord(aRootNodeIndex).getKey());
        System.out.println("Proposed left tree index: " + tempLeftIndex);
        System.out.println("Proposed right tree index: " + tempRightIndex);
        
        if (tempLeftIndex < this.getHeapSize() && 
                (this.getRecord(tempLeftIndex).getKey() > this.getRecord(tempLargestIndex).getKey())) {
            
            tempLargestIndex = tempLeftIndex;
        }

        
        if (tempRightIndex < this.getHeapSize() && 
                (this.getRecord(tempRightIndex).getKey() > this.getRecord(tempLargestIndex).getKey())) {
            
            tempLargestIndex = tempRightIndex;
            
        }
        
        //Keep doing the "heapify" because, by moving the root node down since its not the largest, now
        // causes the heap to violate its rules
        if (tempLargestIndex != aRootNodeIndex) {
            
            System.out.println("Find element larger than root at index: " 
                               + tempLargestIndex + " value: " 
                               + this.getRecord(tempLargestIndex).getKey() 
                               + " interchanging values.");
            this.getRecord(aRootNodeIndex).interchangeKeysAndData(this.getRecord(tempLargestIndex)); //Interchange root with 
                                                                                                     //largest
            this.maxHeapify(tempLargestIndex); //Continue doing the heapify recursively if root was not largest
            
        }
        else {
            
            System.out.println("Current root is the largest");
        }
        
    }
    
    
    /**
     * Perform cormen version of quicksort on me
     * @param aBeginIndex int
     * @param anEndIndex int
     */
    public void cormenQuickSort(int aBeginIndex, int anEndIndex) {
        
        int             j;
        
        if (aBeginIndex < anEndIndex) {
            
            j = this.cormenPartition(aBeginIndex, anEndIndex);
            
            System.out.println("Cormen Partition - pivot index on return from partition: " + j);
            System.out.println("Cormen Partition - pivot key on return from partition: " + this.getRecord(j).getKey());
            
            this.cormenQuickSort(aBeginIndex, j-1);
            this.cormenQuickSort(j+1, anEndIndex);

            
        }
        else {
            
            this.logQuickSortIfExit(aBeginIndex, anEndIndex);
        }

        
    }
    
    /**
     * Perform cormen version of partition for quicksort
     * @param aBeginIndex int
     * @param anEndIndex int
     * @return int
     */
    protected int cormenPartition(int aBeginIndex,
                                  int anEndIndex) {
        
        int j;
        int i;
        int tempPivotKey;
        
        tempPivotKey = this.getRecord(anEndIndex).getKey(); //Getting "Pivot" key from end index record
        i = aBeginIndex -1; 
        System.out.println("Cormen Partition - pivot key: " + tempPivotKey);
        for (j = aBeginIndex; j < anEndIndex; j++) {
            
            if (this.getRecord(j).getKey() <= tempPivotKey) {
                
                i++;
                this.getRecord(i).interchangeKeysAndData(this.getRecord(j));
                
            }
        }
        
        this.getRecord(i+1).interchangeKeysAndData(this.getRecord(anEndIndex)); //This last one was never swapped
                                                                                //and is larger than the pivot key,
                                                                                //and will now be the new pivot point index
        
        return i+1;
        
    }
    
    /**
     * Counting sort. Answer a new instance as me sorted.
     * @param aMaxKey int
     * @return RecordList
     */
    public RecordList countingSort(int aMaxKey) {
        
        int[]       tempCountingKeys;
        RecordList  tempResult;
        
        //Create and zero out counting array
        tempCountingKeys = this.createAndInitializeCountingKeyArray(aMaxKey);
        
        //Count the keys for me and count each element that exists in me. The result will be an 
        //array that has a count for each particular value
        for (int i = 0; i < this.getSize(); i++) {
            
            tempCountingKeys[this.getRecord(i).getKey()] = tempCountingKeys[this.getRecord(i).getKey()] + 1;
        }
        
        //Add in elements so that each element contains the count less than or equal to a given value
        for (int i = 1; i < tempCountingKeys.length; i++) {
            
            tempCountingKeys[i] = tempCountingKeys[i] + tempCountingKeys[i - 1];
        }
        
        //Now perform insertion of the record into its proper position based on the previous steps
        tempResult = new RecordList(new Record[this.getSize()]);
        for (int i = this.getSize()-1; i >=0; i--) {
            
            tempResult.setRecord(tempCountingKeys[this.getRecord(i).getKey()] - 1, this.getRecord(i));
            tempCountingKeys[this.getRecord(i).getKey()] = tempCountingKeys[this.getRecord(i).getKey()] - 1;
        }
        
        return tempResult;
        
        
    }


    /**
     * Create and initialize counting key array
     * @param aMaxKey
     */
    protected int[] createAndInitializeCountingKeyArray(int aMaxKey) {
        
        int[]   tempCountingKeys;
        
        tempCountingKeys =  new int[aMaxKey+1];
        
        for (int i = 0; i < aMaxKey+1; i++) {
            tempCountingKeys[i] = 0;
        }
        
        return tempCountingKeys;
    }
    
 
    /**
     * Radix sort
     * @param aNumberOfDigits int
     * @return RecordList
     */
    public RecordList radixSort(int aNumberOfDigits) {
        
        int             tempMaxKey;
        int             tempCurrentMaxDigit;
        RecordList      tempCurrentList;
        
        tempMaxKey = this.getSummaryOfRecords().getMax();
        tempCurrentList = this;
        for (int i = 0; i <= aNumberOfDigits; i++) {
            
            tempCurrentMaxDigit = this.getDigitFrom(tempMaxKey, i);
            tempCurrentList = tempCurrentList.countingSortForDigit(tempCurrentMaxDigit, i);
        }
        
        return tempCurrentList;
        
    }
    
    /**
     * Answer the digit from aValue
     * @param aValue int
     * @param aDigitNumber int
     * @return int
     */
    protected int getDigitFrom(int aValue, 
                               int aDigitNumber) {
        
        int tempResult;
        
        tempResult = aValue / (int)Math.pow(10.0, aDigitNumber);
        tempResult = tempResult % 10;
        
        return tempResult;
        
    }
    
    
    /**
     * Counting sort. Answer a new instance as me sorted.
     * @param aMaxKeyDigit int
     * @param aDigitNumber int
     * @return RecordList
     */
    protected RecordList countingSortForDigit(int aMaxKeyDigit,
                                              int aDigitNumber) {
        
        int[]       tempCountingKeys;
        RecordList  tempResult;
        
        //Create and zero out counting array
        tempCountingKeys = this.createAndInitializeCountingKeyArray(9);
        
        //Count the keys for me and count each element that exists in me. The result will be an 
        //array that has a count for each particular value
        for (int i = 0; i < this.getSize(); i++) {
            
            tempCountingKeys[this.getRecord(i).getDigitOfKey(aDigitNumber)] = tempCountingKeys[this.getRecord(i).getDigitOfKey(aDigitNumber)] + 1;
        }
        
        //Add in elements so that each element contains the count less than or equal to a given value
        for (int i = 1; i < tempCountingKeys.length; i++) {
            
            tempCountingKeys[i] = tempCountingKeys[i] + tempCountingKeys[i - 1];
        }
        
        //Now perform insertion of the record into its proper position based on the previous steps
        tempResult = new RecordList(new Record[this.getSize()]);
        for (int i = this.getSize()-1; i >=0; i--) {
            
            tempResult.setRecord(tempCountingKeys[this.getRecord(i).getDigitOfKey(aDigitNumber)] - 1, this.getRecord(i));
            tempCountingKeys[this.getRecord(i).getDigitOfKey(aDigitNumber)] = tempCountingKeys[this.getRecord(i).getDigitOfKey(aDigitNumber)] - 1;
        }
        
        return tempResult;
        
        
    }
    
    /**
     * Insert sort
     */
    public void insertSort() {
        
        int     tempKey;
        int     i;
        Record  tempCurrentRecord;
        
        for (int j = 1; j < this.getSize(); j++) {
            
            tempKey = this.getRecord(j).getKey();
            tempCurrentRecord = this.getRecord(j);
            
            //We now insert the j element into the sorted sequence from 0 to j-1
            i = j - 1;
            while (i >= 0 &&
                        this.getRecord(i).getKey() > tempKey) {
                
                this.setRecord(i+1, this.getRecord(i));
                i = i - 1;
            }
            
            this.setRecord(i+1, tempCurrentRecord);
            
        }
        
        
    }
    
}
