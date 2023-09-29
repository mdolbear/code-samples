package com.mjdsoftware.leet.tribonacci;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TribonacciSequence {

    public Integer calculate(int position, Map<Integer, Integer> aMemo) {

        Integer tempResult;

        tempResult = aMemo.get(position);
        if (tempResult == null) {

            if (position == 0) {
                tempResult = 0;
            }
            else if (position == 1) {
                tempResult = 1;
            }
            else if (position == 2) {
                tempResult = 1;
            }
            else {
                tempResult = this.calculate(position - 1, aMemo) +
                        this.calculate(position - 2, aMemo) +
                        this.calculate(position - 3, aMemo);

            }

            aMemo.put(position, tempResult);
        }
        else {
            log.info("Retrieved for position {} = result {}.", position, tempResult);
        }

        return tempResult;

    }


    public static void main(String[] args) {
        TribonacciSequence tempSeq = new TribonacciSequence();
        Map<Integer, Integer> tempMemo = new HashMap<>();

        log.info("Position = {}, Result={}", 0, tempSeq.calculate(0, tempMemo));

        log.info("Position = {}, Result={}", 1, tempSeq.calculate(1, tempMemo));

        log.info("Position = {}, Result={}", 2, tempSeq.calculate(2, tempMemo));

        log.info("Position = {}, Result={}", 3, tempSeq.calculate(3, tempMemo));


        log.info("Position = {}, Result={}", 4, tempSeq.calculate(4, tempMemo));


        log.info("Position = {}, Result={}", 5, tempSeq.calculate(5, tempMemo));


        log.info("Position = {}, Result={}", 10, tempSeq.calculate(10, tempMemo));
    }
}
