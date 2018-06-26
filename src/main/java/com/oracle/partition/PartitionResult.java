package com.oracle.partition;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class PartitionResult<T> {

    private boolean successful;
    private List<T> set1;
    private List<T> set2;
    private int setSumDifference;
    
    /**
     * Answer an instance for the following arguments
     * @param wasSuccessful boolean
     */
    public PartitionResult(boolean wasSuccessful) {
        
        super();
        this.setSuccessful(wasSuccessful);
        this.setSet1(new ArrayList<T>());
        this.setSet2(new ArrayList<T>());
        
    }

    /**
     * Answer my successful
     * @return boolean
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Set my successful
     * @param successful boolean
     */
    protected void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    /**
     * Answer my set1
     * @return List<T>
     */
    public List<T> getSet1() {
        return set1;
    }

    /**
     * Set my set1
     * @param set1 List<T>
     */
    public void setSet1(List<T> set1) {
        this.set1 = set1;
    }

    /**
     * Answer my set2
     * @return List<T>
     */
    public List<T> getSet2() {
        return set2;
    }

    /**
     * Set my set2
     * @param set2 List<T>
     */
    public void setSet2(List<T> set2) {
        this.set2 = set2;
    }

    /**
     * Answer my setSumDifference
     * @return int
     */
    public int getSetSumDifference() {
        return setSumDifference;
    }

    /**
     * Set my setSumDifference
     * @param setSumDifference int
     */
    public void setSetSumDifference(int setSumDifference) {
        this.setSumDifference = setSumDifference;
    }
    
    

}
