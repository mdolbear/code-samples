package com.oracle.tictactoe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oracle.tictactoe.gamemodel.Outcome;

/**
 *
 *
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN, 
                reason="Game is already completed. Please start a new one.")
public class GameAlreadyTerminatedException extends RuntimeException {

    private Outcome outcome;
    
    //Constants
    private static final long serialVersionUID = -2725502829321313391L;

    /**
     * Answer an instance for the following arguments
     */
    public GameAlreadyTerminatedException() {
        super();
    }

    /**
     * Answer an instance for the following arguments
     * @param message
     * @param anOutcome
     */
    public GameAlreadyTerminatedException(String message,
                                         Outcome anOutcome) {
        super(message);
        this.setOutcome(anOutcome);
        
    }

    /**
     * Answer my outcome
     * @return Outcome
     */
    public Outcome getOutcome() {
        return outcome;
    }

    /**
     * Set my outcome
     * @param outcome Outcome
     */
    protected void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }



}
