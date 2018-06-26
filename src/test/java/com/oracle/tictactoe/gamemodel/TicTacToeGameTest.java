package com.oracle.tictactoe.gamemodel;

import org.junit.Test;

import com.oracle.tictactoe.gamemodel.Marker;
import com.oracle.tictactoe.gamemodel.Outcome;
import com.oracle.tictactoe.gamemodel.Player;
import com.oracle.tictactoe.gamemodel.TicTacToeGame;

import static org.junit.Assert.assertTrue;

/**
 *
 *
 */
public class TicTacToeGameTest {

    /**
     * Answer an instance for the following arguments
     */
    public TicTacToeGameTest() {
       super();
    }
    
    /**
     * Test game
     */
    @Test
    public void basicDiagonalTest() {
        
        Outcome         tempOutcome = null;
        TicTacToeGame   tempGame = new TicTacToeGame();
        Player          tempPlayer1;
        Player          tempPlayer2;
        StringBuilder   tempBuilder;
        
        tempGame.startNewGame();
        this.setUpPlayersForNonPersistentTest(tempGame);
        
        //choose marker
        tempPlayer1 = tempGame.getPlayerForIndex(0);
        tempGame.chooseMarkerFor(tempPlayer1, Marker.X);
        
        tempPlayer2 = tempGame.getPlayerForIndex(1);
        tempGame.chooseMarkerFor(tempPlayer2, Marker.O);
        
        //Series of moves where player 1 wins
        tempOutcome = tempPlayer1.makeMove(0,0);        
        tempOutcome = tempPlayer2.makeMove(1,0);
        tempOutcome = tempPlayer1.makeMove(1,1);        
        tempOutcome = tempPlayer2.makeMove(2,0);
       
        tempOutcome = tempPlayer1.makeMove(2,2);
        tempBuilder = new StringBuilder();
        tempGame.dumpBoard(tempBuilder);
        System.out.println(tempBuilder.toString());
        System.out.println();
        
        System.out.println("Outcome: " + tempOutcome + " player 1");
        assertTrue("Outcome for player 1 wrong", tempOutcome.equals(Outcome.WIN));
        
    }
    
    /**
     * Test game
     */
    @Test
    public void basicHorizontalTest() {
        
        Outcome         tempOutcome = null;
        TicTacToeGame   tempGame = new TicTacToeGame();
        Player          tempPlayer1;
        Player          tempPlayer2;
        StringBuilder   tempBuilder;
        
        tempGame.startNewGame();
        this.setUpPlayersForNonPersistentTest(tempGame);
        
        //choose marker
        tempPlayer1 = tempGame.getPlayerForIndex(0);
        tempGame.chooseMarkerFor(tempPlayer1, Marker.X);
        
        tempPlayer2 = tempGame.getPlayerForIndex(1);
        tempGame.chooseMarkerFor(tempPlayer2, Marker.O);
        
        //Series of moves where player 1 wins
        tempOutcome = tempPlayer1.makeMove(0,0);        
        tempOutcome = tempPlayer2.makeMove(1,1);
        tempOutcome = tempPlayer1.makeMove(0,1);        
        tempOutcome = tempPlayer2.makeMove(2,0);
       
        tempOutcome = tempPlayer1.makeMove(0,2);
        tempBuilder = new StringBuilder();
        tempGame.dumpBoard(tempBuilder);
        System.out.println(tempBuilder.toString());
        System.out.println();
        
        System.out.println("Outcome: " + tempOutcome + " player 1");
        assertTrue("Outcome for player 1 wrong", tempOutcome.equals(Outcome.WIN));
        
    }
    
  
    /**
     * Test game
     */
    @Test
    public void basicVerticalTest() {
        
        Outcome         tempOutcome = null;
        TicTacToeGame   tempGame = new TicTacToeGame();
        Player          tempPlayer1;
        Player          tempPlayer2;
        StringBuilder   tempBuilder;
        
        tempGame.startNewGame();
        this.setUpPlayersForNonPersistentTest(tempGame);
        
        //choose marker
        tempPlayer1 = tempGame.getPlayerForIndex(0);
        tempGame.chooseMarkerFor(tempPlayer1, Marker.X);
        
        tempPlayer2 = tempGame.getPlayerForIndex(1);
        tempGame.chooseMarkerFor(tempPlayer2, Marker.O);
        
        //Series of moves where player 1 wins
        tempOutcome = tempPlayer1.makeMove(0,0);        
        tempOutcome = tempPlayer2.makeMove(1,1);
        tempOutcome = tempPlayer1.makeMove(1,0);        
        tempOutcome = tempPlayer2.makeMove(2,1);
       
        tempOutcome = tempPlayer1.makeMove(2,0);
        tempBuilder = new StringBuilder();
        tempGame.dumpBoard(tempBuilder);
        System.out.println(tempBuilder.toString());
        System.out.println();
        
        System.out.println("Outcome: " + tempOutcome + " player 1");
        assertTrue("Outcome for player 1 wrong", tempOutcome.equals(Outcome.WIN));
        
    }
    

    /**
     * Set up players for non-persistent test
     * @param aGame TicTacToeGame
     */
    protected void setUpPlayersForNonPersistentTest(TicTacToeGame aGame) {
        
        int tempValue = 1;
        
        for (Player aPlayer: aGame.getPlayers()) {
            
            aPlayer.setId(new Long(tempValue++));
        }
    }
    
}
