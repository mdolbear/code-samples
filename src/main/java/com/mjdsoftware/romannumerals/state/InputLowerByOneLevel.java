package com.mjdsoftware.romannumerals.state;

import com.mjdsoftware.romannumerals.RomanNumeralParser;
import com.mjdsoftware.romannumerals.RomanNumeralUtilities;

public class InputLowerByOneLevel extends AbstractState {


    /**
     * Answer a default instance
     *
     * @param aSubject RomanNumeralParser
     */
    public InputLowerByOneLevel(RomanNumeralParser aSubject) {
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

        String          tempSecondToLastInput;
        String          tempCorrectHigherPrecendent;
        AbstractState   tempNextState;
        int             tempCurrentPrecedentValue;
        int             tempCorrectHigherPrecedentValue;

        tempSecondToLastInput = this.getSubjectLastInputValue();
        this.pushInputToSubjectQueue(anInput);

        tempCurrentPrecedentValue =
                RomanNumeralUtilities.getInstance().getValueForDigit(anInput);
        tempCorrectHigherPrecendent =
                RomanNumeralUtilities.getInstance().getHigherPrecedentFrom(tempSecondToLastInput);
        tempCorrectHigherPrecedentValue
                = RomanNumeralUtilities.getInstance().getValueForDigit(tempCorrectHigherPrecendent);

        if (tempCurrentPrecedentValue >
                tempCorrectHigherPrecedentValue) {

            tempNextState = new FirstInputAccepted(this.getSubject());
        }
        else {
            tempNextState = new ErrorState(this.getSubject());
        }

        return tempNextState;

    }


}
