package com.mjdsoftware.leet.robber;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HouseRobberIterative {

    /**
     * Rob the houses. Answer the maximum sum given aMoney
     * @param aMoney int[]
     * @return int
     */
    public int rob(int[] aMoney) {

        int oddSum = 0;
        int evenSum = 0;

        if (aMoney.length > 0) {

            if (aMoney.length == 1) {
                evenSum = aMoney[0];
            }
            else if (aMoney.length == 2) {
                evenSum = aMoney[0];
                oddSum = aMoney[1];
            }
            else {

                for (int i = 0; i < aMoney.length; i++) {

                    if (i % 2 == 0) {
                        evenSum += aMoney[i];
                    }
                    else {
                        oddSum += aMoney[i];
                    }

                }

            }

        }

        return Math.max(oddSum, evenSum);
    }

    public static void main(String[] args) {

        int[] money = {2,7,9,3,1};
        HouseRobberIterative tempRobber = new HouseRobberIterative();
        log.info("Result = {}", tempRobber.rob(money));

    }

}
