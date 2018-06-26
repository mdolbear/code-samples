package com.oracle.coingame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class Player {

    private String name;
    private List<Coin> currentCoins;
    
    /**
     * Answer an instance for the following arguments
     */
    public Player(String aName) {
        
       super();
       this.setName(aName);
       this.setCurrentCoins(new ArrayList<Coin>());
       
    }

    /**
     * Answer my name
     * @return String
     */
    protected String getName() {
        return name;
    }

    /**
     * Set my name
     * @param name String
     */
    protected void setName(String name) {
        this.name = name;
    }

    
    /**
     * Answer the current value of coins
     * @return int
     */
    public int getCurrentValueOfCoins() {
        
        int tempResult = 0;
        
        for (int i = 0; i < this.getCurrentCoins().size(); i++) {
            
            tempResult += this.getCurrentCoins().get(i).getDenomination();
        }
        
       return tempResult;
       
    }
    
    
    /**
     * Add a coin to my current coins
     * @param aCoin Coin
     */
    public void addCoin(Coin aCoin) {
        
        this.getCurrentCoins().add(aCoin);
    }
    
    
    /**
     * Answer my currentCoins
     * @return List<Coin>
     */
    protected List<Coin> getCurrentCoins() {
        return currentCoins;
    }

    /**
     * Set my currentCoins
     * @param currentCoins List<Coin>
     */
    protected void setCurrentCoins(List<Coin> currentCoins) {
        this.currentCoins = currentCoins;
    }


    

}
