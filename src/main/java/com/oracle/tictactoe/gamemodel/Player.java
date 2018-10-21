package com.oracle.tictactoe.gamemodel;

import java.io.Serializable;

import javax.persistence.*;

/**
 *
 *
 */
@Entity
@Table(name="player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name="marker")
    private Marker chosenMarker;
    
    @ManyToOne()
    @JoinColumn(name="game_id")
    private TicTacToeGame game;
    
    @Version
    @Column(name="version")
    private long version;
    
    //Constants
    private static final long serialVersionUID = 874641076262350317L;
    
    /**
     * Answer an instance for the following arguments
     */
    public Player() {
        
        super();
        
    }
    
    /**
     * Answer an instance for the following arguments
     * @param aGame TicTacToeGame
     */
    public Player(TicTacToeGame aGame) {
        
        this();
        this.setGame(aGame);
        
    }

    
    /**
     * Answer my id
     * @return long
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Set my id -- only used for test purposes in a non-persistent test
     * @param anId Long
     */
    public void setId(Long anId) {
        
        this.id = anId;
    }


    /**
     * Make move
     * @param aRowIdx int
     * @param aColumnIdx int
     * @return Outcome
     */
    public Outcome makeMove(int aRowIdx, 
                         int aColumnIdx) {
        
        
        this.validateMarkerChosen();
        return this.getGame().makeMove(this, 
                                       aRowIdx, 
                                       aColumnIdx);
        
    }
    
    
    /**
     * Answer whether or not I have aMarker
     * @param aMarker Marker
     * @return boolean
     */
    public boolean hasMarker(Marker aMarker) {
        
        return this.getChosenMarker() != null &&
                    this.getChosenMarker().equals(aMarker);
        
    }
    
    
    /**
     * Answer my chosenMarker
     * @return Marker
     */
    protected Marker getChosenMarker() {
        return chosenMarker;
    }

    /**
     * Set my chosenMarker
     * @param chosenMarker Marker
     */
    protected void setChosenMarker(Marker chosenMarker) {
        this.chosenMarker = chosenMarker;
    }


    /**
     * Answer my game
     * @return TicTacToeGame
     */
    protected TicTacToeGame getGame() {
        return game;
    }


    /**
     * Set my game
     * @param game TicTacToeGame
     */
    protected void setGame(TicTacToeGame game) {
        this.game = game;
    }
    
    
    /**
     * Validate marker chosen
     */
    protected void validateMarkerChosen() 
            throws IllegalStateException {
        
        if (this.getChosenMarker() == null) {
            
            throw new IllegalStateException("Marker not yet chosen");
        }
        
    }
    
    /**
     * Clear game parameters
     */
    public void clearGameParameters() {
        
        this.setChosenMarker(null);
    }
        
    
}
