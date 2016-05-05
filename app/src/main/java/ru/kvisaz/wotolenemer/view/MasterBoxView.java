package ru.kvisaz.wotolenemer.view;

import android.view.View;

import ru.kvisaz.wotolenemer.events.EventSubscriber;

public class MasterBoxView extends EventSubscriber {

    private final InputView inputView;
    private final OutputUserList userListView;

    public MasterBoxView(View rootView){
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
