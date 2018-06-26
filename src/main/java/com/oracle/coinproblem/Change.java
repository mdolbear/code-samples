package com.oracle.coinproblem;

/**
 *
 *
 */
public class Change {

    private Coin coin;
    private int numberOfCoins;
    private int remainingAmount;
    
    /**
     * Answer an instance for the following arguments
     */
    public Change(Coin aCoin,
                  int aNumberOfCoins,
                  int aRemainingAmount) {
        
        super();
        this.setCoin(aCoin);
        this.setNumberOfCoins(aNumberOfCoins);
        this.setRemainingAmount(aRemainingAmount);
        
    }

    /**
     * Answer my coin
     * @return Coin
     */
    public Coin getCoin() {
        return coin;
    }

    /**
     * Set my coin
     * @param coin Coin
     */
    protected void setCoin(Coin coin) {
        this.coin = coin;
    }

    /**
     * Answer my numberOfCoins
     * @return int
     */
    public int getNumberOfCoins() {
        return numberOfCoins;
    }

    /**
     * Set my numberOfCoins
     * @param numberOfCoins int
     */
    protected void setNumberOfCoins(int numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
    }

    /**
     * Answer my remainingAmount
     * @return int
     */
    public int getRemainingAmount() {
        return remainingAmount;
    }

    /**
     * Set my remainingAmount
     * @param remainingAmount int
     */
    protected void setRemainingAmount(int remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
    
    

}
