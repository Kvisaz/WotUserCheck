package ru.kvisaz.wotolenemer.view.output;

import ru.kvisaz.wotolenemer.Constants;

public class Rate {

    public static int get(float percentOfWins, int battles){

        if(battles<Constants.MIN_BATTLES) return 0;

        if (percentOfWins < Constants.VERYLOW_RATE) {
            return 1;
        } else if (percentOfWins < Constants.LOW_RATE) {
            return 2;
        } else if (percentOfWins < Constants.MID_RATE) {
            return 3;
        } else if (percentOfWins < Constants.GOOD_RATE) {
            return 4;
        } else { // best!
            return 5;
        }

    }
}
