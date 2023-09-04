package com.mjdsoftware.romannumerals;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class RomanNumeralTests {

    @Test
    public void happyPathTests() {

        RomanNumeralEvaluator tempEvaluator = new RomanNumeralEvaluator();
        int                   tempResult;
        String                tempRomainNumerals;

        tempRomainNumerals = "XLVIII";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 48);

        tempRomainNumerals = "III";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 3);

        tempRomainNumerals = "XXX";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 30);

        tempRomainNumerals = "CCC";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 300);

        tempRomainNumerals = "CD";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 400);

        tempRomainNumerals = "CDIV";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 404);

        tempRomainNumerals = "I";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 1);

        tempRomainNumerals = "V";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 5);

        tempRomainNumerals = "M";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 1000);

        tempRomainNumerals = "IV";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 4);

        tempRomainNumerals = "CMDXLIV";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 1444);

        tempRomainNumerals = "MDXLIV";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 1544);

        tempRomainNumerals = "IV";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 4);

        tempRomainNumerals = "IX";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 9);

        tempRomainNumerals = "XL";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 40);

        tempRomainNumerals = "XC";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 90);

        tempRomainNumerals = "CD";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 400);

        tempRomainNumerals = "CM";
        tempResult = tempEvaluator.evaluate(tempRomainNumerals);
        Assertions.assertTrue(tempResult == 900);

    }

    @Test
    public void unHappyPathTests() {

        RomanNumeralEvaluator tempEvaluator = new RomanNumeralEvaluator();
        int tempResult;
        String tempRomainNumerals;

        //Non-repeatables
        try {
            tempRomainNumerals = "VV";
            tempResult = tempEvaluator.evaluate(tempRomainNumerals);
            Assertions.fail("Should fail");
        } catch (IllegalArgumentException e) {

        }

        try {
            tempRomainNumerals = "LL";
            tempResult = tempEvaluator.evaluate(tempRomainNumerals);
            Assertions.fail("Should fail");
        } catch (IllegalArgumentException e) {

        }

        try {
            tempRomainNumerals = "DD";
            tempResult = tempEvaluator.evaluate(tempRomainNumerals);
            Assertions.fail("Should fail");
        } catch (IllegalArgumentException e) {

        }


        //Too many single digits
        try {
            tempRomainNumerals = "IIII";
            tempResult = tempEvaluator.evaluate(tempRomainNumerals);
            Assertions.fail("Should fail");
        } catch (IllegalArgumentException e) {

        }

        //Wrong subtraction definitions for factor of 10
        try {
            tempRomainNumerals = "VX";
            tempResult = tempEvaluator.evaluate(tempRomainNumerals);
            Assertions.fail("Should fail");

        } catch (IllegalArgumentException e) {

        }

        try {
            tempRomainNumerals = "LC";
            tempResult = tempEvaluator.evaluate(tempRomainNumerals);
            Assertions.fail("Should fail");

        } catch (IllegalArgumentException e) {

        }

        try {
            tempRomainNumerals = "DM";
            tempResult = tempEvaluator.evaluate(tempRomainNumerals);
            Assertions.fail("Should fail");

        } catch (IllegalArgumentException e) {

        }

        //Blank
        try {
            tempRomainNumerals = "";
            tempResult = tempEvaluator.evaluate(tempRomainNumerals);
            Assertions.fail("Should fail");

        } catch (IllegalArgumentException e) {

        }

        //Blank
        try {
            tempRomainNumerals = "VXRU";
            tempResult = tempEvaluator.evaluate(tempRomainNumerals);
            Assertions.fail("Should fail");

        } catch (IllegalArgumentException e) {

        }

        //Too many X
        try {
            tempRomainNumerals = "XXXX";
            tempResult = tempEvaluator.evaluate(tempRomainNumerals);
            Assertions.fail("Should fail");

        } catch (IllegalArgumentException e) {

        }

        //Out of order
        try {
            tempRomainNumerals = "IVXLCM";
            tempResult = tempEvaluator.evaluate(tempRomainNumerals);
            Assertions.fail("Should fail");

        } catch (IllegalArgumentException e) {

        }

    }



}
