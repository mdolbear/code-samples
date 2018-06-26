package com.oracle.samples;

/**
 *
 *
 */
public class FibonacciSeries {

    /**
     * Answer an instance for the following arguments
     */
    public FibonacciSeries() {
       super();
    }

    
    /**
     * Compute
     * @param anIndex int
     */
    public int compute(int anIndex) {
        
        int tempResult;
        this.validateInput(anIndex);
        
        if (anIndex == 0 ) {
            
            tempResult = 0;
        }
        else if (anIndex == 1) {
            
            tempResult = 1;
        }
        else {
            
            tempResult = this.compute(anIndex - 1) + this.compute(anIndex - 2);
        }
        
        return tempResult;
        
    }
    
    /**
     * Compute iteratively
     * @param anIndex int
     */
    public int computeIteratively(int anIndex) {
        
        int tempResult = 0;
        int tempPreviousnm1 = 1;
        int tempPreviousnm2 = 0;
        
        this.validateInput(anIndex);
        
        if (anIndex >= 1) {
            
            tempResult = 1;
            
            for (int i = 1; i < anIndex; i++) {
                
                //Compute current result
                tempResult = tempPreviousnm1 + tempPreviousnm2;
                
                //Update previous results
                tempPreviousnm2 = tempPreviousnm1;
                tempPreviousnm1 = tempResult;
                
            }
            
        }
        
        return tempResult;
        
    }


    /**
     * @param anIndex
     */
    protected void validateInput(int anIndex) {
        if (anIndex < 0) {
            
            throw new IllegalArgumentException("Input must be >= 0");
        }
    }
     
    
}
