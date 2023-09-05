package com.mjdsoftware.romannumerals.state;


import com.mjdsoftware.romannumerals.RomanNumeralParser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public abstract class AbstractState {

    @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PRIVATE)
    private RomanNumeralParser subject;


    /**
     * Answer my logger
     * @return
     */
    private static Logger getLogger() {
        return log;
    }
    /**
     * Answer a default instance
     * @param aSubject RomanNumeralParser
     */
    public AbstractState(RomanNumeralParser aSubject) {

        this.setSubject(aSubject);
    }


    /**
     * Take input and transition to next state
     * @param anInput Character
     * @return AbstractState
     */
    public AbstractState takeInput(String anInput) {

        AbstractState tempNextState = this;

        getLogger().info("CurrentState: {} - Input: {}", this.getClass().getSimpleName(), anInput);

        tempNextState = this.analyzeInputAndTransitionToNextState(anInput);
        this.getSubject().setCurrentState(tempNextState);

        getLogger().info("NextState: {}", tempNextState.getClass().getSimpleName());

        return tempNextState;

    }

    /**
     * Subclasses override
     * @param anInput Character
     * @return AbstractState
     */
    protected abstract AbstractState analyzeInputAndTransitionToNextState(String anInput);

    /**
     * Push input value to subject's queue
     * @param aCharacter String
     */
    protected void pushInputToSubjectQueue(String aCharacter) {

        this.getSubject().pushValueToHistory(aCharacter);
    }

    /**
     * Answer whether I am the error state
     * @return boolean
     */
    public boolean isErrorState() {
        return false;
    }

    /**
     * Answer my subject's last input value
     * @return String
     */
    protected String getSubjectLastInputValue() {
        return this.getSubject().getLastInputValue();
    }


}
