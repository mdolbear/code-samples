package com.oracle.samples;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 *
 */
public class CombinationTest {

    /**
     * Answer an instance for the following arguments
     */
    public CombinationTest() {
        super();
    }
    
    /**
     * Test combination
     */
    @Test
    public void mainTestFactorial() {
        
        Factorial   tempFactorial;
        
        tempFactorial = new Factorial();
        
        assertTrue("Factorial of 1 failed",tempFactorial.compute(0) == 1);
        assertTrue("Factorial of 5 failed",tempFactorial.compute(5) == 120);
    }
    
    /**
     * Main combination test
     */
    @Test
    public void mainCombinationTest() {
        
        Combination tempCombination;
        
        tempCombination = new Combination(6, 2);
        
        assertTrue("Conbination 6 choose 2 failed", tempCombination.computeResult() == 15);
    }

}
