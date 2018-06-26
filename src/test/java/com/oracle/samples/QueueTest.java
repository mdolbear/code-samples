package com.oracle.samples;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 *
 *
 */
public class QueueTest {

    /**
     * Answer an instance for the following arguments
     */
    public QueueTest() {
        super();
    }
    
    /**
     * Test circular queue
     */
    @Test
    public void linkedListQueueTest() {
        
        Queue<String>           tempQueue;
        String                  tempValueReturned;
        
        //Test addition and retrieval
        tempQueue = new Queue<String>();
        assertTrue("Empty Test Failed", tempQueue.isEmpty());
        
        tempQueue.add("X");
        tempQueue.add("Y");
        tempQueue.add("Z");
        
        tempValueReturned = tempQueue.remove();
        assertTrue("Value not retrieved", tempValueReturned != null);

        tempValueReturned = tempQueue.remove();
        assertTrue("Value not retrieved", tempValueReturned != null);
        
        tempValueReturned = tempQueue.remove();
        assertTrue("Value not retrieved", tempValueReturned != null);
        
        assertTrue("Empty Test Failed", tempQueue.isEmpty());
        
        try {
            tempValueReturned = tempQueue.remove();
            fail("Should have thrown an exeption here");
            
        }
        catch (IllegalStateException e) {
            
            //success
        }
        
        
        
        
    }


}
