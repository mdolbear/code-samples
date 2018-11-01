package com.oracle.tictactoe.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import lombok.Getter;
import lombok.AccessLevel;

import com.oracle.tictactoe.gamemodel.Marker;
import com.oracle.tictactoe.gamemodel.Outcome;
import com.oracle.tictactoe.gamemodel.Player;
import com.oracle.tictactoe.gamemodel.TicTacToeGame;

/**
 *
 *
 */
@Service
public class TicTacToeServiceFacadeImpl implements TicTacToeServiceFacade {

    @Autowired
    private TicTacToeGameJpaRepository repository;

    @Getter(AccessLevel.PRIVATE)
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    //Constants
    private static final String GAME_CREATION_TOPIC_NAME = "game-events";


    /**
     * Answer an instance for the following arguments
     */
    public TicTacToeServiceFacadeImpl() {
        super();
    }

    /**
     * Answer my repository
     * @return TicTacToeGameJpaRepository
     */
    protected TicTacToeGameJpaRepository getRepository() {
        return repository;
    }

    /**
     * Set my repository
     * @param repository TicTacToeGameJpaRepository
     */
    protected void setRepository(TicTacToeGameJpaRepository repository) {
        this.repository = repository;
    }
    
    /* (non-Javadoc)
     * @see com.oracle.tictactoe.persistence.TicTacToeServiceFacade#createNewGame()
     */
    @Override
    public Long createNewGame() {
        
        TicTacToeGame   tempGame;
        
        tempGame = new TicTacToeGame();
        tempGame.startNewGame();
        this.getRepository().saveAndFlush(tempGame);
        this.postGameCreatedEvent(tempGame.getId());

        return tempGame.getId();
    }

    /**
     * Post game created event message
     * @param aGameId Long
     */
    private void postGameCreatedEvent(Long aGameId) {

        this.getKafkaTemplate()
                .send(GAME_CREATION_TOPIC_NAME, "A game was created with id: " + aGameId.toString());
    }

    /* (non-Javadoc)
     * @see com.oracle.tictactoe.persistence.TicTacToeServiceFacade#startNewGame(java.lang.Long)
     */
    @Override
    public void startNewGame(Long aGameId) {
        
        TicTacToeGame   tempGame;
        
        tempGame = this.findGame(aGameId);
        
        tempGame = this.getRepository().getOne(aGameId);
        tempGame.startNewGame();
        this.getRepository().save(tempGame);
        
    }
    
    /* (non-Javadoc)
     * @see com.oracle.tictactoe.persistence.TicTacToeServiceFacade#choosePlayer(java.lang.Long, com.oracle.tictactoe.gamemodel.Marker, int)
     */
    @Override
    public void choosePlayer(Long aGameId,
                             Marker aMarker,
                             int aPlayerIndex) {
        
        Player  tempPlayer;
        TicTacToeGame   tempGame;
        
        tempGame = this.findGame(aGameId);            
        tempPlayer = tempGame.getPlayerForIndex(aPlayerIndex);
        tempGame.chooseMarkerFor(tempPlayer, aMarker);
        this.getRepository().save(tempGame);
            

        
    }
    
    /**
     * Find Game for aGameId
     * @param aGameId Long
     */
    protected TicTacToeGame findGame(Long aGameId) {
        
        TicTacToeGame   tempGame;
        
        if (aGameId != null) {
            
            tempGame = this.getRepository().getOne(aGameId);
            if (tempGame == null) {
            
                throw new IllegalArgumentException("No Game exits for id: " + aGameId);
            
            }
            
        }
        else {
            
            throw new IllegalArgumentException("Game Identifier is null");
        }
        
        return tempGame;
        
    }
    
    /* (non-Javadoc)
     * @see com.oracle.tictactoe.persistence.TicTacToeServiceFacade#makeMove(java.lang.Long, int, int, int)
     */
    @Override
    public Outcome makeMove(Long aGameId,
                            int aPlayerIndex,
                            int aRowIndex,
                            int aColumnIndex) {
        
        Outcome tempResult;
        Player  tempPlayer;
        TicTacToeGame   tempGame;
        
        tempGame = this.findGame(aGameId);
        
        tempPlayer = tempGame.getPlayerForIndex(aPlayerIndex);
        tempResult = tempGame.makeMove(tempPlayer, aRowIndex, aColumnIndex);
        this.getRepository().save(tempGame);
        
        return tempResult;
    }
    
    /* (non-Javadoc)
     * @see com.oracle.tictactoe.persistence.TicTacToeServiceFacade#getGameState(java.lang.Long)
     */
    @Override
    public String getGameState(Long aGameId) {
        
        TicTacToeGame   tempGame;
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempGame = this.findGame(aGameId);
        tempGame.dumpBoard(tempBuilder);
        
        return tempBuilder.toString();
        
    }
    
    /* (non-Javadoc)
     * @see com.oracle.tictactoe.persistence.TicTacToeServiceFacade#deleteGame(java.lang.Long)
     */
    @Override
    public void deleteGame(Long aGameId) {
        
        TicTacToeGame   tempGame;
        
        tempGame = this.findGame(aGameId);
        this.getRepository().delete(tempGame);
        
    }
    
    /**
     * Find all games that currently exist
     * @return List<Game>
     */
    public List<TicTacToeGame> findAllGames() {
        
        List<TicTacToeGame> tempGames;
        
        tempGames = this.getRepository().findAll();
        
        return tempGames;
    }
    

}
