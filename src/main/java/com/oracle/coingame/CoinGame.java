package com.oracle.coingame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 */
public class CoinGame {

    private LinkedList<Coin> coins;
    private List<Player> players;
    
    
    /**
     * Answer an instance for the following arguments
     */
    public CoinGame() {
        
        super();
        this.initializeGame();
        
    }


    /**
     * Initialize game
     */
    protected void initializeGame() {
        
        this.setCoins(new LinkedList<Coin>());
        this.setPlayers(new ArrayList<Player>());
        
    }
    
    /**
     * Reinitialize game
     */
    public void reinitializeGame() {
        
        this.initializeGame();
        this.createInitialPlayers();
        
    }
    
    /**
     * Create initial players
     */
    protected void createInitialPlayers() {
        
        Player  tempPlayer;
        
        tempPlayer = new Player("Player 1");
        this.addPlayer(tempPlayer);
        
        tempPlayer = new Player("Player 2");
        this.addPlayer(tempPlayer);
        
    }


    /**
     * Add a coin to me
     * @param aCoin Coin
     */
    public void addCoin(Coin aCoin) {
        
        this.getCoins().add(aCoin);
        
    }
    
    /**
     * Choose a coin. Subclasses must override this method to provide a method
     * of coin choice.
     * @param aPlayer Player
     * @return CoinGameState
     */
    public CoinGameState chooseCoinMaxFirst(Player aPlayer) {
        
        CoinGameState                       tempResult;
        CoinChoiceStrategy                  tempStrat;
        Coin                                tempChoice;
        
        tempStrat = new ChooseHighestValueAvailableStrategy();
        tempChoice = tempStrat.chooseCoin(aPlayer, this);
        if (tempChoice != null) {
            
            aPlayer.addCoin(tempChoice);
        }
        
        tempResult = this.isCurrentGameOver(tempChoice);
        
        return tempResult;
        
    }
    
    /**
     * Evaluate current game state. Answer true if game is over, false if it
     * can continue
     * @param aCoin Coin
     * @return CoinGameState
     */
    protected CoinGameState isCurrentGameOver(Coin aCoin) {
        
        CoinGameState tempState;
        
        
        if (this.getCoins().isEmpty()) {
            
            tempState = this.choseWinnerAndLoserBasedOnCurrentCoinValue();
            
        }
        else {
            
            tempState = new CoinGameState(false);
        }
        
        tempState.setChosenCoin(aCoin);
        
        return tempState;
        
    }


    /**
     * Choose winner and loser based on current coin value
     * @return CoinGameState
     */
    protected CoinGameState choseWinnerAndLoserBasedOnCurrentCoinValue() {
        
        CoinGameState tempState;
        int           tempPlayer1Value = 0;
        int           tempPlayer2Value = 0;
        
        tempPlayer1Value = this.getPlayerCoinValue(this.getPlayers(), 0);           
        tempPlayer2Value = this.getPlayerCoinValue(this.getPlayers(), 1);
        if (tempPlayer1Value > tempPlayer2Value) {
            
            tempState = new CoinGameState(true, 
                                          this.getPlayers().get(0), 
                                          this.getPlayers().get(1));
        }
        else {
            
            tempState = new CoinGameState(true, 
                                          this.getPlayers().get(1), 
                                          this.getPlayers().get(0));
        }
        
        return tempState;
        
    }


    /**
     * Answer my player coin value
     * @param aPlayers List<Player>
     * @return int
     */
    protected int getPlayerCoinValue(List<Player> aPlayers,
                                    int anIndex) {
        
        int tempPlayerValue = 0;
        
        if (aPlayers.size() > anIndex) {
            
            tempPlayerValue = aPlayers.get(anIndex).getCurrentValueOfCoins();
        }
        
        return tempPlayerValue;
        
    }
    
    
    /**
     * Are there more coins
     * @return boolean
     */
    protected boolean areThereMoreCoins() {
        
        return !this.getCoins().isEmpty();
    }
    
    /**
     * Peak at front
     * @return Coin
     */
    protected Coin getFrontCoin() {
        
        Coin    tempResult = null;
        
        if (this.areThereMoreCoins()) {
            
            tempResult = this.getCoins().peekFirst();
        }
        
        return tempResult;
    }
    
    /**
     * Peak at rear
     * @return Coin
     */
    protected Coin getRearCoin() {
        
        Coin    tempResult = null;
        
        if (this.areThereMoreCoins()) {
            
            tempResult = this.getCoins().peekLast();
        }
        
        return tempResult;
    }
    
    
    /**
     * Remove front
     * @return Coin
     */
    protected Coin removeFrontCoin() {
        
        Coin    tempResult = null;
        
        if (this.areThereMoreCoins()) {
            
            tempResult = this.getCoins().removeFirst();
        }
        
        return tempResult;
    }
    
    /**
     * Remove rear
     * @return Coin
     */
    protected Coin removeRearCoin() {
        
        Coin    tempResult = null;
        
        if (this.areThereMoreCoins()) {
            
            tempResult = this.getCoins().removeLast();
        }
        
        return tempResult;
    }
    
    
    
    /**
     * Answer my coins
     * @return LinkedList<Coin>
     */
    protected LinkedList<Coin> getCoins() {
        return coins;
    }


    /**
     * Set my coins
     * @param coins LinkedList<Coin>
     */
    protected void setCoins(LinkedList<Coin> coins) {
        this.coins = coins;
    }

    
    /**
     * Add a player to me
     * @param aPlayer Player
     */
    public void addPlayer(Player aPlayer) {
        
        this.getPlayers().add(aPlayer);
        
    }
    
 
    /**
     * Switch players
     * @param aPlayer Player
     * @return Player
     */
    public Player switchPlayers(Player aPlayer) {
        
        Player  tempResult = null;
        
        if (aPlayer != null) {
            
            if (this.isPlayer1(aPlayer)) {
                
                tempResult = this.getPlayer2();
            }
            else {
                
                tempResult = this.getPlayer1();
            }
        }
        
        return tempResult;
        
    }
    
    
    /**
     * Answer player 1
     * @return Player
     */
    public Player getPlayer1() {
        
        Player  tempPlayer = null;
        
        if (this.getPlayers().size() >= 1) {
            
            tempPlayer = this.getPlayers().get(0);
        }
        
        return tempPlayer;
        
    }
    
    /**
     * Answer whether or not aPlayer is player 1
     * @param aPlayer Player
     * @return boolean
     */
    protected boolean isPlayer1(Player aPlayer) {
        
        Player  tempPlayer1;
        
        tempPlayer1 = this.getPlayer1();
        
        return (aPlayer != null) &&
                    (tempPlayer1 != null) &&
                        (tempPlayer1.equals(aPlayer));
        
    }
    
    /**
     * Answer whether or not aPlayer is player 2
     * @param aPlayer Player
     * @return boolean
     */
    protected boolean isPlayer2(Player aPlayer) {
        
        Player  tempPlayer2;
        
        tempPlayer2 = this.getPlayer2();
        
        return (aPlayer != null) &&
                    (tempPlayer2 != null) &&
                        (tempPlayer2.equals(aPlayer));
        
    }
    
    
    
    /**
     * Answer player 2
     * @return Player
     */
    public Player getPlayer2() {
        
        Player  tempPlayer = null;
        
        if (this.getPlayers().size() >= 2) {
            
            tempPlayer = this.getPlayers().get(1);
        }
        
        return tempPlayer;
        
    }
    
    
    
    
    /**
     * Answer my players
     * @return List<Player>
     */
    protected List<Player> getPlayers() {
        return players;
    }


    /**
     * Set my players
     * @param players List<Player>
     */
    protected void setPlayers(List<Player> players) {
        this.players = players;
    }

    
}
