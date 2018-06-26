package com.oracle.coinproblem;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

/**
 *
 *
 */
public class CoinChangerTest {

    /**
     * Answer an instance for the following arguments
     */
    public CoinChangerTest() {
        super();
    }
    
    /**
     * Test coin changer
     */
    @Test
    public void testChanger() {
        
        CoinChanger                     tempChanger;
        Map<Coin, Integer>              tempChangeMade;
        
        tempChanger = new CoinChanger();
        assertTrue("No changer", tempChanger != null);
        
        tempChangeMade = tempChanger.makeChange(437);
        assertTrue("No change made 1", !tempChangeMade.keySet().isEmpty());
        System.out.println("Change for 437: " + tempChangeMade.toString());
        System.out.println();
        
        tempChangeMade = tempChanger.makeChange(500);
        assertTrue("No change made 2", !tempChangeMade.keySet().isEmpty());
        System.out.println("Change for 500: " + tempChangeMade.toString());
        System.out.println();
        
        tempChangeMade = tempChanger.makeChange(1);
        assertTrue("No change made 3", !tempChangeMade.keySet().isEmpty());
        System.out.println("Change for 1 :" + tempChangeMade.toString());
        System.out.println();
        
        tempChangeMade = tempChanger.makeChange(0);
        assertTrue("Change made 1", tempChangeMade.keySet().isEmpty());
        System.out.println("Change for 0: " + tempChangeMade.toString());
        System.out.println();
        
        
    }

}
