package com.mjdsoftware.romannumerals.state;

import com.mjdsoftware.romannumerals.RomanNumeralParser;
import com.mjdsoftware.romannumerals.RomanNumeralUtilities;

public class ThirdInputSameAsSecond extends AbstractState {


    /**
     * Answer a default instance
     *
     * @param aSubject RomanNumeralParser
     */
    public ThirdInputSameAsSecond(RomanNumeralParser aSubject) {
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

        String          tempPreviousValue;
        AbstractState   tempNextState;
        int             tempCurrentPrecedentValue;
        int             tempPreviousPrecedentValue;

        tempPreviousValue = this.getSubjectLastInputValue();
        this.pushInputToSubjectQueue(anInput);

        tempCurrentPrecedentValue =
                RomanNumeralUtilities.getInstance().getValueForDigit(anInput);
        tempPreviousPrecedentValue
                = RomanNumeralUtilities.getInstance().getValueForDigit(tempPreviousValue);

        if (tempCurrentPrecedentValue >
                tempPreviousPrecedentValue) {
            tempNextState = new FirstInputAccepted(this.getSubject());
        }
        else {
            tempNextState = new ErrorState(this.getSubject());
        }

        return tempNextState;

    }


}
