package com.oracle.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 *
 */
public class SetUtils {

    private static SetUtils instance;
    
    /**
     * Answer my default instance
     * @return SetUtils
     */
    public synchronized static SetUtils getInstance() {
        
        if (instance == null) {
            
            instance = new SetUtils();
        }
        
        return instance;
    }
    
    
    /**
     * Answer an instance for the following arguments
     */
    protected SetUtils() {
        super();
    }
    
    /**
     * Answer the set that holds aT
     * @param aTSets Set<Set<Vertex>>
     * @return Set<Vertex>
     */
    public <T> Set<T> getSetFor(Set<Set<T>> aTSets,
                                T aT) {
        
        Set<T>             tempResult = null;
        Iterator<Set<T>>   tempItr;
        Set<T>             tempCurrent;
        
        tempItr = aTSets.iterator();
        while (tempItr.hasNext() && tempResult == null) {
            
            tempCurrent = tempItr.next();
            if (tempCurrent.contains(aT)) {
                
                tempResult = tempCurrent;
            }
            
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer my elements as independent sets
     * @return Set<Set<T>>
     */
    public <T> Set<Set<T>> getSetElementsAsIndependentSets(Set<T> anElements) {
        
        Set<Set<T>>    tempResults = new HashSet<Set<T>>();
        Set<T>         tempCurrentSet;
        
        for (T anElement: anElements) {
            
            tempCurrentSet = new HashSet<T>();
            tempCurrentSet.add(anElement);
            tempResults.add(tempCurrentSet);
        }
        
        return tempResults;
        
    }
    
    
    /**
     * Answer a new set without aTSet
     * @return Set<Set<T>>
     */
    public <T> Set<Set<T>> newSetWithout( Set<Set<T>> anOldSets,
                                              Set<T> aSet) {
        
        Set<Set<T>>      tempResults = new HashSet<Set<T>>();
        boolean          tempSameSets;
        
        for (Set<T> aCurrentSet: anOldSets) {
            
            tempSameSets = this.isSameSet(aSet, aCurrentSet);
            if (!tempSameSets) {
                
                tempResults.add(aCurrentSet);
            }
            
        }
        
        return tempResults;
        
    }


    /**
     * Answer whether or not aSet is the same set as aCurrentSet. The normal Set appraoch in java is
     * to compare elements, but we want to know if these are the same set instance
     * @param aSet
     * @param aCurrentSet
     * @return boolean
     */
    public <T> boolean isSameSet(Set<T> aSet, 
                                 Set<T> aCurrentSet) {
        
        return System.identityHashCode(aCurrentSet) == System.identityHashCode(aSet);
        
    }
    

}
