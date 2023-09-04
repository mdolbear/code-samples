package com.mjdsoftware.romannumerals;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class RomanNumeralEvaluator {

    /**
     * Answer a default instandce
     */
    public RomanNumeralEvaluator() {;
    }

    /**
     * Parse aDigits and answer the value of the roman numeral string.
     * @param aDigits String
     * @return int
     */
    public int evaluate(String aDigits) {

        int                 tempResult = 0;
        RomanNumeralDigit   tempDigits;
        RomanNumeralDigit   tempCurrentDigit;
        RomanNumeralDigit   tempPreviousDigit = null;
        RomanNumeralParser  tempParser = new RomanNumeralParser();

        tempDigits = tempParser.parse(aDigits);
        tempCurrentDigit = tempDigits;
        while (tempCurrentDigit != null) {

            //Evaluate current digit value
            tempResult = this.handleSingleDigitEvaluation(tempCurrentDigit, tempPreviousDigit, tempResult);

            //Move up the chain
            tempPreviousDigit = tempCurrentDigit;
            tempCurrentDigit = tempPreviousDigit.getNextDigit();

        }

        return tempResult;

    }

    /**
     * Answer the current accumulation value from calculating the current digit.
     * @param aCurrentDigit RomanNumeralDigit
     * @param aPreviousDigit RomanNumeralDigit
     * @param aCurrentAccumulator int
     * @return int
     */
    private int handleSingleDigitEvaluation(RomanNumeralDigit aCurrentDigit,
                                            RomanNumeralDigit aPreviousDigit,
                                            int aCurrentAccumulator) {

        int               tempCurrentDigitValue;

        tempCurrentDigitValue =
            RomanNumeralUtilities.getInstance().getValueForDigit(aCurrentDigit.digitValueAsString());

        if (aPreviousDigit == null) {

            //First lowest precedent order digit
            aCurrentAccumulator += tempCurrentDigitValue;
        }
        else {

            if (aPreviousDigit.hasHigherOrEqualPrecedenceThan(aCurrentDigit)) {
                aCurrentAccumulator += tempCurrentDigitValue;
            }
            else {
                aCurrentAccumulator -= tempCurrentDigitValue;
            }

        }

        return aCurrentAccumulator;

    }

}
