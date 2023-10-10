package com.mjdsoftware.leet.maxscoremult;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MaxScoreArguments {

    private int[] operationNumbers;
    private int[] multipliers;
    private int[][] memo;

    public MaxScoreArguments(int[] operationNumbers, int[] multipliers) {

        this.setOperationNumbers(operationNumbers);
        this.setMultipliers(multipliers);
        this.setMemo(new int[multipliers.length][operationNumbers.length]);
    }

    /**
     * Answer my memo value for anOperationId and aLeft coordinate
     * @param anOperationId int
     * @param aLeft int
     * @return int
     */
    public int getMemoValue(int anOperationId, int aLeft) {

        return this.memo[anOperationId][aLeft];
    }

    /**
     * Set my memo value
     * @param anOperationId int
     * @param aLeft int
     * @param aValue int
     */
    public void setMemoValue(int anOperationId, int aLeft, int aValue) {

        this.memo[anOperationId][aLeft] = aValue;
    }

    /**
     * Answer whether this is the last operation
     * @param anOperationIndex int
     * @return boolean
     */
    public boolean isLastOperation(int anOperationIndex) {
        return  anOperationIndex == this.getMultipliers().length;

    }

    /**
     * Calculate base case when this is the last operation
     * @return int
     */
    public int calculateBaseCaseWhenLastOperation() {

        return 0;
    }


    /**
     * Calculate multiplied value
     * @param anOperationIndex int
     * @param aSideIndex int
     * @return
     */
    public int calculateMultipliedValue(int anOperationIndex, int aSideIndex) {
        return this.getOperationNumbers()[aSideIndex] * this.getMultipliers()[anOperationIndex];
    }

}
