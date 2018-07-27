package com.cracking;

import com.oracle.utils.NumberUtilities;

import java.util.Stack;

public class PatternMatcher {

    /**
     * Answer my default instance
     */
    public PatternMatcher() {

        super();
    }


    /**
     * Perform rabin-karp pattern match
     * @param aText String
     * @param aPattern String
     * @param aPrimeNumberForModulo int
     * @param aNumberOfCharactersInAlphabet int Example- 256 for ascii char
     * @return Stack<Integer>
     */
    public Stack<Integer> performRabinKarpOn(String aText,
                                             String aPattern,
                                             int aPrimeNumberForModulo,
                                             int aNumberOfCharactersInAlphabet) {


        Stack<Integer> tempResult = new Stack<Integer>();
        int            tempPatternLength;
        int            tempTextLength;
        int            tempPatternHashValue = 0;
        int            tempTextHashValue = 0;
        int            tempPatternMaxDigit;
        int            tempNumberOfFalseHits = 0;

        //Validation
        this.validateNotNull(aText);
        this.validateNotNull(aPattern);
        this.validateTextLongerThanPattern(aText, aPattern);
        this.validatePrimeNumber(aPrimeNumberForModulo);

        //Initialize parameters
        tempPatternLength = aPattern.length();
        tempTextLength = aText.length();

        tempPatternMaxDigit =
                ((int)Math.pow(aNumberOfCharactersInAlphabet,
                        tempPatternLength - 1)) % aPrimeNumberForModulo;

        //Pre-calculations for algorithm -- calculate has value for pattern and first window of text
        for (int i = 0; i < tempPatternLength; i++) {

            tempPatternHashValue = (aNumberOfCharactersInAlphabet * tempPatternHashValue
                    + aPattern.charAt(i)) % aPrimeNumberForModulo;
            tempTextHashValue = (aNumberOfCharactersInAlphabet * tempTextHashValue
                    + aText.charAt(i)) % aPrimeNumberForModulo;
        }

        System.out.println("HashValue for pattern: " + tempPatternHashValue);
        System.out.println("HashValue for first window of text: " +  tempTextHashValue);

        //Slide the pattern across the text one character at a time
        for (int i = 0; i <= (tempTextLength - tempPatternLength); i++) {

            //If hash matches, we have a "hit" -- i.e. the pattern and the text window map to the same hash value
            if (tempPatternHashValue == tempTextHashValue) {

                //We can get "false hits" with this algorithm, so we still need to do a compare
                if (this.matches(aText, i, aPattern)) {

                    tempResult.push(new Integer(i));

                }
                else {

                    tempNumberOfFalseHits++;
                }
            }

            //Calculate hash value for next window of text -- i.e. remove leading digit and add trailing digit (note
            // looking at number in "reverse" direction, so the trailing digit will keep moving as we move up the array of text
            if (i < (tempTextLength - tempPatternLength)) {

                tempTextHashValue = (aNumberOfCharactersInAlphabet *(tempTextHashValue - aText.charAt(i)*tempPatternMaxDigit)
                        + aText.charAt(i + tempPatternLength))
                        % aPrimeNumberForModulo;

                //text hash goes negative in some cases -- need to protect against this
                if (tempTextHashValue < 0 ) {

                    tempTextHashValue += aPrimeNumberForModulo;

                }

                System.out.println("HashValue for next window of text: " +  tempTextHashValue);
            }

        }

        System.out.println("Number of false hits for algorithm: " + tempNumberOfFalseHits);

        return tempResult;

    }


    /**
     * Validate prime number
     * @param aValue int
     */
    protected void validatePrimeNumber(int aValue) {

        if (!NumberUtilities.getInstance().isPrime(aValue)) {

            throw new IllegalArgumentException("Number is not prime");
        }

    }

    /**
     * Validate string not null
     * @param aValue String
     */
    protected void validateNotNull(String aValue) {

        if (aValue == null) {

            throw new IllegalArgumentException("Input argument cannot be null");
        }

    }

    /**
     * Validate text longer than pattern
     * @param aText String
     * @param aPattern String
     */
    protected void validateTextLongerThanPattern(String aText, String aPattern) {

        if (aPattern.length() > aText.length()) {

            throw new IllegalArgumentException("Text must be longer than pattern");
        }

    }

    /**
     * Answer whether aWindow matches aPattern
     * @param aWindow String
     * @param aWindowStartIdx int
     * @param aPattern String
     * @return boolean
     */
    protected boolean matches(String aWindow,
                              int aWindowStartIdx,
                              String aPattern) {

        boolean     tempResult = true;
        int         j = 0;

        while (tempResult &&
                j < aPattern.length()) {

            tempResult = aWindow.charAt(aWindowStartIdx + j) == aPattern.charAt(j);
            j++;

        }

        return tempResult;

    }


    /**
     * Perform pattern matching according to Knuth-Morris-Pratt algorithm
     * @param aText String
     * @param aPattern String
     * @return Stack<Integer>
     */
    public Stack<Integer> kmpMatcher(String aText,
                                     String aPattern) {

        Stack<Integer> tempResult = new Stack<Integer>();
        int[]          tempPrefixFunction;
        int            tempNumberOfCharactersMatched = 0;

        tempPrefixFunction = this.computePrefixFunction(aPattern);

        for (int i = 0; i < aText.length(); i++) {

            while (tempNumberOfCharactersMatched > 0 &&
                    (aPattern.charAt(tempNumberOfCharactersMatched) != aText.charAt(i))) {

                tempNumberOfCharactersMatched = tempPrefixFunction[tempNumberOfCharactersMatched-1];
            }

            if (aPattern.charAt(tempNumberOfCharactersMatched) == aText.charAt(i))  {

                tempNumberOfCharactersMatched +=1;
            }

            if (tempNumberOfCharactersMatched == aPattern.length()) {

                tempResult.push(new Integer(i - aPattern.length() + 1));
                tempNumberOfCharactersMatched = tempPrefixFunction[tempNumberOfCharactersMatched-1];
            }
        }

        return tempResult;

    }


    /**
     * Compute prefix function
     * @param aPattern String
     * @return int[]
     */
    public int[] computePrefixFunction(String aPattern) {

        int[] tempPrefixFunction;
        int   tempNumberOfCharactersMatched = 0;

        tempPrefixFunction = new int[aPattern.length()];
        tempPrefixFunction[0] = 0;

        for (int q = 1; q < aPattern.length(); q++) {

            while ((tempNumberOfCharactersMatched > 0) &&
                         (aPattern.charAt(tempNumberOfCharactersMatched) != aPattern.charAt(q))) {

                tempNumberOfCharactersMatched = tempPrefixFunction[tempNumberOfCharactersMatched];

            }

            if (aPattern.charAt(tempNumberOfCharactersMatched) == aPattern.charAt(q)) {

                tempNumberOfCharactersMatched += 1;
            }

            tempPrefixFunction[q] = tempNumberOfCharactersMatched;

        }

        return tempPrefixFunction;
    }

}
