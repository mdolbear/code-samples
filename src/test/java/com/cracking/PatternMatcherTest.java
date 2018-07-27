package com.cracking;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertTrue;

public class PatternMatcherTest {

    /**
     * Answer my default isntance
     */
    public PatternMatcherTest() {

        super();
    }


    /**
     * Test of rabin karp pattern matching
     */
    @Test
    public void rabinKarpPatternMatchingTest() {

        PatternMatcher    tempChallenge;
        java.util.Stack<Integer> tempResults;
        String                      tempText;
        String                      tempPattern;
        int                         tempPrimeNumberForModulo;
        int                         tempNumberOfCharactersInAlphabet= 256; //For ascii comparisons

        tempChallenge = new PatternMatcher();

        //Test 1
        tempText = "I have a brown dog named gus";
        tempPattern = "dog";
        tempPrimeNumberForModulo = 101;
        System.out.println("Modulo for test: " +tempPrimeNumberForModulo);
        tempResults = tempChallenge.performRabinKarpOn(tempText,
                tempPattern,
                tempPrimeNumberForModulo,
                tempNumberOfCharactersInAlphabet);
        assertTrue("No matches - test 1", tempResults.size() == 1);
        System.out.println("Results: " + tempResults.toString());

        //Test 2
        tempText = "dog have a brown dog named gus";
        tempPattern = "dog";
        tempPrimeNumberForModulo = 101;
        System.out.println("Modulo for test: " +tempPrimeNumberForModulo);
        tempResults = tempChallenge.performRabinKarpOn(tempText,
                tempPattern,
                tempPrimeNumberForModulo,
                tempNumberOfCharactersInAlphabet);
        assertTrue("No matches - test 2", tempResults.size() == 2);
        System.out.println("Results: " + tempResults.toString());


        //Test 3
        tempText = "I have a brown dog named gus";
        tempPattern = "dog";
        tempPrimeNumberForModulo = 13;
        System.out.println("Modulo for test: " +tempPrimeNumberForModulo);
        tempResults = tempChallenge.performRabinKarpOn(tempText,
                tempPattern,
                tempPrimeNumberForModulo,
                tempNumberOfCharactersInAlphabet);
        assertTrue("No matches - test 3", tempResults.size() == 1);
        System.out.println("Results: " + tempResults.toString());

        //Test 4
        tempText = "dog have a brown dog named gus";
        tempPattern = "dog";
        tempPrimeNumberForModulo = 13;
        System.out.println("Modulo for test: " +tempPrimeNumberForModulo);
        tempResults = tempChallenge.performRabinKarpOn(tempText,
                tempPattern,
                tempPrimeNumberForModulo,
                tempNumberOfCharactersInAlphabet);
        assertTrue("No matches - test 4", tempResults.size() == 2);
        System.out.println("Results: " + tempResults.toString());


    }


    /**
     * Test of rabin karp numerical pattern matching
     */
    @Test
    public void rabinKarpPatternNumericalMatchingTest() {

        PatternMatcher    tempChallenge;
        Stack<Integer> tempResults;
        String                      tempText;
        String                      tempPattern;
        int                         tempPrimeNumberForModulo;
        int                         tempNumberOfCharactersInAlphabet= 10; //For possible decimal digits

        tempChallenge = new PatternMatcher();

        //Test 1
        tempText = "123455555123455555";
        tempPattern = "1234";
        tempPrimeNumberForModulo = 101;
        System.out.println("Modulo for test: " +tempPrimeNumberForModulo);
        tempResults = tempChallenge.performRabinKarpOn(tempText,
                tempPattern,
                tempPrimeNumberForModulo,
                tempNumberOfCharactersInAlphabet);
        assertTrue("No matches - test 1", tempResults.size() == 2);
        System.out.println("Results: " + tempResults.toString());

        //Test 2
        tempText = "123455555123455555";
        tempPattern = "45";
        tempPrimeNumberForModulo = 101;
        System.out.println("Modulo for test: " +tempPrimeNumberForModulo);
        tempResults = tempChallenge.performRabinKarpOn(tempText,
                tempPattern,
                tempPrimeNumberForModulo,
                tempNumberOfCharactersInAlphabet);
        assertTrue("No matches - test 2", tempResults.size() == 2);
        System.out.println("Results: " + tempResults.toString());


        //Test 3
        tempText = "123455555123455555";
        tempPattern = "1234";
        tempPrimeNumberForModulo = 13;
        System.out.println("Modulo for test: " +tempPrimeNumberForModulo);
        tempResults = tempChallenge.performRabinKarpOn(tempText,
                tempPattern,
                tempPrimeNumberForModulo,
                tempNumberOfCharactersInAlphabet);
        assertTrue("No matches - test 3", tempResults.size() == 2);
        System.out.println("Results: " + tempResults.toString());

        //Test 4
        tempText = "123455555123455555";
        tempPattern = "45";
        tempPrimeNumberForModulo = 13;
        System.out.println("Modulo for test: " +tempPrimeNumberForModulo);
        tempResults = tempChallenge.performRabinKarpOn(tempText,
                tempPattern,
                tempPrimeNumberForModulo,
                tempNumberOfCharactersInAlphabet);
        assertTrue("No matches - test 4", tempResults.size() == 2);
        System.out.println("Results: " + tempResults.toString());


    }

    /**
     * Test of kmp prefix function test
     */
    @Test
    public void kmpPrefixFunctionTest() {

        PatternMatcher tempChallenge;
        int[]           tempResults;
        String          tempPattern;


        tempChallenge = new PatternMatcher();

        //Test 1
        tempPattern = "1234";

        tempResults = tempChallenge.computePrefixFunction(tempPattern);
        System.out.println("Results: " + tempResults);

        //Test 2
        tempPattern = "ababaca";

        tempResults = tempChallenge.computePrefixFunction(tempPattern);
        System.out.println("Results: " + tempResults);

    }
    /**
     * Test of kmp numerical pattern matching
     */
    @Test
    public void kmpPatternNumericalMatchingTest() {

        PatternMatcher tempChallenge;
        Stack<Integer> tempResults;
        String tempText;
        String tempPattern;


        tempChallenge = new PatternMatcher();

        //Test 1
        tempText = "bacbababaabcbab";
        tempPattern = "ababaca";

        tempResults = tempChallenge.kmpMatcher(tempText,
                                               tempPattern);
        assertTrue("No matches - test 1", tempResults.size() == 0);
        System.out.println("Results: " + tempResults.toString());

        //Test 2
        tempText = "bacbababacabab";
        tempPattern = "ababaca";

        tempResults = tempChallenge.kmpMatcher(tempText,
                                               tempPattern);
        assertTrue("No matches - test 2", tempResults.size() == 1);
        System.out.println("Results: " + tempResults.toString());

        //Test 3
        tempText = "bacbababacabababaca";
        tempPattern = "ababaca";

        tempResults = tempChallenge.kmpMatcher(tempText,
                                               tempPattern);
        assertTrue("No matches - test 3", tempResults.size() == 2);
        System.out.println("Results: " + tempResults.toString());

    }

}
