package com.oracle.editdistance;

/**
 *
 *
 */
public class DeleteOperation extends Operation {

    
    //Constants
    public static final String DELETE = "delete";
    
    /**
     * Answer an instance for the following arguments
     * @param aName String
     * @param anOperationCharacter char
     * @param anEditDistance int
     */
    public DeleteOperation(char anOperationCharacter,
                           int anEditDistance) {
        
        super(DELETE, anOperationCharacter, anEditDistance);
    }



}
