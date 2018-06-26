package com.oracle.editdistance;

/**
 *
 *
 */
public class ReplaceOperation extends Operation {

    private char characterToReplace;
    
    //Constants
    public static final String REPLACE = "replace";
    
    /**
     * Answer an instance for the following arguments
     * @param aName String
     * @param anOperationCharacter char
     * @param anEditDistance int
     */
    public ReplaceOperation(char anOperationCharacter,
                            int anEditDistance,
                            int aReplaceCharacter) {
        
        super(REPLACE, anOperationCharacter, anEditDistance);
        this.setCharacterToReplace(anOperationCharacter);

    }

    /**
     * Answer my characterToReplace
     * @return char
     */
    protected char getCharacterToReplace() {
        return characterToReplace;
    }

    /**
     * Set my characterToReplace
     * @param characterToReplace char
     */
    protected void setCharacterToReplace(char characterToReplace) {
        this.characterToReplace = characterToReplace;
    }
    
    

}
