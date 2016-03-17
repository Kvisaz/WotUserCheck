package ru.kvisaz.wotolenemer.view;

import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.view.events.InputEvent;

/**
 *  show search results and other info
 */
public class OutputView {
    private final TextView textView;

    public OutputView(View rootView){
        textView = (TextView)rootView.findViewById(R.id.textView);

        // register object as subscriber host
        EventBus.getDefault().register(this);
    }

    public void setText(String message){
        textView.setText(message);
    }

    // Clean before exit
    public void bye()  {
        EventBus.getDefault().unregister(this);
    }


    // ------------------ EventBus Handling -----------------------

    // This method will be called when a InputEvent is posted
    @Subscribe
    public void onMessageEvent(InputEvent event){
        setText(event.text);
    }
}
