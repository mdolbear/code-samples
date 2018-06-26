package com.oracle.coinproblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 */
public class CoinChanger {

    private List<Coin> coins;
    private Map<Coin, Integer> coinCounts;
    
    
    /**
     * Answer an instance for the following arguments
     */
    public CoinChanger() {
        
        super();
        this.setCoins(new ArrayList<Coin>());
        this.clearCoinCounts();
        this.initializeMyCoins();
    }

    /**
     * Clear coin counts
     */
    protected void clearCoinCounts() {
        
        this.setCoinCounts(new HashMap<Coin,Integer>());
        
    }
    
    /**
     * Make change
     * @param anInitialAmount int
     * @return Map
     */
    public Map<Coin, Integer> makeChange(int anInitialAmount) {
        
        int                tempCurrentAmount;
        Coin               tempCurrentCoin;
        int                tempNumberOfCoins;
        int                i = 0;
        
        tempCurrentAmount = anInitialAmount;
        tempNumberOfCoins = this.getCoins().size();
        this.clearCoinCounts();
        
        while (tempCurrentAmount > 0 && i < tempNumberOfCoins) {
            
            tempCurrentCoin = this.getCoins().get(i++);
            tempCurrentAmount = this.evaluateCoin(tempCurrentAmount,
                                                  tempCurrentCoin);
            
        }
        
        return new HashMap<Coin, Integer>(this.getCoinCounts());
        
    }

    /**
     * Evaluate whether aCurrentCoin can be obtained from aCurrentAmount.
     * Answer the remainder
     * @param aCurrentAmount int
     * @param aCurrentCoin Coin
     * @return int
     */
    public int evaluateCoin(int aCurrentAmount, Coin aCurrentCoin) {
        
        Change tempChange;
        
        tempChange = aCurrentCoin.calculateChange(aCurrentAmount);
        if (tempChange.getNumberOfCoins() > 0) {
            
            this.getCoinCounts().put(tempChange.getCoin(), tempChange.getNumberOfCoins());
            aCurrentAmount = tempChange.getRemainingAmount();
        }
        
        return aCurrentAmount;
        
    }
    
    
    /**
     * Initialize my coins
     */
    protected void initializeMyCoins() {
        
        Coin    tempCoin;
        
        tempCoin = new Coin(1);
        this.addCoin(tempCoin);
        
        tempCoin = new Coin(5);
        this.addCoin(tempCoin);
        
        tempCoin = new Coin(10);
        this.addCoin(tempCoin);
        
        tempCoin = new Coin(25);
        this.addCoin(tempCoin);
        
        tempCoin = new Coin(50);
        this.addCoin(tempCoin);
        
        Collections.sort(this.getCoins(), this.getComparator());
        
    }


    
    /**
     * Add a coin to me
     * @param aCoin Coin
     */
    public void addCoin(Coin aCoin) {
        
       this.getCoins().add(aCoin);
       
    }
    
    
    /**
     * Answer my coins
     * @return List<Coin>
     */
    protected List<Coin> getCoins() {
        return coins;
    }


    /**
     * Set my coins
     * @param coins List<Coin>
     */
    protected void setCoins(List<Coin> coins) {
        this.coins = coins;
    }


    /**
     * Answer my coinCounts
     * @return Map<Coin,Integer>
     */
    protected Map<Coin, Integer> getCoinCounts() {
        return coinCounts;
    }


    /**
     * Set my coinCounts
     * @param coinCounts Map<Coin,Integer>
     */
    protected void setCoinCounts(Map<Coin, Integer> coinCounts) {
        this.coinCounts = coinCounts;
    }
    
    
    /**
     * Answer my coin denomination comparator
     * @return Comparator<Coin>
     */
    protected Comparator<Coin> getComparator() {
        
        return (Coin o1, Coin o2) -> (o2.getDenomination() - o1.getDenomination());
    }

}
