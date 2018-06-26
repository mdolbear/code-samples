package com.oracle.coingame;

/**
 *
 *
 */
public abstract class CoinChoiceStrategy {

    /**
     * Answer an instance for the following arguments
     */
    public CoinChoiceStrategy() {
       super();
    }

    /**
     * Choose a coin. Subclasses must override this method to provide a method
     * of coin choice.
     * @param aPlayer Player
     * @param aGame CoinGame
     */
    protected abstract Coin chooseCoin(Player aPlayer,
                                       CoinGame aGame);
    
    
    /**
     * Validate remaining coins
     * @param aGame CoinGame
     */
    protected void validateRemainingCoins(CoinGame aGame) {
        
        if (aGame == null || !aGame.areThereMoreCoins()) {
            
            throw new IllegalStateException("No more coins to choose");
        }
        
    }
}
