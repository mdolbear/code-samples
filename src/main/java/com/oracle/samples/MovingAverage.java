package com.oracle.samples;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class MovingAverage {

    private CircularQueue<Double> dataPoints;
    private int windowSize;
    
    
    /**
     * Answer an instance for the following arguments
     */
    public MovingAverage(int aWindowSize) {
        
        super();
        this.setWindowSize(aWindowSize);
        this.setDataPoints(new CircularQueue<Double>(aWindowSize,
                                                    null));
    }


    /**
     * Answer my dataPoints
     * @return CircularQueue<Double>
     */
    protected CircularQueue<Double> getDataPoints() {
        return dataPoints;
    }


    /**
     * Set my dataPoints
     * @param dataPoints <Double>
     */
    protected void setDataPoints(CircularQueue<Double> dataPoints) {
        this.dataPoints = dataPoints;
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
     * Compute moving average 2
     * @param aValue D
     */
    public double computeMovingAverage(double aValue) {
        
        Double[]    tempDataPoints;
        double      tempSum = 0.0;
        double      tempResult = 0.0;
        
        this.getDataPoints().putIgnoreFull(new Double(aValue));
        tempDataPoints = this.getCurrentDataPoints();
        
        for (int i = 0; i < tempDataPoints.length; i++ )  {
                        
            tempSum += tempDataPoints[i].doubleValue();
         }

        
        tempResult = tempSum/tempDataPoints.length;
        
        return tempResult;
        
    }
    
    /**
     * Answer an array of data points based on the current window and what is currently
     * in my queue
     * @return Double[]
     */
    protected Double[] getCurrentDataPoints() {
        
        int             tempExpectedNumberOfElements;
        List<Double>    tempResult = new ArrayList<Double>();
        Double          tempCurrentElement;
        int             tempNumberOfElements = 0;
        
        tempExpectedNumberOfElements = this.getWindowSize();
        tempCurrentElement = this.getDataPoints().peek(tempNumberOfElements+1);
        
        while (tempCurrentElement != null && tempExpectedNumberOfElements > 0) {
            
            tempResult.add(tempCurrentElement);
            tempNumberOfElements++;
            tempCurrentElement = this.getDataPoints().peek(tempNumberOfElements+1);
            tempExpectedNumberOfElements--;
        }
        
        return tempResult.toArray(new Double[tempNumberOfElements]);
        
    }
    
    
}
