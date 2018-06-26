package com.oracle.samples;

import java.util.LinkedList;

/**
 *
 *
 */
public class MovingAverageWithQueue {

    private LinkedList<Double> queue;
    private int windowSize;
    private double average;
    
    
    /**
     * Answer an instance for the following arguments
     */
    public MovingAverageWithQueue(int aWindowSize) {
        
        super();
        this.setWindowSize(aWindowSize);
        this.setQueue(new LinkedList<Double>());
    }


    /**
     * Answer my queue
     * @return LinkedList<Double>
     */
    protected LinkedList<Double> getQueue() {
        return queue;
    }


    /**
     * Set my queue
     * @param queue LinkedList<Double>
     */
    protected void setQueue(LinkedList<Double> queue) {
        this.queue = queue;
    }


    /**
     * Answer my windowSize
     * @return int
     */
    protected int getWindowSize() {
        return windowSize;
    }


    /**
     * Set my windowSize
     * @param windowSize int
     */
    protected void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }
    
    /**
     * Compute my average
     * @param aValue double
     */
    public double computeMovingAverage(double aValue) {
        
        double  tempResult;
        
        if (this.getQueue().size() < this.getWindowSize()) {
            
            tempResult = this.handleForQueueSizeLessThanWindowSize(aValue);
            
        }
        else {
            
            tempResult = this.handleWindowSizeAndQueueSizeSame(aValue);
           
        }
        
        return tempResult;
        
        
        
    }


    /**
     * Handle when the window size and the queue size are the same
     * @param aValue
     * @return double
     */
    protected double handleWindowSizeAndQueueSizeSame(double aValue) {
        
        double tempResult;
        Double  tempFront;
        Double  tempBack;
        
        tempFront = this.getQueue().poll();
        tempBack = new Double(aValue);
        this.getQueue().offer(tempBack);
        
        tempResult = this.getAverage() + 
                    tempBack.doubleValue()/this.getWindowSize() -
                    tempFront.doubleValue()/this.getWindowSize();
        this.setAverage(tempResult);
        
        return tempResult;
        
    }


    /**
     * Handle for queue less than window size
     * @param aValue
     * @param tempSum
     * @return double
     */
    protected double handleForQueueSizeLessThanWindowSize(double aValue) {
        
        double tempResult;
        Double  tempSum = new Double(0.0);
        
        this.getQueue().offer(new Double(aValue));
        
//        for (int i = 0; i < this.getQueue().size(); i++) {
//            
//            tempSum = tempSum + this.getQueue().get(i);
//            
//        }
// The stream code below replaces the for loop in this comment
        
        tempSum = this.getQueue().stream().reduce(new Double(0.0), (a,b)->(a+b));
        
        tempResult = tempSum.doubleValue()/this.getQueue().size();
        this.setAverage(tempResult);
        
        return tempResult;
        
    }


    /**
     * Answer my average
     * @return double
     */
    protected double getAverage() {
        return average;
    }


    /**
     * Set my average
     * @param average double
     */
    protected void setAverage(double average) {
        this.average = average;
    }
    
    

}
