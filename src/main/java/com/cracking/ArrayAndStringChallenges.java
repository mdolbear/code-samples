package com.cracking;

import com.oracle.utils.PermutationUtils;

import java.util.*;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 */
public class ArrayAndStringChallenges {

    /**
     * Answer an instance for the following arguments
     */
    public ArrayAndStringChallenges() {
        super();
    }
    
    /**
     * Answer whether aString2 is a aString1
     * @param aString1 String
     * @param aString2 String
     * @return boolean
     */
    public boolean isPermutationOf(String aString1,
                                   String aString2) {
        
        Map<String, Integer>    tempMap1;
        Map<String, Integer>    tempMap2;
        
        
        tempMap1 = new HashMap<String, Integer>();
        tempMap2 = new HashMap<String, Integer>();
        
        this.fillInMapFor(aString1, tempMap1);
        this.fillInMapFor(aString2, tempMap2);
        
        return tempMap1.equals(tempMap2);
        
    }
    
    /**
     * Fill in the character hash map for aString
     * @param aValue String
     * @param aMap Map<String, Integer>
     */
    protected void fillInMapFor(String aValue, Map<String, Integer> aMap) {
        
        
        String  tempCurrentCharacter;
        Integer tempCurrentCounter;
        
        for (int i = 0; i < aValue.length(); i++) {
            
            tempCurrentCharacter = aValue.substring(i,  i + 1);
            
            tempCurrentCounter = aMap.get(tempCurrentCharacter);
            if (tempCurrentCounter == null) {
                
                tempCurrentCounter = new Integer(1);
            }
            else {
                
                tempCurrentCounter = new Integer(tempCurrentCounter.intValue()+1);
            }
            aMap.put(tempCurrentCharacter, tempCurrentCounter);
            
        }
        
    }
    
    /**
     * "Urlify" aValue .. replace spaces with %20
     * @param aValue
     * @return String
     */
    public String urlIfyRegex(String aValue) {
        
        Pattern tempPattern = Pattern.compile("\\b(\\s+)\\b");
        Pattern tempBeginPattern = Pattern.compile("^(\\s+)");
        Pattern tempEndPattern = Pattern.compile("(\\s+)$");
        Matcher tempMatcher;
        String  tempResult;
        
        //Eliminate upfront spaces
        tempMatcher = tempBeginPattern.matcher(aValue);
        tempResult = tempMatcher.replaceAll("");
        
        //Eliminate ending spaces
        tempMatcher = tempEndPattern.matcher(tempResult);
        tempResult = tempMatcher.replaceAll("");
        
        //replace internal spaces with %20
        tempMatcher = tempPattern.matcher(tempResult);
        tempResult = tempMatcher.replaceAll("%20");
        
        
        return tempResult;
    }
    
 
    
    /**
     * Urlify aValue ...replace spaces with %20 without using regex..goal in book was to do this with an array.
     * This version does a trim up front to get rid of trailing and leading spaces
     * @param aValue String
     * @return String
     */
    public String urlIfy(String aValue) {
        
        
        char[]                      tempChars;
        char[]                      tempPer20Chars;
        String                      tempPer20 = "%20";
        
        aValue = aValue.trim();
        
        if (aValue.length() > 0) {
            
            //Initialize arrays
            tempChars = new char[aValue.length()];
            aValue.getChars(0, aValue.length(), tempChars, 0);            
            tempPer20Chars = new char[tempPer20.length()];
            tempPer20.getChars(0, tempPer20.length(), tempPer20Chars, 0);
                
            //Replace interspersed spaces with %20                
            tempChars = this.performReplacement(tempChars, tempPer20Chars);
                               
            
        }
        else {
            
            tempChars = new char[0];
        }
                
        
        return new String(tempChars);
    }

    /**
     * Perform replacement
     * @param aChars
     * @param aPer20Chars
     * @return
     */
    protected char[] performReplacement(char[] aChars, 
                                        char[] aPer20Chars) {
        
        CharacterArrayPosition tempPosition;
        int                         tempStartingWhitespace;
        int                         tempNonWhitespace;
        
        
        tempStartingWhitespace = this.findNextWhitespace(aChars, 0);
        
        while (tempStartingWhitespace < (aChars.length - 1)
                && (tempStartingWhitespace > -1)) {
            
            tempNonWhitespace = this.skipOverWhitespace(aChars, 
                                                        tempStartingWhitespace, 
                                                        true);
            
            if (tempNonWhitespace < aChars.length) {
                
                tempPosition = this.replaceCharacters(aChars, 
                                                      aPer20Chars, 
                                                      tempStartingWhitespace, 
                                                      tempNonWhitespace-1);
                aChars = tempPosition.getChars();
            }
            
            tempStartingWhitespace = this.findNextWhitespace(aChars, 0);
        }
        
        return aChars;
        
    }

    /**
     * Skip over whitespace
     * @param aChars
     * @param aStartingIndex
     * @param aForwardDirection boolean
     * @return int
     */
    protected int skipOverWhitespace(char[] aChars, 
                                     int aStartingIndex,
                                     boolean aForwardDirection) {
        
        char tempCurrentChar;
        int i;
        int tempIncrement;
        
        tempIncrement = (aForwardDirection) ? 1: -1;
               
        tempCurrentChar = aChars[aStartingIndex];
        i = aStartingIndex;
        
        //Skip over whitespace
        while (Character.isWhitespace(tempCurrentChar) 
                && (i < aChars.length) && (i > -1)) {
            
            tempCurrentChar = aChars[i];
            i += tempIncrement;
        }
        
        i = (aForwardDirection) ? i : i + 2;
        
        return i;
    }
    
    /**
     * Find next whitespace
     * @param aChars
     * @param aStartingIndex
     * @return
     */
    protected int findNextWhitespace(char[] aChars, 
                                     int aStartingIndex) {
        
        char tempCurrentChar;
        int i;
        
        
        tempCurrentChar = aChars[aStartingIndex];
        i = aStartingIndex;
        
        while (i < aChars.length &&
                    !Character.isWhitespace(tempCurrentChar)) {
            
            tempCurrentChar = aChars[i++];
        }
        
        i--;
        
        return i;
    }
    
    /**
     * Replace characters from aStartIndex to anEndIndex with aChars
     * @param aChars char[]
     * @param aReplacement char[]
     * @param aStartIndex int
     * @param anEndIndex int
     * @return CharacterArrayPosition
     */
    protected CharacterArrayPosition replaceCharacters(char[] aChars,
                                                       char[] aReplacement,
                                                       int aStartIndex,
                                                       int anEndIndex) {
        
        char[]                  tempResult;
        int                     tempSourceIndex;
        int                     i;
        
            
        tempResult = Arrays.copyOfRange(aChars, 0, aStartIndex);
        tempResult = Arrays.copyOf(tempResult, aReplacement.length + aChars.length - 1);
        
        tempSourceIndex = 0;
        for (i = aStartIndex; i < (aStartIndex + aReplacement.length); i++) {
            
            tempResult[i] = aReplacement[tempSourceIndex++];
        }

        tempSourceIndex = anEndIndex;
        while(tempSourceIndex < aChars.length) {
            
            tempResult[i++] = aChars[tempSourceIndex++];
        }
        
        return new CharacterArrayPosition(tempResult, tempResult.length);
    }
    
    
   /**
    * Answer all permutation that are also palindromes of aValue
    * @param aValue String
    * @return List<String>
    */
    public Set<String> findAllPermutationsThatArePalidromes(String aValue) {
        
        List<String>                tempResults = new ArrayList<String>();
        Iterator<String>            tempItr;
        String                      tempCurrentValue;
        
        (new PermutationUtils()).findAllPermutationsOf(aValue.toLowerCase(), tempResults);
        
        if (!tempResults.isEmpty()) {
            
            tempItr = new ArrayList<String>(tempResults).iterator();
            tempResults.clear();
            
            while (tempItr.hasNext()) {
                
                tempCurrentValue = tempItr.next();
                this.isPermutationIgnoringSpaces(tempResults, tempCurrentValue);
            }
        }
        
        return new HashSet<String>(tempResults);
    }

    /**
     * Answer whether aCurrentValue is a palindrome ignoring any internal spaces
     * @param aResults
     * @param aCurrentValue
     */
    protected void isPermutationIgnoringSpaces(List<String> aResults,
                                               String aCurrentValue) {
        
        String  tempAlteredValue;
        String  tempReverseValue;
        Pattern tempPattern;
        Matcher tempMatcher;
        
        
        tempAlteredValue = new String(aCurrentValue);
        tempPattern = Pattern.compile("\\s+");
        tempMatcher = tempPattern.matcher(tempAlteredValue);
        tempAlteredValue = tempMatcher.replaceAll("");
        
        tempReverseValue = (new StringBuilder()).append(tempAlteredValue).reverse().toString();
        
        if (tempAlteredValue.equals(tempReverseValue)) {
            
            aResults.add(aCurrentValue);
        }
    }
    
    /**
     * Answer the minimum "edit distance" between aString1 and aString2.
     * @param aString1 String
     * @param aString2 String
     * @param aLength1 int
     * @param aLength2 int
     * @return int
     */
    public int computeEditDistanceBetween(String aString1, 
                                          String aString2, 
                                          int aLength1, 
                                          int aLength2) {
        
        int tempResult;
        
        if (aLength1 == 0) {
            
            //First string has nothing left -- so edit distance needs to consider the remaining characters in second string
            tempResult = aLength2;
            
        }
        else if (aLength2 == 0) {
            
          //Second string has nothing left -- so edit distance needs to consider the remaining characters in first string
          tempResult = aLength1;
          
        }
        else if (aString1.charAt(aLength1-1) == aString2.charAt(aLength2 -1)) {
            
            //Two characters are equal, look at the remaining string
            tempResult = 
                    this.computeEditDistanceBetween(aString1, 
                                                    aString2, 
                                                    aLength1 - 1, 
                                                    aLength2 - 1);
        }
        else {
            
            //First operation - considered an "insert" into the first string by removing the last character of the second string
            //Second operation - considered a remove from the first string by removing the last character
            //Third operation - considered a "replace" by basically ignoring the last characters of each string
            tempResult = 1 + 
                    this.getMinimumOf(this.computeEditDistanceBetween(aString1, aString2, aLength1, aLength2-1), 
                                      this.computeEditDistanceBetween(aString1, aString2, aLength1-1, aLength2), 
                                      this.computeEditDistanceBetween(aString1, aString2, aLength1-1, aLength2-1));
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer the minimum of aValue1, aValue2, and aValue3
     * @param aValue1 int
     * @param aValue2 int
     * @param aValue3 int
     * @return int
     */
    protected int getMinimumOf(int aValue1, int aValue2, int aValue3) {
        
        int tempResult;
        
        tempResult = Math.min(aValue1, aValue2);
        tempResult = Math.min(tempResult, aValue3);
        
        return tempResult;
        
    }
    
    
    /**
     * Produce differences between aString1 and aString2. These are non-ordered differences
     * @param aString1 String
     * @param aString2 String
     * @return Set<String>
     */
    protected Set<String> getDifferencesBetween(String aString1, String aString2) {
        
        Set<String> tempString1Set;
        Set<String> tempString2Set;
        
        tempString1Set = this.asSet(aString1);
        tempString2Set = this.asSet(aString2);
        tempString1Set.removeAll(tempString2Set);
        
        return tempString1Set;
    }
    
    /**
     * Answer a string as a set
     * @param aValue String
     * @return Set<String>
     */
    protected Set<String> asSet(String aValue) {
        
        Set<String> tempStringSet  = new HashSet<String>();
        
        String      tempChar;
        
        for (int i = 0; i < aValue.length(); i++) {
            
            tempChar = aValue.substring(i, i+1);
            tempStringSet.add(tempChar);
        }
        
        return tempStringSet;
        
    }
    
    /**
     * Answer a compressed version of aString
     * @param aValue String
     * @return String
     */
    public String compress(String aValue) {
        
        boolean                 tempCompressMode = false;
        char                    tempCurrentChar;
        char                    tempPreviousChar = 0;
        StringBuffer            tempBuffer = new StringBuffer();
        Stack<Character>        tempRepeats;
        String                  tempResult;
        
        if (aValue.length() > 0) {

            tempRepeats = new Stack<Character>();
            for (int i = 0; i < aValue.length(); i++) {
                
                tempCurrentChar = aValue.charAt(i);
                if (tempPreviousChar == tempCurrentChar) {
                    
                    tempRepeats.push(new Character(tempCurrentChar));
                    tempCompressMode = true;
                }
                else {
                    
                    if (tempCompressMode) {
                        
                        this.createCompressionCharacter(tempRepeats, tempBuffer);
                        tempCompressMode = false;
                    }
                    
                    tempBuffer.append(tempCurrentChar);
                }
                
                tempPreviousChar = tempCurrentChar;
                
                
            }
        
        
            if (tempCompressMode) {
                
                this.createCompressionCharacter(tempRepeats, tempBuffer);
                tempCompressMode = false;
                
            }
            
        }
        
        
        tempResult = this.chooseCompressionValueOrOriginalValue(aValue, tempBuffer);
        
        return tempResult;
        
    }

    /**
     * Choose compression value or original, but only if compression value is shorter than the original
     * @param aValue String
     * @param aBuffer StringBuffer
     * @return String
     */
    protected String chooseCompressionValueOrOriginalValue(String aValue,
                                                           StringBuffer aBuffer) {
        
        String tempResult;
        
        tempResult = aBuffer.toString();
        if (tempResult.length() >= aValue.length()) {
            
            tempResult = aValue;
        }
        
        return tempResult;
        
    }

    /**
     * Create and add the compression character based on the repeats in aRepeats
     * @param aRepeats
     * @param aResult
     */
    private void createCompressionCharacter(Stack<Character> aRepeats,
                                            StringBuffer     aResult) {

        int     tempCountOfChar = 1;

        while (!aRepeats.isEmpty()) {
            
            tempCountOfChar++;
            aRepeats.pop();
        }
        
        aResult.append(tempCountOfChar);
        
    }
    
    /**
     * Rotate matrix
     * @param aSourceMatrix int[][]
     * @return int[][]
     */
    public int[][] rotateMatrixCounterClockwise(int[][] aSource) {
        
        ArrayRotationIndexes tempIndexes;                  
        
        this.validateSquareMatrix(aSource);
        
        tempIndexes = new ArrayRotationIndexes(aSource.length);
        
        for (int i = 0; i < tempIndexes.getNumberOfCycles(); i++) {
            
            tempIndexes.incrementIndexesToNewCycle(i);
            while (tempIndexes.hasMoreRotationsInCurrentCycle()) {
                
                this.performSingleRotationCounterClockwise(aSource, tempIndexes);
                System.out.println("Current Array State: ");
                this.dumpArray(aSource);
                
            }
            
            System.out.println("After Cycle: " + i +" Array State: ");
            this.dumpArray(aSource);
            
        }
        
        return aSource;
    }
    
    /**
     * Perform single matrix rotation counter clockwise
     * @param aMatrix int[][]
     * @param anIndexes ArrayRotationIndexes
     */
    protected void performSingleRotationCounterClockwise(int[][] aMatrix,
                                                         ArrayRotationIndexes anIndexes) {
        
        int tempUpperLeftValue;
        
        tempUpperLeftValue = aMatrix[anIndexes.getUpperLeftRowIndex()][anIndexes.getUpperLeftColumnIndex()];
        
        aMatrix[anIndexes.getUpperLeftRowIndex()][anIndexes.getUpperLeftColumnIndex()] = 
                aMatrix[anIndexes.getUpperRightRowIndex()][anIndexes.getUpperRightColumnIndex()];
        
        aMatrix[anIndexes.getUpperRightRowIndex()][anIndexes.getUpperRightColumnIndex()] = 
                aMatrix[anIndexes.getLowerRightRowIndex()][anIndexes.getLowerRightColumnIndex()];
        
        aMatrix[anIndexes.getLowerRightRowIndex()][anIndexes.getLowerRightColumnIndex()] 
                = aMatrix[anIndexes.getLowerLeftRowIndex()][anIndexes.getLowerLeftColumnIndex()];
        
        aMatrix[anIndexes.getLowerLeftRowIndex()][anIndexes.getLowerLeftColumnIndex()] = tempUpperLeftValue;
       
        
        anIndexes.incrementIndexesCounterClockwiseRotation();
        
    }
                                                    
    
    
    /**
     * Dump array
     * @param anArray int[][]
     */
    protected void dumpArray(int[][] anArray) {
        
        for (int i = 0; i < anArray.length; i++) {
            
            for (int j = 0; j < anArray[i].length; j++) {
                
                System.out.print(anArray[i][j] + " ");
            }
            
            System.out.println();
        }
        
        System.out.println();
        
    }
    
    /**
     * Validate whether or not aMatrix is a square matrix
     * @param aMatrix int[][]
     */
    protected void validateSquareMatrix(int[][] aMatrix) throws IllegalArgumentException {
        
        int tempRows;
        
        tempRows = aMatrix.length;
        if (aMatrix[tempRows-1].length != tempRows) {
            
            throw new IllegalArgumentException("Input array is not square");
        }
        
    }


    
    /**
     * Answer whether or not aTarget is a substring of aSource
     * @param aSource String
     * @param aTarget String
     * @param aRotationPosition int
     * @return boolean
     */
    public boolean isSubstring(String aSource, 
                               String aTarget, 
                               int aRotationPosition) {
        
        boolean tempResult;
        
            
        if (aSource.length() != aTarget.length()) {
            
            tempResult = false;
        }
        else if (aRotationPosition >= aSource.length()) {
            
            tempResult = false;
        }
        else if (aSource.equals(aTarget)) {
            
            tempResult = true;
        }
        else {
            
            aRotationPosition++;
            tempResult =
                    this.isSubstring(this.rotateStringAround(aSource , aRotationPosition),
                                     aTarget,
                                     aRotationPosition);
        }
            

        
        
        return tempResult;
        
    }
    
    
    /**
     * Rotate string around anIndex. Answer the result
     * @param anIndex int
     * @param aValue String
     * @return String
     */
    protected String rotateStringAround(String aValue,
                                        int anIndex) {
        
        String   tempResult;
        
        if (anIndex < aValue.length() && (anIndex > 0)) {
            
            tempResult = aValue.substring(anIndex) + aValue.substring(0, anIndex);
        }
        else {
            
            tempResult = new String(aValue);
        }
        
        
        return tempResult;
        
    }
    
    /**
     * Zero rows and columns for a given zero entry in aMatrix
     * @param aMatrix int[][]
     * @return int[][]
     */
    public int[][] zeroRowAndColumnForZeroEntriesIn(int[][] aSource) {
        
        Iterator<ArrayEntry> tempZeroEntries;
        
        tempZeroEntries = this.findZeroValuesIn(aSource).iterator();
        while (tempZeroEntries.hasNext()) {
            
            this.zeroOutRowAndColumn(aSource, tempZeroEntries.next());
            
        }
        
        
        return aSource;
    }
    
    /**
     * Zero out row and column in aSource for anArrayEntry
     * @param aSource int[][]
     * @param anArrayEntry ArrayEntry
     */
    protected void zeroOutRowAndColumn(int[][] aSource,
                                       ArrayEntry anArrayEntry) {
       
        
        for (int tempColumn = 0; tempColumn < aSource[anArrayEntry.getRow()].length; tempColumn++) {
            
            aSource[anArrayEntry.getRow()][tempColumn] = 0;
        }
        
        
        for (int tempRow = 0; tempRow < aSource.length; tempRow++) {
            
            aSource[tempRow][anArrayEntry.getColumn()] = 0;
        }
        
        
        
    }
    
    
    /**
     * Find zero values in aSource
     * @param aSource int[][]
     * @return List<ArrayEntry>
     */
    protected List<ArrayEntry> findZeroValuesIn(int[][] aSource) {
        
        List<ArrayEntry> tempResults = new ArrayList<ArrayEntry>();
        
        for (int i = 0; i < aSource.length; i++) {
            
            for (int j=0; j < aSource[i].length; j++) {
                
                if (aSource[i][j] == 0) {
                    
                    tempResults.add(new ArrayEntry(i,j,aSource[i][j]));
                }
            }
        }
        
        return tempResults;
        
    }
    
    
    
    /**
     * Transpose anArray
     * @param anArray int[][]
     * @return int[][]
     */
    public int[][] transpose(int[][] aMatrix) {
        
        int[][] tempResult;
        
        tempResult = this.createTransposeSizedArrayFrom(aMatrix);
        for (int j = 0; j < aMatrix[0].length; j++) {
            
            for (int i=0; i < aMatrix.length; i++) {
                             
                tempResult[j][i] = aMatrix[i][j];
                
            }
            
        }
        
        return tempResult;

        
    }
    
    /**
     * Create transpose sized array from aSource that is sized properly. If aSource is MxP, then the
     * returned array is PxN
     * @param aSource int[][]
     * @return int[][]
     */
    protected int[][] createTransposeSizedArrayFrom(int[][] aSource) {
        
        return new int[aSource[0].length][aSource.length];
    }
    
    
    /**
     * Multiply aMatrix1 (nxm) with aMatrix2 (mxp) if they are compatible answering
     * a nxp matrix
     * @param aMatrix1 int[][]
     * @param aMatrix2 int[][]
     */
    public int[][] multiply(int[][] aMatrix1, int[][] aMatrix2) {
        
        int[][] tempResult;
        
        this.validateMatrixSizesCompatibleForMultiplication(aMatrix1, aMatrix2);
        tempResult = this.createProductMatrix(aMatrix1, aMatrix2);
        
        for (int i = 0; i < tempResult.length; i++) {
            
            for (int j= 0; j < tempResult[0].length; j++) {
                
                tempResult[i][j] = this.mutiplyRowAndColumn(i, j, aMatrix1, aMatrix2);
                
            }
        }
        
        return tempResult;
    }
    
    /**
     * Create product dimension array from aMatrix1 and aMatrix2
     * @param aMatrix1 int[][]
     * @param aMatrix2 int[][]
     * @return int[][]
     */
    protected int[][] createProductMatrix(int[][] aMatrix1, int[][] aMatrix2) {
        
        return new int[aMatrix1.length][aMatrix2[0].length];
        
    }
    
    /**
     * Multiple row of aMatrix1 with column of aMatrix2
     * @param aRowIdx int
     * @param aColumnIdx int
     * @param aMatrix1 int[][]
     * @param amatrix2 int[][]
     * @return int
     */
    protected int mutiplyRowAndColumn(int aRowIdx, 
                                      int aColumnIdx, 
                                      int[][] aMatrix1, 
                                      int[][] aMatrix2) {
        
        
        int tempResult = 0;
        
        for (int i = 0; i < aMatrix1[0].length; i++) {
            
            tempResult += aMatrix1[aRowIdx][i] * aMatrix2[i][aColumnIdx];
        }
        
        return tempResult;
        
    }
    
    
    /**
     * Validate proper sizes for multiplication
     * @param anMatrix1 int[][]
     * @param aMatrix2 int[][]
     */
    protected void validateMatrixSizesCompatibleForMultiplication(int[][] aMatrix1,
                                                                  int[][] aMatrix2) {
        
        if (aMatrix1[0].length != aMatrix2.length) {
            
            throw new IllegalArgumentException("Matrices not compatibile for multiplication");
            
        }
        
    }

}
