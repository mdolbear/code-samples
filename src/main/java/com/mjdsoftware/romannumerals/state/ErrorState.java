package com.mjdsoftware.romannumerals.state;

import com.mjdsoftware.romannumerals.RomanNumeralParser;

public class ErrorState extends AbstractState {


    /**
     * Answer a default instance
     *
     * @param aSubject RomanNumeralParser
     */
    public ErrorState(RomanNumeralParser aSubject) {
        super(aSubject);
    }

    /**
     * Subclasses override
     *
     * @param anInput String
     * @return AbstractState
     */
    @Override
    protected AbstractState analyzeInputAndTransitionToNextState(String anInput) {

        //We can never leave the error state once it occurs
        return this;
    }

    /**
     * Answer whether I am the error state
     * @return boolean
     */
    public boolean isErrorState() {
        return true;
    }
}
