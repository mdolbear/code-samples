package com.oracle.utils;

/**
 *
 *
 */
public class NumberUtilities {

    
    private static NumberUtilities instance;
    
    /**
     * Answer my singleton instance
     * @return NumberUtilities
     */
    public synchronized static NumberUtilities getInstance() {
        
        if (instance == null) {
            
            instance = new NumberUtilities();
            
        }
        
        return instance;
        
    }
    
    
    /**
     * Answer an instance for the following arguments
     */
    public NumberUtilities() {
        super();
    }
    
    /**
     * Validate is prime number
     * @param aNumber int
     * @return boolean
     */
    public boolean isPrime(int aCandidatePrime) {

        boolean tempPrime;
        int     i = 2;

        tempPrime = (aCandidatePrime != 0) && (aCandidatePrime != 1);
        while (i < aCandidatePrime && tempPrime) {

            tempPrime = (aCandidatePrime % i) != 0;
            i++;
        }

        return tempPrime;
    
    }

}
