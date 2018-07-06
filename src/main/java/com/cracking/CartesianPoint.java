package com.cracking;

/**
 *
 *
 */
public class CartesianPoint {

    private int x;
    private int y;
    
    /**
     * Answer an instance for the following arguments
     */
    public CartesianPoint(int aX, int aY) {
        
        super();
        this.setX(aX);
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
     * Translate to raw coordinates assuming anOrigin of
     * aPoint
     * @param anOrigin CartesianPoint
     */
    public void translateMeTo(CartesianPoint anOrigin) {
        
        this.setX(this.getX() + anOrigin.getX());
        this.setY(this.getX() + anOrigin.getX());
        
    }

}
