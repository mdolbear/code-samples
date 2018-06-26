package com.oracle.editdistance;

/**
 *
 *
 */
public class CopyOperation extends Operation {

    
    //Constants
    public static final String COPY = "copy";
    
    /**
     * Answer an instance for the following arguments
     * @param anOperationCharacter char
     * @param anEditDistance int
     */
    public CopyOperation(char anOperationCharacter,
                        int anEditDistance) {
        
        super(COPY, anOperationCharacter, anEditDistance);
       
    }

}
