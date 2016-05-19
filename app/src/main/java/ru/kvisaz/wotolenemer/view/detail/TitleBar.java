package ru.kvisaz.wotolenemer.view.detail;

import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.events.EventSubscriber;
import ru.kvisaz.wotolenemer.events.ShowUserInHistoryEvent;
import ru.kvisaz.wotolenemer.events.TitleBarTextEvent;

public class TitleBar extends EventSubscriber {
    private final TextView titleView;

    public TitleBar(View rootView){
        titleView = (TextView) rootView.findViewById(R.id.detail_title_bar);
    }

    public void setText(String text){
        titleView.setText(text);
    }

    // ------------------ EventBus Handling -----------------------
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onTitleChange(TitleBarTextEvent event){
        setText(event.title);
    }
}
