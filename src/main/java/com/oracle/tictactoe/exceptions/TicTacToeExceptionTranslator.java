package com.oracle.tictactoe.exceptions;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oracle.tictactoe.TicTacToeGameService;

/**
 *
 *
 */
@ControllerAdvice
public class TicTacToeExceptionTranslator extends ResponseEntityExceptionHandler {

    //Constants
    private static final Logger logger = LoggerFactory.getLogger(TicTacToeGameService.class);
    
    
    
    /**
     * Answer my logger
     * @return Logger
     */
    protected static Logger getLogger() {
        
        return logger;
    }
    /**
     * Answer an instance for the following arguments
     */
    public TicTacToeExceptionTranslator() {
        super();
    }
    
    /**
     * Handle IllegalStateException
     */
    @ExceptionHandler(value = {IllegalStateException.class, 
                               IllegalArgumentException.class,
                               EntityNotFoundException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException anException,
                                                 WebRequest aRequest) {
        
        String  tempMsg;
        
        tempMsg = anException.getMessage(); 
        getLogger().error(tempMsg);
        
        return this.handleExceptionInternal(anException, 
                                            new ErrorMessage(HttpStatus.CONFLICT, tempMsg), 
                                            new HttpHeaders(), 
                                            HttpStatus.CONFLICT, 
                                            aRequest);

    }
    

}
