package com.oracle.samples;

/**
 *
 *
 */
public class Combination {

    private int n;
    private int m;
    
    /**
     * Answer an instance for the following arguments
     */
    public Combination(int aMainValue, int aChooseValue) {
        
        super();
        this.setN(aMainValue);
        this.setM(aChooseValue);
    }
    
    /**
     * Compute result
     * @return int
     */
    public int computeResult() {
        
        int         tempResult;
        Factorial   tempFactorial = new Factorial();
        
        
        tempResult = tempFactorial.compute(this.getN())/
                        (tempFactorial.compute(this.getM()) * tempFactorial.compute(this.getN() - this.getM()));
        
        return tempResult;
    }

    /**
     * Answer my n
     * @return int
     */
    protected int getN() {
        return n;
    }

    /**
     * Set my n
     * @param n int
     */
    protected void setN(int n) {
        this.n = n;
    }

    /**
     * Answer my m
     * @return int
     */
    protected int getM() {
        return m;
    }

    /**
     * Set my m
     * @param m int
     */
    protected void setM(int m) {
        this.m = m;
    }

    
    
}
