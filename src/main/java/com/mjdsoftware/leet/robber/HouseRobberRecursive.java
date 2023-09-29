package com.mjdsoftware.leet.robber;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HouseRobberRecursive {

    /**
     * Rob the houses. Answer the maximum sum given aMoney
     * @param aHouseIndex int
     * @param aMoney int[]
     * @return int
     */
    public int rob(int aHouseIndex, int[] aMoney) {

        int tempResult = 0;


        if (aHouseIndex == 0) {
            tempResult  = aMoney[0];
        }
        else if (aHouseIndex == 1) {
            tempResult = Math.max(aMoney[0], aMoney[1]);
        }
        else {

            tempResult = Math.max(this.rob(aHouseIndex-1, aMoney),
                                 this.rob(aHouseIndex-2, aMoney) + aMoney[aHouseIndex]);
        }


        return tempResult;

    }


    public static void main(String[] args) {

        int[] money = {2,7,9,3,1};
        HouseRobberRecursive tempRobber = new HouseRobberRecursive();
        log.info("Result = {}", tempRobber.rob(4, money));

    }
}
