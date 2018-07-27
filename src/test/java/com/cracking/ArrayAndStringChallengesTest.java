package com.cracking;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 *
 *
 */
public class ArrayAndStringChallengesTest {

    /**
     * Answer an instance for the following arguments
     */
    public ArrayAndStringChallengesTest() {
       super();
    }
    
    /**
     * Test isPermutationOf challenge
     */
    @Test
    public void isPermutationOfTest() {
        
        
        ArrayAndStringChallenges    tempChallenge;
        boolean                     tempResult;
        
        tempChallenge = new ArrayAndStringChallenges();
        tempResult = tempChallenge.isPermutationOf("abba", "baab");
        assertTrue("Should be true 1", tempResult);
        
        tempChallenge = new ArrayAndStringChallenges();
        tempResult = tempChallenge.isPermutationOf("abbc", "baab");
        assertTrue("Should be false 1", !tempResult);
        
        tempChallenge = new ArrayAndStringChallenges();
        tempResult = tempChallenge.isPermutationOf("cccc", "cccc");
        assertTrue("Should be true 2", tempResult);
                
        tempChallenge = new ArrayAndStringChallenges();
        tempResult = tempChallenge.isPermutationOf("accc", "acc");
        assertTrue("Should be false 2", !tempResult);
        
        tempChallenge = new ArrayAndStringChallenges();
        tempResult = tempChallenge.isPermutationOf("abcdef", "fedcba");
        assertTrue("Should be true 3", tempResult);
        
    }
    
    /**
     * Test urlIfyRegex
     */
    @Test
    public void urlIfyRegexTest() {
        
        ArrayAndStringChallenges    tempChallenge;
        String                      tempResult;
        
        tempChallenge = new ArrayAndStringChallenges();
        tempResult = tempChallenge.urlIfyRegex("     Mr John Smith     ");
        System.out.println(tempResult);
        assertTrue("Should be no spaces", tempResult.indexOf(" ") == -1);
        
    }
    
    
    /**
     * Test urlIfyRegex
     */
    @Test
    public void urlIfy() {
        
        ArrayAndStringChallenges    tempChallenge;
        String                      tempResult;
        
        tempChallenge = new ArrayAndStringChallenges();
        tempResult = tempChallenge.urlIfy("     Mr John Smith     ");
        System.out.println(tempResult);
        assertTrue("Should be no spaces", tempResult.indexOf(" ") == -1);
        
        //Test case with empty string
        tempChallenge = new ArrayAndStringChallenges();
        tempResult = tempChallenge.urlIfy(new String());
        System.out.println(tempResult);
        assertTrue("Should be no spaces", tempResult.indexOf(" ") == -1);
        
        //Test case with only spaces
        tempChallenge = new ArrayAndStringChallenges();
        tempResult = tempChallenge.urlIfy("          ");
        System.out.println(tempResult);
        assertTrue("Should be no spaces", tempResult.indexOf(" ") == -1);
        
    }
    
    /**
     * Test find all permutations that are palindromes
     */
    @Test
    public void findAllPermutationsThatArePalindromes() {
        
        String                      tempValue;
        ArrayAndStringChallenges    tempChallenge;
        Set<String>                 tempResults;
        
        tempChallenge = new ArrayAndStringChallenges();
        tempValue = "Tact Coa";
        tempResults = tempChallenge.findAllPermutationsThatArePalidromes(tempValue);
        System.out.println(tempResults.toString());
        assertTrue("No results present", !tempResults.isEmpty());
        
        tempChallenge = new ArrayAndStringChallenges();
        tempValue = "arabb";
        tempResults = tempChallenge.findAllPermutationsThatArePalidromes(tempValue);
        System.out.println(tempResults.toString());
        assertTrue("No results present", !tempResults.isEmpty());
        
        tempChallenge = new ArrayAndStringChallenges();
        tempValue = "rabbit";
        tempResults = tempChallenge.findAllPermutationsThatArePalidromes(tempValue);
        System.out.println(tempResults.toString());
        assertTrue("There should be no results", tempResults.isEmpty());
        
        
    }
    
    /**
     * Test string difference
     */
    @Test
    public void stringDifferenceTest() {
        
        Set<String>                 tempResults;
        ArrayAndStringChallenges    tempChallenge;
        
        tempChallenge = new ArrayAndStringChallenges();
        
        tempResults = tempChallenge.getDifferencesBetween("taco", "cat");
        assertTrue("Should only have one difference", tempResults.size() == 1);
        System.out.println("Differences: " + tempResults);
        
        tempResults = tempChallenge.getDifferencesBetween("cat", "taco");
        assertTrue("No difference", tempResults.size() == 0);
        System.out.println("Reverse Differences: " + tempResults);
        
        tempResults = tempChallenge.getDifferencesBetween("pale", "bale");
        assertTrue("Should only have one difference", tempResults.size() == 1);
        System.out.println("Differences: " + tempResults);
        
        tempResults = tempChallenge.getDifferencesBetween("bale","pale");
        assertTrue("Should only have one difference", tempResults.size() == 1);
        System.out.println("Reverse Differences: " + tempResults);
        
        
        
    }
    
    /**
     * Edit distance test
     */
    @Test
    public void editDistanceTest() {
        
        int                         tempResult;
        ArrayAndStringChallenges    tempChallenge;
        String                      tempString1;
        String                      tempString2;
        
        tempChallenge = new ArrayAndStringChallenges();
        
        //Test 1
        tempString1 = "pale";
        tempString2 = "ple";
        tempResult = tempChallenge.computeEditDistanceBetween(tempString1,
                                                              tempString2, 
                                                              tempString1.length(),
                                                              tempString2.length());
        System.out.println("Edit distance between " 
                            + tempString1 
                            + " and " 
                            + tempString2 
                            + " = " 
                            + tempResult);
        assertTrue("Test 1", tempResult == 1);
        

        //Test 2
        tempString1 = "fox";
        tempString2 = "foxfire";
        tempResult = tempChallenge.computeEditDistanceBetween(tempString1,
                                                              tempString2, 
                                                              tempString1.length(),
                                                              tempString2.length());
        System.out.println("Edit distance between " 
                            + tempString1 
                            + " and " 
                            + tempString2 
                            + " = " 
                            + tempResult);
        assertTrue("Test 2", tempResult == 4);
        
        //Test 3
        tempString1 = "pale";
        tempString2 = "bale";
        tempResult = tempChallenge.computeEditDistanceBetween(tempString1,
                                                              tempString2, 
                                                              tempString1.length(),
                                                              tempString2.length());
        System.out.println("Edit distance between " 
                            + tempString1 
                            + " and " 
                            + tempString2 
                            + " = " 
                            + tempResult);
        assertTrue("Test 3", tempResult == 1);
        
        
        //Test 4
        tempString1 = "sail";
        tempString2 = "sail";
        tempResult = tempChallenge.computeEditDistanceBetween(tempString1,
                                                              tempString2, 
                                                              tempString1.length(),
                                                              tempString2.length());
        System.out.println("Edit distance between " 
                            + tempString1 
                            + " and " 
                            + tempString2 
                            + " = " 
                            + tempResult);
        assertTrue("Test 4", tempResult == 0);
        
        //Test 5
        tempString1 = "pale";
        tempString2 = "bake";
        tempResult = tempChallenge.computeEditDistanceBetween(tempString1,
                                                              tempString2, 
                                                              tempString1.length(),
                                                              tempString2.length());
        System.out.println("Edit distance between " 
                            + tempString1 
                            + " and " 
                            + tempString2 
                            + " = " 
                            + tempResult);
        assertTrue("Test 5", tempResult == 2);
        
        
    }
    
    
    
    /**
     * Test character compression
     * 
     */
    @Test
    public void characterCompressionTest() {
        
        String                      tempValue;
        String                      tempResult;
        ArrayAndStringChallenges    tempChallenge;

        
        tempChallenge = new ArrayAndStringChallenges();
        tempValue = "aabcccccaaa";
        tempResult = tempChallenge.compress(tempValue);
        System.out.println("Compresssion of " + tempValue + " = " + tempResult);
        assertTrue("Failed compression test 1" , tempResult.equals("a2bc5a3"));
        
        tempChallenge = new ArrayAndStringChallenges();
        tempValue = "      ";
        tempResult = tempChallenge.compress(tempValue);
        System.out.println("Compresssion of " + tempValue + " = " + tempResult);
        assertTrue("Failed compression test 2" , tempResult.equals(" 6"));
        
        tempChallenge = new ArrayAndStringChallenges();
        tempValue = "aabb";
        tempResult = tempChallenge.compress(tempValue);
        System.out.println("Compresssion of " + tempValue + " = " + tempResult);
        assertTrue("Failed compression test 3" , tempResult.equals("aabb"));
        
        
    }
   
    /**
     * Matrix rotation test. The idea here is to rotate a nxn matrix 90 degrees counter clockwise
     */
    @Test
    public void matrixRotationTestFiveByFive() {
        
        int[][]                     tempSource = {{1, 2, 3, 4, 5},
                                                  {6, 7, 8, 9, 10},
                                                  {11,12,13,14,15},
                                                  {16,17,18,19,20},
                                                  {21,22,23,24,25}
                                                 };
        int[][]                     tempResult;
        ArrayAndStringChallenges    tempChallenge;

        
        tempChallenge = new ArrayAndStringChallenges();
        
        System.out.println("Source Matrix:");
        this.dumpArray(tempSource);
        
        tempResult = tempChallenge.rotateMatrixCounterClockwise(tempSource);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);
        
        
    }
    
    
    /**
     * Matrix rotation test. The idea here is to rotate a nxn matrix 90 degrees counter clockwise
     */
    @Test
    public void matrixRotationTestFourByFour() {
        
        int[][]                     tempSource = {{1, 2, 3, 4},
                                                  {5, 6, 7, 8},
                                                  {9, 10, 11, 12},
                                                  {13, 14, 15, 16}
                                                 };
        int[][]                     tempResult;
        ArrayAndStringChallenges    tempChallenge;

        
        tempChallenge = new ArrayAndStringChallenges();
        
        System.out.println("Source Matrix:");
        this.dumpArray(tempSource);
        
        tempResult = tempChallenge.rotateMatrixCounterClockwise(tempSource);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);
        
        
    }
    
    /**
     * Matrix rotation test. The idea here is to rotate a nxn matrix 90 degrees counter clockwise
     */
    @Test
    public void matrixRotationTestThreeByThree() {
        
        int[][]                     tempSource = {{1, 2, 3},
                                                  {4, 5, 6},
                                                  {7, 8, 9}
                                                 };
        int[][]                     tempResult;
        ArrayAndStringChallenges    tempChallenge;

        
        tempChallenge = new ArrayAndStringChallenges();
        
        System.out.println("Source Matrix:");
        this.dumpArray(tempSource);
        
        tempResult = tempChallenge.rotateMatrixCounterClockwise(tempSource);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);
        
        
    }
    
    
    /**
     * Dump array
     * @param anArray int[][]
     */
    protected void dumpArray(int[][] anArray) {
        
        for (int i = 0; i < anArray.length; i++) {
            
            for (int j = 0; j < anArray[i].length; j++) {
                
                System.out.print(anArray[i][j] + " ");
            }
            
            System.out.println();
        }
        
        System.out.println();
        
    }
    
    /**
     * String rotation test
     * 
     */
    @Test
    public void stringRotationTest() {
        
        String                      tempInput;
        String                      tempResult;
        ArrayAndStringChallenges    tempChallenge;

        
        tempChallenge = new ArrayAndStringChallenges();        
        tempInput = "waterbottle";
        tempResult = tempChallenge.rotateStringAround(tempInput, 3);
        System.out.println("Value: " + tempInput + " rotated at: " + 3 + " = " + tempResult);
        assertTrue("Test 1 rotate failed", tempResult.equals("erbottlewat"));
        
        tempInput = "waterbottle";
        tempResult = tempChallenge.rotateStringAround(tempInput, 6);
        System.out.println("Value: " + tempInput + " rotated at: " + 6 + " = " + tempResult);
        assertTrue("Test 2 rotate failed", tempResult.equals("ottlewaterb"));
        
        
    }
    
    /**
     * isSubstring test
     */
    @Test
    public void performIsSubstringTest() {

        String                      tempSource;
        String                      tempTarget;
        ArrayAndStringChallenges    tempChallenge;
        boolean                     tempResult;

        
        tempChallenge = new ArrayAndStringChallenges();        
        tempSource = "waterbottle";
        tempTarget = "erbottlewat";
        tempResult = tempChallenge.isSubstring(tempSource, tempTarget, 0);
        System.out.println("Value: " + tempSource + " possible roation: " + tempTarget + " isRotatedSubstring: " + tempResult);
        assertTrue("Test 1 isSubstring failed", tempResult);
        
        tempSource = "waterbottle";
        tempTarget = "erbottlewax";
        tempResult = tempChallenge.isSubstring(tempSource, tempTarget, 0);
        System.out.println("Value: " + tempSource + " possible roation: " + tempTarget + " isRotatedSubstring: " + tempResult);
        assertTrue("Test 2 isSubstring failed", !tempResult);
        
        tempSource = "waterbottle";
        tempTarget = "erbottlew";
        tempResult = tempChallenge.isSubstring(tempSource, tempTarget, 0);
        System.out.println("Value: " + tempSource + " possible roation: " + tempTarget + " isRotatedSubstring: " + tempResult);
        assertTrue("Test 3 isSubstring failed", !tempResult);
        
        tempSource = "waterbottle";
        tempTarget = "aterbottlew";
        tempResult = tempChallenge.isSubstring(tempSource, tempTarget, 0);
        System.out.println("Value: " + tempSource + " possible roation: " + tempTarget + " isRotatedSubstring: " + tempResult);
        assertTrue("Test 4 isSubstring failed", tempResult);
        
        tempSource = "waterbottle";
        tempTarget = "ewaterbottl";
        tempResult = tempChallenge.isSubstring(tempSource, tempTarget, 0);
        System.out.println("Value: " + tempSource + " possible roation: " + tempTarget + " isRotatedSubstring: " + tempResult);
        assertTrue("Test 5 isSubstring failed", tempResult);
        
        
        
    }
    
    /**
     * Perform zero matrix test
     */
    @Test
    public void performZeroMatrixTestFiveByFive1() {
        
        int[][]                     tempSource = {{1, 2, 3, 4, 5},
                                                  {6, 7, 8, 9, 10},
                                                  {11,12,0,14,15},
                                                  {16,17,18,19,20},
                                                  {21,22,23,24,25}
                                                  };
        int[][]                     tempResult;
        ArrayAndStringChallenges    tempChallenge;

        
        tempChallenge = new ArrayAndStringChallenges();
        
        System.out.println("Source Matrix:");
        this.dumpArray(tempSource);
        
        tempResult = tempChallenge.zeroRowAndColumnForZeroEntriesIn(tempSource);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);
        
    }
    
    /**
     * Perform zero matrix test
     */
    @Test
    public void performZeroMatrixTestFiveByFive2() {
        
        int[][]                     tempSource = {{1, 2, 3, 4, 5},
                                                  {6, 7, 8, 9, 10},
                                                  {11,12,13,14,15},
                                                  {16,17,18,19,20},
                                                  {21,22,23,24,25}
                                                  };
        int[][]                     tempResult;
        ArrayAndStringChallenges    tempChallenge;

        
        tempChallenge = new ArrayAndStringChallenges();
        
        System.out.println("Source Matrix:");
        this.dumpArray(tempSource);
        
        tempResult = tempChallenge.zeroRowAndColumnForZeroEntriesIn(tempSource);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);
        
    }
    
    /**
     * Perform zero matrix test
     */
    @Test
    public void performZeroMatrixTestFiveByFive3() {
        
        int[][]                     tempSource = {{1, 2, 3, 4, 5},
                                                  {6, 7, 8, 9, 10},
                                                  {11,12,13,14,15},
                                                  {16,17,18,19,20},
                                                  {21,22,23,24,0}
                                                  };
        int[][]                     tempResult;
        ArrayAndStringChallenges    tempChallenge;

        
        tempChallenge = new ArrayAndStringChallenges();
        
        System.out.println("Source Matrix:");
        this.dumpArray(tempSource);
        
        tempResult = tempChallenge.zeroRowAndColumnForZeroEntriesIn(tempSource);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);
        
    }
    
    
    
    /**
     * Perform zero matrix test
     */
    @Test
    public void performZeroMatrixTestFiveByFive4() {
        
        int[][]                     tempSource = {{0, 2, 3, 4, 5},
                                                  {6, 7, 8, 9, 10},
                                                  {11,12,13,14,15},
                                                  {16,17,18,19,20},
                                                  {21,22,23,24,0}
                                                  };
        int[][]                     tempResult;
        ArrayAndStringChallenges    tempChallenge;

        
        tempChallenge = new ArrayAndStringChallenges();
        
        System.out.println("Source Matrix:");
        this.dumpArray(tempSource);
        
        tempResult = tempChallenge.zeroRowAndColumnForZeroEntriesIn(tempSource);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);
        
    }
    
    /**
     * Perform transpose test
     */
    @Test
    public void performTransposeTestSquare() {
        
        int[][]                     tempSource = {{1, 2, 3, 4, 5},
                                                  {6, 7, 8, 9, 10},
                                                  {11,12,0,14,15},
                                                  {16,17,18,19,20},
                                                  {21,22,23,24,25}
                                                  };
        ArrayAndStringChallenges    tempChallenge;
        int[][]                     tempResult;

        
        tempChallenge = new ArrayAndStringChallenges();
        
        System.out.println("Source Matrix:");
        this.dumpArray(tempSource);
        
        tempResult = tempChallenge.transpose(tempSource);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);
        
    }
    
    /**
     * Perform transpose test
     */
    @Test
    public void performTransposeTestNonSquare() {
        
        int[][]                     tempSource = {{1, 2, 3, 4, 5},
                                                  {6, 7, 8, 9, 10},
                                                  {11,12,0,14,15},
                                                  {16,17,18,19,20}
                                                  };
        ArrayAndStringChallenges    tempChallenge;
        int[][]                     tempResult;

        
        tempChallenge = new ArrayAndStringChallenges();
        
        System.out.println("Source Matrix:");
        this.dumpArray(tempSource);
        
        tempResult = tempChallenge.transpose(tempSource);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);
        
    }
    
    
    /**
     * Perform multiply test
     */
    @Test
    public void performIdentityMatrixMultiplyTest() {
        
        int[][]                     tempMatrix1 = {{1,0,0,0},
                                                   {0,1,0,0},
                                                   {0,0,1,0},
                                                   {0,0,0,1}
                                                    };
        
        int[][]                     tempMatrix2 = {{1, 2, 3, 4, 5},
                                                  {6, 7, 8, 9, 10},
                                                  {11,12,0,14,15},
                                                  {16,17,18,19,20}
                                                  };
        ArrayAndStringChallenges    tempChallenge;
        int[][]                     tempResult;

        
        tempChallenge = new ArrayAndStringChallenges();
        
        System.out.println("Matrix1:");
        this.dumpArray(tempMatrix1);
        
        System.out.println("Matrix2:");
        this.dumpArray(tempMatrix2);
        
        tempResult = tempChallenge.multiply(tempMatrix1, tempMatrix2);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);
        
    }
    
    /**
     * Perform multiply test
     */
    @Test
    public void performTwoMatrixMultiplyTest() {
        
        int[][]                     tempMatrix1 = {{1,1,1,1},
                                                   {1,1,1,1},
                                                   {1,1,1,1},
                                                   {1,1,1,1}
                                                    };
        
        int[][]                     tempMatrix2 = {{1,2,3,4,5},
                                                   {1,2,3,4,5},
                                                   {1,2,3,4,5},
                                                   {1,2,3,4,5}
                                                  };
        ArrayAndStringChallenges    tempChallenge;
        int[][]                     tempResult;

        
        tempChallenge = new ArrayAndStringChallenges();
        
        System.out.println("Matrix1:");
        this.dumpArray(tempMatrix1);
        
        System.out.println("Matrix2:");
        this.dumpArray(tempMatrix2);
        
        tempResult = tempChallenge.multiply(tempMatrix1, tempMatrix2);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);
        
    }
    
    /**
     * Perform multiply test
     */
    @Test
    public void performZeroMatrixMultiplyTest() {

        int[][] tempMatrix1 = {{0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] tempMatrix2 = {{1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 0, 14, 15},
                {16, 17, 18, 19, 20}
        };
        ArrayAndStringChallenges tempChallenge;
        int[][] tempResult;


        tempChallenge = new ArrayAndStringChallenges();

        System.out.println("Matrix1:");
        this.dumpArray(tempMatrix1);

        System.out.println("Matrix2:");
        this.dumpArray(tempMatrix2);

        tempResult = tempChallenge.multiply(tempMatrix1, tempMatrix2);

        System.out.println("Resulting Matrix:");
        this.dumpArray(tempResult);

    }

    
}
