package com.oracle.samples;

import java.util.BitSet;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 *
 */
public class BitManipulationOperation {

    BitManipulationOperation() {
        super();
    }
    
    public void handleBitOperation(String anOperationDirective,
                                   BitSet[] tempSets) {
        String          tempOp;
        StringTokenizer tempTokens;
        String          tempArg1;
        String          tempArg2;
        int             tempIndexArg1;
        int             tempIndexArg2;
        
        anOperationDirective = anOperationDirective.trim();
        tempTokens = new StringTokenizer(anOperationDirective, " ");
        tempOp = tempTokens.nextToken();
        tempArg1 = tempTokens.nextToken();
        tempArg2 = tempTokens.nextToken();
        
        //Get first set designator. All other arguments depend on operation
        tempIndexArg1 = Integer.parseInt(tempArg1) - 1;
        
        switch (tempOp) {
                
                case "AND" :
                        tempIndexArg2 = Integer.parseInt(tempArg2) - 1;
                        tempSets[tempIndexArg1].and(tempSets[tempIndexArg2]);
                
                        break;
                
                case "OR" :
                        tempIndexArg2 = Integer.parseInt(tempArg2) - 1;
                        tempSets[tempIndexArg1].or(tempSets[tempIndexArg2]);
                
                        break;
                
                case "XOR" :
                        tempIndexArg2 = Integer.parseInt(tempArg2) - 1;
                        tempSets[tempIndexArg1].xor(tempSets[tempIndexArg2]);
                
                        break;
                
                case "FLIP" :
                        tempIndexArg2 = Integer.parseInt(tempArg2);
                        tempSets[tempIndexArg1].flip(tempIndexArg2);
                
                        break;
                
                case "SET" :
                
                        tempIndexArg2 = Integer.parseInt(tempArg2);
                        tempSets[tempIndexArg1].set(tempIndexArg2);
                        break;
            default:
                        throw new IllegalArgumentException("Unrecognized command");
                
        }

        
    }
    
    public void dumpBitSets(BitSet[] aSets) {
        
        System.out.println(this.bitSetToInt(aSets[0]) + " " + this.bitSetToInt(aSets[1]));
    }
    
    public int bitSetToInt(BitSet bitSet) {
        int bitInteger = 0;

        for (int i = 0; i < 32; i++){
            if (bitSet.get(i)) {
                bitInteger |= (1 << i);
            }
        }
        return bitInteger;
    }
    
    
    public static void main(String[] args) {
        
        Scanner tempScanner = new Scanner(System.in);
        int     tempBitSetSize = tempScanner.nextInt();
        int     tempNumberOfOps = tempScanner.nextInt();
        String  tempOperationDirective;
        BitManipulationOperation    tempBitManipulationOperation = new BitManipulationOperation();
        
        BitSet[] tempSets = {new BitSet(tempBitSetSize), new BitSet(tempBitSetSize)};
        
        tempScanner.nextLine();
        
        for (int i = 0; i < tempNumberOfOps; i++) {
            
            tempOperationDirective = tempScanner.nextLine();
            tempBitManipulationOperation.handleBitOperation(tempOperationDirective, tempSets);
            tempBitManipulationOperation.dumpBitSets(tempSets);
        }
        
        tempScanner.close();
        
    }
}
