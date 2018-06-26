package com.oracle.samples;

import org.junit.Test;

/**
 *
 *
 */
public class MagicSquareTest {

    /**
     * Answer an instance for the following arguments
     */
    public MagicSquareTest() {
        super();
    }
    
    /**
     * Magic square creation test
     * 
     */
    @Test
    public void testMagicSquareCreation() {
        
        MagicSquare tempSquare = new MagicSquare(5);
        
        tempSquare.create();
        tempSquare.dumpSquare();
        
    }

}
