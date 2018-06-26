package com.oracle.coingame;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.oracle.coingame.Coin;

/**
 *
 *
 */
public class CoinGameTest {

    /**
     * Answer an instance for the following arguments
     */
    public CoinGameTest() {
        super();
    }
    
    /**
     * Test coin game
     */
    @Test
    public void mainTest() {
        
       CoinGame         tempGame;
       Player           tempCurrentPlayer;
       CoinGameState    tempState;
       
       tempGame = new CoinGame();
       tempGame.reinitializeGame();
       this.initializeMyCoins(tempGame);
       
       tempCurrentPlayer = tempGame.getPlayer1();
       do {
           
           tempState = tempGame.chooseCoinMaxFirst(tempCurrentPlayer);          
           System.out.println("Coin chosen by : " + tempCurrentPlayer.getName() + "  Coin chosen: " + tempState.getChosenCoin().toString());

           tempCurrentPlayer = tempGame.switchPlayers(tempCurrentPlayer);
           
           
       } while (!tempState.isGameOver());
       
       assertTrue("Game did not end", tempState.isGameOver());
       
       if (tempState.isGameOver()) {
           
           System.out.println("Game over");
           System.out.println("Winner: " + tempState.getWinner().getName()
                              + " totalScore: " +tempState.getWinnerTotalScore());
           System.out.println("Loser: " + tempState.getLoser().getName()
                              + " totalScore: " +tempState.getLoserTotalScore());

       }
       
    }
    
    
    /**
     * Initialize my coins
     */
    protected void initializeMyCoins(CoinGame aGame) {
        
        Coin    tempCoin;
        
        tempCoin = new Coin(75);
        aGame.addCoin(tempCoin);
        
        tempCoin = new Coin(5);
        aGame.addCoin(tempCoin);
        
        tempCoin = new Coin(10);
        aGame.addCoin(tempCoin);
        
        tempCoin = new Coin(25);
        aGame.addCoin(tempCoin);
        
        tempCoin = new Coin(50);
        aGame.addCoin(tempCoin);
        
        tempCoin = new Coin(75);
        aGame.addCoin(tempCoin);
        
    }

}
