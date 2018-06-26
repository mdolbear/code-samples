package com.oracle.samples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 */
public class Pipeline {

    private List<OilWell> wells;
    private int y;
    
    /**
     * Answer an instance for the following arguments
     */
    public Pipeline() {
        
        super();
        this.setWells(new ArrayList<OilWell>());
    }

    
    /**
     * Add anOilWell to me
     * @param aWell OilWell
     */
    public void addWell(OilWell aWell) {
        
        this.getWells().add(aWell);
    }
    
    
    /**
     * Answer my wells
     * @return List<OilWell>
     */
    protected List<OilWell> getWells() {
        return wells;
    }

    /**
     * Set my wells
     * @param wells List<OilWell>
     */
    protected void setWells(List<OilWell> wells) {
        this.wells = wells;
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
    public void setY(int y) {
        this.y = y;
    }
  
    
    /**
     * Optimize pipeline position. Answer the y coordinate of the pipeline that does this
     * @return int
     */
    public int optimizePipelinePosition() {
        
        int     tempMinimumY;
        int     tempMaximumY;
        int     tempMinY = 0;
        int     tempMinimumSpurDistance;
        int     tempCurrentSpurDistance;
        
        tempMinimumY = this.getMinimumYCoordinateOilWell().getY();
        tempMaximumY = this.getMaximumYCoordinateOilWell().getY();
        tempMinimumSpurDistance = Integer.MAX_VALUE;
        
        for (int tempWellYPosition = tempMinimumY; tempWellYPosition <= tempMaximumY; tempWellYPosition++) {
            
            this.setY(tempWellYPosition);
            tempCurrentSpurDistance = this.getTotalSpurLength();
            
            if (tempMinimumSpurDistance > tempCurrentSpurDistance) {
                
                tempMinY = tempWellYPosition;
                tempMinimumSpurDistance = tempCurrentSpurDistance;
            }
        }
        
        System.out.println("Minimum total spur distance: " 
                            + tempMinimumSpurDistance 
                            + " y position of pipeline: " + tempMinY);
        
        return tempMinY;
        
    }
    
    
    /**
     * Compute total spur length for all wells given my y
     * @return int
     */
    protected int getTotalSpurLength() {
        
        int tempResult = 0;
        
        for (OilWell aWell: this.getWells()) {
            
            tempResult += Math.abs(this.getY() - aWell.getY());
        }
        
        return tempResult;
        
    }
    
    
    
    /**
     * Answer my well that has a minimum absolute y or null
     * @return OilWell
     */
    protected OilWell getMinimumYCoordinateOilWell() {
        
        Optional<OilWell> tempWell;
        
        tempWell = this.getWells().stream().min(this.getYComparator());
        return tempWell.get();
        
    }
    
    /**
     * Answer my well that has a maximum absolute y or null
     * @return OilWell
     */
    protected OilWell getMaximumYCoordinateOilWell() {
        
        Optional<OilWell> tempWell;
        
        tempWell = this.getWells().stream().max(this.getYComparator());
        return tempWell.get();
        
    }
    
    
    
    /**
     * Answer my y comparator oil wells
     * @return Comparator<OilWell>
     */
    protected Comparator<OilWell> getYComparator() {
        
        return (o1, o2) -> Integer.compare(o1.getY(), o2.getX());
        
    }
    
    
}
