package ru.kvisaz.wotolenemer.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import ru.kvisaz.wotolenemer.App;
import ru.kvisaz.wotolenemer.Constants;
import ru.kvisaz.wotolenemer.Presenter;
import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.events.EventSubscriber;
import ru.kvisaz.wotolenemer.events.ShowUserInHistoryEvent;
import ru.kvisaz.wotolenemer.network.model.SharedInfo;
import ru.kvisaz.wotolenemer.view.detail.SharedButton;
import ru.kvisaz.wotolenemer.view.output.viewpager.DetailPagerAdapter;
import ru.kvisaz.wotolenemer.view.output.viewpager.ViewPagerScroller;
import ru.kvisaz.wotolenemer.view.output.viewpager.ZoomOutPageTransformer;

public class DetailBoxView extends EventSubscriber {
    @Inject
    Presenter presenter;
//    private final OutputUserInfoView outputUserInfoView;

//    private final TitleBar titleBar;
    private ViewPager mDetailPager;
    private DetailPagerAdapter mDetailPagerAdapter;

    private final SharedButton sharedButton;
    private final LinearLayout startScreenLayout;

    public DetailBoxView(View rootView){
        App.getAppComponent().inject(this);
        Log.d(Constants.LOG_TAG, "Presenter sample = " + presenter.toString());

        startScreenLayout = (LinearLayout)rootView.findViewById(R.id.start_screen);

//        outputUserInfoView = new OutputUserInfoView(rootView);
        sharedButton = new SharedButton(rootView);
//        titleBar = new TitleBar(rootView);

        mDetailPager = (ViewPager)rootView.findViewById(R.id.pager);
        AppCompatActivity activity = (AppCompatActivity)rootView.getContext();
        mDetailPagerAdapter = new DetailPagerAdapter(activity.getSupportFragmentManager());
        mDetailPager.setAdapter(mDetailPagerAdapter);

        mDetailPager.addOnPageChangeListener(new PageListener());
        mDetailPager.setPageTransformer(true, new ZoomOutPageTransformer());

        if(presenter.getCurrentUserInHistory()>0) {
            mDetailPagerAdapter.notifyDataSetChanged();
            mDetailPager.setCurrentItem(presenter.getCurrentUserInHistory());
            sharedButton.show();
        }

        // set nice animation for programmatically sliding
        // only for tablets
        if(Constants.IS_TABLET){
            ViewPagerScroller.changePagerScroller(mDetailPager,ViewPagerScroller.DURATION_800);
        }

    }


    // ------------------- register EventBus handlers  --------------
    @Override
    public void registerEventBus() {
//        outputUserInfoView.registerEventBus();
        sharedButton.registerEventBus();
//        titleBar.registerEventBus();
        EventBus.getDefault().register(this);

    }

    @Override
    public void unregisterEventBus() {
//        outputUserInfoView.unregisterEventBus();
        sharedButton.unregisterEventBus();
//        titleBar.unregisterEventBus();
        EventBus.getDefault().unregister(this);
    }

    // ------------------- handle show user event --------------
    // ------------------ EventBus Handling -----------------------
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void showUserInHistory(ShowUserInHistoryEvent event){
        startScreenLayout.setVisibility(View.GONE); // to remove our simple start screen
        mDetailPagerAdapter.notifyDataSetChanged(); // 1
        mDetailPager.setCurrentItem(event.user);    // 2
        Log.d(Constants.LOG_TAG,"postSticky showUserInHistory ");

        // we need sticky for phone design, but once
        EventBus.getDefault().removeStickyEvent(event); // eliminate double create
    }

    private class PageListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            presenter.setCurrentUserInHistory(position); // for shared button
            SharedInfo sharedInfo = presenter.getSharedInfo();

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}