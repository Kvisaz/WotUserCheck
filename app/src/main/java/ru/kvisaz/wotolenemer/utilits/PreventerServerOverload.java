package ru.kvisaz.wotolenemer.utilits;

import android.os.SystemClock;

public class PreventerServerOverload {
    private final static int MAX_QUERIES_PER_SEC = 2;
    private static long mLastClickTime = 0;
    private final static int DELAY_IN_MS = 1000/MAX_QUERIES_PER_SEC;

    public static boolean isQueryAllowed(){
        long time = SystemClock.elapsedRealtime();
        boolean is = time - mLastClickTime >= DELAY_IN_MS;
        return is;
    }

    public static boolean isQueryDisabled(){
        return !isQueryAllowed();
    }

    public static void disableQueries(){
        mLastClickTime = SystemClock.elapsedRealtime();
    }
}
