package ru.kvisaz.wotolenemer.view.output;

import java.util.Random;

import ru.kvisaz.wotolenemer.R;

public class PictureGet {
    private final static float VERYLOW_RATE = 48;
    private final static float LOW_RATE = 50;
    private final static float MID_RATE = 53;
    private final static float GOOD_RATE = 58;
    private final static float BEST_RATE = 100;

    private final static Random random = new Random();

    public static int getPictureResforRating(float rating) {
        int res = R.drawable.rate2_low_kv;

        if (rating < VERYLOW_RATE) {
            res = getVeryLowPicture();
        } else if (rating < LOW_RATE) {
            res = getLowPicture();
        } else if (rating < MID_RATE) {
            res = getMidPicture();
        } else if (rating < GOOD_RATE) {
            res = getGoodPicture();
        } else { // best!
            res = getBestPicture();
        }
        return res;
    }


    private static int getVeryLowPicture() {
        return R.drawable.rate1_verylow_proh;
    }

    private static int getLowPicture() {
        return R.drawable.rate2_low_kv;
    }

    private static int getMidPicture() {

        int[] midPics = {R.drawable.rate3_mid_su100,
                        R.drawable.rate3_mid_t49
                        };
        int rand = random.nextInt(midPics.length);
        return midPics[rand];
    }

    private static int getGoodPicture() {
        return R.drawable.rate4_good_t55;
    }

    private static int getBestPicture() {
        return R.drawable.rate5_best_e100;
    }
}
