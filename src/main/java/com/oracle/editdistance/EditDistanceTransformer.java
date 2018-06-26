package com.oracle.editdistance;

/**
 *
 *
 */
public class EditDistanceTransformer {

    /**
     * Answer an instance for the following arguments
     */
    public EditDistanceTransformer() {
        super();
    }
    
    /**
     * Answer the minimum "edit distance" between aString1 and aString2.
     * @param aString1 String
     * @param aString2 String
     * @param aLength1 int
     * @param aLength2 int
     * @return Operation
     */
    public Operation computeEditDistanceBetween(String aString1, 
                                                String aString2, 
                                                int aLength1, 
                                                int aLength2,
                                                Operation anOperation) {
        
        Operation tempResult;

        
        if ((aLength1 == 0) && (aLength2 == 0)) {
            
            tempResult = null;
        }
        else if (aLength1 == 0) {
            
            //First string has nothing left -- so edit distance needs to consider the remaining characters in second string
            tempResult = new CopyOperation(aString2.charAt(aLength2 - 1), 1);
            for (int i = aLength2 - 1; i > 0; i--) {
                
                tempResult.addOperationToEnd(new CopyOperation(aString2.charAt(i), 1));
            }
            
            if (anOperation != null) {
                
                anOperation.setPreviousOperation(tempResult);
                tempResult = anOperation;
            }
            
        }
        else if (aLength2 == 0) {
            
          //Second string has nothing left -- so edit distance needs to consider the remaining characters in first string 
          tempResult = new CopyOperation(aString1.charAt(aLength1 - 1), 1);
          for (int i = aLength1 - 1; i > 0; i--) {
              
              tempResult.addOperationToEnd(new CopyOperation(aString2.charAt(i), 1));
          }
          
          if (anOperation != null) {
              
              anOperation.setPreviousOperation(tempResult);
              tempResult = anOperation;
          }
          
          
        }
        else if (aString1.charAt(aLength1-1) == aString2.charAt(aLength2 -1)) {
            
            //Two characters are equal, look at the remaining string
            tempResult = 
                    this.computeEditDistanceBetween(aString1, 
                                                    aString2, 
                                                    aLength1 - 1, 
                                                    aLength2 - 1,
                                                    new CopyOperation(aString1.charAt(aLength1 - 1), 0));
            
            if (anOperation != null) {
                
                anOperation.setPreviousOperation(tempResult);
                tempResult = anOperation;
            }

        }
        else {
            
            //First operation - considered an "insert" into the first string by removing the last character of the second string
            //Second operation - considered a remove from the first string by removing the last character
            //Third operation - considered a "replace" by basically ignoring the last two characters
            tempResult = 
                    this.computeEditDistanceBetween(aString1, 
                                                    aString2, 
                                                    aLength1, 
                                                    aLength2-1,
                                                    new InsertOperation(aString1.charAt(aLength1 - 1), 1))
                    
                        .minimumOf(this.computeEditDistanceBetween(aString1, 
                                                                   aString2, 
                                                                   aLength1-1, 
                                                                   aLength2,
                                                                   new DeleteOperation(aString1.charAt(aLength1 - 1), 1)))
                        
                        .minimumOf(this.computeEditDistanceBetween(aString1, 
                                                                   aString2, 
                                                                   aLength1-1, 
                                                                   aLength2-1,
                                                                   new ReplaceOperation(aString1.charAt(aLength1 - 1), 
                                                                                        1, 
                                                                                        aString2.charAt(aLength2 - 1))));
            
            if (anOperation != null) {
                
                anOperation.setPreviousOperation(tempResult);
                tempResult = anOperation;
            }
            
        }
        
        return tempResult;
        
    }

}
