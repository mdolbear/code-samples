package com.oracle.coinproblem;

/**
 *
 *
 */
public class Coin {

    private int denomination;
    
    /**
     * Answer an instance for the following arguments
     */
    public Coin(int aDenomination) {
        
        super();
        this.setDenomination(aDenomination);
        
    }

    /**
     * Answer my denomination
     * @return int
     */
    protected int getDenomination() {
        return denomination;
    }

    /**
     * Set my denomination
     * @param denomination int
     */
    protected void setDenomination(int denomination) {
        this.denomination = denomination;
    }
    
    
    /**
     * Calculate change for anAmount. Answer a Change
     * @param anAmount int
     * @return Change
     */
    public Change calculateChange(int anAmount) {
        
        int     tempNumberOfCoins;
        int     tempRemainder;
        
        tempNumberOfCoins = anAmount / this.getDenomination();
        tempRemainder = anAmount % this.getDenomination();
        
        
        return new Change(this,
                          tempNumberOfCoins,
                          tempRemainder);
        
    }
    
    /**
     * Answer whether or not I am equal to anotherCoin
     * @param anotherCoin Coin
     * @return boolean
     */
    @Override
    public boolean equals(Object anotherCoin) {
        
        boolean tempResult = false;
        Coin    tempAnotherCoin;
        
        if (anotherCoin instanceof Coin) {
            
            tempAnotherCoin = (Coin)anotherCoin;
            
            tempResult = this.getDenomination() == tempAnotherCoin.getDenomination();
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer my hashcode value
     */
    @Override
    public int hashCode() {
        
        return this.getClass().hashCode() + this.getDenomination();
        
    }
    
    /**
     * Answer my string representation
     * @return String
     */
    @Override
    public String toString() {
        
        StringBuilder   tempResult = new StringBuilder();
        
        tempResult.append("Coin-denomination: ");
        tempResult.append(this.getDenomination());
        
        return tempResult.toString();
    }

}
