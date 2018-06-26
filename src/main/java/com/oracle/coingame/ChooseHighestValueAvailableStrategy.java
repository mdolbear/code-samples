package com.oracle.coingame;

/**
 *
 *
 */
public class ChooseHighestValueAvailableStrategy extends CoinChoiceStrategy {

    /**
     * Answer an instance for the following arguments
     */
    public ChooseHighestValueAvailableStrategy() {
        
       super();
    }

    /* (non-Javadoc)
     * @see com.oracle.coingame.CoinChoiceStrategy#chooseCoin(com.oracle.coingame.Player, com.oracle.coingame.CoinGame)
     */
    @Override
    protected Coin chooseCoin(Player aPlayer, 
                              CoinGame aGame) {
        
        Coin    tempResult = null;
        int     tempCoinValue;
        Coin    tempFrontCoin;
        Coin    tempRearCoin;
        
        this.validateRemainingCoins(aGame);
        tempFrontCoin = aGame.getFrontCoin();
        tempRearCoin = aGame.getRearCoin();
        
        if (tempFrontCoin == null) {
            
            tempResult = tempRearCoin;
            aGame.removeRearCoin();
        }
        else if (tempRearCoin == null) {
            
            tempResult = tempFrontCoin;
            aGame.removeFrontCoin();
        }
        else {
            
            tempCoinValue = Math.max(tempFrontCoin.getDenomination(), tempRearCoin.getDenomination());
            if (tempCoinValue == tempFrontCoin.getDenomination()) {
                
                tempResult = tempFrontCoin;
                aGame.removeFrontCoin();
            }
            else {
                
                tempResult = tempRearCoin;
                aGame.removeRearCoin();
                
            }
        }
        
        
        return tempResult;
    }
    

}
