package com.oracle.tictactoe.gamemodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 *
 */
@Entity
@Table(name="GAME_EVAL")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="EVAL_TYPE")
public abstract class GameEvaluation implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="ID")   
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name="GAME_ID")
    private TicTacToeGame game;
    
    @Version
    @Column(name="VERSION")
    private long version;
    
    
    //Constants
    private static final long serialVersionUID = 7821359959928544287L;

    /**
     * Answer an instance for the following arguments
     */
    public GameEvaluation() {
        super();
    }
    
    /**
     * Execute evaluation for aMarker
     * @param aGame TicTacToeGame
     * @param aMarker Marker
     */
    public abstract Outcome executeEvaluation(TicTacToeGame aGame,
                                               Marker aMarker);

    /**
     * Answer my id
     * @return long
     */
    public Long getId() {
        return id;
    }

    /**
     * Answer my game
     * @return TicTacToeGame
     */
    public TicTacToeGame getGame() {
        return game;
    }

    /**
     * Set my game
     * @param game TicTacToeGame
     */
    public void setGame(TicTacToeGame game) {
        this.game = game;
    }
    
    

}
