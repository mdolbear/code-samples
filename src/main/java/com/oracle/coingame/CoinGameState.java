package com.oracle.coingame;

/**
 *
 *
 */
public class CoinGameState {

    private boolean gameOver;
    private Player winner;
    private Player loser;
    private Coin chosenCoin;
    
    /**
     * Answer an instance for the following arguments
     * @param isGameOver boolean
     */
    public CoinGameState(boolean isGameOver) {
       super();
       this.setGameOver(isGameOver);
    }
    
    /**
     * Answer whether or not my game is over
     * @param isGameOver boolean
     * @param aWinner Player
     * @param aLoser Player
     */
    public CoinGameState(boolean isGameOver,
                        Player aWinner,
                        Player aLoser) {
        
        this(isGameOver);
        this.setWinner(aWinner);
        this.setLoser(aLoser);
        
    }

    /**
     * Set my gameOver
     * @param gameOver boolean
     */
    protected void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Set my winner
     * @param winner Player
     */
    protected void setWinner(Player winner) {
        this.winner = winner;
    }

    /**
     * Set my loser
     * @param loser Player
     */
    protected void setLoser(Player loser) {
        this.loser = loser;
    }

    /**
     * Answer my gameOver
     * @return boolean
     */
    public boolean isGameOver() {
        return gameOver;
    }

    
    /**
     * Answer my winner's total score
     * @return int
     */
    public int getWinnerTotalScore() {
        
        int tempResult = 0;
        
        if (this.isGameOver() && this.getWinner() != null) {
            
            tempResult = this.getWinner().getCurrentValueOfCoins();
        }
        
        return tempResult;
        
    }
    
    
    /**
     * Answer my winner
     * @return Player
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Answer my loser's total score
     * @return int
     */
    public int getLoserTotalScore() {
        
        int tempResult = 0;
        
        if (this.isGameOver() && 
                this.getLoser() != null) {
            
            tempResult = this.getLoser().getCurrentValueOfCoins();
        }
        
        return tempResult;
        
    }
    
    
    /**
     * Answer my loser
     * @return Player
     */
    public Player getLoser() {
        return loser;
    }

    /**
     * Answer my chosenCoin
     * @return Coin
     */
    public Coin getChosenCoin() {
        return chosenCoin;
    }

    /**
     * Set my chosenCoin
     * @param chosenCoin Coin
     */
    public void setChosenCoin(Coin chosenCoin) {
        this.chosenCoin = chosenCoin;
    }
    
    

}
