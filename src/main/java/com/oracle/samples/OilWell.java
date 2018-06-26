package com.oracle.samples;

/**
 *
 *
 */
public class OilWell {

    private int x;
    private int y;
    
    /**
     * Answer an instance for the following arguments
     */
    public OilWell() {
        super();
    }
    
    /**
     * Answer an instance of me for the following arguments
     * @param anX int
     * @param aY int
     */
    public OilWell(int anX, int aY) {
        
        this();
        this.setX(anX);
        this.setY(aY);
    }

    /**
     * Answer my x
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Set my x
     * @param x int
     */
    protected void setX(int x) {
        this.x = x;
    }

    /**
     * Answer my y
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Set my y
     * @param y int
     */
    protected void setY(int y) {
        this.y = y;
    }
    
    
    /**
     * Compute Y difference with aY
     * @param aY int
     * @return int
     */
    public int computeYDifferenceWith(int aY) {
        
        return aY - this.getY();
    }
    
    
}
