package com.oracle.randomizedalg.hiringproblem;

/**
 *
 *
 */
public class Candidate {

    private int rank;
    
    /**
     * Answer an instance for the following arguments
     * @param aRank int
     */
    public Candidate(int aRank) {
        
        super();
        this.setRank(aRank);
        
    }

    /**
     * Answer my rank
     * @return int
     */
    protected int getRank() {
        return rank;
    }

    /**
     * Set my rank
     * @param rank int
     */
    protected void setRank(int rank) {
        this.rank = rank;
    }
    
    
    /**
     * Answer whether or not I am a higher rank than anotherCandidate
     * @param anotherCandidate Candidate
     * @return boolean
     */
    public boolean hasHigherRankThan(Candidate aCandidate) {
        
        return aCandidate.getRank() <= this.getRank();
    }
    
    
}
