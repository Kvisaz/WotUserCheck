package ru.kvisaz.wotolenemer.view;

import android.view.View;
import android.widget.TextView;

import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.events.EventSubscriber;

public class MasterBoxView extends EventSubscriber {

    private final TextView titleView;

    private final InputView inputView;
    private final OutputUserList userListView;

    public MasterBoxView(View rootView){
        titleView = (TextView)rootView.findViewById(R.id.detail_title_bar);
        inputView = new InputView(rootView);
        userListView = new OutputUserList(rootView);
    }

    // ------------------- register handlers in children --------------
    @Override
    public void registerEventBus() {
        userListView.registerEventBus();
    }

    @Override
    public void unregisterEventBus() {
        userListView.unregisterEventBus();
    }
}
