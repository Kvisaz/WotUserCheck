package ru.kvisaz.wotolenemer.utilits;
import android.os.SystemClock;


/**
 *  Double click preventer
 *  and any double action preventer too
 */
public class DoubleClickPreventer {
    private static long mLastClickTime = 0;
    private final static int DELAY_IN_MS = 1000;

    public static boolean isAllowClick(){
        long time = SystemClock.elapsedRealtime();
        boolean is = time - mLastClickTime >= DELAY_IN_MS;
        return is;
    }

    public static boolean isDisableClick(){
        return !isAllowClick();
    }


    public static void disableClick(){
        mLastClickTime = SystemClock.elapsedRealtime();
    }

}
