package com.cracking;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 *
 *
 */
public class ZeroValueSortTest {

    /**
     * Answer an instance for the following arguments
     */
    public ZeroValueSortTest() {
       super();
    }
    
    /**
     * Perform sort test
     */
    @Test
    public void zeroValueSortTest() {
        
        ZeroValueSort   tempSort = new ZeroValueSort();
        int[]           tempTestArray;
        int             tempNumberOfIterations;
        
        //Test 1
        tempNumberOfIterations = 0;
        tempTestArray = this.createTestArray(3);
        System.out.println("Unsorted Array: " + this.dumpArray(tempTestArray));
        tempNumberOfIterations = tempSort.sort(tempTestArray,  
                                               (int)Math.pow(tempTestArray.length, 2));
        
        System.out.println("Sorted Array: " +  this.dumpArray(tempTestArray));
        System.out.println("Number of iterations: " + tempNumberOfIterations);
        assertTrue("Test 1 failed", tempSort.isSorted(tempTestArray));
        System.out.println();
        
        //Test 2
        tempNumberOfIterations = 0;
        tempTestArray = this.createTestArray(4);
        System.out.println("Unsorted Array: " + this.dumpArray(tempTestArray));
        tempNumberOfIterations = tempSort.sort(tempTestArray,  
                                               (int)Math.pow(tempTestArray.length, 2));
        
        System.out.println("Sorted Array: " +  this.dumpArray(tempTestArray));
        System.out.println("Number of iterations: " + tempNumberOfIterations);
        assertTrue("Test 2 failed", tempSort.isSorted(tempTestArray));
        System.out.println();
        
        //Test 3
        tempNumberOfIterations = 0;
        tempTestArray = this.createTestArray(5);
        System.out.println("Unsorted Array: " + this.dumpArray(tempTestArray));
        tempNumberOfIterations = tempSort.sort(tempTestArray,  
                                               (int)Math.pow(tempTestArray.length, 2));
        System.out.println("Sorted Array: " +  this.dumpArray(tempTestArray));
        System.out.println("Number of iterations: " + tempNumberOfIterations);
        assertTrue("Test 3 failed", tempSort.isSorted(tempTestArray));
        System.out.println();
        
        //Test 4
        tempNumberOfIterations = 0;
        tempTestArray = this.createTestArray(6);
        System.out.println("Unsorted Array: " + this.dumpArray(tempTestArray));
        tempNumberOfIterations = tempSort.sort(tempTestArray,  
                                               (int)Math.pow(tempTestArray.length, 2));
        System.out.println("Sorted Array: " +  this.dumpArray(tempTestArray));
        System.out.println("Number of iterations: " + tempNumberOfIterations);
        assertTrue("Test 4 failed", tempSort.isSorted(tempTestArray));
        System.out.println();
        
        //Test 5
        tempNumberOfIterations = 0;
        tempTestArray = this.createTestArray(20);
        System.out.println("Unsorted Array: " + this.dumpArray(tempTestArray));
        tempNumberOfIterations = tempSort.sort(tempTestArray,  
                                               (int)Math.pow(tempTestArray.length, 2));
        System.out.println("Sorted Array: " +  this.dumpArray(tempTestArray));
        System.out.println("Number of iterations: " + tempNumberOfIterations);
        assertTrue("Test 5 failed", tempSort.isSorted(tempTestArray));
        System.out.println();
        
     
    }
    
    /**
     * Dump anArray
     * @param anArray int[]
     * @return String
     */
    protected String dumpArray(int[] anArray) {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        for (int i = 0; i < anArray.length; i++) {
            
            tempBuilder.append(anArray[i]);
            if (i < anArray.length - 1) {
                
                tempBuilder.append(", ");
            }
        }
        
        return  tempBuilder.toString();
        
    }
    
    /**
     * Create initial test array
     * @return int[]
     */
    protected int[] createTestArray(int aSize) {
        
        List<Integer>   tempBigResult = new ArrayList<Integer>();
        int[]           tempResult;      
        
        for (int i = 0; i < aSize; i++) {
            
            tempBigResult.add(new Integer(i));
        }
        
        Collections.shuffle(tempBigResult);
        
        tempResult = tempBigResult.stream()
                                   .mapToInt((Integer aValue) -> (aValue.intValue()))
                                   .toArray();
        
        return tempResult;
        
    } 
    
    

}
