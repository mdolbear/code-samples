package com.oracle.tictactoe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 *
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN, 
                reason="Not this player's turn")
public class NotThisPlayerTurnException extends RuntimeException {

    
    //Constants
    private static final long serialVersionUID = -4353979360621180056L;

    /**
     * Answer an instance for the following arguments
     */
    public NotThisPlayerTurnException() {
        super();
    }

    /**
     * Answer an instance for the following arguments
     * @param message
     */
    public NotThisPlayerTurnException(String message) {
        super(message);
    }


}
