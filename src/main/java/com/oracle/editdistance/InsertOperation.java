package com.oracle.editdistance;

/**
 *
 *
 */
public class InsertOperation extends Operation {

    
    //Constants
    public static final String INSERT = "insert";
    
    /**
     * Answer an instance for the following arguments
     * @param aName String
     * @param anOperationCharacter char
     * @param anEditDistance int
     */
    public InsertOperation(char anOperationCharacter,
                           int anEditDistance) {
        
        super(INSERT, anOperationCharacter, anEditDistance); 

    }



}
