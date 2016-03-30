package ru.kvisaz.wotolenemer.view;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Formatter;

import ru.kvisaz.wotolenemer.Constants;
import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.model.UserInfo;
import ru.kvisaz.wotolenemer.view.events.ViewLogEvent;
import ru.kvisaz.wotolenemer.view.events.ViewSearchUserEvent;
import ru.kvisaz.wotolenemer.view.events.ViewSelectUserIdEvent;
import ru.kvisaz.wotolenemer.view.events.ServerUserInfoEvent;
import ru.kvisaz.wotolenemer.view.events.ServerUserListEvent;
import ru.kvisaz.wotolenemer.view.events.ViewTabShowInfoEvent;
import ru.kvisaz.wotolenemer.view.events.ViewToastEvent;

/**
 *  Parent class for user list and user info
 *  used  meta-view
 *          OutputUserInfoView
 *          OutputUserList
 *  hosts all meta-view in tabview
 *
 *  get events from AsyncMaster (server)
 *  |_ command to view components
 *
 *  get log events from view components
 *  |_ log()
 *
 *  post events to AsyncMaster
 *
 *
 */
public class OutputView {
    private final Context context;


    //  view components
    public final OutputTabView tabView;

    private final OutputUserInfoView userInfoView;
    private final OutputUserList userListView;

    public OutputView(View rootView){
        context = rootView.getContext();

        tabView = new OutputTabView(rootView);
        userInfoView = new OutputUserInfoView(rootView);
        userListView = new OutputUserList(rootView);

        // register object as subscriber host
        EventBus.getDefault().register(this);
    }


    // Clean before exit
    public void close()  {
        EventBus.getDefault().unregister(this);
    }


    // ------------------ EventBus Handling -----------------------

    @Subscribe
    public void onLog(ViewLogEvent event){
        Log.d(Constants.LOGTAG, event.message);
    }

    @Subscribe
    public void onToast(ViewToastEvent event){
        Toast.makeText(context, event.text, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onUserInfoList(ServerUserInfoEvent event){
        Log.d(Constants.LOGTAG, "onUserInfoList start!---------");

        if(event.userInfos==null || event.userInfos.size()<1) {
            Log.d(Constants.LOGTAG, "Null UserInfo List size ---------");
            return;
        }
        userInfoView.show(event.userInfos.get(0));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onListUsersArrived(ServerUserListEvent event){
        Log.d(Constants.LOGTAG, "onListUsersArrived start!---------");
        if(event.users==null || event.users.size()<1) {
            Log.d(Constants.LOGTAG, "Null UserList size ---------");
            return;
        }
        // refresh user list
        userListView.show(event.users);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchToInfo(ViewTabShowInfoEvent event){
        tabView.showUserInfo();
    }

}