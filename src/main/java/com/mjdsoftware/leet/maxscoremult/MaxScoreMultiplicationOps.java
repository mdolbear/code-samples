package com.mjdsoftware.leet.maxscoremult;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class MaxScoreMultiplicationOps {

    /**
     * Answer if this is the last operation
     * @return
     */
    public int maximumScore(int anOperationIndex, int left, int right, MaxScoreArguments anArguments) {

        int tempResult;

        if (anArguments.isLastOperation(anOperationIndex)) {
            tempResult = anArguments.calculateBaseCaseWhenLastOperation();
        }
        else {

            tempResult = anArguments.getMemoValue(anOperationIndex, left);
            if (tempResult == 0) {

                tempResult =
                        Math.max(anArguments.calculateMultipliedValue(anOperationIndex, left) +
                                        this.maximumScore(anOperationIndex + 1, left + 1, right, anArguments),
                                anArguments.calculateMultipliedValue(anOperationIndex, right) +
                                        this.maximumScore(anOperationIndex + 1, left, right - 1, anArguments));

                anArguments.setMemoValue(anOperationIndex, left, tempResult);
            }

        }

        return tempResult;

    }

    public static void main(String[] args) {

        int[] numbers = {1,2,3};
        int[] multipliers = {3,2,1};
        MaxScoreArguments tempArgs;

        MaxScoreMultiplicationOps tempMultiplier = new MaxScoreMultiplicationOps();
        tempArgs = new MaxScoreArguments(numbers,
                                         multipliers);
        log.info("Result = {}", tempMultiplier.maximumScore(0,
                                                        0,
                                                       numbers.length-1,
                                                            tempArgs));

        numbers = IntStream.of(-5,-3,-3,-2,7,1).toArray();
        multipliers = IntStream.of(-10,-5,3,4,6).toArray();

        tempArgs = new MaxScoreArguments(numbers,
                                         multipliers);
        log.info("Result = {}", tempMultiplier.maximumScore(0,
                                                                       0,
                                                                      numbers.length-1,
                                                                           tempArgs));

    }

}
