package ru.kvisaz.wotolenemer.view.output;

import ru.kvisaz.wotolenemer.Constants;

public class Status {
    public static String get(int rate){
        switch (rate){
            case 0:
                return Constants.STATUS_0_NOOB;
            case 1:
                return Constants.STATUS_1_VERY_LOW;
            case 2:
                return Constants.STATUS_2_LOW;
            case 3:
                return Constants.STATUS_3_MID;
            case 4:
                return Constants.STATUS_4_GOOD;
            default:
                return Constants.STATUS_5_BEST;
        }
    }
}
