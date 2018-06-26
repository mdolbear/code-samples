package com.oracle.tictactoe.gamemodel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 *
 */
@Entity
@DiscriminatorValue("VERTICAL")
public class VerticalWinEvaluation extends GameEvaluation {

    //Constants
    private static final long serialVersionUID = 2124670804089030047L;

    /**
     * Answer an instance for the following arguments
     */
    public VerticalWinEvaluation() {
        super();
    }
    
    /**
     * Execute evaluation for aMarker
     * @param aGame TicTacToeGame
     * @param aMarker Marker
     */
    public Outcome executeEvaluation(TicTacToeGame aGame,
                                      Marker aMarker) {
        
        int         tempColumnIndex = 0;
        boolean     tempEvaluationResult = false;
        
        //Evaluate all columns to determine if we have a vertical win
        while (!tempEvaluationResult && (tempColumnIndex < aGame.getBoard()[0].length)) {
            
            tempEvaluationResult = this.evaluateColumn(tempColumnIndex, aGame.getBoard(), aMarker);
            tempColumnIndex++;
        }
        
        return tempEvaluationResult ? Outcome.WIN : Outcome.UNKNOWN;
        
    }
    
    /**
     * Evaluate column. Answer true if the column denoted with aRowIndex contains aMarker
     * @param aColumnIndex int
     * @param aBoard Marker[][]
     * @param aMarker Marker
     * @return boolean
     */
    protected boolean evaluateColumn(int aColumnIndex, 
                                     Marker[][] aBoard,
                                     Marker aMarker) {
        
        boolean tempResult = true;
        Marker  tempMarkerLocationValue;
        
        for (int j = 0; j < aBoard.length; j++) {
            
            tempMarkerLocationValue = aBoard[j][aColumnIndex];
            tempResult = tempResult && ( tempMarkerLocationValue!= null && tempMarkerLocationValue.equals(aMarker));
                                            
        }
        
        return tempResult;
        
    }


}
