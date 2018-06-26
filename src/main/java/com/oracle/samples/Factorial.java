package com.oracle.samples;

/**
 *
 *
 */
public class Factorial {

    /**
     * Answer an instance for the following arguments
     */
    public Factorial() {
        
        super();

    }
    
    /**
     * Compute
     * @return int
     */
    public int compute(int aValue) {
        
        int tempResult;
        
        if (aValue == 0) {
            
            tempResult = 1;
        }
        else {
            
            tempResult = aValue * this.compute(aValue - 1);
        }
        
        return tempResult;
        
    }


    
}
