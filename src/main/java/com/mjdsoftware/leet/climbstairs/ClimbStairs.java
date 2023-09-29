package com.mjdsoftware.leet.climbstairs;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ClimbStairs {

    public Long climbStairs(Long n, Map<Long, Long> aMemo) {

        Long tempResult;

        this.validateInput(n);
        tempResult = aMemo.get(n);
        if (tempResult == null) {

            if (n > 0 && n < 3 ) {
                aMemo.put(n, n);
                return n;
            }

            tempResult  = this.climbStairs(n-1, aMemo) + climbStairs(n-2, aMemo);
            aMemo.put(n, tempResult);
        }
        else {
            log.info("Found existing value for {} = {}}", n, tempResult);
        }

        return tempResult;

    }

    /**
     * Validate input value
     * @param aValue Long
     */
    private void validateInput(Long aValue) {

        if (aValue > 90l) {
            throw new IllegalArgumentException("Value too large. Will cause overflow");
        }
        else if (aValue < 0) {
            throw new IllegalArgumentException("Input value can't be negative");
        }

    }

    public static void main(String[] args) {

        Long tempResult;
        tempResult =
                (new ClimbStairs()) .climbStairs(90l, new HashMap<Long,Long>());
        log.info("Result = " + tempResult);
    }

}
