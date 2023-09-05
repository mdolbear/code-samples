package com.mjdsoftware.romannumerals;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class RomanNumeralUtilities {

    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
    private  Map<String, Integer> valueMap;


    private static RomanNumeralUtilities instance;

    //Constants
    private static final String[] PRECEDENT = {"I", "V", "X", "L", "C", "D", "M"};



    /**
     * Answer my singleton instance
     * @return RomanNumeralUtilities
     */
    public synchronized static RomanNumeralUtilities getInstance() {

        if (instance == null) {
            instance = new RomanNumeralUtilities();
        }

        return instance;

    }


    /**
     * Answer a default instance
     */
    private RomanNumeralUtilities() {
        super();
        this.initialize();
    }

    /**
     * Initialize me
     */
    private void initialize() {

        this.setValueMap(new HashMap<>());
        this.getValueMap().put("M", 1000);
        this.getValueMap().put("D", 500);
        this.getValueMap().put("C", 100);
        this.getValueMap().put("L", 50);
        this.getValueMap().put("X", 10);
        this.getValueMap().put("V", 5);
        this.getValueMap().put("I", 1);

    }

    /**
     * Answer the value for a romain numeral digit or null
     * @param aDigit String
     * @reutrn Integer
     */
    public Integer getValueForDigit(String aDigit) {
        return this.getValueMap().get(aDigit);
    }


    /**
     * Answer higher order precedent digit for aDigit
     * @param aDigit String
     * @return String
     */
    public String getHigherPrecedentFrom(String aDigit) {

        int             tempIndex;
        List<String>    tempDigits = Arrays.asList(PRECEDENT);
        String          tempResult = null;

        tempIndex = tempDigits.indexOf(aDigit);
        if (tempIndex != -1 ||
                tempIndex != 0) {

            tempIndex += 1;
            if (tempIndex >= 0) {
                tempResult = tempDigits.get(tempIndex);
            }

        }

        return tempResult;

    }

    /**
     * Answer lower order precedent digit for aDigit
     * @param aDigit String
     * @return String
     */
    public String getLowerPrecedentFrom(String aDigit) {

        int             tempIndex;
        List<String>    tempDigits = Arrays.asList(PRECEDENT);
        String          tempResult = null;

        tempIndex = tempDigits.indexOf(aDigit);
        if (tempIndex != -1 ||
                tempIndex != 0) {

            //This is based on valid combos. A power of 10 is two below (like IX),
            //but a non power of 10 (like IV, or XL) is only 1 below
            if (this.isPowerOf10(aDigit)) {
                tempIndex -= 2;
            }
            else {
                tempIndex -= 1;
            }

            if (tempIndex < tempDigits.size()) {
                tempResult = tempDigits.get(tempIndex);
            }

        }

        return tempResult;

    }

    /**
     * Answer whether anInput is a power of 10. There is probably a cleaner way
     * but we are not going very high anyway.
     * @param anInput String
     */
    private boolean isPowerOf10(String anInput) {

        double      tempLogResult;
        int tempValue = this.getValueForDigit(anInput);

        tempLogResult = Math.log10(tempValue);
        return tempLogResult%1 == 0;

    }

    /**
     * Answer whether aRomanNumeralString contains only valid romain
     * numeral characters
     * @param aRomanNumeralString String
     * @return boolean
     */
    public boolean hasOnlyValidRomanNumeralCharacters(String aRomanNumeralString) {

        Set<String> tempValidDigits = this.getValueMap().keySet();
        boolean     tempResult = true;
        char[]      tempChars;
        int         i = 0;
        Character   tempCurrentChar;

        if (aRomanNumeralString == null || aRomanNumeralString.trim().isEmpty()) {
            tempResult = false;
        }
        else {

            tempChars = aRomanNumeralString.toCharArray();
            while ( i < tempChars.length && tempResult) {

                tempCurrentChar = Character.valueOf(tempChars[i]);
                tempResult = tempValidDigits.contains(tempCurrentChar.toString());
                i++;

            }
        }

        return tempResult;

    }


    /**
     * Answer the index precedence for aDigit or -1 if aDigit does not
     * exist in the precedence order
     * @param aDigit String
     * @return int
     */
    public int getPrecedenceDigitFor(String aDigit) {

        List<String>    tempDigits = Arrays.asList(PRECEDENT);
        return tempDigits.indexOf(aDigit);

    }

}
