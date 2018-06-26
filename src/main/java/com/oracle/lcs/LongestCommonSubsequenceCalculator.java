package com.oracle.lcs;

/**
 *
 *
 */
public class LongestCommonSubsequenceCalculator {

    private int[][] lcsCount;
    private String[][] lcsPath;
    
    //Constants
    protected static final String UP = "^";
    protected static final String SIDE = "<";
    protected static final String HIT = "X";
    
    /**
     * Answer an instance for the following arguments
     */
    public LongestCommonSubsequenceCalculator() {
       super();
    }
    
    /**
     * Find lcs length
     * @param aFirstString String
     * @param aSecondString String
     */
    public StringBuilder findLongestCommonSubsequence(String aFirstString, 
                                                      String aSecondString) {
        
        char[]          tempFirstSequence;
        char[]          tempSecondSequence;
        int             m;
        int             n;
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempFirstSequence = aFirstString.toCharArray();
        tempSecondSequence = aSecondString.toCharArray();
        
        m = tempFirstSequence.length;
        n = tempSecondSequence.length;
        this.initializeArrays(m, 
                              n);
        
        for (int i = 0; i < m; i++) {
            
            for (int j = 0; j <n; j++) {
                
                if (tempFirstSequence[i] == tempSecondSequence[j]) {
                    
                    this.getLcsCount()[i+1][j+1] = 
                            this.getLcsCount()[i][j] + 1;
                    this.getLcsPath()[i+1][j+1] = HIT;
                    
                }
                else if (this.getLcsCount()[i][j+1] >= 
                            this.getLcsCount()[i+1][j]) {
                    
                    this.getLcsCount()[i+1][j+1] = 
                            this.getLcsCount()[i][j+1];
                    this.getLcsPath()[i+1][j+1] = UP;
                    
                }
                else {
                    
                    this.getLcsCount()[i+1][j+1] = 
                            this.getLcsCount()[i+1][j];
                    this.getLcsPath()[i+1][j+1] = SIDE;
                    
                }
                
            }
            
        }
        
        
        this.dumpSequence(tempBuilder,  
                          this.getLcsPath(), 
                          tempFirstSequence, 
                          m, 
                          n);
        
        return tempBuilder;
        
    }
    
    /**
     * Dump sequence
     * @param aBuilder StringBuilder
     * @param aPathArray String[][]
     * @param aFirstSequence char[]
     * @param i int
     * @param j int
     */
    protected void dumpSequence(StringBuilder aBuilder,
                                String[][] aPathArray,
                                char[] aFirstSequence,
                                int i,
                                int j) {
        
        if ( i != 0 && j != 0) {
            
            if (aPathArray[i][j] != null &&
                    HIT.equals(new String(aPathArray[i][j]))) {
                
                this.dumpSequence(aBuilder, aPathArray, aFirstSequence, i-1, j-1);
                aBuilder.append(aFirstSequence[i-1]);
                
            }
            else if (aPathArray[i][j] != null &&
                        UP.equals(new String(aPathArray[i][j]))) {
                
                this.dumpSequence(aBuilder, aPathArray, aFirstSequence, i-1, j);
            }
            else {
                
                this.dumpSequence(aBuilder, aPathArray, aFirstSequence, i, j-1);
            }
        }
        
    }
    
    /**
     * Initialize arrays based on string length
     * @param aFirstStringLength int
     * @param aSecondStringLength int
     */
    protected void initializeArrays(int aFirstStringLength, int aSecondStringLength) {
        
        String[][] tempPathArray;
        int[][] tempCountArray;
        
        tempPathArray = new String[aFirstStringLength+1][aSecondStringLength+1];
        tempCountArray = new int[aFirstStringLength+1][aSecondStringLength+1];
        
        this.setLcsPath(tempPathArray);
        this.setLcsCount(tempCountArray);
        
    }

    /**
     * Answer my lcsCount
     * @return int[][]
     */
    protected int[][] getLcsCount() {
        return lcsCount;
    }

    /**
     * Set my lcsCount
     * @param lcsCount int[][]
     */
    protected void setLcsCount(int[][] lcsCount) {
        this.lcsCount = lcsCount;
    }

    /**
     * Answer my lcsPath
     * @return String[][]
     */
    protected String[][] getLcsPath() {
        return lcsPath;
    }

    /**
     * Set my lcsPath
     * @param lcsPath String[][]
     */
    protected void setLcsPath(String[][] lcsPath) {
        this.lcsPath = lcsPath;
    }
    
    

}
