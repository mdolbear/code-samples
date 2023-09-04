package com.mjdsoftware.romannumerals.state;

import com.mjdsoftware.romannumerals.RomanNumeralParser;
import com.mjdsoftware.romannumerals.RomanNumeralUtilities;
import org.jetbrains.annotations.NotNull;

public class FirstInputAccepted extends AbstractState {

    /**
     * Answer a default instance
     *
     * @param aSubject RomanNumeralParser
     */
    public FirstInputAccepted(RomanNumeralParser aSubject) {
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
        int             tempCurrentIntValue;
        int             tempPreviousIntValue;

        tempPreviousValue = this.getSubjectLastInputValue();
        tempPreviousIntValue = RomanNumeralUtilities.getInstance().getValueForDigit(tempPreviousValue);
        tempCurrentIntValue = RomanNumeralUtilities.getInstance().getValueForDigit(anInput);

        this.pushInputToSubjectQueue(anInput);

        if (anInput.equals(tempPreviousValue)) {

            tempNextState = this.transitionBasedOnRulesForSameValue(anInput);

        }
        else if (tempCurrentIntValue > tempPreviousIntValue) {
            tempNextState = this;
        }
        else {

            tempNextState =
                    this.transitionBasedOnRulesForValueLowerThanPrevious(anInput, tempPreviousValue);

        }


        return tempNextState;
    }

    /**
     * Transition base on rules for lower value
     * @param anInput String
     * @param aPreviousValue String
     * @return
     */
    @NotNull
    private AbstractState transitionBasedOnRulesForValueLowerThanPrevious(String anInput,
                                                                          String aPreviousValue) {
        AbstractState tempNextState;
        String        tempLowerPrecendent =
                RomanNumeralUtilities.getInstance().getLowerPrecedentFrom(aPreviousValue);

        if (anInput.equals(tempLowerPrecendent)) {

            tempNextState = new InputLowerByOneLevel(this.getSubject());
        }
        else {
            tempNextState = new ErrorState(this.getSubject());
        }

        return tempNextState;

    }

    /**
     * Handle rules when the value is the same
     * @param anInput String
     * @return AbstractState
     */
    @NotNull
    private AbstractState transitionBasedOnRulesForSameValue(String anInput) {

        AbstractState   tempNextState;
        int             tempCurrentPrecedentValue;
        int             tempNextPrecedentValue;
        String          tempHigherPrecedenceDigit;

        tempCurrentPrecedentValue =
                RomanNumeralUtilities.getInstance().getValueForDigit(anInput);

        tempHigherPrecedenceDigit =
                RomanNumeralUtilities.getInstance().getHigherPrecedentFrom(anInput);
        tempNextPrecedentValue=
                RomanNumeralUtilities.getInstance().getValueForDigit(tempHigherPrecedenceDigit);

        if ((tempCurrentPrecedentValue * 2 ) >= tempNextPrecedentValue) {
            tempNextState = new ErrorState(this.getSubject());
        }
        else {
            tempNextState = new SecondInputSameAsFirst(this.getSubject());
        }

        return tempNextState;

    }

}
