package com.mjdsoftware.leet.deleteearn;


import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DeleteAndEarn {

    public static void main(String[] args) {

        Integer[] numbers = {1,2,3,4};
        DeleteAndEarn tempEarner = new DeleteAndEarn();
        log.info("Result = {}", tempEarner.deleteAndEarn(numbers));


    }

    public int deleteAndEarn(Integer[] numbers) {

        Map<Integer, List<Integer>> tempResultsMap;
        List<Integer>               tempCalcResults = new ArrayList<>();
        Integer                     tempCurrentResult;
        OptionalInt                 tempMaxResult;

        for (int i = 0; i < numbers.length; i++) {

            tempResultsMap = new HashMap<Integer, List<Integer>>();
            this.basicDeleteAndEarn(numbers[i],
                                    numbers,
                                    tempResultsMap);

            tempCurrentResult = this.calculateResults(tempResultsMap);

            log.info("Score: {}, Target Number: {}, Results Map: {}",
                    tempCurrentResult, numbers[i], tempResultsMap);

            tempCalcResults.add(tempCurrentResult);
        }


        tempMaxResult = tempCalcResults.stream()
                                       .mapToInt(anInt -> anInt)
                                       .max();
        return tempMaxResult.getAsInt();
    }

    public void basicDeleteAndEarn(Integer aTargetNumber,
                                   Integer[] aNumbers,
                                   Map<Integer, List<Integer>> aResults) {

        aNumbers =
                this.retrieveAllTargetNumbersFrom(aTargetNumber,
                                                  aNumbers,
                                                   aResults);
        List<Integer> tempNextCandidateNumbers =
                this.getNextCandidateNumbers(aTargetNumber, aNumbers);

        log.info("Next candidates = {}", tempNextCandidateNumbers);

        for (Integer aCandidate: tempNextCandidateNumbers) {

            this.basicDeleteAndEarn(aCandidate,
                                    aNumbers,
                                    aResults);
        }

    }

    /**
     * Retrieve all aTargetNumber from aNumbers and add it to aSet
     * @param aTargetNumber Integer
     * @param aResult Map
     * @param aNumbers Integer[]
     */
    private  Integer[] retrieveAllTargetNumbersFrom(Integer aTargetNumber,
                                                    Integer[] aNumbers,
                                                    Map<Integer, List<Integer>> aResult) {

        for (int i = 0; i < aNumbers.length; i++) {

            if (aNumbers[i].equals(aTargetNumber)) {
                this.addNumberToResult(aTargetNumber, aResult);
            }
            
        }

        aNumbers =  this.removeTargetNumberFrom(aTargetNumber, aNumbers);

        return aNumbers;
    }

    /**
     * Add aNumber to aResult
     * @param aNumber Integer
     * @param aResult Map
     */
    private void addNumberToResult(Integer aNumber, Map<Integer, List<Integer>> aResult) {

        List<Integer> tempNumberList;

        tempNumberList = aResult.get(aNumber);
        if (tempNumberList == null) {

            tempNumberList = new ArrayList<Integer>();
            tempNumberList.add(aNumber);
            aResult.put(aNumber, tempNumberList);
        }
        else {
            tempNumberList.add(aNumber);
        }
    }

    /**
     * Remove aTargetNumber from aNumbers
     * @param aTargetNumber Integer
     * @param aNumbers Integer[]
     * @return Integer[]
     */
    private Integer[] removeTargetNumberFrom(Integer aTargetNumber, Integer[] aNumbers) {

        List<Integer> tempExistingNumbers = Arrays.asList(aNumbers);
        List<Integer> tempResult;

        tempResult = tempExistingNumbers.stream()
                                        .filter(num->!num.equals(aTargetNumber))
                                        .collect(Collectors.toList());

        return tempResult.toArray(new Integer[tempResult.size()]);

    }

    /**
     * Answer the next candidate numbers base on aTargetNumber
     * @param aTargetNumber Integer
     * @return List
     */
    private List<Integer> getNextCandidateNumbers(Integer aTargetNumber,
                                                  Integer[] aNumbers) {

        List<Integer> tempResult = new ArrayList<>();
        List<Integer> tempExistingNumbers = Arrays.asList(aNumbers);

        if (tempExistingNumbers.contains(aTargetNumber-1))  {
            tempResult.add(aTargetNumber-1);
        }

        if (tempExistingNumbers.contains(aTargetNumber+1))  {
            tempResult.add(aTargetNumber+1);
        }

        return tempResult;

    }

    /**
     * Calculate results from aMap
     * @param aMap Map
     */
    private Integer calculateResults(Map<Integer, List<Integer>> aMap) {

        Integer tempResult = Integer.valueOf(0);
        Collection<List<Integer>> tempValues;

        tempValues = aMap.values();
        for (List<Integer> aSpecificNumberList : tempValues) {

            Integer tempPartialSum = aSpecificNumberList.stream()
                                             .reduce(0, (a, b) -> a + b);
            tempResult += tempPartialSum;
        }

        return tempResult;

    }

}
