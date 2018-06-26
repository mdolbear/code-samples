package com.oracle.tictactoe.gamemodel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 *
 */
@Entity
@DiscriminatorValue("DIAGONAL")
public class DiagonalWinEvaluation extends GameEvaluation {

    //Constants
    private static final long serialVersionUID = 7291839644140459481L;

    /**
     * Answer an instance for the following arguments
     */
    public DiagonalWinEvaluation() {
        super();
    }
    
    
    /* (non-Javadoc)
     * @see com.oracle.tictactoe.GameEvaluation#executeEvaluation(com.oracle.tictactoe.TicTacToeGame, com.oracle.tictactoe.Marker)
     */
    @Override
    public Outcome executeEvaluation(TicTacToeGame aGame, Marker aMarker) {
        
        boolean tempEvaluationResult;
        
        tempEvaluationResult = 
                    this.evaluateDiagonalLeftToRight(aGame.getBoard(), aMarker)
                        && this.evaluateDiagonalRightToLeft(aGame.getBoard(), aMarker);
        
        return tempEvaluationResult ? Outcome.WIN : Outcome.UNKNOWN;
    } 


    /**
     * Evaluate diagonal left to right
     * @param aBoard Marker[][]
     * @param aMarker Marker
     * @return boolean
     */
    protected boolean evaluateDiagonalLeftToRight(Marker[][] aBoard,
                                                  Marker aMarker) {
        
        boolean tempResult = true;
        Marker  tempMarkerLocationValue;
        int     tempColumnIndex = 0;
        
        for (int i = 0; i < aBoard.length; i++) {
            
            tempMarkerLocationValue = aBoard[i][tempColumnIndex];
            tempResult = tempResult && ( tempMarkerLocationValue!= null && tempMarkerLocationValue.equals(aMarker));
            tempColumnIndex++;
                                            
        }
        
        return tempResult;
        
    }
    
    /**
     * Evaluate diagonal left to right
     * @param aBoard Marker[][]
     * @param aMarker Marker
     * @return boolean
     */
    protected boolean evaluateDiagonalRightToLeft(Marker[][] aBoard,
                                                  Marker aMarker) {
        
        boolean tempResult = true;
        Marker  tempMarkerLocationValue;
        int     tempColumnIndex = aBoard[0].length-1;
        
        for (int i = aBoard.length-1; i > 0; i--) {
            
            tempMarkerLocationValue = aBoard[i][tempColumnIndex];
            tempResult = tempResult && ( tempMarkerLocationValue!= null && tempMarkerLocationValue.equals(aMarker));
            tempColumnIndex--;
                                            
        }
        
        return tempResult;
        
    }


}
