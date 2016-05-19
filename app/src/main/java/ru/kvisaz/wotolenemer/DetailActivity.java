package ru.kvisaz.wotolenemer;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import ru.kvisaz.wotolenemer.events.ShowUserInHistoryEvent;
import ru.kvisaz.wotolenemer.view.DetailBoxView;

public class DetailActivity extends AppCompatActivity {
    private DetailBoxView detailBoxView;

    private TextView titleBarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        View rootView = getWindow().getDecorView();
        detailBoxView = new DetailBoxView(rootView);

//        setupTitle();

    }

    private void setupTitle() {
        titleBarTextView = (TextView)findViewById(R.id.detail_title_bar);
        titleBarTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_navigate_before,
                0,0,0);
        titleBarTextView.setClickable(true);
        titleBarTextView.setOnClickListener(new BackPressListener());
    }

    private class BackPressListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    }

    // --------------- Subscribe & UnSubscribe Event Handlers --------------

    @Override
    protected void onStart() {
        super.onStart();
        detailBoxView.registerEventBus();
    }

    @Override
    protected void onStop() {
        super.onStop();
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


}
