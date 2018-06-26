package com.oracle.tictactoe.gamemodel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 *
 */
@Entity
@DiscriminatorValue("TIE")
public class TieEvaluation extends GameEvaluation {

    //Constants
    private static final long serialVersionUID = 916242195063069224L;

    /**
     * Answer an instance for the following arguments
     */
    public TieEvaluation() {
        
        super();
        
    }

    /* (non-Javadoc)
     * @see com.oracle.tictactoe.GameEvaluation#executeEvaluation(com.oracle.tictactoe.TicTacToeGame, com.oracle.tictactoe.Marker)
     */
    @Override
    public Outcome executeEvaluation(TicTacToeGame aGame, Marker aMarker) {
       
        int     tempRowIndex = 0;
        int     tempColumnIndex;
        boolean tempEmptySquareFound = false;
        
        //Answer a tie if all board squares are occupied. We are assuming that the other evaluators are called before
        //me       
        while (!tempEmptySquareFound && (tempRowIndex < aGame.getBoard().length)) {
            
            tempColumnIndex = 0;
            while (!tempEmptySquareFound && (tempColumnIndex < aGame.getBoard()[0].length)) {
                
                tempEmptySquareFound = tempEmptySquareFound || (aGame.getBoard()[tempRowIndex][tempColumnIndex] == null);
                tempColumnIndex++;
            }
            
            tempRowIndex++;
            
        }
        
        return tempEmptySquareFound ? Outcome.UNKNOWN : Outcome.TIE;
    }
    
    

}
