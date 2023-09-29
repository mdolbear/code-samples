package com.mjdsoftware.leet.climbstairs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClimbStairsWithCostIterative {

    public int climb(int[] aCost) {

        int minimumCostResult[] = new int[aCost.length + 1];
        int aStairIndex = 2;

        while (aStairIndex < minimumCostResult.length) {

            int takeOneStep = minimumCostResult[aStairIndex-1] + aCost[aStairIndex-1];
            int takeTwoSteps = minimumCostResult[aStairIndex-2] + aCost[aStairIndex-2];
            minimumCostResult[aStairIndex]  = Math.min(takeOneStep, takeTwoSteps);
            aStairIndex++;
        }

        return minimumCostResult[minimumCostResult.length - 1];

    }


    public static void main(String[] args) {

        int[] cost = {2,7,9,3,1};
        ClimbStairsWithCostIterative tempClimber = new ClimbStairsWithCostIterative();
        log.info("Result = {}", tempClimber.climb(cost));

    }

}
