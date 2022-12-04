package com.example.puzzleproject.AdOfCo2021;

import lombok.Data;

@Data
public class NumberOfFish {

    long zeroDayFish = 0;
    long oneDayFish = 0;
    long twoDayFish = 0;
    long threeDayFish = 0;
    long fourDayFish = 0;
    long fiveDayFish = 0;
    long sixDayFish = 0;
    long sevenDayFish = 0;
    long eightDayFish = 0;

    public NumberOfFish(long zeroDayFish,long oneDayFish, long twoDayFish, long threeDayFish, long fourDayFish, long fiveDayFish, long sixDayFish, long sevenDayFish, long eightDayFish) {

        this.zeroDayFish = zeroDayFish;
        this.oneDayFish = oneDayFish;
        this.twoDayFish = twoDayFish;
        this.threeDayFish = threeDayFish;
        this.fourDayFish = fourDayFish;
        this.fiveDayFish = fiveDayFish;
        this.sixDayFish = sixDayFish;
        this.sevenDayFish = sevenDayFish;
        this.eightDayFish = eightDayFish;
    }

    public void addOneDay(){
        long oldZeroDayFish = zeroDayFish;

        zeroDayFish = oneDayFish;
        oneDayFish = twoDayFish;
        twoDayFish = threeDayFish;
        threeDayFish = fourDayFish;
        fourDayFish = fiveDayFish;
        fiveDayFish = sixDayFish;
        sixDayFish = sevenDayFish + oldZeroDayFish;
        sevenDayFish = eightDayFish;
        eightDayFish = oldZeroDayFish;

    }

    public long totalNumberOfFish(){
        return zeroDayFish + oneDayFish + twoDayFish + threeDayFish + fourDayFish + fiveDayFish + sixDayFish + sevenDayFish + eightDayFish;
    }
}
