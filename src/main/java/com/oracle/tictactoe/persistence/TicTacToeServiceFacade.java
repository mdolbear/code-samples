package com.oracle.tictactoe.persistence;

import java.util.List;

import com.oracle.tictactoe.gamemodel.Marker;
import com.oracle.tictactoe.gamemodel.Outcome;
import com.oracle.tictactoe.gamemodel.TicTacToeGame;

public interface TicTacToeServiceFacade {

    /**
     * Create new game
     * @return Long
     */
    Long createNewGame();

    /**
     * Start new game
     * @param anId Long
     */
    void startNewGame(Long aGameId);

    /**
     * Choose player
     * @param aGameId Long
     * @param aMarker Marker
     * @param aPlayerIndex int
     */
    void choosePlayer(Long aGameId, Marker aMarker, int aPlayerIndex);

    /**
     * Make move
     * @param aGameId Long
     * @param aPlayerIndex int
     * @param aRowIndex int
     * @param aColumnIndex int
     * @return Outcome
     */
    Outcome makeMove(Long aGameId, int aPlayerIndex, int aRowIndex,
            int aColumnIndex);

    /**
     * Answer the state of the game with aGameId
     * @param aGameId Long
     * @return String
     */
    String getGameState(Long aGameId);

    /**
     * Delete game
     * @param aGameId Long
     */
    void deleteGame(Long aGameId);
    
    /**
     * Find all games that currently exist
     * @return List<Game>
     */
    List<TicTacToeGame> findAllGames();

}