package com.oracle.tictactoe.gamemodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.oracle.tictactoe.exceptions.GameAlreadyTerminatedException;
import com.oracle.tictactoe.exceptions.NotThisPlayerTurnException;

/**
 *
 *
 */
@Entity
@Table(name="TIC_TAC_TOE")
public class TicTacToeGame implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="ID")   
    private Long id;
    
    @Lob
    @Column(name ="BOARD")
    private Marker[][] board;
    
    @OneToMany(mappedBy = "game", 
               cascade = { CascadeType.ALL })
    private List<Player> players;
    
    @Column(name="CURRENT_PLAYER")
    private int playerIndex;
    
    @Enumerated(EnumType.STRING)
    @Column(name="OUTCOME")
    private Outcome gameOutcome;
    
    @OneToMany(mappedBy = "game", 
               cascade = { CascadeType.ALL })
    private List<GameEvaluation> evaluators;
    
    @Version
    @Column(name="VERSION")
    private long version;
    
    //Constants
    protected static final int BOARD_SIZE = 3;
    protected static final int NUMBER_OF_PLAYERS = 2;
    private static final long serialVersionUID = 1L;
    
    
    /**
     * Answer an instance for the following arguments
     */
    public TicTacToeGame() {
        
        super();
        this.initializeEvaluators();
        this.createPlayers();
        this.setBoard(new Marker[BOARD_SIZE][BOARD_SIZE]);
        this.setPlayerIndex(0);
        
    }
    
    
    /**
     * Clear parameters for players to start a new game
     */
    protected void clearGameParamtersForPlayers() {
        
        for (Player aPlayer: this.getPlayers()) {
            
            aPlayer.clearGameParameters();
        }
        
    }



    /**
     * Answer my id
     * @return long
     */
    public Long getId() {
        return id;
    }



    /**
     * Initialize evaluators. TieEvaluation must end up at the end so that it is always
     * evaluated last
     */
    protected void initializeEvaluators() {
        
        
        this.setEvaluators(new ArrayList<GameEvaluation>());
        
        this.addGameEvaluation(new HorizontalWinEvaluation());
        this.addGameEvaluation(new VerticalWinEvaluation());
        this.addGameEvaluation(new DiagonalWinEvaluation());
        this.addGameEvaluation(new TieEvaluation());
        
    }

    /**
     * Clear players
     */
    protected void clearPlayers() {
        
        this.setPlayers(new ArrayList<Player>());
    }
    
    
    /**
     * Start a new game. Re-initialize everything required
     */
    public void startNewGame() {
        
        this.setPlayerIndex(0);
        this.clearBoard();
        this.clearGameParamtersForPlayers();
        this.setPlayerIndex(0);
        this.setGameOutcome(Outcome.UNKNOWN);
        
    }
    
    /**
     * Create players
     */
    protected void createPlayers() {
        
        this.clearPlayers();
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            
            this.addPlayer(new Player(this));
        }
        
    }
    

    /**
     * Answer my board
     * @return Marker[][]
     */
    protected Marker[][] getBoard() {
        return board;
    }

    /**
     * Set my board
     * @param board Marker[][]
     */
    protected void setBoard(Marker[][] board) {
        this.board = board;
    }

    /**
     * Add aPlayer
     * @param aPlayer Player
     */
    protected void addPlayer(Player aPlayer) {
        
        this.getPlayers().add(aPlayer);
        aPlayer.setGame(this);
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

    /**
     * Answer my currentPlayer
     * @return Player
     */
    protected Player getCurrentPlayer() {

        return this.getPlayersOrdered().get(this.getPlayerIndex());
    }

    
    
    /**
     * Answer the marker at aRowIndex and aColumnIndex
     * @param aRowIndex int
     * @param aColumnIndex int
     * @return Marker
     */
    public Marker getMarkerAt(int aRowIndex,
                              int aColumnIndex) 
                        throws IllegalArgumentException {
        
        this.validateIndexes(aRowIndex, aColumnIndex);
        return this.getBoard()[aRowIndex][aColumnIndex];
        
    }
    
    
    /**
     * Make move
     * @param aPlayer Player
     * @param aRowIndex int
     * @param aColumnIndex int
     * @return Outcome
     */
    public Outcome makeMove(Player aPlayer,
                            int aRowIndex,
                            int aColumnIndex) {
        
        Outcome tempResult;
        
        this.validateCurrentPlayer(aPlayer);
        this.validateGameNotTerminated();
        
        this.placeMarkerAt(aPlayer.getChosenMarker(), 
                           aRowIndex, 
                           aColumnIndex);
        tempResult = this.performEvaluationOfGameState(aPlayer.getChosenMarker());
        this.getNextPlayer(); //This method shifts the index to the next player
        
        this.setGameOutcome(tempResult);
        
        return tempResult;
        
        
    }
    
    /**
     * Perform evaluations of game state. Answer an Outcome if the game is concluded. Otherwise null
     * @param aMarker Marker
     */
    protected Outcome performEvaluationOfGameState(Marker aMarker) {
        
        Iterator<GameEvaluation>    tempItr;
        Outcome                     tempResult = Outcome.UNKNOWN;
        GameEvaluation              tempCurrentEval;
        
        tempItr = this.getEvaluators().iterator();
        while (tempItr.hasNext() && tempResult.equals(Outcome.UNKNOWN)) {
            
            tempCurrentEval = tempItr.next();
            tempResult = tempCurrentEval.executeEvaluation(this, aMarker);
            
        }
        
        return tempResult;
        
    }
    
    /**
     * Place aMerker at aRowIndex/aColumnIndex
     * @param aMarker Marker
     * @param aRowIndex int
     * @param aColumnIndex int
     * 
     */
    protected void placeMarkerAt(Marker aMarker,
                                int aRowIndex,
                                int aColumnIndex) {
        
        this.validateIndexes(aRowIndex, aColumnIndex);
        this.validateNoMarkerAt(aRowIndex, aColumnIndex);
        
        this.getBoard()[aRowIndex][aColumnIndex] = aMarker;
        
    }
    
    
    /**
     * Validate indexes
     * @param aRowIndex int
     * @param aColumnIndex int
     */
    protected void validateNoMarkerAt(int aRowIndex, 
                                      int aColumnIndex) 
            throws IllegalStateException {
        
        if (this.getBoard()[aRowIndex][aColumnIndex] != null) {
            
            throw new IllegalStateException("Marker already exists at row: " 
                                                + aRowIndex
                                                + " column: " + aColumnIndex);
        }
        
    }
    
    /**
     * Validate aMarker is not null
     * @param aMarker Marker
     */
    protected void validateMarker(Marker aMarker) 
            throws IllegalArgumentException {
        
        if (aMarker == null) {
            
            throw new IllegalArgumentException("Marker is null");
        }
    }
    
    
    /**
     * Validate indexes
     * @param aRowIndex int
     * @param aColumnIndex int
     */
    protected void validateIndexes(int aRowIndex, int aColumnIndex) 
            throws IllegalArgumentException {
        
        if (!((aRowIndex < BOARD_SIZE) &&
                (aColumnIndex < BOARD_SIZE))) {
            
            throw new IllegalArgumentException("Invalid indexes. row: " 
                                                + aRowIndex
                                                + " column: " + aColumnIndex);
        }
    }
    
    /**
     * Clear board
     */
    protected void clearBoard() {
        
        for (int i = 0; i < BOARD_SIZE; i++) {
            
            for (int j = 0; j < BOARD_SIZE; j++) {
                
                this.getBoard()[i][j] = null;
            }
            
        }
        
    }
    
    /**
     * Answer the next player in the cycle
     * @return Player
     */
    protected Player getNextPlayer() {
        
        int tempIndex;
        
        tempIndex = incrementPlayerIndex();       
        return this.getPlayersOrdered().get(tempIndex);
        
    }

    /**
     * Increment player index
     * @return int
     */ 
    protected int incrementPlayerIndex() {
        
        int     tempIndex;
       
        tempIndex = this.getPlayerIndex();
        tempIndex = (tempIndex + 1) % NUMBER_OF_PLAYERS;
        this.setPlayerIndex(tempIndex);
        
        return tempIndex;
        
    }

    /**
     * Answer my playerIndex
     * @return int
     */
    protected int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * Set my playerIndex
     * @param playerIndex int
     */
    protected void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
    
    
    /**
     * Choose marker for aPlayer. Throw an exception if this marker is already chosen by
     * another player
     * @param aPlayer Player
     * @param aMarker Marker
     */
    public void chooseMarkerFor(Player aPlayer, Marker aMarker) {
        
        this.validateMarkerNotChosenByAnotherPlayer(aMarker);       
        aPlayer.setChosenMarker(aMarker);
        
    }
    
    
    /**
     * Validate marker not chosen by another player. Throw exception if it has
     * @param aMarker Marker
     */
    protected void validateMarkerNotChosenByAnotherPlayer(Marker aMarker) {
        
        if (this.isMarkerChosen(aMarker)) {
            
            throw new IllegalArgumentException("Marker " + aMarker + " is "
                                                + "already chosen by another player");
            
        }
        
    }

    /**
     * Answer whether a marker is chosen
     * @param aMarker Marker
     * @return boolean
     */
    protected boolean isMarkerChosen(Marker aMarker) {
        
        Predicate<Player> tempFilter;
        List<Player>      tempResults;
        
        tempFilter = (Player aPlayer) -> (aPlayer.hasMarker(aMarker));
        
        tempResults = this.getPlayers().stream()
                                       .filter(tempFilter)
                                       .collect(Collectors.toList());
        
        return !tempResults.isEmpty();
        
    }
    
    /**
     * Validate that aPlayer is the current Player
     * @param aPlayer
     */
    protected void validateCurrentPlayer(Player aPlayer) {
        
        if (!this.isCurrentPlayer(aPlayer)) {
            
            throw new NotThisPlayerTurnException("Player " + aPlayer + " is not the current player");
            
        }
        
    }
    
    /**
     * Answer whether aPlayer is the current player
     * @param aPlayer Player
     * @return boolean
     */
    protected boolean isCurrentPlayer(Player aPlayer) {
        
        return this.getCurrentPlayer() != null &&
                    this.getCurrentPlayer().equals(aPlayer);
        
    }

    /**
     * Answer my gameOutcome
     * @return Outcome
     */
    protected Outcome getGameOutcome() {
        return gameOutcome;
    }

    /**
     * Set my gameOutcome
     * @param gameOutcome Outcome
     */
    protected void setGameOutcome(Outcome gameOutcome) {
        this.gameOutcome = gameOutcome;
    }

    
    /**
     * Add aGameEvaluation to me
     * @param anEvaluator GameEvaluation
     */
    protected void addGameEvaluation(GameEvaluation anEvaluator) {
        
        this.getEvaluators().add(anEvaluator);
        anEvaluator.setGame(this);
    }
    
    
    /**
     * Answer my evaluators
     * @return List<GameEvaluation>
     */
    protected List<GameEvaluation> getEvaluators() {
        return evaluators;
    }

    /**
     * Set my evaluators
     * @param evaluators List<GameEvaluation>
     */
    protected void setEvaluators(List<GameEvaluation> evaluators) {
        this.evaluators = evaluators;
    }
    
    
    /**
     * Dump board
     * @param aBuilder StringBuilder
     */
    public void dumpBoard(StringBuilder aBuilder) {
        
        String  tempCellValue;
        
        for (int i = 0; i < BOARD_SIZE; i++) {
            
            aBuilder.append("Row[" + i +"]:");
            for (int j = 0; j < BOARD_SIZE; j++) {
                
                tempCellValue = (this.getBoard()[i][j] != null) 
                            ? this.getBoard()[i][j].toString() : "null";
                aBuilder.append(" " + tempCellValue + " ");
                
            }
            aBuilder.append(System.getProperty("line.separator"));
            
        }
        
    }
    
    /**
     * Answer the player for anIndex or throw an exception if anIndex is out of bounds
     * @param anIndex int 
     * @return Player
     */
    public Player getPlayerForIndex(int anIndex) {
        
        
        this.validatePlayerIndex(anIndex);        
        return this.getPlayersOrdered().get(anIndex);
        
    }
    
    /**
     * Answer my players ordered by their ids
     * @return List<Player>
     */
    protected List<Player> getPlayersOrdered() {
        
        List<Player>    tempPlayers;
        
        tempPlayers = new ArrayList<Player>(this.getPlayers());
        tempPlayers.sort(this.getPlayerComparator());
        
        return tempPlayers;
    }
    
    /**
     * Answer my Comparator<Player> to order players
     * @return Comparator<Plaer>
     */
    protected Comparator<Player> getPlayerComparator() {
        
        return (p1, p2) -> (p1.getId().intValue() - p2.getId().intValue());
        
    }

    /**
     * Validate player index
     * @param anIndex
     */
    protected void validatePlayerIndex(int anIndex) {
        
        if (anIndex >= this.getPlayers().size()) {
            
            throw new IllegalArgumentException("Player does not exist for index: " + anIndex);
        }
        
    }
    
    /**
     * Validate if game is not terminated
     */
    protected void validateGameNotTerminated() {
        
        if (this.isGameAlreadyTerminated()) {
            
            throw new GameAlreadyTerminatedException("This game is already terminated", 
                                                      this.getGameOutcome());
        }
    }
   
    /**
     * Answer whether or not game is already terminated
     * @return boolean
     */
    public boolean isGameAlreadyTerminated() {
        
        return this.getGameOutcome() != null && !this.getGameOutcome().equals(Outcome.UNKNOWN);
    }
}
