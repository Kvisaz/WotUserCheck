package ru.kvisaz.wotolenemer;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

public class Constants {
    public static String APP_NAME;
    public static String LOG_TAG;

    public static boolean IS_TABLET;

    public static String UI_MESSAGE_NO_USERS_FOUND;

    public static TypedArray colorsOfRate;
    public static int colorAccent;

    /* ---  status settings ------- */
    public static final int MIN_BATTLES = 100;
    public final static float VERYLOW_RATE = 48;
    public final static float LOW_RATE = 50;
    public final static float MID_RATE = 53;
    public final static float GOOD_RATE = 58;
    public final static float BEST_RATE = 100;

    public static String STATUS_0_NOOB;
    public static String STATUS_1_VERY_LOW;
    public static String STATUS_2_LOW;
    public static String STATUS_3_MID;
    public static String STATUS_4_GOOD;
    public static String STATUS_5_BEST;

    public static void getResources(Context context){
        APP_NAME = context.getString(R.string.app_name);
        LOG_TAG = context.getString(R.string.logTag);


        UI_MESSAGE_NO_USERS_FOUND = context.getString(R.string.ui_message_no_users_found);

        IS_TABLET = context.getResources().getBoolean(R.bool.isTablet);

        colorsOfRate = context.getResources().obtainTypedArray(R.array.colorsRate);
        colorAccent = context.getResources().getColor(R.color.colorAccent);

        STATUS_0_NOOB = context.getString(R.string.info_rate0_status);
        STATUS_1_VERY_LOW = context.getString(R.string.info_rate1_status);
        STATUS_2_LOW = context.getString(R.string.info_rate2_status);
        STATUS_3_MID = context.getString(R.string.info_rate3_status);
        STATUS_4_GOOD = context.getString(R.string.info_rate4_status);
        STATUS_5_BEST = context.getString(R.string.info_rate5_status);

        Log.d(LOG_TAG,"Constants init");
    }

}
