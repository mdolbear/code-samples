package com.oracle.randomizedalg.hiringproblem;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 *
 *
 */
public class HiringProblemTest {

    /**
     * Answer an instance for the following arguments
     */
    public HiringProblemTest() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Randomized index list based on permutation test. We hit a limit on this based on the number of permutations of a given index
     * array that are returned.
     */
    @Test
    public void hireCandidateUsingRandomizedIndexList() {
        
        HiringManager   tempManager;
        int             tempNumberOfTrials;
        HiringResult    tempResult;
        List<Candidate> tempCandidates;
        int             tempMaxNumberOfCompares = 0;
        double          tempTotalNumberOfCompares = 0;
        
        tempManager = new HiringManager();
        tempNumberOfTrials = 10;
        tempCandidates   = this.createRankedCandidates();
        for (int i = 0; i < tempNumberOfTrials; i++) {
            
            tempResult = tempManager.hireCandidateWithRandomSelectionPermutation(tempCandidates);
            assertTrue("Best candidate not picked", tempResult.getBestCandidate().getRank() == 80);
            System.out.println("Trial number: " + i + " result: " + tempResult.toString());
            tempMaxNumberOfCompares = Math.max(tempMaxNumberOfCompares, tempResult.getNumberOfTimesComparisonFired());
            tempTotalNumberOfCompares += tempResult.getNumberOfTimesComparisonFired();
        }
        
        System.out.println("Maximum number of compares: " + tempMaxNumberOfCompares);
        System.out.println("Average number of compares: " + tempTotalNumberOfCompares/tempNumberOfTrials);
        
    }
    
    /**
     * Create ranked list of candidates
     */
    protected List<Candidate>   createRankedCandidates() {
        
        List<Candidate>     tempResults = new ArrayList<Candidate>();
        
        tempResults.add(new Candidate(10));
        tempResults.add(new Candidate(20));
        tempResults.add(new Candidate(30));
        tempResults.add(new Candidate(40));
        tempResults.add(new Candidate(50));
        tempResults.add(new Candidate(60));
        tempResults.add(new Candidate(70));
        tempResults.add(new Candidate(80));
        
        
        return tempResults;
        
    }
    
    /**
     * Randomized selection randomizes the list up front, and then does the selection.
     */
    @Test
    public void hireCandidateUsingRandomizedSelection() {
        
        HiringManager   tempManager;
        int             tempNumberOfTrials;
        HiringResult    tempResult;
        List<Candidate> tempCandidates;
        int             tempMaxNumberOfCompares = 0;
        double          tempTotalNumberOfCompares = 0;
        
        tempManager = new HiringManager();
        tempNumberOfTrials = 10;
        tempCandidates   = this.createRankedCandidates();
        for (int i = 0; i < tempNumberOfTrials; i++) {
            
            tempResult = tempManager.hireCandidateWithRandomizedSelection(tempCandidates);
            assertTrue("Best candidate not picked", tempResult.getBestCandidate().getRank() == 80);
            System.out.println("Trial number: " + i + " result: " + tempResult.toString());
            tempMaxNumberOfCompares = Math.max(tempMaxNumberOfCompares, tempResult.getNumberOfTimesComparisonFired());
            tempTotalNumberOfCompares += tempResult.getNumberOfTimesComparisonFired();
        }
        
        System.out.println("Maximum number of compares: " + tempMaxNumberOfCompares);
        System.out.println("Average number of compares: " + tempTotalNumberOfCompares/tempNumberOfTrials);
        
    }
    
    

}
