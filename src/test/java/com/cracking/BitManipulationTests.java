package com.cracking;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
/**
 *
 *
 */
public class BitManipulationTests {

    /**
     * Answer an instance for the following arguments
     */
    public BitManipulationTests() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Insert bits test
     * 
     */
    @Test
    public void insertBitsTest() {
        
        int                          tempValue;
        int                          tempSource;
        int                          tempStart;
        int                          tempEnd;
        BitManipulationChallenges    tempManipulator = new BitManipulationChallenges();
        int                          tempResult;
        
        
        tempValue = 0x0400;
        tempSource = 0x0013;
        tempStart = 2;
        tempEnd = 6;
        
        tempResult = tempManipulator.insertBits(tempValue, tempSource, tempStart, tempEnd);
        System.out.println("Starting value: " + String.format("%x", tempValue));
        System.out.println("Source value: " + String.format("%x", tempSource));
        System.out.println("Staring bit position: " + tempStart);
        System.out.println("Ending bit position: " + tempEnd);
        System.out.println("Result: " + String.format("%x", tempResult));
        assertTrue("Did not insert bits properly", tempResult == 0x044c);
        
        
    }
    
    /**
     * Binary to string test - her solution
     */
    @Test
    public void binaryToStringTest() {
        
        BitManipulationChallenges    tempManipulator = new BitManipulationChallenges();
        double                       tempHerValue = .72;
        
        System.out.println("Her result: " + tempManipulator.herBinaryToString(tempHerValue));
    }
    
    /**
     * Max bit count flip test
     */
    @Test
    public void maxBitCountFlipTest() {
        
        int                             tempTestValue;
        int                             tempMaxCount;
        BitManipulationChallenges       tempManipulator = new BitManipulationChallenges();
        
        //Test 1
        tempTestValue = 0x00000001;
        tempMaxCount = tempManipulator.getMaxConsecutiveCountOfOneBitsForSingleBitFlip(tempTestValue);
        assertTrue("Test 1 failed", tempMaxCount ==2);
        
        //Test 2
        tempTestValue = 0x0000000A;
        tempMaxCount = tempManipulator.getMaxConsecutiveCountOfOneBitsForSingleBitFlip(tempTestValue);
        assertTrue("Test 2 failed", tempMaxCount ==3);
        
        //Test 3
        tempTestValue = 0x7FFFFFFF;
        tempMaxCount = tempManipulator.getMaxConsecutiveCountOfOneBitsForSingleBitFlip(tempTestValue);
        assertTrue("Test 3 failed", tempMaxCount == 32);
        
        //Test 4
        tempTestValue = 0x77777777;
        tempMaxCount = tempManipulator.getMaxConsecutiveCountOfOneBitsForSingleBitFlip(tempTestValue);
        assertTrue("Test 4 failed", tempMaxCount == 7);
        
        //Test 5
        tempTestValue = 0x00000000;
        tempMaxCount = tempManipulator.getMaxConsecutiveCountOfOneBitsForSingleBitFlip(tempTestValue);
        assertTrue("Test 1 failed", tempMaxCount == 1);
        
        //Test 6
        tempTestValue = 0xcffffff7;
        tempMaxCount = tempManipulator.getMaxConsecutiveCountOfOneBitsForSingleBitFlip(tempTestValue);
        assertTrue("Test 6 failed", tempMaxCount == 28);
        
        
    }
    
    /**
     * Test brute force find closest number with same number of ones
     */
    @Test
    public void bruteForceFindClosestNumberWithSameNumberOfOnesTest() {
        
        int                             tempValue;
        int                             tempResult;
        BitManipulationChallenges       tempManipulator = new BitManipulationChallenges();
        
        //Test1
        tempValue = 10;
        tempResult = tempManipulator.bruteForceFindNumberClosestTo(tempValue, 1);
        assertTrue("Test1", tempResult == 12);
        
        //Test2
        tempValue = 10;
        tempResult = tempManipulator.bruteForceFindNumberClosestTo(tempValue, -1);
        assertTrue("Test2", tempResult == 9);
        
        //Test3
        tempValue = 8;
        tempResult = tempManipulator.bruteForceFindNumberClosestTo(tempValue, 1);
        assertTrue("Test3", tempResult == 16);
        
        
    }
    

    /**
     * Test of the count of the number of bits that need to be changed to move from aValue to another
     */
    @Test
    public void countNumberOfBitsRequiredToChangeTest() {
        
        BitManipulationChallenges       tempManipulator = new BitManipulationChallenges();
        int                             tempNumberOfBits;
        
        //Test1
        tempNumberOfBits = tempManipulator.countBitChangesRequiredToMoveFrom(0x0000001d,  0x0000000f);
        assertTrue("Test1", tempNumberOfBits ==2);
        
        //Test2
        tempNumberOfBits = tempManipulator.countBitChangesRequiredToMoveFrom(0x00000000,  0xffffffff);
        assertTrue("Test2", tempNumberOfBits ==32);
        
    }
    
    /**
     * Test even and odd bits switched - brute force
     */
    @Test
    public void evenAndOddBitsSwitchedTestBruteForce() {
        
        BitManipulationChallenges       tempManipulator = new BitManipulationChallenges();
        int                             tempValue;
        int                             tempResult;
        
        //Test 1
        tempValue = 0x000000aa;
        tempResult = tempManipulator.asEvenAndOddBitsSwitchedBruteForce(tempValue);
        assertTrue("Test 1", tempResult == 0x00000055);
        System.out.println("Initial value: " + String.format("%x",tempValue) + " result: " + String.format("%x",tempResult));
        
        //Test 2
        tempValue = 0x000000cc;
        tempResult = tempManipulator.asEvenAndOddBitsSwitchedBruteForce(tempValue);
        assertTrue("Test 2", tempResult == 0x000000cc);
        System.out.println("Initial value: " + String.format("%x",tempValue) + " result: " + String.format("%x",tempResult));
        
        //Test 3
        tempValue = 0x0000c2a3;
        tempResult = tempManipulator.asEvenAndOddBitsSwitchedBruteForce(tempValue);
        assertTrue("Test 3", tempResult == 0x0000c153);
        System.out.println("Initial value: " + String.format("%x",tempValue) + " result: " + String.format("%x",tempResult));
        
    }
    
    
    /**
     * Test even and odd bits switched
     */
    @Test
    public void evenAndOddBitsSwitchedTest() {
        
        BitManipulationChallenges       tempManipulator = new BitManipulationChallenges();
        int                             tempValue;
        int                             tempResult;
        
        //Test 1
        tempValue = 0x000000aa;
        tempResult = tempManipulator.asEvenAndOddBitsSwitched(tempValue);
        assertTrue("Test 1", tempResult == 0x00000055);
        System.out.println("Initial value: " + String.format("%x",tempValue) + " result: " + String.format("%x",tempResult));
        
        //Test 2
        tempValue = 0x000000cc;
        tempResult = tempManipulator.asEvenAndOddBitsSwitched(tempValue);
        assertTrue("Test 2", tempResult == 0x000000cc);
        System.out.println("Initial value: " + String.format("%x",tempValue) + " result: " + String.format("%x",tempResult));
        
        //Test 3
        tempValue = 0x0000c2a3;
        tempResult = tempManipulator.asEvenAndOddBitsSwitched(tempValue);
        assertTrue("Test 3", tempResult == 0x0000c153);
        System.out.println("Initial value: " + String.format("%x",tempValue) + " result: " + String.format("%x",tempResult));
        
        //Test 4
        tempValue = 0xff00c2a3;
        tempResult = tempManipulator.asEvenAndOddBitsSwitched(tempValue);
        assertTrue("Test 4", tempResult == 0xff00c153);
        System.out.println("Initial value: " + String.format("%x",tempValue) + " result: " + String.format("%x",tempResult));
        
        
    }
    
    /**
     * Draw line test
     */
    @Test
    public void drawLineTest() {
        
        BitManipulationChallenges       tempManipulator = new BitManipulationChallenges();
        byte[][]                        tempDrawingSurface = new byte[64*8][64];
        
        tempManipulator.drawLineOn(tempDrawingSurface, 
                                  -255, 
                                  -255, 
                                   255, 
                                   255);
        
        this.dumpByteArray(tempDrawingSurface);
        
    }
    
    /**
     * Dump byte array
     * @param anArray byte[][]
     */
    protected void dumpByteArray(byte[][] aDrawingSurface) {
        
        for (int i = 0; i < aDrawingSurface.length; i++) {
            
            for (int j = 0; j < aDrawingSurface[0].length; j++) {
                
                System.out.print(String.format("%x", aDrawingSurface[i][j]) + " ");
            }
            
            System.out.println();
            
        }
        
    }
    
    
}
