package com.cracking.huffmancodes;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 *
 */
public class HuffmanPrefixCodeGeneratorTest {

    /**
     * Answer an instance for the following arguments
     */
    public HuffmanPrefixCodeGeneratorTest() {
       super();
    }
    
    /**
     * Code generation test
     */
    @Test
    public void codeGenerationTest() {
        
        HuffmanPrefixCodeGenerator  tempGenerator;
        String[]                    tempCodeCharacters = {"a", "b", "c", "d", "e", "f"};
        int[]                       tempFrequencies = {45, 13, 12, 16, 9, 5};
        String                      tempResult;
        
        tempGenerator = new HuffmanPrefixCodeGenerator();
        tempGenerator.createCodeMappingFor(tempCodeCharacters, tempFrequencies);
        assertTrue("No code tree created", tempGenerator.getResultingTree() != null);
        tempResult = tempGenerator.dumpResults();
        
        System.out.println("Code GenerationResults:");
        System.out.println();
        System.out.println(tempResult);
        
    }

}
