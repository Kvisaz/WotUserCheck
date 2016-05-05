package ru.kvisaz.wotolenemer;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ru.kvisaz.wotolenemer.events.ServerUserInfoEvent;
import ru.kvisaz.wotolenemer.view.DetailBoxView;

public class DetailActivity extends AppCompatActivity {
    private DetailBoxView detailBoxView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        View rootView = getWindow().getDecorView();
        detailBoxView = new DetailBoxView(rootView);
    }

    // --------------- Subscribe & UnSubscribe Event Handlers --------------

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        detailBoxView.registerEventBus();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        detailBoxView.unregisterEventBus();
    }

    // --------------- Make home buttom as back button --------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    // ------------------ EventBus Handling for Title -----------------------
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onUserInfoList(ServerUserInfoEvent event){

        if(event.userInfos==null || event.userInfos.size()<1) {
            return;
        }

        String name = event.userInfos.get(0).nickname;
        ActionBar bar = getSupportActionBar();
        if(bar!=null && name!=null){
            bar.setTitle(name);
        }
    }
}
