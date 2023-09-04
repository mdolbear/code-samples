package com.mjdsoftware.romannumerals;

import lombok.Data;

@Data
public class RomanNumeralDigit {

    private Character digit;
    private RomanNumeralDigit nextDigit;

    /**
     * Answer my digit as a string
     * @return String
     */public String digitValueAsString() {
        return this.getDigit().toString();
    }


    /**
     * Answer whether I have higher precedence than aDigitValue
     * @param anotherDigit RomanNumeralDigit
     */
    public boolean hasHigherOrEqualPrecedenceThan(RomanNumeralDigit anotherDigit) {

        int tempMyIndex;
        int tempTheirIndex;

        tempMyIndex =
                RomanNumeralUtilities.getInstance().getPrecedenceDigitFor(this.digitValueAsString());
        tempTheirIndex =
                RomanNumeralUtilities.getInstance().getPrecedenceDigitFor(anotherDigit.digitValueAsString());

        return tempTheirIndex >= tempMyIndex;

    }

}
