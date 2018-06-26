package com.cracking;

import java.io.Serializable;

/**
 *
 *
 */
public class CharacterArrayPosition implements Serializable {

    private int index;
    private char[] chars;
    
    //Constants
    private static final long serialVersionUID = 1L;
    
    /**
     * Answer an instance for the following arguments
     */
    public CharacterArrayPosition(char[] anArray,
                                  int anIndex) {
        super();
        this.setChars(anArray);
        this.setIndex(anIndex);
        
    }

    /**
     * Answer my index
     * @return int
     */
    public int getIndex() {
        return index;
    }

    /**
     * Set my index
     * @param index int
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Answer my chars
     * @return char[]
     */
    public char[] getChars() {
        return chars;
    }

    /**
     * Set my chars
     * @param chars char[]
     */
    public void setChars(char[] chars) {
        this.chars = chars;
    }
    
    

}
