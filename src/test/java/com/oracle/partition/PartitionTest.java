package com.oracle.partition;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 *
 *
 */
public class PartitionTest {

    /**
     * Answer an instance for the following arguments
     */
    public PartitionTest() {
        super();
    }
    
    
    /**
     * Test can be divided into equal sum sets
     */
    @Test
    public void canBeDividedIntoEqualSumSetsTest() {
        
        Partition                   tempPartition;
        Integer[]                   tempTest1 = {new Integer(1), new Integer(5), new Integer(11), new Integer(5)};
        Integer[]                   tempTest2 = {new Integer(1), new Integer(3), new Integer(5)};
        PartitionResult<Integer>    tempResult;
        
        
        tempPartition = new Partition();
        tempResult = tempPartition.canBeBrokenIntoTwoSetsWithEqualSum(Arrays.asList(tempTest1));
        assertTrue("Partition test 1 failed", tempResult.isSuccessful() 
                                                && !tempResult.getSet1().isEmpty()
                                                    && !tempResult.getSet2().isEmpty());
        System.out.println("Set1: " + tempResult.getSet1().toString() + " Set2: " + tempResult.getSet2().toString());
        
        tempResult = tempPartition.canBeBrokenIntoTwoSetsWithEqualSum(Arrays.asList(tempTest2));
        assertTrue("Partition test 2 failed", !tempResult.isSuccessful() 
                                                && tempResult.getSet1().isEmpty()
                                                    && tempResult.getSet2().isEmpty());
        
        
        
    }



    /**
     * Test can be divided into equal sum sets
     */
    @Test
    public void findMinimumDifferenceSetsTest() {
        
        Partition                   tempPartition;
        Integer[]                   tempTest1 = {new Integer(1), new Integer(6), new Integer(11), new Integer(5)};
        PartitionResult<Integer>    tempResult;
        
        
        tempPartition = new Partition();
        tempResult = tempPartition.findSetsWithMinimumDifference(Arrays.asList(tempTest1));
        assertTrue("Partition Minimum Difference test 1 failed", tempResult.isSuccessful() 
                                                && !tempResult.getSet1().isEmpty()
                                                    && !tempResult.getSet2().isEmpty());
        System.out.println("Set1: " + tempResult.getSet1().toString() + " Set2: " + tempResult.getSet2().toString());
        
        
        
        
    }

    
    /**
     * Find all subsets that sum up to aSum
     */
    @Test
    public void findAllSubsetsThatAddUpToASum() {
        
        Partition                   tempPartition;
        Integer[]                   tempTest1 = {new Integer(3), new Integer(34), new Integer(4), new Integer(12), new Integer(5), new Integer(2)};
        List<List<Integer>>         tempResults;
        int                         tempSum;
        
        
        tempPartition = new Partition();
        tempSum = 9;
        tempResults = tempPartition.findSubsetsWhereElementsEqualSum(Arrays.asList(tempTest1), 
                                                                     tempSum);
        assertTrue("No subsets found", !tempResults.isEmpty());
        System.out.println("Subsets: " + tempResults.toString());
        
        
    }
    
    
}


