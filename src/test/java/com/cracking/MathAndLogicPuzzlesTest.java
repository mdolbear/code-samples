package com.cracking;


import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 *
 */
public class MathAndLogicPuzzlesTest {

    /**
     * Answer an instance for the following arguments
     */
    public MathAndLogicPuzzlesTest() {
        super();
    }
    
    /**
     * Test the sieve Of Eratosthenes
     */
    @Test
    public void sieveOfEratosthenesTest() {
        
        MathAndLogicPuzzles tempPuzzles = new MathAndLogicPuzzles();
        boolean[]           tempResult;
        int                 tempLimit;
        
        //Test up to 100
        tempLimit = 100;
        tempResult = tempPuzzles.sieveOfEratosthenes(tempLimit);
        this.dumpPrimeNumbers(tempLimit, tempResult);
        
        //Test up to 10000
        tempLimit = 10000;
        tempResult = tempPuzzles.sieveOfEratosthenes(10000);
        this.dumpPrimeNumbers(tempLimit, tempResult);
        
        
    }
    
    /**
     * Dump prime numbers
     * @param aMax int
     * @param aNumbers boolean[]
     */
    protected void dumpPrimeNumbers(int aMax,
                                    boolean[] aNumbers) {
        
        System.out.println("Prime Numbers Up To " + aMax + " Found:");
        
        for (int i = 0; i < aNumbers.length; i++) {
            
            if (aNumbers[i]) {
                
                System.out.print(" " + i);
                
                if (i < aNumbers.length - 1) {
                    
                    System.out.print(", ");
                }
                
            }
            
        }
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------");
        
    }
    
    /**
     * Find greatest common denominator
     */
    @Test
    public void greatestCommonDenominatorTest() {
        
        MathAndLogicPuzzles tempPuzzles = new MathAndLogicPuzzles();
        
        int                 tempValue1;
        int                 tempValue2;
        int                 tempResult;
        
        //Test 1
        tempValue1 = 30;
        tempValue2 = 21;
        tempResult = tempPuzzles.computeGreatestCommonDenominatorEuclid(tempValue1, tempValue2);
        assertTrue("Test1 failed", tempResult == 3);
        System.out.println("The greatest common denominator of " 
                            + tempValue1 
                            + " and " + tempValue2 
                            + " = " + tempResult);
        
        //Test 2
        tempValue1 = 99;
        tempValue2 = 21;
        tempResult = tempPuzzles.computeGreatestCommonDenominatorEuclid(tempValue1, tempValue2);
        assertTrue("Test2 failed", tempResult == 3);
        System.out.println("The greatest common denominator of " 
                            + tempValue1 
                            + " and " + tempValue2 
                            + " = " + tempResult);
        
        //Test 3
        tempValue1 = 77;
        tempValue2 = 21;
        tempResult = tempPuzzles.computeGreatestCommonDenominatorEuclid(tempValue1, tempValue2);
        assertTrue("Test3 failed", tempResult == 7);
        System.out.println("The greatest common denominator of " 
                            + tempValue1 
                            + " and " + tempValue2 
                            + " = " + tempResult);
        
        //Test 4
        tempValue1 = 77;
        tempValue2 = 154;
        tempResult = tempPuzzles.computeGreatestCommonDenominatorEuclid(tempValue1, tempValue2);
        assertTrue("Test4 failed", tempResult == 77);
        System.out.println("The greatest common denominator of " 
                            + tempValue1 
                            + " and " + tempValue2 
                            + " = " + tempResult);
        
        //Test 5
        tempValue1 = 250;
        tempValue2 = 71;
        tempResult = tempPuzzles.computeGreatestCommonDenominatorEuclid(tempValue1, tempValue2);
        assertTrue("Test5 failed", tempResult == 1);
        System.out.println("The greatest common denominator of " 
                            + tempValue1 
                            + " and " + tempValue2 
                            + " = " + tempResult);
        
        //Test 6
        tempValue1 = 213;
        tempValue2 = 71;
        tempResult = tempPuzzles.computeGreatestCommonDenominatorEuclid(tempValue1, tempValue2);
        assertTrue("Test6 failed", tempResult == 71);
        System.out.println("The greatest common denominator of " 
                            + tempValue1 
                            + " and " + tempValue2 
                            + " = " + tempResult);
        
        
    }

}
