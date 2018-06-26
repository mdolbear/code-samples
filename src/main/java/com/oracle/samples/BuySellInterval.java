package com.oracle.samples;

import java.util.Iterator;
import java.util.List;

/**
 *
 *
 */
public class BuySellInterval {

    private int start;
    private int end;
    private int profit;
    
    /**
     * Answer an instance for the following arguments
     */
    public BuySellInterval(int aStart, int anEnd, int aProfit) {
        
        super();
        this.setStart(aStart);
        this.setEnd(anEnd);
        this.setProfit(aProfit);
    }

    /**
     * Answer my start
     * @return int
     */
    protected int getStart() {
        return start;
    }

    /**
     * Set my start
     * @param start int
     */
    protected void setStart(int start) {
        this.start = start;
    }

    /**
     * Answer my end
     * @return int
     */
    protected int getEnd() {
        return end;
    }

    /**
     * Set my end
     * @param end int
     */
    protected void setEnd(int end) {
        this.end = end;
    }

    /**
     * Answer my profit
     * @return int
     */
    protected int getProfit() {
        return profit;
    }

    /**
     * Set my profit
     * @param profit int
     */
    protected void setProfit(int profit) {
        this.profit = profit;
    }
    
    
    /**
     * Overlaps with any
     * @param aList List<Interval>
     */
    public boolean overlapsWithAny(List<BuySellInterval> aList) {
        
        Iterator<BuySellInterval> tempItr;
        BuySellInterval           tempResult = null;
        BuySellInterval           tempCurrent;
        
        tempItr = aList.iterator();
        while (tempItr.hasNext() && tempResult == null) {
            
            tempCurrent = tempItr.next();
            if (this.overlapsWith(tempCurrent)) {
                
                tempResult = tempCurrent;
            }
        }
        
        return tempResult != null;
    }
    /**
     * Overlaps with anotherInterval
     * @param anotherInterval Interval
     */
    public boolean overlapsWith(BuySellInterval anotherInterval) {
        
        return !(this.getStart() > anotherInterval.getEnd()) ||
                (this.getEnd() < anotherInterval.getStart());
    }
    
    /**
     * Answer my string representation
     */
    public String toString() {
        
        StringBuilder    tempBuilder = new StringBuilder();
        
        tempBuilder.append("Start: " + this.getStart());
        tempBuilder.append(" End: " + this.getEnd());
        tempBuilder.append(" Profit: " + this.getProfit());
        
        return tempBuilder.toString();
        
    }

}
