package com.oracle.rodcut;

import java.util.Map;

/**
 *
 *
 */
public class RodCut {

    private int[] prices;
    
    /**
     * Answer an instance for the following arguments
     */
    public RodCut(int[] aPrices) {
        
        super();
        this.setPrices(aPrices);
        
    }
    
    /**
     * Perform cut
     * @param aRodSize int
     * @param aBuilder StringBuilder
     */
    public int performCut(int aRodSize, 
                          StringBuilder aBuilder,
                          Map<Integer, Integer> aMaxRevenues) {
        
        int                         tempRevenue;
        Integer                     tempValue;
        
        tempValue = aMaxRevenues.get(new Integer(aRodSize));
        if (tempValue != null) {
            
            tempRevenue = tempValue.intValue();
            aBuilder.append(" Looking up rodSize: " + aRodSize + " revenue: " + tempRevenue);
            aBuilder.append(System.getProperty("line.separator"));
            
        }
        else if (aRodSize == 0) {
            
            tempRevenue = 0;
        }
        else {
            
            tempRevenue = 0;
            
            for (int i = 0; i < aRodSize; i++) {
                           
                tempRevenue = Math.max(tempRevenue, 
                                       this.getPrices()[i] + 
                                       this.performCut(aRodSize - 1 - i, aBuilder, aMaxRevenues));
                aBuilder.append(" Computing rodSize: " + aRodSize + " revenue: " + tempRevenue);
                aBuilder.append(System.getProperty("line.separator"));

                
            }
            
            aMaxRevenues.put(new Integer(aRodSize), new Integer(tempRevenue));
            
            
        }
        
        return tempRevenue;
        
    }

    
    /**
     * Perform cut bottom up
     * @param aRodSize int
     * @param aBuilder StringBuilder
     */
    public void performCutBottomUp(int aRodSize, 
                                   StringBuilder aBuilder,
                                   Map<Integer, Integer> aMaxRevenues) {
        
        int                         tempRevenue;
        int                         tempStoredMax;
        

            
        
        for (int i = 0; i < aRodSize; i++) {
          
            tempRevenue = 0;
            for (int j = 0; j < i; j++) {
                
                aBuilder.append(" Computing rodSize: " + aRodSize + "...");
                aBuilder.append(System.getProperty("line.separator"));
                tempStoredMax = (aMaxRevenues.get(i - j - 1) != null) ? aMaxRevenues.get(i - j - 1).intValue() : 0;
                tempRevenue = Math.max(tempRevenue, 
                                       this.getPrices()[j] + tempStoredMax);
                
            }
            
            aMaxRevenues.put(new Integer(i), new Integer(tempRevenue));

            
        }        
        
        this.dumpMaxRevenues(aRodSize, aBuilder, aMaxRevenues);
        
    }

    
    
    /**
     * Dump max revenues for each rod size
     * @param aRodSize
     * @param aBuilder
     * @param aMaxRevenues
     */
    public void dumpMaxRevenues(int aRodSize, 
                                StringBuilder aBuilder,
                                Map<Integer, Integer> aMaxRevenues) {
        
        Integer tempValue;
        
        for (int i = 0; i <= aRodSize; i++) {
            
            tempValue = aMaxRevenues.get(new Integer(i));
            if (tempValue != null) {
                
                aBuilder.append(" Rod Size: " + i + " revenue: " + tempValue);
                aBuilder.append(System.getProperty("line.separator"));
            }
            
        }
        
        
    }

    /**
     * Answer my prices
     * @return int[]
     */
    protected int[] getPrices() {
        return prices;
    }

    /**
     * Set my prices
     * @param prices int[]
     */
    protected void setPrices(int[] prices) {
        this.prices = prices;
    }

    
    
}
