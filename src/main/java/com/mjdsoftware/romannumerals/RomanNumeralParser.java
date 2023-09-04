package com.mjdsoftware.romannumerals;

import com.mjdsoftware.romannumerals.state.AbstractState;
import com.mjdsoftware.romannumerals.state.StartState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class RomanNumeralParser {

    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
    private LinkedList<String> parsedNumeralHistory;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC)
    private AbstractState currentState;

    /**
     * Answer a default instance
     */
    public RomanNumeralParser() {

        this.setParsedNumeralHistory(new LinkedList<>());
        this.setCurrentState(new StartState(this));

    }

    /**
     * Answer a roman numeral string as a "least significant
     * digit first" linked list
     * @param aRomanStringToParse String
     * @return RomanNumeralDigit
     */
    public RomanNumeralDigit parse(String aRomanStringToParse) {

        char[]                  tempCharacters;
        RomanNumeralDigit       tempResult;
        RomanNumeralDigit       tempPreviousDigit = null;
        RomanNumeralDigit       tempCurrentDigit = null;

        this.validateTotalString(aRomanStringToParse);

        //Reverse order so precedent is from least to most
        aRomanStringToParse =
                this.reverseRomanNumeralStringToHaveLeastPrecedentFirst(aRomanStringToParse);

        tempCharacters = aRomanStringToParse.toCharArray();
        tempResult = new RomanNumeralDigit();
        tempCurrentDigit = tempResult;
        for (int i = 0; i < tempCharacters.length; i++) {

            this.parseCurrentDigit(tempCurrentDigit, tempPreviousDigit, tempCharacters[i]);

            tempPreviousDigit = tempCurrentDigit;
            tempCurrentDigit = new RomanNumeralDigit();

        }

        return tempResult;

    }

    /**
     * Parse the current romain numeral digit
     * @param aCurrentDigit RomanNumeralDigit
     * @param aPreviousDigit RomanNumeralDigit
     * @param theCurrentCharacter char
     */
    private void parseCurrentDigit(RomanNumeralDigit aCurrentDigit,
                                   RomanNumeralDigit aPreviousDigit,
                                   char theCurrentCharacter) {

        Character tempCurrentValue;

        tempCurrentValue = Character.valueOf(theCurrentCharacter);

        //Capture value in linked list
        aCurrentDigit.setDigit(tempCurrentValue);

        //Validate current input
        this.validateRomanNumeralString(aCurrentDigit.digitValueAsString());


        if (aPreviousDigit != null) {
            aPreviousDigit.setNextDigit(aCurrentDigit);
        }

    }

    /**
     * Revers the precedent order of the string o have the least significant digit
     * first
     * @param aRomanStringToParse String
     * @return String
     */
    private String reverseRomanNumeralStringToHaveLeastPrecedentFirst(String aRomanStringToParse) {

        StringBuilder   tempBuilder = new StringBuilder();

        tempBuilder.append(aRomanStringToParse);
        tempBuilder.reverse();

        return tempBuilder.toString();

    }

    /**
     * Answer whether roman numeral string is valid. Throw an exception
     * if that is not the case
     * @param aRomanStringToParse String
     */
    private void validateTotalString(String aRomanStringToParse) {

        if (aRomanStringToParse == null ||
                aRomanStringToParse.trim().isEmpty() ||
                  !RomanNumeralUtilities.getInstance().hasOnlyValidRomanNumeralCharacters(aRomanStringToParse)) {

            throw new IllegalArgumentException("Invalid digit encountered");

        }


    }


    /**
     * Throw an exception if the romain numeral digit is not valid. This needs to do
     * precedent checking....TBD some sort of object that keeps history (State machine? with
     * state kept in parser? Makes parser non-thread safe unless we create the state machine on the fly).
     * @param aDigit String
     */
    private void validateRomanNumeralString(String aDigit) {

        if (RomanNumeralUtilities.getInstance().getValueForDigit(aDigit) == null) {
            throw new IllegalArgumentException("Invalid digit encountered");
        }

        //Let current state evaluate input
        this.getCurrentState().takeInput(aDigit);

        //Throw exception if we entered the error state
        if (this.getCurrentState().isErrorState()) {
            throw new IllegalArgumentException("Invalid digit encountered based on input state");
        }

    }


    /**
     * Push value to history
     * @param aValue String
     */
    public void pushValueToHistory(String aValue) {
        this.getParsedNumeralHistory().offer(aValue);
    }

    /**
     * Answer last input value in history
     * @return String
     */
    public String getLastInputValue() {
        return this.getParsedNumeralHistory().peekLast();
    }

    /**
     * Answer last input value in history
     * @return String
     */
    public String getSecondToLastInputValue() {

        Object tempLastValue = this.getParsedNumeralHistory().peekLast();
        int    tempIdx = this.getParsedNumeralHistory().lastIndexOf(tempLastValue);

        return this.getParsedNumeralHistory().get(tempIdx - 1);

    }

}
