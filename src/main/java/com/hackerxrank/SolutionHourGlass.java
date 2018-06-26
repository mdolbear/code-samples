package com.hackerxrank;

/**
 * Class to use for Hacker X depending on what problem I am solving
 *
 */

class HourGlass {
    
    private int centerRow;
    private int centerColumn;
    private int sum;
    
    HourGlass(int aCenterRow, int aCenterColumn) {
        
        this();
        this.setCenterRow(aCenterRow);
        this.setCenterColumn(aCenterColumn);
        this.setSum(0);
    }
    
    HourGlass() {
        
        super();
        this.setSum(Integer.MIN_VALUE);
    }

    /**
     * Answer my centerRow
     * @return int
     */
    public int getCenterRow() {
        return centerRow;
    }

    /**
     * Set my centerRow
     * @param centerRow int
     */
    public void setCenterRow(int centerRow) {
        this.centerRow = centerRow;
    }

    /**
     * Answer my centerColumn
     * @return int
     */
    public int getCenterColumn() {
        return centerColumn;
    }

    /**
     * Set my centerColumn
     * @param centerColumn int
     */
    public void setCenterColumn(int centerColumn) {
        this.centerColumn = centerColumn;
    }

    /**
     * Answer my sum
     * @return int
     */
    public int getSum() {
        return sum;
    }

    /**
     * Set my sum
     * @param sum int
     */
    public void setSum(int sum) {
        this.sum = sum;
    }
    
    
    /**
     * Compute sum for me
     * @param aCenterRow int
     * @param aCenterColumn int
     * @param anArray int[][]
     */
    public int computeSum(int aCenterRow, 
                          int aCenterColumn,
                          int[][] anArray) {
        
        int tempSum = 0;
        int tempCurrentRow;
        
        //Set center
        this.setCenterRow(aCenterRow);
        this.setCenterColumn(aCenterColumn);
        
        //Sum row above center first
        tempCurrentRow = this.getCenterRow()-1;
        for (int j = this.getCenterColumn() - 1; j <= this.getCenterColumn() + 1; j++) {
            
            tempSum += anArray[tempCurrentRow][j];
        }
        
        //Center of hour glass
        tempSum += anArray[this.getCenterRow()][this.getCenterColumn()];
        
        
        //Sum row below center last
        tempCurrentRow = this.getCenterRow()+1;
        for (int j = this.getCenterColumn() - 1; j <= this.getCenterColumn() + 1; j++) {
            
            tempSum += anArray[tempCurrentRow][j];
        }
        
        this.setSum(tempSum);
        
        return tempSum;
    }
    
    /**
     * Answer whether or not I can "fit" inside the current specified array coordinates
     * @param aProposedCenterRow int
     * @param aProposedCenterColumn int
     * @param anArray int[][]
     * @return boolean
     */
    public boolean canFitInside(int aProposedCenterRow, 
                                   int aProposedCenterColumn,
                                   int[][] anArray) {
        
        boolean tempResult = true;
        
        tempResult = tempResult && ((aProposedCenterRow - 1) >= 0) 
                                && ((aProposedCenterRow + 1) < anArray.length);
        
        tempResult = tempResult && ((aProposedCenterColumn - 1 >= 0))
                                && ((aProposedCenterColumn + 1) < anArray[aProposedCenterRow].length);
        
        return tempResult;
                
    }
    
    
}


public class SolutionHourGlass {

    /**
     * Answer a default instance
     */
    public SolutionHourGlass() {
        
        super();
    }
    
    public static void main(String[] args) {
        
        int[][] tempArray = { {1, 1, 1, 0, 0, 0},
                              {0, 1, 0, 0, 0, 0},
                              {1, 1, 1, 0, 0, 0},
                              {0, 0, 2, 4, 4, 0},
                              {0, 0, 0, 2, 0, 0},
                              {0, 0, 1, 2, 4, 0}
                            };
        
        HourGlass   tempCurrentHourGlass;
        HourGlass   tempMaxHourGlass;
        int         tempSum;
        
        
        tempMaxHourGlass = new HourGlass();
        tempCurrentHourGlass = new HourGlass();
        
        for (int i= 0; i < tempArray.length; i++) {
            
            for (int j = 0; j < tempArray[i].length; j++) {
                
                if (tempCurrentHourGlass.canFitInside(i, j, tempArray)) {
                    
                    tempSum = tempCurrentHourGlass.computeSum(i, j, tempArray);
                    if (tempSum > tempMaxHourGlass.getSum()) {
                        
                        tempMaxHourGlass = tempCurrentHourGlass;
                        tempCurrentHourGlass = new HourGlass();
                    }
                    
                    
                    
                }
            }
        }
        
        System.out.println(tempMaxHourGlass.getSum());
        
        
    }
    
}


