package com.oracle.editdistance;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 *
 */
public class EditDistanceTest {

    /**
     * Answer an instance for the following arguments
     */
    public EditDistanceTest() {
        super();
    }
    
    /**
     * Basic Test
     */
    @Test
    @Ignore
    public void basicTest() {
        
        Operation                   tempResult;
        EditDistanceTransformer     tempChallenge;
        String                      tempString1;
        String                      tempString2;
        StringBuilder               tempBuilder;
        
        tempChallenge = new EditDistanceTransformer();
        
        //Test 1
        tempString1 = "pale";
        tempString2 = "ple";
        tempResult = tempChallenge.computeEditDistanceBetween(tempString1,
                                                              tempString2, 
                                                              tempString1.length(),
                                                              tempString2.length(),
                                                              null);
        System.out.println("Edit distance between " 
                            + tempString1 
                            + " and " 
                            + tempString2 
                            + " = " 
                            + tempResult);
        tempBuilder = new StringBuilder();
        tempResult.computeString(tempBuilder);
        System.out.println("Recomputed string: " + tempBuilder.toString());
        assertTrue("Test 1", tempResult.computeEditDistance() == 1);
        

        //Test 2
        tempString1 = "fox";
        tempString2 = "foxfire";
        tempResult = tempChallenge.computeEditDistanceBetween(tempString1,
                                                              tempString2, 
                                                              tempString1.length(),
                                                              tempString2.length(),
                                                              null);
        System.out.println("Edit distance between " 
                            + tempString1 
                            + " and " 
                            + tempString2 
                            + " = " 
                            + tempResult);
        assertTrue("Test 2", tempResult.computeEditDistance() == 4);
        
        //Test 3
        tempString1 = "pale";
        tempString2 = "bale";
        tempResult = tempChallenge.computeEditDistanceBetween(tempString1,
                                                              tempString2, 
                                                              tempString1.length(),
                                                              tempString2.length(),
                                                              null);
        System.out.println("Edit distance between " 
                            + tempString1 
                            + " and " 
                            + tempString2 
                            + " = " 
                            + tempResult);
        assertTrue("Test 3", tempResult.computeEditDistance() == 1);
        
        
        //Test 4
        tempString1 = "sail";
        tempString2 = "sail";
        tempResult = tempChallenge.computeEditDistanceBetween(tempString1,
                                                              tempString2, 
                                                              tempString1.length(),
                                                              tempString2.length(),
                                                              null);
        System.out.println("Edit distance between " 
                            + tempString1 
                            + " and " 
                            + tempString2 
                            + " = " 
                            + tempResult);
        assertTrue("Test 4", tempResult.computeEditDistance() == 0);
        
        //Test 5
        tempString1 = "pale";
        tempString2 = "bake";
        tempResult = tempChallenge.computeEditDistanceBetween(tempString1,
                                                              tempString2, 
                                                              tempString1.length(),
                                                              tempString2.length(),
                                                              null);
        System.out.println("Edit distance between " 
                            + tempString1 
                            + " and " 
                            + tempString2 
                            + " = " 
                            + tempResult);
        assertTrue("Test 5", tempResult.computeEditDistance() == 2);
        
        
    }

}
