package com.oracle.samples;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
/**
 *
 *
 */
public class CircularQueueTest {

    /**
     * Answer an instance for the following arguments
     */
    public CircularQueueTest() {
        super();
    }
    
    /**
     * Test circular queue
     */
    @Test
    public void testCircularQueue() {
        
        CircularQueue<String>   tempQueue;
        String                  tempValueReturned;
        
        //Test addition and retrieval
        tempQueue = new CircularQueue<String>(3, new String());
        assertTrue("Empty Test Failed", tempQueue.isEmpty());
        
        tempQueue.put("X");
        tempQueue.put("Y");
        tempQueue.put("Z");
        
        assertTrue("Full Test Failed", tempQueue.isFull());
        
        tempValueReturned = tempQueue.put("Q");
        assertTrue("Full Retrieval Failed", tempValueReturned == null); //Should return null since full and was not stored
        
        tempValueReturned = tempQueue.get();
        assertTrue("Value not retrieved", tempValueReturned != null);

        tempValueReturned = tempQueue.get();
        assertTrue("Value not retrieved", tempValueReturned != null);
        
        tempValueReturned = tempQueue.get();
        assertTrue("Value not retrieved", tempValueReturned != null);
        
        assertTrue("Empty Test Failed", tempQueue.isEmpty());
        
        tempValueReturned = tempQueue.get();
        assertTrue("Empty Retrieval Failed", tempValueReturned == null); //Should return null since empty
        
        
        
        
    }

}
