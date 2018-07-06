package com.cracking.huffmancodes;

import java.io.Serializable;

/**
 *
 *
 */
public class CodeElement implements Serializable {

    private String codeCharacter;
    private int appearanceFrequency;
    private String generatedCode;
    
    //Constants
    private static final long serialVersionUID = 1L;
    
    /**
     * Answer an instance for the following arguments
     * @param aCodeCharacter
     * @param anAppearanceFrequency
     */
    public CodeElement(String aCodeCharacter,
                       int anAppearanceFrequency) {
        
        super();
        this.setCodeCharacter(aCodeCharacter);
        this.setAppearanceFrequency(anAppearanceFrequency);
    }

    /**
     * Answer my codeCharacter
     * @return String
     */
    public String getCodeCharacter() {
        return codeCharacter;
    }

    /**
     * Set my codeCharacter
     * @param codeCharacter String
     */
    public void setCodeCharacter(String codeCharacter) {
        this.codeCharacter = codeCharacter;
    }

    /**
     * Answer my appearanceFrequency
     * @return int
     */
    public int getAppearanceFrequency() {
        return appearanceFrequency;
    }

    /**
     * Set my appearanceFrequency
     * @param appearanceFrequency int
     */
    public void setAppearanceFrequency(int appearanceFrequency) {
        this.appearanceFrequency = appearanceFrequency;
    }

    /**
     * Answer my generatedCode
     * @return String
     */
    public String getGeneratedCode() {
        return generatedCode;
    }

    /**
     * Set my generatedCode
     * @param generatedCode String
     */
    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }
    
    /**
     * Answer a string representation of myself
     * @return String
     */
    @Override
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempBuilder.append("codeCharacter: ");
        tempBuilder.append((this.getCodeCharacter()) != null ? this.getCodeCharacter() : "null");
        
        tempBuilder.append(" appearanceFrequency: ");
        tempBuilder.append(this.getAppearanceFrequency());
        
        tempBuilder.append(" generatedCode: ");
        tempBuilder.append((this.getGeneratedCode()) != null ? this.getGeneratedCode() : "null");
        
        return tempBuilder.toString();
    }

}
