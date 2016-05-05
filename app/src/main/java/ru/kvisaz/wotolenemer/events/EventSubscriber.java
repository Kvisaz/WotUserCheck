package ru.kvisaz.wotolenemer.events;
import org.greenrobot.eventbus.EventBus;

public class EventSubscriber {
    // ------------------------ EventBus register --------------
    public void registerEventBus(){
        EventBus.getDefault().register(this);
    }
    public void unregisterEventBus(){
        EventBus.getDefault().unregister(this);
    }
}
