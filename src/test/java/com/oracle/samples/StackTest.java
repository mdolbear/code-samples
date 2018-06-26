package com.oracle.samples;

import org.junit.Test;


import static org.junit.Assert.assertTrue;

import org.junit.Assert;

/**
 *
 *
 */
public class StackTest {

    /**
     * Answer an instance for the following arguments
     */
    public StackTest() {
        super();
    }
    
    /**
     * Simple test
     */
    @Test
    public void simpleTest() {
        
        Stack<String>   tempStack = new Stack<String>(3);
        String          tempElement;
        
        try {
            
            tempElement = tempStack.pop();
            Assert.fail("Pop should have failed");
        }
        catch (IllegalStateException e) {
            
        }
        
        tempStack.push("1");
        tempStack.push("2");
        tempStack.push("3");
        
        assertTrue("Should be full", tempStack.isFull());
        
        try {
            
            tempStack.push("4");
            Assert.fail("Push should have failed");
        }
        catch (IllegalStateException e) {
            
        }
        
        tempElement = tempStack.pop();
        assertTrue("Should be top 3", tempElement.equals("3"));
        
        tempElement = tempStack.pop();
        assertTrue("Should be top 2", tempElement.equals("2"));
        
        tempElement = tempStack.pop();
        assertTrue("Should be top 1", tempElement.equals("1"));
        
        assertTrue("Should be empty", tempStack.isEmpty());
        
        
        
        
        
    }

}
