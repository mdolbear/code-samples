package com.oracle.randomizedalg.hiringproblem;

import java.io.Serializable;

public class HiringResult implements Serializable {

    
    private Candidate   bestCandidate;
    private int         numberOfInitialCandidates;
    private int         numberOfTimesComparisonFired;
    private int         permutationIndex;
    
    
    //Constants
    private static final long serialVersionUID = 1L;

    /**
     * Answer an instance for the following arguments
     */
    public HiringResult(Candidate aBestCandidate,
                       int aNumberOfInitialCandidates,
                       int aNumberOfTimesComparisonFired,
                       int aPermutationIndex) {
        
        super();
        this.setBestCandidate(aBestCandidate);
        this.setNumberOfInitialCandidates(aNumberOfInitialCandidates);
        this.setNumberOfTimesComparisonFired(aNumberOfTimesComparisonFired);
        this.setPermutationIndex(aPermutationIndex);
        
    }

    /**
     * Answer my bestCandidate
     * @return Candidate
     */
    public Candidate getBestCandidate() {
        return bestCandidate;
    }

    /**
     * Set my bestCandidate
     * @param bestCandidate Candidate
     */
    protected void setBestCandidate(Candidate bestCandidate) {
        this.bestCandidate = bestCandidate;
    }

    /**
     * Answer my numberOfInitialCandidates
     * @return int
     */
    public int getNumberOfInitialCandidates() {
        return numberOfInitialCandidates;
    }

    /**
     * Set my numberOfInitialCandidates
     * @param numberOfInitialCandidates int
     */
    protected void setNumberOfInitialCandidates(int numberOfInitialCandidates) {
        this.numberOfInitialCandidates = numberOfInitialCandidates;
    }

    /**
     * Answer my numberOfTimesComparisonFired
     * @return int
     */
    public int getNumberOfTimesComparisonFired() {
        return numberOfTimesComparisonFired;
    }

    /**
     * Set my numberOfTimesComparisonFired
     * @param numberOfTimesComparisonFired int
     */
    protected void setNumberOfTimesComparisonFired(
            int numberOfTimesComparisonFired) {
        this.numberOfTimesComparisonFired = numberOfTimesComparisonFired;
    }
    
    
    /**
     * Answer my string representation
     * @return String
     */
    @Override
    public String toString() {
        
        StringBuilder    tempBuffer;
        
        tempBuffer = new StringBuilder();
        tempBuffer.append("Best Candidate: " + this.getBestCandidate().getRank());
        tempBuffer.append(" ");
        tempBuffer.append("Number of initial candidates: " + this.getNumberOfInitialCandidates());
        tempBuffer.append(" ");
        tempBuffer.append("Number of comparision updates: " + this.getNumberOfTimesComparisonFired());
        tempBuffer.append(" ");
        tempBuffer.append("PermutationIndex: " + this.getPermutationIndex());
        
        return tempBuffer.toString();
        
    }

    /**
     * Answer my permutationIndex
     * @return int
     */
    protected int getPermutationIndex() {
        return permutationIndex;
    }

    /**
     * Set my permutationIndex
     * @param permutationIndex int
     */
    protected void setPermutationIndex(int permutationIndex) {
        this.permutationIndex = permutationIndex;
    }

    
}
