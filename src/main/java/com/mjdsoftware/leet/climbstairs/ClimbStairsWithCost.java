package com.mjdsoftware.leet.climbstairs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClimbStairsWithCost {

    public int climb(int aStairIndex, int[] aCost) {

        int tempResult;


        if (aStairIndex == 0) {
          tempResult = aCost[0];
        }
        else if (aStairIndex == 1) {
            tempResult = aCost[1];
        }
        else {

           tempResult  = aCost[aStairIndex] +
                    Math.min(this.climb(aStairIndex-1, aCost),
                                  this.climb(aStairIndex-2, aCost));
        }

        log.info("Current Step = {}, Current Cost = {}", aStairIndex, tempResult);
        return tempResult;

    }


    public static void main(String[] args) {

        int[] cost = {2,7,9,3,1,0}; //Very top is 0

        ClimbStairsWithCost tempClimber = new ClimbStairsWithCost();
        log.info("Result = {}", tempClimber.climb(cost.length-1, cost));

    }

}
