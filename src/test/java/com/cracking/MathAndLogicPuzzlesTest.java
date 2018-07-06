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

}
