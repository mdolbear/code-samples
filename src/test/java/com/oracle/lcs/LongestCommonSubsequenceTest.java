package com.oracle.lcs;

import org.junit.Test;
/**
 *
 *
 */
public class LongestCommonSubsequenceTest {

    /**
     * Answer an instance for the following arguments
     */
    public LongestCommonSubsequenceTest() {
        super();
    }
    
    /**
     * Test 1
     */
    @Test
    public void test1() {
        
        LongestCommonSubsequenceCalculator  tempCalc = new LongestCommonSubsequenceCalculator();
        String                              tempFirstString = "ABCBDAB";
        String                              tempSecondString = "BDCABA";
        StringBuilder                       tempBuilder;
        
        tempBuilder = tempCalc.findLongestCommonSubsequence(tempFirstString, tempSecondString);
        System.out.println("Longest common subsequence 1: " + tempBuilder.toString());
        
    }
    
    /**
     * Test 2
     */
    @Test
    public void test2() {
        
        LongestCommonSubsequenceCalculator  tempCalc = new LongestCommonSubsequenceCalculator();
        String                              tempFirstString = "10010101";
        String                              tempSecondString = "010110110";
        StringBuilder                       tempBuilder;
        
        tempBuilder = tempCalc.findLongestCommonSubsequence(tempFirstString, tempSecondString);
        System.out.println("Longest common subsequence 2: " + tempBuilder.toString());
        
    }

    

}
