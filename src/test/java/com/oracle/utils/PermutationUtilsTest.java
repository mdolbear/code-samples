package com.oracle.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.oracle.utils.PermutationUtils;

import static org.junit.Assert.assertTrue;

/**
 *
 *
 */
public class PermutationUtilsTest {

    /**
     * Answer an instance for the following arguments
     */
    public PermutationUtilsTest() {
       super();
    }
    
    /**
     * Basic Test
     */
    @Test
    public void basicTest() {
        
        String              tempSourceString = "cbabadcbbabbcbabaabccbabc";
        String              tempSubstring = "abbc";
        List<String>        tempPermutations;
        PermutationUtils    tempHunter = new PermutationUtils();
        
        //Test 1
        tempPermutations = new ArrayList<String>();
        tempHunter.findPermutationsIn(tempSourceString, 
                                      tempSubstring, 
                                      tempPermutations);
        
        for (String aPermutation: tempPermutations) {
            
            System.out.println(aPermutation);
        }
        
        
    }
    
    /**
     * Another basic test
     */
    @Test
    public void findAllPermutationsTest() {
        
        String              tempValue = "abc";
        PermutationUtils    tempUtils = new PermutationUtils();
        List<String>        tempList;
        
        //Three character test
        tempList = new ArrayList<String>();
        tempUtils.findAllPermutationsOf(tempValue, tempList);
        assertTrue("3 character test", tempList.size() == 6);
        System.out.println(tempList.toString());
        
        //Four character test
        tempValue = "abcd";
        tempList = new ArrayList<String>();
        tempUtils.findAllPermutationsOf(tempValue, tempList);
        assertTrue("4 character test", tempList.size() == 24);
        System.out.println(tempList.toString());
        
        //Five character test
        tempValue = "abcde";
        tempList = new ArrayList<String>();
        tempUtils.findAllPermutationsOf(tempValue, tempList);
        assertTrue("5 character test", tempList.size() == 120);
        System.out.println(tempList.toString());
        
    }
    
    /**
     * Find all array permutations test
     */
    @Test
    public void findAllArrayPermutationsTest() {
        
        PermutationUtils    tempUtils = new PermutationUtils();
        int[]               tempInitialArray = {0,1,2,3,4,5,6};
        List<int[]>         tempResults = new ArrayList<int[]>();
        
        tempUtils.findAllPermutationsOf(tempInitialArray, tempInitialArray.length, tempResults);
        assertTrue("Incorrect number of results", tempResults.size() == 5040);
        System.out.println("Possible combinations of initial int[] array:");
        this.dumpPossibleIntegerArrayCombinations(tempResults);
        
    }
    
    /**
     * Find all array permutations test
     */
    @Test
    public void findAllArrayPermutationsTwoSizeTest() {
        
        PermutationUtils    tempUtils = new PermutationUtils();
        int[]               tempInitialArray = {0,1};
        List<int[]>         tempResults = new ArrayList<int[]>();
        
        tempUtils.findAllPermutationsOf(tempInitialArray, tempInitialArray.length, tempResults);
        assertTrue("Incorrect number of results", tempResults.size() == 2);
        System.out.println("Possible combinations of initial int[] array:");
        this.dumpPossibleIntegerArrayCombinations(tempResults);
        
    }
    
    /**
     * Find all array permutations test
     */
    @Test
    public void findAllArrayPermutationsThreeSizeTest() {
        
        PermutationUtils    tempUtils = new PermutationUtils();
        int[]               tempInitialArray = {0,1,2};
        List<int[]>         tempResults = new ArrayList<int[]>();
        
        tempUtils.findAllPermutationsOf(tempInitialArray, tempInitialArray.length, tempResults);
        assertTrue("Incorrect number of results", tempResults.size() == 6);
        System.out.println("Possible combinations of initial int[] array:");
        this.dumpPossibleIntegerArrayCombinations(tempResults);
        
    }
    
    
    

    /**
     * Dump possible int[] combinations
     * @param aResults List<int[]>
     */ 
    protected void dumpPossibleIntegerArrayCombinations(List<int[]> aResults) {
        
        int[] tempCurrent;
        
        for (int i=0; i < aResults.size(); i++) {
            
            tempCurrent = aResults.get(i);
            System.out.print("{");
            
            for (int j = 0; j < tempCurrent.length; j++) {
                
                System.out.print(tempCurrent[j]);
                System.out.print(",");
                
            }
            
            System.out.println("}");

        }
        
    }
    
}
