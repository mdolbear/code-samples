package com.oracle.tictactoe.gamemodel;

import java.io.Serializable;

import javax.persistence.*;

/**
 *
 *
 */
@Entity
@Table(name="game_eval")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="eval_type")
public abstract class GameEvaluation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name="game_id")
    private TicTacToeGame game;
    
    @Version
    @Column(name="version")
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
