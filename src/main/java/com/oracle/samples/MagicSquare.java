package com.oracle.samples;

/**
 *
 *
 */
public class MagicSquare {

    private int[][] square;
    private int     n;
    
    
    /**
     * Answer an instance for the following arguments
     * @param n int
     */
    public MagicSquare(int aSize) {
        
        super();
        this.setN(aSize);
        this.setSquare(new int[this.getN()][this.getN()]);
        
    }


    /**
     * Answer my square
     * @return int[][]
     */
    public int[][] getSquare() {
        return square;
    }


    /**
     * Set my square
     * @param square int[][]
     */
    protected void setSquare(int[][] square) {
        this.square = square;
    }


    /**
     * Answer my n
     * @return int
     */
    public int getN() {
        return n;
    }


    /**
     * Set my n
     * @param n int
     */
    protected void setN(int n) {
        this.n = n;
    }

    /**
     * Create magic square
     */
    public void create() throws IllegalArgumentException {
        
        int i;
        int j;
        int key;
        
        //Validation
        this.validate();
        
        //Initialization
        this.initializeSquare();
        this.getSquare()[0][(this.getN() - 1 )/2] = 1;
        key = 2;
        i = 0;
        j = (this.getN() - 1)/2;
        
        //Calculations
        this.calculateSquare(i, j, key);
        
        
    }


    /**
     * Calculate the square
     * @param i
     * @param j
     * @param key
     */
    protected void calculateSquare(int i, 
                                   int j, 
                                   int key) {
        
        int k;
        int l;
        
        while (key <= Math.pow(this.getN(), 2)) {
            
            k = this.negativeMod(i);
            l = this.negativeMod(j);
            
            if (this.getSquare()[k][l] != 0) {
                
                //Skip to next row
                i = (i + 1) % this.getN();
                
            }
            else {
                
                i = k;
                j = l;
            }
            
            
            this.getSquare()[i][j] = key;
            key = key + 1;
            
        }
        
    }


    /**
     * Handle negative mod for this algorithm. When we go below zero on the index, we must return to zero
     * @param i
     * @return int
     */
    protected int negativeMod(int aValue) {
        
        int tempResult;
        int tempDiff;
        
        tempDiff = aValue - 1;
        if (tempDiff < 0) {
            
            tempResult = this.getN() + tempDiff;
        }
        else {
            tempResult = tempDiff;
        }
        
        return tempResult;
    }
    
    /**
     * Validate
     *
     */
    protected void validate() throws IllegalArgumentException {
        
        if (this.getN() % 2 == 0) {
            
            throw new IllegalArgumentException("Size of MagicSquare must be odd");
        }
    }
    
    /**
     * Initialize square
     */
    protected void initializeSquare() {
        
        for (int i = 0; i < this.getN(); i++) {
            
            for (int j = 0; j < this.getN(); j++) {
                
                this.getSquare()[i][j] = 0;
            }
            
        }
        
    }
    
    /**
     * Dump square
     */
    public void dumpSquare() {
        
        System.out.println("Current state of MagicSquare: ");
        
        for (int i = 0; i < this.getN(); i++) {
            
            System.out.print("Row: " + i + " :");
            
            for (int j = 0; j < this.getN(); j++) {
                
                System.out.print(" " + this.getSquare()[i][j] + " ");
            }
            
            System.out.println("");
            
        }
        
    }
    
    
}
