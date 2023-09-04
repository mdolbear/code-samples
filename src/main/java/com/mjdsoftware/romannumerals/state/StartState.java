package com.mjdsoftware.romannumerals.state;

import com.mjdsoftware.romannumerals.RomanNumeralParser;

public class StartState extends AbstractState {

    /**
     * Answer a default instance
     *
     * @param aSubject RomanNumeralParser
     */
    public StartState(RomanNumeralParser aSubject) {
        super(aSubject);
    }

    /**
     * Subclasses override
     *
     * @param anInput Character
     * @return AbstractState
     */
    @Override
    protected AbstractState analyzeInputAndTransitionToNextState(String anInput) {

        this.pushInputToSubjectQueue(anInput);
        return new FirstInputAccepted(this.getSubject());

    }

}
