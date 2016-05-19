package ru.kvisaz.wotolenemer.view.output.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

import ru.kvisaz.wotolenemer.Constants;

public class ViewPagerScroller extends Scroller {

    public final static int DEFAULT_DURATION = 600;
    public final static int DURATION_800 = 800;
    public final static int DOUBLE_DURATION = 1200;
    public final static int SECOND = 1000;
    private int mScrollDuration = DEFAULT_DURATION;

    public ViewPagerScroller(Context context) {
        super(context);
    }

    public ViewPagerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }

    private void setmScrollDuration(int scrollDuration){
        mScrollDuration = scrollDuration;
    }

    public static void changePagerScroller(ViewPager viewPager, int duration) {
        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(viewPager.getContext());
            scroller.setmScrollDuration(duration);
            mScroller.set(viewPager, scroller);
        } catch (Exception e) {
            Log.e(Constants.LOG_TAG, "error of change scroller ", e);
        }
    }


}
