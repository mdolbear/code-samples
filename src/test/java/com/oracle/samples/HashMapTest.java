package com.oracle.samples;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

/**
 *
 *
 */
public class HashMapTest {

    /**
     * Answer an instance for the following arguments
     */
    public HashMapTest() {
        super();
    }
    
    /**
     * Simple test
     */
    @Test
    public void simpleTest() {
        
        HashMap<String, String> tempMyMap = new HashMap<String, String>(16);
        String                  tempValue;
        List<String>                 tempValues;
        Set<String>                  tempKeys;
        
        
        tempMyMap.put("me", "Mike");
        tempMyMap.put("you", "Joe");
        
        tempValue = tempMyMap.get("me");
        assertTrue("Retrieve failed", tempValue != null);
        
        tempKeys = tempMyMap.getKeySet();
        assertTrue("Keys don't exist", !tempKeys.isEmpty());
        
        tempValues = tempMyMap.getValues();
        assertTrue("Values don't exist", !tempValues.isEmpty());
        
        
        
    }

}
