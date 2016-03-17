package ru.kvisaz.wotolenemer.view;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import ru.kvisaz.wotolenemer.Constants;
import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.model.User;
import ru.kvisaz.wotolenemer.view.events.InputEvent;
import ru.kvisaz.wotolenemer.view.events.UserListEvent;

/**
 *  show search results and other info
 */
public class OutputView {
    private final TextView textView;

    public OutputView(View rootView){
        textView = (TextView)rootView.findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

               // register object as subscriber host
        EventBus.getDefault().register(this);
    }

    public void setText(String message){
        textView.setText(message);
    }

    // Clean before exit
    public void close()  {
        EventBus.getDefault().unregister(this);
    }


    // ------------------ EventBus Handling -----------------------

    // This method will be called when a InputEvent is posted
    @Subscribe
    public void onMessageEvent(InputEvent event){
        Log.d(Constants.LOGTAG, "onMessageEvent start!---------");
        setText(event.text);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onListUsersArrived(UserListEvent event){
        Log.d(Constants.LOGTAG, "onListUsersArrived start!---------");
        List<User> users = event.users;
        String all = "";
        int i = 1;
        for(User user:users){
            all += "\n"+i + ". " + user.nickname + " - " + user.account_id;
            i++;
        }

        setText(all);

    }

}
