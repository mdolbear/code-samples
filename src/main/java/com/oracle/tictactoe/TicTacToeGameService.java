package com.oracle.tictactoe;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.tictactoe.gamemodel.Marker;
import com.oracle.tictactoe.gamemodel.Outcome;
import com.oracle.tictactoe.gamemodel.TicTacToeGame;
import com.oracle.tictactoe.persistence.TicTacToeServiceFacade;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *
 */
@RestController
@RequestMapping("/tictactoe")
public class TicTacToeGameService {

    @Autowired
    private TicTacToeApplicationProperties properties;
    
    @Autowired
    private TicTacToeServiceFacade serviceFacade;
    
    
    //Constants
    private static final Logger logger = LoggerFactory.getLogger(TicTacToeGameService.class);
    
    
    
    /**
     * Answer my logger
     * @return Logger
     */
    protected static Logger getLogger() {
        
        return logger;
    }
    
    
    
    /**
     * Answer an instance for the following arguments
     */
    public TicTacToeGameService() {
        super();
    }
    
    /**
     * Create game
     * @return Long
     */
    @PostMapping("creategame")
    public Long createGame() {
        
        return this.getServiceFacade().createNewGame();
    }
    
    /**
     * Start game
     * @param aGameId Long
     */
    @PostMapping("startgame")
    public void startNewGame(@RequestParam("game_id") Long aGameId) {
        
       this.getServiceFacade().startNewGame(aGameId);
       
    }
    
    /**
     * Choose marker for player
     * @param aGameId Long
     * @param aMarker Marker
     * @param aPlayerIndex int
     * 
     */
    @PutMapping("chooseplayer")
    public void choosePlayer(@RequestParam("game_id") Long aGameId,
                             @RequestParam("marker") Marker aMarker,
                             @RequestParam("pindex") int aPlayerIndex) {
        
        this.getServiceFacade().choosePlayer(aGameId, 
                                             aMarker, 
                                             aPlayerIndex);
        
    }
    
    /**
     * Make a move
     * @param aGameId Long
     * @param aPlayerIndex int
     * @param aRowIndex int
     * @param aColumnIndex int
     * @return Outcome
     */
    @PutMapping("move")
    public Outcome makeMove(@RequestParam("game_id") Long aGameId,
                            @RequestParam("pindex") int aPlayerIndex,
                            @RequestParam("row") int aRowIndex,
                            @RequestParam("column") int aColumnIndex) {
        
       return this.getServiceFacade().makeMove(aGameId, 
                                               aPlayerIndex, 
                                               aRowIndex, 
                                               aColumnIndex);
        
    }
    
    /**
     * Answer my current game state
     * @return String
     */
    @GetMapping("state")
    public String getGameState(@RequestParam("game_id") Long aGameId) {
        
        return this.getServiceFacade().getGameState(aGameId);
        
    }
    
    /**
     * Delete game for aGameId
     * @param aGameId Long
     */
    @DeleteMapping("delete")
    public void deleteGame(@RequestParam("game_id") Long aGameId) {
        
        this.getServiceFacade().deleteGame(aGameId);
    }
    
    /**
     * Find all games
     * @return List<TicTacToeGame>
     */
    @GetMapping("allgames")
    public List<TicTacToeGame> findAllGames() {
        
        return this.getServiceFacade().findAllGames();
    }

    /**
     * Upload file test -- just convenient to try it here
     * @param aFile MultipartFile
     * @ResponseEntity
     */
    @PostMapping("uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile aFile) {

        getLogger().debug("Uploading file: " + aFile.getOriginalFilename() + " isEmpty: " + aFile.isEmpty());

          try {
              if (!aFile.isEmpty()) {

                  InputStreamResource tempResource = new InputStreamResource(aFile.getInputStream());


              }
          }
          catch (IOException e) {

              e.printStackTrace();
          }

        return new ResponseEntity("Upload successful", HttpStatus.OK);

    }

    /**
     * Answer my properties
     * @return TicTacToeApplicationProperties
     */
    public TicTacToeApplicationProperties getProperties() {
        return properties;
    }


    /**
     * Answer my serviceFacade
     * @return TicTacToeServiceFacade
     */
    protected TicTacToeServiceFacade getServiceFacade() {
        return serviceFacade;
    }


    
    
}
