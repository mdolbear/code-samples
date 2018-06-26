package com.oracle.samples;

import org.junit.Test;

/**
 *
 *
 */
public class LinkedListTest {

    /**
     * Answer an instance for the following arguments
     */
    public LinkedListTest() {
       super();
    }

    
    /**
     * Simple test
     */
    @Test
    public void simpleTest() {
        
        LinkedList<String, String> tempList = new LinkedList<String, String>();
        String                     tempContents;
        
        tempContents = tempList.dumpContents();
        System.out.println("Inital Contents -" + tempContents);
        
       tempList.add("President" , "Jose Avilar");
       tempContents = tempList.dumpContents();
       System.out.println(tempContents);
       
       tempList.add("Vice president" , "John Smith");
       tempContents = tempList.dumpContents();
       System.out.println(tempContents);
       
       tempList.delete("President");
       tempContents = tempList.dumpContents();
       System.out.println(tempContents);
       
       tempList.delete("Vice president");
       tempContents = tempList.dumpContents();
       System.out.println(tempContents);
       
       //Test reverse
       tempList.add("President" , "Jose Avilar");
       tempList.add("Vice president" , "John Smith");
       tempList.add("Secretary of state", "Alexander Haag");
       tempContents = tempList.dumpContents();
       System.out.println(tempContents);
       
       tempList.reverse();
       tempContents = tempList.dumpContents();
       System.out.println(tempContents);
       
       
    }
    
    
}
