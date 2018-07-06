package com.cracking;

/**
 *
 *
 */
public class BitManipulationChallenges {

    //Constants
    private static final int BITS_PER_BYTE = 8;

    /**
     * Answer an instance for the following arguments
     */
    public BitManipulationChallenges() {
        super();
    }
    
    /**
     * Bit insertion from aNumber1 to aNumber2 in bit positions aStartingBitPosition through anEndingBitPosition
     * @param aNumber2 int
     * @param aNumber1 int
     * @param aStartingBitPosition int
     * @param anEndingBitPosition int
     */
    public int insertBits(int aNumber1, 
                          int aNumber2,
                          int aStartingBitPosition,
                          int anEndingBitPosition) {
        
        return this.copyBitsInto(aNumber1, 
                                 aNumber2, 
                                 aStartingBitPosition, 
                                 anEndingBitPosition);
    }
    
    /**
     * Clear bit position at aBitPosition
     * @param aValue int
     * @param aBitPosition
     */
    protected int clearBitPosition(int aValue, int aBitPosition) {
        
        return aValue & ~(0x0001 << aBitPosition);
    }
    
    /**
     * Copy bit from aSource into aValue from aBitPosition
     * @param aValue
     * @param aSource
     * @param aBitPosition
     * @return
     */
    protected int copyBitValue(int aValue, 
                               int aSource, 
                               int aBitPosition) {
        
        int tempBitMask;
        
        tempBitMask =  0x00000001 << aBitPosition;
        tempBitMask = tempBitMask & aSource;
        aValue = aValue | aSource;
        
        return aValue;
        
    }
    
    
    /**
     * Copy bits into aValue from aSource staring from aStartingBitPosition and
     * ending at anEndingBitPosition
     * @param aValue int
     * @param aSource int
     * @param aStaringBitPosition int
     * @param anEndingBitPosition
     * @return int
     */
    protected int copyBitsInto(int aValue, 
                               int aSource, 
                               int aStartingBitPosition, 
                               int anEndingBitPosition) {
        
        int tempShiftedBits;
        
        tempShiftedBits = aSource << aStartingBitPosition;
        
        for (int i = aStartingBitPosition; i < anEndingBitPosition+1; i++) {
            
            aValue = this.clearBitPosition(aValue, i);
            aValue = this.copyBitValue(aValue, tempShiftedBits, i);
            
        }
        
        return aValue;
        
    }
    
    
    /**
     * Her version of binary to string
     * @param aValue double
     * @return String
     */
    public String herBinaryToString(double aValue) {
        
        StringBuilder binary = new StringBuilder();
        
        binary.append(".");
        
        while (aValue > 0) {
            
            double r = aValue*2;
            if ( r >= 1) {
                
                binary.append(1);
                aValue = r - 1;
            }
            else {
                binary.append(0);
                aValue = r;
            }
        }
        
        return binary.toString();
    }


    /**
     * Answer the max consecutive count of of 1 bits given a single bit flip
     * @param aValue int
     * @return int
     */
    public int getMaxConsecutiveCountOfOneBitsForSingleBitFlip(int aValue) {
        
        int tempResult;
        int tempCurrentCount;
        
        tempResult = this.getConsecutiveCountOfOneBits(aValue);
        for (int i = 0; i < 32; i ++) {
            
            if (!this.isOneBitInPosition(aValue, i)) {
                
                tempCurrentCount = this.getConsecutiveCountOfOneBits(this.setBit(aValue, i));
                tempResult = Math.max(tempCurrentCount, tempResult);
            }
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer the number of 1 bits for aValue
     * @param aValue int
     */
    protected int getConsecutiveCountOfOneBits(int aValue) {
        
        int tempValue = 0;
        int tempMaxConsecutiveCount = 0;
        
        for (int i = 0; i < 32; i++) {
            
            if (this.isOneBitInPosition(aValue, i)) {
                
                tempValue++;
                tempMaxConsecutiveCount = Math.max(tempMaxConsecutiveCount, tempValue);
            }
            else {
                
                tempValue = 0;
            }
        }
        
        return tempMaxConsecutiveCount;
        
    }

    /**
     * Answer whether there is a 1 bit in a aBitPosition
     * @param aValue
     * @param aBitPosition
     * @return boolean
     */
    protected boolean isOneBitInPosition(int aValue, int aBitPosition) {
        
        return ((aValue >> aBitPosition) & 0x00000001) == 0x00000001;
        
    }
    
    /**
     * Set bit in aBitPosition. Answer the value with the bit set
     * @param aBitPosition int
     * @param aValue int
     * @return int
     */
    protected int setBit(int aValue, int aBitPosition) {
        
        return aValue | (0x00000001 << aBitPosition);
    }
    
   
    /**
     * Brute force version of closest number to aValue with the same number of ones
     * @param aValue int
     * @param anIncrement int
     * @return int
     */
    public int bruteForceFindNumberClosestTo(int aValue, int anIncrement) {
        
        int tempResult = 0;
        int tempCurrent;
        int tempNumberOfOnes;
        
        tempNumberOfOnes = this.getTotalCountOfOneBits(aValue);
        tempCurrent = aValue + anIncrement;
        
        while (tempResult == 0 && 
                    (tempCurrent < Integer.MAX_VALUE && tempCurrent > Integer.MIN_VALUE)) {
            
            if (this.getTotalCountOfOneBits(tempCurrent) == tempNumberOfOnes) {
                
                tempResult = tempCurrent;
            }
            else {
                
                tempCurrent += anIncrement;
            }
            
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer the number of 1 bits for aValue
     * @param aValue int
     */
    protected int getTotalCountOfOneBits(int aValue) {
        
        int tempValue = 0;
        int tempMaxConsecutiveCount = 0;
        
        for (int i = 0; i < 32; i++) {
            
            if (this.isOneBitInPosition(aValue, i)) {
                
                tempValue++;
                tempMaxConsecutiveCount = Math.max(tempMaxConsecutiveCount, tempValue);
            }

        }
        
        return tempMaxConsecutiveCount;
        
    }
    
    /**
     * Count bit changes required to move from aValue1 to aValue2
     * @param aValue1 int
     * @param aValue2 int
     * @return int
     */
    public int countBitChangesRequiredToMoveFrom(int aValue1, int aValue2) {
        
        int tempNumberOfBitsRequiredToBeChanged = 0;
        int tempXoredValue;
        
        tempXoredValue = aValue1 ^ aValue2;
        tempNumberOfBitsRequiredToBeChanged = this.getTotalCountOfOneBits(tempXoredValue);
        
        return tempNumberOfBitsRequiredToBeChanged;
        
    }
    
    /**
     * Bit switcher. This method switches even and odd bits for aValue and returns the result
     * @param aValue int
     * @return int
     */
    public int asEvenAndOddBitsSwitchedBruteForce(int aValue) {
        
        int tempResult = 0;
        int tempMask;
        
        for (int i = 0; i < 32; i++) {
            
            tempMask = (0x00000001 << i) & aValue;
            tempMask = (i % 2 == 0) ? tempMask << 1 : tempMask >> 1;

            tempResult = tempResult | tempMask;
            
        }
        
        return tempResult;
        
    }
    
    
    /**
     * Bit switcher. This method switches even and odd bits for aValue and returns the result
     * @param aValue int
     * @return int
     */
    public int asEvenAndOddBitsSwitched(int aValue) {
        
        int tempEvenMask;
        int tempEvenBits = 0x55555555;
        int tempOddMask;
        int tempOddBits = 0xaaaaaaaa;
        
        //Mask off even bits and shift them up by 1 
        tempEvenMask = (aValue & tempEvenBits) << 1;
        
        //Mask off odd bits and shift them down by 1
        tempOddMask = (aValue & tempOddBits) >> 1;
        
        
        return tempOddMask | tempEvenMask; //the or of the odd and even bits shifted properly
        
    }
    
    /**
     * Draw line on aCartisian from (x1,y1) to (x2, y2)
     * @aCartisian byte[][]
     * @param anX1 int
     * @param aY1 int
     * @param anX2 int
     * @param aY2 int
     */
    public void drawLineOn(byte[][] aCartesian,
                           int aX1,
                           int aY1,
                           int aX2,
                           int aY2) {
        
        int tempSlope;
        int tempYIntercept;
        int tempCurrentX;
        int tempCurrentY;
        
        //Validate points
//        this.validatePointFitsWithin(aCartesian, aX1, aY1);
//        this.validatePointFitsWithin(aCartesian, aX2, aY2);
        
        //Plot line
        tempSlope = this.computeSlope(aX1, aY1, aX2, aY2);
        tempYIntercept = this.computeYIntercept(tempSlope, aX2, aY2);
        tempCurrentX = aX1;
        tempCurrentY = tempSlope*tempCurrentX + tempYIntercept;
        
        while (tempCurrentX < aX2 &&
                tempCurrentY < aY2) {
            
            this.setBitPointIn(aCartesian, new CartesianPoint(tempCurrentX, tempCurrentY));
            tempCurrentX += 1;
            tempCurrentY = tempSlope*tempCurrentX + tempYIntercept;
            
        }
        
        
        
    }
    
    /**
     * Validate whether aX and aY fit within aCartesian
     * @param aCartesian byte[][]
     * @param aX int
     * @param aY int
     * 
     */
    public void validatePointFitsWithin(byte[][] aCartesian,  
                                        int aX,
                                        int aY) {
        
        CartesianPoint  tempOrigin;
        CartesianPoint  tempPotentialPoint;
        
        tempOrigin = this.getOriginOf(aCartesian);
        tempPotentialPoint = new CartesianPoint(aX, aY);
        tempPotentialPoint.translateMeTo(tempOrigin);
        
        if ((tempPotentialPoint.getY() < 0 || tempPotentialPoint.getY() >= aCartesian.length*BITS_PER_BYTE)
                ||
                (tempPotentialPoint.getX() < 0 || tempPotentialPoint.getX() >= aCartesian[0].length*BITS_PER_BYTE)) {
            
            throw new IllegalArgumentException("Point x: " + aX + " y: " + aY + " is invalid for this cartesian");
            
        }
    }
    
    /**
     * Compute slope
     * 
     * @param anX1 int
     * @param aY1 int
     * @param anX2 int
     * @param aY2 int
     * @return int
     */
    protected int computeSlope(int aX1,
                               int aY1,
                               int aX2,
                               int aY2) {
        
        return (aY2 - aY1)/(aX2 - aX1);
    }
    
    /**
     * Compute y intercept
     * @param aSlope int
     * @param aX int
     * @param aY int
     * @return int
     */
    protected int computeYIntercept(int aSlope,
                                    int aX,
                                    int aY) {
        
        return (aY - aSlope*aX);
    }
    
    /**
     * Compute an origin for aCartesian. Based on the size
     * of aCartesian in bytes and bits, answer the raw point
     * which should be the origin
     */
    protected CartesianPoint getOriginOf(byte[][] aCartesian) {
        
        int tempYSize;
        int tempXSize;
        
        tempYSize = aCartesian.length;
        tempXSize = aCartesian[0].length * BITS_PER_BYTE; //Assumes uniform array
        
        return new CartesianPoint(tempXSize/2, tempYSize/2);
    }
    
    /**
     * Set bit point in aCartesian for aPoint
     * @param aCartesian byte[][]
     */
    protected void setBitPointIn(byte[][] aCartesian,
                                 CartesianPoint aPoint) {
        
        int tempBitNumber;
        int tempRowByteIndex;
        int tempColumnByteIndex;
        
        //Translate point to absolute coordinates of the array
        aPoint.translateMeTo(this.getOriginOf(aCartesian));
        
        //Compute indexes
        tempRowByteIndex = aPoint.getY()/BITS_PER_BYTE;
        tempColumnByteIndex = aPoint.getX()/BITS_PER_BYTE;
        tempBitNumber = aPoint.getX() % BITS_PER_BYTE;
        
        //Set bit
        aCartesian[tempRowByteIndex][tempColumnByteIndex] = 
                (byte)(aCartesian[tempRowByteIndex][tempColumnByteIndex] | (0x01 << tempBitNumber));
        
    }
    
    
}
