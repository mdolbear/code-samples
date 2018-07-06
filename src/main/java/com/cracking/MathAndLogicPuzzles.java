package com.cracking;

/**
 *
 *
 */
public class MathAndLogicPuzzles {
    
    
    /**
     * Answer an instance for the following arguments
     */
    public MathAndLogicPuzzles() {
        
       super();
    }

    
    
    /**
     * Sieve of Eratosthenes
     * @param aMax int
     */
    public boolean[] sieveOfEratosthenes(int aMax) {
        
        boolean[]   tempResult;
        int         tempPrime = 2;
        
        tempResult = new boolean[aMax+1];
        this.initializeFlags(tempResult);
        
        while (tempPrime <= Math.sqrt(aMax)) {
            
            this.eliminateMultiplesOfPrime(tempResult, tempPrime);           
            tempPrime = this.getNextPrime(tempResult, tempPrime);
            
        }
        return tempResult;
        
    }
    
    /**
     * Get next prime number
     * @param aFlags
     * @param aPrime
     * @return
     */
    protected int getNextPrime(boolean[] aFlags, 
                               int aPrime) {
        
        int tempNext = aPrime + 1;
        while ((tempNext < aFlags.length) 
                && !aFlags[tempNext]) {
            
            tempNext++;
        }
        
        return tempNext;
    }


    /**
     * Cross of multiples of tempPrime
     * @param aFlags
     * @param aPrime
     */
    protected void eliminateMultiplesOfPrime(boolean[] aFlags, 
                                             int aPrime) {
       
        for (int i = (int)Math.pow(aPrime, 2.0); i < aFlags.length; i+=aPrime) {
            
            aFlags[i] = false;
        }
        
    }


    /**
     * Initialize flags
     * @param aFlags boolean[]
     */
    protected void initializeFlags(boolean[] aFlags) {
        
        for (int i = 2; i < aFlags.length; i++) {
            
            aFlags[i] = true;
        }
        
    }
    
}
