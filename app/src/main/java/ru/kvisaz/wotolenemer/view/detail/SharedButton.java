package ru.kvisaz.wotolenemer.view.detail;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.OnSheetDismissedListener;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import ru.kvisaz.wotolenemer.App;
import ru.kvisaz.wotolenemer.Constants;
import ru.kvisaz.wotolenemer.Presenter;
import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.events.EventSubscriber;
import ru.kvisaz.wotolenemer.events.FabHideEvent;
import ru.kvisaz.wotolenemer.events.FabShowEvent;
import ru.kvisaz.wotolenemer.network.model.SharedInfo;

public class SharedButton extends EventSubscriber {
    @Inject
    Presenter presenter;
    private final Context contextOfActivity;
    private final FloatingActionButton fab;
    private final BottomSheetLayout bottomSheet;

    public SharedButton(View rootView) {
        App.getAppComponent().inject(this);

        Log.d(Constants.LOG_TAG, "Presenter sample = " + presenter.toString());

        contextOfActivity = rootView.getContext();

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab_detail);
        fab.setOnClickListener(new FABListener());
        fab.hide();

        bottomSheet = (BottomSheetLayout) rootView.findViewById(R.id.bottomsheet);
    }

    public void show(){
        fab.show();
    }

    public void hide(){
        fab.hide();
    }

    // ------------------ Dismiss Bottom Sheet Listener -----------------------
    private class DismissListener implements OnSheetDismissedListener {
        @Override
        public void onDismissed(BottomSheetLayout bottomSheetLayout) {
            show();
        }
    }

    // ------------------ FABListener -----------------------
    private class FABListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            SharedInfo sharedInfo = presenter.getSharedInfo();
            if (sharedInfo == null) return;
            fab.hide();

            Log.d(Constants.LOG_TAG,"FAB user name sharedInfo.body == "+sharedInfo.body);
            Log.d(Constants.LOG_TAG,"FAB presenter current user == "+presenter.getCurrentUserInHistory());

            final Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, sharedInfo.body);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, sharedInfo.subject);


            IntentPickerSheetView intentPickerSheet = new IntentPickerSheetView(contextOfActivity, shareIntent, sharedInfo.title, new BottomSheetPickListener(shareIntent));

            bottomSheet.showWithSheetView(intentPickerSheet);
            bottomSheet.addOnSheetDismissedListener(new DismissListener()); 
        }
    }

    private class BottomSheetPickListener implements IntentPickerSheetView.OnIntentPickedListener {
        private Intent mIntent;

        public BottomSheetPickListener(final Intent intent) {
            mIntent = intent;
        }

        @Override
        public void onIntentPicked(IntentPickerSheetView.ActivityInfo activityInfo) {
            bottomSheet.dismissSheet();
            fab.show();
            contextOfActivity.startActivity(activityInfo.getConcreteIntent(mIntent));
        }
    }

    // --------------------------- Event Handlers for Phone ----------------------------
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void showFAB(FabShowEvent event) {
        show();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void hideFAB(FabHideEvent event) {
        hide();
    }


}