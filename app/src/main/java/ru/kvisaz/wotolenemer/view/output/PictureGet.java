package ru.kvisaz.wotolenemer.view.output;

import java.util.Random;

import retrofit2.http.GET;
import ru.kvisaz.wotolenemer.R;

public class PictureGet {

    private final static Random random = new Random();

    public static int getPictureResforRating(int rate) {
        switch(rate){
            case 0:
                return getNoobPicture();
            case 1:
                return getVeryLowPicture();
            case 2:
                return getLowPicture();
            case 3:
                return getMidPicture();
            case 4:
                return getGoodPicture();
            default:
                return getBestPicture();
        }
    }

    private static int getNoobPicture() {
        return R.drawable.rate0_noob;
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
