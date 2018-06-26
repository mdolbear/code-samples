package com.oracle.rodcut;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


/**
 *
 *
 */
public class RodCutTest {

    /**
     * Answer an instance for the following arguments
     */
    public RodCutTest() {
        super();
    }
    
    /**
     * Test rod cut
     */
    @Test
    public void testRodCut() {
        
        int[]                       tempPrices = { 1,5,8,9,10,17,17,20,24,30};
        StringBuilder               tempBuilder;
        int                         tempMaxRevenue;
        RodCut                      tempCut;
        Map<Integer, Integer>       tempMaxRevenues = new HashMap<Integer, Integer>();
        
        tempCut = new RodCut(tempPrices);
        tempBuilder = new StringBuilder();
        tempMaxRevenue = tempCut.performCut(10, tempBuilder, tempMaxRevenues);
        System.out.println(tempBuilder.toString());
        System.out.println("Max Revenue: " + tempMaxRevenue);
        
        tempBuilder = new StringBuilder();
        tempCut.dumpMaxRevenues(10, tempBuilder, tempMaxRevenues);
        System.out.println("Max Revenues: ");
        System.out.println(tempBuilder.toString());

        
    }
    
    /**
     * Test rod cut
     */
    @Test
    public void testRodCutBottomUp() {
        
        int[]                       tempPrices = { 1,5,8,9,10,17,17,20,24,30,31,35,39,40,41,42, 43, 44, 45, 
                                                   46, 45, 44, 43, 42, 41, 40, 39, 35, 31, 31, 30, 24, 20, 17, 17, 10, 9, 8, 5, 1};
        StringBuilder               tempBuilder = new StringBuilder();
        RodCut                      tempCut;
        Map<Integer, Integer>       tempMaxRevenues = new HashMap<Integer, Integer>();
        
        tempCut = new RodCut(tempPrices);
        tempCut.performCutBottomUp(11, tempBuilder, tempMaxRevenues);
        System.out.println(tempBuilder);

        
    }

}
