package com.oracle.tictactoe.gamemodel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 *
 */
@Entity
@DiscriminatorValue("HORIZONTAL")
public class HorizontalWinEvaluation extends GameEvaluation {

    //Constants
    private static final long serialVersionUID = -5390175642125589964L;

    /**
     * Answer an instance for the following arguments
     */
    public HorizontalWinEvaluation() {
        super();
    }
    
    /**
     * Execute evaluation for aMarker
     * @param aGame TicTacToeGame
     * @param aMarker Marker
     */
    public Outcome executeEvaluation(TicTacToeGame aGame,
                                      Marker aMarker) {
        
        int         tempRowIndex = 0;
        boolean     tempEvaluationResult = false;
        
        //Evaluate all rows to determine if we have a horizontal win
        while (!tempEvaluationResult && (tempRowIndex < aGame.getBoard().length)) {
            
            tempEvaluationResult = this.evaluateRow(tempRowIndex, aGame.getBoard(), aMarker);
            tempRowIndex++;
        }
        
        return tempEvaluationResult ? Outcome.WIN : Outcome.UNKNOWN;
        
    }
    
    /**
     * Evaluate row. Answer true if the row denoted with aRowIndex contains aMarker
     * @param aRowIndex int
     * @param aBoard Marker[][]
     * @param aMarker Marker
     * @return boolean
     */
    protected boolean evaluateRow(int aRowIndex, 
                                 Marker[][] aBoard,
                                 Marker aMarker) {
        
        boolean tempResult = true;
        Marker  tempMarkerLocationValue;
        
        for (int j = 0; j < aBoard[aRowIndex].length; j++) {
            
            tempMarkerLocationValue = aBoard[aRowIndex][j];
            tempResult = tempResult && ( tempMarkerLocationValue!= null && tempMarkerLocationValue.equals(aMarker));
                                            
        }
        
        return tempResult;
        
    }

}
