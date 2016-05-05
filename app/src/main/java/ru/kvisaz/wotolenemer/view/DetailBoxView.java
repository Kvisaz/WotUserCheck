package ru.kvisaz.wotolenemer.view;

import android.view.View;

import ru.kvisaz.wotolenemer.events.EventSubscriber;

public class DetailBoxView extends EventSubscriber {
    private final OutputUserInfoView outputUserInfoView;

    public DetailBoxView(View rootView){
        outputUserInfoView = new OutputUserInfoView(rootView);
    }

    // ------------------- register handlers in children --------------
    @Override
    public void registerEventBus() {
        outputUserInfoView.registerEventBus();
    }

    @Override
    public void unregisterEventBus() {
        outputUserInfoView.unregisterEventBus();
    }
}