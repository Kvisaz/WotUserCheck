package ru.kvisaz.wotolenemer;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import ru.kvisaz.wotolenemer.events.ServerUserInfoEvent;
import ru.kvisaz.wotolenemer.events.ServerUserListEvent;
import ru.kvisaz.wotolenemer.view.DetailBoxView;
import ru.kvisaz.wotolenemer.view.InputView;
import ru.kvisaz.wotolenemer.view.MasterBoxView;


public class MainActivity extends AppCompatActivity {

    private MasterBoxView masterBoxView;
    private DetailBoxView detailBoxView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View rootView = getWindow().getDecorView();
        masterBoxView = new MasterBoxView(rootView);

        if (Constants.IS_TABLET) {
            Log.d(Constants.LOG_TAG, " is Tablet!");
            detailBoxView = new DetailBoxView(rootView);
        }

        /*// Activity animation
        if (!Constants.IS_TABLET){
            this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
*/
    }

    // --------------- Subscribe & UnSubscribe Event Handlers --------------

    @Override
    protected void onStart() {
        super.onStart();

        masterBoxView.registerEventBus();
        if (Constants.IS_TABLET) {
            detailBoxView.registerEventBus();
        }
        else{
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        masterBoxView.unregisterEventBus();
        if (Constants.IS_TABLET) {
            detailBoxView.unregisterEventBus();
        }
        else{
            EventBus.getDefault().unregister(this);
        }

    }


    // --------------------------- Detail Event Handlers for Phone ----------------------------

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onUserInfoEvent(ServerUserInfoEvent event) {
        Context activityContext = this;
        Intent intent = new Intent(activityContext, DetailActivity.class);

        Bundle bndlAnimation = ActivityOptions.makeCustomAnimation(activityContext,R.anim.right_to_left,R.anim.left_to_right).toBundle();
        activityContext.startActivity(intent,bndlAnimation);
    }


}
