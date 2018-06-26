package com.oracle.randomizedalg.hiringproblem;

import java.util.List;

import com.oracle.randomizedalg.RandomizedAlgorithmUtils;

/**
 *
 *
 */
public class HiringManager {

    /**
     * Answer an instance for the following arguments
     */
    public HiringManager() {
       super();
    }

    
    /**
     * Hire candidate from aCandidates
     * @param aCandidates List<Candidate>
     * @return Candidate
     */
    public HiringResult hireCandidateWithRandomSelectionPermutation(List<Candidate> aCandidates) {
        
        int[]           tempRandomizedIndexes;
        Candidate       tempBestCandidate = null;
        Candidate       tempCurrent;
        int             tempNumberOfComparisons = 0;
        int             tempFirstRandomizedIndex = 0;
        
        if (!aCandidates.isEmpty()) {
            
            tempRandomizedIndexes = 
                    RandomizedAlgorithmUtils.getInstance().createRandomizedIndexList(aCandidates.size());
            tempBestCandidate = aCandidates.get(tempRandomizedIndexes[0]);
            tempFirstRandomizedIndex = tempRandomizedIndexes[0];
            
            for (int i = 1; i < aCandidates.size(); i++) {
                
                tempCurrent = aCandidates.get(tempRandomizedIndexes[i]);
                if (tempCurrent.hasHigherRankThan(tempBestCandidate)) {
                    
                    tempBestCandidate = tempCurrent;
                    tempNumberOfComparisons++;
                }
                
            }
            
        }
        
        return new HiringResult(tempBestCandidate, 
                                aCandidates.size(), 
                                tempNumberOfComparisons,
                                tempFirstRandomizedIndex);
   
    }
        
    /**
     * Hire candidate from aCandidates
     * @param aCandidates List<Candidate>
     * @return Candidate
     */
    public HiringResult hireCandidateWithRandomizedSelection(List<Candidate> aCandidates) {
        
        List<Candidate> tempRandomizedCandidates;
        Candidate       tempBestCandidate = null;
        Candidate       tempCurrent;
        int             tempNumberOfComparisons = 0;
        int             tempFirstRandomizedIndex = 0;
        
        if (!aCandidates.isEmpty()) {
                
            tempRandomizedCandidates = 
                    RandomizedAlgorithmUtils.getInstance().randomize(aCandidates);
            tempBestCandidate = tempRandomizedCandidates.get(0);
            
            for (int i = 1; i < aCandidates.size(); i++) {
                
                tempCurrent = tempRandomizedCandidates.get(i);
                if (tempCurrent.hasHigherRankThan(tempBestCandidate)) {
                    
                    tempBestCandidate = tempCurrent;
                    tempNumberOfComparisons++;
                }
                
            }
                
        }
            
            
        
        
        
        return new HiringResult(tempBestCandidate, 
                                aCandidates.size(), 
                                tempNumberOfComparisons,
                                tempFirstRandomizedIndex);
        
    }
    
    
}
