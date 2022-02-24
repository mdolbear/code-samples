package com.cracking;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

/**
 *
 *
 */
public class LinkedListChallengesTest {

    /**
     * Answer an instance for the following arguments
     */
    public LinkedListChallengesTest() {
       super();
    }
    
    /**
     * Test removing duplicates in linked list
     */
    @Test
    public void removeDuplicatesTest() {
        
        String[]                    tempKeys = {"a", "b", "c", "d", "e", "f", "g","a", "b", "c", "d", "e", "f", "g"};
        String[]                    tempValues = {"a", "b", "c", "d", "e", "f", "g","a", "b", "c", "d", "e", "f", "g"};
        LinkedList<String,String>   tempList;
        StringBuilder               tempBuilder;
        
        
        tempList = this.buildListFrom(tempKeys, tempValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("Original List: " + tempBuilder.toString());
        
        tempBuilder = new StringBuilder();
        tempList.deleteNodesWithDuplicateKeys();
        tempList.dumpListTo(tempBuilder);
        System.out.println("List with duplicates removed: " + tempBuilder.toString());
        
    }
    
    /**
     * Build List from anArray[]. 
     * @param aKeys K[]
     * @param aValues V[]
     * @return LinkedNode<K,V>
     */
    protected <K,V> LinkedList<K,V> buildListFrom(K[] aKeys, 
                                                  V[] aValues,
                                                  Comparator<K> aComparator) {
        
        LinkedList<K,V> tempList = new LinkedList<K,V>();
        
        for (int i = 0; i < aKeys.length; i++) {
            
            tempList.insertNodeWith(aKeys[i],  
                                    aValues[i],
                                    aComparator);
        }
        
        return tempList;
        
    }
    
    /**
     * Build List from anArray[]. 
     * @param anArray K[]
     * @param aValue V[]
     * @return LinkedNode<K,V>
     */
    protected <K,V> LinkedList<K,V> buildListWithLoop(K[] aKeys, 
                                                      V[] aValues,
                                                      Comparator<K> aComparator,
                                                      K aLoopPoint) {
        
        LinkedList<K,V> tempList = new LinkedList<K,V>();
        LinkedNode<K,V> tempLoopNode = null;
        LinkedNode<K,V> tempCurrentNode = null;
        
        for (int i = 0; i < aKeys.length; i++) {
            
            tempCurrentNode =
                    tempList.insertNodeWith(aKeys[i],  
                                            aValues[i],
                                            aComparator);
            
            if (aKeys[i].equals(aLoopPoint)) {
                
                tempLoopNode = tempCurrentNode;
            }
            
        }
        
        //Set last node to point to the loop point
        tempList.getLastNode().setNextNode(tempLoopNode);
        
        return tempList;
        
    }
    
    /**
     * Answer a string comparator
     * @return Comparator<String, String>
     */
    protected Comparator<String> createStringComparator() {
        
        return (String s1, String s2) -> (s1.compareTo(s2));
        
    }
    
    
    /**
     * Kth element test
     */
    @Test
    public void kthElementTest() {
        
        String[]                    tempKeys = {"a", "b", "c", "d", "e", "f", "g","a", "b", "c", "d", "e", "f", "g"};
        String[]                    tempValues = {"a", "b", "c", "d", "e", "f", "g","a", "b", "c", "d", "e", "f", "g"};
        LinkedList<String,String>   tempList;
        StringBuilder               tempBuilder;
        LinkedNode<String, String>  tempResult;
        int                         tempIndex;
        
        
        tempList = this.buildListFrom(tempKeys, tempValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("Original List: " + tempBuilder.toString());
        
        tempIndex = 7;
        tempResult = tempList.getNodeFromEnd(tempIndex);
        assertTrue("Did not find element 7", tempResult.hasKey("g"));
        System.out.println(tempIndex + " element is " + tempResult);
        
        tempIndex = 1;
        tempResult = tempList.getNodeFromEnd(tempIndex);
        assertTrue("Did not find element 0", tempResult.hasKey("a"));
        System.out.println(tempIndex + " element is " + tempResult);
        
        
        
    }
    
    /**
     * Delete "center node" tests in linked list
     */
    @Test
    public void deleteCenterTest() {
        
        String[]                    tempKeys = {"a", "b", "c", "d", "e", "f", "g","a", "b", "c", "d", "e", "f", "g"};
        String[]                    tempValues = {"a", "b", "c", "d", "e", "f", "g","a", "b", "c", "d", "e", "f", "g"};
        LinkedList<String,String>   tempList;
        StringBuilder               tempBuilder;
        LinkedNode<String, String>  tempCandidateNode;
        
        
        tempList = this.buildListFrom(tempKeys, tempValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("Original List: " + tempBuilder.toString());
        
        //Delete first "a" from the list
        tempBuilder = new StringBuilder();
        tempCandidateNode = tempList.findNodeWithKey("a");
        tempCandidateNode = tempList.deleteCenterNode(tempCandidateNode);
        
        assertTrue("Node was not deleted", tempCandidateNode != null);
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("Remaining List with first a removed: " + tempBuilder.toString());
        
        //Remaining "a" should not be deleted from the list
        tempBuilder = new StringBuilder();
        tempCandidateNode = tempList.findNodeWithKey("a");
        tempCandidateNode = tempList.deleteCenterNode(tempCandidateNode);
        
        tempBuilder = new StringBuilder();
        assertTrue("Some node was deleted", tempCandidateNode == null);
        tempList.dumpListTo(tempBuilder);
        System.out.println("Remaining List should not change: " + tempBuilder.toString());
        
        //Should not delete head from the list
        tempBuilder = new StringBuilder();
        tempCandidateNode = tempList.findNodeWithKey("g");
        tempCandidateNode = tempList.deleteCenterNode(tempCandidateNode);
        assertTrue("Some node was deleted", tempCandidateNode == null);
        
        tempBuilder = new StringBuilder();
        assertTrue("Some node was deleted", tempCandidateNode == null);
        tempList.dumpListTo(tempBuilder);
        System.out.println("Remaining List should not change: " + tempBuilder.toString());
        
        
    }
    
    /**
     * Partition test
     */
    @Test
    public void partitionTest() {
        
        String[]                    tempKeys = {"a", "b", "c", "d", "e", "f", "h","i", "j", "k", "l", "m", "n", "o"};
        String[]                    tempValues = {"a", "b", "c", "d", "e", "f", "h","i", "j", "k", "l", "m", "n", "o"};
        String                      tempPivotKey;
        
        //Test in center of list
        tempPivotKey = "f";
        System.out.println("PivotKey: " + tempPivotKey);
        this.performSinglePartitionTestCase(tempKeys, tempValues, tempPivotKey, "test 1");
        
        //Towards one end    
        tempPivotKey = "k";
        System.out.println("PivotKey: " + tempPivotKey);
        this.performSinglePartitionTestCase(tempKeys, tempValues, tempPivotKey, "test 2");
        
        //Start of list
        tempPivotKey = "o";
        System.out.println("PivotKey: " + tempPivotKey);
        this.performSinglePartitionTestCase(tempKeys, tempValues, tempPivotKey, "test 3");
        
        //End of list
        tempPivotKey = "a";
        System.out.println("PivotKey: " + tempPivotKey);
        this.performSinglePartitionTestCase(tempKeys, tempValues, tempPivotKey, "test 4");
        
        //Pivot key not in list
        tempPivotKey = "g";
        System.out.println("PivotKey: " + tempPivotKey);
        this.performSinglePartitionTestCase(tempKeys, tempValues, tempPivotKey, "test 4");
        
        
    }

    /**
     * @param aKeys
     * @param aValues
     * @param aPivotKey
     */
    protected void performSinglePartitionTestCase(String[] aKeys,
                                                  String[] aValues, 
                                                  String aPivotKey, 
                                                  String aTestId) {
        
        
        LinkedList<String, String>                              tempList;
        StringBuilder                                           tempBuilder;
        
        
        tempList = this.buildListFrom(aKeys, aValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println(aTestId + " Original List: " + tempBuilder.toString());
        
        tempList.partitionAround(aPivotKey);
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println(aTestId + " PartitionList: " + tempBuilder.toString());
        
    }
    
    /**
     * Test reverse
     */
    @Test
    public void reverseTest() {
        
        String[]                                                tempKeys = {"a", "b", "c", "d", "e", "f", "h","i", "j", "k", "l", "m", "n", "o"};
        String[]                                                tempValues = {"a", "b", "c", "d", "e", "f", "h","i", "j", "k", "l", "m", "n", "o"};
        LinkedList<String, String>                              tempList;
        StringBuilder                                           tempBuilder;
        LinkedNode<String, String>                              tempOriginalFirstNode;
        LinkedNode<String, String>                              tempOriginalLastNode;
        
        tempList = this.buildListFrom(tempKeys, tempValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("Reverse Test Original List: " + tempBuilder.toString());
        tempOriginalLastNode = tempList.getLastNode();
        tempOriginalFirstNode = tempList.getHead();
        
        tempList.reverse();
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("Reverse Test Reversed List: " + tempBuilder.toString());
        assertTrue("Reverse failed", tempList.getHead().equals(tempOriginalLastNode) && tempList.getLastNode().equals(tempOriginalFirstNode));
        
    }

    /**
     * Test single reverse
     */
    @Test
    public void reverseSingleTest() {

        String[]                                                tempKeys = {"a", "b", "c", "d", "e", "f", "h","i", "j", "k", "l", "m", "n", "o"};
        String[]                                                tempValues = {"a", "b", "c", "d", "e", "f", "h","i", "j", "k", "l", "m", "n", "o"};
        LinkedList<String, String>                              tempList;
        StringBuilder                                           tempBuilder;
        LinkedNode<String, String>                              tempOriginalFirstNode;
        LinkedNode<String, String>                              tempOriginalLastNode;

        tempList = this.buildListFrom(tempKeys, tempValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("Reverse Test Original List: " + tempBuilder.toString());
        tempOriginalLastNode = tempList.getLastNode();
        tempOriginalFirstNode = tempList.getHead();

        tempList.reverseSingle();
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("Reverse Test Reversed List: " + tempBuilder.toString());
        assertTrue("Reverse failed", tempList.getHead().equals(tempOriginalLastNode) && tempList.getLastNode().equals(tempOriginalFirstNode));

    }
    
    /**
     * Test sum of lists
     */
    @Test
    public void sumOfListsTest() {
        
        String[]                                                tempKeys = {"3", " 2", "1", "0"};
        Integer[]                                               tempValues = {new Integer(3), new Integer(3), new Integer(3), new Integer(3)};
        LinkedList<String, Integer>                             tempList1;
        LinkedList<String, Integer>                             tempList2;
        LinkedList<String, Integer>                             tempResult;
        StringBuilder                                           tempBuilder;
        
        tempList1 = this.buildListFrom(tempKeys, tempValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList1.dumpListTo(tempBuilder);
        System.out.println("First List: " + tempBuilder.toString());
        
        tempList2 = this.buildListFrom(tempKeys, tempValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList2.dumpListTo(tempBuilder);
        System.out.println("Second List: " + tempBuilder.toString());
        
        tempResult = tempList1.newListAsSumOf(tempList2, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempResult.dumpListTo(tempBuilder);
        System.out.println("Sum List: " + tempBuilder.toString());       
        
        
    }
    
    /**
     * Test sum of lists with carry
     */
    @Test
    public void sumOfListsWithCarryTest() {
        
        String[]                                                tempKeys = {"3", " 2", "1", "0"};
        Integer[]                                               tempValues = {new Integer(9), new Integer(9), new Integer(9), new Integer(9)};
        LinkedList<String, Integer>                             tempList1;
        LinkedList<String, Integer>                             tempList2;
        LinkedList<String, Integer>                             tempResult;
        StringBuilder                                           tempBuilder;
        
        tempList1 = this.buildListFrom(tempKeys, tempValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList1.dumpListTo(tempBuilder);
        System.out.println("First List: " + tempBuilder.toString());
        
        tempList2 = this.buildListFrom(tempKeys, tempValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList2.dumpListTo(tempBuilder);
        System.out.println("Second List: " + tempBuilder.toString());
        
        tempResult = tempList1.newListAsSumOf(tempList2, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempResult.dumpListTo(tempBuilder);
        System.out.println("Sum List: " + tempBuilder.toString());       
        
        
    }
    
    /**
     * Test sum of lists with carry
     */
    @Test
    public void sumOfListsWithAnEmptyListTest() {
        
        String[]                                                tempKeys = {"3", " 2", "1", "0"};
        Integer[]                                               tempValues = {new Integer(9), new Integer(9), new Integer(9), new Integer(9)};
        LinkedList<String, Integer>                             tempList1;
        LinkedList<String, Integer>                             tempList2;
        LinkedList<String, Integer>                             tempResult;
        StringBuilder                                           tempBuilder;
        
        tempList1 = this.buildListFrom(tempKeys, tempValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList1.dumpListTo(tempBuilder);
        System.out.println("First List: " + tempBuilder.toString());
        
        tempList2 = new LinkedList<String, Integer>();
        tempBuilder = new StringBuilder();
        tempList2.dumpListTo(tempBuilder);
        System.out.println("Second List: " + tempBuilder.toString());
        
        tempResult = tempList1.newListAsSumOf(tempList2, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempResult.dumpListTo(tempBuilder);
        System.out.println("Sum List: " + tempBuilder.toString());       
        
        
    }
    
    
    
    /**
     * Is palidrome test
     */
    @Test
    public void performIsPalindromeTest() {
        
            
        String[]                                                tempKeys1 = {"a", "b", "c", "d", "e", "f", "h","i", "j", "k", "l", "m", "n", "o"};
        String[]                                                tempValues1 = {"a", "b", "c", "d", "e", "f", "h","i", "j", "k", "l", "m", "n", "o"};
        String[]                                                tempKeys2 = {"a", "b", "c", "d", "c", "b", "a"};
        String[]                                                tempValues2 = {"a", "b", "c", "d", "c", "b", "a"};
        String[]                                                tempKeys3 = {"a", "b", "c",  "c", "b", "a"};
        String[]                                                tempValues3 = {"a", "b", "c",  "c", "b", "a"};
        LinkedList<String, String>                              tempList;
        StringBuilder                                           tempBuilder;
        boolean                                                 tempResult;
        
        tempList = this.buildListFrom(tempKeys1, tempValues1, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("First List: " + tempBuilder.toString());
        tempResult = tempList.isPalindrome();
        assertTrue("Test 1 not palidrome", !tempResult);
        
        tempList = this.buildListFrom(tempKeys2, tempValues2, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("Second List: " + tempBuilder.toString());
        tempResult = tempList.isPalindrome();
        assertTrue("Test 2 is palidrome", tempResult);
        
        tempList = this.buildListFrom(tempKeys3, tempValues3, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("Third List: " + tempBuilder.toString());
        tempResult = tempList.isPalindrome();
        assertTrue("Test 3 is palidrome", tempResult);
        
            
            
    }
    
    /**
     * Test loop detection
     */
    @Test
    public void loopDetectionTest() {
        
        String[]                     tempKeys = {"7", "6", "5", "4", "3", "2", "1"};
        String[]                     tempValues = {"7", "6", "5", "4", "3", "2", "1"};
        LinkedList<String, String>   tempList;
        StringBuilder                tempBuilder;
        LinkedNode<String,String>    tempLoopNode;
        String                       tempLoopKey;
        
        //First test with no loop
        tempList = this.buildListFrom(tempKeys, tempValues, this.createStringComparator());
        tempBuilder = new StringBuilder();
        tempList.dumpListTo(tempBuilder);
        System.out.println("First List: " + tempBuilder.toString());
        tempLoopNode = tempList.findLoopIfExists();
        assertTrue("Should not have a loop", tempLoopNode == null);
        
        //Second test with loop
        tempLoopKey = "1";
        tempList = this.buildListWithLoop(tempKeys, 
                                          tempValues, 
                                          this.createStringComparator(), 
                                          tempLoopKey);
        tempLoopNode = tempList.findLoopIfExists();
        assertTrue("Should have a loop", tempLoopNode != null && tempLoopNode.hasKey(tempLoopKey));
        System.out.println("Loop point: " + tempLoopKey + " discoveredNode: " + tempLoopNode);
        
        //Second test with loop
        tempLoopKey = "2";
        tempList = this.buildListWithLoop(tempKeys, 
                                          tempValues, 
                                          this.createStringComparator(), 
                                          tempLoopKey);
        tempLoopNode = tempList.findLoopIfExists();
        assertTrue("Should have a loop", tempLoopNode != null  && tempLoopNode.hasKey(tempLoopKey));
        System.out.println("Loop point: " + tempLoopKey + " discoveredNode: " + tempLoopNode);
        
        //Third test with loop
        tempLoopKey = "3";
        tempList = this.buildListWithLoop(tempKeys, 
                                          tempValues, 
                                          this.createStringComparator(), 
                                          tempLoopKey);
        tempLoopNode = tempList.findLoopIfExists();
        assertTrue("Should have a loop", tempLoopNode != null && tempLoopNode.hasKey(tempLoopKey));
        System.out.println("Loop point: " + tempLoopKey + " discoveredNode: " + tempLoopNode);
        
        //Fourth test with loop
        tempLoopKey = "4";
        tempList = this.buildListWithLoop(tempKeys, 
                                          tempValues, 
                                          this.createStringComparator(), 
                                          tempLoopKey);
        tempLoopNode = tempList.findLoopIfExists();
        assertTrue("Should have a loop", tempLoopNode != null && tempLoopNode.hasKey(tempLoopKey));
        System.out.println("Loop point: " + tempLoopKey + " discoveredNode: " + tempLoopNode);
        
        //Fifth test with loop
        tempLoopKey = "5";
        tempList = this.buildListWithLoop(tempKeys, 
                                          tempValues, 
                                          this.createStringComparator(), 
                                          tempLoopKey);
        tempLoopNode = tempList.findLoopIfExists();
        assertTrue("Should have a loop", tempLoopNode != null && tempLoopNode.hasKey(tempLoopKey));
        System.out.println("Loop point: " + tempLoopKey + " discoveredNode: " + tempLoopNode);
        
        //Sixth test with loop
        tempLoopKey = "6";
        tempList = this.buildListWithLoop(tempKeys, 
                                          tempValues, 
                                          this.createStringComparator(), 
                                          tempLoopKey);
        tempLoopNode = tempList.findLoopIfExists();
        assertTrue("Should have a loop", tempLoopNode != null && tempLoopNode.hasKey(tempLoopKey));
        System.out.println("Loop point: " + tempLoopKey + " discoveredNode: " + tempLoopNode);
        
        
    }
        
        

}
